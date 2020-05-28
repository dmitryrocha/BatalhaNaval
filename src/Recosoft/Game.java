package Recosoft;

import java.util.*;

import javax.swing.JOptionPane;

public class Game {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Boolean current = false;
        int choice;
        String slaskvariabel;
        JOptionPane.showMessageDialog(null, "             START GAME");

        Player player[] = new Player[2];

        slaskvariabel = JOptionPane.showInputDialog("Do you 1. want to play against an other player or 2. Player against the computer?");

        choice = Integer.parseInt(slaskvariabel);

        while(choice<=0 || choice>2){
            slaskvariabel = JOptionPane.showInputDialog("\"1\" to play against a player \"2\" to play against the computer.");

            choice = Integer.parseInt(slaskvariabel);

        }

        if (choice == 1) {

            for (int i = 0; i < 2; i++) {

                System.out.println("Player " + (i + 1));
                System.out.println("¯ ¯ ¯ ¯ ¯ ");
                player[i] = new Player();

                player[i].setShipPositions(1);
                delay(5000);

                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
            }

            while (current == false) {
                System.out.println("Player 1");
                System.out.println("¯ ¯ ¯ ¯ ¯");
                player[0].attack(player[1], 1);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("\n");
                delay(85);
                System.out.println("Player 2");
                System.out.println("¯ ¯ ¯ ¯ ¯");
                player[1].attack(player[0], 1);

                for (int k = 0; k < 2; k++) {

                    int check = 0;

                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {

                            if (player[k].getShipsOnMapHealth(i, j) == 0) {
                                check++;

                                if (check == 5 && k == 0) {
                                    current = true;
                                    System.out.println("Player " + (k + 1) + " has won!\nHere is player " + (k + 1)
                                            + " map: ");
                                    player[0].drawMap();

                                }

                                if (check == 5 && k == 1) {
                                    current = true;
                                    System.out.println("Player " + (k + 1) + " has won!\nHere is player " + (k + 1)
                                            + " map: ");
                                    player[1].drawMap();
                                }

                            }

                        }
                    }
                }

            }

        }
        if (choice == 2) {

            player[0] = new Player();

            player[0].setShipPositions(1);

            player[1] = new Player();

            player[1].setShipPositions(0);

            while (current == false) {

                player[0].attack(player[1], 1);

                player[1].attack(player[0], 2);
                for (int k = 0; k < 2; k++) {

                    int check = 0;

                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {

                            if (player[k].getShipsOnMapHealth(i, j) == 0) {
                                check++;

                                if (check == 5 && k == 0) {
                                    current = true;
                                    System.out.println("Player " + (k + 1) + " has won!\nHere is player " + (k + 1)
                                            + " map: ");
                                    player[0].drawMap();

                                }

                                if (check == 5 && k == 1) {
                                    current = true;
                                    System.out.println("The computer has won..\nHere is the computers map");
                                    player[1].drawMap();
                                }

                            }

                        }
                    }
                }
            }

        }

    }

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exp) {
        }
    }

}

