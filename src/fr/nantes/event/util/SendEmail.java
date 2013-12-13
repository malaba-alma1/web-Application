package fr.nantes.event.util;

import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import fr.nantes.event.dao.NewsletterDao;
import fr.nantes.event.dao.UserDao;

public final class SendEmail {
	
	public static boolean sendEmailNewEvent(String key, String name, String description, String date, String location, String stadium, String userAdded){
		// get 
		String urlEvent = Initialization.httpPath+"/detailsEvent.jsp?key="+key;
		
		String subject = "Nantes Events: New event added";
		
		String message = "Dear member,<br>A new event, <a href='"+urlEvent+"'><b>"+name+"</b></a>, just added by <b>"+userAdded+"</b> :<br><br>";
		message += "<<"+description+">>";
		message += "<br>Date : "+date;
		message += "<br>Stadium : "+stadium;
		message += "<br>Location :"+location;
		message += "<br><br><br><span style='text-size:9px; color:red;'>This an automatic message, Please do not response it</span>";
		
		String to = "malaba03@gmail.com";
		String cc = "";
		String bcc = "";
		
		//get all users mail
		PersistenceManager pManager = null;
		try{
			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(UserDao.class);
			List<UserDao> results = (List<UserDao>) query.execute();
			
			for(UserDao userDao : results){
				if(!cc.isEmpty()) cc +=";";
				cc += userDao.getEmail();
			}
			
			//Get emails from newsletter
			query = pManager.newQuery(NewsletterDao.class);
			List<NewsletterDao> newsletter = (List<NewsletterDao>) query.execute();
			
			for(NewsletterDao subscrit : newsletter){
				if(!cc.contains(subscrit.getEmail())){
					if(!cc.isEmpty()) cc +=";";
					cc += subscrit.getEmail();
				}
			}
			
		}
		finally{};
		return sendMessage(subject, message, to, cc, bcc);
	}
	
	public static boolean sendMessage(String subject, String message, String to, String cc, String bcc){
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("malaba03@gmail.com", "BA Mamadou Lamine-E13D192L"));
            
            if(to.length()>0 && to.contains("@")) 
            	msg.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
            
            //Adding CC if needed
            if(cc.length()>0){
            	String[] ccList = cc.split("\\;", -1);
            	for(String email : ccList){
            		if(email.length() >0 && email.contains("@"))
            			msg.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
            	}
            }
            
          //Adding BCC if needed
            if(bcc.length()>0){
            	String[] bccList = bcc.split("\\;", -1);
            	for(String email : bccList){
            		if(email.length() >0 && email.contains("@"))
            			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(email));
            	}
            }
            Multipart mp = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(message, "text/html");
            mp.addBodyPart(htmlPart);
            
            msg.setSubject(subject);
            msg.setContent(mp);
            Transport.send(msg);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } 
		return false;
	}

	public static boolean sendEmailSubcribtion(String email, String key, String name, String date, String location, String stadium, String userCreated, String sport){
		String urlEvent = Initialization.httpPath+"/detailsEvent.jsp?key="+key;
		
		String subject = "Nantes Events: Your subscription";
		
		String message = "Dear member,<br>You are subscribed to the event, <a href='"+urlEvent+"'><b>"+name+"</b></a>, created by  <b>"+userCreated+"</b> <br><br>";
		message += "<br>Date : "+date;
		message += "<br>Stadium : "+stadium;
		message += "<br>Sport : "+sport;
		message += "<br>Location :"+location;
		message += "<br><br><br><span style='text-size:9px; color:red;'>This an automatic message, Please do not response it</span>";
		
		String cc = "malaba03@gmail.com";
		String bcc = "";

		return sendMessage(subject, message, email, cc, bcc);
	}
	
	public static boolean sendEmailNewsletter(String email){
		String subject = "Nantes Events: newsletter subscription";
		
		String message = "Hello!,<br>You are subscribed for our newsletter in, <a href='"+Initialization.httpPath+"'><b>"+Initialization.httpPath+"</b></a><br><br>";
		message += "You will receive notifications for new events<br>Thanks see you soon.";
		message += "<br><br><br><span style='text-size:9px; color:red;'>This an automatic message, Please do not response it</span>";
		
		String cc = "malaba03@gmail.com";
		String bcc = "";

		return sendMessage(subject, message, email, cc, bcc);
	}
}
