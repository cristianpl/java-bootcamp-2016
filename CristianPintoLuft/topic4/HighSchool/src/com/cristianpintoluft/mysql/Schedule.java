package com.cristianpintoluft.mysql;

import java.sql.Time;

import models.ScheduleConnector;

public class Schedule {
	int scheduleNumber;
	int courseNumber;
	String dayOfTheWeek;
	Time startingTime;
	Time endingTime;

	public Schedule(int scheduleNumber, int courseNumber, String dayOfTheWeek, 
			Time startingTime, Time endingTime) {
		this.scheduleNumber = scheduleNumber;
		this.courseNumber = courseNumber;
		this.dayOfTheWeek = dayOfTheWeek;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
	}
	
	public Schedule(int courseNumber, String dayOfTheWeek, Time st1, 
			Time et1) {
		this.courseNumber = courseNumber;
		this.dayOfTheWeek = dayOfTheWeek;
		this.startingTime = st1;
		this.endingTime = et1;
	}

	public boolean save() {
		//I need to fix the String sql creation in order to allow the insertion
		//of Time objects in a proper way. Despite of this, the insertion of a
		//new row through the MySQL console is working fine.
		String sql = String.format("insert into schedule (courseNumber, dayOfTheWeek, "
				+ "startingTime, endingTime) values (%d, %s, %s, %s)", 
				courseNumber, dayOfTheWeek, startingTime, endingTime);
		try{
			ScheduleConnector.insert(sql);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
}
