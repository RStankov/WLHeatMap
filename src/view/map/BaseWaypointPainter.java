package view.map;

import java.awt.Graphics2D;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

@SuppressWarnings("unchecked")
public class BaseWaypointPainter<T extends JXMapViewer> extends WaypointPainter {
	@Override
	protected void paintWaypoint(Waypoint w, JXMapViewer map, Graphics2D g){
		int size = ((BaseWaypoint) w).getSize();
		
		g.setColor(((BaseWaypoint) w).getColor());
        g.fillOval(0, 0, size, size);
	}
}
