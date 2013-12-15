package fr.nantes.event.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.nantes.event.dao.EventDao;
import fr.nantes.event.dao.UserDao;

public final class UserUtility {
	public static void addUserIfNew() {
		UserService userService = UserServiceFactory.getUserService();
		User oUser = userService.getCurrentUser();
		
		if(oUser == null) return;
		String email = oUser.getEmail();
		String id = oUser.getUserId();

		PersistenceManager pManager = null;
		boolean isRegistered = false;
		try {
			pManager = PMF.get().getPersistenceManager();

			if (pManager.getObjectById(UserDao.class, email) != null) {
				isRegistered = true;
			}

		} catch (Exception ex) {
		} finally {
		}

		if (!isRegistered) {
			try {
				// String id = oUser.getUserId();
				String nickname = oUser.getNickname();

				Transaction transaction = pManager.currentTransaction();

				transaction.begin();
				UserDao userDao = new UserDao(id, nickname, email, null);

				pManager.makePersistent(userDao);

				transaction.commit();
				
				//On lui envoi un mail
				SendEmail.sendEmailNewUser(email, nickname);
			} catch (Exception ex) {
			} finally {
				pManager.close();
			}
		}
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


	public static UserDao getUserByEmail(String email){
		UserDao userDao = null;
		String req = "email == email";
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		List<UserDao> users = new ArrayList<UserDao>();
		
		PersistenceManager pManager = null;
		try {
			pManager = PMF.get().getPersistenceManager();

			pManager = PMF.get().getPersistenceManager();
			Query query = pManager.newQuery(UserDao.class, req);
			users = (List<UserDao>) query.executeWithMap(params);
			
			if(!users.isEmpty()) userDao = users.get(0);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pManager.close();
		}
		return userDao;
	}
}
