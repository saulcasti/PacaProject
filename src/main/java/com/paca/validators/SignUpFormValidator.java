package com.paca.validators;

import com.paca.entities.User;
import com.paca.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator{

	@Autowired
	private UsersService usersService;
	
	@Override
	public boolean supports(Class<?> aClass) { 
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) { 
	

	}

}
