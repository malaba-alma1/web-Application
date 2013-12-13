package fr.nantes.event.dao;


import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class UserDao{
	@PrimaryKey
    private String id;
	
	@Persistent
	private String nickname = "";
	
	@Persistent
	private String email = "";
	
	@Persistent
	@Element(dependent = "true")
	private List<EventDao> userEvents = null;
	
	/**
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param email
	 */
	public UserDao(String id, String nickname, String email, List<EventDao> userEvents) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.userEvents = userEvents;
	}
	
	
	public String getId() {
        return id;
    }
	public void setId(String id) {
        this.id = id;
    }
	
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nom the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
		

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the userEvents
	 */
	public List<EventDao> getUserEvents() {
		return userEvents;
	}

	/**
	 * @param userEvents the userEvents to set
	 */
	public void setUserEvents(List<EventDao> userEvents) {
		this.userEvents = userEvents;
	}
	
	
}
