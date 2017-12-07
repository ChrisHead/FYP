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
    private final String id;

    public Neuron(String s) {
        inputs = new ArrayList<>();
        value = 0.0;
        id = s;
    }
    
    public String getName(){
        return id;
    }
    
    public void resetInputs(){
        inputs.clear();
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
