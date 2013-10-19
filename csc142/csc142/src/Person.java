/*
 * a simple model of a person
 * @author csc 142
 * @version 1.0
 */

public class Person {
	
	//a person is defined by an age and a name
	private int age;
	private String name;
	
	/**
	 * constructs a person object given a name and an age
	 * @param n   the name of the person
	 * @param a    the age of the person
	 */
	public Person(String n, int a) {
		name = n;
		age = a;
	}
	
	
	/**
	 * prints the name and the age of the person
	 * 
	 */
	public void speak() {
		System.out.println("My name is " + name + ". My age is "
				+ age + ".");
	}
}
