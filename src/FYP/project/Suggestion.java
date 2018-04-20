package FYP.project;

import java.util.*;
import java.util.stream.Collectors;

/**
 * * @author Chris
 */
public class Suggestion {

    private Map<Integer, Record> previousMoves;
    private Map<Integer, Double> finalFitness;
    private List<Record> records;
    private int movesKey;

    public Suggestion() {
        previousMoves = new LinkedHashMap<>();
        finalFitness = new LinkedHashMap<>();
        records = new ArrayList<>();
        movesKey = 0;
    }

    public void addMove(int real_ID, List<Double> inputs, String output) {
//        System.out.println("Adding Move");
        Map<Integer, Record> movesTemp = new LinkedHashMap<>(previousMoves);
        for (Integer i : previousMoves.keySet()) {
            if (previousMoves.get(i).getID().equals(real_ID)) {
                if (previousMoves.get(i).getMove().equals(output)) {
                    List<?> in = Arrays.asList(inputs);
                    List<?> prev = Arrays.asList(previousMoves.get(i).getValues());
                    if (in.equals(prev)) {
                        records.remove(previousMoves.get(i));
                        movesTemp.remove(i);
                    }
                }
            }
        }
        previousMoves = new LinkedHashMap<>(movesTemp);

        Record temp = new Record(real_ID, inputs, output);
        records.add(temp);
        previousMoves.put(movesKey, temp);
        movesKey++;
    }

    public void addFitness(int real_ID, double fitness) {
//        System.out.println("Adding Fitness");
        finalFitness.put(real_ID, fitness);
    }

    public Map<String, Double> getSuggestions(List<Double> inputs, int suggestLimit, double suggestFullValue, int currentGen) {
//        System.out.println("Getting Suggestions");
        //make list of previousMoves that match inputs
        Map<Integer, Record> temp = new LinkedHashMap<>();
        for (Integer i : previousMoves.keySet()) {
            List in = Arrays.asList(inputs);
            List prev = Arrays.asList(previousMoves.get(i).getValues());
            if (in.equals(prev)) {
                temp.put(i, previousMoves.get(i));
            }
        }

        //Order fitness values of organsims (decending) for the given input
        Map<Integer, Double> tempFinalFitness = finalFitness.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldResult, newResult) -> oldResult, LinkedHashMap::new));

        List<String> outputs = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        for (Integer i : tempFinalFitness.keySet()) {
            vals.add(i);
        }
        if (currentGen < suggestLimit) {
            suggestLimit = currentGen;
        }
        for (int i = 0; i < suggestLimit; i++) {
            for (Integer j : previousMoves.keySet()) {
                if (vals.get(i).equals(previousMoves.get(j).getID())) {
                    List<?> in = Arrays.asList(inputs);
                    List<?> prev = Arrays.asList(previousMoves.get(j).getValues());
                    if (in.equals(prev)) {
                        outputs.add(previousMoves.get(j).getMove());
                    }
                }
            }
        }
        Map<String, Double> suggestTemp = new LinkedHashMap<>();
        if (suggestLimit == 0) {
            suggestLimit++;
        }

        if (!outputs.isEmpty()) {

            //need to split vals properly
            double suggestVal = suggestFullValue / outputs.size();
            for (String s : outputs) {
                suggestTemp.put(s, 0.0);
            }
            for (String s : outputs) {
                suggestTemp.put(s, suggestTemp.get(s) + suggestVal);
            }
        }
//        System.out.println(suggestTemp);
//        for (String s : suggestTemp.keySet()) {
//            System.out.println(s + " " + suggestTemp.get(s));
//        }
        return suggestTemp;
    }

    public void trimSuggestions(double top, double bottom, boolean keepBottom) {
        //order fitness table
        //divide 100 by size to get percentage value of each entry
        //from bottom, add percentage values, if not in range, add to list
        //for each in list, remove from fitness table

        Map<Integer, Double> tempFinalFitness = finalFitness.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldResult, newResult) -> oldResult, LinkedHashMap::new));

        double eachVal = (float) 100 / tempFinalFitness.size();

        double counter = 0;
        List<Integer> del = new ArrayList<>();
        for (Integer i : tempFinalFitness.keySet()) {
            counter += eachVal;
            if (keepBottom) {
                if (counter > bottom && counter < 100 - top) {
                    del.add(i);
                }
            } else {
                if (counter < 100 - top) {
                    del.add(i);
                }
            }
        }

        for (Integer i : del) {
            finalFitness.remove(i);
        }

        List<Integer> delTemp = new ArrayList<>();
        for (Integer i : previousMoves.keySet()) {
            if (!finalFitness.containsKey(previousMoves.get(i).getID())) {
                delTemp.add(i);
            }
        }
        for (Integer i : delTemp) {
            previousMoves.remove(i);
        }

    }

    public void printPreviousMoves() {
        for (Integer i : previousMoves.keySet()) {
            System.out.println(previousMoves.get(i));
        }
        for (Integer i : finalFitness.keySet()) {
            System.out.println("Real_ID: " + i + " Fitness: " + finalFitness.get(i));
        }
    }

    public void printPreviousMovesSize() {
        System.out.println(previousMoves.size());
    }

    public void saveSuggestions() {

    }

    public void loadSuggestions() {

    }
}
