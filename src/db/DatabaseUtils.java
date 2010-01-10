package db;

import db.models.*;
import static db.DatabaseConnection.getResults;
import static db.DatabaseConnection.handleException;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseUtils {
	public static List<NetworkSignal> findAllNetworksBySsid(String ssid){
		return NetworkSignal.findAll("ssid = \"" + ssid + "\"");
	}
	
	public static List<SignalPoint> findAllPointsBySsid(String ssid){
		return SignalPoint.findAllBySql(
			"SELECT * FROM signal_points WHERE id IN (" +
				"SELECT point_id FROM network_signals WHERE ssid = \"" + ssid + "\"" +
			")"
		);
	}
	
	public static List<String> findAllSsids(){
		List<String> ssids = new ArrayList<String>();
		
		try {
			ResultSet rs = getResults("SELECT DISTINCT ssid FROM network_signals");
			while(rs.next()){
				ssids.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e){
			handleException(e);
		}
		
		return ssids;
	}
}
