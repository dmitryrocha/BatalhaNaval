package ProjetoFinal;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class jogoDaVelha {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		inicio();
	}

	public static void inicio() {
		do {
			System.out.println("\n\t\t\t\t\t\t\t\t\tBem Vindo ao Jogo da Velha\n");
			String[][] tab = contruirTabuleiro();
			String modo = modoJogo(), marcadorJogador1 = obterMarcador();
			System.out.println("\n\nVocê optou pelo jogo " + modo + "usando o marcador " + marcadorJogador1);
			if (modo.equals("com outro jogador "))
				jogadorJogador(marcadorJogador1, tab);
			else
				jogadorComputador(marcadorJogador1, tab);
		} while (JogarNovamente());
		sc.close();
	}

	public static String[][] contruirTabuleiro() {
		String[][] tab = new String[3][3];

		for (int i = 0; i < tab.length; i++) {
			if (i == 0)
				System.out.println("     0   1   2");
			System.out.print(i + "   ");
			for (int j = 0; j < tab[i].length; j++) {
				if (j != 2)
					System.out.print("   |");
				tab[i][j] = " ";
			}
			if (i != 2)
				System.out.println("\n    -----------");
		}
		return tab;
	}
	
	public static String modoJogo() {
		int modo = 0;
		String texto = "";
		do {
			System.out.println("\n\nEscolha como quer jogar: (1) jogador X Jogador ou (2) Jogador X Computador");
			System.out.print("Digite aqui sua escolha: ");
			try {
				modo = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("\nValor informado inválido, por favor, informe novamente!");
				sc.nextLine();// Descarta a entrada errada e solicita novamente
			}
		} while (modo != 1 && modo != 2);
		if (modo == 1)
			texto = "com outro jogador ";
		else
			texto = "com o computador ";
		return texto;
	}

	public static String obterMarcador() {
		String marcador = "";
		do {
			System.out.println("\n\nAgora escolha qual é o seu jogador: ('X') Jogador X ou ('O') Jogador O");
			System.out.print("Digite aqui sua escolha: ");
			marcador = sc.next().toUpperCase();
		} while (!(marcador.equals("X") || marcador.equals("O")));
		return marcador;
	}
	
	//Verifica se a posição informada é válida
	public static int[] validarPosicaoJogada() {
		int[] posicao = { 10, 10 };// Inicia com valores incorretos
		do {
			System.out.println("\n\nFaça sua jogada identificando a linha e a coluna em um intervalo\n"
					+ "fechado de 0 a 2 conforme a posição indicada no tabuleiro.\n");
			try {
				System.out.print("Digite aqui a escolha da linha: ");
				posicao[0] = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nValor informado inválido, por favor, informe novamente!");
				sc.nextLine();// Descarta a entrada errada
			}

			if (posicao[0] != 10) {//Verifica se conseguiu ler a linha corretamente
				try {
					System.out.print("Agora digite aqui a escolha da coluna: ");
					posicao[1] = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nValor informado inválido, por favor, informe novamente!");
					sc.nextLine();// Descarta a entrada errada
				}
			}
		} while ((posicao[0] < 0 || posicao[0] > 2) || (posicao[1] < 0 || posicao[1] > 2));
		return posicao;
	}

	// Verifica se a posição que o jogador tentor jogar já foi preenchida
	public static boolean validarJogada(int[] posicao, String[][] tab) {
		boolean teste = false;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if (i == posicao[0] && j == posicao[1]) {
					if (!tab[i][j].equals(" "))
						System.out.println("\n\nEssa posição já foi preenchida, jogue novamente!");
					else
						teste = true;
				}
			}
		}
		return teste;
	}

	public static void efetuarJogada(int[] posicao, String marcador, String[][] tab) {
		if (validarJogada(posicao, tab)) {
			for (int i = 0; i < tab.length; i++) {
				if (i == 0)
					System.out.println("\n     0   1   2\n");
				System.out.print(i + "   ");
				for (int j = 0; j < tab[i].length; j++) {
					if ((i == posicao[0] && j == posicao[1]) && j != 2) {
						System.out.print(" " + marcador + " |");
						tab[i][j] = marcador;
					} else if (i == posicao[0] && j == posicao[1] && j == 2) {
						System.out.print(" " + marcador + " ");
						tab[i][j] = marcador;
					} else if (j != 2)
						System.out.print(" " + tab[i][j] + " |");
					else if (j == 2)
						System.out.print(" " + tab[i][j] + " ");
				}
				if (i != 2)
					System.out.println("\n    -----------");
			}
		} else
			efetuarJogada(validarPosicaoJogada(), marcador, tab);
	}
	
	//Modo de jogo jogador contra jogador
	public static void jogadorJogador(String marcadorJogador1, String[][] tab) {
		int jogadas = 0;
		String marcadorJogador2 = "";

		// Definir o marcador do jogador 2
		if (marcadorJogador1.equals("X"))
			marcadorJogador2 = "O";
		else if (marcadorJogador1.equals("O"))
			marcadorJogador2 = "X";

		while (true) {
			if (!(ganhou(jogadas, tab, marcadorJogador1, marcadorJogador2).equals("0")))
				break;

			// Jogadas do jogador 1
			System.out.print("\n\nJogador 1:");
			efetuarJogada(validarPosicaoJogada(), marcadorJogador1, tab);
			jogadas++;

			if (!(ganhou(jogadas, tab, marcadorJogador1, marcadorJogador2).equals("0")))
				break;

			// Jogadas do Jogador 2
			System.out.print("\n\nJogador 2:");
			if (marcadorJogador1.equals("X"))
				efetuarJogada(validarPosicaoJogada(), "O", tab);
			else if (marcadorJogador1.equals("O"))
				efetuarJogada(validarPosicaoJogada(), "X", tab);
			jogadas++;

			if (!(ganhou(jogadas, tab, marcadorJogador1, marcadorJogador2).equals("0")))
				break;
		}

		System.out.println("\n\n" + ganhou(jogadas, tab, marcadorJogador1, marcadorJogador2));
	}

	//Valida a jogada do computador
	public static int[] jogadaComputador(String[][] tab) {
		int[] posicao = new int[2];
		Random random = new Random();
		boolean b = true;
		while (b) {
			posicao[0] = random.nextInt(3);
			posicao[1] = random.nextInt(3);
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[i].length; j++) {
					if (i == posicao[0] && j == posicao[1]) {
						// Verifica se a posição que o computador tentou jogar não foi preenchida
						if (tab[i][j].equals(" "))
							b = false;
					}
				}
			}
		}
		return posicao;
	}

	//Modo de jogo jogador contra computador
	public static void jogadorComputador(String marcadorJogador1, String[][] tab) {
		int jogadas = 0;
		String marcadorComputador = "";

		// Definir o marcador do Computador
		if (marcadorJogador1.equals("X"))
			marcadorComputador = "O";
		else if (marcadorJogador1.equals("O"))
			marcadorComputador = "X";

		while (true) {
			if (!(ganhou(jogadas, tab, marcadorJogador1, marcadorComputador).equals("0")))
				break;

			// Jogadas do jogador 1
			System.out.print("\n\nJogador 1:");
			efetuarJogada(validarPosicaoJogada(), marcadorJogador1, tab);
			jogadas++;

			if (!(ganhou(jogadas, tab, marcadorJogador1, marcadorComputador).equals("0")))
				break;

			// Jogadas do Computador
			System.out.print("\n\n\nJogada do Computador:");
			if (marcadorJogador1.equals("X"))
				efetuarJogada(jogadaComputador(tab), "O", tab);
			else if (marcadorJogador1.equals("O"))
				efetuarJogada(jogadaComputador(tab), "X", tab);
			jogadas++;

			if (!(ganhou(jogadas, tab, marcadorJogador1, marcadorComputador).equals("0")))
				break;
		}

		System.out.println("\n\n" + ganhou(jogadas, tab, marcadorJogador1, marcadorComputador));
	}
	
	public static String ganhou(int jogadas, String[][] tab, String marcadorJogador1, String marcadorJogador2) {
		String vencedor = "0";
		String[] possibilidades = new String[8];
		if (jogadas == 9)
			vencedor = "O jogo deu em empate";

		// Horizontais
		possibilidades[0] = tab[0][0] + tab[0][1] + tab[0][2];
		possibilidades[1] = tab[1][0] + tab[1][1] + tab[1][2];
		possibilidades[2] = tab[2][0] + tab[2][1] + tab[2][2];

		// Verticais
		possibilidades[3] = tab[0][0] + tab[1][0] + tab[2][0];
		possibilidades[4] = tab[0][1] + tab[1][1] + tab[2][1];
		possibilidades[5] = tab[0][2] + tab[1][2] + tab[2][2];

		// Diagonais
		possibilidades[6] = tab[0][0] + tab[1][1] + tab[2][2];
		possibilidades[7] = tab[0][2] + tab[1][1] + tab[2][0];

		for (int i = 0; i < possibilidades.length; i++) {
			if (possibilidades[i].equals("XXX") && marcadorJogador1.equals("X"))
				vencedor = "O jogo foi vencido pelo Jogador 1";
			else if (possibilidades[i].equals("OOO") && marcadorJogador1.equals("O"))
				vencedor = "O jogo foi vencido pelo Jogador 1";
			else if (possibilidades[i].equals("XXX") && marcadorJogador2.equals("X"))
				vencedor = "O jogo foi vencido pelo Jogador 2";
			else if (possibilidades[i].equals("OOO") && marcadorJogador2.equals("O"))
				vencedor = "O jogo foi vencido pelo Jogador 2";
		}

		return vencedor;
	}

	public static boolean JogarNovamente() {
		boolean jogar = true;
		int escolha = 0;
		do {
			System.out.println("\n\nDeseja jogar novamente? Digite (1) para Sim (2) para Não");
			try {
				escolha = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("\nValor informado inválido, por favor, informe novamente!");
				sc.nextLine();// Descarta a entrada errada
			}
		} while (escolha != 1 && escolha != 2);
		if (escolha == 2)
			jogar = false;
		return jogar;
	}
}