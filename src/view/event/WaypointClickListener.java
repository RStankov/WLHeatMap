package view.event;

import java.util.EventListener;

public interface WaypointClickListener extends EventListener {
	public void waypointClick(WaypointClickEvent event);
}
