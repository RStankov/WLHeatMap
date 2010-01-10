package view.networks;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import view.Map;
import view.event.WaypointClickEvent;
import view.event.WaypointClickListener;
import view.map.NetworkWaypoint;

@SuppressWarnings("serial")
public class NetworksView extends JPanel {
	private NetworkInfo info = new NetworkInfo();
	private Map map = new Map();
	private NetworkMenu menu = new NetworkMenu(map, info);
	
	public NetworksView(){
		super();
		
	    map.addWaypointClickListener(new WaypointClickListener() {
			@Override
			public void waypointClick(WaypointClickEvent event) {
				info.showNetwork(((NetworkWaypoint) event.getWaypoint()).getNetwork());
			}
	    });

	    setLayout(new BorderLayout());
	    
		add(menu, BorderLayout.WEST);
		add(map, BorderLayout.CENTER);
	}
}
