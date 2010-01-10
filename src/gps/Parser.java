package gps;

/**
 * NMEA-0183 Parser. 
 * Parses data sent by GPS receiver. 
 * As data is being transfered via XML to server, parsing consists in most cases of separating fields.
 * 
 */
public class Parser {

    public static Record parseRecrod(String s) {
    	Tokenizer tokenizer = new Tokenizer(s);
	    
	    while(tokenizer.hasNext() && !tokenizer.next().endsWith("$GPGGA"));
	    
	    if (!tokenizer.hasNext()) {
	    	return null;
	    }

	    // not needed element
	    tokenizer.next();
	    	
	    Record record = new Record();
	    
	    record.setLatitude(parseLatitude(tokenizer.next(), tokenizer.next()));
	    record.setLongitude(parseLongitude(tokenizer.next(), tokenizer.next()));
	    record.setQuality(tokenizer.next());
	    record.setSatelliteCount(tokenizer.next());
	    
	    return record;
    }
    
    private static String parseLatitude(String raw, String direction){
        String deg = raw.substring(0, 2);
        String min = "0." + raw.substring(2, 4) + raw.substring(5);
        
        float value = Float.parseFloat(deg) + Float.parseFloat(min)/.6f;
        
        if(!direction.equals("N")){
        	value = value * -1;
        }

        return value + "";
    }
    
    private static String parseLongitude(String raw, String direction){
        String deg = raw.substring(0, 3);          
        String min = "0." + raw.substring(3, 5) + raw.substring(6);
        
        float value = Float.parseFloat(deg) + Float.parseFloat(min)/.6f;
        
        if(!direction.equals("E")){
        	value = value * -1;
        }
    	
    	return value + "";
    }
}