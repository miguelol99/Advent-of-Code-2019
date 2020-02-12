package src;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		Point p = new Point();
		int charToInt = 0;
		int numero = 0;
		int sig_posicion = 0;
		int cont_linea = 1;
		String linea;
		int inic, fin;
		int distanciaMinima;
		
		ArrayList<Point> cable1 = new ArrayList<>();
		cable1.add(p.getLocation());
		
		ArrayList<Integer> dist1 = new ArrayList<>();
		dist1.add(0);
		int sum_dist1 = 0;
		
		ArrayList<Point> cable2 = new ArrayList<>();
		cable2.add(p.getLocation());
		
		ArrayList<Integer> dist2 = new ArrayList<>();
		dist2.add(0);
		int sum_dist2 = 0;
		
		ArrayList<Integer> distCruze = new ArrayList<>();
		
		FileReader f = new FileReader("/home/miguelol/AoC/Day_3/day_3.txt");
		BufferedReader br = new BufferedReader(f);
		
		while((linea = br.readLine())!= null) {
			
			for(int i = 0; i < linea.length(); i = i + sig_posicion) {
				sig_posicion = 1;
				
				for(int j = i + 1; linea.charAt(j) != ','  && linea.charAt(j) != '.'; j++) {
					
					charToInt = linea.charAt(j) - '0';
					numero = (numero*10) + charToInt;
					sig_posicion++;
				}
				if(linea.charAt(i) == 'U') {
					p.y = p.y + numero;
				}
				else if(linea.charAt(i) == 'D') {
					p.y = p.y - numero;
				}
				else if(linea.charAt(i) == 'L') {
					p.x = p.x - numero;
				}
				else if(linea.charAt(i) == 'R') {
					p.x = p.x + numero;
				}
				if(cont_linea == 1) {
					cable1.add(p.getLocation());
					sum_dist1 = sum_dist1 + numero;
					dist1.add(sum_dist1);
				}
				if(cont_linea == 2) {
					cable2.add(p.getLocation());
					sum_dist2 = sum_dist2 + numero;
					dist2.add(sum_dist2);
				}
				numero = 0;
				sig_posicion++;
			}
			p.setLocation(0, 0);
			cont_linea = 2;
		}
		br.close();
	
		for(int i = 0; i < cable1.size()-1; i++) {
			
			if(cable1.get(i).y == cable1.get(i+1).y) {
				int limite = cable1.get(i).y;		
				if(cable1.get(i).x < cable1.get(i+1).x) {
					inic = cable1.get(i).x;
					fin = cable1.get(i+1).x;
				}
				else {
					inic = cable1.get(i+1).x;
					fin = cable1.get(i).x;
				}
				
				for(int j = 0; j < cable2.size()-1; j++) {
					if(cable2.get(j).x == cable2.get(j+1).x) {
						if(cable2.get(j).x > inic && cable2.get(j).x < fin	) {
							if((cable2.get(j).y > limite && cable2.get(j+1).y < limite) ||
									(cable2.get(j).y < limite && cable2.get(j+1).y > limite)) {
								distCruze.add(dist1.get(i) + Math.abs(cable1.get(i).x - cable2.get(j).x) +
										dist2.get(j) + Math.abs(cable2.get(j).y - cable1.get(i).y));
								//distCruze.add(Math.abs(limite) + Math.abs(cable2.get(j).x));
							}
						}
					}
				}
			}
			
			if(cable1.get(i).x == cable1.get(i+1).x) {			
				int limite = cable1.get(i).x;
				if(cable1.get(i).y < cable1.get(i+1).y) {
					inic = cable1.get(i).y;
					fin = cable1.get(i+1).y;
				}
				else {
					inic = cable1.get(i+1).y;
					fin = cable1.get(i).y;
				}
				
				for(int j = 0; j < cable2.size()-1; j++) {
					if(cable2.get(j).y == cable2.get(j+1).y) {
						if(cable2.get(j).y > inic && cable2.get(j).y < fin	) {
							if((cable2.get(j).x > limite && cable2.get(j+1).x < limite) ||
									(cable2.get(j).x < limite && cable2.get(j+1).x > limite)) {
								distCruze.add(dist1.get(i) + Math.abs(cable1.get(i).y - cable2.get(j).y) +
										dist2.get(j) + Math.abs(cable2.get(j).x - cable1.get(i).x));
									//distCruze.add(Math.abs(limite) + Math.abs(cable2.get(j).y));
							}
						}
					}
				}
			}
		}
		distanciaMinima = distCruze.get(0);
		for(int i = 0; i<distCruze.size();i++) {
			if(distCruze.get(i) < distanciaMinima) {
				distanciaMinima = distCruze.get(i);
			}
			System.out.print(distCruze.get(i) + " ");
		}
		System.out.println();
		System.out.print(distanciaMinima);
		//System.out.println(dist1);
		//System.out.println(dist2);
	}

}
