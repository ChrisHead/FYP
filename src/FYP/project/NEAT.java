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
    private Map<Integer, List<String>> mutations;
    private Map<Neuron, Double> previousValues;
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
        mutations = new LinkedHashMap<>();
        previousValues = new LinkedHashMap<>();
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
        for (Neuron n : allNeurons) {
            previousValues.put(n, n.getValue());
        }

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
    }

    //Network Running
    public void runNetwork(List<Double> v, boolean b) {
        for (int i = 0; i < v.size(); i++) {
            inputNeurons.get(i).setValue(v.get(i));
        }
        for (Neuron h : hiddenNeurons) {
            h.setValue(0.0);
        }
        for (Neuron o : outputNeurons) {
            o.setValue(0.0);
        }
        for (Neuron a : allNeurons) {
            a.setRecurrentValueAvailable(true);
        }
        Stack<Neuron> toCalculate = new Stack<>();
        for (Neuron n : allNeurons) {
            n.setIsCalculated(false);
        }
        for (Neuron o : outputNeurons) {
            for (Axon a : o.getInputs()) {
                if (!a.getInput().isCalculated() && !toCalculate.contains(a.getInput())) {
                    toCalculate.push(a.getInput());
                }
            }
        }
        while (!toCalculate.isEmpty()) {
            if (toCalculate.peek().getInputs().isEmpty()) {
                toCalculate.peek().calculateValue();
                toCalculate.pop();
            } else if (toCalculate.peek().isCalculated()) {
                toCalculate.pop();
            } else {
                int temp = 0;
                Neuron tempNeuron = toCalculate.peek();
                for (Axon a : toCalculate.peek().getInputs()) {
                    if (!a.getInput().isCalculated() && a.getInput().getRecurrentValueAvailable()) {
                        int r = toCalculate.search(a.getInput());
                        if (r == -1 && !outputNeurons.contains(a.getInput())) {
                            toCalculate.push(a.getInput());
                        } else {
                            tempNeuron.setValue(previousValues.get(a.getInput()) * a.getWeight());
                            a.getInput().setRecurrentValueAvailable(false);
                        }
                    } else {
                        temp++;
                    }
                }
                int noOfCalInputs = toCalculate.peek().getInputs().size();
                if (noOfCalInputs == temp) {
                    toCalculate.peek().calculateValue();
                    toCalculate.pop();
                }
            }
        }
        for (Neuron o : outputNeurons) {
            o.calculateValue();
        }
        for (Neuron n : allNeurons) {
            previousValues.put(n, n.getValue());
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

    public void printGenerationresults() {
        for (Map.Entry<Integer, Integer> r : results.entrySet()) {
            System.out.println(r.getKey() + ", " + r.getValue());
        }
    }

    public void printMutations() {
        for (Map.Entry<Integer, List<String>> m : mutations.entrySet()) {
            System.out.println(m.getKey() + ", " + m.getValue());
        }
    }

    public void printPreviousValues() {
        for (Map.Entry<Neuron, Double> p : previousValues.entrySet()) {
            System.out.println(p.getKey() + ", " + p.getValue());
        }
    }

    public List<Neuron> getInputNeurons() {
        return inputNeurons;
    }

    public void printInputNeurons() {
        System.out.println("Size: " + inputNeurons.size());
        for (Neuron n : inputNeurons) {
            System.out.println(n.getName());
        }
    }

    public List<Neuron> getAllNeurons() {
        return allNeurons;
    }

    public void printAllNeurons() {
        System.out.println("Size: " + allNeurons.size());
        for (Neuron n : allNeurons) {
            System.out.println(n.getName());
        }
    }

    public List<Neuron> getHiddenNeurons() {
        return hiddenNeurons;
    }

    public void printHiddenNeurons() {
        System.out.println("Size: " + hiddenNeurons.size());
        for (Neuron n : hiddenNeurons) {
            System.out.println(n.getName());
        }
    }

    public List<Neuron> getOutputNeurons() {
        return outputNeurons;
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

    public void printStartingValues() {
        for (Neuron n : inputNeurons) {
            System.out.println(n.getValue());
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
                File folder = new File(System.getProperty("user.dir")
                        + "/results/Generation_" + g);
                if (folder.mkdir()) {
                    System.out.println("Directory Created");
                } else {
                    System.out.println("Directory Not Created");
                }
            }
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/Generation_" + g + "/Organism_" + o + ".txt");
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
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/Generation_" + g + "/Organism_" + o + ".txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            List<String> values;
            while (s.hasNext()) {
                values = new ArrayList<>();
                String key = s.next();
                key = key.replaceAll("\\r\\n", "");
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

    public void saveGenerationResults(int g) {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/Generation_" + g + "/results.txt");
            for (Map.Entry<Integer, Integer> r : results.entrySet()) {
                w.write("\r\n" + r.getKey() + "," + r.getValue());
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void loadGenerationResults(int g) {
        results = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/Generation_" + g + "/results.txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            int result;
            while (s.hasNext()) {
                String key = s.next();
                key = key.replaceAll("\\r\\n", "");
                result = Integer.parseInt(s.next());
                results.put(Integer.parseInt(key), result);
            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex);
        }
    }

    public void saveMutations(int g) {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/Generation_" + g + "/mutations.txt");
            for (Map.Entry<Integer, List<String>> m : mutations.entrySet()) {
                w.write("\r\n" + m.getKey() + "," + m.getValue());
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void loadMutations(int g) {
        mutations = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/Generation_" + g + "/mutations.txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            List<String> values;
            while (s.hasNext()) {
                values = new ArrayList<>();
                String key = s.next();
                key = key.replaceAll("\\r\\n", "");
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                mutations.put(Integer.parseInt(key), values);
            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex);
        }
    }

    //Mutation Functions
    public void mutate() {
        //80% chance for changeWeights
    }

    public Map<Integer, List<String>> crossover(int generation, int organsim1, int organsim2, int enableChance) {
        this.loadGenome(generation, organsim1);
        Map<Integer, List<String>> genome1 = new LinkedHashMap<>(genome);
        this.loadGenome(generation, organsim2);
        Map<Integer, List<String>> genome2 = new LinkedHashMap<>(genome);
        this.loadGenerationResults(generation);

        int genome1Result = results.get(organsim1);
        int genome2Result = results.get(organsim2);

        String fittest;
        if (genome1Result == genome2Result) {
            fittest = "equal";
        } else if (genome1Result > genome2Result) {
            fittest = "genome1";
        } else {
            fittest = "genome2";
        }

        List<Integer> matching = new ArrayList<>();
        List<Integer> disjoint1 = new ArrayList<>();
        List<Integer> disjoint2 = new ArrayList<>();
        List<Integer> excess = new ArrayList<>();

        List<Integer> g1KeySet = new ArrayList<>(genome1.keySet());
        List<Integer> g2KeySet = new ArrayList<>(genome2.keySet());

        //matching
        for (int i : genome1.keySet()) {
            if (genome2.keySet().contains(i)) {
                matching.add(i);
            }
        }
        //excess
        if (genome1.keySet().size() > genome2.keySet().size()) {
            for (int i : g1KeySet) {
                if (i > g2KeySet.get(g2KeySet.size() - 1)) {
                    excess.add(i);
                }
            }
        } else {
            for (int i : g2KeySet) {
                if (i > g1KeySet.get(g1KeySet.size() - 1)) {
                    excess.add(i);
                }
            }
        }
        //disjoint
        for (int i : g1KeySet) {
            if (!matching.contains(i) && !excess.contains(i)) {
                disjoint1.add(i);
            }
        }
        for (int i : g2KeySet) {
            if (!matching.contains(i) && !excess.contains(i)) {
                disjoint2.add(i);
            }
        }

//        System.out.println(matching);
//        System.out.println(excess);
//        System.out.println("D1: " + disjoint1);
//        System.out.println("D2: " + disjoint2);
        //create new genome
        //matching
        Map<Integer, List<String>> newGenome = new LinkedHashMap<>();
        for (int i : matching) {
            int rand = ThreadLocalRandom.current().nextInt(0, 100);
            if (rand > 50) {
                newGenome.put(i, genome1.get(i));
            } else {
                newGenome.put(i, genome2.get(i));
            }
            if (!(genome1.get(i).get(3).equals("ENABLED")
                    && genome2.get(i).get(3).equals("ENABLED"))) {
                int eChance = ThreadLocalRandom.current().nextInt(0, 100);
                List<String> temp = new ArrayList<>(newGenome.get(i));
                if (eChance > enableChance) {
                    temp.set(3, "ENABLED");
                    newGenome.put(i, temp);
                } else {
                    temp.set(3, "DISABLED");
                    newGenome.put(i, temp);
                }
            }
        }
        //disjoint and excess
        if (fittest.equals("equal")) {
            for (int i : disjoint1) {
                int rand = ThreadLocalRandom.current().nextInt(0, 100);
                if (rand > 50) {
                    newGenome.put(i, genome1.get(i));
                }
            }
            for (int i : disjoint2) {
                int rand = ThreadLocalRandom.current().nextInt(0, 100);
                if (rand > 50) {
                    newGenome.put(i, genome2.get(i));
                }
            }
            for (int i : excess) {
                int rand = ThreadLocalRandom.current().nextInt(0, 100);
                if (rand > 50 && (genome1.keySet().size() > genome2.keySet().size())) {
                    newGenome.put(i, genome1.get(i));
                } else if (rand > 50) {
                    newGenome.put(i, genome2.get(i));
                }
            }
        } else if (fittest.equals("genome1")) {
            for (int i : disjoint1) {
                newGenome.put(i, genome1.get(i));
            }
            if (genome1.keySet().size() > genome2.keySet().size()) {
                for (int i : excess) {
                    newGenome.put(i, genome1.get(i));
                }
            }
        } else {
            for (int i : disjoint2) {
                newGenome.put(i, genome2.get(i));
            }
            if (genome2.keySet().size() > genome1.keySet().size()) {
                for (int i : excess) {
                    newGenome.put(i, genome2.get(i));
                }
            }
        }
        genome = new LinkedHashMap<>(newGenome);
        return newGenome;
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

        List<String> newMut = new ArrayList<>();
        newMut.add("addNeuron");
        newMut.add(allNeurons.get(indexIn).getName());
        newMut.add(n.getName());
        if (mutations.containsValue(newMut)) {
            for (Integer m : mutations.keySet()) {
                if (mutations.get(m).equals(newMut)) {
                    genome.put(m, inNew);
                }
            }
        } else {
            int tempInnov = innov.getInv();
            genome.put(tempInnov, inNew);
            mutations.put(tempInnov, newMut);
            innov.incInv();
        }

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

        newMut = new ArrayList<>();
        newMut.add("addNeuron");
        newMut.add(n.getName());
        newMut.add(allNeurons.get(indexOut).getName());
        System.out.println(newMut);
        if (mutations.containsValue(newMut)) {
            for (Integer m : mutations.keySet()) {
                if (mutations.get(m).equals(newMut)) {
                    genome.put(m, newOut);
                }
            }
        } else {
            int tempInnov = innov.getInv();
            genome.put(tempInnov, newOut);
            mutations.put(tempInnov, newMut);
            innov.incInv();
        }

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
        List<Neuron> existingOutputs;
        List<Neuron> available = new ArrayList<>();
        List<Neuron> hidOut = new ArrayList<>();
        hidOut.addAll(hiddenNeurons);
        hidOut.addAll(outputNeurons);
        for (Neuron all : allNeurons) {
            existingOutputs = new ArrayList<>();
            existingOutputs.add(all);
            for (Axon a : axons) {
                if (a.getInput() == all) {
                    existingOutputs.add(a.getOutput());
                }
            }
            if (!existingOutputs.containsAll(hidOut)) {
                available.add(all);
            }
        }
        if (available.isEmpty()) {
            System.out.println("No Available Connections");
        } else {
            int availRand = ThreadLocalRandom.current().nextInt(0, available.size());
            List<Neuron> availableOutputs = new ArrayList<>();
            List<Neuron> outTemp = new ArrayList<>();
            for (Axon a : axons) {
                if (a.getInput() == available.get(availRand)) {
                    outTemp.add(a.getInput());
                    outTemp.add(a.getOutput());
                }
            }
            for (Neuron o : hidOut) {
                if (!outTemp.contains(o)) {
                    availableOutputs.add(o);
                }
            }
            int outRand = ThreadLocalRandom.current().nextInt(0, availableOutputs.size());
            double weight = (Math.random() * 2 - 1);
            Axon a = new Axon(available.get(availRand), availableOutputs.get(outRand), weight);
            axons.add(a);
            List<String> temp = new ArrayList<>();
            temp.add(available.get(availRand).getName());
            temp.add(availableOutputs.get(outRand).getName());
            temp.add(String.valueOf(weight));
            temp.add("ENABLED");

            List<String> newMut = new ArrayList<>();
            newMut.add("addAxon");
            newMut.add(available.get(availRand).getName());
            newMut.add(availableOutputs.get(outRand).getName());
            if (mutations.containsValue(newMut)) {
                for (Integer m : mutations.keySet()) {
                    if (mutations.get(m).equals(newMut)) {
                        genome.put(m, temp);
                    }
                }
            } else {
                int tempInnov = innov.getInv();
                genome.put(tempInnov, temp);
                mutations.put(tempInnov, newMut);
                innov.incInv();
            }
        }
    }

    public void changeWeights(int r) {
        double value = ThreadLocalRandom.current().nextDouble(-1, 1);
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            int uniform = ThreadLocalRandom.current().nextInt(0, 100);
            if (uniform > r) {
                m.getValue().set(2, Double.toString(Double.parseDouble(m.getValue().get(2)) + value));
            } else {
                m.getValue().set(2, Double.toString(Math.random() * 2 - 1));
            }
        }
    }
}
