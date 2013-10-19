// Definition of the Critter class
// Must be abstract since we can't define a concrete Critter

public abstract class Critter {
	// description of this Critter
	private String description;

	// Construct Critter with description s.
	public Critter(String s) {
		description=s;
	}

	// return Critter description
	public String toString() {
		return description;
	}

	// Write noise made by this Critter to the console
	// abstract since we can't say what noise a Critter makes
	public abstract void speak();
}
