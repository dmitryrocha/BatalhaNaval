package Recosoft;

import java.util.*;

import javax.swing.JOptionPane;

public class Player {

    private Ship ship[] = new Ship[5];
    private Map map;

    public Player() {

        for (int i = 0; i < 5; i++) {

            ship[i] = new Ship();

            while (i > 1 && i < 4) {

                i++;
                ship[i] = new Ship(4);

            }

        }

        map = new Map();

    }

    public void setShipPositions(int temp) {
        Scanner sc = new Scanner(System.in);

        // A65-Z90, ASCII

        int y, x;
        int choice = 0;
        char cordinateAsLetter;

        if (temp == 1) {

            System.out.println("Do you 1. want to do the placement of the boats on your own or 2. randomize them?: ");
            choice = sc.nextInt();

            while (choice <= 0 || choice > 2) {
                System.out.println("\"1\" to do the placement on your own \"2\" to randomize them.");
                choice = sc.nextInt();
            }
        }

        if (choice == 1 && temp == 1) {

            System.out.print("Here is the battleground.\n");
            map.drawMap();
            System.out.println("\n");

            for (int i = 0; i < 5; i++) {
                System.out.println("Where do you want to put boat " + (i + 1) + " with " + ship[i].getHealth()
                        + "HP, on the map?\nY cordinate (1-7): \nX cordinate (A-G): ");

                y = sc.nextInt() - 1;
                cordinateAsLetter = sc.next().charAt(0);
                x = (int) cordinateAsLetter - 65;

                while (y < 0 || y > 6 || (int) cordinateAsLetter > 90 || (int) cordinateAsLetter < 65) {

                    System.out.println(
                            "Choose 1-7 for Y cordinate and A-G for X cordinate (Important with big letters (A-G).");

                    y = sc.nextInt() - 1;
                    cordinateAsLetter = sc.next().charAt(0);
                    x = (int) cordinateAsLetter - 65;
                }

                ship[i].setPositionX(x);
                ship[i].setPositionY(y);

                if (i > 0) {
                    while (y == ship[i - 1].getPositionY() && x == ship[i - 1].getPositionX()) {

                        System.out.println(
                                "Y. " + (y + 1) + " and X. " + (x + 1) + " is taken, choose again. \nY (1-7): \nX (A-G):");

                        y = sc.nextInt() - 1;
                        cordinateAsLetter = sc.next().charAt(0);
                        x = (int) cordinateAsLetter - 65;

                        ship[i].setPositionX(x);
                        ship[i].setPositionY(y);

                    }
                }
                map.setShipOnMap(ship[i].getPositionY(),ship[i].getPositionX(), ship[i].getHealth());
            }

            System.out.print("Here is the battleground with boats.\n");
            map.drawMap();
        }
        if (choice == 2 || temp != 1) {
            for (int i = 0; i < 5; i++) {

                Random rand = new Random();
                y = rand.nextInt(6);
                x = rand.nextInt(6);

                if (i > 0) {

                    for (int j = 0; j < i; j++) {

                        while (y == ship[j].getPositionY() && x == ship[j].getPositionX()) {

                            y = rand.nextInt(6);
                            x = rand.nextInt(6);
                        }
                    }

                }

                ship[i].setPositionX(x);
                ship[i].setPositionY(y);

                map.setShipOnMap(ship[i].getPositionY(), ship[i].getPositionX(), ship[i].getHealth());

            }

        }
        if (choice == 2) {
            System.out.print("Here is the battleground with boats randomized by the computer.\n");
            map.drawMap();
        }
    }

    public void attack(Player enemy, int temp) {
        // method to attack the other player.

        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int y, x, health;
        char cordinateAsLetter;

        if (temp == 1) {
            // If temp is 1 means that its a player who is playing.

            for (int i = 0; i < 3; i++) {
                // Each player gets to shoot 3 times when its their turn.
                System.out
                        .println("Where do you want to attack? \nY cordinate (1-7): \nX cordinate (A-G)\n(Your map is beeing shown) ");

                map.drawMap();

                y = sc.nextInt()-1;
                cordinateAsLetter = sc.next().charAt(0);
                x = (int) cordinateAsLetter - 65;

                while (y < 0 || y > 6 || (int) cordinateAsLetter > 90 || (int) cordinateAsLetter < 65) {

                    System.out.println("Y cordinate 1-7 and X cordinate A-G (Important with big letters A-G).");

                    y = sc.nextInt() - 1;
                    cordinateAsLetter = sc.next().charAt(0);
                    x = (int) cordinateAsLetter - 65;
                }

                if (enemy.map.getKordinat(y , x ) < 10) {
                    // If the hit is successfully the following will happen.

                    int damage = rand.nextInt(3);

                    health = (enemy.map.getKordinat(y , x ) - damage);

                    if (damage == 0) {
                        // If the hit is 0 there will be no damaged made but the boat will be localised.

                        System.out.println(
                                "Since it was a windy day with high waves the shot didn't hit the boat but atleast the boat is localised...\n");

                        map.changeStringKordinat(y+1, x+1, String.valueOf(enemy.map.getKordinat(y , x )));

                        map.drawMap();

                    }
                    if (damage > 0) {
                        // If the damaged is over 0 the hit will be made and the boat will be shown on your map in form of its health.

                        System.out.println("You got a hit and made " + damage
                                + " in damage! Lucky there weren't high waves and winds..");

                        if (health < 0) {
                            health = 0;
                        }
                        enemy.map.changeIntKordinat(y , x , health);
                        map.changeStringKordinat(y+1, x+1, (String.valueOf(enemy.map.getKordinat(y , x)) + " "));

                        map.drawMap();

                    }

                }

                else {
                    // If no boat was hit this will happen.
                    System.out.println("No boat got hit.");
                    map.changeStringKordinat(y+1, x+1, "! ");

                    map.drawMap();
                }
            }
            delay(2000);
        }

        else {
            // If the computer shall play.
            //This doesn't work atm...
            for (int i = 0; i < 3; i++) {

                y = rand.nextInt(6);
                x = rand.nextInt(6);

                if (enemy.map.getKordinat(y, x) < 10) {

                    int damage = rand.nextInt(2);

                    health = (enemy.map.getKordinat(y, x) - damage);

                    if (damage == 0) {

                        enemy.map.changeIntKordinat(y, x, health);
                        enemy.map.changeStringKordinat(y, x, (String.valueOf(enemy.map.getKordinat(y, x)) + " "));
                        map.changeStringKordinat(y, x, String.valueOf(enemy.map.getKordinat(y, x)));

                    }
                    if (damage > 0) {

                        if (health < 0) {
                            health = 0;
                        }

                        enemy.map.changeIntKordinat(y, x, health);
                        enemy.map.changeStringKordinat(y, x, (String.valueOf(enemy.map.getKordinat(y, x)) + " "));
                        map.changeStringKordinat(y, x, (String.valueOf(enemy.map.getKordinat(y, x)) + " "));

                    }

                }

            }
            System.out.println("Here is your map after the attacks.");
            enemy.map.drawMap();

        }

    }

    public int getShipsOnMapHealth(int y, int x) {

        return map.getKordinat(y, x);
    }

    public void drawMap() {
        map.drawMap();
    }

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exp) {
        }
    }
}


