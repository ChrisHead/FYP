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
 * @author Chris
 */
public class NEAT {

    private Map<Integer, List<String>> genome;
    private Network net;
    private Entity e;
    private final int generation;
    private final int organism;
    private final int numOfHiddenLayers;
    private final List<String> idsOfInputs;
    private final List<String> idsOfOutputs;
    private final List<Neuron> allNeurons;
    private final List<Neuron> inputNeurons;
    private final List<Neuron> outputNeurons;
    private final List<Neuron> hiddenNeurons;

    public NEAT(List<String> i, List<String> o) {
        generation = 0;
        organism = 0;
        numOfHiddenLayers = 0;
        idsOfInputs = i;
        idsOfOutputs = o;
        allNeurons = new ArrayList<>();
        inputNeurons = new ArrayList<>();
        outputNeurons = new ArrayList<>();
        hiddenNeurons = new ArrayList<>();
    }

    public void createNeurons() {
        List<String> neurons = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            if (!neurons.contains(m.getValue().get(0))) {
                neurons.add(m.getValue().get(0));
            }
            if (!neurons.contains(m.getValue().get(1))) {
                neurons.add(m.getValue().get(1));
            }
        }
//        System.out.println(neurons);
        for (String s : neurons) {
            Neuron n = new Neuron(s);
            allNeurons.add(n);
            if (idsOfInputs.contains(s)) {
                inputNeurons.add(n);
            } else if (idsOfOutputs.contains(s)) {
                outputNeurons.add(n);
            } else {
                hiddenNeurons.add(n);
            }
        }
//        for (Neuron n : allNeurons) {
//            System.out.print(n.getName() + " ");
//        }
//        System.out.println();
//        for (Neuron n : inputNeurons) {
//            System.out.print(n.getName() + " ");
//        }
//        System.out.println();
//        for (Neuron n : outputNeurons) {
//            System.out.print(n.getName() + " ");
//        }
//        System.out.println();
//        for (Neuron n : hiddenNeurons) {
//            System.out.print(n.getName() + " ");
//        }
//        System.out.println();
        Layer inputLayer = new Layer(inputNeurons);
        Layer outputLayer = new Layer(outputNeurons);
    }

    //Create Hidden Layers
    //Modify for new code
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
    }

    public void printGenome() {
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            System.out.println(m.getKey() + ", " + m.getValue());
        }
    }

    public void saveGenome() {
        Writer w;
        try {
//            File dir = new File("Generation: " + generation);
//            dir.mkdir();
            w = new FileWriter("Generation_" + generation + "_Organsim_" + organism + ".txt");
            for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
                w.write("\r\n" + m.getKey() + "," + m.getValue().get(0)
                        + "," + m.getValue().get(1) + "," + m.getValue().get(2)
                        + "," + m.getValue().get(3) + ",");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void loadGenome(int g, int o) {
        genome = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader("Generation_" + generation + "_Organsim_" + organism + ".txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            List<String> values;
            while (s.hasNext()) {
                values = new ArrayList<>();
                String key = s.next();
                key = key.replaceAll("\\r\\n", "");
//                System.out.println(key);
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                genome.put(Integer.parseInt(key), values);
            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex);
        }
    }
}

//public void buildNetworkFromGenome() {
//        //Clear axon and neuron input (class)lists
//        List<String> input = new ArrayList<>();
//        List<String> output = new ArrayList<>();
//        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
//            if (!input.contains(m.getValue().get(0))) {
//                input.add(m.getValue().get(0));
//            }
//            //Loop for hidden layers
//            if (!output.contains(m.getValue().get(1))
//                    && !input.contains(m.getValue().get(1))) {
//                output.add(m.getValue().get(1));
//            }
//        }
//  
