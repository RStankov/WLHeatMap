package view.event;

import java.util.EventObject;
import java.awt.event.MouseEvent;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;

@SuppressWarnings("serial")
public class WaypointClickEvent extends EventObject {
	private Waypoint waypoint;
	private MouseEvent mouseEvent;

	public WaypointClickEvent(JXMapViewer source, Waypoint waypoint, MouseEvent mouseEvent){
        super(source);
        getSource();

        this.waypoint = waypoint;
        this.mouseEvent = mouseEvent;
    }
	
	public JXMapViewer getMapView(){
		return (JXMapViewer) getSource();
	}
	
	public Waypoint getWaypoint() {
		return waypoint;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}
}
