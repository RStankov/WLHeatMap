package bluetooth;

import java.io.IOException;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class RemoteDeviceFinder {
	private String address;
	private RemoteDevice device;
	
	public RemoteDeviceFinder(String address){
		this.address = address;
	}
	
	public RemoteDevice getDevice(){
		 return device;
	}
	
	public static RemoteDevice getDevice(String address){
		RemoteDeviceFinder finder = new RemoteDeviceFinder(address);
		
		finder.searchForDevice();
		
		return finder.getDevice();
	}
	
	public void searchForDevice(){
		final Object inquiryCompletedEvent = new Object();

        DiscoveryListener listener = new DiscoveryListener() {
            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
            	if (btDevice.getBluetoothAddress().equals(address)){
                	device = btDevice;
                }
            }

            public void inquiryCompleted(int discType) {
                synchronized(inquiryCompletedEvent){
                    inquiryCompletedEvent.notifyAll();
                }
            }

            public void serviceSearchCompleted(int transID, int respCode) {}
            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {}
        };

        try {
	        synchronized(inquiryCompletedEvent) {
	            if (LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener)) {
	                inquiryCompletedEvent.wait();
	            }
	        }
        } catch(IOException e) {
        } catch(InterruptedException e) {}
	}
}
