package fr.nantes.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class User extends Controller{
	public String view = "index.jsp";
	public String id = "";
	
	public User(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	
	public void index(){
	}
	
	public void add(){
		
	}

}
