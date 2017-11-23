/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Layer {
    private List<Neuron> neurons;
    
    public Layer(List<Neuron> n) {
        neurons = new ArrayList<>();
        this.neurons = n;
    }
    
    public List<Neuron> getNeurons() {
        return neurons;
    }
}
