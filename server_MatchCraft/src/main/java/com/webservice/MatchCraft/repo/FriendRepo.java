package com.webservice.MatchCraft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webservice.MatchCraft.model.Friend;

@Repository
public interface FriendRepo extends JpaRepository<Friend, Long>{

}
