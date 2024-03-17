package com.webservice.MatchCraft.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "user_id1")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "user_id2")
	    private User matchedUser;
	    
	    private String friendshipStatus;

	    /**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return the user
		 */
		public User getUser() {
			return user;
		}

		/**
		 * @param user the user to set
		 */
		public void setUser(User user) {
			this.user = user;
		}

		/**
		 * @return the matchedUser
		 */
		public User getMatchedUser() {
			return matchedUser;
		}

		/**
		 * @param matchedUser the matchedUser to set
		 */
		public void setMatchedUser(User matchedUser) {
			this.matchedUser = matchedUser;
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
