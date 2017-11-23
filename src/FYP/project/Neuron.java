package FYP.project;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Neuron {

    private final List<Axon> inputs;
    private double value;

    public Neuron() {
        inputs = new ArrayList<>();
        value = 0.0;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        if (!inputs.isEmpty()) {
            inputs.stream().forEach((a) -> {
                value += (a.getWeight() * a.getInput().returnValue());
            });
        }
        return value;
    }

    public void printValue() {
        System.out.println(value);
    }

    public double returnValue() {
        return value;
    }

    public double getSigValue() {
        this.getValue();
        value = 1 / (1 + Math.exp(-value));
        return value;
    }

    public void addInput(Axon axon) {
        inputs.add(axon);
    }

    public void getInputs() {
        inputs.stream()
                .forEach((a) -> System.out.println(a.getInput().returnValue()
                + ", " + a.getWeight()));
    }
}