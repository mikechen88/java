// Adapted from the UW
// Provides the main method for the simulation.
// 
public class CritterMain {

	public static void main(String[] args) {
		// create the simulation world
		CritterModel model = new CritterModel();

		// add the various critters to the simulation
		model.add(25, Stone.class);
		model.add(25, Bear.class);
		model.add(25, Lion.class);
		model.add(25, Tiger.class);
		model.add(25, Wolf.class);

		// run the simulation
		CritterGui gui = new CritterGui(model);
		gui.start();
	}
}
