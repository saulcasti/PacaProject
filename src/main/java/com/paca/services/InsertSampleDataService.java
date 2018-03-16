package com.paca.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paca.entities.Request;
import com.paca.entities.User;


@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RequestsService requestService;
	
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
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
//		Request request1 = new Request("La Paca quiere ser tu amiga.", user2, user1);
//		Request request2 = new Request("La Vero quiere ser tu amiga.", user3, user1);
//		Request request3 = new Request("La Monse quiere ser tu amiga.", user4, user1);
//		Request request4 = new Request("El que lo vale quiere ser tu amigo.", user5, user1);
//		Request request5 = new Request("El Jonas quiere ser tu amigo.", user6, user1);
//		Request request6 = new Request("La Paca quiere ser tu amiga, otra vez.", user2, user1);
		
		requestService.sendRequest(user1.getId(), user2.getId());
		requestService.sendRequest(user1.getId(), user3.getId());
		requestService.sendRequest(user1.getId(), user4.getId());
		requestService.sendRequest(user1.getId(), user5.getId());
		requestService.sendRequest(user1.getId(), user6.getId());
		requestService.sendRequest(user2.getId(), user1.getId());
		
		
//		Set<Request> user1Received = new HashSet<Request>() {
//			{
//				add(request1);
//				add(request2);
//				add(request3);
//				add(request4);
//				add(request5);
//				add(request6);
//			}
//		};
//		Set<Request> user2Sent = new HashSet<Request>() {
//			{
//				add(request1);
//			}
//		};
//		user1.setReceived(user1Received);
//		user2.setSent(user2Sent);


	}

}
