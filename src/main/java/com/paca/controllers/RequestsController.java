package com.paca.controllers;

import java.security.Principal;
import java.util.LinkedList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paca.entities.Request;
import com.paca.entities.User;
import com.paca.services.RequestsService;
import com.paca.services.UsersService;

@Controller
public class RequestsController {
	
	
	@Autowired //Inyectar el servicio
	private RequestsService requestService;
	
	@Autowired 
	private UsersService usersService;
	
	@RequestMapping("/request/list")
	public String getList(Model model, Pageable pageable, Principal principal){
		
		String email = principal.getName(); // email es el name de la autenticación
		User user = usersService.getUserEmail(email);
		Page<Request> request = new PageImpl<Request>(new LinkedList<Request>());
		
		request = requestService.searchRequestReceived(pageable, user) ;

		model.addAttribute("requestList", request.getContent());
		model.addAttribute("page", request);
		return "/request/list";
	}
	
	@RequestMapping("/request/list/update") 
	public String updateList(Model model, Pageable pageable, Principal principal){
		String email = principal.getName(); // email es el name de la autenticación
		User user = usersService.getUserEmail(email);
		Page<Request> request = new PageImpl<Request>(new LinkedList<Request>());
		
		request = requestService.searchRequestReceived(pageable, user) ;

		model.addAttribute("requestList", request.getContent());
		return "/request/list :: tableRequests";
	}
	

	@RequestMapping(value="/request/{id}/accept", method=RequestMethod.GET) 
	public String setResendTrue(Model model, @PathVariable Long id, Principal principal){
		requestService.acceptRequest( id, usersService.getUserEmail(principal.getName()).getId());
		return "redirect:/user/list";
	}
}
