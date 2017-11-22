/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.Arrays;
import java.util.List;
import org.neuroph.contrib.neat.gen.*;
import org.neuroph.contrib.neat.gen.operations.*;
import org.neuroph.core.NeuralNetwork;

/**
 *
 * @author Chris
 */
public class FitFunction implements FitnessFunction {

    public int calculateNetworkFitness(NeuralNetwork<?> net) {
        World w = new World(26);
        w.loadWorld();
        Entity e = new Entity(w);
        e.setPosition(0, 0, 0);
        //network input behaviour
        while (e.getEnergy() > 0) {
            net.setInput(e.getX(), e.getY(), e.getO(), e.getEnergy(),
                    e.getInventory(), e.getSensor()[0], e.getSensor()[1],
                    e.getSensor()[2], e.getSensor()[3], e.getSensor()[4],
                    e.getSensor()[5], e.getSensor()[6], e.getSensor()[7],
                    e.getSensor()[8], e.getSensor()[9], e.getSensor()[10],
                    e.getSensor()[11], e.getSensor()[12], e.getSensor()[13],
                    e.getSensor()[14], e.getSensor()[15], e.getSensor()[16],
                    e.getSensor()[17], e.getSensor()[18], e.getSensor()[19],
                    e.getSensor()[20], e.getSensor()[21], e.getSensor()[22],
                    e.getSensor()[23], e.getSensor()[24]);
            System.out.println(Arrays.toString(net.getOutput()));

        }
        //network output behaviour
        return e.getMoves();
    }

    @Override
    public void evaluate(List<OrganismFitnessScore> fitnesses) {
        for (OrganismFitnessScore ofs : fitnesses) {
            Organism o = ofs.getOrganism();
            NeuralNetwork<?> net = ofs.getNeuralNetwork();
            int fitness = calculateNetworkFitness(net);

            ofs.setFitness(fitness);
        }
    }
}
