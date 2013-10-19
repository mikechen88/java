import javax.swing.*;

public class TestCritter {
	// Write description of and sound produced by Critter c.
	public static void writeInfo(Critter c) {
		System.out.println(c);
		c.speak(); // compiles because speak is in Critter
		// at run time, speak of one of the derived classes
		// (Poodle, Cow, Buffalo ) is called.
		if (c instanceof Trainable) {
			Trainable t = (Trainable) c;

			t.fetch();
			t.rollOver();
		}
		if (c instanceof Sellable) {
			Sellable s = (Sellable) c;

			// in printf, % stands for a placeholder
			// .2f is the format to use the print the value
			// -> floating point number formatted to 2 decimal places.
			System.out.printf("Price = $%.2f\n", s.getPrice());
		}
	}

	// Test critter classes
	public static void main(String[] args) {
		Critter c; // ok ,since an abstract class defines a type
		// but an abstract class can't be instantiated

		// Create a poodle, a buffalo, a cow
		Critter[] a = { new Poodle(), new Cow(), new Buffalo() };
		// Call writeInfo for each critter
		for (Critter ct : a) {
			writeInfo(ct);
			System.out.println();
		}
	}
}
