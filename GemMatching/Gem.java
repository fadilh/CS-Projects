import java.awt.Font;
import java.util.Random;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{	
	private int gemPoints;
	private GemType gemType;

	public Gem() {
		Random rand = new Random();
		this.gemPoints = (int)(Math.random() * 11) * 5;
		this.gemType = GemType.values()[rand.nextInt(GemType.values().length)];
	}

	public Gem(GemType gemType, int gemPoints) {
		this.gemPoints = gemPoints;
		this.gemType = gemType;
	}

	public String toString() {
		return gemType + ": " + gemPoints;
	}

	public GemType getType() {
		return gemType;
	}

	public int getPoints() {
		return gemPoints;
	}

	public void draw(double x, double y) {
		String gemImage = "gem_" + this.gemType.toString() + ".png";
		StdDraw.picture(x, y, gemImage);
		StdDraw.text(x, y, this.gemPoints + "");
	}

	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}

	
}
