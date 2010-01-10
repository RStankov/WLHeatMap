package db;

import java.sql.SQLException;

@SuppressWarnings("all")
public class DatabaseException extends SQLException {

	public DatabaseException(String message){
		super(message);
	}
	
	public DatabaseException(Throwable e){
		super(e);
	}
	
	public DatabaseException(String message, Throwable e){
		super(message, e);
	}
}
