package src;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_10 {
	static int numLine = 0;
	
	public static ArrayList<Point> readFile(String file) throws IOException{
		ArrayList<Point> asteroids = new ArrayList<>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		while((line = br.readLine()) != null) {
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == '#') {
					asteroids.add(new Point(i, numLine));
				}
			}
			numLine++;
		}				
		br.close();
		return asteroids;
	}

	//y = mx + b;
	public static void main(String[] args) throws IOException {
		ArrayList<Point> asteroids= readFile("prueba1.0.txt");
		int pendiente;
		
		for(Point p:asteroids) {
			System.out.print("(" + p.x + ", " + p.y + ") ");
		}
		System.out.println();
		
		for(Point p1 : asteroids) {
			for(Point p2 :asteroids) {
				if(!p1.equals(p2)) {
					if((p2.x - p1.x) != 0) {
						pendiente = (p2.y - p1.y) / (p2.x - p1.x);
						System.out.println("(" + p1.x + ", " + p1.y + ") (" + p2.x + ", " + p2.y + ")  ");
						System.out.println(pendiente);
					}
				}
			}
		}
		

		
		for(Point p:asteroids) {
			
		}
		
		
	}

}
