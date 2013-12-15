package fr.nantes.event.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fr.nantes.event.bean.EventBean;
import fr.nantes.event.dao.EventDao;
import fr.nantes.event.dao.GuestDao;

public final class EventUtility {
	public static EventDao getEventById(String key) {
		EventDao event = null;
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			event = pManager.getObjectById(EventDao.class, key);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return event;
	}
	
	public static List<EventDao> getListEvents() {
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
				bean.setDate(Utility.getDateToString(event.getDate(), "MM/dd/yyyy HH:mm:ss"));
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
			params.put("date", Utility.getDateFromString(date, "yyyy-MM-dd HH:mm:ss"));
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
			params.put("date", Utility.getDateFromString(date, "yyyy-MM-dd HH:mm:ss"));
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
		String req = "";
		List<EventDao> events = new ArrayList<EventDao>();
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(EventDao.class, req);
			query.setOrdering("createdAt descending");
			query.setRange(start, limit);
			events = (List<EventDao>) query.execute();
			
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
	
	public static List<EventDao> getPastEvents(long start, long limit) {
		String req = "date<:nowTime";
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
	
	public static int getNbEvents(){
		return getListEventsBy("", "", "", "", "", "").size();
	}
}
