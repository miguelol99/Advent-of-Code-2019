package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_9 {
	
	public static ArrayList<Long> readFile(String file) throws IOException{
		int caracter;
		long numero = 0;
		boolean negativo = false;
		ArrayList<Long> list = new ArrayList<>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		while((caracter = br.read()) != -1) {
			caracter = caracter - '0';			
			if(caracter  == '-' - '0'){
				negativo = true;
				continue;
			}
			if(caracter >= 0) {
				numero = (numero  * 10) + caracter;	
			}
			else {
				if(negativo == true) {
					numero = numero * -1;
					negativo = false;
				}
				list.add(numero);
				numero = 0;
			}
		}
		br.close();
		return list;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Long> list = readFile("Day_9.txt");
		System.out.print(list);
		System.out.println();
		long adress1 = 0, content1 = 0;
		long adress2 = 0, content2 = 0; 
		long adress3 = 0;		
		long puntero_instruccion = 0;
		String codigo;
		int opcode = 0;		
		int parameter_mode1;
		int parameter_mode2;
		int parameter_mode3;
		Scanner in = new Scanner(System.in);
		
		long relative_base = 0;
		
		for(long i = 0; i < list.size(); i = i + puntero_instruccion) {
			
			codigo =  "0000" + Long.toString(list.get((int) i));
			
			opcode = Integer.parseInt(codigo.substring(codigo.length()-2));	
			parameter_mode1 = codigo.charAt(codigo.length()-3) - '0';
			parameter_mode2 = codigo.charAt(codigo.length()-4) - '0';			
			parameter_mode3 = codigo.charAt(codigo.length()-5) - '0';

/**************/
			if(opcode != 99) {
				
				if(parameter_mode1 == 0) {
					adress1 = list.get((int) (i+1)); 
					while(adress1 > (list.size()-1)) {
						list.add((long) 0);
					}		
					content1 = list.get((int) adress1);					
				}
				
				else if(parameter_mode1 == 1) {
					adress1 = (i+1);
					content1 = list.get((int) adress1);
				}
				
				else if(parameter_mode1 == 2){
					adress1 = list.get((int) (i+1)) + relative_base;
					if(adress1 < 0) {
						adress1 = 0;
					}
					while(adress1 > (list.size()-1)) {
						list.add((long) 0);
					}
					content1 = list.get((int) adress1);	
				}
			}
			
			if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				
				if(parameter_mode2 == 0) {
					adress2 = list.get((int) (i+2)); 
					while(adress2 > (list.size()-1)) {
						list.add((long) 0);
					}		
					content2 = list.get((int) adress2);					
				}
				
				else if(parameter_mode2 == 1) {
					adress2 = (i+2);
					content2 = list.get((int) adress2);
				}
				
				else if(parameter_mode2 == 2){
					adress2 = list.get((int) (i+2)) + relative_base;
					if(adress2 < 0) {
						adress2 = 0;
					}
					while(adress2 > (list.size()-1)) {
						list.add((long) 0);
					}
					content2 = list.get((int) adress2);	
				}
			}
			
			if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				
				if(parameter_mode3 == 0) {
					adress3 = list.get((int) (i+3)); 
					while(adress3 > (list.size()-1)) {
						list.add((long) 0);
					}						
				}
				
				else if(parameter_mode3 == 1) {
					adress3 = (i+3);
				}
				
				else if(parameter_mode3 == 2){
					adress3 = list.get((int) (i+3)) + relative_base;
					if(adress3 < 0) {
						adress3 = 0;
					}
					while(adress3 > (list.size()-1)) {
						list.add((long) 0);
					}
				}
			}
		
/**************/
			if(opcode == 1 || opcode == 2) {
				
				puntero_instruccion = 4;
				
				if(opcode == 1) {
					list.set((int) adress3, (content1 + content2));
				}
				
				else if(opcode == 2) {
					list.set((int) adress3, (content1 * content2));
				}
			}

			else if(opcode == 3 || opcode == 4 || opcode == 9) {
				
				puntero_instruccion = 2;
				
				if(opcode == 3) {
					Long n = in.nextLong(); in.close();
					list.set((int) adress1, n);
				}
				
				else if(opcode == 4){
					System.out.println(content1);
				}
				
				else if(opcode == 9) {
					if((content1 + relative_base) < 0) {
						relative_base = 0;
					}
					else {
						relative_base = relative_base + content1;
					}
				}
			}

			else if(opcode == 5 || opcode == 6) {
				
				puntero_instruccion = 3;
				
				if(opcode == 5) {
					if(content1 != 0) {
						puntero_instruccion = content2 - i;
					}
				}
				
				else if(opcode == 6){
					if(content1 == 0) {
						puntero_instruccion = content2 - i;
					}
				}				
			}
			
			else if(opcode == 7 || opcode == 8) {
				
				puntero_instruccion = 4;
				
				if(opcode == 7) {
					if(content1 < content2) {
						list.set((int) adress3, (long) 1);
					}else {
						list.set((int) adress3, (long) 0);
					}
				}
				
				else if(opcode == 8){
					if(content1 == content2) {
						list.set((int) adress3, (long) 1);
					}else {
						list.set((int) adress3, (long) 0);
					}
				}				
			}

			else if(opcode == 99) {
				System.out.println();
				System.out.println("EJECUCION TERMINADA CON EXITO");
				break;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
