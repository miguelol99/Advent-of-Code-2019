package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class prueba {
	
	static int puntero[] = {0,0,0,0,0};
	static boolean first_time[] = {true,true,true,true,true};;
	static boolean terminar = false;
	static int input = 0;
	
	/*************************************************************************************************************************************************/
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
	
	/*************************************************************************************************************************************************/
	public static ArrayList<Integer> readFile(FileReader fr) throws IOException{
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Integer> list = new ArrayList<>();
		int caracter = 0;
		int numero = 0;
		boolean negativo = false;
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
	
	/*************************************************************************************************************************************************/
	public static ArrayList<Integer> computer(ArrayList<Integer> lista, int phase_setting, int amp) throws IOException {
			int index1=0;
			int index2=0;
			int index3=0;
			int input1=0;
			int input2=0;
			
			String codigo;
			int opcode = 0;
			int parameter_mode1, parameter_mode2, parameter_mode3;
			
			int inicio = puntero[amp];
			
			for(int i = inicio ; i < lista.size(); i = i + puntero[amp]) {
						
				codigo =  "0000" + Integer.toString(lista.get(i));
				
				opcode = Integer.parseInt(codigo.substring(codigo.length()-2));	
				parameter_mode1 = codigo.charAt(codigo.length()-3) - '0';
				parameter_mode2 = codigo.charAt(codigo.length()-4) - '0';			
				parameter_mode3 = codigo.charAt(codigo.length()-5) - '0';

				if(opcode == 1 || opcode == 2 || opcode == 3 || opcode == 4 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
					if(parameter_mode1 == 0) {
						index1 = lista.get(i+1); 
						input1 = lista.get(index1);
					}else {
						index1 = i+1;
						input1 = lista.get(index1);
					}
				}
				
				if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
					if(parameter_mode2 == 0) {
						index2 = lista.get(i+2);
						input2 = lista.get(index2);
					} else {
						index2 = i+2;
						input2 = lista.get(index2);
					}
				}
				
				if(opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6 || opcode == 7 || opcode == 8) {
					if(parameter_mode3 == 0) {
						index3 = lista.get(i+3);
					} else {
						index3 = (i+3);				
					}
				}
			
				if(opcode == 1 || opcode == 2) {
					
					puntero[amp] = 4;
					
					if(opcode == 1) {
						lista.set(index3, (input1 + input2));
					}else {
						lista.set(index3, (input1 * input2));
					}
				}

				else if(opcode == 3 || opcode == 4) {
					
					puntero[amp] = 2;
					
					if(opcode == 3) {
						if(first_time[amp] == true) {
							first_time[amp] = false;
							int n = phase_setting; //in.nextInt(); in.close();
							lista.set(index1, n);
						}else {
							int n = input; //in.nextInt(); in.close();
							lista.set(index1, n);
						}
					}else {	
						puntero[amp] = i + puntero[amp];
						input = input1;
						return lista;  //System.out.println(input1);
					}
				}

				else if(opcode == 5 || opcode == 6) {
					
					puntero[amp] = 3;
					
					if(opcode == 5) {
						if(input1 != 0) {
							puntero[amp] = Math.abs(input2 - i);
						}
					}else {
						if(input1 == 0) {
							puntero[amp] = Math.abs(input2 - i);
						}
					}				
				}
				
				else if(opcode == 7 || opcode == 8) {
					
					puntero[amp] = 4;
					
					if(opcode == 7) {
						if(input1 < input2) {
							lista.set(index3, 1);
						}else {
							lista.set(index3, 0);
						}
					}else {
						if(input1 == input2) {
							lista.set(index3, 1);
						}else {
							lista.set(index3, 0);
						}
					}				
				}

				else if(opcode == 99) {		
					terminar = true;
					break;
				}else {
			        System.out.println("ERROR. OPCODE NO VALIDO");
				}
			}
			return lista;
	}
	
	/*************************************************************************************************************************************************/
	public static void main(String[] args) throws IOException {
		int salida;
		
		//CREA UNA LISTA CON TODAS LAS POSIBLES COMBINACIONES DE PHASE_SETTINGS
		String[] elementos = "5,6,7,8,9".split(",");
        int n = 5;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos
        ArrayList<String> lista_elementos = new ArrayList<>();
        Perm2(elementos, "", n, r, lista_elementos);
        //System.out.println("CHECK 1: " + lista_elementos);
        
        //ABRE EL FICHERO Y LO PONE EN 5 LISTAS, UNA POR CADA AMPLIFICADOR
        String fichero = "Day_7.txt";
        FileReader fr;
      
        int phase_setting[] = {0,0,0,0,0};
        int input_max = 0;
        for(int i = 0; i < lista_elementos.size();i++) {
        	
        	fr = new FileReader(fichero);
            ArrayList<Integer> lista0 = readFile(fr);
            //System.out.println("CHECK 2: " + lista0);

            fr = new FileReader(fichero);
            ArrayList<Integer> lista1 = readFile(fr);
            fr = new FileReader(fichero);
            ArrayList<Integer> lista2 = readFile(fr);
            fr = new FileReader(fichero);
            ArrayList<Integer> lista3 = readFile(fr);
            fr = new FileReader(fichero);
            ArrayList<Integer> lista4 = readFile(fr);
        	
        	phase_setting[0] = lista_elementos.get(i).charAt(0) - '0';     
        	phase_setting[1] = lista_elementos.get(i).charAt(2) - '0';
        	phase_setting[2] = lista_elementos.get(i).charAt(4) - '0';
        	phase_setting[3] = lista_elementos.get(i).charAt(6) - '0';
        	phase_setting[4] = lista_elementos.get(i).charAt(8) - '0';
        	
        	while(terminar == false) {
		        lista0 = computer(lista0,phase_setting[0], 0);	
		        lista1 = computer(lista1,phase_setting[1], 1);
		        lista2 = computer(lista2,phase_setting[2], 2);	
		        lista3 = computer(lista3,phase_setting[3], 3);	
		        lista4 = computer(lista4,phase_setting[4], 4);
		        System.out.println(input);
        	}
        	if(input > input_max) {
				input_max = input;
			}
        	
        	terminar = false;
        	puntero[0] = 0;
        	puntero[1] = 0;
        	puntero[2] = 0;
        	puntero[3] = 0;
        	puntero[4] = 0;
        	first_time[0] = true;
        	first_time[1] = true;
        	first_time[2] = true;
        	first_time[3] = true;
        	first_time[4] = true;			
			input = 0;
       }
		
		 System.out.println(input_max);
	}	
}