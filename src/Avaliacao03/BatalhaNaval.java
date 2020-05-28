package Avaliacao03;

import java.util.Scanner;

public class BatalhaNaval {

    public static Scanner sc = new Scanner(System.in);

    //Chama o m�todo bemvindo para saber o n�mero de jogadores e depois pede para iniciar o jogo com o n�mero de jogadores
    public static void main(String[] args) {
        int jogadores = bemVindo();
        String[][] tabuleiro = new String[10][10];

        iniciarJogo(jogadores);

    }

    //indica a quantidade de jogadores dando apenas 1 ou 2 como alternativa
    public static int bemVindo() {
        int qtd = -1;
        System.out.println("Bem vindo(a) � Batalha naval");
        barco();
        do {
            System.out.println("Favor informar o n�mero de jogadores\n(1) - Para jogar contra o computador\n(2) - Para jogar contra outro jogador");
            qtd = sc.nextInt();
        } while(qtd<1 || qtd>2);
        return qtd;
    }

    //o jogo acontece basicamente aqui
    public static void iniciarJogo(int qtd) {
        int ganhador = 0;
        //Jogador 2 est� como Null porque pode ser o computador ou um outro humano
        String P1 = "Jogador 1", P2 = null;
        String[][] tabuleiroP1 = Mapa.criarTabuleiro(P1);

        if(qtd == 1) {
            P2 = "Computador";
        } else {
            P2 = "Jogador 2";
        }
        //Decidido quem � o jogador 2, faz-se seu tabuleiro
        String[][] tabuleiroP2 = Mapa.criarTabuleiro(P2);

        //Chama o m�todo que posiciona os barcos
        tabuleiroP1 = Barcos.posicaoBarcos(P1,tabuleiroP1);
        Mapa.imprimirTabuleiro(tabuleiroP1,P1);
        System.out.println("\nPosicionamento do "+P1+" finalizado");
        //Se for 1 jogador, o PC faz seu pr�prio tabuleiro, caso contr�rio o segundo jogador escolher� suas posi��es
        if(qtd == 1){
            tabuleiroP2 = PC.posicaoBarcosPC(P2, tabuleiroP2);
        } else {
            tabuleiroP2 = Barcos.posicaoBarcos(P2, tabuleiroP2);
        }
        System.out.println("Posicionamento do "+P2+" finalizado");
        System.out.println("\nPosicionamentos finais\n");
        //Imprime os 2 tabuleiro para iniciar o jogo
        Mapa.imprimirTabuleiro(tabuleiroP1,P1);
        System.out.println(" ");
        Mapa.imprimirTabuleiro(tabuleiroP2,P2);
        System.out.println("  ");

        //Chama o m�todo de jogo que traz como resposta quem foi o vencedor
        ganhador = jogar(tabuleiroP1,tabuleiroP2,P1,P2);
        if(ganhador == 1){
            System.out.println("O vencedor foi o "+P1);
        } else if(ganhador == 2) {
            System.out.println("o Vencedor foi o "+P2);
        }
    }

    //As jogadas ocorrem aqui
    public static int jogar (String[][]tabuleiroP1, String[][]tabuleiroP2, String jogador1, String jogador2) {
        int ganhador = 0, placarP1 = 14, placarP2 = 14,acertosP1 = 0, acertosP2 = 0, x = -1, y = -1;
        boolean acertou = false;
        //Enquanto o placar for diferente de 0, o jogo continua
        while(placarP1 != 0 || placarP2 != 0){
            //Pede pro usu�rio digitar a linha (dando apenas as op��es de 0 a 9)
            do{
                System.out.println(jogador1+" favor informar a linha do seu tiro (valor deve estar entre 0 e 9)");
                x = sc.nextInt();
            }while (x<0 || x>9);
            //Pede pro usu�rio digitar a coluna (dando apenas as op��es de 0 a 9)
            do{
                System.out.println(jogador1+" favor informar a coluna do seu tiro (valor deve estar entre 0 e 9)");
                y = sc.nextInt();
            }while (y<0 || y>9);
            //Envia a linha, a coluna e o tabuleiro do advers�rio e recebe de volta se acertou ou n�o
            acertou = Jogada.tiro(tabuleiroP2,x,y);
            //Se acertou, envia os dados para o m�todo acertou, que modifica o vetor e depois modifica o placar
            if(acertou == true) {
                tabuleiroP2 = Jogada.acertou(tabuleiroP2,x,y);
                acertosP1++;
                System.out.println("Voc� acertou e seu placar � "+acertosP1);
                placarP2--;
                //caso contr�rio, envia os dados para o m�todo errow (Faustop) e modifica o vetor
            } else {
                tabuleiroP2 = Jogada.errow(tabuleiroP2, x, y);

                System.out.println("Voc� errou e seu advers�rio ainda pode ser acertado "+placarP2+" vezes.");
            }
            Mapa.imprimirTabuleiro(tabuleiroP2, jogador2);
            System.out.println("  ");
            //Zera os dados para iniciar nova jogada
            x=-1;
            y=-1;
            acertou = false;
            //Se o n�mero de acertos atinge o valor m�ximo (todos os barcos destru�dos), o jogo acaba e o m�todo retorna o vencedor
            if(acertosP1 == 14) {
                return 1;

            }

            do{
                System.out.println(jogador2+" favor informar a linha do seu tiro (valor deve estar entre 0 e 9)");
                x = sc.nextInt();
            }while (x<0 || x>9);
            do{
                System.out.println(jogador2+" favor informar a coluna do seu tiro (valor deve estar entre 0 e 9)");
                y = sc.nextInt();
            }while (y<0 || y>9);
            acertou = Jogada.tiro(tabuleiroP1,x,y);
            if(acertou == true) {
                tabuleiroP1 = Jogada.acertou(tabuleiroP1,x,y);
                acertosP2++;
                System.out.println("Voc� acertou e seu placar � "+acertosP1);
                placarP1--;
            } else {
                tabuleiroP1 = Jogada.errow(tabuleiroP1, x, y);
                System.out.println("Voc� errou e seu advers�rio ainda pode ser acertado "+placarP1+" vezes.");
            }

            x=-1;
            y=-1;
            acertou = false;
            Mapa.imprimirTabuleiro(tabuleiroP1,jogador1);
            System.out.println("  ");
            if(acertosP2 == 14) {
                return 2;
            }
        }
        //Se o placar zerar para algum dos jogadores (todos os barcos destru�dos), o m�todo retorna o ganhador
        if(placarP1 == 0){
            ganhador = 2;
        } else if (placarP2 == 0) {
            ganhador = 1;
        }

        return ganhador;

    }





    public static void barco() {
        System.out.println("                                       __  o");
        System.out.println("                                      /  |/");
        System.out.println("                                    _/___|___________");
        System.out.println("                                   /  _______      __\\");
        System.out.println("                                   /  _______      __\\");
        System.out.println("   _______                        /  /_o_||__|    |");
        System.out.println("    \\_\\_\\_\\______________________/___             |");
        System.out.println("             \\                       \\____________|______________");
        System.out.println("              \\     ||                                           |");
        System.out.println("               \\  +_||_+       () () ()                      ____|");
        System.out.println("                \\                                             |");
        System.out.println("                 \\     _  ,,          _                      /");
        System.out.println(" ^^^^^^^^^^^^^^^^ \\_.=\'' )\''  \''-._____,' \'';__________________ /_^^^^^^^^");
        System.out.println("   ^^^^  ^^^^                                              \\__|==% ^^");
        System.out.println("  ^^         ^^^^^^^^       ^^^^ ^^^ ^^^^^      ^^^^^^^^^^ ^      ^^^^");
        System.out.println("^^^   ^^^^          ^^^^^^^^^^^^          ^^^^     ^^       ^^^^^");


    }
}
