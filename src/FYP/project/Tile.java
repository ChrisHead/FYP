package FYP.project;

import java.util.Arrays;

/**
 *
 * @author Chris
 */
public class Tile {

    private final World test;

    public Tile(World m) {
        test = m;
    }

    public void plantTile(int x, int y) {
        if (y >= 1 && y < (test.getSize() + 1)) {
            if (x > 0) {
                test.getMatrix()[y - 1][x - 1] = 1;
            }
            test.getMatrix()[y - 1][x] = 1;
            if (x < test.getSize() - 1) {
                test.getMatrix()[y - 1][x + 1] = 1;
            }
        }

        if (y < test.getSize()) {
            if (x > 0) {
                test.getMatrix()[y][x - 1] = 1;
            }
            test.getMatrix()[y][x] = 0;
            if (x < test.getSize() - 1) {
                test.getMatrix()[y][x + 1] = 1;
            }
        }

        if (y < (test.getSize() - 1)) {
            if (x > 0) {
                test.getMatrix()[y + 1][x - 1] = 1;
            }
            test.getMatrix()[y + 1][x] = 1;
            if (x < test.getSize() - 1) {
                test.getMatrix()[y + 1][x + 1] = 1;
            }
        }
    }

    public void emptyTile(int x, int y) {
        test.getMatrix()[y][x] = 0;
    }

    public void foodTile(int x, int y) {
        test.getMatrix()[y][x] = 1;
    }

    public void farmTile(int x, int y) {
        test.getMatrix()[y][x] = 2;
    }

    public void emptyAll() {

        int a = 0;
        for (int[] array : test.getMatrix()) {
            for (int i = 0; i < array.length; i++) {
                this.emptyTile(a, i);
            }
            a++;
        }
    }
    
    public void foodAll() {

        int a = 0;
        for (int[] array : test.getMatrix()) {
            for (int i = 0; i < array.length; i++) {
                this.foodTile(a, i);
            }
            a++;
        }
    }
    
    public void farmAll() {

        int a = 0;
        for (int[] array : test.getMatrix()) {
            for (int i = 0; i < array.length; i++) {
                this.farmTile(a, i);
            }
            a++;
        }
    }

    public void printFarm() {
        System.out.println();
        for (int[] array : test.getMatrix()) {
            System.out.println(Arrays.toString(array));
        }
    }

}
