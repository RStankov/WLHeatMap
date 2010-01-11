package view.networks;

import db.models.NetworkSignal;
import db.models.SignalPoint;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

@SuppressWarnings("serial")
public class NetworkInfo extends JPanel {
	private JLabel ssidLabel = new JLabel("Ssid:");
	private JLabel bssidLabel = new JLabel("Bssid:");
	private JLabel rssiLabel = new JLabel("Rssi");
	private JLabel encryptionLabel = new JLabel("Encryption:");
	private JLabel channelLabel = new JLabel("Channel:");
	private JLabel speedLabel = new JLabel("Speed:");
	
	private JLabel ssidValue = new JLabel();
	private JLabel bssidValue = new JLabel();
	private JLabel rssiValue = new JLabel();
	private JLabel encryptionValue = new JLabel();
	private JLabel channelValue = new JLabel();
	private JLabel speedValue = new JLabel();
	
	public NetworkInfo(){
		setVisible(false);
	
		GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ssidLabel, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(ssidValue))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bssidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bssidValue))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rssiLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rssiValue))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(encryptionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(encryptionValue))
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(channelLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(channelValue))
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(speedLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(speedValue))
                     )
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(ssidLabel)
                    .addComponent(ssidValue))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bssidLabel)
                    .addComponent(bssidValue))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(rssiLabel)
                    .addComponent(rssiValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(encryptionLabel)
                    .addComponent(encryptionValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(channelLabel)
                    .addComponent(channelValue))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(speedLabel)
                    .addComponent(speedValue))
                .addContainerGap(64, Short.MAX_VALUE))
        );
	}
	
	public void showPoint(SignalPoint point, String ssid){
		if (point == null){
			setVisible(false);
		} else {
			showNetwork(point.getNetworkBySsid(ssid));
		}
	}
	
	public void showNetwork(NetworkSignal network){
		if (network == null){
			setVisible(false);
		} else {
			ssidValue.setText(network.getSsid());
			bssidValue.setText(network.getBssid());
			rssiValue.setText(network.getRssi() + "%");
			encryptionValue.setText(network.getEncryption());
			channelValue.setText(network.getChannel());
			speedValue.setText(network.getSpeed());
			setVisible(true);
		}
	}
}
