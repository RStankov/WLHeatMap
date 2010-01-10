package view.networks;

import db.DatabaseUtils;
import db.models.SignalPoint;

import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.Map;

@SuppressWarnings("serial")
public class NetworkMenu extends JPanel {
	private Map map;
	private NetworkInfo info;
	
	private JComboBox networksComboBox;
	private PointsListModel points;
	private JList pointsList;
	
	public NetworkMenu(Map mainMap, NetworkInfo mainInfo){
		map = mainMap;
		info = mainInfo;
		
		List<String> ssids = DatabaseUtils.findAllSsids();
		
		networksComboBox = new JComboBox();
		for(String ssid : ssids){
			networksComboBox.addItem(ssid);
		}
		
		networksComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ssid = (String) ((JComboBox) e.getSource()).getSelectedItem();
				points.setSsid(ssid);
				map.setWaypointsBySsid(ssid);
				info.setVisible(false);
			}
	    });

		points = new PointsListModel();
		if (ssids.get(0) != null){
			points.setSsid(ssids.get(0));
			map.setWaypointsBySsid(ssids.get(0));
		}
		
		pointsList = new JList(points);
		pointsList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				info.showPoint((SignalPoint) pointsList.getSelectedValue(), networksComboBox.getSelectedItem().toString());
			}
		});
		
		GroupLayout layout = new GroupLayout(this);
        
        layout.setHorizontalGroup(
        	layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	.addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(networksComboBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pointsList, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
                    .addComponent(info, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	.addGroup(
        		layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(networksComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			.addComponent(pointsList, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        			.addComponent(info, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addContainerGap()
        	)
        );
        
        setLayout(layout);
	}
}
