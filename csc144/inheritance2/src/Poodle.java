// A Poodle is a Critter that makes a noise of a Poodle

public class Poodle extends Critter implements Trainable, Sellable {
	// constructor
	public Poodle() {
		super("A poodle is a dog with a funny haircut ");
	}

	// We must override speak if we want Poodle to be instantiated
	public void speak() {
		System.out.println("I want a new haircut!");

	}

	public void rollOver() {
		System.out.println("Roll over");
	}

	public void fetch() {

	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 1000;
	}
}
