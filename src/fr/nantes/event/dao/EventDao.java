package fr.nantes.event.dao;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class EventDao {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
	
	@Persistent
	private String sport = "";
	
	@Persistent
	private Date date = new Date();
	
	@Persistent
	private String name = "";
	
	@Persistent
	private String description = "";
	
	@Persistent
	private String stadium = "";
	
	@Persistent
	private String address = "";
	
	@Persistent
	private String longitude = "";
	
	@Persistent
	private String latitude = "";
	
	@Persistent
	private String userCreated = "";
	
	@Persistent
	private String maxPeoples = "";
	
	@Persistent
	private String nbSubscrits = "";
	
	
	 /**
	 * @param key
	 * @param name
	 * @param description
	 * @param place
	 * @param longitude
	 * @param latitude
	 * @param date
	 * @param userCreated
	 * @param maxPeoples
	 * @param nbSubscrits
	 * @param stadium
	 */
	public EventDao(String sport, Date date, String name, String description, String stadium, String address,
			String longitude, String latitude, String userCreated,
			String maxPeoples, String nbSubscrits) {
		this.sport = sport;
		this.date = date;
		this.name = name;
		this.description = description;
		this.stadium = stadium;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.userCreated = userCreated;
		this.maxPeoples = maxPeoples;
		this.nbSubscrits = nbSubscrits;
	}
	
	/**
	 * @return the id
	 */
	public Key getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Key id) {
		this.id = id;
	}
	/**
	 * @return the sport
	 */
	public String getSport() {
		return sport;
	}
	/**
	 * @param sport the sport to set
	 */
	public void setSport(String sport) {
		this.sport = sport;
	}
	/**
	 * @return the dateEvent
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param dateEvent the dateEvent to set
	 */
	public void setDate(Date dateEvent) {
		this.date = dateEvent;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param nom the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the place
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param place the place to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the userCreated
	 */
	public String getUserCreated() {
		return userCreated;
	}
	/**
	 * @param userCreated the userCreated to set
	 */
	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}
	/**
	 * @return the maxPeoples
	 */
	public String getMaxPeoples() {
		return maxPeoples;
	}
	/**
	 * @param maxPeoples the maxPeoples to set
	 */
	public void setMaxPeoples(String maxPeoples) {
		this.maxPeoples = maxPeoples;
	}
	/**
	 * @return the stadium
	 */
	public String getStadium() {
		return stadium;
	}
	/**
	 * @param stadium the stadium to set
	 */
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	/**
	 * @return the nbSubscrits
	 */
	public String getNbSubscrits() {
		return nbSubscrits;
	}
	/**
	 * @param nbSubscrits the nbSubscrits to set
	 */
	public void setNbSubscrits(String nbSubscrits) {
		this.nbSubscrits = nbSubscrits;
	}
}

