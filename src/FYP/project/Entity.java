package FYP.project;

/**
 *
 * @author Chris
 */
public class Entity {

    private int x;
    private int y;
    private int o;
    private int energy;
    private boolean inventory;
    private int moves;
    private int eLossOnMove;
    private int eLossOnTurn;
    private int eGainOnEat;

    private final int[] xOffset;
    private final int[] xReverseOffset;
    private final int[] yOffset;
    private int[] sensorArray;

    private final World mat;

    public Entity(World m) {
        mat = m;
        moves = 0;
        energy = 100;
        inventory = false;
        eLossOnMove = 5;
        eLossOnTurn = 1;
        eGainOnEat = 10;
        xOffset = new int[]{-4, -3, -2, -1, 0, 1, 2, 3, 4, -3, -2, -1, 0, 1, 2, 3, -2, -1, 0, 1, 2, -1, 0, 1, 0};
        xReverseOffset = new int[]{4, 3, 2, 1, 0, -1, -2, -3, -4, 3, 2, 1, 0, -1, -2, -3, 2, 1, 0, -1, -2, 1, 0, -1, 0};
        yOffset = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 0};
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

    public void coneUp() {

        sensorArray = new int[xOffset.length];
        for (int i = 0; i < sensorArray.length; i++) {
            int sX = x + xOffset[i];
            int sY = y - yOffset[i];
            if (this.inBounds(sX, sY)) {
                sensorArray[i] = mat.getMatrix()[sY][sX];
            } else {
                sensorArray[i] = -1;
            }
        }
    }

    public void coneRight() {

        sensorArray = new int[xOffset.length];
        for (int i = 0; i < sensorArray.length; i++) {
            int sX = x + yOffset[i];
            int sY = y + xOffset[i];
            if (this.inBounds(sX, sY)) {
                sensorArray[i] = mat.getMatrix()[sY][sX];
            } else {
                sensorArray[i] = -1;
            }
        }
    }

    public void coneDown() {

        sensorArray = new int[xOffset.length];
        for (int i = 0; i < sensorArray.length; i++) {
            int sX = x + xReverseOffset[i];
            int sY = y + yOffset[i];
            if (this.inBounds(sX, sY)) {
                sensorArray[i] = mat.getMatrix()[sY][sX];
            } else {
                sensorArray[i] = -1;
            }
        }
    }

    public void coneLeft() {

        sensorArray = new int[xOffset.length];
        for (int i = 0; i < sensorArray.length; i++) {
            int sX = x - yOffset[i];
            int sY = y + xReverseOffset[i];
            if (this.inBounds(sX, sY)) {
                sensorArray[i] = mat.getMatrix()[sY][sX];
            } else {
                sensorArray[i] = -1;
            }
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

    public int[] getSensor() {
        return sensorArray;
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
        if (mat.getMatrix()[y][x] == 2 && inventory == true) {
            t.plantTile(x, y);
            inventory = false;
        }
    }

    public void eat(Tile t) {
        if (mat.getMatrix()[y][x] == 1) {
            t.emptyTile(this.getX(), this.getY());
            energy += eGainOnEat;
        }
    }

}
