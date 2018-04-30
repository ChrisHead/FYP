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
////        inputs.add("bias");
////       
        outputs.add("move");
        outputs.add("turnLeft");
        outputs.add("turnRight");
        outputs.add("eat");
        outputs.add("pickUp");
        outputs.add("drop");

        //XOR Test
//        inputs.add("input_1");
//        inputs.add("input_2");
////        inputs.add("bias");
////
//        outputs.add("output");

        World w = new World(11, 95, 65);
        Tile t = new Tile(w);
        Window win = new Window();
        Entity e = new Entity(w);
        e.setPosition(5, 8, 0);
        t.emptyAll();
//        w.loadWorld();
        win.createWindow(w,30,e,true);
//        
        //TESTING METHOD
//        NEAT neat = new NEAT(inputs, outputs, 3);
//        World w = new World(26, 95, 65);
//        w.loadWorld();
//        Window win = new Window();
//        Entity e = new Entity(w);
//        win.createWindow(w, 30, e, false);
//        Tile t = new Tile(w);
//        e.setEGainOnEat(5);
//        int startingEnergy = 100;
//        int maxEnergy = 100;
//        e.setMoves(0);
//        e.setFoodEaten(0);
//        e.setInventory(false);
//        t.foodAll();
//        e.setPosition(13, 13, 0);
//        e.setEnergy(startingEnergy, maxEnergy);
//        neat.loadGenome(999, 0);
//        while (e.getEnergy() > 0) {
//            values = new ArrayList<>();
//                   values = new ArrayList<>();
//                   e.coneSensor();
//                   values.add((double)(e.getEnergy() - 1) / (maxEnergy - 1));
//                   values.add((double) e.getInventory());
//                   int[] sens = e.getSensor();
//                   for (int i : sens) {
//                       values.add((double)i / 3);
//                   }
////                   values.add(1.0);
//            neat.runNetwork(values, false);
//            e.actionWin(win, t, false, neat.returnRunValues());
////            System.out.println("Energy: " + e.getEnergy());
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        System.out.println(e.getFoodEaten());
//        System.out.println("DONE");
//        
        //RUNNING METHOD
//        NEAT neat = new NEAT(inputs, outputs, 3);
//        World w = new World(26, 95, 65);
//        w.loadWorld();
//        Window win = new Window();
//        Entity e = new Entity(w);
//        win.createWindow(w, 30, e, false);
//        Tile t = new Tile(w);
//        Suggestion s = new Suggestion();
//        boolean useSuggestions = true;
//        int suggestLimit = 10;
//        double suggestMaxVal = -0.1;
//        double suggestTrimTop = 20;
//        double suggestTrimBottom = 20;
//        boolean suggestTrimKeepBottom = false;
//
//        int populationLimit = 150;
//        int generationLimit = 50;
//
//        e.setEGainOnEat(5);
//        int startingEnergy = 100;
//        int maxEnergy = 100;
//        neat.createStartingGeneration(populationLimit);
//        int a = 0;
//        while (a < generationLimit) {
////        while (t.getFarmed() < 1) {
//            System.out.println("Generation: " + a);
//            int b = 0;
//            while (b < populationLimit) {
//                e.setMoves(0);
//                e.setFoodEaten(0);
//                e.setInventory(false);
//                w.loadWorld();
//                t.foodAll();
//                e.setPosition(13, 13, 0);
//                e.setEnergy(startingEnergy, maxEnergy);
//                neat.loadGenome(a, b);
//                while (e.getEnergy() > 0) {
//                    values = new ArrayList<>();
//                    e.coneSensor();
//                    values.add((double) (e.getEnergy() - 1) / (maxEnergy - 1));
//                    values.add((double) e.getInventory());
//                    int[] sens = e.getSensor();
//                    for (int i : sens) {
//                        values.add((double) i / 3);
//                    }
////                    values.add(1.0);
//                    neat.runNetwork(values, false);
//
//                    List<Double> vals = new ArrayList<>(neat.returnRunValues());
//                    values.remove(0);
//                    if (useSuggestions && neat.getGeneration() > suggestLimit) {
//                        Map<String, Double> x = new LinkedHashMap<>(s.getSuggestions(values, suggestLimit, suggestMaxVal, neat.getGeneration()));
//                        if (!x.isEmpty()) {
//                            for (String st : x.keySet()) {
//                                switch (st) {
//                                    case "move":
//                                        vals.set(0, vals.get(0) + x.get(st));
//                                        break;
//                                    case "turnLeft":
//                                        vals.set(1, vals.get(1) + x.get(st));
//                                        break;
//                                    case "turnRight":
//                                        vals.set(2, vals.get(2) + x.get(st));
//                                        break;
//                                    case "eat":
//                                        vals.set(3, vals.get(3) + x.get(st));
//                                        break;
//                                    case "pickUp":
//                                        vals.set(4, vals.get(4) + x.get(st));
//                                        break;
//                                    case "drop":
//                                        vals.set(5, vals.get(5) + x.get(st));
//                                        break;
//                                }
//                            }
//                            e.actionNoWin(win, t, false, vals);
//                        } else {
//                            e.actionNoWin(win, t, false, vals);
//                        }
//                    } else {
//                        e.actionNoWin(win, t, false, vals);
//                    }
//                    if (useSuggestions) {
//                        s.addMove(neat.getReal(), values, e.getLastMove());
//                    }
//                }
//                neat.setFitness(b, e.getFoodEaten());
//                if (useSuggestions) {
//                    s.addFitness(neat.getReal(), e.getFoodEaten());
//                }
//                b++;
//            }
//            neat.speciate(a, populationLimit, 1, 1, 3.0, 4.0, 15);
//            neat.addToGraphSpecResults();
//            neat.printSpecies();
//            neat.mutate(populationLimit, 40, 5, 0.1, 0.03, 0.05, 80, 90, 0);
//            System.out.println("");
//            s.trimSuggestions(suggestTrimTop, suggestTrimBottom, suggestTrimKeepBottom);
////            System.out.println("Farmed: " + t.getFarmed());
////            t.setFarmed(0);
//            a++;
//        }
//        neat.recordSpecies();
//        neat.graphMaxfitness(generationLimit);
//        neat.graphSpeciesResults(populationLimit);
//        System.out.println("DONE");
//        System.exit(0);
//    
//
//
//
//
//
//
//
        //XOR Test
//        NEAT neat = new NEAT(inputs, outputs, 3);
//        int populationLimit = 150;
////        int generationLimit = 100;
//        neat.createStartingGeneration(populationLimit);
//        int a = 0;
//        boolean cont = true;
//        while (cont) {
//            System.out.println("Generation: " + a);
//            int b = 0;
//            while (b < populationLimit) {
//                neat.loadGenome(a, b);
//                values = new ArrayList<>();
//                values.add(0.0);
//                values.add(0.0);
////                values.add(1.0);
//                neat.runNetwork(values, false);
//                double output1 = Math.abs(0 - neat.returnRunValues().get(0));
//
//                neat.loadGenome(a, b);
//                values = new ArrayList<>();
//                values.add(1.0);
//                values.add(0.0);
////                values.add(1.0);
//                neat.runNetwork(values, false);
//                double output2 = Math.abs(1 - neat.returnRunValues().get(0));
//
//                neat.loadGenome(a, b);
//                values = new ArrayList<>();
//                values.add(0.0);
//                values.add(1.0);
////                values.add(1.0);
//                neat.runNetwork(values, false);
//                double output3 = Math.abs(1 - neat.returnRunValues().get(0));
//
//                neat.loadGenome(a, b);
//                values = new ArrayList<>();
//                values.add(1.0);
//                values.add(1.0);
////                values.add(1.0);
//                neat.runNetwork(values, false);
//                double output4 = Math.abs(0 - neat.returnRunValues().get(0));
//
//                double summed = output1 + output2 + output3 + output4;
//                double fitness = 0;
//                if ((4 - summed) > 0) {
//                    fitness = Math.pow(4 - summed, 2);
//                }
//
//                neat.setFitness(b, fitness);
//                if (fitness > 9) {
//                    cont = false;
//                }
//                b++;
//            }
//            neat.speciate(a, populationLimit, 1, 1, 0.4, 3.0, 15);
//            neat.addToGraphSpecResults();
//            neat.printSpecies();
//            neat.mutate(populationLimit, 40, 5, 0.1, 1, 10, 80, 90, 0);
//            System.out.println("");
//            a++;
//        }
//        neat.recordSpecies();
//        neat.graphMaxfitness(a);
//        neat.graphSpeciesResults(populationLimit);
//        System.out.println("DONE");
//        System.exit(0);
//    
        //XOR Testing
//        NEAT neat = new NEAT(inputs, outputs, 3);
//        neat.loadGenome(122, 137);
//        values = new ArrayList<>();
//        values.add(0.0);
//        values.add(0.0);
////        values.add(1.0);
//        neat.runNetwork(values, false);
//        System.out.println(neat.returnRunValues());
//        System.out.println("");
//        values = new ArrayList<>();
//        values.add(1.0);
//        values.add(1.0);
////        values.add(1.0);
//        neat.runNetwork(values, false);
//        System.out.println(neat.returnRunValues());
//        System.out.println("");
//        values = new ArrayList<>();
//        values.add(1.0);
//        values.add(0.0);
////        values.add(1.0);
//        neat.runNetwork(values, false);
//        System.out.println(neat.returnRunValues());
//        System.out.println("");
//        values = new ArrayList<>();
//        values.add(0.0);
//        values.add(1.0);
////        values.add(1.0);
//        neat.runNetwork(values, false);
//        System.out.println(neat.returnRunValues());
//        System.out.println("");
//        System.out.println("DONE");
    }
}
