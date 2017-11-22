/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.Arrays;
import org.neuroph.contrib.neat.gen.Evolver;
import org.neuroph.contrib.neat.gen.NeuronGene;
import org.neuroph.contrib.neat.gen.NeuronType;
import org.neuroph.contrib.neat.gen.Organism;
import org.neuroph.contrib.neat.gen.impl.SimpleNeatParameters;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author Chris
 */
public class NTest {

////    public static void main(String[] args) {
////        DataSet trainingSet = new DataSet(2, 1);
////        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
////        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
////        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));
////        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);
////        myMlPerceptron.learn(trainingSet);
////        System.out.println("Testing trained neural network");
////        testNeuralNetwork(myMlPerceptron, trainingSet);
////        myMlPerceptron.save("myMlPerceptron.nnet");
////        NeuralNetwork<?> loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");
////        System.out.println("Testing loaded neural network");
////        testNeuralNetwork(loadedMlPerceptron, trainingSet);
////        NEAT test = new NEAT();
//        SimpleNeatParameters params = new SimpleNeatParameters();
//        params.setFitnessFunction(new FitFunction());
//        params.setPopulationSize(150);
//        params.setMaximumFitness(1000);
//        params.setMaximumGenerations(100);
//        
//        NeuronGene xPosition = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene yPosition = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene orientation = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene energy = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene inventory = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor1 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor2 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor3 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor4 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor5 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor6 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor7 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor8 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor9 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor10 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor11 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor12 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor13 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor14 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor15 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor16 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor17 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor18 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor19 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor20 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor21 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor22 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor23 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor24 = new NeuronGene(NeuronType.INPUT, params);
//        NeuronGene sensor25 = new NeuronGene(NeuronType.INPUT, params);
//
//        NeuronGene move = new NeuronGene(NeuronType.OUTPUT, params);
//        NeuronGene turnLeft = new NeuronGene(NeuronType.OUTPUT, params);
//        NeuronGene turnRight = new NeuronGene(NeuronType.OUTPUT, params);
//        NeuronGene eat = new NeuronGene(NeuronType.OUTPUT, params);
//        NeuronGene pickUp = new NeuronGene(NeuronType.OUTPUT, params);
//        NeuronGene drop = new NeuronGene(NeuronType.OUTPUT, params);
//
//        Evolver e = Evolver.createNew(params, Arrays.asList(xPosition,
//                yPosition, orientation, energy, inventory, sensor1, sensor2,
//                sensor3, sensor4, sensor5, sensor6, sensor7, sensor8, sensor9,
//                sensor10, sensor11, sensor12, sensor13, sensor14, sensor15,
//                sensor16, sensor17, sensor18, sensor19, sensor20, sensor21,
//                sensor22, sensor23, sensor24, sensor25),
//                Arrays.asList(move, turnLeft, turnRight, eat, pickUp, drop));
//        try {
//            Organism best = e.evolve();
//            System.out.println(best.toString());
//        } catch (Exception ex) {
//            System.out.println("Error evolving: " + ex);
//        }
////        test.setEvolver(params);
//
//    }
//
//    public static void testNeuralNetwork(NeuralNetwork<?> nnet, DataSet testSet) {
//        for (DataSetRow dataRow : testSet.getRows()) {
//            nnet.setInput(dataRow.getInput());
//            nnet.calculate();
//            double[] networkOutput = nnet.getOutput();
//            System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
//            System.out.println(" Output: " + Arrays.toString(networkOutput));
//        }
//    }
}
