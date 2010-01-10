package view.points;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import db.models.NetworkSignal;

@SuppressWarnings("serial")
public class NetworksTableModel extends AbstractTableModel {
	private String[] columnNames = {"Ssid", "Bssid", "Rssi", "Encryption", "Channel", "Speed"};
	
	private List<NetworkSignal> networks = new ArrayList<NetworkSignal>();
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int columnIndex){
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return networks.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		NetworkSignal network = networks.get(rowIndex);
		switch(columnIndex){
			case 0: return network.getSsid();
			case 1: return network.getBssid();
			case 2: return network.getRssi();
			case 3: return network.getEncryption();
			case 4: return network.getChannel();
			case 5: return network.getSpeed();
		}
		
		return "";
	}

	public void setNetworks(List<NetworkSignal> networks){
		this.networks = networks;
		fireTableDataChanged();
	}
}
