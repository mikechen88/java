/**
 * A simple class to illustrate the use of equals and clone
 * 
 * @author open
 * 
 */

public class Car implements Cloneable {
	private int weight;
	private String make;
	private Person owner;

	private boolean deepCopy;

	// true if clone returns a deep copy
	/**
	 * create a car given a weight and a make
	 * 
	 */
	public Car(int weight, String make, Person owner) {
		this.weight = weight;
		this.make = make;
		this.owner = owner;

	}

	/**
	 * returns true if obj is equal to this Car.
	 */
	public boolean equals(Object obj) {
		// overrides equals inherited from Object

		// if (obj instanceof Car) {

		// use getClass to check the dynamic types
		if (obj != null && obj.getClass() == this.getClass()) {
			Car c = (Car) obj;

			return this.weight == c.weight && this.make.equals(c.make);
		}
		return false;
	}

	/**
	 * sets the type of copy for clone
	 */
	public void setDeepCopy(boolean b) {
		deepCopy = b;
	}

	/**
	 * returns a copy of this car move out
	 * "  throws CloneNotSupportedException  " so make the class implements
	 * cloneable
	 */
	public Object clone() {
		try {
			// clone from Object returns a shallow copy
			// return super.clone();

			Car copy = (Car) super.clone();
			// make it a deep copy
			if (deepCopy) {
				copy.owner = new Person(this.owner); 
			}
			return copy;
		} catch (CloneNotSupportedException e) {
			return null;// this will never happen
		}
	}

	/**
	 * return a string representation of the car
	 */
	public String toString() {
		return "make=  " + make + ", weight =  " + weight + ",  owner = "
				+ owner;
	}

	/**
	 * sets the weight of the car to a new weight
	 */

	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * returns the owner of this car
	 */
	public Person getOwner() {
		return owner;
	}

}
