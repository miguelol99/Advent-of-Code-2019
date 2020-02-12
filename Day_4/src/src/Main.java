package src;

public class Main {
	
	public static void main(String[] args) {
		int tam = 6;
		String numero= "";
		boolean creciente = false;
		boolean par = false;
		int contador = 0;
		
		for(int i = 124075; i <= 580769; i++) {
			
			numero = "_" +Integer.toString(i) + "_";
			
			
			
			for(int j = 1; j < tam; j++) {			
				if(numero.charAt(j) <= numero.charAt(j+1)) {
					creciente = true;
				}
				else {
					creciente = false;
					break;
				}
				if(numero.charAt(j-1) != numero.charAt(j) && numero.charAt(j) == numero.charAt(j+1) && 
						numero.charAt(j+1) != numero.charAt(j+2)) {
					par = true;
				}
			}
			
			if(creciente == true && par == true) {
				contador++;
				//System.out.println(numero);			
			}
			creciente = false;
			par = false;
		}	
		System.out.println(contador);
	}    		
}



