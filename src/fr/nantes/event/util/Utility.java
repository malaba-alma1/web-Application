package fr.nantes.event.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class Utility {

	public static String getClassName(String string) {
		String name = "";
		String[] infosUrl = string.replaceAll("-", "_").split("\\_", -1);
		for (String ch : infosUrl) {
			name += ch.substring(0, 1).toUpperCase()
					+ ch.substring(1, ch.length());
		}
		return name;
	}

	public static String getMethodName(String string) {
		String name = "";
		String[] infosUrl = string.replaceAll("-", "_").split("\\_", -1);

		for (int i = 0; i < infosUrl.length; i++) {
			String ch = infosUrl[i];
			if (i == 0)
				name += ch.substring(0, 1).toLowerCase()
						+ ch.substring(1, ch.length());
			else
				name += ch.substring(0, 1).toUpperCase()
						+ ch.substring(1, ch.length());
		}
		return name;
	}

		
	public static String getDateNow(String format) {
		String dateStr = "";
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            dateStr = dateFormat.format(date);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return dateStr;
    }
	
	public static String getDateToString(Date date, String format) {
		String dateStr = "";
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            dateStr = dateFormat.format(date);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return dateStr;
    }
	
	public static Date getDateFromString(String date, String format){
    	DateFormat df = new SimpleDateFormat(format); 
			
    	try {
			return df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return null;
    }
	
	
	public static String getOptionsSports(String checked){
		String options = "<option value=''>Choose a sport</option>";
		SortedMap<String, String> allSports = getSports();
		if(!allSports.isEmpty()){
			for (String value : allSports.keySet()) {
				// utilise ici hashMap.get(mapKey) pour accéder aux valeurs
				String label = allSports.get(value);
				
				String isChecked = "";
				if(allSports.get(value).equalsIgnoreCase(checked))
					isChecked = " selected='true'";
				
				options += "<option value='"+value+"'"+isChecked+">"+label+"</option>";
			}
		}
		return options;
	}
	
	public static SortedMap<String, String> getSports(){
		SortedMap<String, String> allSports = new TreeMap<String, String>();
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
		
		return allSports;
	}
	
	public static String getOptionsStadiums(String checked){
		String options = "<option value=''>Choose a stadium</option>";
		SortedMap<String, Map<String, String>> stadiums = XmlParser.instance.getSortedStadiumFromOpenData();
		if(!stadiums.isEmpty()){
			for (String value : stadiums.keySet()) {
				// utilise ici hashMap.get(mapKey) pour accéder aux valeurs
				Map<String, String> data = stadiums.get(value);
				String label = data.get("name");
				String isChecked = "";
				if(data.get("name").equalsIgnoreCase(checked))
					isChecked = " selected='true'";
				
				options += "<option value='"+value+"'"+isChecked+">"+label+"</option>";
			}
		}
		return options;
	}
	
}
