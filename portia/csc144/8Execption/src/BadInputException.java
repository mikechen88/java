/**
 * A class that extends the Exception class defines a checked exception
 * 
 * @author CSC 143
 * 
 */
public class BadInputException extends Exception {
	public BadInputException() {
	}

	public BadInputException(String s) {
		super(s);
	}
}
