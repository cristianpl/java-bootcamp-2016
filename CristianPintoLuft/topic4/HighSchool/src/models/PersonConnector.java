package models;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import com.cristianpintoluft.mysql.Person;

public class PersonConnector {
	static Connection connection = null;
	static Statement statement = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	
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
	
	public static boolean insert(String sql) throws ClassNotFoundException, SQLException
	{
		try {
			connect();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Person> select(String sql) throws SQLException
	{
		ArrayList<Person> persons = new ArrayList<Person>();
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				int dni = resultSet.getInt("dni");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Date sqlDate = resultSet.getDate("dateOfBirth");
				Person person = new Person(dni, firstName, lastName, sqlDate);
				persons.add(person);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
		return persons;
	}

	public int countAll() throws SQLException {
		int quantity = 0;
		try
		{
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from person;");
			while (resultSet.next())
				quantity = resultSet.getInt("count(*)");
		}
		catch (Exception e)
		{}
		connection.close();
		return quantity;
	}
}
