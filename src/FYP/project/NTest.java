/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.Arrays;
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

    public static void main(String[] args) {
        DataSet trainingSet = new DataSet(2, 1);
        trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
        trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);
        myMlPerceptron.learn(trainingSet);
        System.out.println("Testing trained neural network");
        testNeuralNetwork(myMlPerceptron, trainingSet);
        myMlPerceptron.save("myMlPerceptron.nnet");
//        NeuralNetwork<?> loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");
//        System.out.println("Testing loaded neural network");
//        testNeuralNetwork(loadedMlPerceptron, trainingSet);
    }

        public static void testNeuralNetwork(NeuralNetwork<?> nnet, DataSet testSet) {
            for (DataSetRow dataRow : testSet.getRows()) {
                nnet.setInput(dataRow.getInput());
                nnet.calculate();
                double[] networkOutput = nnet.getOutput();
                System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
                System.out.println(" Output: " + Arrays.toString(networkOutput));
            }
        }
    }
