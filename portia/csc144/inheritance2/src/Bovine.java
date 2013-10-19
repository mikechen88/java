// A Bovine is a Critter that enjoys hay as its favorite dish

public abstract class Bovine extends Critter implements Sellable{
	// Constructor
	public Bovine(String s) {
		super(s + "Hay is my favorite food");

	}
	// We don't override speak.
	// Bovine is abstract as well.
}
