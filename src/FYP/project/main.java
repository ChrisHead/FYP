package FYP.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Chris
 */
public class main {

    public static void main(String[] args) {

        List<Neuron> n = new ArrayList<>();
        List<Layer> h = new ArrayList<>();

        Neuron one = new Neuron();
        Neuron two = new Neuron();
        Neuron three = new Neuron();
        n.add(one);
        n.add(two);
        n.add(three);
        Layer input = new Layer(n);

        Neuron four = new Neuron();
        Neuron five = new Neuron();
        Neuron seven = new Neuron();
        n = new ArrayList<>();
        n.add(four);
        n.add(five);
        n.add(seven);
        Layer h1 = new Layer(n);

        Neuron six = new Neuron();
        Neuron eight = new Neuron();
        n = new ArrayList<>();
        n.add(six);
        n.add(eight);
        Layer h2 = new Layer(n);

        h.add(h1);
        h.add(h2);

        Neuron nine = new Neuron();
        n = new ArrayList<>();
        n.add(nine);
        Layer output = new Layer(n);

        Network network = new Network(input, h, output);

        one.setValue(1);
        two.setValue(2);
        three.setValue(3);

//        Axon aOne = new Axon(one, four, Math.random());
//        Axon aTwo = new Axon(one, five, Math.random());
//        Axon aNine = new Axon(one, seven, Math.random());
//        Axon aThree = new Axon(two, four, Math.random());
//        Axon aFour = new Axon(two, five, Math.random());
//        Axon aTen = new Axon(two, seven, Math.random());
//        Axon aFive = new Axon(three, four, Math.random());
//        Axon aSix = new Axon(three, five, Math.random());
//        Axon aEleven = new Axon(three, seven, Math.random());
//        Axon aSeven = new Axon(four, six, Math.random());
//        Axon aTwelve = new Axon(four, eight, Math.random());
//        Axon aEight = new Axon(five, six, Math.random());
//        Axon aThirteen = new Axon(five, eight, Math.random());
//        Axon aSixteen = new Axon(seven, six, Math.random());
//        Axon aSeventeen = new Axon(seven, eight, Math.random());
//        Axon aFourteen = new Axon(six, nine, Math.random());
//        Axon aFithteen = new Axon(eight, nine, Math.random());

        Axon aOne = new Axon(one, four, 10.0);
        Axon aTwo = new Axon(one, five, 5.0);
        Axon aNine = new Axon(one, seven, 3.0);
        Axon aThree = new Axon(two, four, 10.0);
        Axon aFour = new Axon(two, five, 5.0);
        Axon aTen = new Axon(two, seven, 3.0);
        Axon aFive = new Axon(three, four, 10.0);
        Axon aSix = new Axon(three, five, 5.0);
        Axon aEleven = new Axon(three, seven, 3.0);
        Axon aSeven = new Axon(four, six, 10.0);
        Axon aTwelve = new Axon(four, eight, 4.0);
        Axon aEight = new Axon(five, six, 5.0);
        Axon aThirteen = new Axon(five, eight, 4.0);
        Axon aSixteen = new Axon(seven, six, 4.0);
        Axon aSeventeen = new Axon(seven, eight, 4.0);
        Axon aFourteen = new Axon(six, nine, 8.0);
        Axon aFithteen = new Axon(eight, nine, 8.0);

//        four.getInputs();
        network.runNetwork();







//        Neuron[] n = new Neuron[20];
//        for (int i = 0; i < 11; i++) {
//            n[i] = new Neuron(); 
//        }
//        n[3].setValue(1.2);
//        System.out.println(n[3].returnValue());





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
