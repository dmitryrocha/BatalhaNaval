package ProjetoFinal;
import java.util.Random;

public class PC {

    public static String[][] posicaoBarcosPC(String player, String[][] tabuleiro) {
        String[][] tabuleiroNovo = tabuleiro;
        boolean porCima = false;
        int x = 0, y = 0, p = 0, tamanho = 5;
        Random rd = new Random();

        //Porta-aviões
        x = rd.nextInt(5);
        y = rd.nextInt(5);
        p = rd.nextInt(2);
        while (tamanho != 0) {
            if (p == 1) {
                if (y == 9) {
                    tabuleiroNovo[x][y] = "|XXXXX|";
                } else {
                    tabuleiroNovo[x][y] = "|XXXXX";                  
                }
                x++;
            }
            if (p == 2) {
                if (y == 9) {
                    tabuleiroNovo[x][y] = "|XXXXX|";
                } else {
                    tabuleiroNovo[x][y] = "|XXXXX";
                }
                y++;
            }
            tamanho--;
        }

        Mapa.imprimirTabuleiro(tabuleiroNovo, player);

        tabuleiro = tabuleiroNovo;

        //Navio-tanque
        x = rd.nextInt(6);
        y = rd.nextInt(6);
        p = rd.nextInt(2);
        tamanho = 4;
        while (porCima == true) {


            while (tamanho != 0) {
                if (tabuleiroNovo[x][y].equals("|XXXXX") || tabuleiroNovo[x][y].equals("|XXXXX|")) {

                    x = rd.nextInt(6);
                    y = rd.nextInt(6);
                    p = rd.nextInt(2);
                    tamanho = -3;
                } else {
                    if (p == 1) {
                        if (y == 9) {
                            tabuleiroNovo[x][y] = "|XXXXX|";
                        }
                        tabuleiroNovo[x][y] = "|XXXXX";
                        System.out.println("NT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        x++;
                    }
                    if (p == 2) {
                        if (y == 9) {
                            tabuleiroNovo[x][y] = "|XXXXX|";
                        }
                        tabuleiroNovo[x][y] = "|XXXXX";
                        y++;
                    }
                    tamanho--;
                }


                if (tamanho == 0) {
                    porCima = false;
                } else if (tamanho <= -3) {
                    porCima = true;
                    tamanho = 4;
                    tabuleiroNovo = tabuleiro;
                }
            }
        }

        Mapa.imprimirTabuleiro(tabuleiroNovo, player);


        return tabuleiroNovo;

    }

}
