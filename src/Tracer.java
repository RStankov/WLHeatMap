import bluetooth.Observer;
import bluetooth.ObserverAction;
import db.models.SignalPoint;
import wireless.Network;
import static wireless.Retriever.getNetworks;
import gps.Record;

public class Tracer {
	public void main(){

		Observer o = new Observer("btspp://001C88006D7D:1;authenticate=false;encrypt=false;master=false");
		o.setMaxRecords(4);
		o.setAction(new ObserverAction(){
			@Override
			public void on(Record record) {
				try {
					SignalPoint point = new SignalPoint( record );
					
					for(Network network : getNetworks()){
						point.addNetwork(network);
					}
				} catch(Exception e){
					
				}	
			}
			
		});
		o.start();
	}
}
