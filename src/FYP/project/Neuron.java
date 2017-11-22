package FYP.project;

import java.util.List;

/**
 *
 * @author Chris
 */
public class Neuron {

    private final List<Axon> inputs;
    private double value;

    public Neuron(List<Axon> inputs) {
        this.inputs = inputs;
        value = 0.0;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        inputs.stream().forEach((a) -> {
            value += (a.getWeight() * a.getInput().getValue());
        });
        return value;
    }

    public double getSigValue() {
        this.getValue();
        value = 1 / (1 + Math.exp(-value));
        return value;
    }
}
