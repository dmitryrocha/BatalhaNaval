package ProjetoFinal;

import java.util.Scanner;

public class Barcos {

    public static Scanner sc = new Scanner(System.in);

    //Método pede ao usuário informar a posição dos barcos, para retornar o vetor com tudo no lugar certo
    public static String[][] posicaoBarcos(String player, String[][] tabuleiro) {
        String[][] tabuleiroNovo = tabuleiro;
        boolean finalizou = false, porCima = false;
        int x = 0, y = 0, p = 0, tamanho = 5;
        /*  (1) porta-aviões (cinco quadrados)
            (2) navios-tanque (quatro quadrados)
            (3) contratorpedeiros (três quadrados)
            (4) submarinos (dois quadrados)
        */


        //Porta-Aviões
        do {
            //Primeiro pede se o barco está na horizontal ou na vertical, dando apenas 1 ou 2 como opções válidas
            do {
                System.out.println("Seu porta-aviões estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                p = sc.nextInt();
            } while (p < 1 || p > 2);
            //Pede a linha onde estará o barco, dando apenas de 0 a 9 como opção
            do {
                System.out.println("Favor escolher a linha onde seu porta-aviões estará:\nDigite um número de 0 a 9");
                x = sc.nextInt();
                //Se o barco estiver na vertical, verifica se ele extrapola o tabuleiro
                if (p == 1) {
                    if (x + 4 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        x = -1;
                    }
                }
            } while (x < 0 || x > 9);
            //Pede a coluna onde ele será posicionado
            do {
                System.out.println("Favor escolher a coluna onde seu porta-aviões estará:\nDigite um número de 0 a 9");
                y = sc.nextInt();
                ////Se o barco estiver na horizontal, verifica se ele extrapola o tabuleiro
                if (p == 2) {
                    if (y + 4 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        y = -1;
                    }
                }
            } while (y < 0 || y > 9);

            //Uma vez validados os dados, verifica-se a posição. Se for na última coluna o preenchimento é diferenciado
            //Loop continuará até todos os espaços do tamanho do barco estarem preenchidos
            while (tamanho != 0) {

                if (p == 1) {
                    if (y == 9) {
                        tabuleiroNovo[x][y] = "|XXXXX|";
                    }
                    tabuleiroNovo[x][y] = "|XXXXX";
                    x++;
                } else if (p == 2) {
                    if (y == 9) {
                        tabuleiroNovo[x][y] = "|XXXXX|";
                    }
                    tabuleiroNovo[x][y] = "|XXXXX";
                    y++;
                }
                //Após preencher sua célula do vetor, diminui o tamanho
                tamanho--;
            }
            //Se chegou até aquio sem problema, então finalizou
            finalizou = true;
        }while(!finalizou);

            //Imprime novamente o mapa, zera os dados e inicia o mesmo procedimento novamente
            Mapa.imprimirTabuleiro(tabuleiroNovo, player);

            x = -1;
            y = -1;
            p = -1;
            finalizou = false;

            //Navios-tanque (quatro quadrados)
            do {
                do {
                    System.out.println("Seu navio-tanque estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                    p = sc.nextInt();
                } while (p < 1 || p > 2);
                do {
                    System.out.println("Favor escolher a linha onde seu navio-tanque estará:\nDigite um número de 0 a 9");
                    x = sc.nextInt();
                    if (p == 1) {
                        if (x + 3 > 9) {
                            System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                            x = -1;
                        }
                    }
                } while (x < 0 || x > 9);
                do {
                    System.out.println("Favor escolher a coluna onde seu navio-tanque estará:\nDigite um número de 0 a 9");
                    y = sc.nextInt();
                    if (p == 2) {
                        if (y + 3 > 9) {
                            System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                            y = -1;
                        }
                    }
                } while (y < 0 || y > 9);
                //Dado os dados da posição, reinicia o tamanho para o da nova embarcação
                tamanho = 4;

                while (tamanho != 0) {
                    //Se o espaço já¡ está¡ preenchido, um aviso é dado e os dados são reiniciados
                    if (tabuleiroNovo[x][y].equals("|XXXXX|") || tabuleiroNovo[x][y].equals("|XXXXX")) {
                        System.out.println("Este espaço já está ocupado. Favor esolher os locais novamente");
                        porCima = true;
                        tamanho = 0;
                        porCima = true;
                        Mapa.imprimirTabuleiro(tabuleiroNovo,player);
                    } else {
                        if (p == 1) {
                            if (y == 9) {
                                tabuleiroNovo[x][y] = "|XXXXX|";
                            }
                            tabuleiroNovo[x][y] = "|XXXXX";
                            x++;
                        } else if (p == 2) {
                            if (y == 9) {
                                tabuleiroNovo[x][y] = "|XXXXX|";
                            }
                            tabuleiroNovo[x][y] = "|XXXXX";
                            y++;
                        }
                        tamanho--;
                        porCima = false;
                    }
                    //Verifica se o barco está por cima e decide se sai do método ou se o reinicia
                    if(tamanho == 0 && porCima == false){
                        porCima = false;
                    } else if(tamanho == 0 && porCima == true) {
                        porCima = true;
                        x=-1;
                        y=-1;
                        p=-1;

                    }

                }
            }while (porCima);

        x = -1;
        y = -1;
        p = -1;
        finalizou = false;

        Mapa.imprimirTabuleiro(tabuleiroNovo, player);

        //Contratorpedeiros (três quadrados)
        do {
            do {
                System.out.println("Seu contratorpedeiro estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                p = sc.nextInt();
            } while (p < 1 || p > 2);
            do {
                System.out.println("Favor escolher a linha onde seu contratorpedeiro estará:\nDigite um número de 0 a 9");
                x = sc.nextInt();
                if (p == 1) {
                    if (x + 2 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        x = -1;
                    }
                }
            } while (x < 0 || x > 9);
            do {
                System.out.println("Favor escolher a coluna onde seu contratorpedeiro estará:\nDigite um número de 0 a 9");
                y = sc.nextInt();
                if (p == 2) {
                    if (y + 2 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        y = -1;
                    }
                }
            } while (y < 0 || y > 9);
            tamanho = 3;

            while (tamanho != 0) {
                if (tabuleiroNovo[x][y].equals("|XXXXX|") || tabuleiroNovo[x][y].equals("|XXXXX")) {
                    System.out.println("Este espaço já está ocupado. Favor esolher os locais novamente");
                    porCima = true;
                    tamanho = 0;
                    porCima = true;
                    Mapa.imprimirTabuleiro(tabuleiroNovo,player);
                } else {
                    if (p == 1) {
                        if (y == 9) {
                            tabuleiroNovo[x][y] = "|XXXXX|";
                        }
                        tabuleiroNovo[x][y] = "|XXXXX";
                        x++;
                    } else if (p == 2) {
                        if (y == 9) {
                            tabuleiroNovo[x][y] = "|XXXXX|";
                        }
                        tabuleiroNovo[x][y] = "|XXXXX";
                        y++;
                    }
                    tamanho--;
                    porCima = false;
                }

                if(tamanho == 0 && porCima == false){
                    porCima = false;
                } else if(tamanho == 0 && porCima == true) {
                    porCima = true;
                    x=-1;
                    y=-1;
                    p=-1;

                }

            }
        }while (porCima);


                x = -1;
                y = -1;
                p = -1;
                finalizou = false;

                Mapa.imprimirTabuleiro(tabuleiroNovo, player);

        //Submarinos (dois quadrados)
        do {
            do {
                System.out.println("Seu submarino estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                p = sc.nextInt();
            } while (p < 1 || p > 2);
            do {
                System.out.println("Favor escolher a linha onde seu submarino estará:\nDigite um número de 0 a 9");
                x = sc.nextInt();
                if (p == 1) {
                    if (x + 1 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        x = -1;
                    }
                }
            } while (x < 0 || x > 9);
            do {
                System.out.println("Favor escolher a coluna onde seu submarino estará:\nDigite um número de 0 a 9");
                y = sc.nextInt();
                if (p == 2) {
                    if (y + 1 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        y = -1;
                    }
                }
            } while (y < 0 || y > 9);
            tamanho = 2;

            while (tamanho != 0) {
                if (tabuleiroNovo[x][y].equals("|XXXXX|") || tabuleiroNovo[x][y].equals("|XXXXX")) {
                    System.out.println("Este espaço já está ocupado. Favor esolher os locais novamente");
                    porCima = true;
                    tamanho = 0;
                    porCima = true;
                    Mapa.imprimirTabuleiro(tabuleiroNovo,player);
                } else {
                    if (p == 1) {
                        if (y == 9) {
                            tabuleiroNovo[x][y] = "|XXXXX|";
                        }
                        tabuleiroNovo[x][y] = "|XXXXX";
                        x++;
                    } else if (p == 2) {
                        if (y == 9) {
                            tabuleiroNovo[x][y] = "|XXXXX|";
                        }
                        tabuleiroNovo[x][y] = "|XXXXX";
                        y++;
                    }
                    tamanho--;
                    porCima = false;
                }

                if(tamanho == 0 && porCima == false){
                    porCima = false;
                } else if(tamanho == 0 && porCima == true) {
                    porCima = true;
                    x=-1;
                    y=-1;
                    p=-1;

                }

            }
        }while (porCima);

                return tabuleiroNovo;


    }
}
