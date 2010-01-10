package bluetooth;

public class Daemon extends Observer {
	public Daemon(String deviceAddress){
   	 	super(ServiceFinder.getConnectionUrl(RemoteDeviceFinder.getDevice(deviceAddress)));
	}
}
