import java.util.ArrayList;
import java.util.List;

/**
 * A SimModel is a basic simulation framework for simulations of collections of
 * objects. SimThings can be added to the model, and SimViewers can ask to be
 * notified regularly so they can display the world.
 * <p>
 * 
 * The simulation repeats the following cycle repeatedly:<br>
 * 
 * Birth - anything added to the simulation on the previous cycle are added to the world.<br>
 * 
 * Action - all SimThings are asked to perform their action() method in some
 * unpredictable order.<br>
 * 
 * View - all SimViewers are notified
 * <p>
 */
public class DrawingModle {
	// instance variables
	private ArrayList<AbstractShape> things; // List of all SimThings in this model

	// at the beginning of the current cycle.
	private ArrayList<AbstractShape> newThings; // SimThings added to this model

	// during

	// the current cycle.
	private ArrayList<DrawingView> viewers; // SimViews registered to view this

	// SimModel.
	private int cyclesToGo; // Number of cycles remaining in the

	// current simulation

	private final int sleepTime = 50; // # msec. to sleep between cycles

	/**
	 * Constructor for objects of class SimModel
	 */
	public DrawingModle() {
		// Start with no SimThings and no new ones to add
		things = new ArrayList<AbstractShape>();
		newThings = new ArrayList<AbstractShape>();
		viewers = new ArrayList<DrawingView>();
		cyclesToGo = 0;
	}

	/**
	 * Add the given SimThing to the world. (The SimThing is actually added to
	 * the newThings list until the end of the current cycle, when all newThings
	 * are added to the world at once.)
	 */
	public void add(AbstractShape t) {
		newThings.add(t);
	}

	/**
	 * Add the given SimView to the list of viewers to be notified each cycle.
	 */
	public void addView(DrawingView v) {
		viewers.add(v);
	}

	/**
	 * Return a copy of the list of Things in the simulation at the beginning of
	 * the current cycle.
	 */
	public List getThings() {
		return new ArrayList<AbstractShape>(things);
	}

	/**
	 * Run the simulation for the requested number of cycles
	 */
	public void go(int nCycles) {
		cyclesToGo = nCycles;
		runSim();
	}

	/**
	 * Run the simulation indefinitey
	 */
	public void go() {
		go(Integer.MAX_VALUE);
	}

	/**
	 * Stop the simulation
	 */
	public void stop() {
		cyclesToGo = 0;
	}

	/**
	 * Run the simulation until number of requested cycles performed, or until
	 * stopped.
	 */
	private void runSim() {
		while (cyclesToGo > 0) {
			cycle();
			cyclesToGo--;
			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) { /* ignore */
			}
		}
	}

	/**
	 * Run one simulation cycle.
	 */
	private void cycle() {
		// Add newborn things to the world, and set
		// list of newborns to empty for this cycle
		things.addAll(newThings);
		newThings.clear();

		// Perform actions for all things
		for (AbstractShape t : things) {
		
			t.action();
		}

		// Notify all viewers
		for (DrawingView v : viewers) {
			v.notify(this);
		}
	}

}
