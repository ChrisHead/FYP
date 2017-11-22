/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

/**
 *
 * @author Chris
 */
public class Axon {

    private final Neuron input;
    private final Neuron output;
    private double weight;

    public Axon(Neuron input, Neuron output, double weight) {
        this.input = input;
        this.output = output;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Neuron getInput() {
        return this.input;
    }

    public Neuron getOutput() {
        return this.output;
    }
}
