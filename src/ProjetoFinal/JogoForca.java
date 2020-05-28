package ProjetoFinal;

import java.util.Scanner;

public class JogoForca {
	
	public static int tentativas=0;
	public static Scanner entrada = new Scanner(System.in);
	public static void main(String[] args) {
		inicio();	
	}
	
	public static void inicio(){
		System.out.println("\n\n\t\t\t\t\t\tJogo da forca\n");
		String pal = palavraOculta();
		String texto = tracos(pal);
		String letra = "";
		String[] saida = new String[texto.length()];
		for (int i = 0; i < saida.length; i++) {
			saida[i] = texto.substring(i, i+1) + " ";
		}
		for (int i = 0; i < saida.length; i++) {
			System.out.print(saida[i]);
		}
		System.out.println("\n");
		do {
			letra = letra();
			System.out.println("O usuário informou a letra: " + letra);
			tentativas++;
			saida = existeLetra(letra, pal, saida);
			for (int i = 0; i < saida.length; i++) {
				System.out.print(saida[i]);
			}
		}while(tentativas<=pal.length());
		entrada.close();
	}

	//Imprime os traços na tela
	public static String tracos(String palavra){
		String texto="";
		for (int i=0; i<palavra.length(); i++){
				texto += "_";		
		}			
		return texto;
	}
	
	//Retorna a palavra oculta
	public static String palavraOculta(){
		return "DEMOCRACIA";
	}
	
	//Solicita a letra do usuário
	public static String letra(){
		System.out.println("\n\nInforme uma letra do alfabeto: ");
		return entrada.next().toUpperCase();
	}
	
	//Verifica letra
	public static String[] existeLetra(String letra, String palavra, String[] texto){		
		int cont=0;
		for(int i=0;i<palavra.length();i++){			
			if(palavra.substring(i, i+1).equals(letra)){
				cont++;
				System.out.println("A letra " + letra + " existe no indice " + i);	
				texto[i] = letra + " ";
			}			
		}
		
		if(cont==0){
			System.out.println("A letra não existe na palavra");
		}else{
			System.out.println("A letra "+ letra +" existe " + cont + " vez(es) na palavra");
		}	
		return texto;
	}	
}