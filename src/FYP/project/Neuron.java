package FYP.project;

import java.util.List;

/**
 *
 * @author Chris
 */
public class Neuron {

    private final String id;
    private List<Axon> Inputs;
    private List<Axon> Outputs;
    private double value;

    public Neuron(String id, List<Axon> Inputs, List<Axon> Outputs) {
        this.id = id;
        this.Inputs = Inputs;
        this.Outputs = Outputs;
    }

    public String getID() {
        return this.id;
    }

    public void calculateValue() {
        Inputs.stream().forEach((a) -> {
            value += (a.getWeight() * a.getInput().getValue());
        });
    } 

    public double getValue() {
        return this.value;
    }
}
