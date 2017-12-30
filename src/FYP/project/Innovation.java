package FYP.project;

import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Chris
 */
public class Innovation {

    private Integer inv;

    public Innovation() {
        inv = 0;
    }

    public int getInv() {
        this.load();
        return inv;
    }

    public void setInv(Integer x) {
        inv = x;
        this.save();
    }
    
    public void incInv(){
        inv++;
        this.save();
    }

    public void save() {
        Writer w;
        try {
            w = new FileWriter("Innovation.txt");
            w.write(inv.toString());
            w.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }

    public void load() {
        try (BufferedReader r = new BufferedReader(new FileReader("Innovation.txt"))) {
            String line = r.readLine();
            inv = Integer.parseInt(line);
            r.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e);
        }
    }
}
