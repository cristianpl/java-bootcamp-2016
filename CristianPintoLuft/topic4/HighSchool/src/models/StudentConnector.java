package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cristianpintoluft.mysql.Student;

public class StudentConnector {
	static Statement statement;
	static Connection connection;
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
	
	public static ArrayList<Student> select(String sql) throws SQLException
	{
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				int registrationNumber = resultSet.getInt("registrationNumber");
				int dni = resultSet.getInt("dni");
				Student student = new Student(dni, registrationNumber);
				students.add(student);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
		return students;
	}

	public static ArrayList<Student> selectAllData() {
		ArrayList<Student> students = new ArrayList<Student>();
		try
		{
			connect();
			String sql = "select * from student s inner join person p on s.dni = p.dni "
					+ "order by lastName;";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				int registrationNumber = resultSet.getInt("registrationNumber");
				int dni = resultSet.getInt("dni");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Date dateOfBirth = resultSet.getDate("dateOfBirth");
				Student student = new Student(dni, firstName, lastName, dateOfBirth, registrationNumber);
				students.add(student);
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return students;
	}
}
