/**
 * A Fancy car is a car with a top speed
 * 
 * @author open
 * 
 */
public class FancyCar extends Car {
	private int topSpeed;

	/**
	 * Creates a FancyCar given its weight, make and topSpeed
	 * 
	 * @param weight
	 * @param make
	 * @param topSpeed
	 */

	public FancyCar(int weight, String make, int topSpeed,Person owner) {
		super(weight, make,owner);
		this.topSpeed = topSpeed;
	}

	/**
	 * returns true if obj is equal to this FancyCar
	 * 
	 */
	public boolean equals(Object obj) {
		// if (obj instanceof FancyCar) {

		// use getClass to check the dynamic types
		// since FancyCar is not inherited, instanceof would work here.
		if (obj != null && obj.getClass() == this.getClass()) {
			FancyCar f = (FancyCar) obj;
			return super.equals(f) && topSpeed == f.topSpeed;
		} else {
			return false;
		}
	}

}
