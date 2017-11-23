/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYP.project;

import java.util.List;

/**
 *
 * @author Chris
 */
public class Network {

    private final Layer input;
    private final Layer output;
    private final List<Layer> hidden;

    public Network(Layer i, List<Layer> h, Layer o) {
        this.input = i;
        this.output = o;
        this.hidden = h;
    }

    public void runNetwork() {
        hidden.stream().forEach((i) -> {
            i.getNeurons().stream()
                    .forEach((n) -> n.getValue());
        });
        output.getNeurons().stream()
                .forEach((n) -> System.out.println(n.getValue()));
    }

    public void fullyConnect() {
       
    }
}

// Neuron[] n = new Neuron[20];
//        for (int i = 0; i < 11; i++) {
//            n[i] = new Neuron(); 
//        }