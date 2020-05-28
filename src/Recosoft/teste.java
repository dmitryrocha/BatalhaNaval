package Recosoft;

import java.util.Scanner;

public class teste {

    static Scanner sc = new Scanner(System.in);
    static String palavra = "ESCOLA";
    static String[] letras = new String[palavra.length()];

    public static void main(String[] args) {
        inicio();

    }

    public static void inicio() {
        start_vetor();
        System.out.println("JOGO DA FORCA\n");
        System.out.print("Palavra oculta: ");
        montaTraco(palavra);
        System.out.println("\n");
        System.out.print("Você informou a letra: " + "" + retornaLetra());

    }

    public static void start_vetor() {
        for (int i = 0; i < letras.length; i++) {
            letras[i] = " _";
        }
    }

    public static String retornaLetra() {
        String letra;
        do {
            System.out.print("Informe uma letra qualquer(Caixa alta ignorada): ");
            letra = sc.next().toUpperCase();
            if (letra.length()>1) {
                System.out.println("Digite apenas uma letra");
            }
        } while (letra.length()>1);
        tentativas(letra);

        return letra;

    }

    public static void montaTraco(String palavra) {
        for (int i = 0; i < palavra.length(); i++) {
            System.out.print("_ ");
        }
    }

    public static boolean tentativas(String letra) {
        boolean teste = false;
        int cont = 0;
        for (int i = 0; i < palavra.length(); i++) {
            String tentativa = palavra.substring(i, i+1);
            if (letra.equals(tentativa)) {
                teste = true;
                cont = i;
                break;
            }
        }
        if(teste == true) {
            letras[cont] = letra;
            testeVetor();
        } else {
            System.out.println("Letra incorreta");
            testeVetor();
        }


        return teste;
    }

    public static void testeVetor() {
        boolean teste = false;
        for (int i = 0; i < letras.length; i++) {
            if (letras[i].equals(palavra.substring(i, i+1))) {
                teste = true;
            } else {
                teste = false;
            }
        }
        if (teste == true) {
            System.out.println("Parabéns!! Você venceu o jogo da forca!");
            System.exit(0);
        } else {
            for (int i = 0; i < letras.length; i++) {
                System.out.print(letras[i]);

            }
            System.out.println("");
            retornaLetra();
        }
    }

}
