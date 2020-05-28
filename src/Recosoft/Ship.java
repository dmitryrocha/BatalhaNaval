package Recosoft;

public class Ship {

    private int health;
    private int positionX;
    private int positionY;

    public Ship() {

        health = 3;
        positionX = 0;
        positionY = 0;

    }

    public Ship(int h) {

        health = h;
        positionX = 0;
        positionY = 0;

    }

    public void setPositionX(int position) {

        positionX = position;

    }

    public void setPositionY(int position) {

        positionY = position;

    }

    public int getHealth() {
        return health;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

}




