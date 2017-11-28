package FYP.project;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

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
    
    public void printGenome(){
        for (Map.Entry<Integer, List<String>> m : genome.entrySet()){
        System.out.println(m.getKey() + ", " + m.getValue());
        }
    }

    public void runGenome() {
        //Initial position?
        World w = new World(50);
        Tile t = new Tile(w);
        int x = 0;
        while (e.getEnergy() > 0) {
            e.updateInputValues();
            e.getOutput().LayerZeroInputs();
            net.runNetwork(false, false);
            e.action(t, false);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
            x++;
        }
        System.out.println(x);
        System.out.println("DONE");
    }

    public void genomeManipulation() {
        //Genome manipulation stuff
    }

    public void saveGenome() {

    }

    public void loadGenome() {

    }
}
