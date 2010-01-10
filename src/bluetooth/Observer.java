package bluetooth;

import java.io.*;
import javax.microedition.io.*;

import gps.Record;
import gps.Parser;

public class Observer extends Thread {
	private String connectionUrl;
	
	public Observer(String connectionUrl){
		this.connectionUrl = connectionUrl;
	}
	
	public void run(){
		try{
			StreamConnection connection = (StreamConnection)Connector.open(connectionUrl);
			InputStream in = connection.openInputStream();

			boolean readData = true;
			while(readData == true){			
				int length = in.available();

				if (length > 0){
					byte[] rawData = new byte[length];
					length = in.read(rawData);
					
					String serialData = new String(rawData);
					
					System.out.println("[" + serialData + "]");
			
					Record gpsDataRecord = null;
					try{
						gpsDataRecord = Parser.parseRecrod(serialData);
						
						System.out.println(gpsDataRecord);
						
						if (gpsDataRecord.isValid()){
							readData = true;
							
							// TODO - work
						}
					} catch (Exception e){
						System.out.println("GPS data parsing exception");
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
