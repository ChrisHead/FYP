package FYP.project;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Chris
 */
public class NEAT {

    private Map<Integer, List<String>> genome;
    private Map<Integer, Double> results;
    private Map<Integer, Double> adjustResults;
    private Map<Integer, Double> adjustSize;
    private Map<Integer, List<String>> mutations;
    private Map<Neuron, Double> previousValues;
    private int generation;
    private int organism;
    private final List<String> idsOfInputs;
    private final List<String> idsOfOutputs;
    private List<Neuron> allNeurons;
    private List<Neuron> inputNeurons;
    private List<Neuron> outputNeurons;
    private List<Neuron> hiddenNeurons;
    private List<Axon> axons;
    private Innovation innov;
    private List<Species> species;
    private int genomeNum;
    private int real_ID;
    private int speciesNum;
    private List<List<List<String>>> specResults;
    private List<Integer> noOfSpecs;
    private Map<String, Integer> activations;

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
        adjustResults = new LinkedHashMap<>();
        adjustSize = new LinkedHashMap<>();
        mutations = new LinkedHashMap<>();
        previousValues = new LinkedHashMap<>();
        innov = new Innovation();
        innov.save();
        species = new ArrayList<>();
        genomeNum = 0;
        real_ID = 0;
        speciesNum = 0;
        noOfSpecs = new ArrayList<>();
        specResults = new ArrayList<>();
        activations = new LinkedHashMap<>();
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
            if (activations.containsKey(n.getName())) {
                n.setActType(activations.get(n.getName()));
            } else {
                activations.put(n.getName(), n.getActType());
            }
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
            this.printRunValues();
        }
    }

    public List<Double> returnRunValues() {
        List<Double> tempResults = new ArrayList<>();
        for (Neuron o : outputNeurons) {
            tempResults.add(o.getValue());
        }
        return tempResults;
    }

    public void printRunValues() {
        for (Neuron o : outputNeurons) {
            o.printValue();
        }
    }

    public void setFitness(int o, double f) {
        results.put(o, f);
//        System.out.println("O:" + o + ", F: " + f);
//        this.saveGenerationResults(generation);
    }

    public Double getAdjustFitness(int o, Species s) {
        double val = results.get(o);
        return (val / s.getSpecies().size());
    }

    public double getFitness(int o) {
        double fitness = results.get(o);
        return fitness;
    }

    public void orderResults() {
//        this.loadGenerationResults(generation);
        Map<Integer, Double> orderedResults = results.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldResult, newResult) -> oldResult, LinkedHashMap::new));
        results = new LinkedHashMap<>(orderedResults);
        this.saveOrderedResults(generation);
    }

    public Map<Integer, Double> getResults() {
        return results;
    }

    public void createStartingGeneration(int size) {
        innov.setInv(0);
        List<String> neurons = new ArrayList<>();
        neurons.addAll(idsOfInputs);
        neurons.addAll(idsOfOutputs);
        for (String s : neurons) {
            Neuron n = new Neuron(s);
            if (idsOfInputs.contains(s)) {
                inputNeurons.add(n);
            } else {
                outputNeurons.add(n);
            }
        }
        for (int i = 0; i < size; i++) {
            axons = new ArrayList<>();
            for (Neuron out : outputNeurons) {
                for (Neuron in : inputNeurons) {
                    Axon a = new Axon(in, out, (Math.random() * 2 - 1));
                    axons.add(a);
                }
            }
            genome = new LinkedHashMap<>();
            for (Axon a : axons) {
                List<String> values = new ArrayList<>();
                values.add(a.getInput().getName());
                values.add(a.getOutput().getName());
                values.add(String.valueOf(a.getWeight()));
                values.add("ENABLED");

                List<String> newMut = new ArrayList<>();
                newMut.add("original");
                newMut.add(a.getInput().getName());
                newMut.add(a.getOutput().getName());
                if (mutations.containsValue(newMut)) {
                    for (Integer m : mutations.keySet()) {
                        if (mutations.get(m).equals(newMut)) {
                            genome.put(m, values);
                        }
                    }
                } else {
                    int tempInnov = innov.getInv();
                    genome.put(tempInnov, values);
                    mutations.put(tempInnov, newMut);
                    innov.incInv();
                }
            }
            this.saveGenome(0, i, true, true);
        }
        this.saveMutations(0);
        this.saveSpecies(generation);
    }

    //Utility
    public void printGenome() {
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
            System.out.println(m.getKey() + ", " + m.getValue());
        }
    }

    public void printGenerationresults() {
        for (Map.Entry<Integer, Double> r : results.entrySet()) {
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
        if (!hiddenNeurons.isEmpty()) {
            String lastName = hiddenNeurons.get(hiddenNeurons.size() - 1).getName();
            int hiddenNum = Integer.parseInt(lastName.substring(1));
            hiddenNum++;
            return hiddenNum;
        } else {
            return 1;
        }
    }

    public int getRandomGenomeEntry() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, genome.size());
        List<Integer> keys = new ArrayList<>(genome.keySet());
        int i = keys.get(randomNum);
        return i;
    }

    public void reset() {
        allNeurons = new ArrayList<>();
        inputNeurons = new ArrayList<>();
        outputNeurons = new ArrayList<>();
        hiddenNeurons = new ArrayList<>();
        axons = new ArrayList<>();
        previousValues = new LinkedHashMap<>();
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(int g) {
        generation = g;
    }

    public Integer getOrganism() {
        return organism;
    }
    
    public Integer getReal() {
        return real_ID;
    }

    //Save/loading
    public void saveGenome(int g, int o, boolean b, boolean inc) {
        Writer w;
        try {
            if (b) {
                File folder = new File(System.getProperty("user.dir")
                        + "/results/generation_" + g);
                if (folder.mkdir()) {
//                    System.out.println("Directory Created");
                } else {
//                    System.out.println("Directory Not Created");
                }
            }
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/generation_" + g + "/organism_" + o + ".txt");
            if (inc) {
                w.write("\r\n" + genomeNum + ",");
                genomeNum++;
            } else {
                w.write("\r\n" + real_ID + ",");
            }
            for (Map.Entry<Integer, List<String>> m : genome.entrySet()) {
                w.write("\r\n" + m.getKey()
                        + "," + m.getValue().get(0)
                        + "," + m.getValue().get(1)
                        + "," + m.getValue().get(2)
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
                + "/results/generation_" + g + "/organism_" + o + ".txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            List<String> values;
            String temp = s.next();
            temp = temp.replaceAll("\\r\\n", "");
            real_ID = Integer.parseInt(temp);
//            System.out.println("C: " + real_ID);
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
        generation = g;
        organism = o;
        this.reset();
        this.createNeurons();
        this.createAxons();
//        this.loadMutations(g);
    }

    public Map<Integer, List<String>> loadGenome1(int g, int o) {
        Map<Integer, List<String>> qqqq = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/generation_" + g + "/organism_" + o + ".txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            List<String> values;
            s.next();
            while (s.hasNext()) {
                values = new ArrayList<>();
                String key = s.next();
                key = key.replaceAll("\\r\\n", "");
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                values.add(s.next());
                qqqq.put(Integer.parseInt(key), values);
            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex);
        }
        return qqqq;
    }

    public void saveGenerationResults(int g) {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/generation_" + g + "/results.txt");
            for (Map.Entry<Integer, Double> r : results.entrySet()) {
                w.write("\r\n" + r.getKey() + "," + r.getValue() + ",");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void saveOrderedResults(int g) {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/generation_" + g + "/orderedResults.txt");
            for (Map.Entry<Integer, Double> r : results.entrySet()) {
                w.write("\r\n" + r.getKey() + "," + r.getValue() + ",");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void loadGenerationResults(int g) {
        results = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/generation_" + g + "/results.txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            double result;
            while (s.hasNext()) {
                String key = s.next();
                key = key.replaceAll("\\r\\n", "");
                result = Double.parseDouble(s.next());
                results.put(Integer.parseInt(key), result);
            }
            s.close();
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex);
        }
    }

    public String loadMaxResult(int g) {
        String result = "";
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/generation_" + g + "/orderedResults.txt"))) {
            s.delimiter();
            s.useDelimiter(",");
            String key = s.next();
            key = s.next();
            key = key.replaceAll("\\r\\n", "");
            result = key;
            s.close();
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + ex);
        }
        return result;
    }

    public void saveMutations(int g) {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/results/generation_" + g + "/mutations.txt");
            for (Map.Entry<Integer, List<String>> m : mutations.entrySet()) {
                w.write("\r\n" + m.getKey() + "," + m.getValue().get(0)
                        + "," + m.getValue().get(1) + "," + m.getValue().get(2)
                        + ",");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void loadMutations(int g) {
        mutations = new LinkedHashMap<>();
        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
                + "/results/generation_" + g + "/mutations.txt"))) {
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

    public void saveSpecies(int g) {
//        Writer w;
//        try {
//            w = new FileWriter(System.getProperty("user.dir")
//                    + "/results/generation_" + g + "/species.txt");
//            for (List<Integer> s : species) {
//                w.write("\r\n" + s + ":");
//            }
//            w.close();
//        } catch (IOException ex) {
//            System.out.println("Error writing to file: " + ex);
//        }
    }

    public void loadSpecies(int g) {
//        species = new ArrayList<>();
//        try (Scanner s = new Scanner(new FileReader(System.getProperty("user.dir")
//                + "/results/generation_" + g + "/species.txt"))) {
//            s.delimiter();
//            s.useDelimiter(":");
//            List<String> values;
//            values = new ArrayList<>();
//            while (s.hasNext()) {
//                String key = s.next();
//                key = key.replaceAll("\\r\\n", "");
//                key = key.replaceAll("\\[", "");
//                key = key.replaceAll("\\]", "");
//                values.add(key);
//            }
//            species = new ArrayList<>();
//            for (String a : values) {
//                String b = a.replaceAll("\\s", "");
//                List<String> c = Arrays.asList(b.split("\\,"));
//                List<Integer> temp = new ArrayList<>();
//                for (String d : c) {
//                    temp.add(Integer.parseInt(d));
//                }
//                species.add(temp);
//            }
////            System.out.println(species);
//            s.close();
//        } catch (IOException ex) {
//            System.out.println("Error reading from file: " + ex);
//        }
    }

    //Mutation Functions
    public void mutate(int popSize, double enableChance,
            double eliteThresh, double champThresh,
            double crossMate, double addNeuron, double addConnection,
            double weightChangeChance, double weightRandomChance,
            double actChangeChance) {

        this.saveGenerationResults(generation);
        mutations.clear();
        this.orderResults();

        int o = 0;
        Map<Integer, List<String>> temp = new LinkedHashMap<>();

        this.specAdjustAllSizes(popSize, champThresh);

//        System.out.println("Species" + species);
        for (Species s : species) {
            double newSize = adjustSize.get(s.getId());
//            System.out.println("nS: " + newSize);
            double count = 0;

            List<Integer> ordered = s.orderSpecies(this);
            List<Integer> trimmed = s.trimSpecies(eliteThresh, ordered);

//            System.out.println("Trim: " + trimmed);
            //Save Champion if species hasn't been exterminated, and above
            //champThresh size
//            if (newSize > 0 && s.getSpecies().size() > champThresh) {
//                s.clearSpecies();
//                this.loadGenome(generation, trimmed.get(0));
//                this.saveGenome(generation + 1, o, true, false);
//                o++;
//                count++;
//                s.addtoSpec(0, real_ID);
//            }
            //Save All Elite
//            if (newSize >= trimmed.size()) {
//                s.clearSpecies();
//                for (Integer i : trimmed) {
//                    this.loadGenome(generation, i);
//                    this.saveGenome(generation + 1, o, true, false);
//                    o++;
//                    count++;
//                    s.addtoSpec(i, real_ID);
//                }
//            } else {
//                s.clearSpecies();
//                for (int i = 0; i < newSize; i++) {
//                    this.loadGenome(generation, trimmed.get(i));
//                    this.saveGenome(generation + 1, o, true, false);
//                    o++;
//                    count++;
//                    s.addtoSpec(i, real_ID);
//                }
//            }
            //Save Champion
            if (s.getSpecies().size() >= champThresh && newSize >= 1) {
                this.loadGenome(generation, trimmed.get(0));
                s.clearSpecies();
                this.saveGenome(generation + 1, o, true, false);
                o++;
                count++;
                s.addtoSpec(0, real_ID);
            } else {
                s.clearSpecies();
            }

            //elite no crossover
//             if (newSize >= trimmed.size()) {
//                s.clearSpecies();
//                for (Integer i : trimmed) {
//                    this.loadGenome(generation, i);
//                    double mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
//                    if (mutChance <= weightChangeChance) {
//                        this.changeWeights(temp, weightRandomChance);
//                    }
//                    mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
//                    if (mutChance <= addNeuron) {
//                        this.addNeuron();
//                    }
//                    mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
//                    if (mutChance <= addConnection) {
//                        this.addAxon();
//                    }
//                    this.saveGenome(generation + 1, o, true, true);
//                    o++;
//                    count++;
//                    s.addtoSpec(i, real_ID);
//                }
//            } else {
//                s.clearSpecies();
//                for (int i = 0; i < newSize; i++) {
//                    this.loadGenome(generation, trimmed.get(i));
//                    double mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
//                    if (mutChance <= weightChangeChance) {
//                        this.changeWeights(temp, weightRandomChance);
//                    }
//                    mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
//                    if (mutChance <= addNeuron) {
//                        this.addNeuron();
//                    }
//                    mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
//                    if (mutChance <= addConnection) {
//                        this.addAxon();
//                    }
//                    this.saveGenome(generation + 1, o, true, false);
//                    o++;
//                    count++;
//                    s.addtoSpec(i, real_ID);
//                }
//            }
            while (count < newSize) {
                double value = ThreadLocalRandom.current().nextDouble(0, 101);
                if (value > crossMate) {
                    int rand1 = ThreadLocalRandom.current().nextInt(0, trimmed.size());
                    int rand2 = ThreadLocalRandom.current().nextInt(0, trimmed.size());
                    temp = new LinkedHashMap<>(this.crossover(generation, trimmed.get(rand1), trimmed.get(rand2), enableChance));
                    genome = temp;
                    double mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
                    if (mutChance <= weightChangeChance) {
                        this.changeWeights(temp, weightRandomChance);
                    }
                    mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
                    if (mutChance <= addNeuron) {
                        this.addNeuron();
                    }
                    mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
                    if (mutChance <= addConnection) {
                        this.addAxon();
                    }
                    this.saveGenome(generation + 1, o, true, true);
                    o++;
                    count++;
                } else {
                    int rand1 = ThreadLocalRandom.current().nextInt(0, trimmed.size());
                    int randSpec = ThreadLocalRandom.current().nextInt(0, species.size());
                    List<Integer> trimmedTemp = species.get(randSpec).trimSpecies(eliteThresh, species.get(randSpec).orderSpecies(this));
                    if (!trimmedTemp.isEmpty()) {
                        int rand2 = ThreadLocalRandom.current().nextInt(0, trimmedTemp.size());
                        temp = new LinkedHashMap<>(this.crossover(generation, trimmed.get(rand1), trimmedTemp.get(rand2), enableChance));
                        genome = temp;
                        double mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
                        if (mutChance <= weightChangeChance) {
                            this.changeWeights(temp, weightRandomChance);
                        }
                        mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
                        if (mutChance <= addNeuron) {
                            this.addNeuron();
                        }
                        mutChance = ThreadLocalRandom.current().nextDouble(0, 101);
                        if (mutChance <= addConnection) {
                            this.addAxon();
                        }
                        this.changeActivation(actChangeChance);
//                        }
                        this.saveGenome(generation + 1, o, true, true);
                        o++;
                        count++;
                    }
                }
            }
            if (count != 0) {
                System.out.println("C: " + count);
            }
        }

        System.out.println("");
//        this.printSpecies();

        results = new LinkedHashMap<>();
        adjustSize = new LinkedHashMap<>();
        this.saveMutations(generation + 1);
        generation++;
        this.tidySpecies();
    }

    public Map<Integer, List<String>> crossover(int generation, int organsim1, int organsim2, double enableChance) {
        Map<Integer, List<String>> genome1 = this.loadGenome1(generation, organsim1);
        Map<Integer, List<String>> genome2 = this.loadGenome1(generation, organsim2);
//        this.loadGenerationResults(generation);

        double genome1Result = results.get(organsim1);
        double genome2Result = results.get(organsim2);

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
        for (int i : g2KeySet) {
            if (g1KeySet.contains(i)) {
                matching.add(i);
            }
        }
        //excess
        if (g1KeySet.size() > g2KeySet.size()) {
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

        //create new genome
        //matching
        Map<Integer, List<String>> newGenome = new LinkedHashMap<>();
        for (int i : matching) {
            int rand = ThreadLocalRandom.current().nextInt(0, 101);
            if (rand > 50) {
                newGenome.put(i, genome1.get(i));
            } else {
                newGenome.put(i, genome2.get(i));
            }
            if (!(genome1.get(i).get(3).equals("ENABLED")
                    && genome2.get(i).get(3).equals("ENABLED"))) {
                double eChance = ThreadLocalRandom.current().nextDouble(0, 101);
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
                int rand = ThreadLocalRandom.current().nextInt(0, 101);
                if (rand > 50) {
                    newGenome.put(i, genome1.get(i));
                }
            }
            for (int i : disjoint2) {
                int rand = ThreadLocalRandom.current().nextInt(0, 101);
                if (rand > 50) {
                    newGenome.put(i, genome2.get(i));
                }
            }
            for (int i : excess) {
                int rand = ThreadLocalRandom.current().nextInt(0, 101);
                if (rand > 50 && (g1KeySet.size() > g2KeySet.size())) {
                    newGenome.put(i, genome1.get(i));
                } else if (rand > 50) {
                    newGenome.put(i, genome2.get(i));
                }
            }
        } else if (fittest.equals("genome1")) {
            for (int i : disjoint1) {
                newGenome.put(i, genome1.get(i));
            }
            if (g1KeySet.size() > g2KeySet.size()) {
                for (int i : excess) {
                    newGenome.put(i, genome1.get(i));
                }
            }
        } else {
            for (int i : disjoint2) {
                newGenome.put(i, genome2.get(i));
            }
            if (g2KeySet.size() > g1KeySet.size()) {
                for (int i : excess) {
                    newGenome.put(i, genome2.get(i));
                }
            }
        }
//        genome = new LinkedHashMap<>(newGenome);
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
        disabled.set(3, "DISABLED");
        genome.put(genomeKey, disabled);

    }

    public void addAxon() {
        List<Neuron> currentOutputs;
        List<Neuron> availableInput = new ArrayList<>();
        List<Neuron> hidOut = new ArrayList<>();
        hidOut.addAll(hiddenNeurons);
        hidOut.addAll(outputNeurons);

        List<Neuron> inHid = new ArrayList<>();
        inHid.addAll(inputNeurons);
        inHid.addAll(hiddenNeurons);

        //get available inputs
        for (Neuron all : inHid) {
            currentOutputs = new ArrayList<>();
//            currentOutputs.add(all);
//            for (Neuron n : inputNeurons) {
//                currentOutputs.add(n);
//            }
            for (Axon a : axons) {
                if (a.getInput() == all) {
                    currentOutputs.add(a.getOutput());
                }
            }
            if (!currentOutputs.containsAll(hidOut)) {
                availableInput.add(all);
            }
        }
        if (availableInput.isEmpty()) {
            System.out.println("No Available Inputs");
        } else {
            //get available outputs
            int availRand = ThreadLocalRandom.current().nextInt(0, availableInput.size());
            List<Neuron> availableOutputs = new ArrayList<>();
            List<Neuron> outTemp = new ArrayList<>();
            for (Axon a : axons) {
                if (a.getInput() == availableInput.get(availRand)) {
//                    outTemp.add(a.getInput());
                    outTemp.add(a.getOutput());
                }
            }
            for (Neuron o : hidOut) {
                if (!outTemp.contains(o)) {
                    availableOutputs.add(o);
                }
            }
            if (availableOutputs.isEmpty()) {
                System.out.println("No Available Outputs");
            } else {
                int outRand = ThreadLocalRandom.current().nextInt(0, availableOutputs.size());
                double weight = (Math.random() * 2 - 1);
                Axon a = new Axon(availableInput.get(availRand), availableOutputs.get(outRand), weight);
                axons.add(a);
                List<String> temp = new ArrayList<>();
                temp.add(availableInput.get(availRand).getName());
                temp.add(availableOutputs.get(outRand).getName());
                temp.add(String.valueOf(weight));
                temp.add("ENABLED");

                List<String> newMut = new ArrayList<>();
                newMut.add("addAxon");
                newMut.add(availableInput.get(availRand).getName());
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
    }

    public void changeWeights(Map<Integer, List<String>> offspring, double randomChance) {
        for (Map.Entry<Integer, List<String>> m : offspring.entrySet()) {
            double value = ThreadLocalRandom.current().nextDouble(-1, 1.00000000000001);
            double uniform = ThreadLocalRandom.current().nextDouble(0, 101);
            if (uniform < randomChance) {
                m.getValue().set(2, Double.toString(Double.parseDouble(m.getValue().get(2)) + value));
            } else {
                m.getValue().set(2, Double.toString(Math.random() * 2 - 1));
            }
        }
    }

    public void removeAxon() {
        //remove axon from axons and genome
        //check all neurons, if not input or ourtput, remove
    }

    public void removeNeuron() {
        //remove neuron from all/in/out/hid and genome
        //remove all connected axons
    }

    public void saveActivations(int gen) {
        //Map of neuron name to actType
        // On neuron creation, check map
        //if in map set actType of neuron to value
        //else add neuron to Map with default value

    }

    public void changeActivation(double actChangeChance) {
        //change act value of neuron
        for (Neuron n : allNeurons) {
            int newAct = ThreadLocalRandom.current().nextInt(0, 8);
            double value = ThreadLocalRandom.current().nextDouble(0, 101);
            if (value < actChangeChance) {
                n.setActType(newAct);
                activations.put(n.getName(), n.getActType());
            }
        }
    }

    public void printActivations() {
        for (String s : activations.keySet()) {
            System.out.println("Neuron: " + s + ", actType: " + activations.get(s));
        }
    }

    //Speciation
    //Function to return values from two networks
    public Double specCal(int generation, Species s, double c1, double c2, double c3) {

        //get random organism from species
        int rand = s.getRandomSpecies();
        Map<Integer, List<String>> genome1 = this.loadGenome1(generation, rand);

        List<Integer> matching = new ArrayList<>();
        List<Integer> excess = new ArrayList<>();
        List<Integer> disjoint1 = new ArrayList<>();
        List<Integer> disjoint2 = new ArrayList<>();

        List<Integer> g1KeySet = new ArrayList<>(genome1.keySet());
        List<Integer> g2KeySet = new ArrayList<>(genome.keySet());

        //Matching
        for (int i : genome1.keySet()) {
            if (genome.keySet().contains(i)) {
                matching.add(i);
            }
        }

        //Excess
        if (genome1.keySet().size() > genome.keySet().size()) {
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

        //Disjoint
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

        //Distance calculation
        double W = 0.0;
        for (int i : matching) {
            W += Math.abs(Double.parseDouble(genome1.get(i).get(2)) - Double.parseDouble(genome.get(i).get(2)));
        }
        W /= matching.size();

        int N;
        if (g1KeySet.size() < 20 && g2KeySet.size() < 20) {
            N = 1;
        } else if (g1KeySet.size() >= g2KeySet.size()) {
            N = g1KeySet.size();
        } else {
            N = g2KeySet.size();
        }

        double dist = ((c1 * excess.size()) / N)
                + ((c2 * (disjoint1.size() + disjoint2.size())) / N)
                + (c3 * W);

//        System.out.println("Matching: " + matching);
//        System.out.println("Excess: " + excess);
//        System.out.println("Dis1: " + disjoint1);
//        System.out.println("Dis2: " + disjoint2);
//        System.out.println(dist);
        return dist;
    }

    //Add to species/Create new species
    public void addToSpecies(int generation, double c1, double c2, double c3, double threshold, int repNum) {
        boolean added = false;
//        System.out.println("T: " + real_ID);

        //get list of all species realNums, check if not present
        List<Integer> temp = new ArrayList<>();
        for (Species s : species) {
            for (Integer i : s.getReal()) {
                temp.add(i);
            }
        }

        if (temp.contains(real_ID)) {
            added = true;
        } else {
            for (Species s : species) {
                double dist = this.specCal(generation, s, c1, c2, c3);
                if (dist < threshold) {
                    s.addtoSpec(organism, real_ID);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            Species a = new Species(speciesNum, repNum);
            a.addtoSpec(organism, real_ID);
            species.add(a);
            speciesNum++;
        }
    }
    //Fix to work on non 0 generation

    public void speciate(int generation, int popSize, double c1, double c2, double c3, double threshold, int repNum) {
//        this.loadGenerationResults(generation);
        int x = 0;
        while (x < popSize) {
            this.loadGenome(generation, x);
            this.addToSpecies(generation, c1, c2, c3, threshold, repNum);
            x++;
        }
        noOfSpecs.add(species.size());
    }

    public double popMean(int popSize) {
        adjustResults = new LinkedHashMap<>();
        for (Species s : species) {
            for (Integer i : s.getSpecies()) {
                adjustResults.put(i, this.getAdjustFitness(i, s));
            }
        }
        double temp = 0;
        for (Integer i : adjustResults.keySet()) {
            temp += adjustResults.get(i);
        }
        return temp / popSize;
    }

    public void specAdjustAllSizes(int popSize, double champThresh) {

        adjustSize = new LinkedHashMap<>();
        for (Species s : species) {
            adjustSize.put(s.getId(), s.specAdjustSize(this, popSize, champThresh));
        }
        System.out.println("Adjusted: " + adjustSize);

        //readjust sizes to match pop count
        Map<Integer, Double> temp = new LinkedHashMap<>(adjustSize);
        for (Integer i : temp.keySet()) {
            temp.put(i, temp.get(i) % 1);
        }

        for (Integer i : adjustSize.keySet()) {
            adjustSize.put(i, Math.floor(adjustSize.get(i)));
        }
        System.out.println("Rounded: " + adjustSize);

        double pop = 0;
        for (Integer i : adjustSize.keySet()) {
            pop += adjustSize.get(i);
        }

        int diff = popSize - (int) pop;
        System.out.println("Current Size: " + pop);
        System.out.println("Difference: " + diff);

        if (diff != 0) {
//        System.out.println(temp);
            Map<Integer, Double> orderedFrac = temp.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldResult, newResult) -> oldResult, LinkedHashMap::new));
            temp = new LinkedHashMap<>(orderedFrac);
            for (Integer i : temp.keySet()) {
                if (temp.get(i) == 0.0) {
                    orderedFrac.remove(i);
                }
            }

            System.out.println("Sorted Fractionals: " + orderedFrac);
            List<Integer> k = new ArrayList<>();
            for (Integer i : orderedFrac.keySet()) {
                k.add(i);
            }
            System.out.println("Sorted Keys: " + k);

            if (!k.isEmpty()) {
                if ((Math.abs(diff) >= k.size())) {
                    int counter = (int) Math.floor(Math.abs(diff) / k.size());
                    while (counter > 0) {
                        if (diff > 0) {
                            for (int i = 0; i < k.size(); i++) {
                                int x = k.get(k.size() - 1 - i);
                                adjustSize.put(x, adjustSize.get(x) + 1);
                            }
                        } else {
                            for (int i = 0; i < k.size(); i++) {
                                int x = k.get(i);
                                adjustSize.put(x, adjustSize.get(x) - 1);
                            }
                        }
                        counter--;
                    }
                    if (diff > 0) {
                        diff -= (int) Math.floor(Math.abs(diff) / k.size()) * k.size();
                    } else {
                        diff += (int) Math.floor(Math.abs(diff) / k.size()) * k.size();
                    }
                }
                System.out.println("NewDiff: " + diff);
                if (diff > 0) {
                    for (int i = 0; i < Math.abs(diff); i++) {
                        int x = k.get(k.size() - 1 - i);
                        adjustSize.put(x, adjustSize.get(x) + 1);
                    }
                } else {
                    for (int i = 0; i < Math.abs(diff); i++) {
                        int x = k.get(i);
                        adjustSize.put(x, adjustSize.get(x) - 1);
                    }
                }
            }
        } else {
            if (diff > 0) {
                while (diff > 0) {
                    for (Integer i : adjustSize.keySet()) {
                        adjustSize.put(i, adjustSize.get(i) + 1);
                        diff--;
                        if (!(diff > 0)) {
                            break;
                        }
                    }
                }
            } else {
                while (diff < 0) {
                    for (Integer i : adjustSize.keySet()) {
                        adjustSize.put(i, adjustSize.get(i) - 1);
                        diff++;
                        if (!(diff < 0)) {
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("ReAdjusted: " + adjustSize);
        System.out.println("");
    }

    public void printSpecies() {
        System.out.println("Species: " + species.size());
//        System.out.println(species);
        for (Species s : species) {
            System.out.println("Size: " + s.getSpecies().size());
//            System.out.println("Spec: " + s.getSpecies());
            System.out.println("Real: " + s.getReal());
            System.out.println("");
        }
//        for(Species s : species) {
//            System.out.println(s.getSpecies().size());
//        }
    }

    public void tidySpecies() {
        List<Species> temp = new ArrayList<>();
        for (Species s : species) {
            if (s.getSpecies().isEmpty()) {
                temp.add(s);
            }
        }
        for (Species s : temp) {
            species.remove(s);
        }
    }

    public void recordSpecies() {
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/noOfSpecs.csv");
            for (int i = 0; i < noOfSpecs.size(); i++) {
                w.write(noOfSpecs.get(i) + "," + "\r\n");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void graphMaxfitness(int genSize) {
        List<List<String>> max = new ArrayList<>();
        for (int i = 0; i < genSize; i++) {
            List<String> temp = new ArrayList<>();
            this.loadGenerationResults(i);
            temp.add(this.loadMaxResult(i));
            max.add(temp);
        }
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/maxResults.csv");
            for (List<String> s : max) {
                w.write(s.get(0) + "," + "\r\n");
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }

    public void addToGraphSpecResults() {
        List<List<String>> vals = new ArrayList<>();
        for (Species s : species) {
            List<String> temp = new ArrayList<>();
            temp.add(Integer.toString(s.getId()));
            temp.add(Double.toString(s.getMaxFitness()));
            vals.add(temp);
        }
        specResults.add(vals);
    }

    public void graphSpeciesResults(int popSize) {
//        System.out.println(speciesNum);
//        for (List<List<String>> s : specResults) {
//            System.out.println(s);
//        }
        Writer w;
        try {
            w = new FileWriter(System.getProperty("user.dir")
                    + "/specResults.csv");
            String vals = "";
            for (int i = 0; i < speciesNum; i++) {
                vals += "Species_" + i + ",";
            }
            vals += "\r\n";
            w.write(vals);
            for (List<List<String>> s : specResults) {
                vals = "";
                for (int i = 0; i < speciesNum; i++) {
                    List<String> temp = new ArrayList<>();
                    for (List<String> l : s) {
                        temp.add(l.get(0));
                    }
                    if (temp.contains(Integer.toString(i))) {
                        for (List<String> a : s) {
                            if (a.get(0).equals(Integer.toString(i))) {
                                vals += a.get(1) + ",";
                            }
                        }
                    } else {
                        vals += "0" + ",";
                    }
                }
                vals += "\r\n";
                w.write(vals);
            }
            w.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex);
        }
    }
}
