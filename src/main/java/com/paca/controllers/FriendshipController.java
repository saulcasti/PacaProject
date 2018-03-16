package com.paca.controllers;


import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paca.entities.User;
import com.paca.services.FriendshipService;


@Controller
public class FriendshipController {


	@Autowired
	private FriendshipService friendshipService;
	

	 
	
	@RequestMapping("/user/friendsList" )
	public String getListadoAmigos(Model model, Pageable pageable, Principal principal){
		
		String email = principal.getName();
		
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		
		users = friendshipService.getFriends(pageable, email);
		
		model.addAttribute("usersList", users.getContent() );
		model.addAttribute("page", users);
		
		return "user/friendsList";
	}
	
	
}
