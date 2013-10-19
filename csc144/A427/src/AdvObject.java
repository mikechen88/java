/*
 * File: AdvObject.java
 * --------------------
 * This file defines a class that models an object in the
 * Adventure game.
 */

import java.util.ArrayList;
import java.util.Scanner;

/* Class: AdvObject */
/**
 * This class defines an object in the Adventure game. An object is
 * characterized by the following properties:
 * 
 * <ul>
 * <li>Its name, which is the noun used to refer to the object
 * <li>Its description, which is a string giving a short description
 * <li>The room number in which the object initially lives</li>
 * 
 * The external format of the objects file is described in the assignment
 * handout. The comments on the methods exported by this class show how to use
 * the initialized data structure.
 */

public class AdvObject {

	/* Method: getName() */
	/**
	 * Returns the object name, which is the word used to refer to it.
	 * 
	 * @usage String name = obj.getName();
	 * @return The name of the object
	 */
	public String getName() {
		return name; // Replace with your code
	}

	/* Method: getDescription() */
	/**
	 * Returns the one-line description of the object. This description should
	 * start with an article, as in "a set of keys" or "an emerald the size of a
	 * plover's egg."
	 * 
	 * @usage String name = obj.getDescription();
	 * @return The description of the object
	 */
	public String getDescription() {
		return description; // Replace with your code
	}

	/* Method: getInitialLocation() */
	/**
	 * Returns the initial location of the object.
	 * 
	 * @usage int roomNumber = obj.getInitialLocation();
	 * @return The room number in which the object initially resides
	 */
	public int getInitialLocation() {
		return location; // Replace with your code
	}
	
	public void setLocation(int lo){
		this.location=lo;
	}

	/* Method: readFromFile(scan) */
	/**
	 * Reads the data for this object from the Scanner scan, which must have
	 * been opened by the caller. This method returns the object if the object
	 * initialization is successful; if there are no more objects to read,
	 * readFromFile returns null
	 * 
	 * @usage AdvObject object = AdvObject.readFromFile(scan);
	 * @param scan
	 *            A Scanner open on the objects data file
	 * @return the object if an object is successfully read; null at end of file
	 */
	public static AdvObject readFromFile(Scanner scan) {

		AdvObject o = null;
		String line;

		if (scan.hasNextLine()) {
			while (scan.hasNextLine()
					&& !(line = scan.nextLine().trim()).equals("")) {
				o = new AdvObject();
				o.name = line;
				o.description = scan.nextLine();
				o.location = scan.nextInt();
				scan.nextLine();
			}
			return o;
		} else {
			return null;
		}

	}

	public String toString() {
		String fullObject = "";
		fullObject += name;
		fullObject += description;
		fullObject += location;
		return fullObject;

	}

	/* Private instance variables */
	// Add your own instance variables here
	private String name;
	String description;
	int location;

	static ArrayList<AdvObject> objects = new ArrayList<AdvObject>();
}
