package wireless.adapters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wireless.Network;

/**
 * @author Yami
 *	GUID regex: "(?i)\b[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}\b"
 *	FULL regex (MAC missing): "(?i).*(SSID: )((\b[\w\-\.]*\b)|\s).*(\b1?\d{1,2}\b%).*(Default authentication algorithm: )(\b[\w\-]+\b).*(Default cipher algorithm: )(\b\w+\b).*"
 *	MAC regex: "(?i).*(SSID: )((\b[\w\-\.]*\b)|\s).*([0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2})"
 */
public class WindowsAdapter implements Adapter {

	/* (non-Javadoc)
	 * @see wireless.adapters.Adapter#getNetworks()
	 */
	@Override
	
	//TODO: change it when final version of wcm.exe is ready
	public ArrayList<Network> getNetworks() {
		ArrayList<Network> networks = new ArrayList<Network>();
		
		try {
			final Pattern NETWORK_PATTERN = Pattern.compile("(?i)\\b[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}\\b");
			final Pattern FULL_NETWORK_PATTERN = Pattern.compile("(?i).*(SSID: )((\\b[\\w\\-\\.]*\\b)|\\s).*(\\b1?\\d{1,2}\\b%).*(Default authentication algorithm: )(\\b[\\w\\-]+\\b).*(Default cipher algorithm: )(\\b\\w+\\b).*");
			final Pattern MAC_PATTERN = Pattern.compile("(?i).*(SSID: )((\\b[\\w\\-\\.]*\\b)|\\s).*([0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2}:[0-9a-z]{1,2})");
			
			ArrayList<String> lines = new ArrayList<String>();
			
			try {
				Process 		proc 	= Runtime.getRuntime().exec("wcm.exe ei");
				BufferedReader	buffer 	= new BufferedReader(new InputStreamReader(proc.getInputStream()));			
				String 			line;
				
				// collect all lines
				while ((line = buffer.readLine()) != null) {
					lines.add(line);
				}
			} catch(IOException e){
				// TODO: handle exception
			}
			
			ArrayList<String> wnics = new ArrayList<String>();
			
			for(Iterator<String> i = lines.listIterator(); i.hasNext();)
			{
				Matcher matcher = NETWORK_PATTERN.matcher(i.next().trim());
				
				if(matcher.find())
				{
					System.out.println(matcher.group(0));
					wnics.add(matcher.group(0));
				}
			}
			
			for(Iterator<String> i = wnics.listIterator(); i.hasNext();)
			{
				ArrayList<String> output = new ArrayList<String>();
				
				try {
					Process 		proc 	= Runtime.getRuntime().exec("wcm.exe gnl " + i.next());
					BufferedReader	buffer 	= new BufferedReader(new InputStreamReader(proc.getInputStream()));			
					String 			line;
					
					// collect all lines
					while ((line = buffer.readLine()) != null) {
						output.add(line);
					}
				} catch(IOException e){
					// TODO: handle exception
				}
				
				ArrayList<String> mac = new ArrayList<String>();
				
				for(Iterator<String> j = output.listIterator(); j.hasNext();)
				{
					String s = j.next().trim();
					Network nety = new Network();
					
					Matcher matcher1 = FULL_NETWORK_PATTERN.matcher(s);
					Matcher matcher2 = MAC_PATTERN.matcher(s);
					
					if(matcher1.find())
					{
						System.out.println(matcher1.group(2));
						System.out.println(matcher1.group(4));
						System.out.println(matcher1.group(6));
						System.out.println(matcher1.group(8));
						
						nety.setSsid(matcher1.group(2));
						nety.setRssi(matcher1.group(4));
						nety.setEncryption(matcher1.group(6) + "(" + matcher1.group(8) + ")");
						
						networks.add(nety);
					}
					if(matcher2.find())
					{
						System.out.println(matcher2.group(4));
						
						mac.add(matcher2.group(4));
					}
				}
				
				if(!networks.isEmpty()) {
					for(int j=0; j<networks.size(); j++) {
						Network n = networks.get(j);
						n.setBssid(mac.get(j));
						n.setChannel("");
						n.setSpeed("");
						
						networks.set(j, n);
					}
				}
			}
		}
		catch(Exception e) {

		}
		return networks;
	}
}
