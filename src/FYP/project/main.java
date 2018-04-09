package FYP.project;

import java.util.*;

/**
 *
 * @author Chris
 */
public class main {

    public static void main(String[] args) {

        List<String> inputs = new ArrayList<>();
        List<String> outputs = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        inputs.add("energy");
        inputs.add("inv");
        inputs.add("sensor1");
        inputs.add("sensor2");
        inputs.add("sensor3");
        inputs.add("sensor4");
        inputs.add("sensor5");
        inputs.add("sensor6");
        inputs.add("sensor7");
        inputs.add("sensor8");
        inputs.add("sensor9");
        inputs.add("sensor10");
        inputs.add("sensor11");
        inputs.add("sensor12");
        inputs.add("sensor13");
        inputs.add("sensor14");
        inputs.add("sensor15");
        inputs.add("sensor16");
        inputs.add("sensor17");
        inputs.add("sensor18");
        inputs.add("sensor19");
        inputs.add("sensor20");
        inputs.add("sensor21");
        inputs.add("sensor22");
        inputs.add("sensor23");
        inputs.add("sensor24");
        inputs.add("sensor25");
        inputs.add("sensor26");
        inputs.add("sensor27");
//       
        outputs.add("move");
        outputs.add("turnLeft");
        outputs.add("turnRight");
        outputs.add("eat");
        outputs.add("pickUp");
        outputs.add("drop");

        //TESTING METHOD
//        NEAT neat = new NEAT(inputs, outputs);
//        World w = new World(26);
//        w.loadWorld();
//        Window win = new Window();
//        Entity e = new Entity(w);
//        win.createWindow(w, 30, e, false);
//        Tile t = new Tile(w);
//        e.setEGainOnEat(10);
//        int startingEnergy = 250;
//        int maxEnergy = 1000;
//        e.setMoves(0);
//        e.setFoodEaten(0);
//        e.setInventory(false);
//        t.foodAll();
//        e.setPosition(13, 13, 0);
//        e.setEnergy(startingEnergy, maxEnergy);
//        neat.loadGenome(480, 49);
//        while (e.getEnergy() > 0) {
//            values = new ArrayList<>();
//            e.coneSensor();
//            values.add((double) (e.getEnergy() - 1) / (maxEnergy - 1));
//            values.add((double) e.getInventory());
//            for (int i : e.getSensor()) {
//                values.add((double) i);
//            }
//            neat.runNetwork(values, false);
//            e.actionWin(win, t, false, neat.returnRunValues());
////            System.out.println("Energy: " + e.getEnergy());
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        System.out.println(e.getFoodEaten());
//        System.out.println("DONE");

        //RUNNING METHOD
        NEAT neat = new NEAT(inputs, outputs);
        World w = new World(26);
        w.loadWorld();
        Window win = new Window();
        Entity e = new Entity(w);
        win.createWindow(w, 30, e, false);
        Tile t = new Tile(w);
        e.setEGainOnEat(10);

        int populationLimit = 100;
        int generationLimit = 500;

        int startingEnergy = 250;
        int maxEnergy = 1000;
        neat.createStartingGeneration(populationLimit);
        int a = 0;
        while (a < generationLimit) {
//        while (t.getFarmed() < 2) {
            System.out.println("Generation: " + a);
            int b = 0;
            while (b < populationLimit) {
                e.setMoves(0);
                e.setFoodEaten(0);
                e.setInventory(false);
                w.loadWorld();
                t.foodAll();
                e.setPosition(13, 13, 0);
                e.setEnergy(startingEnergy, maxEnergy);
                neat.loadGenome(a, b);
                while (e.getEnergy() > 0) {
                    values = new ArrayList<>();
                    e.coneSensor();
                    values.add((double) e.getEnergy() / 1000);
                    values.add((double) e.getInventory());
                    for (int i : e.getSensor()) {
                        values.add((double) i);
                    }
                    neat.runNetwork(values, false);
                    e.actionNoWin(win, t, false, neat.returnRunValues());
                }
                neat.setFitness(b, e.getFoodEaten());
//                System.out.println("O: " + b + ", Food Eaten: " + e.getFoodEaten());
                b++;
            }
            neat.speciate(a, populationLimit, 1, 1, 3, 2.3, 500);
            neat.addToGraphSpecResults();
            neat.printSpecies();
            neat.mutate(populationLimit, 50, 40, 5, 50, 50, 50, 80, 90);
            System.out.println("");
//            System.out.println("Farmed: " + t.getFarmed());
//            t.setFarmed(0);
            a++;
        }
        neat.recordSpecies();
        neat.graphMaxfitness(generationLimit);
        neat.graphSpeciesResults(populationLimit);
        System.out.println("DONE");
        System.exit(0);
    }
}
