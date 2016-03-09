package com.cristianpintoluft.mysql;
import models.EnrollmentConnector;
import models.MySQLConnector;

public class Enrollment {

	int courseNumber;
	int registrationNumber;
	double firstNote;
	double secondNote;
	double thirdNote;
	double finalNote;
	
	public Enrollment(int courseNumber, int registrationNumber) {
		this.courseNumber = courseNumber;
		this.registrationNumber = registrationNumber;
	}

	public Enrollment(int courseNumber, int registrationNumber, double firstNote,
			double secondNote, double thirdNote, double finalNote) {
		this.courseNumber = courseNumber;
		this.registrationNumber = registrationNumber;
		this.firstNote = firstNote;
		this.secondNote = secondNote;
		this.thirdNote = thirdNote;
		this.finalNote = finalNote;
	}

	public boolean save() {
		String sql = String.format("insert into Enrollment(registrationNumber,"
				+ "courseNumber) values (%d, %d)", registrationNumber, courseNumber);
		try
		{
			MySQLConnector.insert(sql);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	public boolean updateNotes() {
		String sql = String.format("update enrollment set firstNote = %.2f, " + 
				"secondNote = %.2f, thirdNote = %.2f, finalNote = %.2f where " + 
				"registrationNumber = %d and courseNumber = %d", firstNote, 
				secondNote, thirdNote, finalNote, registrationNumber, courseNumber);
		try{
			EnrollmentConnector.update(sql);
			return true;
		}
		catch (Exception ex){
			return false;
		}
	}
}
