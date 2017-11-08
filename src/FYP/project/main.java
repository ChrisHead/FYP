package FYP.project;

/**
 *
 * @author Chris
 */
public class main {

    public static void main(String[] args) {
        World m = new World(10);
        Tile t = new Tile(m);
        m.printMatrix();
        m.saveWorld();
        System.out.println();
        t.foodTile(1, 1);
        t.plantTile(1, 1);
        m.printMatrix();
        m.saveWorld();
        System.out.println("Read Test:");
        m.loadWorld();
        m.printMatrix();
//
        Window win = new Window();
        win.createWindow(m, 30);
//
//        Entity e = new Entity(m);
//        e.setPosition(5, 5, 0);
//        t.foodTile(5, 4);
//        System.out.println(e.getInventory());
//        System.out.println(e.getEnergy());
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

//        e.setPosition(5, 5, 1);
//        System.out.println(e.getInventory());
//        e.setSensorSize(4);
//        e.coneSensor();
//        e.printSensor();
//        e.printSensorSize();
//        m.printSize();
//        e.printMoves();
//        e.move();
//        e.printMoves();
//        e.printLocation();
//        e.turnLeft();
//        e.turnLeft();
//        e.turnLeft();
//        e.turnLeft();
//        e.move();
//        e.printLocation();
    }
}
