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

    public void createWindow(World m, int s) {
        mat = m;

        JPanel p = new JPanel(new FlowLayout());

        Grid grid = new Grid(m, s);
        int mSize = mat.getSize();
        int rep = mSize * (s + 1);
        int dim = rep + 20;
        grid.setPreferredSize(new Dimension(dim, dim));

        p.add(grid);
        JScrollPane scroll = new JScrollPane(p);
        add(scroll);

        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setBackground(Color.BLACK);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);
    }
}

class Grid extends JPanel {

    private static final long serialVersionUID = 03;
    private final int size;
    private final World gMat;

    public Grid(World m, int s) {
        size = s;
        gMat = m;
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
    }
}
