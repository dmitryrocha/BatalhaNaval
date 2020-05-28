package Recosoft;


public class Map {

    private int map[][] = new int[7][7];
    private String battleground[][] = new String[8][8];

    public Map() {

        String column[] = { "0 ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 " };
        String row[] = { "0", " A", "B", "C", "D", "E", "F", "G" };

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                map[i][j] = 10;
            }
        }

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    battleground[i][j] = " " + row[j];
                    battleground[j][i] = column[j] + " ";
                }
                if (battleground[i][j] == null) {
                    battleground[i][j] = "- ";
                }
            }

        }
        battleground[0][0] = " ";
    }

    public void setShipOnMap(int positionY, int positionX, int health) {

        map[positionY][positionX] = health;

        battleground[positionY + 1][positionX + 1] = "V ";

    }

    public void drawMap() {

        System.out.println("___________________________________________");
        for (int i = 0; i < 8; i++) {
            System.out.println("");

            for (int j = 0; j < 8; j++) {

                System.out.print(battleground[i][j]);

            }
        }
        System.out.println("");
        System.out.println("__________________________________________");

    }

    public void changeStringKordinat(int y, int x, String boatHealth) {

        battleground[y][x] = boatHealth + "";

    }

    public void changeIntKordinat(int y, int x, int health) {

        map[y][x] = health;
    }

    public int getKordinat(int y, int x) {

        return map[y][x];
    }

    public String getStringKordinat(int y, int x) {

        return battleground[y][x];
    }

}