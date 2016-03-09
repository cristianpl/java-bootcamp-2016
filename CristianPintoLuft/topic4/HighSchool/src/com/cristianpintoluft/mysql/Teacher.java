package com.cristianpintoluft.mysql;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CourseConnector;

public class Teacher extends Person{

	public Teacher(int dni, String firstName, String lastName, Date sqlDate) {
		super(dni, firstName, lastName, sqlDate);
		// TODO Auto-generated constructor stub
	}

	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public boolean save() {
		try
		{
			super.save();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public int countAll() {
		int quantity = 0;
		try
		{
			quantity = super.countAll();
		}
		catch (Exception e)
		{}
		return quantity;
	}

	public void load(int dniTeacher) {
		super.load(dniTeacher);
	}

	public ArrayList<Course> getAssignedCourses() {
		String sql = String.format("select * from course where dni = %d", dni);
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			courses = CourseConnector.select(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
}
