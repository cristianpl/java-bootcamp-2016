package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import com.cristianpintoluft.mysql.Schedule;

public class ScheduleConnector {
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

	public static ArrayList<Schedule> selectByCourse(String sql) {
		ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		try {
			connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int scheduleNumber = resultSet.getInt("scheduleNumber");
				int courseNumber = resultSet.getInt("courseNumber");
				String dayOfTheWeek = resultSet.getString("dayOfTheWeek");
				Time startingTime = resultSet.getTime("startingTime");
				Time endingTime = resultSet.getTime("endingTime");
				Schedule schedule = new Schedule(scheduleNumber, courseNumber, 
						dayOfTheWeek, startingTime, endingTime);
				schedules.add(schedule);
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedules;
	}

	public static boolean insert(String sql) throws ClassNotFoundException {
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
}
