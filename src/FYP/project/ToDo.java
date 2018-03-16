package FYP.project;

/**
 *
 * @author Chris
 */
public class ToDo {

}

//Primary
//Change genome to be an object - modify NEAT to cope
//Define external fitness class
//Add remove node/connection mutators
//Add parallelization
//Allow inputs to be output nodes on 'add Neuron' + allow self-recurrance
//Change innovation record to be global
//Fuction to delete old organism files for space
//Chnage farm to add food randomly distributed across grid + add delay timer
//Change fitness values to be double
//
//
//Secondary
//TODO Make pest class
//TODO Add multithreading to pests
//TODO Merge Tile class into World
//TODO Add tick counter to world, make entities act on ticks
//TODO Input/Output neuron restrictions
//
//Misc
//Use Atl+ins to generate code
//Convert all for loops to lambda functions
//TODO Add analytics tracking to classes
//TODO Reorder Entity methods
//TODO Entity '0 energy' behaviour
//TODO Write tests (unit tests)
//TODO Comment code (javadoc)
//TODO Change farm food size generation
//TODO Pull occurance variables out into constructor
//TODO Add controls to window
//TODO Go over method encapsulation for main use
//TODO Switch chained if's to switch case in window
//
//Ideas
//Mutate activation function
//Add previous value to neurons to asct as memory




//        List<Integer> tempResults = new ArrayList<>(results.keySet());
//        for (int i = 0; i < top; i++) {
//            this.loadGenome(generation, tempResults.get(i));
//            this.saveGenome(generation + 1, i, true);
//        }
//        for (int i = 0; i < 8; i++) {
////            int weightChance = ThreadLocalRandom.current().nextInt(0, top - 1);
//            this.loadGenome(generation, tempResults.get(i % 10));
//            this.changeWeights(10);
////            this.saveGenome(generation + 1, i+top, true);
////            int addChance = ThreadLocalRandom.current().nextInt(0, top - 1);
////            this.loadGenome(generation, tempResults.get(addChance));
//            int chance = ThreadLocalRandom.current().nextInt(0, 100);
//            if (chance < 50) {
//                this.addNeuron();
//            } else {
//                this.addAxon();
//            }
////            this.saveGenome(generation + 1, i+top*2, true);
//            int firstChance = ThreadLocalRandom.current().nextInt(0, 10);
//            this.crossover(generation, firstChance, i % 10, 75);
//            this.saveGenome(generation + 1, i + top, true);
//        }
////        for (int i = 0; i < top; i++) {
////            int addChance = ThreadLocalRandom.current().nextInt(0, top - 1);
////            this.loadGenome(generation, tempResults.get(addChance));
////            int chance = ThreadLocalRandom.current().nextInt(0, 100);
////            if (chance < 50) {
////                this.addNeuron();
////            } else {
////                this.addAxon();
////            }
////            this.saveGenome(generation + 1, i+top*2, true);
////        }
////        for (int i = 0; i < top; i++) {
//////            this.loadGenome(generation, tempResults.get(i));
////            int firstChance = ThreadLocalRandom.current().nextInt(0, 25);
////            int secondChance = ThreadLocalRandom.current().nextInt(0, 25);
////            this.crossover(generation, firstChance, secondChance, 75);
////            this.saveGenome(generation + 1, i+top*3, true);
////        }
