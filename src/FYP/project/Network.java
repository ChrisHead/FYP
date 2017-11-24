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
//    private Axon[] axon;

    public Network(Layer i, List<Layer> h, Layer o) {
        this.input = i;
        this.output = o;
        this.hidden = h;
    }

    public void runNetwork() {
//        input.getNeurons().stream().forEach((i) -> 
//            i.getSigValue());
        hidden.stream().forEach((i) -> {
            i.getNeurons().stream()
                    .forEach((n) -> n.getSigValue());
        });
        output.getNeurons().stream()
                .forEach((n) -> System.out.println(n.getValue()));
    }

    public void fullyConnect() {
        for (Neuron hn : hidden.get(0).getNeurons()) {
            for (Neuron in : input.getNeurons()) {
                Axon axon = new Axon(in, hn, Math.random());
            }
        }
        for (Neuron hn : hidden.get(1).getNeurons()) {
            for (Neuron hn1 : hidden.get(0).getNeurons()) {
                Axon axon = new Axon(hn1, hn, Math.random());
            }
        }
        for (Neuron on : output.getNeurons()) {
            for (Neuron hn : hidden.get(1).getNeurons()) {
                Axon axon = new Axon(hn, on, Math.random());
            }
        }
    }
    
    public void noHiddenConnect() {
        for (Neuron on : output.getNeurons()) {
            for (Neuron in : input.getNeurons()) {
                Axon axon = new Axon(in, on, Math.random());
                System.out.println(axon.getWeight());
            }
        }
    }

    public void printSize() {
        System.out.println(hidden.get(0).getNeurons().size());
    }
    
    public void resetNetwork(){
        
    }
    
}
