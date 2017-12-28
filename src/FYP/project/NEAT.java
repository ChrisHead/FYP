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
import java.util.Stack;

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
    private final List<Axon> axons;

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
        axons = new ArrayList<>();
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
    }

    // Not needed anymore
    public void createLayers() {
//        Layer inputLayer = new Layer(inputNeurons);
//        Layer outputLayer = new Layer(outputNeurons);
//
//        List<Neuron> temp = new ArrayList<>();
//        List<String> hiddenIds = new ArrayList<>();
//
//        int layerInc = 0;
//
//        for (Neuron n : hiddenNeurons) {
//            hiddenIds.add(n.getName());
//        }
//
//        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
//            for (Neuron n : hiddenNeurons) {
//                if (idsOfInputs.contains(m.getValue().get(0))
//                        && hiddenIds.contains(m.getValue().get(1))
//                        && n.getName().equals(m.getValue().get(1))) {
//                    temp.add(n);
//                    hiddenIds.remove(m.getValue().get(1));
//                }
//            }
//        }
//        Layer hiddenLayer = new Layer(Integer.toString(layerInc), temp);
//        layerInc++;
//
////        for (Neuron n : temp) {
////            System.out.println(n.getName() + " ");
////        }
////        System.out.println(hiddenIds);
    }

    public void createAxons() {
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            if (m.getValue().get(3).equals("ENABLED")) {

                Neuron input = new Neuron();
                Neuron output = new Neuron();

                for (Neuron n : allNeurons) {
                    if (n.getName().equals(m.getValue().get(0))) {
                        input = n;
                    }
                }
                for (Neuron n : allNeurons) {
                    if (n.getName().equals(m.getValue().get(1))) {
                        output = n;
                    }
                }
                Axon a = new Axon(input, output, Double.parseDouble(m.getValue().get(2)));
                axons.add(a);

            }
        }
//        System.out.println(idsOfOutputs);
//        System.out.println(hiddenNeurons.get(0).getName());
//        hiddenNeurons.get(0).getInputs();
//        System.out.println(genome.get(157));
//        System.out.println(axons.size());
//        System.out.println(axons.get(157).getInput().getName());
//        System.out.println(allNeurons.size());
//        for (Neuron n : allNeurons) {
//            System.out.println(n.getName());
//        }
//        System.out.println(axons);
//        for (Axon a : axons) {
//            System.out.println(a.getInput().getName());
//        }
//        System.out.println(axons.size());
//        for (Neuron n : hiddenNeurons) {
//            System.out.println("Name: " + n.getName());
//            n.getInputs();
//        }
//        outputNeurons.get(5).getInputs();
//          System.out.println(inputNeurons.get(0).returnInputs().isEmpty());

//          System.out.println(hiddenNeurons.get(4).getName());
//          hiddenNeurons.get(4).getInputs();
    }

    public void runNetwork() {
        Stack<Neuron> toCalculate = new Stack<>();

        inputNeurons.get(0).setValue(5.0);
        inputNeurons.get(1).setValue(10.0);
        
        inputNeurons.get(0).printValue();
        inputNeurons.get(1).printValue();
        System.out.println();

        for (Neuron n : allNeurons) {
            n.setIsCalculated(false);
        }
        
        inputNeurons.get(0).setIsCalculated(true);
        inputNeurons.get(1).setIsCalculated(true);

        for (Neuron o : outputNeurons) {
            for (Axon a : o.returnInputs()) {
                if (!a.getInput().isCalculated() && !toCalculate.contains(a.getInput())) {
                    toCalculate.push(a.getInput());
                }
            }
        }

        while (!toCalculate.isEmpty()) {

//            System.out.println("Top: " + toCalculate.peek().getName());
            if (toCalculate.peek().returnInputs().isEmpty()) {

                //Calculate value
                toCalculate.peek().getValue();

                toCalculate.pop();
            } else if (toCalculate.peek().isCalculated()) {
                toCalculate.pop();
            } else {
                int temp = 0;
                for (Axon a : toCalculate.peek().returnInputs()) {
                    if (!a.getInput().isCalculated()) {
                        toCalculate.add(a.getInput());
                    } else {
                        temp++;
                    }
                }
                int noOfCalInputs = toCalculate.peek().returnInputs().size();
//                System.out.println("Size: " + noOfCalInputs);
                if (noOfCalInputs == temp) {

                    //Calculate value
                    toCalculate.peek().getValue();

                    toCalculate.pop();
                }
            }

//            for (Neuron n : toCalculate) {
//                System.out.println("C: " + n.getName());
//            }
//            System.out.println();
        }

        outputNeurons.get(0).printValue();
        outputNeurons.get(1).printValue();
        outputNeurons.get(2).printValue();
        
        for (Axon a : outputNeurons.get(0).returnInputs()) {
            a.getInput().printValue();
        }
    }

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
