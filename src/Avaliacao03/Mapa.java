package Avaliacao03;

public class Mapa {
    //Método mostra como é um tabuleiro e preenche um vetor com os dados para formar um novo tabuleiro, retornando-o
    public static String[][] criarTabuleiro(String player) {
        int count = 0;
        String tabuleiro[][] = new String[10][10];

        String linhas = "    -------------------------------------------------------------";

        System.out.println("                      Tabuleiro do " + player + "\n");
        System.out.println("       0     1     2     3     4     5     6     7     8     9");
        System.out.print(linhas);
        System.out.print("\n" + count + "   ");
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (y == 9) {
                    System.out.print("|     |");
                    tabuleiro[x][y] = "|     |";
                } else {
                    System.out.print("|     ");
                    tabuleiro[x][y] = "|     ";
                }
            }
            count++;
            if (count != 10) {
                {
                    System.out.println("");
                    System.out.println(linhas);
                    System.out.print(count + "   ");
                }

            }
        }
        System.out.println("\n"+linhas);

        return tabuleiro;
    }

    //Método imprime o tabuleiro sempre que necessário, sem alterá-lo
    public static void imprimirTabuleiro (String[][] tabuleiroEmJogo, String player) {
        int count = 0;
        String tabuleiro[][] = tabuleiroEmJogo;

        String linhas = "    -------------------------------------------------------------";

        System.out.println("                      Tabuleiro do " + player + "\n");
        System.out.println("       0     1     2     3     4     5     6     7     8     9");
        System.out.print(linhas);
        System.out.print("\n" + count + "   ");
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.print(tabuleiro[x][y]);
            }
            count++;
            if (count != 10) {
                {
                    System.out.println("");
                    System.out.println(linhas);
                    System.out.print(count + "   ");
                }

            }
        }
        System.out.println("\n"+linhas);
    }

}
