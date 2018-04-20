package FYP.project;

import java.util.*;

/**
 * * @author Chris
 */
public class Record {
    
    List<Double> values;
    String move;
    Integer ID;
    
    public Record (Integer real_ID, List<Double> inputs, String output) {
        values = new ArrayList<>(inputs);
        move = output;
        ID = real_ID;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    
}
