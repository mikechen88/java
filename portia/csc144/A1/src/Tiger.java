// From the UW
//
// Stone objects are displayed as S and always stay put.
// They always pick ROCK in the Rock-Paper-Scissors game.
//
import java.awt.Color;

public class Tiger extends Critter {
	boolean scissor = true;
	int[] aa = { SOUTH, WEST, NORTH, EAST };
	Color color;
	int bb = 0;
	int direction=SOUTH;

	public Tiger(Color color) {
		this.color = color;
	}

	public int fight(String opponent) {
		if (scissor) {
			scissor = false;
			return SCISSORS;
		} else {
			scissor = true;
			return PAPER;
		}

	}

	public Color getColor() {
		return this.color;
	}

	public int getMove(CritterInfo info) {
		bb++;
		if (bb == aa.length) {
			bb = 0;
		}
		direction = aa[bb];
		switch (direction) {
		case SOUTH:
			this.getLocation().y += 5;

			break;
		case WEST:
			this.getLocation().x -= 5;

			break;
		case NORTH:
			this.getLocation().y -= 5;

			break;
		case EAST:
			this.getLocation().x += 5;

			break;
		}

		return direction;

	}

	public String toString() {
		return "T"; // displays stone as T
	}
}
