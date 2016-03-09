package com.cristianpintoluft.mysql;
import java.util.ArrayList;

import models.CourseConnector;
import models.EnrollmentConnector;
import models.MySQLConnector;
import models.ScheduleConnector;

public class Course {

	int courseNumber;
	String name;
	int dniTeacher;
	ArrayList<Enrollment> enrollments;
	
	public Course(String name, int dniTeacher) {
		this.name = name;
		this.dniTeacher = dniTeacher;
	}
	
	public Course(){};

	public Course(int courseNumber, String name, int dniTeacher) {
		this.courseNumber = courseNumber;
		this.name = name;
		this.dniTeacher = dniTeacher;
	}

	public boolean save() {
		try
		{
			String sql = String.format("insert into Course(dni, name) values (+%d, '%s')",
					dniTeacher, name);
			MySQLConnector.insert(sql);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	public void load(int courseNumber) {
		String sql = String.format("select * from course where courseNumber = %d", 
				courseNumber);
		try
		{
			ArrayList<Course> courses = CourseConnector.select(sql);
			Course course = courses.get(0);
			courseNumber = course.courseNumber;
			name = course.name;
			dniTeacher = course.dniTeacher;
		}
		catch (Exception ex){}
	}

	public void loadEnrollments() {
		String sql = String.format("select * from enrollment where courseNumber = %d", courseNumber);
		try {
			enrollments = EnrollmentConnector.select(sql);
		}
		catch (Exception ex){}
	}

	public ArrayList<Schedule> getSchedules() {
		String sql = String.format("select * from schedule where courseNumber = %d", 
				courseNumber);
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		try{
			schedules = ScheduleConnector.selectByCourse(sql);
		}
		catch (Exception ex) {}
		return schedules;
	}

}
