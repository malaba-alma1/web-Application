package fr.nantes.event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.nantes.event.util.Initialization;

public class Controller {
	public HttpServletRequest request;
	public HttpServletResponse response;
	public String view = "";
	public User user = null;
	
	public Controller(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		
		this.user = UserServiceFactory.getUserService().getCurrentUser();
	}
	
	public void render(){
		try {
			String url = Initialization.httpPath+"/"+this.view;
			System.out.println("url: "+url);
			//request.getRequestDispatcher("/"+view).forward(this.request, this.response);
			response.sendRedirect(response.encodeRedirectURL(url));
			
			/*RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeRedirectURL("/detailsEvent.jsp"));
			dispatcher.forward(request, response);*/
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
