package com.uniovi.services;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("pedro@uniovi.es", "Pedro"); 
		user1.setPassword("aaa");
		User user2 = new User("lucas@uniovi.es", "Lucas"); 
		user2.setPassword("aaa");
		
	}
}
