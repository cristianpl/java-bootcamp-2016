package com.cristianpintoluft.mysql;

import java.sql.Date;
import java.util.ArrayList;

import models.PersonConnector;

public class Person {

	int dni;
	String firstName;
	String lastName;
	Date sqlDate;
	
	public Person(){};
	
	public Person(int dni, String firstName, String lastName, Date sqlDate) {
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sqlDate = sqlDate;
	}

	public boolean save() {
		try
		{
			String sql = String.format("insert into person(dni, firstName, "
					+ "lastName, dateOfBirth) values (%d, '%s', '%s', '%s')", 
					dni, firstName, lastName, sqlDate);
			
			PersonConnector.insert(sql);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public int countAll() {
		PersonConnector personConnector = new PersonConnector();
		int quantity = 0;
		
		try
		{
			quantity = personConnector.countAll();
		}
		catch (Exception e){}
		return quantity;
	}

	public void load(int dni)
	{
		String sql = String.format("select * from person where dni = %d", dni);
		try
		{
			ArrayList<Person> persons = PersonConnector.select(sql);
			Person person = persons.get(0);
			this.dni = person.dni;
			this.firstName = person.firstName;
			this.lastName = person.lastName;
			this.sqlDate = person.sqlDate;
		}
		catch (Exception ex){}
	}
}
