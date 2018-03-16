package FYP.project;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Chris
 */
public class Species {

    private List<Integer> species;
    private double maxFitness;
    private double lastMaxFitness;
    private int lastImproved;
    private int id;
    private List<Integer> realNums;
    private int generation;
    private int repMin;

    public Species(Integer i, int r) {
        species = new ArrayList<>();
        realNums = new ArrayList<>();
        id = i;
        generation = 0;
        maxFitness = 0;
        lastMaxFitness = 0;
        lastImproved = 0;
        repMin = r;
    }

    public Integer getId() {
        return id;
    }

    public void setSpecies(List<Integer> s) {
        species = s;
    }

    public void clearSpecies() {
        species = new ArrayList<>();
        realNums = new ArrayList<>();
    }

    public List<Integer> getSpecies() {
        return species;
    }

    public List<Integer> getReal() {
        return realNums;
    }

    public Integer getRandomSpecies() {
        int rand = ThreadLocalRandom.current().nextInt(0, species.size());
        return species.get(rand);
    }

    public void addtoSpec(int x, int real) {
        species.add(x);
        realNums.add(real);
//        System.out.println("Spec:" + x);
//        System.out.println("Real:" + real);
    }

    public void removeFromSpec(int x) {
        species.remove(x);
    }

    public void saveSpecies() {

    }

    public double specAdjustSize(NEAT n, int popSize, double champThresh) {
        double newSize;

        //get adjusted fitness for species
        Map<Integer, Double> adjustResults = new LinkedHashMap<>();
        for (Integer i : species) {
            adjustResults.put(i, n.getAdjustFitness(i, this));
        }

        //get sum of adjusted fitnesses for species
        double fitSum = 0;
        for (Integer i : adjustResults.keySet()) {
            fitSum += adjustResults.get(i);
        }

        Map<Integer, Integer> fitness = new LinkedHashMap<>();
        for (Integer i : species) {
            fitness.put(i, n.getFitness(i));
        }
        maxFitness = 0;
        for (Integer i : fitness.keySet()) {
            if (fitness.get(i) > maxFitness) {
                maxFitness = fitness.get(i);
            }
        }

        if (maxFitness > lastMaxFitness) {
            lastMaxFitness = maxFitness;
            lastImproved = 0;
        } else {
            lastImproved++;
        }

        //calculate mean of all pop adjusted fitnessess
        double popFitMean = n.popMean(popSize);

        newSize = fitSum / popFitMean;

        if (Double.isNaN(newSize)) {
            newSize = 0;
        }

        if (lastImproved >= repMin) {
            if (species.size() >= champThresh) {
                newSize = 1;
            } else {
                newSize = 0;
            }
        }

        generation++;
        return newSize;
    }

    public List<Integer> orderSpecies(NEAT n) {
        Map<Integer, Integer> temp = new LinkedHashMap<>();
        Map<Integer, Integer> res = new LinkedHashMap<>(n.getResults());

        for (Integer i : species) {
            temp.put(i, res.get(i));
        }

        Map<Integer, Integer> orderedResults = temp.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldResult, newResult) -> oldResult, LinkedHashMap::new));

        List<Integer> list = new ArrayList<>(orderedResults.keySet());
//        System.out.println(orderedResults);
        return list;
    }

    public List<Integer> trimSpecies(double threshold, List<Integer> s) {
        List<Integer> trimmed = new ArrayList<>();
        double newSize = Math.ceil(s.size() * threshold / 100);
//        System.out.println(newSize);
        for (int i = 0; i < newSize; i++) {
            trimmed.add(s.get(i));
        }
//        System.out.println(trimmed);

        return trimmed;
    }
}
