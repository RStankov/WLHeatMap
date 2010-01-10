package view;

import java.util.HashSet;
import java.util.Set;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.event.EventListenerList;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.Waypoint;

import db.DatabaseUtils;
import db.models.NetworkSignal;

import view.event.WaypointClickEvent;
import view.event.WaypointClickListener;
import view.map.BaseWaypoint;
import view.map.NetworkWaypoint;
import view.map.BaseWaypointPainter;

@SuppressWarnings("serial")
public class Map extends JXMapKit {
	public final double DEFUALT_LATITUDE = 43.222944;
	public final double DEFAULT_LONGITUDE = 27.935925;
	
	protected EventListenerList _waypointEventListeners = new EventListenerList();
	
	protected Set<BaseWaypoint> _waypoints;
	
	public Map(){
		init(DEFUALT_LATITUDE, DEFAULT_LONGITUDE);
	}
	
	public Map(double latitude, double longitude){
		init(latitude, longitude);
	}
	
	private void init(double latitude, double longitude){
		setDefaultProvider( JXMapKit.DefaultProviders.valueOf("OpenStreetMaps") );
		setCenterPosition(new GeoPosition(latitude, longitude));
		setZoom(1);
		setMiniMapVisible(false);
		setZoomButtonsVisible(false);
		setZoomSliderVisible(false);
		
		getMainMap().addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent event) {
                Waypoint waypoint = getWaypoint(event.getPoint());
                
                if (waypoint != null){
                	fireWaypointClickEvent(new WaypointClickEvent(getMainMap(), waypoint, event));
                }
			}

			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseReleased(MouseEvent e) {}
	    });
	}
	
	@SuppressWarnings("all")
	public void setWaypoints(Set<BaseWaypoint> waypoints){
		WaypointPainter painter = new BaseWaypointPainter(); //new WaypointPainter();
	    painter.setWaypoints(_waypoints = waypoints);
	    getMainMap().setOverlayPainter(painter);
	}
	
	public Waypoint getWaypoint(Point mousePoint){
		if (_waypoints == null){
			return null;
		}
		
		JXMapViewer map = getMainMap();
        
		Rectangle bounds = map.getViewportBounds();
		for(BaseWaypoint waypoint : _waypoints){
			Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
			
			int x = (int)(point.getX() - bounds.getX());
            int y = (int)(point.getY() - bounds.getY());
            int s = waypoint.getSize();
            
            if (new Rectangle(x - s, y - s, s * 2, s * 2).contains(mousePoint)){
            	return waypoint;
            }
		}
		
		return null;
	}
	
	public void setWaypointsBySsid(String ssid){
		Set<BaseWaypoint> waypoints = new HashSet<BaseWaypoint>();
	    
		for(NetworkSignal s : DatabaseUtils.findAllNetworksBySsid(ssid)){
	    	waypoints.add(new NetworkWaypoint(s));
	    }
		
		setWaypoints(waypoints);
	}
	
    public void addWaypointClickListener(WaypointClickListener listener) {
        listenerList.add(WaypointClickListener.class, listener);
    }

    public void removeWaypointClickListener(WaypointClickListener listener) {
        listenerList.remove(WaypointClickListener.class, listener);
    }

    void fireWaypointClickEvent(WaypointClickEvent event) {
        Object[] listeners = listenerList.getListenerList();
        // 2 elements - first is the listener class, second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==WaypointClickListener.class) {
                ((WaypointClickListener)listeners[i+1]).waypointClick(event);
            }
        }
    }
}
