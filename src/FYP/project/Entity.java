package FYP.project;

import java.util.ArrayList;
import java.util.List;

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
    private int eLossOnAction;
    private int foodEaten;

    private final int[] xOffset;
    private final int[] xReverseOffset;
    private final int[] yOffset;
    private int[] sensorArray;

    private List<Neuron> in;
    private List<Neuron> out;
    private Layer input;
    private Layer output;

    private final World mat;

    public Entity(World m) {
        mat = m;
        moves = 0;
        energy = 100;
        foodEaten = 0;
        inventory = false;
        eLossOnMove = 1;
        eLossOnTurn = 1;
        eLossOnAction = 1;
        eGainOnEat = 10;
        xOffset = new int[]{-4, -3, -2, -1, 0, 1, 2, 3, 4, -3, -2, -1, 0, 1, 2, 3, -2, -1, 0, 1, 2, -1, 0, 1, -1, 0, 1};
        xReverseOffset = new int[]{4, 3, 2, 1, 0, -1, -2, -3, -4, 3, 2, 1, 0, -1, -2, -3, 2, 1, 0, -1, -2, 1, 0, -1, 1, 0, -1};
        yOffset = new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 0, 0, 0};
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
        }
        energy -= eLossOnMove;
    }

    public void moveRight() {
        if (x < (mat.getSize() - 1)) {
            x += 1;
            this.setLocation(x, y);
        }
        energy -= eLossOnMove;
    }

    public void moveDown() {
        if (y < (mat.getSize() - 1)) {
            y += 1;
            this.setLocation(x, y);
        }
        energy -= eLossOnMove;
    }

    public void moveLeft() {
        if (x > 0) {
            x -= 1;
            this.setLocation(x, y);
        }
        energy -= eLossOnMove;
    }

    public void turnLeft() {
        if (o == 0) {
            o = 3;
        } else {
            o = (o - 1) % 4;
        }
        energy -= eLossOnTurn;
        moves++;
    }

    public void turnRight() {
        o = (o + 1) % 4;
        energy -= eLossOnTurn;
        moves++;
    }

    public void coneUp() {

        sensorArray = new int[xOffset.length];
        for (int i = 0; i < sensorArray.length; i++) {
            int sX = x + xOffset[i];
            int sY = y - yOffset[i];
            if (this.inBounds(sX, sY)) {
                sensorArray[i] = mat.getMatrix()[sY][sX];
            } else {
                sensorArray[i] = 0;
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
                sensorArray[i] = 0;
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
                sensorArray[i] = 0;
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
                sensorArray[i] = 0;
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

    public void setMoves(int m) {
        moves = m;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int e, int m) {
        if (e > m) {
            energy = m;
        } else {
            energy = e;
        }
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

    public int getELossOnAction() {
        return eLossOnAction;
    }

    public void setELossOnAction(int e) {
        eLossOnAction = e;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public void setFoodEaten(int f) {
        foodEaten = 0;
    }

    public void pickUp(Tile t) {
        if (mat.getMatrix()[y][x] == 2 && inventory == false) {
            t.emptyTile(this.getX(), this.getY());
            inventory = true;
        }
        energy -= eLossOnAction;
        moves++;
    }

    public void drop(Tile t) {
        if (mat.getMatrix()[y][x] == 1 && inventory == true) {
            t.foodTile(this.getX(), this.getY());
            inventory = false;
        } else if (mat.getMatrix()[y][x] == 3 && inventory == true) {
            this.farm(t);
        }
        energy -= eLossOnAction;
        moves++;
    }

    public int getInventory() {
        if (this.inventory == true) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean returnInventory() {
        return inventory;
    }

    public void setInventory(boolean b) {
        inventory = b;
    }

    public void farm(Tile t) {
        if (mat.getMatrix()[y][x] == 3 && inventory == true) {
            t.plantTile(x, y);
            inventory = false;
        }
    }

    public void eat(Tile t) {
        if (mat.getMatrix()[y][x] == 2) {
            t.emptyTile(this.getX(), this.getY());
            this.setEnergy(energy + eGainOnEat, 1000);
            foodEaten++;
        }
        this.setEnergy(energy - eLossOnAction, 1000);
        moves++;
    }

//    public Layer createInputs() {
//        in = new ArrayList<>();
//
//        Neuron inv = new Neuron("inv");
//        Neuron sensor1 = new Neuron("sensor1");
//        Neuron sensor2 = new Neuron("sensor2");
//        Neuron sensor3 = new Neuron("sensor3");
//        Neuron sensor4 = new Neuron("sensor4");
//        Neuron sensor5 = new Neuron("sensor5");
//        Neuron sensor6 = new Neuron("sensor6");
//        Neuron sensor7 = new Neuron("sensor7");
//        Neuron sensor8 = new Neuron("sensor8");
//        Neuron sensor9 = new Neuron("sensor9");
//        Neuron sensor10 = new Neuron("sensor10");
//        Neuron sensor11 = new Neuron("sensor11");
//        Neuron sensor12 = new Neuron("sensor12");
//        Neuron sensor13 = new Neuron("sensor13");
//        Neuron sensor14 = new Neuron("sensor14");
//        Neuron sensor15 = new Neuron("sensor15");
//        Neuron sensor16 = new Neuron("sensor16");
//        Neuron sensor17 = new Neuron("sensor17");
//        Neuron sensor18 = new Neuron("sensor18");
//        Neuron sensor19 = new Neuron("sensor19");
//        Neuron sensor20 = new Neuron("sensor20");
//        Neuron sensor21 = new Neuron("sensor21");
//        Neuron sensor22 = new Neuron("sensor22");
//        Neuron sensor23 = new Neuron("sensor23");
//        Neuron sensor24 = new Neuron("sensor24");
//        Neuron sensor25 = new Neuron("sensor25");
//        in.add(inv);
//        in.add(sensor1);
//        in.add(sensor2);
//        in.add(sensor3);
//        in.add(sensor4);
//        in.add(sensor5);
//        in.add(sensor6);
//        in.add(sensor7);
//        in.add(sensor8);
//        in.add(sensor9);
//        in.add(sensor10);
//        in.add(sensor11);
//        in.add(sensor12);
//        in.add(sensor13);
//        in.add(sensor14);
//        in.add(sensor15);
//        in.add(sensor16);
//        in.add(sensor17);
//        in.add(sensor18);
//        in.add(sensor19);
//        in.add(sensor20);
//        in.add(sensor21);
//        in.add(sensor22);
//        in.add(sensor23);
//        in.add(sensor24);
//        in.add(sensor25);
//        input = new Layer(in);
//        return input;
//    }
//    public Layer getInput() {
//        return input;
//    }
//    public Layer createOutputs() {
//        out = new ArrayList<>();
//
//        Neuron move = new Neuron("move");
//        Neuron turnLeft = new Neuron("turnLeft");
//        Neuron turnRight = new Neuron("turnRight");
//        Neuron eat = new Neuron("eat");
//        Neuron pickUp = new Neuron("pickUp");
//        Neuron drop = new Neuron("drop");
//        out = new ArrayList<>();
//        out.add(move);
//        out.add(turnLeft);
//        out.add(turnRight);
//        out.add(eat);
//        out.add(pickUp);
//        out.add(drop);
//        output = new Layer(out);
//        return output;
//    }
//    public Layer getOutput() {
//        return output;
//    }
//    public void updateInputValues() {
//        this.coneSensor();
//        in.get(0).setValue(this.getInventory());
//        in.get(1).setValue(this.getSensor()[0]);
//        in.get(2).setValue(this.getSensor()[1]);
//        in.get(3).setValue(this.getSensor()[2]);
//        in.get(4).setValue(this.getSensor()[3]);
//        in.get(5).setValue(this.getSensor()[4]);
//        in.get(6).setValue(this.getSensor()[5]);
//        in.get(7).setValue(this.getSensor()[6]);
//        in.get(8).setValue(this.getSensor()[7]);
//        in.get(9).setValue(this.getSensor()[8]);
//        in.get(10).setValue(this.getSensor()[8]);
//        in.get(11).setValue(this.getSensor()[10]);
//        in.get(12).setValue(this.getSensor()[11]);
//        in.get(13).setValue(this.getSensor()[12]);
//        in.get(14).setValue(this.getSensor()[13]);
//        in.get(15).setValue(this.getSensor()[14]);
//        in.get(16).setValue(this.getSensor()[15]);
//        in.get(17).setValue(this.getSensor()[16]);
//        in.get(18).setValue(this.getSensor()[17]);
//        in.get(19).setValue(this.getSensor()[18]);
//        in.get(20).setValue(this.getSensor()[19]);
//        in.get(21).setValue(this.getSensor()[20]);
//        in.get(22).setValue(this.getSensor()[21]);
//        in.get(23).setValue(this.getSensor()[22]);
//        in.get(24).setValue(this.getSensor()[23]);
//        in.get(25).setValue(this.getSensor()[24]);
//    }
//    public void actionWin(Window win, Tile t, boolean b) {
//        double[] outputs = new double[6];
//        int i = 0;
//        for (Neuron nOut : this.getOutput().getNeurons()) {
//            outputs[i] = nOut.returnValue();
//            i++;
//        }
//        int max = 0;
//        for (int a = 0; a < 6; a++) {
//            if (outputs[a] > outputs[max]) {
//                max = a;
//            }
//        }
//        if (b) {
//            System.out.println("Action: " + max);
//        }
//
//        switch (max) {
//            case 0:
//                this.move();
//                win.reload();
//                break;
//            case 1:
//                this.turnLeft();
//                win.reload();
//                break;
//            case 2:
//                this.turnRight();
//                win.reload();
//                break;
//            case 3:
//                this.eat(t);
//                win.reload();
//                break;
//            case 4:
//                this.pickUp(t);
//                win.reload();
//                break;
//            case 5:
//                this.drop(t);
//                win.reload();
//                break;
//        }
//    }
    public void actionWin(Window win, Tile t, boolean b, List<Double> d) {
        int max = 0;
        for (int a = 0; a < 6; a++) {
            if (d.get(a) > d.get(max)) {
                max = a;
            }
        }
        if (b) {
            System.out.println("Action: " + max);
        }

        switch (max) {
            case 0:
                this.move();
                win.reload();
                break;
            case 1:
                this.turnLeft();
                win.reload();
                break;
            case 2:
                this.turnRight();
                win.reload();
                break;
            case 3:
                this.eat(t);
                win.reload();
                break;
            case 4:
                this.pickUp(t);
                win.reload();
                break;
            case 5:
                this.drop(t);
                win.reload();
                break;
        }
    }

    public void actionNoWin(Window win, Tile t, boolean b, List<Double> d) {
        int max = 0;
        for (int a = 0; a < 6; a++) {
            if (d.get(a) > d.get(max)) {
                max = a;
            }
        }
        if (b) {
            System.out.println("Action: " + max);
        }

        switch (max) {
            case 0:
                this.move();
//                win.reload();
                break;
            case 1:
                this.turnLeft();
//                win.reload();
                break;
            case 2:
                this.turnRight();
//                win.reload();
                break;
            case 3:
                this.eat(t);
//                win.reload();
                break;
            case 4:
                this.pickUp(t);
//                win.reload();
                break;
            case 5:
                this.drop(t);
//                win.reload();
                break;
        }
    }
//
//    public void action(Tile t, boolean b) {
//        double[] outputs = new double[6];
//        int i = 0;
//        for (Neuron nOut : this.getOutput().getNeurons()) {
//            outputs[i] = nOut.returnValue();
//            i++;
//        }
//        int max = 0;
//        for (int a = 0; a < 6; a++) {
//            if (outputs[a] > outputs[max]) {
//                max = a;
//            }
//        }
//        if (b) {
//        System.out.println("Action: " + max);
//        }
//
//        switch (max) {
//            case 0:
//                this.move();
//                break;
//            case 1:
//                this.turnLeft();
//                break;
//            case 2:
//                this.turnRight();
//                break;
//            case 3:
//                this.eat(t);
//                break;
//            case 4:
//                this.pickUp(t);
//                break;
//            case 5:
//                this.drop(t);
//                break;
//        }
//    }
}
