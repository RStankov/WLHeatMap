
import static wireless.Retriever.getNetworks;
import wireless.Network;
import bluetooth.*;
import gps.Record;

/*
 * 
 * import static wireless.Retriver.getNetworks
 * import static gps.Retriver.getCoordinates
 * 
 * Tracer
 * 	-> run (+ more action)
 * 
 *  -> saveState
 *
 *    
 *  	SignalPoint point = new SignalPoint( getGpsRecord() );
 *  
 *  	for(Network network : getNetworks(){
 *  		point.addNetwork(network);
 *  	}
 * 
 * 
 * 		
 * 
 * 001C88006D7D
 * btspp://001C88006D7D:1;authenticate=false;encrypt=false;master=false
 * <Gps::Record latitude:43.221985 longitude:27.934818 quality: 1 satelliteCount: 9>
 * <Gps::Record latitude:43.22197 longitude:27.934843 quality: 0 satelliteCount: 3>
 * <Gps::Record latitude:43.22215 longitude:27.934889 quality: 1 satelliteCount: 7>
 * new Daemon("001C88006D7D");
 * new Observer("btspp://001C88006D7D:1;authenticate=false;encrypt=false;master=false");
 * 
 * 
 * 
 * Observer o = new Observer("btspp://001C88006D7D:1;authenticate=false;encrypt=false;master=false");
 * o.start();
 * 
 */

public class Main {
	public static void main(String[] args) throws Exception {
		/*
		Observer o = new Observer("btspp://001C88006D7D:1;authenticate=false;encrypt=false;master=false");
		o.setMaxRecords(4);
		o.start();
		*/
		
		
		//new Daemon("001C88006D7D");
		/*
		for(Network network : getNetworks()){
			System.out.println(network);
		}
		*/
		// NetworkSignal s = new NetworkSignal();
		//NetworkSignal s = NetworkSignal.find(2);
		/*
		Record r = new Record();
		
		r.setLatitude("43.222944");
		r.setLongitude("27.935925");
		r.setQuality("1");
		r.setSatelliteCount("7");

		SignalPoint p = new SignalPoint(r);
		
		p.save();
		
		s.setPointId(p.getId());
		s.save();
		
		//s.setNetwork(Retriever.getNetworks().get(0));
		//s.save();
		
		
		s.setPointId(2);
		s.setBssid("bssid");
		s.setChannel("channel");
		s.setEncryption("encryption");
		s.setRssi("rssi");
		s.setSpeed("speed");
		s.setSsid("ssid");
		*/
		
		//System.out.println(s);
		//System.out.println(s.getPoint());
	}
}
