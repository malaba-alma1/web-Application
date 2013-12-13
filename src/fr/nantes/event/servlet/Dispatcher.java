package fr.nantes.event.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.nantes.event.util.Initialization;
import fr.nantes.event.util.Utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("serial")
public class Dispatcher extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		processRequest(req, resp);
	}

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		Initialization.initServer(request);
		
		//System.out.println("httpPath: "+Initialization.httpPath);
		
		String controller = "index", action = "index";
		String[] infosUrl = request.getServletPath().replaceAll(".do", "").split("\\/", -1);
		
		if(infosUrl.length > 1) controller	 = infosUrl[1];
		if(infosUrl.length > 2) action = infosUrl[infosUrl.length-1];
		
		try {
			System.out.println("Dispatcher.processRequest()");
			// Call of appropriate class controller
			Class[] types = {HttpServletRequest.class, HttpServletResponse.class};
			Constructor constructor = Class.forName("fr.nantes.event.controller."+Utility.getClassName(controller)).
							getConstructor(types);
			Object[] parameters = {request, response};
			Object myController = constructor.newInstance(parameters);
			// call of appropriate methode
			System.out.println("MET:"+Utility.getMethodName(action));
			Method method = myController.getClass().getMethod(Utility.getMethodName(action), (Class<?>[]) null);
			method.invoke(myController, (Object[]) null);
			
			//Render the view
			method = myController.getClass().getMethod("render", (Class<?>[]) null);
			method.invoke(myController, (Object[]) null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
