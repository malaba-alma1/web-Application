package fr.nantes.event.util;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

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
			} catch (Exception ex) {
			} finally {
				pManager.close();
			}
		}
	}
}
