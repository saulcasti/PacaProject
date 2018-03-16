package com.paca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paca.entities.Friendship;
import com.paca.entities.User;
import com.paca.repositories.FriendshipRepository;

@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepository friendshipRepository;

	
	@Autowired
	private UsersService usersService;
	
	public void addFriend(Long idUser1, Long idUser) {
		usersService.getUser(idUser).addFriend(new Friendship(usersService.getUser(idUser1), usersService.getUser(idUser)));
		usersService.getUser(idUser1).addFriend(new Friendship(usersService.getUser(idUser), usersService.getUser(idUser1)));
	}

	public Page<User> getFriends(Pageable pageable, String email) {
		Page<User> users = friendshipRepository.findFriendOfOneUser(pageable, email); 
		return users;
	}

}
