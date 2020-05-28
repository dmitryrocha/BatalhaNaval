package br.ucsal;


import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Seja bem vindo");
        iniciarJogo();


    }


    public static void iniciarJogo() {
        int ganhou = 0;
        String jogador1 = "Nome do jogador", jogador2 = "Nome do jogador";
        String[][] board1 = fazerTabua(jogador1);
        String[][] board2 = fazerTabua(jogador2);


        board1 = posicaoBarcos(jogador1, board1);
        imprimir(board1, jogador1);
        System.out.println("\n"+jogador1 + " pronto");
        board2 = posicaoBarcos(jogador2, board2);
        imprimir(board2, jogador2);
        System.out.println("\n"+jogador2 + " pronto");
        System.out.println("\nPosicionamentos finais\n");
        imprimir(board1, jogador1);
        System.out.println(" ");
        imprimir(board2, jogador2);
        System.out.println("  ");

        ganhou = jogar(board1, board2, jogador1, jogador2);
        if (ganhou == 1) {
            System.out.println("Vencedor: " + jogador1);
        } else if (ganhou == 2) {
            System.out.println("Vencedor: " + jogador2);
        }


    }

    public static int jogar(String[][] tabuleiroP1, String[][] tabuleiroP2, String jogador1, String jogador2) {
        int ganhador = 0, qtdBarcos1 = 14, qtdBarcos2 = 14, placar1 = 0, placar2 = 0, x = -1, y = -1;
        boolean acertou = false;
        while (qtdBarcos1 != 0 || qtdBarcos2 != 0) {
            do {
                System.out.println(jogador1 + " favor informar a linha do seu tiro (valor deve estar entre 0 e 9)");
                x = sc.nextInt();
            } while (x < 0 || x > 9);
            do {
                System.out.println(jogador1 + " favor informar a coluna do seu tiro (valor deve estar entre 0 e 9)");
                y = sc.nextInt();
            } while (y < 0 || y > 9);
            acertou = tiro(tabuleiroP2, x, y);
            if (acertou == true) {
                tabuleiroP2 = acertou(tabuleiroP2, x, y);
                placar1++;
                qtdBarcos2--;
            } else {
                tabuleiroP2 = erro(tabuleiroP2, x, y);
            }
            imprimir(tabuleiroP2, jogador2);
            System.out.println("  ");
            x = -1;
            y = -1;
            acertou = false;
            if (placar1 == 14) {
                return 1;

            }

            do {
                System.out.println(jogador2 + " favor informar a linha do seu tiro (valor deve estar entre 0 e 9)");
                x = sc.nextInt();
            } while (x < 0 || x > 9);
            do {
                System.out.println(jogador2 + " favor informar a coluna do seu tiro (valor deve estar entre 0 e 9)");
                y = sc.nextInt();
            } while (y < 0 || y > 9);
            acertou = tiro(tabuleiroP1, x, y);
            if (acertou == true) {
                tabuleiroP1 = acertou(tabuleiroP1, x, y);
                placar2++;
                qtdBarcos1--;
            } else {
                tabuleiroP1 = erro(tabuleiroP1, x, y);
            }

            x = -1;
            y = -1;
            acertou = false;
            imprimir(tabuleiroP1, jogador1);
            System.out.println("  ");
            if (placar2 == 14) {
                return 2;
            }
        }

        if (qtdBarcos1 == 0) {
            ganhador = 2;
        } else if (qtdBarcos2 == 0) {
            ganhador = 1;
        }

        return ganhador;

    }

    public static String[][] fazerTabua(String player) {
        int count = 0;
        String board[][] = new String[10][10];

        String linhas = "   -------------------------------";

        System.out.println("       Jogo do " + player + "\n");
        System.out.println("    0  1  2  3  4  5  6  7  8  9");
        System.out.print(linhas);
        System.out.print("\n" + count+"  ");
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (y == 9) {
                    System.out.print("|  |");
                    board[x][y] = "|  |";
                } else {
                    System.out.print("|  ");
                    board[x][y] = "|  ";
                }
            }
            count++;
            if (count != 10) {
                {
                    System.out.println("");
                    System.out.println(linhas);
                    System.out.print(count + "  ");
                }

            }
        }
        System.out.println("\n"+linhas);

        return board;
    }

    public static void imprimir (String[][] tabuleiroEmJogo, String player) {
        int count = 0;
        String tabuleiro[][] = tabuleiroEmJogo;

        String linhas = "   -------------------------------";

        System.out.println("       Jogo do " + player + "\n");
        System.out.println("    0  1  2  3  4  5  6  7  8  9");
        System.out.print(linhas);
        System.out.print("\n" + count+"  ");
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

    public static String[][] posicaoBarcos(String player, String[][] tabuleiro) {
        String[][] newBoard = tabuleiro;
        boolean fim = false, cobtiu = false;
        int linha = 0, coluna = 0, posicionamento = 0, tam = 5;


        //Porta-Aviões
        do {
            do {
                System.out.println("Porta-aviões estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                posicionamento = sc.nextInt();
            } while (posicionamento < 1 || posicionamento > 2);
            do {
                System.out.println("Favor escolher a linha onde seu porta-aviões estará:\nDigite um número de 0 a 9");
                linha = sc.nextInt();
                if (posicionamento == 1) {
                    if (linha + 4 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        linha = -1;
                    }
                }
            } while (linha < 0 || linha > 9);
            do {
                System.out.println("Favor escolher a coluna onde seu porta-aviões estará:\nDigite um número de 0 a 9");
                coluna = sc.nextInt();
                if (posicionamento == 2) {
                    if (coluna + 4 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        coluna = -1;
                    }
                }
            } while (coluna < 0 || coluna > 9);

            while (tam != 0) {

                if (posicionamento == 1) {
                    if (coluna == 9) {
                        newBoard[linha][coluna] = "|XXXXX|";
                    }
                    newBoard[linha][coluna] = "|XXXXX";
                    linha++;
                } else if (posicionamento == 2) {
                    if (coluna == 9) {
                        newBoard[linha][coluna] = "|XXXXX|";
                    }
                    newBoard[linha][coluna] = "|XXXXX";
                    coluna++;
                }
                tam--;
            }
            fim = true;
        }while(!fim);

        imprimir(newBoard, player);

        linha = -1;
        coluna = -1;
        posicionamento = -1;
        fim = false;


        do {
            do {
                System.out.println("Navio-tanque estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                posicionamento = sc.nextInt();
            } while (posicionamento < 1 || posicionamento > 2);
            do {
                System.out.println("Favor escolher a linha onde seu navio-tanque estará:\nDigite um número de 0 a 9");
                linha = sc.nextInt();
                if (posicionamento == 1) {
                    if (linha + 3 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        linha = -1;
                    }
                }
            } while (linha < 0 || linha > 9);
            do {
                System.out.println("Favor escolher a coluna onde seu navio-tanque estará:\nDigite um número de 0 a 9");
                coluna = sc.nextInt();
                if (posicionamento == 2) {
                    if (coluna + 3 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        coluna = -1;
                    }
                }
            } while (coluna < 0 || coluna > 9);
            tam = 4;

            while (tam != 0) {
                if (newBoard[linha][coluna].equals("|XXXXX|") || newBoard[linha][coluna].equals("|XXXXX")) {
                    System.out.println("Este espaço já está ocupado. Favor esolher os locais novamente");
                    cobtiu = true;

                    tam = 0;
                    cobtiu = true;
                    imprimir(newBoard,player);
                } else {
                    if (posicionamento == 1) {
                        if (coluna == 9) {
                            newBoard[linha][coluna] = "|XXXXX|";
                        }
                        newBoard[linha][coluna] = "|XXXXX";
                        linha++;
                    } else if (posicionamento == 2) {
                        if (coluna == 9) {
                            newBoard[linha][coluna] = "|XXXXX|";
                        }
                        newBoard[linha][coluna] = "|XXXXX";
                        coluna++;
                    }
                    tam--;
                    cobtiu = false;
                }

                if(tam == 0 && cobtiu == false){
                    cobtiu = false;
                } else if(tam == 0 && cobtiu == true) {
                    cobtiu = true;
                    linha=-1;
                    coluna=-1;
                    posicionamento=-1;

                }

            }
        }while (cobtiu);

        linha = -1;
        coluna = -1;
        posicionamento = -1;
        fim = false;

        imprimir(newBoard, player);


        do {
            do {
                System.out.println("Contratorpedeiro estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                posicionamento = sc.nextInt();
            } while (posicionamento < 1 || posicionamento > 2);
            do {
                System.out.println("Favor escolher a linha onde seu contratorpedeiro estará:\nDigite um número de 0 a 9");
                linha = sc.nextInt();
                if (posicionamento == 1) {
                    if (linha + 2 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        linha = -1;
                    }
                }
            } while (linha < 0 || linha > 9);
            do {
                System.out.println("Favor escolher a coluna onde seu contratorpedeiro estará:\nDigite um número de 0 a 9");
                coluna = sc.nextInt();
                if (posicionamento == 2) {
                    if (coluna + 2 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        coluna = -1;
                    }
                }
            } while (coluna < 0 || coluna > 9);
            tam = 3;

            while (tam != 0) {
                if (newBoard[linha][coluna].equals("|XXXXX|") || newBoard[linha][coluna].equals("|XXXXX")) {
                    System.out.println("Este espaço já está ocupado. Favor esolher os locais novamente");
                    cobtiu = true;
                    tam = 0;
                    cobtiu = true;
                    imprimir(newBoard,player);
                } else {
                    if (posicionamento == 1) {
                        if (coluna == 9) {
                            newBoard[linha][coluna] = "|XXXXX|";
                        }
                        newBoard[linha][coluna] = "|XXXXX";
                        linha++;
                    } else if (posicionamento == 2) {
                        if (coluna == 9) {
                            newBoard[linha][coluna] = "|XXXXX|";
                        }
                        newBoard[linha][coluna] = "|XXXXX";
                        coluna++;
                    }
                    tam--;
                    cobtiu = false;
                }

                if(tam == 0 && cobtiu == false){
                    cobtiu = false;
                } else if(tam == 0 && cobtiu == true) {
                    cobtiu = true;
                    linha=-1;
                    coluna=-1;
                    posicionamento=-1;

                }

            }
        }while (cobtiu);


        linha = -1;
        coluna = -1;
        posicionamento = -1;
        fim = false;

        imprimir(newBoard, player);

        do {
            do {
                System.out.println("Contratorpedeiro estará na vertical ou horizontal?\nDigite 1 para vertical ou 2 para horizontal");
                posicionamento = sc.nextInt();
            } while (posicionamento < 1 || posicionamento > 2);
            do {
                System.out.println("Favor escolher a linha onde seu contratorpedeiro estará:\nDigite um número de 0 a 9");
                linha = sc.nextInt();
                if (posicionamento == 1) {
                    if (linha + 1 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        linha = -1;
                    }
                }
            } while (linha < 0 || linha > 9);
            do {
                System.out.println("Favor escolher a coluna onde seu contratorpedeiro estará:\nDigite um número de 0 a 9");
                coluna = sc.nextInt();
                if (posicionamento == 2) {
                    if (coluna + 1 > 9) {
                        System.out.println("O valor escolhido extrapola o tabuleiro, favor tentar novamente");
                        coluna = -1;
                    }
                }
            } while (coluna < 0 || coluna > 9);
            tam = 2;

            while (tam != 0) {
                if (newBoard[linha][coluna].equals("|XXXXX|") || newBoard[linha][coluna].equals("|XXXXX")) {
                    System.out.println("Este espaço já está ocupado. Favor esolher os locais novamente");
                    cobtiu = true;

                    tam = 0;
                    cobtiu = true;
                    imprimir(newBoard,player);
                } else {
                    if (posicionamento == 1) {
                        if (coluna == 9) {
                            newBoard[linha][coluna] = "|XX|";
                        }
                        newBoard[linha][coluna] = "|XX";
                        linha++;
                    } else if (posicionamento == 2) {
                        if (coluna == 9) {
                            newBoard[linha][coluna] = "|XX|";
                        }
                        newBoard[linha][coluna] = "|XX";
                        coluna++;
                    }
                    tam--;
                    cobtiu = false;
                }

                if(tam == 0 && cobtiu == false){
                    cobtiu = false;
                } else if(tam == 0 && cobtiu == true) {
                    cobtiu = true;
                    linha=-1;
                    coluna=-1;
                    posicionamento=-1;

                }

            }
        }while (cobtiu);

        return newBoard;


    }

    public static boolean tiro (String[][] tabuleiro, int x, int y) {

        if(tabuleiro[x][y].equals("|XX")||tabuleiro[x][y].equals("|XX|")){
            return true;
        } else {
            return false;
        }

    }

    public static String[][] acertou(String[][]tabuleiro, int x, int y){
        if(y==9) {
            tabuleiro[x][y] = "|**|";
        } else {
            tabuleiro[x][y] = "|**";
        }

        return tabuleiro;
    }

    public static String[][] erro(String[][]tabuleiro, int x, int y) {
        if(y==9) {
            tabuleiro[x][y] = "|aa|";
        } else {
            tabuleiro[x][y] = "|aa";
        }

        return tabuleiro;
    }



}