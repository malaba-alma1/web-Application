import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import fr.nantes.event.dao.EventDao;


public class Testt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Key k = KeyFactory.createKey(EventDao.class.getSimpleName(), "6473924464345088");
		System.out.println("keyToString "+KeyFactory.keyToString(k));
	}

}
