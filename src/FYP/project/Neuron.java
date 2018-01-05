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
    private boolean isCalculated;
    private boolean recurrentValueAvailable;

    public Neuron() {
        inputs = new ArrayList<>();
        value = 0.0;
        id = "temp";
        isCalculated = false;
        recurrentValueAvailable = true;
    }
    
    public Neuron(String s) {
        inputs = new ArrayList<>();
        value = 0.0;
        id = s;
        isCalculated = false;
        recurrentValueAvailable = true;
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

    public double calculateValue() {
        if (!inputs.isEmpty()) {
            inputs.stream().forEach((a) -> {
                value += (a.getWeight() * a.getInput().getValue());
            });
        }
        isCalculated = true;
        return value;
    }

    public void printValue() {
        System.out.println(value);
    }

    public double getValue() {
        return value;
    }

    public double calculateSigValue() {
        this.calculateValue();
        value = 1 / (1 + Math.exp(-value*4.9));
        return value;
    }

    public void addInput(Axon axon) {
        inputs.add(axon);
    }

    public void printInputs() {
        inputs.stream()
                .forEach((a) -> System.out.println(a.getInput().getValue()
                + ", " + a.getWeight()));
    }
    
    public List<Axon> getInputs(){
        return inputs;
    }
    
    public void setIsCalculated(boolean b){
        isCalculated = b;
    }
    
    public boolean isCalculated() {
        return isCalculated;
    }
    
    public boolean getRecurrentValueAvailable(){
        return recurrentValueAvailable;
    }
    
    public void setRecurrentValueAvailable(boolean b) {
        recurrentValueAvailable = b;
    }
}
