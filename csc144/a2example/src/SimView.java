/**
 * Basic interface for viewers of a SimModel. A SimView can be added to a
 * SimModel, which will periodically notify the SimView so it can update its
 * display.
 */

public interface SimView {

	/**
	 * Notify this SimView so it can update its display of the SimModel.
	 * 
	 * @param m
	 *            SimModel notifying this SimViewer
	 */

	public void notify(SimModel m);

}
