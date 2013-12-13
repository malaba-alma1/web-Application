package fr.nantes.event.bean;

@SuppressWarnings("serial")
public class EventBean implements java.io.Serializable{
	private String name = "";
	private String description = "";
	private String address = "";
	private String longitude = "";
	private String latitude = "";
	private String date = "";
	private String userCreated = "";
	private String maxPeoples = "";
	private String nbSubscrits = "";
	private String stadium = "";
	private String sport = "";
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	
	
}