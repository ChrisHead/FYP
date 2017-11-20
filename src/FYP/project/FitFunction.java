/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.List;
import org.neuroph.contrib.neat.gen.*;
import org.neuroph.contrib.neat.gen.operations.*;
import org.neuroph.core.NeuralNetwork;

/**
 *
 * @author Chris
 */
public class FitFunction implements FitnessFunction{
    
    public int calculateNetworkFitness(NeuralNetwork<?> net){
        World w = new World(26);
        w.loadWorld();
        Entity e = new Entity(w);
        e.setPosition(0, 0, 0);
        //network input behaviour
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
