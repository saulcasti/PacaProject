package com.paca.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paca.entities.User;


@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("pedro-@hotmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		usersService.addUser(user1);
	}

}
