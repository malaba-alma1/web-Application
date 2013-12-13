package fr.nantes.event.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;

public class Initialization {
	public static String httpPath;
	public static Map<String, String> allSports = new HashMap<String, String>();
	public static User user = null;
	
	public static boolean initServer(HttpServletRequest request) {
		httpPath = "http://"+request.getServerName();
		if(request.getServerPort() != 80 && request.getServerPort() != 443 )
			httpPath += ":"+request.getServerPort();
		
		setSports();
        return true;
    }
	
	public static void setSports(){
		allSports.put("baseball", "Baseball");
		allSports.put("basketball", "Basketball");
		allSports.put("bowling", "Bowling");
		allSports.put("boxing", "Boxing");
		allSports.put("cycling", "Cycling");
		allSports.put("football", "Football");
		allSports.put("golf", "Golf");
		allSports.put("handball", "Handball");	
		allSports.put("rugby", "Rugby");
		allSports.put("swimming", "Swimming");
		allSports.put("tennis", "Tennis");
		allSports.put("volleyball", "Volleyball");
		allSports.put("wrestling", "Wrestling");
	}
}
