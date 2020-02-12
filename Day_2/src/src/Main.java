package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		int caracter, numero = 0;
		int index1, index2, index3;
		int input1, input2;
		int nombre = 0, verbo = 0;
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		FileReader f = new FileReader("Day_2.txt");
		BufferedReader br = new BufferedReader(f);
		
		while((caracter = br.read()) != -1) {
			if((caracter = caracter - '0') >= 0) {
				numero = (numero  * 10) + caracter;	
			}
			else {
				list2.add(numero);
				numero = 0;
			}
		}
		br.close();
		
		for(int j = 0; j < 100; j++) {
			for(int k = 0; k < 100; k++) {
				
				list = (ArrayList<Integer>) list2.clone();
				list.set(1, j);
				list.set(2, k);
				
				for(int i = 0; i < list.size(); i = i + 4) {
					if(list.get(i) == 1) {
						index1 = list.get(i+1);
						index2 = list.get(i+2);				
						index3 = list.get(i+3);
						
						input1 = list.get(index1);
						input2 = list.get(index2);
						list.set(index3, (input1 + input2));
					}
					else if(list.get(i) == 2) {
						index1 = list.get(i+1);
						index2 = list.get(i+2);
						index3 = list.get(i+3);
						
						input1 = list.get(index1);
						input2 = list.get(index2);
						list.set(index3, (input1 * input2));
					}
					else if(list.get(i) == 99) {
						break;
					}
				}
				if(list.get(0) == 19690720) {
					nombre = j;
					verbo = k;
					break;
				}
			}
			if(list.get(0) == 19690720) {
				break;
			}
		}
		System.out.println(list);
		System.out.println("Nombre: " + nombre + " Verbo: "+ verbo);
	}	
}
