package view.networks;

import db.DatabaseUtils;
import db.models.SignalPoint;

import javax.swing.DefaultListModel;

@SuppressWarnings("serial")
public class PointsListModel extends DefaultListModel {
	public void setSsid(String ssid){
		removeAllElements();
		
		for(SignalPoint point : DatabaseUtils.findAllPointsBySsid(ssid)){
			addElement(point);
		}
	}
}
