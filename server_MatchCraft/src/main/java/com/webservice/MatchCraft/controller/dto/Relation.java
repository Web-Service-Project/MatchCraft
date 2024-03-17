package com.webservice.MatchCraft.controller.dto;

public class Relation {
	private Long requesterId;
    private Long requestedUserId;
    private String friendshipStatus;
    /**
	 * @return the requesterId
	 */
	public Long getRequesterId() {
		return requesterId;
	}
	/**
	 * @param requesterId the requesterId to set
	 */
	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}
	/**
	 * @return the requestedUserId
	 */
	public Long getRequestedUserId() {
		return requestedUserId;
	}
	/**
	 * @param requestedUserId the requestedUserId to set
	 */
	public void setRequestedUserId(Long requestedUserId) {
		this.requestedUserId = requestedUserId;
	}
	/**
	 * @return the friendshipStatus
	 */
	public String getFriendshipStatus() {
		return friendshipStatus;
	}
	/**
	 * @param friendshipStatus the friendshipStatus to set
	 */
	public void setFriendshipStatus(String friendshipStatus) {
		this.friendshipStatus = friendshipStatus;
	}
	
}
