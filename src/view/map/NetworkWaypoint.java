package view.map;

import db.models.NetworkSignal;

public class NetworkWaypoint extends BaseWaypoint {
	private NetworkSignal network;
	
	public NetworkWaypoint(NetworkSignal network){
		super(network.getPoint().getLatitude(), network.getPoint().getLongitude());
		this.network = network;
	}
	
	public NetworkSignal getNetwork(){
		return network;
	}
	
	@Override
	public int getSize(){
		int rssi = Math.abs(Integer.parseInt(network.getRssi()));
		
		if (rssi < 45)  return 4;
		if (rssi < 65)  return 6;
		if (rssi < 85)  return 8;
		if (rssi < 100) return 10;
		
		return 12;
	}
}
