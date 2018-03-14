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
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");

		if (user.getEmail().length() < 5 || user.getEmail().length() > 24) {
			errors.rejectValue("email", "Error.signup.email.length");
		}
		if (user.getEmail().split("@").length != 2 || user.getEmail().split(".").length != 2) {
			errors.rejectValue("email", "Error.signup.email.format");
		}

		if (usersService.getUserEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Error.signup.email.duplicate");

		}

		if (user.getName().length() < 5 || user.getName().length() > 24) {
			errors.rejectValue("name", "Error.signup.name.length");
		}

		if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastName.length");
		}

		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) { 
			errors.rejectValue("passwordConfirm",
					"Error.signup.passwordConfirm.coincidence");
		}
	}

}
