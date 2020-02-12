package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		int caracter , numero = 0;
		int index1=0, index2=0, index3=0;
		int input1=0, input2=0;
		boolean negativo = false;
		Scanner in = new Scanner(System.in);
		
		String codigo;
		int opcode = 0;
		int puntero_instruccion = 0;
		int parameter_mode1, parameter_mode2, parameter_mode3;
		
		ArrayList<Integer> list = new ArrayList<>();
		FileReader f = new FileReader("prueba1.txt");
		BufferedReader br = new BufferedReader(f);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

		for(int i = 0; i < list.size(); i = i + puntero_instruccion) {
					
			codigo =  "0000" + Integer.toString(list.get(i));
			
			opcode = Integer.parseInt(codigo.substring(codigo.length()-2));	
			parameter_mode1 = codigo.charAt(codigo.length()-3) - '0';
			parameter_mode2 = codigo.charAt(codigo.length()-4) - '0';			
			parameter_mode3 = codigo.charAt(codigo.length()-5) - '0';

			if(opcode == 1 || opcode == 2 || opcode == 3 || opcode == 4 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				if(parameter_mode1 == 0) {
					index1 = list.get(i+1); 
					input1 = list.get(index1);
				}else {
					index1 = i+1;
					input1 = list.get(index1);
				}
			}
			
			if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				if(parameter_mode2 == 0) {
					index2 = list.get(i+2);
					input2 = list.get(index2);
				} else {
					index2 = i+2;
					input2 = list.get(index2);
				}
			}
			
			if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				if(parameter_mode3 == 0) {
					index3 = list.get(i+3);
				} else {
					index3 = (i+3);				
				}
			}
		
			if(opcode == 1 || opcode == 2) {
				
				puntero_instruccion = 4;
				
				if(opcode == 1) {
					list.set(index3, (input1 + input2));
				}else {
					list.set(index3, (input1 * input2));
				}
			}

			else if(opcode == 3 || opcode == 4) {
				
				puntero_instruccion = 2;
				
				if(opcode == 3) {
					int n = in.nextInt(); in.close();
					list.set(index1, n);
				}else {
					System.out.println(input1);
				}
			}

			else if(opcode == 5 || opcode == 6) {
				
				puntero_instruccion = 3;
				
				if(opcode == 5) {
					if(input1 != 0) {
						puntero_instruccion = Math.abs(input2 - i);
					}
				}else {
					if(input1 == 0) {
						puntero_instruccion = Math.abs(input2 - i);
					}
				}				
			}
			
			else if(opcode == 7 || opcode == 8) {
				
				puntero_instruccion = 4;
				
				if(opcode == 7) {
					if(input1 < input2) {
						list.set(index3, 1);
					}else {
						list.set(index3, 0);
					}
				}else {
					if(input1 == input2) {
						list.set(index3, 1);
					}else {
						list.set(index3, 0);
					}
				}				
			}

			else if(opcode == 99) {
				break;
			}
		}
	}
}