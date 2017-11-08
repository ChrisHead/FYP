package FYP.project;

import java.util.*;
import java.io.*;

/**
 * @author Chris
 */
public class World implements Serializable {

    private static final long serialVersionUID = 01;
    private final int size;
    private int[][] matrix;

    public World(int gridSize) {
        size = gridSize;
        matrix = new int[size][size];
        this.createMatrix();
    }

    public final void createMatrix() {
        for (int[] array : matrix) {
            for (int i = 0; i < array.length; i++) {
                Random ran = new Random();
                int num = ran.nextInt(101);
                if (num == 100) {
                    num = 2;
                } else if (num < 85) {
                    num = 0;
                } else {
                    num = 1;
                }
                array[i] = num;
            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void printMatrix() {
        for (int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
    }

    public int getSize() {
        return size;
    }

    public void printSize() {
        System.out.println(size);
    }

    public void saveWorld() {
        FileOutputStream f = null;
        ObjectOutputStream o = null;
        try {
            f = new FileOutputStream("World.txt");
            o = new ObjectOutputStream(f);
            o.writeObject(matrix);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }

    public void loadWorld() {
        FileInputStream f = null;
        ObjectInputStream o = null;
        try {
            f = new FileInputStream("World.txt");
            o = new ObjectInputStream(f);
            matrix = (int[][]) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e);
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (o != null) {
                    o.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }
}
