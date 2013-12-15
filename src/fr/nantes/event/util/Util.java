package fr.nantes.event.util;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.nantes.event.dao.UserDao;

public final class  Util {

	public static int convertToInt(String string){
		try{
			return Integer.parseInt(string);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
	
	public final double convertToDouble(String string){
		try{
			return Double.parseDouble(string);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
}
