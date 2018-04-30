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
    private Integer actType;

    public Neuron() {
        inputs = new ArrayList<>();
        value = 0.0;
        id = "temp";
        isCalculated = false;
        recurrentValueAvailable = true;
        actType = 3;
    }

    public Neuron(String s, int aType) {
        inputs = new ArrayList<>();
        value = 0.0;
        id = s;
        isCalculated = false;
        recurrentValueAvailable = true;
        actType = aType;
    }

    public String getName() {
        return id;
    }

    public void resetInputs() {
        inputs.clear();
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void printValue() {
        System.out.println(value);
    }

    public double getValue() {
        return value;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }
    
    public double calculateValue() {
        if (!inputs.isEmpty()) {
            inputs.stream().forEach((a) -> {
                value += (a.getWeight() * a.getInput().getValue());
            });
        }
        isCalculated = true;
        switch (actType) {
            case 0:
                break;
            case 1:
                value = this.calculateBinaryStepValue();
                break;
            case 2:
                value = this.calculateSigValue();
                break;
            case 3:
                value = this.calculateModifiedSigValue();
                break;
            case 4:
                value = this.calculateTanhValue();
                break;
            case 5:
                value = this.calculateArcTanValue();
                break;
            case 6:
                value = this.calculateReLuValue();
                break;
            case 7:
                value = this.calculateSoftPlusValue();
                break;
        }
        return value;
    }

    public double calculateBinaryStepValue() {
        double val = value;
        if (val >= 0) {
            val = 1;
        } else {
            val = 0;
        }
        return val;
    }

    public double calculateSigValue() {
        double val = value;
        value = 1 / (1 + Math.exp(-value));
        return value;
    }

    public double calculateModifiedSigValue() {
        double val = value;
        val = 1 / (1 + Math.exp(-val * 4.9));
        return val;
    }

    public double calculateTanhValue() {
        double val = value;
        val = (2 / (1 + Math.exp(-val * 2))) - 1;
        return val;
    }

    public double calculateArcTanValue() {
        double val = value;
        val = Math.atan(val);
        return val;
    }

    public double calculateReLuValue() {
        double val = value;
        if (val < 0) {
            val = 0;
        }
        return val;
    }

    public double calculateSoftPlusValue() {
        double val = value;
        val = Math.log(1 + Math.exp(val));
        return val;
    }

    public void addInput(Axon axon) {
        inputs.add(axon);
    }

    public void printInputs() {
        inputs.stream()
                .forEach((a) -> System.out.println(a.getInput().getValue()
                + ", " + a.getWeight()));
    }

    public List<Axon> getInputs() {
        return inputs;
    }

    public void setIsCalculated(boolean b) {
        isCalculated = b;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public boolean getRecurrentValueAvailable() {
        return recurrentValueAvailable;
    }

    public void setRecurrentValueAvailable(boolean b) {
        recurrentValueAvailable = b;
    }
}
