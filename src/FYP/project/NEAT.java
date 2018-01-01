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
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Chris
 */
public class NEAT {

    private Map<Integer, List<String>> genome;
    private Map<Integer, Integer> results;
    private Network net;
    private Entity e;
    private int generation;
    private int organism;
    private final List<String> idsOfInputs;
    private final List<String> idsOfOutputs;
    private final List<Neuron> allNeurons;
    private final List<Neuron> inputNeurons;
    private final List<Neuron> outputNeurons;
    private final List<Neuron> hiddenNeurons;
    private final List<Axon> axons;
    private Innovation innov;

    public NEAT(List<String> i, List<String> o) {
        generation = 0;
        organism = 0;
        idsOfInputs = i;
        idsOfOutputs = o;
        allNeurons = new ArrayList<>();
        inputNeurons = new ArrayList<>();
        outputNeurons = new ArrayList<>();
        hiddenNeurons = new ArrayList<>();
        axons = new ArrayList<>();
        results = new LinkedHashMap<>();
        innov = new Innovation();
    }

    //Network Creation
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
//        System.out.println(axons.size());
//        for (Neuron n : hiddenNeurons) {
//            System.out.println("Name: " + n.getName());
//            n.getInputs();
//        }
    }

    //Network Running
    public void runNetwork(List<Double> v, boolean b) {
        for (int i = 0; i < v.size(); i++) {
            inputNeurons.get(i).setValue(v.get(i));
        }
        Stack<Neuron> toCalculate = new Stack<>();
        for (Neuron n : allNeurons) {
            n.setIsCalculated(false);
        }
        for (Neuron o : outputNeurons) {
            for (Axon a : o.returnInputs()) {
                if (!a.getInput().isCalculated() && !toCalculate.contains(a.getInput())) {
                    toCalculate.push(a.getInput());
                }
            }
        }
        while (!toCalculate.isEmpty()) {
            if (toCalculate.peek().returnInputs().isEmpty()) {
                toCalculate.peek().getSigValue();
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
                if (noOfCalInputs == temp) {
                    toCalculate.peek().getSigValue();
                    toCalculate.pop();
                }
            }
        }
        for (Neuron o : outputNeurons) {
            o.getSigValue();
        }
        if (b) {
            this.returnRunValues();
        }
    }

    public void returnRunValues() {
        for (Neuron o : outputNeurons) {
            o.printValue();
        }
    }

    //Needs to be finished
    public void getFitness() {
        //Get organism fitness result
        //Can use while loop counter
        int fitness = 0;
        results.put(organism, fitness);
    }

    //Modify for new code
    public void startingGenome(Entity ent) {
//        e = ent;
//        List<Layer> h = new ArrayList<>();
//        Network n = new Network(e.createInputs(), h, e.createOutputs());
//        net = n;
//        n.noHiddenConnect();
//        Innovation i = new Innovation();
//        genome = new LinkedHashMap<>();
//        List<Axon> axons = n.returnAxons();
//        for (Axon a : axons) {
//            List<String> values = new ArrayList<>();
//            values.add(a.getInput().getName());
//            values.add(a.getOutput().getName());
//            values.add(String.valueOf(a.getWeight()));
//            values.add("ENABLED");
//            genome.put(i.getInv(), values);
//            i.incInv();
//        }
    }

    //Utility
    public void printGenome() {
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            System.out.println(m.getKey() + ", " + m.getValue());
        }
    }

    public void printInputNeurons() {
        System.out.println("Size: " + inputNeurons.size());
        for (Neuron n : inputNeurons) {
            System.out.println(n.getName());
        }
    }

    public void printHiddenNeurons() {
        System.out.println("Size: " + hiddenNeurons.size());
        for (Neuron n : hiddenNeurons) {
            System.out.println(n.getName());
        }
    }

    public void printOutputNeurons() {
        System.out.println("Size: " + outputNeurons.size());
        for (Neuron n : outputNeurons) {
            System.out.println(n.getName());
        }
    }

    public void printAxons() {
        System.out.println(axons);
        for (Axon a : axons) {
            System.out.println("In: " + a.getInput().getName()
                    + " Out: " + a.getOutput().getName()
                    + " Weight: " + a.getWeight());
        }
    }

    private int getNextHiddenName() {
        String lastName = hiddenNeurons.get(hiddenNeurons.size() - 1).getName();
        int hiddenNum = Integer.parseInt(lastName.substring(1));
        hiddenNum++;
        return hiddenNum;
    }

    public int getRandomGenomeEntry() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, genome.size());
        List<Integer> keys = new ArrayList<>(genome.keySet());
        int i = keys.get(randomNum);
        return i;
    }

    //Save/loading
    public void saveGenome(int g, int o, boolean b) {
        Writer w;
        try {
            if (b) {
                File folder = new File(System.getProperty("user.dir") + "/results/Generation_" + g);
                if (folder.mkdir()) {
                    System.out.println("Directory Created");
                } else {
                    System.out.println("Directory Not Created");
                }
            }
            w = new FileWriter(System.getProperty("user.dir") + "/results/Generation_" + g + "/Organism_" + o + ".txt");
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
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir") + "/results/Generation_" + g + "/Organism_" + o + ".txt"))) {
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
        List<Integer> keySet = new ArrayList<>(genome.keySet());
    }

    public void saveGenerationResults(int g) {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir") + "/results/Generation_" + g + "/results.txt");
            for (Map.Entry<Integer, Integer> r : results.entrySet()) {
                w.write("\r\n" + r.getKey() + "," + r.getValue());
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    //Mutation Functions
    public void mutate() {

    }

    public void crossover() {

    }

    public void addNeuron() {
        //get random enabled genome entry
        int genomeKey = getRandomGenomeEntry();
        List<String> original = new ArrayList<>(genome.get(genomeKey));
        while (original.get(3).equals("DISABLED")) {
            genomeKey = getRandomGenomeEntry();
            original = new ArrayList<>(genome.get(genomeKey));
        }

        //create new neuron
        Neuron n = new Neuron("h" + getNextHiddenName());
        hiddenNeurons.add(n);

        //add new axon between old input and new, add to genome
        int indexIn = 0;
        for (Neuron in : allNeurons) {
            if (in.getName().equals(original.get(0))) {
                indexIn = allNeurons.indexOf(in);
            }
        }
        Axon a = new Axon(allNeurons.get(indexIn), n, 1.0);
        axons.add(a);
        List<String> inNew = new ArrayList<>();
        inNew.add(allNeurons.get(indexIn).getName());
        inNew.add(n.getName());
        inNew.add("1.0");
        inNew.add("ENABLED");
        genome.put(innov.getInv(), inNew);
        innov.incInv();

        //add original axon between new and old output, add to genome
        int indexOut = 0;
        for (Neuron out : allNeurons) {
            if (out.getName().equals(original.get(1))) {
                indexOut = allNeurons.indexOf(out);
            }
        }
        Axon b = new Axon(n, allNeurons.get(indexOut), Double.parseDouble(original.get(2)));
        axons.add(b);
        List<String> newOut = new ArrayList<>();
        newOut.add(n.getName());
        newOut.add(allNeurons.get(indexOut).getName());
        newOut.add(original.get(2));
        newOut.add("ENABLED");
        genome.put(innov.getInv(), newOut);
        innov.incInv();

        //remove original axon from axons
        int oldAxon = 0;
        for (Axon c : axons) {
            if (c.getInput() == allNeurons.get(indexIn) && c.getOutput() == allNeurons.get(indexOut)) {
                oldAxon = axons.indexOf(c);
            }
        }
        axons.remove(oldAxon);

        //disable original axon
        List<String> disabled = new ArrayList<>(original);
        disabled.add(3, "DISABLED");
        genome.put(genomeKey, disabled);

    }

    public void addAxon() {
        //get random input/hidden neuron
        List<Neuron> inHid = new ArrayList<>();
        inHid.addAll(inputNeurons);
        inHid.addAll(hiddenNeurons);
        int inHidRand = ThreadLocalRandom.current().nextInt(0, inHid.size());
        String newIn = inHid.get(inHidRand).getName();

        //get random hidden/output neuron
        List<Neuron> hidOut = new ArrayList<>();
        hidOut.addAll(hiddenNeurons);
        hidOut.addAll(outputNeurons);
        int hidOutRand = ThreadLocalRandom.current().nextInt(0, hidOut.size());
        String newOut = hidOut.get(hidOutRand).getName();

        List<String> hidOutNames = new ArrayList<>();

        //checks whether new input neuron is already connected to all
        //hidden and output neurons, changes input if this is the case
        boolean cont = false;
        while (!cont) {
            List<String> tempNames = new ArrayList<>();
            //get list of outputs in existing axons for given new input
            for (Axon a : axons) {
                if (a.getInput().getName().equals(newIn)) {
                    tempNames.add(a.getOutput().getName());
                }
            }
            //adds all hidden and output neuron names to a list
            List<String> names = new ArrayList<>();
            for (Neuron n : hidOut) {
                names.add(n.getName());
            }
            if (tempNames.containsAll(names)) {
                inHidRand = ThreadLocalRandom.current().nextInt(0, inHid.size());
                newIn = inHid.get(inHidRand).getName();
            } else {
                hidOutNames.addAll(tempNames);
                System.out.println(hidOutNames);
                cont = true;
            }

        }

        //changes new output if new input already has a connect to it
        while (!hidOutNames.contains(newOut)) {
            hidOutRand = ThreadLocalRandom.current().nextInt(0, hidOut.size());
            newOut = hidOut.get(hidOutRand).getName();
        }
        
        //create new axon, add to axons and genome
        double weight = (Math.random()*2-1);
        Axon a = new Axon(inHid.get(inHidRand), hidOut.get(hidOutRand), weight);
        axons.add(a);
        List<String> temp = new ArrayList<>();
        temp.add(newIn);
        temp.add(newOut);
        temp.add(String.valueOf(weight));
        temp.add("ENABLED");
        genome.put(innov.getInv(), temp);
        innov.incInv();
    }

    public void removeAxon() {

    }

    public void changeWeight() {

    }
}
