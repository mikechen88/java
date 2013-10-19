package com.example.locationdb;

import java.util.ArrayList;

/*
 * This stores the information for each patient stored in the database
 */
public class Person
{
	private int id;
	private String firstName;
	private String lastName;
	
	public Person(int id, String firstName, String lastName)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getName()
	{
		return firstName + " " + lastName;
	}
}
