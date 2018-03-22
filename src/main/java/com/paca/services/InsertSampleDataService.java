package com.paca.services;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paca.entities.Post;
import com.paca.entities.User;


@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RequestsService requestService;
	
	@Autowired
	private PostService postService;
	
	 @Autowired
	 private RolesService rolesService;
	 
	 
	@PostConstruct
	public void init() {
		
		// Se crean los diferentes usuarios, se les asigna su contraseña y su rol
		User user1 = new User("elPaco-@hotmail.com", "Paco", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[1]);
		
		User user2 = new User("laPaca@gmail.com", "Paca", "Salas");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		
		User user3 = new User("laVero@gmail.com", "Vero", "Ortíz");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		
		User user4 = new User("laMonse@gmail.com", "Monse", "García");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("xkYoLoValgo@gmail.com", "José Luis", "LoBate");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("elJonas@gmail.com", "Jonás", "Tomás");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		
		User user7 = new User("elJhonan@gmail.com", "Jhonan", "Del Barrio");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		
		//Único usuario administrador
		User user8 = new User("admin@paca.es", "Admin", "Paca");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[1]);
		
		
		// Se añaden a la base de datos
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		
		
		requestService.sendRequest(user3.getId(), user7.getId());
		requestService.sendRequest(user3.getId(), user4.getId());
		requestService.sendRequest(user3.getId(), user5.getId());
		requestService.sendRequest(user3.getId(), user6.getId());
		requestService.sendRequest(user4.getId(), user3.getId());

		
		// El usuario user2 publica un post con imagen
		Post post1 = new Post();
		post1.setAuthor(user2);
		post1.setDateToday();
		post1.setText("Paca Salas");
		post1.setTitle("Mujeres de honor");
		post1.setImageURL("/img/post/PacaSalas.jpg");
		
		postService.createPost(post1);

	}

}
