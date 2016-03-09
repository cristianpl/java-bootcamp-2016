package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cristianpintoluft.mysql.Course;

public class CourseConnector {
	static Statement statement;
	static Connection connection;
	static ResultSet resultSet;

	private static void connect() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/high-school?" + "user=root&password=");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<Course> select(String sql) throws SQLException {
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int courseNumber = resultSet.getInt("courseNumber");
				String name = resultSet.getString("name");
				int dni = resultSet.getInt("dni");
				Course course = new Course(courseNumber, name, dni);
				courses.add(course);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		connection.close();
		return courses;
	}
}
