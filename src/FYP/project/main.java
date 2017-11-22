package FYP.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Chris
 */
public class main {
    

    public static void main(String[] args) {
        
        Neuron test;
        double[][] t = new double[10][10];
        t[0][0] = 1.2;
        test = new Neuron(Neuron.Type.INPUT, 1.0, t);
        System.out.println(test.getInput());
//        World m = new World(150);
////        m.loadWorld();
////        m.saveWorld();
//        Tile t = new Tile(m);
//
//        Window win = new Window();
//        win.createWindow(m, 5);
//
//        Entity e = new Entity(m);
//        e.setPosition(5, 5, 0);
//
//        t.farmAll();
//        e.setEnergy(1000);
//        e.setInventory(true);
//
//        int x = 0;
//        while (e.getEnergy() > 0) {
//            Random ran = new Random();
//            int num = ran.nextInt(101);
//            if (num < 1) {
//                e.setInventory(true);
////                e.turnLeft();
//                e.move();
//                e.farm(t);
//                e.eat(t);
//                win.repaint();
//            } else if (num < 15) {
//                e.setInventory(true);
//                e.turnRight();
//                e.move();
//                e.farm(t);
//                e.eat(t);
//                win.repaint();
//            } else {
//                e.setInventory(true);
//                e.move();
//                e.farm(t);
//                e.eat(t);
//                win.repaint();
//            }
//
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//            x++;
//        }
//        System.out.println(x);
//        System.out.println(e.getEnergy());
//        System.out.println("DONE");

//        for (ArrayList<Integer> array : e.getSensor()){
//            int x = array.get(0);
//            int y = array.get(1);
//            System.out.println("X: " + x + " Y: " + y + " V: " + m.getMatrix()[y][x]);
//        }
//
//
//        Scanner scanner = new Scanner(System.in);
//        String command = scanner.nextLine();
//        System.out.println(command);
//        if (command.equals("test")) {
//
//            e.move();
//            e.eat(t);
//            System.out.println(e.getEnergy());
//            win.repaint();
//        }
    }
}
