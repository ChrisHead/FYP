package FYP.project;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Chris
 */
public class main {

    public static void main(String[] args) {
        World m = new World(10);
        m.loadWorld();
        Tile t = new Tile(m);
        Window win = new Window();
        win.createWindow(m, 30);
        
        
        Entity e = new Entity(m);
        e.setPosition(4, 4, 3);
        e.coneSensor();
        System.out.println(Arrays.toString(e.getSensor()));
//        for (ArrayList<Integer> array : e.getSensor()){
//            int x = array.get(0);
//            int y = array.get(1);
//            System.out.println("X: " + x + " Y: " + y + " V: " + m.getMatrix()[y][x]);
//        }
//

//
//        Scanner scanner = new Scanner(System.in);
//        String command = scanner.nextLine();
//        System.out.println(command);
//        if (command.equals("test")) {
//
//            e.move();
//            e.eat(t);
//            System.out.println(e.getEnergy());
//            win.repaint();
//        }

    }
}
