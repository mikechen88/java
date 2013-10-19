// Adapted from the UW
// Defines the base class extended by any animal that participates in the critter
// safari simulation.
//
import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public abstract class Critter implements CritterConstants {
	
	private Point location;
	
	protected static Random rand = new Random();

	// Constructs critter at a random location
	public Critter() {
		int x = rand.nextInt(WIDTH);
		int y = rand.nextInt(HEIGHT);
		this.location = new Point(x, y);
	}

	// Returns the location of the critter
	public Point getLocation() {
		return location;
	}

	// methods to be implemented
	public abstract int fight(String opponent);

	public abstract Color getColor();

	public abstract int getMove(CritterInfo info);

	public abstract String toString();

}
