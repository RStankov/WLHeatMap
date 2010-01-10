package wireless;

public class Network {
	private String ssid;
	private String bssid;
	private String rssi;
	private String encryption;
	private String channel;
	private String speed; //link speed,54,48,... Mbps
	
	public void setSsid(String ssid){
		this.ssid = ssid;
	}
	public String getSsid(){
		return ssid;
	}
	
	public void setBssid(String bssid){
		this.bssid = bssid;
	}
	public String getBssid(){
		return bssid;
	}
	
	public void setRssi(String rssi){
		this.rssi = rssi;
	}
	public String getRssi(){
		return rssi;
	}
		
	public void setChannel(String channel){
		this.channel = channel;
	}
	public String getChannel(){
		return channel;
	}

	public void setEncryption(String encryption){
		this.encryption = encryption;
	}
	public String getEncryption(){
		return encryption;
	}

	public void setSpeed(String speed){
		this.speed = speed;
	}
	
	public String getSpeed(){
		return speed;
	}
	
	public String toString(){
		return "<network name:" + ssid + " mac:" + bssid + " rssi:" + rssi + " encryption:" + encryption + " channel:" + channel + " speed:" + speed + ">";
	}
}
