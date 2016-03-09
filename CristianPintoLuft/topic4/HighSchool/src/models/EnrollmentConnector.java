package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cristianpintoluft.mysql.Enrollment;

public class EnrollmentConnector {
	static Connection connection;
	static Statement statement;
	static ResultSet resultSet;
	
	private static void connect() throws ClassNotFoundException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/high-school?"
			              + "user=root&password=");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Enrollment> select(String sql) throws SQLException
	{
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				int registrationNumber = resultSet.getInt("registrationNumber");
				int courseNumber = resultSet.getInt("courseNumber");
				double firstNote = resultSet.getDouble("firstNote");
				double secondNote = resultSet.getDouble("secondNote");
				double thirdNote = resultSet.getDouble("thirdNote");
				double finalNote = resultSet.getDouble("finalNote");
				Enrollment enrollment = new Enrollment(courseNumber, registrationNumber, 
						firstNote, secondNote, thirdNote, finalNote);
				enrollments.add(enrollment);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
		return enrollments;
	}

	public static boolean update(String sql) {
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			return true;
		}
		catch (Exception ex){
			return false;
		}
	}

	public static ArrayList<Enrollment> selectByCourse(int courseNumber) {
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		String sql = String.format("select * from enrollment where courseNumber = %d", 
				courseNumber);
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				int registrationNumber = resultSet.getInt("registrationNumber");
				double firstNote = resultSet.getDouble("firstNote");
				double secondNote = resultSet.getDouble("secondNote");
				double thirdNote = resultSet.getDouble("thirdNote");
				double finalNote = resultSet.getDouble("finalNote");
				Enrollment enrollment = new Enrollment(courseNumber, registrationNumber, 
						firstNote, secondNote, thirdNote, finalNote);
				enrollments.add(enrollment);
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enrollments;
	}
}
