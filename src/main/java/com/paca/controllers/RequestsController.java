package com.paca.controllers;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paca.entities.Request;
import com.paca.entities.User;
import com.paca.services.RequestsService;
import com.paca.services.UsersService;

@Controller
public class RequestsController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired //Inyectar el servicio
	private RequestsService requestService;
	
	@Autowired 
	private UsersService usersService;
	
	@RequestMapping("/request/list")
	public String getList(Model model, Pageable pageable, Principal principal, 
			@RequestParam(value = "", required=false) String searchText){
		
		String email = principal.getName(); // email es el name de la autenticación
		User user = usersService.getUserEmail(email);
		Page<Request> request = new PageImpl<Request>(new LinkedList<Request>());
		if (searchText != null && !searchText.isEmpty()) {
//			request =  requestService
//					.searchRequestReceived(pageable, searchText, user);
			
		} else {
			request = requestService.searchRequestReceived(pageable, user) ;
		}
		
		model.addAttribute("requestList", request.getContent());
		model.addAttribute("page", request);
		return "/request/list";
	}

}
