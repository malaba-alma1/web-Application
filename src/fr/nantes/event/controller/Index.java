package fr.nantes.event.controller;

import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.nantes.event.dao.NewsletterDao;
import fr.nantes.event.util.PMF;
import fr.nantes.event.util.SendEmail;
import fr.nantes.event.util.Utility;

public class Index extends Controller{
	
	public Index(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	
	public void index(){
		
	}
	
	public void search(){
		System.out.println("searching");
		String date = request.getParameter("date") !=null ? request.getParameter("date") : "";
		String nom = request.getParameter("nom") !=null ? request.getParameter("nom") : "";
		String sport = request.getParameter("sport") !=null ? request.getParameter("sport") : "";
		String stadium = request.getParameter("stadium") !=null ? request.getParameter("stadium") : "";
		String creator = request.getParameter("creator") !=null ? request.getParameter("creator") : "";
		
		request.getSession(true).setAttribute("date", date);
		request.setAttribute("nom", nom);
		request.getSession(true).setAttribute("sport", sport);
		request.setAttribute("stadium", stadium);
		request.setAttribute("creator", creator);
	}
	
	public void newsletter(){
		System.out.println("newsletter");
		String email = request.getParameter("email");
		
		if(email != null && !email.isEmpty()){
			Date date = Utility.getDateFromString(Utility.getDateNow("yyyy-MM-dd HH:mm:ss"), "");
			PersistenceManager pManager = null;
			try {
				// Saving event
				pManager = PMF.get().getPersistenceManager();
				Transaction transaction = pManager.currentTransaction();
				
				transaction.begin();
				
				NewsletterDao news = new NewsletterDao(email, date);
				pManager.makePersistent(news);
				
				transaction.commit();
				//SendMail 
				SendEmail.sendEmailNewsletter(email);
			
			} finally  {
				pManager.close();
			}
			
		} else{
		}
	}
	
}
