package FYP.project;

import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 *
 * @author Chris
 */
public class Entity {

    private int x;
    private int y;
    private int o;
    private int sensorSize;
    private int energy;
    private boolean inventory;
    private int moves;
    private int eLossOnMove;
    private int eLossOnTurn;
    private int eGainOnEat;

    private Set<ArrayList<Integer>> sensor;

    private final World mat;

    public Entity(World m) {
        mat = m;
        sensorSize = 4;
        moves = 0;
        energy = 100;
        inventory = false;
        eLossOnMove = 5;
        eLossOnTurn = 1;
        eGainOnEat = 10;
    }

    public void setLocation(int a, int b) {
        if (this.inBounds(a, b)) {
            y = b;
            x = a;
        } else {
            System.out.println("LOCATION OUT OF BOUNDS");
        }
    }

    public void setOrientation(int c) {
        if (c >= 0 && c <= 3) {
            o = c;
        } else {
            System.out.println("ORIENTATION OUT OF BOUNDS");
        }
    }

    public void setPosition(int a, int b, int c) {
        this.setLocation(a, b);
        this.setOrientation(c);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getO() {
        return o;
    }

    public void printLocation() {
        System.out.println("X: " + x + " Y: " + y + " O: " + o);
    }

    public void move() {
        switch (o) {
            case 0:
                this.moveUp();
                moves++;
                break;
            case 1:
                this.moveRight();
                moves++;
                break;
            case 2:
                this.moveDown();
                moves++;
                break;
            case 3:
                this.moveLeft();
                moves++;
                break;
        }
    }

    public void moveUp() {
        if (y > 0) {
            y -= 1;
            this.setLocation(x, y);
            energy -= eLossOnMove;
        }
    }

    public void moveRight() {
        if (x < (mat.getSize() - 1)) {
            x += 1;
            this.setLocation(x, y);
            energy -= eLossOnMove;
        }
    }

    public void moveDown() {
        if (y < (mat.getSize() - 1)) {
            y += 1;
            this.setLocation(x, y);
            energy -= eLossOnMove;
        }
    }

    public void moveLeft() {
        if (x > 0) {
            x -= 1;
            this.setLocation(x, y);
            energy -= eLossOnMove;
        }
    }

    public void turnLeft() {
        if (o == 0) {
            o = 3;
        } else {
            o = (o - 1) % 4;
            energy -= eLossOnTurn;
        }
    }

    public void turnRight() {
        o = (o + 1) % 4;
        energy -= eLossOnTurn;
    }

    public void setSensorSize(int s) {
        sensorSize = s;
    }

    public int getSensorSize() {
        return sensorSize;
    }

    public void coneUp() {
        int sY = y - sensorSize;
        int sX = x - sensorSize;
        int sS = sensorSize;
        sensor = new LinkedHashSet<>();
        ArrayList<Integer> temp;

        while (sY <= y) {
            while (sX <= (x + sS)) {
                if (this.inBounds(sX, sY)) {
                    temp = new ArrayList<>();
                    temp.add(sX);
                    temp.add(sY);
                    sensor.add(temp);
                }
                sX++;
            }
            sY++;
            sS--;
            sX = x - sS;
        }
    }

    public void coneRight() {
        int sY = y - sensorSize;
        int sX = x + sensorSize;
        int sS = sensorSize;
        sensor = new LinkedHashSet<>();
        ArrayList<Integer> temp;

        while (sX >= x) {
            while (sY <= (y + sS)) {
                if (this.inBounds(sY, sX)) {
                    temp = new ArrayList<>();
                    temp.add(sX);
                    temp.add(sY);
                    sensor.add(temp);
                }
                sY++;
            }
            sX--;
            sS--;
            sY = y - sS;
        }
    }

    public void coneDown() {
        int sY = y + sensorSize;
        int sX = x - sensorSize;
        int sS = sensorSize;
        sensor = new LinkedHashSet<>();
        ArrayList<Integer> temp;

        while (sY >= y) {
            while (sX <= (x + sS)) {
                if (this.inBounds(sX, sY)) {
                    temp = new ArrayList<>();
                    temp.add(sX);
                    temp.add(sY);
                    sensor.add(temp);
                }
                sX++;
            }
            sY--;
            sS--;
            sX = x - sS;
        }
    }

    public void coneLeft() {
        int sY = y - sensorSize;
        int sX = x - sensorSize;
        int sS = sensorSize;
        sensor = new LinkedHashSet<>();
        ArrayList<Integer> temp;

        while (sX <= x) {
            while (sY <= (y + sS)) {
                if (this.inBounds(sY, sX)) {
                    temp = new ArrayList<>();
                    temp.add(sX);
                    temp.add(sY);
                    sensor.add(temp);
                }
                sY++;
            }
            sX++;
            sS--;
            sY = y - sS;
        }
    }

    public boolean inBounds(int x, int y) {
        return ((y >= 0) && (x >= 0) && (x <= (mat.getSize() - 1))
                && (y <= (mat.getSize() - 1)));
    }

    public void coneSensor() {
        switch (o) {
            case 0:
                this.coneUp();
                break;
            case 1:
                this.coneRight();
                break;
            case 2:
                this.coneDown();
                break;
            case 3:
                this.coneLeft();
                break;
        }
    }

    public Set<ArrayList<Integer>> getSensor() {
        return sensor;
    }

    public int getMoves() {
        return moves;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int e) {
        energy = e;
    }

    public int getELossOnMove() {
        return eLossOnMove;
    }

    public void setELossOnMove(int e) {
        eLossOnMove = e;
    }

    public int getELossOnTurn() {
        return eLossOnTurn;
    }

    public void setELossOnTurn(int e) {
        eLossOnTurn = e;
    }

    public int getEGainOnEat() {
        return eGainOnEat;
    }

    public void setEGainOnEat(int e) {
        eGainOnEat = e;
    }

    public void pickUp(Tile t) {
        if (mat.getMatrix()[y][x] == 1 && inventory == false) {
            t.emptyTile(this.getX(), this.getY());
            inventory = true;
        }
    }

    public boolean getInventory() {
        return inventory;
    }

    public void farm(Tile t) {
        if (mat.getMatrix()[y][x] == 2) {
            t.plantTile(x, y);
        }
    }

    public void eat(Tile t) {
        if (mat.getMatrix()[y][x] == 1) {
            t.emptyTile(this.getX(), this.getY());
            energy += eGainOnEat;
        }
    }

}
