package com.webservice.MatchCraft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.MatchCraft.serviceimp.FriendServicesImp;

@RestController
@RequestMapping("Api/friends")
public class FriendController {
	private final FriendServicesImp friendServiceImp;

	public FriendController(FriendServicesImp friendServiceImp) {
		this.friendServiceImp = friendServiceImp;
	}
	
	@PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam Long matchedUserId) {

        friendServiceImp.sendFriendRequest(friendServiceImp.getCurrentUserId(), matchedUserId);
        return ResponseEntity.ok("Friend request sent successfully");
    }
	
	@PostMapping("/accept")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam Long matchedUserId) {
		friendServiceImp.acceptFriendRequest(matchedUserId);
        return ResponseEntity.ok("Friend request accepted");
    }

    @PostMapping("/decline")
    public ResponseEntity<String> declineFriendRequest(@RequestParam Long friendId) {
    	friendServiceImp.declineFriendRequest(friendId);
        return ResponseEntity.ok("Friend request declined");
    }
}
