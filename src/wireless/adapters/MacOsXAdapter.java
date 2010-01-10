package wireless.adapters;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wireless.Network;

public class MacOsXAdapter implements Adapter {
	private final String AIRPORT_COMMAND  = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -s";
	private final Pattern NETWORK_PATTERN = Pattern.compile("([\\s\\w]+)(([\\d\\w]{2}:?){6})\\s+(-\\d+)\\s+([^\\s]+)\\s+(\\w)\\s+([^\\s]{2})\\s+(.*)");
	
	public ArrayList<Network> getNetworks(){
		ArrayList<Network> list = new ArrayList<Network>();
		
		for(String line : getAirportInfo()){
			list.add(getNetwork(line));
		}
		
		return list;
	}
	
	private ArrayList<String> getAirportInfo(){
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			Process 		proc 	= Runtime.getRuntime().exec(AIRPORT_COMMAND);
			BufferedReader	buffer 	= new BufferedReader(new InputStreamReader(proc.getInputStream()));			
			String 			line;
			
			// skip first line
			buffer.readLine();
			
			// collect all other lines
			while ((line = buffer.readLine()) != null) {
				lines.add(line);
			}
		} catch(IOException e){
			// TODO: handle exception
		}
		
		return lines;
	}
	/*
	 * Line format:
	 *                      SSID BSSID             RSSI CHANNEL HT CC SECURITY (auth/unicast/group)
     *                 BTC-ADSL  00:1e:e5:f1:2c:67 -58  1       N  TW WPA(PSK/TKIP/TKIP) 
     *          SAF wireless net 00:1d:7e:e3:57:d6 -87  11      N  -- WPA(PSK/AES,TKIP/TKIP) WPA2(PSK/AES,TKIP/TKIP) 
     *
     * Regex:
     * 		([\s\w]+)(([\d\w]{2}:?){6})\s+(-\d+)\s+([^\s]+)\s+(\w)\s+([^\s]{2})\s+(.*)
     * 1 - SSID
     * 2 - BSSID
     * 3 - (empty)
     * 4 - RSSI
     * 5 - CHANNEL
     * 6 - HT
     * 7 - CC
     * 8 - SECURITY
     * 
	 */
	private Network getNetwork(String line){
		Matcher matcher = NETWORK_PATTERN.matcher(line.trim());
		
		matcher.find();
		
		Network network = new Network();
		
		network.setSsid(matcher.group(1));
		network.setBssid(matcher.group(2));
		network.setRssi(matcher.group(4));
		network.setChannel(matcher.group(5));
		network.setEncryption(matcher.group(8));
				
		return network;
	}
}
