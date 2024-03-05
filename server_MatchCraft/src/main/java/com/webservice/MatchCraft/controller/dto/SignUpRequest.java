package com.webservice.MatchCraft.controller.dto;

public class SignUpRequest {

	private String userName;
	private String email;
	private String password;
	private String skillLevel;
	private String gamePreferences;
	
	
	/**
	 * @return the skillLevel
	 */
	public String getSkillLevel() {
		return skillLevel;
	}
	/**
	 * @param skillLevel the skillLevel to set
	 */
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	/**
	 * @return the gamePreferences
	 */
	public String getGamePreferences() {
		return gamePreferences;
	}
	/**
	 * @param gamePreferences the gamePreferences to set
	 */
	public void setGamePreferences(String gamePreferences) {
		this.gamePreferences = gamePreferences;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
