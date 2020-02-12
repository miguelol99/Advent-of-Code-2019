package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static int calculateFuel(int mass) {
		int totalFuel = 0;
		int fuel =  0;
		
		while(fuel >= 0) {
			fuel = (mass/3) - 2;
			if(fuel < 0) {
				break;
			}	
			totalFuel = totalFuel + fuel;	
			mass = fuel;
		}
		return totalFuel;
	}

	public static void main(String[] args) throws IOException {
		String linea;
		int fuel = 0;
		FileReader f = new FileReader("/home/miguelol/AoC/Day_1/day1.txt");
		BufferedReader br = new BufferedReader(f);
		while((linea = br.readLine())!= null) {
	          fuel = fuel + calculateFuel(Integer.parseInt(linea));
	      }		
	    System.out.println(fuel);
		br.close();
	}

}
