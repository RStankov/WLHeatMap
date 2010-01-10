package gps;

public class Record {
    private String latitude 		= "";
    private String longitude 		= "";
    private String quality 			= "";    
    private String satelliteCount	= "";
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getQuality() {
		return quality;
	}
	
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	public String getSatelliteCount() {
		return satelliteCount;
	}
	
	public void setSatelliteCount(String satelliteCount) {
		this.satelliteCount = satelliteCount;
	}
	
	public boolean isValid(){
		return !latitude.equals("") && !longitude.equals("");
	}
	
	public String toString(){
		return "<Gps::Record latitude:" + latitude + " longitude:" + longitude + " quality: " + quality + " satelliteCount: " + satelliteCount + ">";
	}
}
