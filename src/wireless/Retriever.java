package wireless;

import java.util.ArrayList;

import wireless.adapters.Adapter;
import wireless.adapters.MacOsXAdapter;
import wireless.adapters.NotFoundException;
import wireless.adapters.WindowsAdapter;

public class Retriever {
	private static Adapter getAdapter() throws NotFoundException{
		String osName = System.getProperty("os.name");
		
		if (osName.equals("Mac OS X")){
			return new MacOsXAdapter();
		}
		
		if (osName.equals("Windows")){
			return new WindowsAdapter();
		}
		
		if (osName.equals("Linux")){
			// return new LinuxAdapter(); 
		}
		 
		throw new NotFoundException();
	}
	
	public static ArrayList<Network> getNetworks() throws Exception { 
		return getAdapter().getNetworks();
	}
}
