package fr.nantes.event.bean;

public class UserBean {
	private String id = "";
	private String nickname = "";
	private String email = "";
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the prenom
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the login
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param login the login to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
