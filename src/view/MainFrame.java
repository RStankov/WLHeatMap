package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.networks.NetworksView;
import view.points.PointsView;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {	
	public MainFrame(){
		super();
		
		setTitle("Wireless networks map");
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    JTabbedPane tabs = new JTabbedPane();
	    tabs.addTab("points", new PointsView());
	    tabs.addTab("networks", new NetworksView());
	    
		getContentPane().add(tabs);
	}
}
