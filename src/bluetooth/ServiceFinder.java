package bluetooth;

import java.io.IOException;

import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

public class ServiceFinder {
	private RemoteDevice device;
	private String connectionUrl;
	
	private UUID[] uuidSet = {new UUID(0x1101) };
	private int[] attrSet =  new int[] { 0x0100 };
	
	public ServiceFinder(RemoteDevice device){
		this.device = device;
		this.connectionUrl = null;
	}

	public String getConnectionUrl(){
		return connectionUrl;
	}
	
	public static String getConnectionUrl(RemoteDevice device){
		ServiceFinder finder = new ServiceFinder(device);
		finder.searchForService();
		return finder.getConnectionUrl();
	}
	
	public void searchForService(){
		final Object serviceSearchCompletedEvent = new Object();
    	
    	DiscoveryListener listener = new DiscoveryListener() {
            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {}
            public void inquiryCompleted(int discType) {}

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                for (int i = 0; i < servRecord.length; i++) {
                    String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                    if (url == null) {
                        continue;
                    }

                    DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
                    if (serviceName == null) {
                        System.out.println("service found " + url);
                    } else {
                        System.out.println("service " + serviceName.getValue() + " found " + url);
                    } 
                    
                    connectionUrl = url;
                }
                    
            }

            public void serviceSearchCompleted(int transID, int respCode) {
                synchronized(serviceSearchCompletedEvent){
                    serviceSearchCompletedEvent.notifyAll();
                }
            }
        };
		
        synchronized(serviceSearchCompletedEvent){
        	try {
        		LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrSet, uuidSet, device, listener);
            	serviceSearchCompletedEvent.wait();
        	} catch(IOException e) {
        	} catch(InterruptedException e) {}
        }
	}
}
