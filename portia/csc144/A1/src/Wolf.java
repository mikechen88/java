// From the UW
//
// Stone objects are displayed as S and always stay put.
// They always pick ROCK in the Rock-Paper-Scissors game.
//
import java.awt.Color;

public class Wolf extends Critter {
	String str = ".";
	String name = "WOLF";
	int dir = EAST;

	Color color=Color.RED;
	public int fight(String opponent) {
		if (opponent.equals("B") || opponent.equals("T")) {
			name = "ROCK";
			color=Color.DARK_GRAY;
			return ROCK;
		} else if (opponent.equals("L")) {
			name = "SCISSORS";
			color=Color.GREEN;
			return SCISSORS;
		} else if (opponent.equals("S")) {
			name = "PAPER";
			color=Color.BLUE;
			return PAPER;
		} else
			color=Color.MAGENTA;
			name = "BEAR";
		return ROCK; // good ol' rock... nothing beats that!
	}

	public Color getColor() {
		return color; // gray
	}

	public int getMove(CritterInfo info) {

		if (!info.getNeighbor(SOUTH).equals(".")) {
			str = info.getNeighbor(SOUTH);
			dir = SOUTH;

		} else if (!info.getNeighbor(WEST).equals(".")) {
			str = info.getNeighbor(WEST);
			dir = WEST;

		} else if (!info.getNeighbor(NORTH).equals(".")) {
			dir = NORTH;
			str = info.getNeighbor(NORTH);

		} else if (!info.getNeighbor(EAST).equals(".")) {
			str = info.getNeighbor(EAST);
			dir = EAST;

		}
		return dir;

	}

	public String toString() {
		return name; // displays stone as S
	}

}
