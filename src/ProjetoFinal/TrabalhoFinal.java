package ProjetoFinal;

import java.util.Scanner;

public class TrabalhoFinal {

	static Scanner xuxu = new Scanner(System.in);

	public static void main(String[] args) {
		inicio();
	}

	public static void inicio() {
		
		login(criarCpf(), criarSenha());
	}

	public static String[] criarCpf() {
		String[] arrayU = new String[3];
		arrayU[0] = "12345678910";
		arrayU[1] = "67889661000";
		arrayU[2] = "98768756090";
		return arrayU;
	}

	public static String[] criarSenha() {
		String[] arrayS = new String[3];
		arrayS[0] = "123";
		arrayS[1] = "329";
		arrayS[2] = "890";
		return arrayS;
	}

	public static boolean login(String[] arrayU, String[] arrayS) {
		boolean password_tuco = false;
		boolean password_cpf = false;
		int contU = 0;
		int contS = 0;
		System.out.println("\n\t\t\t\t\t\t\t\t\t\tBem vindo ao menu de jogos! \n" + "Porfavor insira seU CPF: \n" + "CPF:");
		String cpf = xuxu.nextLine();
		System.out.println("SENHA:");
		String pass = xuxu.nextLine();
		for (contU = 0; contU < arrayU.length; contU++) {
			if (cpf.equals(arrayU[contU])) {
				password_cpf = true;
				break;
			}
		}
		for (contS = 0; contS < arrayS.length; contS++) {
			if (pass.equals(arrayS[contS])) {
				password_tuco = true;
				break;
			}
		}
		telaInicial(password_cpf, password_tuco);
		return password_tuco;
	}

	public static void telaInicial(boolean password_tuco, boolean password_cpf) {
		int jogo = 0;
		if (password_tuco == false || password_cpf == false) {
			System.out.println("Algo deu errado, tente outra senha ou cpf!");
		} else {
			System.out.println("Qual jogo deseja jogar? \n" + "(1) JOGO DA FORCA \n" + "(2) JOGO DA VELHA \n"
					+ "(3) BATALHA NAVAL \n" + "JOGO:");
			jogo = xuxu.nextInt();
			switch (jogo) {
			case 1:
				JogoForca.inicio();
				break;
			case 2:
				jogoDaVelha.inicio();
				break;
			case 3:
				BatalhaNaval.bemVindo();
				break;	
			default:
				System.out.println("\n\nValor informado inválido!");

			}

		}
	}
}
