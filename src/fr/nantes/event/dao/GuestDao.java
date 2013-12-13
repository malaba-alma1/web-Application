package fr.nantes.event.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class GuestDao {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
	
	@Persistent
	private String keyEvent = "";
	
	@Persistent
	private String userEmail = "";
	
	@Persistent
	private String date = "";

	
	
	/**
	 * @param id
	 * @param keyEvent
	 * @param date
	 */
	public GuestDao(String keyEvent, String userEmail, String date) {
		this.keyEvent = keyEvent;
		this.userEmail = userEmail;
		this.date = date;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Key id) {
		this.id = id;
	}

	/**
	 * @param keyEvent the keyEvent to set
	 */
	public void setKeyEvent(String keyEvent) {
		this.keyEvent = keyEvent;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the id
	 */
	public Key getId() {
		return id;
	}

	/**
	 * @return the keyEvent
	 */
	public String getKeyEvent() {
		return keyEvent;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	

}
