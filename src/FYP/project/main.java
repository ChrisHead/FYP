package FYP.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class main {

    public static void main(String[] args) {

//        Layer test = new Layer(3);
//        List<Neuron> n = new ArrayList<>();
//        List<Layer> h = new ArrayList<>();
//        Neuron one = new Neuron();
//        Neuron two = new Neuron();
//        Neuron three = new Neuron();
//        n.add(one);
//        n.add(two);
//        n.add(three);
//        Layer input = new Layer(n);
//
//        Neuron four = new Neuron();
//        Neuron five = new Neuron();
//        Neuron seven = new Neuron();
//        n = new ArrayList<>();
//        n.add(four);
//        n.add(five);
//        n.add(seven);
//        Layer h1 = new Layer(n);
//
//        Neuron six = new Neuron();
//        Neuron eight = new Neuron();
//        n = new ArrayList<>();
//        n.add(six);
//        n.add(eight);
//        Layer h2 = new Layer(n);
//
//        h.add(h1);
//        h.add(h2);
//
//        Neuron nine = new Neuron();
//        n = new ArrayList<>();
//        n.add(nine);
//        Layer output = new Layer(n);
//
//        Network network = new Network(input, h, output);
//
//        one.setValue(1);
//        two.setValue(2);
//        three.setValue(3);
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
//
//
//        Axon aOne = new Axon(one, four, 10.0);
//        Axon aTwo = new Axon(one, five, 5.0);
//        Axon aNine = new Axon(one, seven, 3.0);
//        Axon aThree = new Axon(two, four, 10.0);
//        Axon aFour = new Axon(two, five, 5.0);
//        Axon aTen = new Axon(two, seven, 3.0);
//        Axon aFive = new Axon(three, four, 10.0);
//        Axon aSix = new Axon(three, five, 5.0);
//        Axon aEleven = new Axon(three, seven, 3.0);
//        Axon aSeven = new Axon(four, six, 10.0);
//        Axon aTwelve = new Axon(four, eight, 4.0);
//        Axon aEight = new Axon(five, six, 5.0);
//        Axon aThirteen = new Axon(five, eight, 4.0);
//        Axon aSixteen = new Axon(seven, six, 4.0);
//        Axon aSeventeen = new Axon(seven, eight, 4.0);
//        Axon aFourteen = new Axon(six, nine, 8.0);
//        Axon aFithteen = new Axon(eight, nine, 8.0);
//
//        network.fullyConnect();
//        network.noHiddenConnect();
//        network.runNetwork();
//        
//        Neuron[] n = new Neuron[20];
//        for (int i = 0; i < 11; i++) {
//            n[i] = new Neuron(); 
//        }
//        n[3].setValue(1.2);
//        System.out.println(n[3].returnValue());
//
//
//
//
        World m = new World(50);
//
        Window win = new Window();
        Tile t = new Tile(m);
        Entity e = new Entity(m);
        e.setPosition(24, 24, 0);
        win.createWindow(m, 10, e);

//        t.farmAll();
//
        List<Neuron> n = new ArrayList<>();
        List<Layer> h = new ArrayList<>();
//
        Neuron inventory = new Neuron();
        Neuron sensor1 = new Neuron();
        Neuron sensor2 = new Neuron();
        Neuron sensor3 = new Neuron();
        Neuron sensor4 = new Neuron();
        Neuron sensor5 = new Neuron();
        Neuron sensor6 = new Neuron();
        Neuron sensor7 = new Neuron();
        Neuron sensor8 = new Neuron();
        Neuron sensor9 = new Neuron();
        Neuron sensor10 = new Neuron();
        Neuron sensor11 = new Neuron();
        Neuron sensor12 = new Neuron();
        Neuron sensor13 = new Neuron();
        Neuron sensor14 = new Neuron();
        Neuron sensor15 = new Neuron();
        Neuron sensor16 = new Neuron();
        Neuron sensor17 = new Neuron();
        Neuron sensor18 = new Neuron();
        Neuron sensor19 = new Neuron();
        Neuron sensor20 = new Neuron();
        Neuron sensor21 = new Neuron();
        Neuron sensor22 = new Neuron();
        Neuron sensor23 = new Neuron();
        Neuron sensor24 = new Neuron();
        Neuron sensor25 = new Neuron();
        n.add(inventory);
        n.add(sensor1);
        n.add(sensor2);
        n.add(sensor3);
        n.add(sensor4);
        n.add(sensor5);
        n.add(sensor6);
        n.add(sensor7);
        n.add(sensor8);
        n.add(sensor9);
        n.add(sensor10);
        n.add(sensor11);
        n.add(sensor12);
        n.add(sensor13);
        n.add(sensor14);
        n.add(sensor15);
        n.add(sensor16);
        n.add(sensor17);
        n.add(sensor18);
        n.add(sensor19);
        n.add(sensor20);
        n.add(sensor21);
        n.add(sensor22);
        n.add(sensor23);
        n.add(sensor24);
        n.add(sensor25);
        Layer input = new Layer(n);
//
        Neuron h1 = new Neuron();
        Neuron h2 = new Neuron();
        Neuron h3 = new Neuron();
        Neuron h4 = new Neuron();
        Neuron h5 = new Neuron();
        Neuron h6 = new Neuron();
        Neuron h7 = new Neuron();
        Neuron h8 = new Neuron();
        Neuron h9 = new Neuron();
        Neuron h10 = new Neuron();
        Neuron h11 = new Neuron();
        Neuron h12 = new Neuron();
        Neuron h13 = new Neuron();
        Neuron h14 = new Neuron();
        Neuron h15 = new Neuron();
        n = new ArrayList<>();
        n.add(h1);
        n.add(h2);
        n.add(h3);
        n.add(h4);
        n.add(h5);
        n.add(h6);
        n.add(h7);
        n.add(h8);
        n.add(h9);
        n.add(h10);
        n.add(h11);
        n.add(h12);
        n.add(h13);
        n.add(h14);
        n.add(h15);
        Layer hl1 = new Layer(n);
//
        Neuron h16 = new Neuron();
        Neuron h17 = new Neuron();
        Neuron h18 = new Neuron();
        Neuron h19 = new Neuron();
        Neuron h20 = new Neuron();
        Neuron h21 = new Neuron();
        Neuron h22 = new Neuron();
        Neuron h23 = new Neuron();
        Neuron h24 = new Neuron();
        Neuron h25 = new Neuron();
        n = new ArrayList<>();
        n.add(h16);
        n.add(h17);
        n.add(h18);
        n.add(h19);
        n.add(h20);
        n.add(h21);
        n.add(h22);
        n.add(h23);
        n.add(h24);
        n.add(h25);
        Layer hl2 = new Layer(n);
//
        h.add(hl1);
        h.add(hl2);
//
        Neuron move = new Neuron();
        Neuron turnLeft = new Neuron();
        Neuron turnRight = new Neuron();
        Neuron eat = new Neuron();
        Neuron pickUp = new Neuron();
        Neuron drop = new Neuron();
        n = new ArrayList<>();
        n.add(move);
        n.add(turnLeft);
        n.add(turnRight);
        n.add(eat);
        n.add(pickUp);
        n.add(drop);
        Layer output = new Layer(n);
//
        Network network = new Network(input, h, output);
//        network.fullyConnect();

        e.setEnergy(100);
//        e.setInventory(true);
//       
        int x = 0;
        while (e.getEnergy() > 0) {

            e.coneSensor();
            inventory.setValue(e.getInventory());
            sensor1.setValue(e.getSensor()[0]);
            sensor2.setValue(e.getSensor()[1]);
            sensor3.setValue(e.getSensor()[2]);
            sensor4.setValue(e.getSensor()[3]);
            sensor5.setValue(e.getSensor()[4]);
            sensor6.setValue(e.getSensor()[5]);
            sensor7.setValue(e.getSensor()[6]);
            sensor8.setValue(e.getSensor()[7]);
            sensor9.setValue(e.getSensor()[8]);
            sensor10.setValue(e.getSensor()[8]);
            sensor11.setValue(e.getSensor()[10]);
            sensor12.setValue(e.getSensor()[11]);
            sensor13.setValue(e.getSensor()[12]);
            sensor14.setValue(e.getSensor()[13]);
            sensor15.setValue(e.getSensor()[14]);
            sensor16.setValue(e.getSensor()[15]);
            sensor17.setValue(e.getSensor()[16]);
            sensor18.setValue(e.getSensor()[17]);
            sensor19.setValue(e.getSensor()[18]);
            sensor20.setValue(e.getSensor()[19]);
            sensor21.setValue(e.getSensor()[20]);
            sensor22.setValue(e.getSensor()[21]);
            sensor23.setValue(e.getSensor()[22]);
            sensor24.setValue(e.getSensor()[23]);
            sensor25.setValue(e.getSensor()[24]);

            h1.setValue(0.0);
            h2.setValue(0.0);
            h3.setValue(0.0);
            h4.setValue(0.0);
            h5.setValue(0.0);
            h6.setValue(0.0);
            h7.setValue(0.0);
            h8.setValue(0.0);
            h9.setValue(0.0);
            h10.setValue(0.0);
            h11.setValue(0.0);
            h12.setValue(0.0);
            h13.setValue(0.0);
            h14.setValue(0.0);
            h15.setValue(0.0);

            h16.setValue(0.0);
            h17.setValue(0.0);
            h18.setValue(0.0);
            h19.setValue(0.0);
            h20.setValue(0.0);
            h21.setValue(0.0);
            h22.setValue(0.0);
            h23.setValue(0.0);
            h24.setValue(0.0);
            h25.setValue(0.0);

            move.setValue(0.0);
            turnLeft.setValue(0.0);
            turnRight.setValue(0.0);
            eat.setValue(0.0);
            pickUp.setValue(0.0);
            drop.setValue(0.0);

            network.fullyConnect();
            network.runNetwork();

            double[] outputs = new double[6];
            int i = 0;
            for (Neuron out : output.getNeurons()) {
                outputs[i] = out.returnValue();
                i++;
            }
            int max = 0;
            for (int a = 0; a < 6; a++) {
                if (outputs[a] > outputs[max]) {
                    max = a;
                }
            }
            System.out.println("Action: " + max);

            switch (max) {
                case 0:
                    e.move();
                    win.reload();
                    break;
                case 1:
                    e.turnLeft();
                    win.reload();
                    break;
                case 2:
                    e.turnRight();
                    win.reload();
                    break;
                case 3:
                    e.eat(t);
                    win.reload();
                    break;
                case 4:
                    e.pickUp(t);
                    win.reload();
                    break;
                case 5:
                    e.drop(t);
                    win.reload();
                    break;
            }

            System.out.println("Energy: " + e.getEnergy());
            System.out.println();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            x++;
        }

        System.out.println(x);
        System.out.println("DONE");
        move.printValue();

//       move.getInputs();
//       h1.getInputs();
//       System.out.println();
//       h2.getInputs();
//       network.printSize();
//
//
//
//
//
//
//        Neuron one = new Neuron();
//        Neuron two = new Neuron();
//
//        Axon test = new Axon(one, two, 1.0);
//        System.out.println(test.getWeight());
//        test = new Axon(one, two, 2.0);
//        System.out.println(test.getWeight());
//        
        //NEEDED: Array of neuron names, no. of axon to be created 
//        
//        Axon[] n = new Axon[20];
//        for (int i = 0; i < 11; i++) {
//            n[i] = new Axon(one, two, 2.0); 
//        }
//        n[0].setWeight(1.0);
//        System.out.println(n[0].getWeight());
//        System.out.println(n[1].getWeight());
//
//
//        World m = new World(30);
//////        m.loadWorld();
//////        m.saveWorld();
//        Tile t = new Tile(m);
////
//        Window win = new Window();
//        Entity e = new Entity(m);
//        e.setPosition(5, 5, 0);
//        win.createWindow(m, 20, e);
////
////
//        t.farmAll();
//        e.setEnergy(1000);
//        e.setInventory(true);
////
//        int x = 0;
//        while (e.getEnergy() > 0) {
//            Random ran = new Random();
//            int num = ran.nextInt(101);
//            if (num < 1) {
//                e.setInventory(true);
//                e.turnLeft();
//                e.move();
//                e.farm(t);
//                e.eat(t);
//                win.reload();
//            } else if (num < 15) {
//                e.setInventory(true);
//                e.turnRight();
//                e.move();
//                e.farm(t);
//                e.eat(t);
//                win.reload();
//            } else {
//                e.setInventory(true);
//                e.move();
//                e.farm(t);
//                e.eat(t);
//                win.reload();
//            }
//
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//            x++;
//        }
//
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
//            e.showEntity(m);
//            System.out.println("Add Component");
//            win.repaint();
//        }
    }
}
