package com.cristianpintoluft.mysql;
import java.sql.Date;
import java.util.ArrayList;

import models.MySQLConnector;
import models.StudentConnector;

public class Student extends Person {
	int registrationNumber;
	
	public Student(int dni, String firstName, String lastName, Date sqlDate, 
			int registrationNumber) 
	{
		super(dni, firstName, lastName, sqlDate);
		this.registrationNumber = registrationNumber;
	}

	public Student(int dni, int registrationNumber)
	{
		this.dni = dni;
		this.registrationNumber = registrationNumber;
	}
	
	public Student(){};
	
	public boolean save()
	{
		try
		{
			super.save();
			
			String sql = String.format("insert into Student(registrationNumber, dni)"
					+ "values (%d, %d)", registrationNumber, dni);
			
			MySQLConnector.insert(sql);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	public void load(int registrationNumber) {
		String sql = String.format("select * from student where registrationNumber = %d",
				registrationNumber);
		try
		{
			ArrayList<Student> students = StudentConnector.select(sql);
			Student student = students.get(0);
			this.registrationNumber = student.registrationNumber;
			this.dni = student.dni;
		}
		catch (Exception ex){}
	}
}
