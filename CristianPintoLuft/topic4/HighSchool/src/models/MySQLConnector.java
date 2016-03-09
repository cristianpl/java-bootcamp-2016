package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {
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
		boolean value = false;
		try {
			connect();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			value = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = false;
		}
		connection.close();
		return value;
	}
}
