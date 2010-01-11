package gps;

public class Record {
    private String latitude 		= "";
    private String longitude 		= "";
    private String quality 			= "";    
    private String satelliteCount	= "";
    
    public Record(){
    	
    }
    
    public Record(String latitude, String longitude){
		this.latitude = latitude;
		this.longitude = longitude;
    }
    
    public Record(String latitude, String longitude, String quality, String satelliteCount) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.quality = quality;
		this.satelliteCount = satelliteCount;
    }
	
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
	
	public boolean equals(Record record){
		if (record == null){
			return false;
		}
		
		String rLatitude = record.latitude;
		rLatitude = rLatitude.substring(0, rLatitude.indexOf(".") + 4);
		
		String rLongitude = record.longitude;
		rLongitude = rLongitude.substring(0, rLongitude.indexOf(".") + 4);
		
		return 
			rLatitude.equals(latitude.substring(0, latitude.indexOf(".") + 4)) &&  
			rLongitude.equals(longitude.substring(0, longitude.lastIndexOf(".") + 4));
	}
}
