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
		
		User user2 = new User("laPaca@gmail.com", "Paca", "Salas");
		user2.setPassword("123456");
		
		User user3 = new User("laVero@gmail.com", "Vero", "Ortíz");
		user3.setPassword("123456");
		
		User user4 = new User("laMonse@gmail.com", "Monse", "García");
		user4.setPassword("123456");
		
		User user5 = new User("xkYoLoValgo@gmail.com", "José Luis", "LoBate");
		user5.setPassword("123456");
		
		User user6 = new User("elJonas@gmail.com", "Jonás", "Tomás");
		user6.setPassword("123456");
		
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);

	}

}
