package bluetooth;

import java.io.*;
import javax.microedition.io.*;

import gps.Record;
import gps.Parser;

public class Observer extends Thread {
	private String connectionUrl;
	private int maxCount = -1;
	private int count = 0;
	
	public Observer(String connectionUrl){
		this.connectionUrl = connectionUrl;
	}
	
	public void setMaxRecords(int value){
		maxCount = value;
	}
	
	public void run(){
		try{
			StreamConnection connection = (StreamConnection) Connector.open(connectionUrl);
			InputStream in = connection.openInputStream();

			boolean readData = true;
			Record previousGpsRecord = null; 
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
						
						if (gpsDataRecord != null && gpsDataRecord.isValid() && !gpsDataRecord.equals(previousGpsRecord)){
							System.out.println(gpsDataRecord);
							
							previousGpsRecord = gpsDataRecord;
							
							count++;
							
							if (maxCount > 0 && count >= maxCount){
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
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}		
	}
}
