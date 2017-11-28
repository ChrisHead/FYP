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
        this.updateNeuron();
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

    private void updateNeuron() {
        output.addInput(this);
    }

}
