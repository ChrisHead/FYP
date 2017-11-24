package FYP.project;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Graphics;
import java.awt.Dimension;

/**
 * @author Chris
 */
public class Window extends JFrame {

    private static final long serialVersionUID = 02;
    private World mat;
    private int s;
    private Entity e;
    private final JPanel p = new JPanel(new FlowLayout());

    public void createWindow(World m, int si, Entity en) {
        mat = m;
        s = si;
        e = en;
        this.addGrid();
        JScrollPane scroll = new JScrollPane(p);
        add(scroll);
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public void addGrid() {
        p.removeAll();
        Grid grid = new Grid(mat, s, e);
        int mSize = mat.getSize();
        int rep = mSize * (s + 1);
        int dim = rep + 20;
        grid.setPreferredSize(new Dimension(dim, dim));
        p.add(grid);
        p.setBackground(Color.BLACK);

        setVisible(true);
    }

    public void reload() {
        this.addGrid();
        this.repaint();
    }
}

class Grid extends JPanel {

    private static final long serialVersionUID = 03;
    private final int size;
    private final World gMat;
    private final int eX;
    private final int eY;

    public Grid(World m, int s, Entity e) {
        size = s;
        gMat = m;
        eX = e.getX();
        eY = e.getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        int y = 10;
        for (int[] array : gMat.getMatrix()) {
            int x = 10;
            for (int type : array) {
                if (type == 0) {
                    g.setColor(Color.GREEN);
                } else if (type == 1) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.RED);
                }
                g.fillRect(x, y, size, size);
                x += (size + 1);
            }
            y += (size + 1);
        }
        int a = 9 + eX * (size + 1);
        int b = 9 + eY * (size + 1);
        g.setColor(Color.BLUE);
        g.fillOval(a, b, size + 1, size + 1);
    }
}
