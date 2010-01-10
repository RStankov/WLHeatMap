package view.map;

import java.awt.Color;

import org.jdesktop.swingx.mapviewer.Waypoint;

public abstract class BaseWaypoint extends Waypoint {
	public BaseWaypoint(String latitude, String longitude){
		super(Double.parseDouble(latitude), Double.parseDouble(longitude));
	}
	
	public Color getColor(){
		return Color.BLACK;
	}
	
	public abstract int getSize();
}
