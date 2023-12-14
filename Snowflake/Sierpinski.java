import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

class Sierpinski extends JPanel {

    public Sierpinski()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int width  = getWidth();
		int height = getHeight();

		super.paintComponent(g);
		g.setColor(Color.BLUE);

        draw(g,width/2, height/2, width/2);
	}

    public static void draw(Graphics g, int x, int y, int sideLen) {
        if (sideLen < 1) { return; }
        g.drawLine(x, y, x - sideLen, y);
        g.drawLine(x, y, x, y - sideLen);
        g.drawLine(x - sideLen, y, x, y - sideLen);

        draw(g, x - sideLen / 2, y + sideLen / 2, sideLen / 2);
        draw(g, x + sideLen / 2, y - sideLen / 2, sideLen / 2);
        draw(g, x - sideLen / 2, y - sideLen / 2, sideLen / 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Sierpinski());
		frame.pack();
		frame.setVisible(true);
    }
}
