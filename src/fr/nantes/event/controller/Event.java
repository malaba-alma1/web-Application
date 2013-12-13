package fr.nantes.event.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.KeyFactory;

import fr.nantes.event.bean.EventBean;
import fr.nantes.event.dao.EventDao;
import fr.nantes.event.dao.GuestDao;
import fr.nantes.event.util.PMF;
import fr.nantes.event.util.SendEmail;
import fr.nantes.event.util.Util;
import fr.nantes.event.util.Utility;
import fr.nantes.event.util.XmlParser;

public class Event extends Controller{
	
	public Event(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	
	public void index(){
		this.view = "listeEvents.jsp";
		
		//Get all events
		PersistenceManager pManager = null;
		try{
			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(EventDao.class);
			List<EventDao> results = (List<EventDao>) query.execute();
			
			this.request.setAttribute("listEvens", results);
		}
		finally{};
	}
	
	public void add(){
		String errorMsg = "";
		String lastKey = "";
		
		String sport = request.getParameter("sport");
		String date = request.getParameter("date");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String stadium = request.getParameter("stadium");
		String maxPeoples = request.getParameter("maxPeoples");
		
		ArrayList<Map<String, String>> stadiums = new ArrayList<Map<String,String>>();
		Map<String, String> mapStadium = new HashMap<String, String>();
		String nameStadium = "";
		
		if(sport.length()==0) errorMsg = "Please choose a sport.<br>";
		if(date.length()==0) errorMsg += "Please enter the date.<br>";
		if(name.length()==0) errorMsg += "Please enter the name.<br>";
		//if(description.length()==0) errorMsg += "Vous devez preciser la description.<br>";
		
		if(stadium.length()==0) errorMsg += "Please choose a stadium.<br>";
		else{
			//Get all stadium from Open data nantes
			stadiums = XmlParser.instance.getStadiumFromOpenData("http://data.nantes.fr/api/publication/24440040400129_NM_NM_00024/LOC_EQUIPUB_SPORT_NM_STBL/content/?format=xml");
			
			int index = Util.convertToInt(stadium);
			mapStadium = stadiums.get(index);
			nameStadium = mapStadium.get("name").toString(); 
		}
		
		if(maxPeoples.length()==0) errorMsg += "Please enter the max guests.<br>";
		else if(Util.convertToInt(maxPeoples) <0 ) errorMsg += "The max people must be an integer.<br>";
		
		if(errorMsg.length()==0){
			//ArrayList<Map<String, String>> stadiums = (ArrayList<Map<String, String>>)request.getAttribute("stadiums");
			System.out.println("NBSTD"+stadiums.size());
			
			PersistenceManager pManager = null;
			try {
				String longitude = mapStadium.get("longitude").toString(); 
				String latitude = mapStadium.get("latitude").toString(); 
				String address = mapStadium.get("address").toString(); 

				String userCreated = this.user.getNickname();
				
				// Saving event
				pManager = PMF.get().getPersistenceManager();
				Transaction transaction = pManager.currentTransaction();
				
				transaction.begin();
				Date dateEvent = Utility.getDateFromString(date+":00", "MM/dd/yyyy HH:mm:ss");
				EventDao event = new EventDao(sport, dateEvent, name, description, nameStadium, address, longitude, latitude, userCreated, maxPeoples, "0");
				
				event = pManager.makePersistent(event);
				
				transaction.commit();
				
				lastKey = KeyFactory.keyToString(event.getId());
				
				//SendMail to users member
				SendEmail.sendEmailNewEvent(lastKey, name, description, date, address, nameStadium, userCreated);
				
				// Set the view
				this.view = "detailsEvent.jsp?key="+lastKey;
		        
			} finally  {
				pManager.close();
			}
		}
		else{
			System.out.println("ELSE");
			this.view = "addEvent.jsp";
			EventBean eventBean = new EventBean();
			this.request.getSession(true).setAttribute("errorMsg", errorMsg);
			
			eventBean.setSport(sport);
			eventBean.setDate(date);
			eventBean.setName(name);
			eventBean.setDescription(description);
			eventBean.setStadium(nameStadium);
			eventBean.setMaxPeoples(maxPeoples);
			
			this.request.getSession(true).setAttribute("Event", eventBean);
		}
	}
	
	public void details(){// NOT USE
		//Get the vent from the datastore
		String key = request.getParameter("key");
		System.out.println("KEY: "+key);
		this.view = "detailsEvent.jsp";
	}
	
	public void subscribe(){
		String key = request.getParameter("key");
		System.out.println("subscribe KEY: "+key);
		
		this.view = "detailsEvent.jsp?key="+key;
		
		if(this.user != null){
			String userEmail = this.user.getEmail();
			
			PersistenceManager pManager = null;
			try {
				EventDao event = Utility.getEventById(key);
				String date = Utility.getDateToString(event.getDate(), "MM/dd/yyyy HH:MM");
				// Saving event
				pManager = PMF.get().getPersistenceManager();
				Transaction transaction = pManager.currentTransaction();
				
				// Add subscription
				transaction.begin();
				GuestDao guest = new GuestDao(key, userEmail, Utility.getDateNow("yyyy-MM-dd HH:mm:ss"));
				pManager.makePersistent(guest);
				transaction.commit();
				
				//Updating nb subcription
				transaction.begin();
				event.setNbSubscrits((Util.convertToInt(event.getNbSubscrits())+1)+"");
				pManager.makePersistent(event);
				transaction.commit();
				//SendMail to users member
				
				SendEmail.sendEmailSubcribtion(userEmail, key, event.getName(), date, event.getAddress(), event.getStadium(), event.getUserCreated(), event.getSport());
				this.view += "&sub=SUCCESS";
			
			} finally  {
				pManager.close();
			}
			
		} else{
			this.view += "&sub=ERROR";
		}
	}
	
	public void unsubscribe(){
		String key = request.getParameter("key");
		System.out.println("unsubscribe KEY: "+key);
		
		this.view = "detailsEvent.jsp?key="+key;
		
		if(this.user != null){
			String userEmail = this.user.getEmail();
			
			long deleted = Utility.deleteUserSubcription(userEmail, key);
			
			if(deleted > 0){
				PersistenceManager pManager = null;
				try {
					EventDao event = Utility.getEventById(key);
					// Saving event
					pManager = PMF.get().getPersistenceManager();
					
					//Updating nb subcription
					pManager.currentTransaction().begin();
					event.setNbSubscrits((Util.convertToInt(event.getNbSubscrits())-1)+"");
					pManager.makePersistent(event);
					pManager.currentTransaction().commit();
					
					//SendMail to users member
					//SendEmail.sendEmailUnSubcribtion(userEmail, key, event.getName(), date, event.getAddress(), event.getStadium(), event.getUserCreated(), event.getSport());
					this.view += "&sub=SUCCESS";
				
				} finally  {
					pManager.close();
				}
			}
			
		} else{
			this.view += "&sub=ERROR";
		}
	}
	
	public void update(){
		// /event/update.do?id= if error
		
		
		// /event/view.do?id=lastKey if ok
	}
	
	public void delete(){
		
	}
}
