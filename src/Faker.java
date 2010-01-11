
import db.Initializer;
import db.models.*;
import wireless.Network;
import gps.Record;

public class Faker {
	private static String ssid;
	
	public static void main(String[] args) throws Exception {
		// clear all old records
		new Initializer().recreateTables();

		// create test data
		ssid = "test network 1";
		
	    createNetworkAtPoint("43.222933181863176", "27.935914993286133", "1");
	    createNetworkAtPoint("43.22294099990729", "27.936108112335205", "71");
	    createNetworkAtPoint("43.22291754577198", "27.936354875564575", "18");
	    createNetworkAtPoint("43.22294881795041", "27.936590909957886", "52");
	    createNetworkAtPoint("43.22300354422405", "27.9367196559906", "10");
	    createNetworkAtPoint("43.22313645068408", "27.9367733001709", "100");
	    createNetworkAtPoint("43.2231599047352", "27.936633825302124", "31");
	    createNetworkAtPoint("43.22324590284537", "27.93645143508911", "96");
	    createNetworkAtPoint("43.22341008071887", "27.93601155281067", "75");
	    createNetworkAtPoint("43.2233475368192", "27.935646772384644", "5");
	    createNetworkAtPoint("43.22330062885233", "27.935067415237427", "3");
	    createNetworkAtPoint("43.22323808484039", "27.93473482131958", "53");
	    createNetworkAtPoint("43.22323808484039", "27.93445587158203", "84");
	    createNetworkAtPoint("43.22319117678931", "27.93420910835266", "69");
	    
		ssid = "test network 2";
		
	    createNetworkAtPoint("43.22313645068408", "27.93476700782776", "30");
	    createNetworkAtPoint("43.223269356854374", "27.935346364974976", "30");
	    createNetworkAtPoint("43.223394444749985", "27.935056686401367", "14");
	    createNetworkAtPoint("43.22336317280014", "27.93401598930359", "28");
	    createNetworkAtPoint("43.222933181863176", "27.93397307395935", "53");
	    createNetworkAtPoint("43.223425716683764", "27.93620467185974", "44");

		ssid = "test network 3";
		
	    createNetworkAtPoint("43.222933181863176", "27.935914993286133", "1");
	    createNetworkAtPoint("43.22294099990729", "27.936108112335205", "71");
	    createNetworkAtPoint("43.22291754577198", "27.936354875564575", "18");
	    createNetworkAtPoint("43.22294881795041", "27.936590909957886", "52");
	    createNetworkAtPoint("43.22300354422405", "27.9367196559906", "10");
	    createNetworkAtPoint("43.22313645068408", "27.9367733001709", "100");
	    createNetworkAtPoint("43.2231599047352", "27.936633825302124", "31");
	    createNetworkAtPoint("43.22324590284537", "27.93645143508911", "96");
	    createNetworkAtPoint("43.22341008071887", "27.93601155281067", "75");
	    createNetworkAtPoint("43.2233475368192", "27.935646772384644", "5");
	    createNetworkAtPoint("43.22330062885233", "27.935067415237427", "3");
	    createNetworkAtPoint("43.22323808484039", "27.93473482131958", "53");
	    createNetworkAtPoint("43.22323808484039", "27.93445587158203", "84");
	    createNetworkAtPoint("43.22319117678931", "27.93420910835266", "69");
	}
	
	private static void createNetworkAtPoint(String lat, String lng, String rssi) throws Exception {
		Record r = new Record();
		
		r.setLatitude(lat);
		r.setLongitude(lng);
		r.setQuality("1");
		r.setSatelliteCount("7");

		SignalPoint p = new SignalPoint(r);
		
		p.save();

		Network n = new Network();
		
		n.setSsid(ssid);
		n.setBssid("00:1e:e5:f1:2c:67");
		n.setRssi(rssi);
		n.setEncryption("WPA(PSK/TKIP/TKIP)");
		n.setChannel("1");

		NetworkSignal s = new NetworkSignal();
		
		s.setNetwork(n);
		s.setPointId(p.getId());
		s.save();
		
		System.out.println("Created:" + s);
	}
}
