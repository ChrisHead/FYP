package FYP.project;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class NEAT {

    private Map<Integer, List<String>> genome;
    private Network net;
    private Entity e;

    //Create Entity/World/Tile (Done)
    //Create input/ouputs (Done)
    //NoHiddenConnect inputs/outputs (Done)
    //Generate initial genome (Done)
    //Generate other genomes
    //Run each genome, record genome/score
    //Organise genomes, take surviving sample
    //Run evolve on surviving sample
    public NEAT() {
    }

    public void startingGenome(Entity ent) {
        e = ent;
        List<Layer> h = new ArrayList<>();
        Network n = new Network(e.createInputs(), h, e.createOutputs());
        net = n;
        n.noHiddenConnect();
        Innovation i = new Innovation();
        genome = new LinkedHashMap<>();
        List<Axon> axons = n.returnAxons();
        for (Axon a : axons) {
            List<String> values = new ArrayList<>();
            values.add(a.getInput().getName());
            values.add(a.getOutput().getName());
            values.add(String.valueOf(a.getWeight()));
            values.add("ENABLED");
            genome.put(i.getInv(), values);
            i.incInv();
        }
        i.save();
    }

    public void printGenome() {
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            System.out.println(m.getKey() + ", " + m.getValue());
        }
    }

//    public void runGenome() {
//        //Initial position?
//        World w = new World(50);
//        Tile t = new Tile(w);
//        int x = 0;
//        while (e.getEnergy() > 0) {
//            e.updateInputValues();
//            e.getOutput().LayerZeroInputs();
//            net.runNetwork(false, false);
//            e.action(t, false);
////            try {
////                Thread.sleep(100);
////            } catch (InterruptedException ex) {
////                Thread.currentThread().interrupt();
////            }
//            x++;
//        }
//        System.out.println(x);
//        System.out.println("DONE");
//    }
    public void genomeManipulation() {
        //Genome manipulation stuff
    }

    public void buildNetworkFromGenome() {
        //Clear axon and neuron input lists
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            if (!input.contains(m.getValue().get(0))) {
                input.add(m.getValue().get(0));
            }
            if (!output.contains(m.getValue().get(1))
                    && !input.contains(m.getValue().get(1))) {
                output.add(m.getValue().get(1));
            }
        }
        //Activate after clearing axons/inputs
//        for (String s : input) {
//            Neuron neuron = new Neuron(s);
//        }
//        for (String s : output) {
//            Neuron neuron = new Neuron(s);
//        }
    }

    public void saveGenome() {
        Writer w;
        try {
            w = new FileWriter("Genome.txt");
            for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
                w.write(m.getKey() + "," + m.getValue().get(0) + 
                        "," + m.getValue().get(1) + "," + m.getValue().get(2) + 
                        "," + m.getValue().get(3) + ",");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void loadGenome() {
        genome = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader("Genome.txt"))) {
            s.delimiter();
            s.useDelimiter(",");
             List<String> values;
            while (s.hasNext()) {
                values = new ArrayList<>();
                String key = s.next();
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                genome.put(Integer.parseInt(key), values);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e);
        }
    }
}
