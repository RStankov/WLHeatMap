package view.map;

import db.models.SignalPoint;

public class PointWaypoint extends BaseWaypoint {
	private SignalPoint point;
	
	public PointWaypoint(SignalPoint point){
		super(point.getLatitude(), point.getLongitude());
		
		this.point = point;
	}

	public SignalPoint getPoint() {
		return point;
	}

	@Override
	public int getSize() {
		return point.getNetworks().size() * 5;
	}

}
