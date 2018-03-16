package com.paca.controllers;


import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paca.entities.Request;
import com.paca.entities.User;
import com.paca.services.RequestsService;
import com.paca.services.SecurityService;
import com.paca.services.UsersService;
import com.paca.validators.SignUpFormValidator;

@Controller
public class UsersController {


	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RequestsService requestsService;
	
	 @Autowired
	 private SecurityService securityService;

	 @Autowired
	 private SignUpFormValidator signUpFormValidator;
	 
	@RequestMapping(value = "/signup", method = RequestMethod.GET) 
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup"; 
	}

	@RequestMapping(value = "/signup", method= RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result ,Model model) {
		signUpFormValidator.validate(user, result);
		 if (result.hasErrors()) {
			 return "signup";
		 }
		 usersService.addUser(user); 
		 securityService.autoLogin(user.getEmail(), user.getPasswordConfirm()); 
		 return "home";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET) 
	public String home(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//		User activeUser = usersService.getUserEmail(email);
//		model.addAttribute("requestList", activeUser.getReceived());
		return "home"; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(required=false) String error) {
		
		if(error !=null) {
			model.addAttribute("error", "Inicio de sesión fallido");
		}
		model.addAttribute("user", new User());	
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(@Validated User user, BindingResult result ,Model model) {
		signUpFormValidator.validate(user, result);
		 if (result.hasErrors()) {
			 return "login";
		 }
		 securityService.autoLogin(user.getEmail(), user.getPasswordConfirm()); 
		 return "home";
	}
	
	@RequestMapping("/user/list" )
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required=false) String searchText, Principal principal){
		
		String email = principal.getName();
		
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		
		if (searchText != null && !searchText.isEmpty()) {
			users=usersService.searchUsersByDNIAndName (pageable, searchText, email);
		}else {
			users = usersService.getUsers(pageable, email);
		}
		model.addAttribute("usersList", users.getContent() );
		model.addAttribute("page", users);
		
		return "user/list";
	}
	
	@RequestMapping("/user/list/update") 
	public String updateList(Model model, Pageable pageable, Principal principal){
		Page<User> users = usersService.getUsers(pageable,  principal.getName());
		model.addAttribute("usersList", users.getContent() );
		return "user/list :: tableUsers";
	}
	
	@RequestMapping(value="/user/{id}/isAddFriend", method=RequestMethod.GET) 
	public String setResendTrue(Model model, @PathVariable Long id, Principal principal){
//		usersService.setUserIsAddFriend(true, id);
		requestsService.sendRequest(id, usersService.getUserEmail(principal.getName()).getId());
		return "redirect:/user/list";
	}
	@RequestMapping(value="/user/{id}/noIsAddFriend", method=RequestMethod.GET) 
	public String setResendFalse(Model model, @PathVariable Long id, Principal principal){
//		usersService.setUserIsAddFriend(false, id);
		requestsService.cancellRequest(id, usersService.getUserEmail(principal.getName()).getId());
		return "redirect:/user/list";
	}
}
