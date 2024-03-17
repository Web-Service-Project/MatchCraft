package com.webservice.MatchCraft.serviceimp;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webservice.MatchCraft.model.Friend;
import com.webservice.MatchCraft.model.User;
import com.webservice.MatchCraft.repo.FriendRepo;
import com.webservice.MatchCraft.repo.UserRepo;

@Service
public class FriendServicesImp {
	private final FriendRepo friendRepo;
	private final UserRepo userRepo;

	public FriendServicesImp(FriendRepo friendRepo, UserRepo userRepo) {
		this.friendRepo = friendRepo;
		this.userRepo = userRepo;
	}

	public Long sendFriendRequest(Long userId, Long matchedUserId) {
		// Fetch user entity using UserRepository
		User requester = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("Requester not found"));
		User requestedUser = userRepo.findById(matchedUserId)
				.orElseThrow(() -> new RuntimeException("Requested user not found"));

		Friend friend = new Friend();
		friend.setUser(requester);
		friend.setMatchedUser(requestedUser);
		friend.setFriendshipStatus("pending");

		Friend savedFriend = friendRepo.save(friend);
		return savedFriend.getId();
	}

	public void acceptFriendRequest(Long friendId) {
		Friend friend = friendRepo.findById(friendId)
				.orElseThrow(() -> new IllegalArgumentException("Friend not found"));
		friend.setFriendshipStatus("accepted");
		friendRepo.save(friend);
	}

	public void declineFriendRequest(Long friendId) {
		friendRepo.deleteById(friendId);
	}

	public Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			String currentUserName = authentication.getName();
			User user = userRepo.findByUserEmail(currentUserName);
			if (user != null) {
				return user.getId();
			}
		}
		return null;
	}
}
