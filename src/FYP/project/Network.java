package FYP.project;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Chris
 */
public class Network {

    private final Layer input;
    private final Layer output;
    private final List<Layer> hidden;
    private List<Axon> axon;

    public Network(Layer i, List<Layer> h, Layer o) {
        this.input = i;
        this.output = o;
        this.hidden = h;
    }

    public void runNetwork() {
        hidden.stream().forEach((i) -> {
            i.getNeurons().stream()
                    .forEach((n) -> n.getSigValue());
        });
        output.getNeurons().stream()
                .forEach((n) -> n.getSigValue());
        //Print Values
        input.getNeurons().stream()
                .forEach((n) -> System.out.println(n.returnValue()));
        output.getNeurons().stream()
                .forEach((n) -> System.out.println(n.returnValue()));
    }

    public void fullyConnect() {
        axon = new ArrayList<>();
        for (Neuron hn : hidden.get(0).getNeurons()) {
            for (Neuron in : input.getNeurons()) {
                Axon a = new Axon(in, hn, Math.random());
                axon.add(a);
            }
        }
        for (Neuron hn : hidden.get(1).getNeurons()) {
            for (Neuron hn1 : hidden.get(0).getNeurons()) {
                Axon a = new Axon(hn1, hn, Math.random());
                axon.add(a);
            }
        }
        for (Neuron on : output.getNeurons()) {
            for (Neuron hn : hidden.get(1).getNeurons()) {
                Axon a = new Axon(hn, on, Math.random());
                axon.add(a);
            }
        }
    }
    
    public List<Axon> returnAxons(){
        return axon;
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
