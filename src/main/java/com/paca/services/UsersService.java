package com.paca.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paca.entities.User;

import com.paca.repositories.UsersRepository;

@Service
public class UsersService { 
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private RequestsService requestsService;
	
	@Autowired 
	private PostService postService;
	
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostConstruct
	public void init() {
	}
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>(); 
		usersRepository.findAll().forEach(users::add); 
		return users;
	}
	
	public Page<User> getUsers(Pageable pageable, String email){
//		Page<User> users = usersRepository.findAllWithOutCurrentUser(pageable, email); 
		Page<User> users = usersRepository.findAll(pageable); 

		return users;
	}	
	
	public User getUser(Long id) {
		return usersRepository.findOne(id); 
	}
	
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}
	
	public void deleteUser(Long id) { 
		requestsService.deleteUser(id);
		friendshipService.deleteUser(id);
		postService.deleteUser(id);
		usersRepository.delete(id);
	}

	public User getUserEmail(String email) {
		return usersRepository.findByEmail(email);
	} 
	
	public Page<User> searchUsersByDNIAndName(Pageable pageable, String searchText, String email){ 
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.searchByEmailAndNameWithOutCurrentUser(pageable, searchText, email); 
		return users;
	}
	
	public void setUserIsAddFriend(boolean revised,Long id){ 
		usersRepository.updateIsAddFriend(revised, id);	
	}
	


}