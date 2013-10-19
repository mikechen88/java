// Adapted from the UW
// Represents the model of all critters in the simulation.
//
import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.*;
import java.util.*;

public class CritterModel extends Observable implements CritterConstants {
	// class constants
	private static final int LION_MAX = 10;

	// fields
	private Critter[][] grid;

	private String[][] display;

	private Color[][] colorDisplay;

	private Random rand;

	private List<Critter> critterList;

	private SortedMap<String, Integer> countMap;

	// Constructs a new model of the given size.
	public CritterModel() {
		// check for invalid model size
		if (HEIGHT <= 0 || WIDTH <= 0) {
			throw new IllegalArgumentException();
		}

		this.grid = new Critter[WIDTH][HEIGHT];
		this.display = new String[WIDTH][HEIGHT];
		this.colorDisplay = new Color[WIDTH][HEIGHT];
		updateDisplay();
		rand = new Random();

		// initialize various data structures
		critterList = new ArrayList<Critter>();
		countMap = new TreeMap<String, Integer>();
	}

	// Adds the given number of critters of the given type to the simulation.
	public void add(int number, Class<? extends Critter> critterClass) {
		try {
			// call private helper add method many times
			for (int i = 0; i < number; i++) {
				add(critterClass);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		}

		updateDisplay();
	}

	// Returns the color that should be displayed on the given (x, y) location,
	// or black if nothing is there.
	public Color getColor(int x, int y) {
		return colorDisplay[x][y];
	}

	// Returns a set of [class name, count] entry pairs in this model.
	public Set<Map.Entry<String, Integer>> getCounts() {
		return Collections.unmodifiableSet(countMap.entrySet());
	}

	// Returns the String of text to display at the given (x, y) location.
	public String getString(int x, int y) {
		return display[x][y];
	}

	// Moves the position of all critters and handles collisions.
	public synchronized void update() {
		// reorder the list to be fair about move/collision order
		Collections.shuffle(critterList);

		// move each critter to its new position
		for (int i = 0; i < critterList.size(); i++) {
			Critter critter1 = critterList.get(i);

			// move the critter
			Point location = critter1.getLocation();
			grid[location.x][location.y] = null;
			int oldX = location.x;
			int oldY = location.y;
			int move = critter1
					.getMove(new CritterInfoImpl(new Point(location)));
			//if (critter1 instanceof Tiger) move+=5;
			
				movePoint(location, move);
		
			Critter critter2 = grid[location.x][location.y];
			Critter winner = critter1;

			if (critter2 != null) {
				// square is already occupied; play rock-paper-scissors
				winner = playGame(critter1, critter2);
				Critter loser = (winner == critter1) ? critter2 : critter1;
				// Log any fight with a wolf
				if (critter1.toString().equals("WOLF")
						|| critter2.toString().equals("WOLF")) {
					System.out.println(loser + " lost to " + winner);
				}
				int indexToRemove = (winner == critter1) ? critterList
						.indexOf(critter2) : i;
				critterList.remove(indexToRemove);
				if (indexToRemove <= i) {
					i--; // so we won't skip a critter by mistake
				}

				// decrement class counter for the losing critter
				String className = loser.getClass().getName();
				countMap.put(className, countMap.get(className) - 1);
			}
			grid[location.x][location.y] = winner;
			display[oldX][oldY] = ".";
			display[location.x][location.y] = winner.toString();

		}

		updateDisplay();
	}

	// Adds a single instance of the given type to this model.
	// If the critter's constructor needs any parameters, gives random values.
	private void add(Class<? extends Critter> critterClass)
			throws IllegalAccessException, InvocationTargetException,
			InstantiationException {
		if (critterList.size() >= WIDTH * HEIGHT) {
			throw new RuntimeException("adding too many critters");
		}

		// create critter
		Constructor<? extends Critter> ctor = getConstructor(critterClass);
		Critter critter;
		Point location;
		do {
			Object[] params = createRandomParameters(ctor);
			critter = ctor.newInstance(params);
			location = critter.getLocation();
		} while (grid[location.x][location.y] != null);
		critterList.add(critter);

		// place critter on board
		grid[location.x][location.y] = critter;

		// count # of critters
		String className = critterClass.getName();
		if (!countMap.containsKey(className)) {
			countMap.put(className, 1);
		} else {
			countMap.put(className, countMap.get(className) + 1);
		}
	}

	// Gets and returns the constructor for the given class by reflection.
	private Constructor<? extends Critter> getConstructor(
			Class<? extends Critter> critterClass) {
		Constructor<? extends Critter>[] ctors = (Constructor<? extends Critter>[]) critterClass
				.getConstructors();
		if (ctors.length != 1) {
			throw new RuntimeException("wrong number of constructors for "
					+ critterClass);
		}
		return ctors[0];
	}

	// Fills and returns an array of random values of the proper types
	// for the given constructor.
	private Object[] createRandomParameters(Constructor<? extends Critter> ctor) {
		Class[] paramTypes = ctor.getParameterTypes();
		Object[] params = new Object[paramTypes.length];

		// build random parameters
		for (int j = 0; j < params.length; j++) {
			if (paramTypes[j] == Integer.TYPE) {
				params[j] = new Integer(rand.nextInt(LION_MAX) + 1);
			} else if (paramTypes[j] == Color.class) {
				params[j] = randomColor();
			} else {
				throw new RuntimeException(
						"unknown constructor parameter type: " + paramTypes[j]);
			}
		}
		return params;
	}

	// Translates a point's coordinates 1 unit in a particular direction.
	private Point movePoint(Point p, int direction) {
		if (direction == Critter.NORTH) {
			p.y = (p.y + HEIGHT - 1) % HEIGHT;
		} else if (direction == Critter.SOUTH) {
			p.y = (p.y + 1) % HEIGHT;
		} else if (direction == Critter.EAST) {
			p.x = (p.x + 1) % WIDTH;
		} else if (direction == Critter.WEST) {
			p.x = (p.x + WIDTH - 1) % WIDTH;
		} else if (direction != Critter.CENTER) {
			throw new RuntimeException("Illegal direction");
		}
		return p;
	}

	// Plays rock-paper-scissors between the given two critters.
	// Returns which critter won the game. The other must die!
	private Critter playGame(Critter critter1, Critter critter2) {
		int weapon1 = critter1.fight(critter2.toString());
		verifyWeapon(weapon1);

		int weapon2 = critter2.fight(critter1.toString());
		verifyWeapon(weapon2);

		if (weapon1 == Critter.ROCK && weapon2 == Critter.SCISSORS
				|| weapon1 == Critter.SCISSORS && weapon2 == Critter.PAPER
				|| weapon1 == Critter.PAPER && weapon2 == Critter.ROCK) {
			// playor 1 wins
			return critter1;
		} else if (weapon1 == weapon2) {
			// tie
			return Math.random() < 0.5 ? critter1 : critter2;
		} else {
			// player 2 wins
			return critter2;
		}
	}

	// Returns a random color.
	private Color randomColor() {
		double r = Math.random();
		if (r < 0.333) {
			return Color.YELLOW;
		} else if (r < 0.667) {
			return Color.GREEN;
		} else {
			return Color.BLUE;
		}
	}

	// Updates the internal string array representing the text to display.
	private void updateDisplay() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (grid[i][j] == null) {
					display[i][j] = ".";
					colorDisplay[i][j] = Color.BLACK;
				} else {
					display[i][j] = grid[i][j].toString();
					colorDisplay[i][j] = grid[i][j].getColor();
				}
			}
		}

		setChanged();
		notifyObservers();
	}

	// Throws an exception if the given weapon is not ROCK, PAPER, or SCISSORS.
	private void verifyWeapon(int weapon) {
		if (weapon != Critter.ROCK && weapon != Critter.PAPER
				&& weapon != Critter.SCISSORS) {
			throw new IllegalArgumentException("Invalid weapon for fight:\n"
					+ "must be one of ROCK, PAPER, or SCISSORS: " + weapon);
		}
	}

	// Used to query a critter's state (position, neighbors, etc).
	// See CritterInfo.java for documentation of methods.
	private class CritterInfoImpl implements CritterInfo {
		private Point location;

		public CritterInfoImpl(Point location) {
			this.location = location;
		}

		public String getNeighbor(int direction) {
			Point other = new Point(location);
			movePoint(other, direction);
			return display[other.x][other.y];
		}

		// For debugging; dumps the state of this object as text.
		public String toString() {
	
			return "Info{location=(" + location.x + ", " + location.y
					+ "),WIDTH=" + WIDTH + ",height=" + HEIGHT
					+ ",neighbors=[N=" + getNeighbor(Critter.NORTH) + ",S="
					+ getNeighbor(Critter.SOUTH) + ",W="
					+ getNeighbor(Critter.WEST) + ",E="
					+ getNeighbor(Critter.EAST) + ",C="
					+ getNeighbor(Critter.CENTER) + "]}";
		}
	}
}
