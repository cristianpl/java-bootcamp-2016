package com.cristianpintoluft.mysql;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import models.CourseConnector;
import models.EnrollmentConnector;
import models.StudentConnector;

public class HighSchoolTester {

	@Test
	public void insertThreeTeachers() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse("1990-01-01");
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		for (int i = 1; i < 4; i++) {
			Teacher teacher = new Teacher(i, "Teacher" + i, "Last Name" + 
					i, sqlDate);
			assertEquals(true, teacher.save());
		}
	}

	@Test
	public void countTeachers() {
		Teacher teacher = new Teacher();
		assertEquals(3, teacher.countAll());
	}

	@Test
	public void insertFiveCourses() {
		for (int i = 1; i < 6; i++) {
			Course course = new Course("Course" + i, 1);
			assertEquals(true, course.save());
		}
	}

	@Test
	public void insertTwentyStudents() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = formatter.parse("2000-05-03");
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

		for (int i = 1; i < 21; i++) {
			Student student = new Student(i + 100, "Student Name " + i, 
					"Student Lastname " + i, sqlDate, i);
			assertEquals(true, student.save());
		}
	}

	@Test
	public void assignStudentsToCourses() throws SQLException {
		// old way now improved
		/**
		 * for (int i = 1; i < 6; i++) { Course course = new Course();
		 * course.load(i); for (int j = 1; j < 21; j++) { Student student = new
		 * Student(); student.load(i);
		 * 
		 * Enrollment enrollment = new Enrollment(i, j); assertEquals(true,
		 * enrollment.save()); } }
		 **/
		String selectCourses = "select * from course;";
		ArrayList<Course> courses = CourseConnector.select(selectCourses);
		String selectStudents = "select * from student;";
		ArrayList<Student> students = StudentConnector.select(selectStudents);
		for (Course c : courses) {
			for (Student s : students) {
				Enrollment enrollment = new Enrollment(c.courseNumber, 
						s.registrationNumber);
				assertEquals(true, enrollment.save());
			}
		}
	}

	@Test
	public void listStudentsAndTeachersOfACourse() {
		Course course = new Course("Course1", 1);
		Teacher teacher = new Teacher();
		teacher.load(course.dniTeacher);

		System.out.println("Course: " + course.name);
		System.out.println("Teacher: " + teacher.lastName + ", " + 
				teacher.firstName);
		System.out.println("Students: ");

		ArrayList<Student> students = StudentConnector.selectAllData();
		for (Student s : students) {
			System.out.println("        " + s.lastName + ", " + s.firstName);
		}
	}

	@Test
	public void listAllTheEnrollments() throws SQLException {
		String sql = "select * from enrollment;";
		ArrayList<Enrollment> enrollments = EnrollmentConnector.select(sql);
		System.out.println("Enrollments: ");
		for (Enrollment e : enrollments) {
			System.out.println("        Course number: " + e.courseNumber + ", " 
					+ "registration number: " + e.registrationNumber);
		}
	}

	@Test
	public void assignNotesToStudentsOfACourse() {
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		enrollments = EnrollmentConnector.selectByCourse(1);
		Random rand = new Random();

		for (Enrollment e : enrollments) {
			e.firstNote = rand.nextDouble();
			e.secondNote = rand.nextDouble();
			e.thirdNote = rand.nextDouble();
			e.finalNote = (e.firstNote + e.secondNote + e.thirdNote) / 3;
			assertEquals(true, e.updateNotes());
		}
	}
	
	@Test
	public void insertSchedules() throws ParseException {
		//Schedules from course 1
		
		Time st1 = Time.valueOf("5:00:00");
		Time et1 = Time.valueOf("7:00:00");
		Schedule s1 = new Schedule(1, "Monday", st1 , et1);
		Schedule s2 = new Schedule(1, "Wednesday", st1 , et1);
		Schedule s3 = new Schedule(1, "Friday", st1 , et1);
		
		assertEquals(true, s1.save());
		assertEquals(true, s2.save());
		assertEquals(true, s3.save());
		
		//Schedules from course 2
		
		Time st2 = Time.valueOf("3:00:00");
		Time et2 = Time.valueOf("5:00:00");
		Schedule s4 = new Schedule(2, "Tuesday", st2 , et2);
		Schedule s5 = new Schedule(2, "Thursday", st2 , et2);
		Schedule s6 = new Schedule(2, "Friday", st2 , et2);
		
		assertEquals(true, s4.save());
		assertEquals(true, s5.save());
		assertEquals(true, s6.save());
	}
	
	@Test
	public void listTeacherCoursesAndSchedules(){
		//selecting the teacher with dni = 1
		Teacher teacher = new Teacher();
		teacher.load(1);
		ArrayList<Course> courses = teacher.getAssignedCourses();
		
		System.out.println("Teacher: " + teacher.lastName + ", " + teacher.firstName);
		System.out.println("Schedule: ");
		for (Course c: courses){
			ArrayList<Schedule> schedules = c.getSchedules();
			for (Schedule s: schedules){
				System.out.println("    " + s.dayOfTheWeek + " " + s.startingTime + " - " +
						s.endingTime + ": " + c.name);
			}
		}
	}
}
