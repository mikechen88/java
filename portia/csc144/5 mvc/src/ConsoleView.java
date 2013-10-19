/**
 * Displays the data of the model in the console
 * @author CSC 143
 *
 */
public class ConsoleView implements View {

	public void update(ModelOfSquares m) {
		System.out.println("Number of squares = " + 
	        m.getSquares().size());
	}

}
