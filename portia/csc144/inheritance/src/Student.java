import java.text.DecimalFormat;

/**
 * A student is a person with a gpa
 * 
 * @author CSC 143
 * 
 */
public class Student extends Person {
	double gpa;

	// for the output
	// don't declare the DecimalFormat object in speak,
	// it would be created every time speak is called-> inefficient
	// The decimalFormat object may be shared between all
	// student objects -> make it static;
	private static DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * creates a student given a name, age and gpa
	 * 
	 * @param name
	 *            the name of student
	 * @param age
	 *            the age of the student
	 * @param gpa
	 *            the gpa of the student
	 */
	public Student(String name, int age, double gpa) {
		super(name, age);
		// super has to be the first instruction in the construction

		this.gpa = gpa;
	}

	/**
	 * returns a string giving the name, age and gpa of the student
	 */

	public String speak() {
		// overrides the speak method from Person
		// two used of the keyword super
		// super(...) is used to call the superclass constructor
		// -> only in the class constructor and it must be the first instruction
		// in that constructor

		// super.m() is used to call the method m() in the superclass
		// -> can be used anywhere

		// another way to round to 2 decimalplaces
		double gpa2 = Math.round(gpa * 100) / 1000;
		// if gpa is 3.567
		// then gpa*100->356.7
		// Math.round( 356.7) -> 357
		// 358/100.0 -> 3.57
		return super.speak() + ",  gpa= " + df.format(gpa);
	}

	public double getGpa() {
		return gpa;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + ",  gpa= " + df.format(gpa);
	}
}
