package FYP.project;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Layer {
    private List<Neuron> neurons;
    private String id;
    
    public Layer(List<Neuron> n) {
        neurons = new ArrayList<>();
        this.neurons = n;
    }
    
    public Layer(String i, List<Neuron> n) {
        neurons = new ArrayList<>();
        this.neurons = n;
        id = i;
    }
    
    public Layer(int size) {
        
    }
    
    public List<Neuron> getNeurons() {
        return neurons;
    }
    
    public String getId(){
        return id;
    }
    
    public void LayerZeroInputs(){
        for (Neuron n : this.getNeurons()){
            n.resetInputs();
            n.setValue(0.0);
        }
    }
    
    public void removeNeuron(Neuron n) {
        neurons.remove(n);
    }
}
