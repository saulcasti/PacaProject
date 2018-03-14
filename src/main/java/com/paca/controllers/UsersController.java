package com.paca.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paca.entities.User;
import com.paca.services.SecurityService;
import com.paca.services.UsersService;
import com.paca.validators.SignUpFormValidator;

@Controller
public class UsersController {


	@Autowired
	private UsersService usersService;
	
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
		return "home"; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
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
}
