import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel
{
	public SnowFlakePanel()
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

		// drawStar(g, width/2, height/2, (int) (width * 0.25));
		blizzard(g);
	}

	public void drawStar(Graphics g, int centerX, int centerY, int len) {
		if (len < getWidth() / 200) {return;} 

		for (double i = 0.0; i < 2*Math.PI; i+=Math.PI/3) {
			int radiusX = (int) (len * Math.cos(i));
			int radiusY = (int) (len * Math.sin(i));
			g.drawLine(centerX, centerY, centerX + radiusX, centerY + radiusY);
			drawStar(g, centerX + radiusX, centerY + radiusY, len/3);
		}
	}

	public void drawSnowflake(int centerX, int centerY, int len) {
		if (len < 0.2) {return;} 

		drawStar(getGraphics(), centerX, centerY, len/2);

		int radiusX = (int) (len * Math.cos(Math.PI/3));
		int radiusY = (int) (len * Math.sin(Math.PI/3));
		int[] sixCentersX = {centerX - radiusX, centerX - radiusX, centerX - radiusX * 2, centerX - radiusX * 2, centerX + radiusX, centerX + radiusX};
		int[] sixCentersY = {centerY - radiusY, centerY + radiusY, centerY, centerY, centerY - radiusY, centerY + radiusX};

		for (int i = 0; i < sixCentersX.length; i++){
			drawSnowflake(sixCentersX[i], sixCentersY[i], len/2);
		}
		
	}

	public void blizzard(Graphics g){
		for (int i = 0; i < 15; i++) {
			Random rand = new Random();
			float red = rand.nextFloat();
			float green = rand.nextFloat();
			float blue = rand.nextFloat();
			Color randomColor = new Color(red,green,blue);
			g.setColor(randomColor);
			int centerX = (int) (Math.random() * getWidth());
			int centerY = (int) (Math.random() * getHeight());
			int len = (int) (Math.random() * getWidth()/8) + getWidth() / 200;
			drawStar(g, centerX, centerY, len);
		}
	}
}

public class Snowflake
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
