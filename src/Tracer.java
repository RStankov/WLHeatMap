import bluetooth.Observer;
import bluetooth.ObserverAction;
import db.models.SignalPoint;
import wireless.Network;
import static wireless.Retriever.getNetworks;
import gps.Record;

public class Tracer {
	// Device Uri:  "btspp://001C88006D7D:1;authenticate=false;encrypt=false;master=false"
	// Device Name: "001C88006D7D"
	private static final String DEVICE_NAME = "001C88006D7D";
	private static final int MAX_RECORDS = 1;
	
	public static void main(String[] args) throws Exception {
		try {
			Observer o = new Observer(DEVICE_NAME);
			o.setMaxRecords(MAX_RECORDS);
			o.setAction(new ObserverAction(){
				@Override
				public void on(Record record) {
					try {
						SignalPoint point = new SignalPoint( record );
						
						System.out.println(record);
						System.out.println("-- networks --");
						
						for(Network network : getNetworks()){
							System.out.println(network);
							
							point.addNetwork(network);
						}
						
						System.out.println("-- end --");
					} catch(Exception e){
						System.out.println("Exception: " + e.getMessage());
					}	
				}
			});
			o.start();
		} catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
