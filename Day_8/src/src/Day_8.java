package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day_8 {

	public static void main(String[] args) throws IOException {
		int width = 25;
		int height = 6;
		int character = 0;
		int contador = 0;
		int layer = 0;
		int num0 = 0;
		int min0 = 0;
		int num1 = 0;
		int num2 = 0;
		int output = 1;
		int minlayer=0, layer_max=0;
		ArrayList<ArrayList<Integer>> layers = new ArrayList<>();
		layers.add(new ArrayList<>());
		ArrayList<Integer> picture = new ArrayList<>();
		
		
		FileReader fr = new FileReader("Day_8.txt");
		BufferedReader br = new BufferedReader(fr);
		
		while((character = br.read()) >= '0' && character <= '9') {
			contador++;
			if(contador == (width*height)+1) {
				layers.add(new ArrayList<>());
				layer++;
				contador = 1;
			}
			layers.get(layer).add(character-'0');
			picture.add(2);
		}
		
		for(int cont = 0; cont < layers.size(); cont++) {
			for(int i = 0; i < height*width; i++) {
				if(picture.get(i) == 2) {
					picture.set(i, layers.get(cont).get(i));
				}
			}
		}
		

			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(picture.get(j + (i*width)) == 0) {
						System.out.print(" ");
					}
					else if(picture.get(j + (i*width)) == 1) {
						System.out.print("1");
					}
				}
				System.out.println();
			}
	}
}
