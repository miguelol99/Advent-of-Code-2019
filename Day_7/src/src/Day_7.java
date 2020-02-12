
package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_7 {
	
	static int puntero_instruccion[] = {0,0,0,0,0};
	static ArrayList<ArrayList<Integer>> filelist = new ArrayList<>();
	
	static boolean terminar = false;
	static boolean primera_iteracion = true;
	
	public static void readFile(String file, int amp) throws IOException{
		int caracter , numero = 0;
		boolean negativo = false;
		FileReader f = new FileReader(file);
		BufferedReader br = new BufferedReader(f);
		
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
				filelist.get(amp).add(numero);
				numero = 0;
			}
		}
		br.close();
	}

	@SuppressWarnings("unchecked")
	public static int computer(String file, int phase_setting, int input, int amp) throws IOException {
		int index1=0, index2=0, index3=0;
		int input1=0, input2=0;
		Scanner in = new Scanner(System.in);
		
		String codigo;
		int opcode = 0;
		int parameter_mode1, parameter_mode2, parameter_mode3;
		
		boolean first_time = true;
		
		if(primera_iteracion == true) {
			readFile(file,amp);
			if(amp == 4) {
				primera_iteracion = false;
			}
		}
		
		for(int i = puntero_instruccion[amp]; i < filelist.get(amp).size(); i = i + puntero_instruccion[amp]) {
					
			codigo =  "0000" + Integer.toString(filelist.get(amp).get(i));
			
			opcode = Integer.parseInt(codigo.substring(codigo.length()-2));	
			parameter_mode1 = codigo.charAt(codigo.length()-3) - '0';
			parameter_mode2 = codigo.charAt(codigo.length()-4) - '0';			
			parameter_mode3 = codigo.charAt(codigo.length()-5) - '0';

			if(opcode == 1 || opcode == 2 || opcode == 3 || opcode == 4 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				if(parameter_mode1 == 0) {
					index1 = filelist.get(amp).get(i+1); 
					input1 = filelist.get(amp).get(index1);
				}else {
					index1 = i+1;
					input1 = filelist.get(amp).get(index1);
				}
			}
			
			if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				if(parameter_mode2 == 0) {
					index2 = filelist.get(amp).get(i+2);
					input2 = filelist.get(amp).get(index2);
				} else {
					index2 = i+2;
					input2 = filelist.get(amp).get(index2);
				}
			}
			
			if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
				if(parameter_mode3 == 0) {
					index3 = filelist.get(amp).get(i+3);
				} else {
					index3 = (i+3);				
				}
			}
		
			if(opcode == 1 || opcode == 2) {
				
				puntero_instruccion[amp] = 4;
				
				if(opcode == 1) {
					filelist.get(amp).set(index3, (input1 + input2));
				}else {
					filelist.get(amp).set(index3, (input1 * input2));
				}
			}

			else if(opcode == 3 || opcode == 4) {
				
				puntero_instruccion[amp] = 2;
				
				if(opcode == 3) {
					if(first_time == true) {
						first_time = false;
						int n = phase_setting; //in.nextInt(); in.close();
						filelist.get(amp).set(index1, n);
					}else {
						int n = input; //in.nextInt(); in.close();
						filelist.get(amp).set(index1, n);
					}
				}else {	
					//filelist[amp] = (ArrayList<Integer>) list.clone();
					return input1;
				}
			}

			else if(opcode == 5 || opcode == 6) {
				
				puntero_instruccion[amp] = 3;
				
				if(opcode == 5) {
					if(input1 != 0) {
						puntero_instruccion[amp] = Math.abs(input2 - i);
					}
				}else {
					if(input1 == 0) {
						puntero_instruccion[amp] = Math.abs(input2 - i);
					}
				}				
			}
			
			else if(opcode == 7 || opcode == 8) {
				
				puntero_instruccion[amp] = 4;
				
				if(opcode == 7) {
					if(input1 < input2) {
						filelist.get(amp).set(index3, 1);
					}else {
						filelist.get(amp).set(index3, 0);
					}
				}else {
					if(input1 == input2) {
						filelist.get(amp).set(index3, 1);
					}else {
						filelist.get(amp).set(index3, 0);
					}
				}				
			}

			else if(opcode == 99) {
				if(amp == 4) {
					terminar = true;
				}
				break;
			}
		}
		return -1;
	}
	
	private static ArrayList<String> Perm2(String[] elem, String act, int n, int r, ArrayList<String> list) {
        if (n == 0) {
            list.add(act);
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    Perm2(elem, act + elem[i] + ",", n - 1, r,list);
                }
            }
        }
        return list;
    }
	
	public static void main(String[] args) throws IOException {
		filelist.add(new ArrayList<>());
		filelist.add(new ArrayList<>());
		filelist.add(new ArrayList<>());
		filelist.add(new ArrayList<>());
		filelist.add(new ArrayList<>());
		
		int input = 0;
		int inputMaximo = 0;
		String fichero = "prueba1.txt";

		String[] elementos = "4,5,6,7,8,9".split(",");
        int n = 6;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos
        ArrayList<String> list = new ArrayList<>();
        Perm2(elementos, "", n, r, list);
        //System.out.println(list);
        
        for(int i = 0; i < list.size();i++) {
        	
        	int phase_setting[] = {0,0,0,0,0};
        	phase_setting[0] = list.get(i).charAt(0) - '0';     
        	phase_setting[1] = list.get(i).charAt(2) - '0';
        	phase_setting[2] = list.get(i).charAt(4) - '0';
        	phase_setting[3] = list.get(i).charAt(6) - '0';
        	phase_setting[4] = list.get(i).charAt(8) - '0';
        	
        	while(terminar == false) {
        		System.out.println("Bucle wjhilw");
		        input = computer(fichero,phase_setting[0],input, 0);	        
		        input = computer(fichero,phase_setting[1],input, 1);
		        input = computer(fichero,phase_setting[2],input, 2);	
				input = computer(fichero,phase_setting[3],input, 3);						
				input = computer(fichero,phase_setting[4],input, 4);
        	}
			
			if(input>inputMaximo) {
				inputMaximo = input;
			}
       }
		
		 //System.out.println(inputMaximo);		
	}	
}
