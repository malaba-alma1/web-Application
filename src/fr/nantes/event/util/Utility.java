package fr.nantes.event.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import fr.nantes.event.bean.EventBean;
import fr.nantes.event.dao.EventDao;
import fr.nantes.event.dao.GuestDao;
import fr.nantes.event.dao.UserDao;

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

	public static EventDao getEventById(String key) {
		/*Key k = KeyFactory.createKey(EventDao.class.getSimpleName(), "6473924464345088");
		System.out.println("keyToString "+KeyFactory.keyToString(k));*/
		
		EventDao event = null;
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			event = pManager.getObjectById(EventDao.class, key);

			if(event != null) System.out.println("eve" + event.getDate());
			else System.out.println("null");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return event;
	}
	
	public static List<EventDao> getListEvents() {
		/*Key k = KeyFactory.createKey(EventDao.class.getSimpleName(), "6473924464345088");
		System.out.println("keyToString "+KeyFactory.keyToString(k));*/
		
		List<EventDao> events = new ArrayList<EventDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(EventDao.class);
			events = (List<EventDao>) query.execute();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return events;
	}

	public static List<EventBean> getListEventsBean() {
		List<EventBean> beans = new ArrayList<EventBean>();
		
		List<EventDao> events = getListEvents();
		if(!events.isEmpty()){
			for(EventDao event : events){
				EventBean bean = new EventBean();
				bean.setAddress(event.getAddress());
				bean.setDate(getDateToString(event.getDate(), "MM/dd/yyyy HH:mm:ss"));
				bean.setDescription(event.getDescription());
				bean.setLatitude(event.getLatitude());
				bean.setLongitude(event.getLongitude());
				bean.setMaxPeoples(event.getMaxPeoples());
				bean.setName(event.getName());
				bean.setNbSubscrits(event.getNbSubscrits());
				bean.setStadium(event.getStadium());
				bean.setUserCreated(event.getUserCreated());
				bean.setSport(event.getSport());;
				
				beans.add(bean);
			}
			
		}
		
		return beans;
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
	
	public static List<UserDao> getListUsers() {
		
		List<UserDao> users = new ArrayList<UserDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(UserDao.class);
			users = (List<UserDao>) query.execute();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return users;
	}
	
	public static List<EventDao> getListEventsBy(String id, String sport, String date, String name, String stadium, String userCreated) {
		String req = "";
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(!id.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " id == idEvent";
			params.put("id", id);
		}
		
		if(!sport.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " sport == sport";
			params.put("sport", sport);
		}
		
		if(!date.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " date == date";
			params.put("date", getDateFromString(date, "yyyy-MM-dd HH:mm:ss"));
		}
		
		if(!name.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " name == name";
			params.put("name", name);
		}
		
		if(!stadium.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " stadium == stadium";
			params.put("stadium", stadium);
		}
		
		if(!userCreated.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " userCreated == userCreated";
			params.put("userCreated", userCreated);
		}
		
		List<EventDao> events = new ArrayList<EventDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(EventDao.class, req);
			events = (List<EventDao>) query.executeWithMap(params);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return events;
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
				
				options += "<option value='"+value+"'>"+label+"</option>";
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
	
	public static List<GuestDao> getSubcriptions(String userEmail, String keyEvent, String date) {
		Map<String, String> params = new HashMap<String, String>();
		
		String req = "";
		if(!userEmail.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " userEmail == userEmail";
			params.put("userEmail", userEmail);
		}
		
		if(!keyEvent.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " keyEvent == keyEvent";
			params.put("keyEvent", keyEvent);
		}
		
		if(!date.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " date == date";
			params.put("date", date);
		}
		
		List<GuestDao> subcriptions = new ArrayList<GuestDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(GuestDao.class, req);
			subcriptions = (List<GuestDao>) query.executeWithMap(params);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return subcriptions;
	}
	
	
	public static long deleteUserSubcription(String userEmail, String keyEvent) {
		Map<String, String> params = new HashMap<String, String>();
		
		String req = "userEmail == userEmail && keyEvent == keyEvent";
		params.put("userEmail", userEmail);
		params.put("keyEvent", keyEvent);
		
		long nbDeleted = 0;
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();
			
			pManager.currentTransaction().begin();
			Query query = pManager.newQuery(GuestDao.class, req);
			nbDeleted = query.deletePersistentAll(params);
			
			pManager.currentTransaction().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return nbDeleted;
	}
	
	//
	public static List<EventDao> getUpComingEvents(String id, String sport, String date, String name, String stadium, String userCreated) {
		String req = "date>=:nowTime";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nowTime", new Date());
		
		if(!id.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " id == idEvent";
			params.put("id", id);
		}
		
		if(!sport.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " sport == sport";
			params.put("sport", sport);
		}
		
		if(!date.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " date == date";
			params.put("date", getDateFromString(date, "yyyy-MM-dd HH:mm:ss"));
		}
		
		if(!name.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " name == name";
			params.put("name", name);
		}
		
		if(!stadium.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " stadium == stadium";
			params.put("stadium", stadium);
		}
		
		if(!userCreated.isEmpty()){
			if(!req.isEmpty()) req += " &&";
			req += " userCreated == userCreated";
			params.put("userCreated", userCreated);
		}
		
		//req += " ORDER BY date ASC";
		
		List<EventDao> events = new ArrayList<EventDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(EventDao.class, req);
			query.setOrdering("date ascending");
			events = (List<EventDao>) query.executeWithMap(params);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return events;
	}
	
	public static List<EventDao> getLastEvents(long start, long limit) {
		String req = "date>=:nowTime";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nowTime", new Date());
		List<EventDao> events = new ArrayList<EventDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(EventDao.class, req);
			query.setOrdering("date descending");
			query.setRange(start, limit);
			events = (List<EventDao>) query.executeWithMap(params);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return events;
	}
	
	public static double convertToDouble(String string){
		try{
			return Double.parseDouble(string);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
	public static boolean isSubcribed(String key, String email) {
		List<GuestDao> subcriptions = getSubcriptions(email, key, "");
		return !subcriptions.isEmpty();
	}
}
