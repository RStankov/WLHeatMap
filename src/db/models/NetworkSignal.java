package db.models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import db.BaseModel;
import static db.DatabaseConnection.getConnection;
import static db.DatabaseConnection.getResults;
import static db.DatabaseConnection.handleException;

import wireless.Network;

public class NetworkSignal extends BaseModel {
	private int pointId;
	private Network network;
	private SignalPoint point;
	
	public NetworkSignal(){
		this.network = new Network();
	}
	
	public NetworkSignal(Network network){
		this.network = network;
	}
	
	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public String getSsid() {
		return network.getSsid();
	}
	
	public String getBssid() {
		return network.getBssid();
	}
	
	public String getChannel() {
		return network.getChannel();
	}
	
	public String getEncryption() {
		return network.getEncryption();
	}
	
	public String getRssi() {
		return network.getRssi();
	}
	
	public String getSpeed() {
		return network.getSpeed();
	}
	
	public void setBssid(String bssid) {
		network.setBssid(bssid);
	}

	public void setChannel(String channel) {
		network.setChannel(channel);
	}

	public void setEncryption(String encryption) {
		network.setEncryption(encryption);
	}

	public void setRssi(String rssi) {
		network.setRssi(rssi);
	}

	public void setSpeed(String speed) {
		network.setSpeed(speed);
	}

	public void setSsid(String ssid) {
		network.setSsid(ssid);
	}

	public SignalPoint getPoint() {
		if (point == null && pointId > 0){
			try {
				point = SignalPoint.find(pointId);
			} catch (SQLException e){}
		}
		return point;
	}
	
	public String toString(){
		return "<network_signal " +
			"id:" 			+ id 				+ " " +
			"point_id:" 	+ pointId 			+ " " +
			"ssid:"			+ getSsid() 		+ " " +
			"bssid:"		+ getBssid() 		+ " " +
			"rssi:"			+ getRssi() 		+ " " +
			"encryption:"	+ getEncryption()	+ " " +
			"channel:"		+ getChannel()		+ " " +
			"speed:"		+ getSpeed()		+
		">";
	}
	
	protected PreparedStatement getCreateStatement() throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO network_signals ( point_id, ssid, bssid, rssi, encryption, channel, speed ) VALUES ( ?, ?, ? , ? , ? , ?, ? )");
		
		stmt.setInt		(1, this.pointId);
		stmt.setString	(2, this.network.getSsid());
		stmt.setString	(3, this.network.getBssid());
		stmt.setString	(4, this.network.getRssi());
		stmt.setString	(5, this.network.getEncryption());
		stmt.setString	(6, this.network.getChannel());
		stmt.setString	(7, this.network.getSpeed());
		
		return stmt;
	}
	
	protected PreparedStatement getUpdateStatement() throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement("UPDATE network_signals SET point_id = ? , ssid = ? , bssid = ? , rssi = ? , encryption = ? , channel = ?, speed = ? WHERE `id` = ?");
		
		stmt.setInt		(1, this.pointId);
		stmt.setString	(2, this.network.getSsid());
		stmt.setString	(3, this.network.getBssid());
		stmt.setString	(4, this.network.getRssi());
		stmt.setString	(5, this.network.getEncryption());
		stmt.setString	(6, this.network.getChannel());
		stmt.setString	(7, this.network.getSpeed());
		stmt.setInt		(8, this.id);
		
		return stmt;
	}
	
	protected PreparedStatement getDeleteStatement() throws SQLException {
		return getConnection().prepareStatement("DELETE FROM network_signals WHERE id = ?");
	}
	
	public static NetworkSignal find(int id) throws SQLException {
		return fetchFromFirstResultSet( getResults("SELECT * FROM network_signals WHERE id = " + id) );
	}
	
	public static List<NetworkSignal> findByPointId(int pointId) {
		return findAll("point_id = " + pointId );
	}
	
	
	public static List<NetworkSignal> findAll() {
		return findAllBySql("SELECT * FROM network_signals");
	}

	public static List<NetworkSignal> findAll(String where) {
		return findAllBySql("SELECT * FROM network_signals " + (where.length() > 0 ?  "WHERE " + where : ""));
	}
	
	public static List<NetworkSignal> findAllBySql(String query) {
		List<NetworkSignal> networks = new ArrayList<NetworkSignal>();
		
		try {
			ResultSet rs = getResults(query);
				
			while(rs.next()){
				networks.add(fetchFromResultSet(rs));
			}
			
			rs.close();
		} catch(SQLException e){
			handleException(e);
		}
		
		return networks;
	}
	
	private static NetworkSignal fetchFromFirstResultSet(ResultSet rs) throws SQLException {
		if (!rs.next()){
			return null;
		}
		
		NetworkSignal signal = fetchFromResultSet(rs);
		
		rs.close();
		
		return signal;
	}
	
	private static NetworkSignal fetchFromResultSet(ResultSet rs) throws SQLException {
		NetworkSignal signal = new NetworkSignal();
		
		signal.id 		= 				rs.getInt("id");
		signal.pointId	=				rs.getInt("point_id");
		signal.network.setSsid(			rs.getString("ssid"));
		signal.network.setBssid(		rs.getString("bssid"));
		signal.network.setRssi(			rs.getString("rssi"));
		signal.network.setEncryption(	rs.getString("encryption"));
		signal.network.setChannel(		rs.getString("channel"));
		signal.network.setSpeed(		rs.getString("speed"));
		
		return signal;
	}
}
