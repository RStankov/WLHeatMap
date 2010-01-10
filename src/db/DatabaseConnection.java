package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseConnection {
	private static final String DATABASE_DRIVER_STRING		= "org.sqlite.JDBC";
	private static final String DATABASE_CONNECTION_STRING	= "jdbc:sqlite:test.db";
	private static final String DATABASE_USERNAME			= "";
	private static final String DATABASE_PASSWORD			= "";
	
	private static Connection connection = null;
	

	private static void connect() throws SQLException {
		try {
			Class.forName(DATABASE_DRIVER_STRING);
			
			connection = DATABASE_USERNAME.length() > 0 ? DriverManager.getConnection(DATABASE_CONNECTION_STRING, DATABASE_USERNAME, DATABASE_PASSWORD) : DriverManager.getConnection(DATABASE_CONNECTION_STRING);
		} catch (ClassNotFoundException e){
			throw new DatabaseException("Can not load the JDBC driver class", e);
		} catch (SQLException e){
			throw new DatabaseException("Can not establish DB connection", e);
		} catch (Exception e){
			throw new DatabaseException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null){
			connect();
		}
	
		return connection;
	}

	public static void closeConnection(){
		if (connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// connection is closed anyway
			} finally {
				connection = null;
			}
		}
	}
	
	public static void execute(String sql) throws SQLException {
		Statement stmt = null;
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} finally {
			if (stmt != null){
				stmt.close();
			}
		}
	}
	
	public static ResultSet getResults(String sql) throws SQLException {
		return getConnection().createStatement().executeQuery(sql);
	}
	
	public static void handleException(SQLException e){
		System.out.println(e.getMessage());
	}
}
