import db.models.SignalPoint;
import wireless.Network;
import static wireless.Retriever.getNetworks;
import gps.Record;

public class Tracer {
	public void run(){
		try {
			SignalPoint point = new SignalPoint( getGpsRecord() );
			
			for(Network network : getNetworks()){
				point.addNetwork(network);
			}
		} catch(Exception e){
			
		}
	}
	
	private Record getGpsRecord(){
		// TODO - populate with test data
		return new Record();
	}
}
