package db.models;

import static db.DatabaseConnection.getConnection;
import static db.DatabaseConnection.getResults;
import static db.DatabaseConnection.handleException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.BaseModel;
import gps.Record;
import wireless.Network;

public class SignalPoint extends BaseModel {
	private String latitude 		= "";
    private String longitude 		= "";
    private String quality 			= "";    
    private String satelliteCount	= "";
    private List<NetworkSignal> networks;
    
    public SignalPoint(){
    	
    }
    
    public SignalPoint(Record record) throws SQLException {
    	latitude		= record.getLatitude();
    	longitude		= record.getLongitude();
    	quality			= record.getQuality();
    	satelliteCount	= record.getSatelliteCount();
    	save();
    }
    
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getQuality() {
		return quality;
	}
	
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	public String getSatelliteCount() {
		return satelliteCount;
	}
	
	public void setSatelliteCount(String satelliteCount) {
		this.satelliteCount = satelliteCount;
	}
    
	public String toString(){
		return (latitude.length() > 9 ? latitude.substring(0, 9) : latitude) + ", " + (longitude.length() > 9 ? longitude.substring(0, 9) : longitude);
	}
	
	protected PreparedStatement getCreateStatement() throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO signal_points ( latitude, longitude, quality, satelliteCount) VALUES ( ?, ?, ? , ? )");
		    
		stmt.setString(1, this.latitude);
		stmt.setString(2, this.longitude);
		stmt.setString(3, this.quality);
		stmt.setString(4, this.satelliteCount);
		
		return stmt;
	}
	
	protected PreparedStatement getUpdateStatement() throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement("UPDATE signal_points SET latitude = ?, longitude = ?, quality = ? , satelliteCount = ? WHERE `id` = ?");
		
		stmt.setString(1, this.latitude);
		stmt.setString(2, this.longitude);
		stmt.setString(3, this.quality);
		stmt.setString(4, this.satelliteCount);
		stmt.setInt	  (5, this.id);
		
		return stmt;
	}
	
	protected PreparedStatement getDeleteStatement() throws SQLException {
		return getConnection().prepareStatement("DELETE FROM signal_points WHERE id = ?");
	}
   
	public void addNetwork(Network network) throws SQLException {
		NetworkSignal signal = new NetworkSignal(network);
		signal.setPointId(id);
		signal.save();
	}
	
	public List<NetworkSignal> getNetworks() {
		if (networks == null){
			networks = NetworkSignal.findByPointId(id);
		}
		
		return networks;
	}
	
	public NetworkSignal getNetworkBySsid(String ssid){
		return NetworkSignal.findAll("point_id = " + id + " AND ssid = \"" + ssid + "\"").get(0);
	}

	public static SignalPoint find(int id) throws SQLException {
		return fetchFromFirstResultSet( getResults("SELECT * FROM signal_points WHERE id = " + id) );
	}
	
	public static List<SignalPoint> findAll() {
		return findAllBySql("SELECT * FROM signal_points");
	}

	public static List<SignalPoint> findAll(String where) {
		return findAllBySql("SELECT * FROM network_signals " + (where.length() > 0 ?  "WHERE " + where : ""));
	}
	
	public static  List<SignalPoint> findAllBySql(String query) {
		List<SignalPoint> points = new ArrayList<SignalPoint>();
		try {
			ResultSet rs = getResults(query);
			
			while(rs.next()){
				points.add(fetchFromResultSet(rs));
			}
			
			rs.close();
		} catch (SQLException e){
			handleException(e);
		}
		
		return points;
	}
	
	private static SignalPoint fetchFromFirstResultSet(ResultSet rs) throws SQLException {
		if (!rs.next()){
			return null;
		}
		
		SignalPoint point = fetchFromResultSet(rs);
		
		rs.close();
		
		return point;
	}
	
	private static SignalPoint fetchFromResultSet(ResultSet rs) throws SQLException {
		SignalPoint point = new SignalPoint();
		
		point.id 				= rs.getInt("id");
		point.latitude 			= rs.getString("latitude");
		point.longitude 		= rs.getString("longitude");
	    point.quality 			= rs.getString("quality");    
	    point.satelliteCount	= rs.getString("satelliteCount");
	    
		return point;
	}

}
