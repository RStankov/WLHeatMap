package view.points;

import java.util.Set;
import java.util.HashSet;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import db.models.SignalPoint;

import view.Map;
import view.event.WaypointClickEvent;
import view.event.WaypointClickListener;
import view.map.BaseWaypoint;
import view.map.PointWaypoint;

@SuppressWarnings("serial")
public class PointsView extends JPanel {
	private Map map = new Map();
	private NetworksTableModel networksTableModel = new NetworksTableModel();
	private JTable networksTable = new JTable();
	private JScrollPane panel = new JScrollPane(networksTable);
	
	public PointsView(){
		super();

		Set<BaseWaypoint> points = new HashSet<BaseWaypoint>();
		
		for(SignalPoint point : SignalPoint.findAll()){
			points.add(new PointWaypoint(point));
		}
		
		map.setWaypoints(points);
		
		map.addWaypointClickListener(new WaypointClickListener() {
			@Override
			public void waypointClick(WaypointClickEvent event) {
				networksTableModel.setNetworks( ((PointWaypoint) event.getWaypoint()).getPoint().getNetworks() );
			}
	    });
		
		networksTable.setModel(networksTableModel);
		networksTable.setRowHeight(25);
		
		GroupLayout layout = new GroupLayout(this);
		
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(map, GroupLayout.Alignment.LEADING)
                    .addComponent(panel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(map, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );

		setLayout(layout);
        
        setLayout(layout);
        /*
	    setLayout(new GridLayout(2,1));
	    
		add(map);
		add(panel);
		*/
	}
}
