/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.List;
import java.util.Arrays;
import org.neuroph.contrib.neat.gen.*;
import org.neuroph.contrib.neat.gen.impl.*;
import org.neuroph.contrib.neat.gen.operations.*;
import org.neuroph.contrib.neat.gen.operations.fitness.*;
import org.neuroph.contrib.neat.gen.operations.mutation.*;
import org.neuroph.contrib.neat.gen.operations.reproduction.*;
import org.neuroph.contrib.neat.gen.operations.selector.*;
import org.neuroph.contrib.neat.gen.operations.speciator.*;
import org.neuroph.contrib.neat.gen.persistence.*;
import org.neuroph.contrib.neat.gen.persistence.impl.*;
import org.neuroph.contrib.neat.gen.persistence.impl.serialize.*;
import org.neuroph.contrib.neat.gen.util.*;
import org.neuroph.contrib.neat.gen.impl.SimpleNeatParameters;
import org.neuroph.core.NeuralNetwork;

/**
 *
 * @author Chris
 */
public class NEAT {

//    public void setParameters() {
//        SimpleNeatParameters params = new SimpleNeatParameters();
//        params.setFitnessFunction(new FitFunction());
//        params.setPopulationSize(1);
//        params.setMaximumFitness(1000);
//        params.setMaximumGenerations(1);
//    }
    public void setEvolver(NeatParameters params) {
        NeuronGene xPosition = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene yPosition = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene orientation = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene energy = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene inventory = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor1 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor2 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor3 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor4 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor5 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor6 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor7 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor8 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor9 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor10 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor11 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor12 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor13 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor14 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor15 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor16 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor17 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor18 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor19 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor20 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor21 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor22 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor23 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor24 = new NeuronGene(NeuronType.INPUT, params);
        NeuronGene sensor25 = new NeuronGene(NeuronType.INPUT, params);

        NeuronGene move = new NeuronGene(NeuronType.OUTPUT, params);
        NeuronGene turnLeft = new NeuronGene(NeuronType.OUTPUT, params);
        NeuronGene turnRight = new NeuronGene(NeuronType.OUTPUT, params);
        NeuronGene eat = new NeuronGene(NeuronType.OUTPUT, params);
        NeuronGene pickUp = new NeuronGene(NeuronType.OUTPUT, params);
        NeuronGene drop = new NeuronGene(NeuronType.OUTPUT, params);

        Evolver e = Evolver.createNew(params, Arrays.asList(xPosition,
                yPosition, orientation, energy, inventory, sensor1, sensor2,
                sensor3, sensor4, sensor5, sensor6, sensor7, sensor8, sensor9,
                sensor10, sensor11, sensor12, sensor13, sensor14, sensor15,
                sensor16, sensor17, sensor18, sensor19, sensor20, sensor21,
                sensor22, sensor23, sensor24, sensor25),
                Arrays.asList(move, turnLeft, turnRight, eat, pickUp, drop));
        try {
            Organism best = e.evolve();
            System.out.println(best.toString());
        } catch (Exception ex) {
            System.out.println("Error evolving: " + ex);
        }
    }

    public void convert(NeatParameters params, Organism o) {
        org.neuroph.core.NeuralNetwork<?> nn = params.getNeuralNetworkBuilder().createNeuralNetwork(o);
    }
}
