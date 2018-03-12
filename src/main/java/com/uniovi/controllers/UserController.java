package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;
import com.uniovi.validator.SignUpFormValidator;

@Controller
public class UserController {
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private UsersService usersService;
	
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
//		 securityService.autoLogin(user.getDni(), user.getPasswordConfirm()); 
		 return "home";
	}


}
