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
        
       List<String> inputs = new ArrayList<>();
       List<String> outputs = new ArrayList<>();
       List<Double> values = new ArrayList<>();
       
//       inputs.add("inv");
//       inputs.add("sensor1");
//       inputs.add("sensor2");
//       inputs.add("sensor3");
//       inputs.add("sensor4");
//       inputs.add("sensor5");
//       inputs.add("sensor6");
//       inputs.add("sensor7");
//       inputs.add("sensor8");
//       inputs.add("sensor9");
//       inputs.add("sensor10");
//       inputs.add("sensor11");
//       inputs.add("sensor12");
//       inputs.add("sensor13");
//       inputs.add("sensor14");
//       inputs.add("sensor15");
//       inputs.add("sensor16");
//       inputs.add("sensor17");
//       inputs.add("sensor18");
//       inputs.add("sensor19");
//       inputs.add("sensor20");
//       inputs.add("sensor21");
//       inputs.add("sensor22");
//       inputs.add("sensor23");
//       inputs.add("sensor24");
//       inputs.add("sensor25");
//       
//       outputs.add("move");
//       outputs.add("turnLeft");
//       outputs.add("turnRight");
//       outputs.add("eat");
//       outputs.add("pickUp");
//       outputs.add("drop");

         inputs.add("i1");
         inputs.add("i2");
         inputs.add("i3");
         
         outputs.add("o1");
         
         values.add(2.0);
         values.add(3.0);
         values.add(5.0);

       World w = new World(50);     
       Entity e = new Entity(w); 
       NEAT neat = new NEAT(inputs, outputs); 
       neat.startingGenome(e);
////       neat.buildNetworkFromGenome();
//       neat.saveGenome();
       neat.loadGenome(0,0);
       neat.createNeurons();
       neat.createAxons();
//       neat.runNetwork(values,true);
       neat.addAxon();
//       neat.printAxons();
//       neat.addNeuron();
       neat.saveGenome(0,0,false);
//        neat.printHiddenNeurons();

//       neat.getFitness();
//       neat.saveGenerationResults(0);
//       neat.printGenome();
        
//        World m = new World(50);
//        Window win = new Window();
//        Tile t = new Tile(w);
////        Entity e = new Entity(m);
//        e.setPosition(24, 24, 0);
//        win.createWindow(w, 10, e);
//        
//        e.coneSensor();
//        for (int i : e.getSensor()) {
//            System.out.println(i);
//        }
////      
//        List<Neuron> n;
//        List<Layer> h = new ArrayList<>();
//        Neuron h1 = new Neuron("h1");
//        Neuron h2 = new Neuron("h2");
//        Neuron h3 = new Neuron("h3");
//        Neuron h4 = new Neuron("h4");
//        Neuron h5 = new Neuron("h5");
//        Neuron h6 = new Neuron("h6");
//        Neuron h7 = new Neuron("h7");
//        Neuron h8 = new Neuron("h8");
//        Neuron h9 = new Neuron("h9");
//        Neuron h10 = new Neuron("h10");
//        Neuron h11 = new Neuron("h11");
//        Neuron h12 = new Neuron("h12");
//        Neuron h13 = new Neuron("h13");
//        Neuron h14 = new Neuron("h14");
//        Neuron h15 = new Neuron("h15");
//        n = new ArrayList<>();
//        n.add(h1);
//        n.add(h2);
//        n.add(h3);
//        n.add(h4);
//        n.add(h5);
//        n.add(h6);
//        n.add(h7);
//        n.add(h8);
//        n.add(h9);
//        n.add(h10);
//        n.add(h11);
//        n.add(h12);
//        n.add(h13);
//        n.add(h14);
//        n.add(h15);
//        Layer hl1 = new Layer(n);
////
//        Neuron h16 = new Neuron("h16");
//        Neuron h17 = new Neuron("h17");
//        Neuron h18 = new Neuron("h18");
//        Neuron h19 = new Neuron("h19");
//        Neuron h20 = new Neuron("h20");
//        Neuron h21 = new Neuron("h21");
//        Neuron h22 = new Neuron("h22");
//        Neuron h23 = new Neuron("h23");
//        Neuron h24 = new Neuron("h24");
//        Neuron h25 = new Neuron("h25");
//        n = new ArrayList<>();
//        n.add(h16);
//        n.add(h17);
//        n.add(h18);
//        n.add(h19);
//        n.add(h20);
//        n.add(h21);
//        n.add(h22);
//        n.add(h23);
//        n.add(h24);
//        n.add(h25);
//        Layer hl2 = new Layer(n);
////
//        h.add(hl1);
//        h.add(hl2);
//        Network network = new Network(e.createInputs(), h, e.createOutputs());
//        network.fullyConnect();
//
//        e.setEnergy(1000);
////       
//        int x = 0;
//        while (e.getEnergy() > 0) {
//            e.updateInputValues();
//            hl1.LayerZeroInputs();
//            hl2.LayerZeroInputs();
//            e.getOutput().LayerZeroInputs();
//            network.fullyConnect();
//            network.runNetwork(false, true);
//            e.actionWin(win, t, false);
//            System.out.println("Energy: " + e.getEnergy());
//            System.out.println();
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//            x++;
//        }
//        System.out.println(x);
//        System.out.println("DONE");

//        
//        
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
//        Innovation i = new Innovation();
//        i.setInv(100);
//        i.save();
//        System.out.println(i.getInv());
//        i = new Innovation();
//        System.out.println(i.getInv());
//        i.load();
//        System.out.println(i.getInv());
//       move.getInputs();
//       h1.getInputs();
//       System.out.println();
//       h2.getInputs();
//       network.printSize();
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
