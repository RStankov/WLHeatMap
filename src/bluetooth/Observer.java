package bluetooth;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import javax.microedition.io.*;

import gps.Record;
import gps.Parser;

public class Observer extends Thread {
	private String connectionUrl;
	private int maxCount = -1;
	private List<Record> loadedRecords;
	private ObserverAction action;
	
	public Observer(String connectionUrl){
		this.connectionUrl = connectionUrl;
		this.maxCount = -1;
		this.loadedRecords = new ArrayList<Record>();
		this.action = null;
	}
	
	public void setMaxRecords(int value){
		maxCount = value;
	}
	
	public void setAction(ObserverAction action){
		this.action = action;
	}
	
	public void run(){
		try{
			StreamConnection connection = (StreamConnection) Connector.open(connectionUrl);
			InputStream in = connection.openInputStream();

			boolean readData = true;
			while(readData == true){			
				int length = in.available();

				if (length > 0){
					byte[] rawData = new byte[length];
					length = in.read(rawData);
					
					String serialData = new String(rawData);
					
					//System.out.println("[" + serialData + "]");
			
					Record gpsDataRecord = null;
					try{
						gpsDataRecord = Parser.parseRecrod(serialData);
						
						if (gpsDataRecord != null && gpsDataRecord.isValid() && loadedRecords.contains(gpsDataRecord)){
							System.out.println(gpsDataRecord);
							
							if (this.action != null){
								this.action.on(gpsDataRecord);
							}
							
							loadedRecords.add(gpsDataRecord);
							
							if (maxCount > 0 && loadedRecords.size() >= maxCount){
								readData = false;
							}
						}
					} catch (Exception e){
						System.out.println("GPS data parsing exception:" + e.getMessage());
					}
				}
			}
			
			in.close();
			connection.close();
			
		} catch(IOException ioe){
			ioe.printStackTrace();
		}		
	}
}
