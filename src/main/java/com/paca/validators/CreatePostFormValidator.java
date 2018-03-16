package com.paca.validators;

import com.paca.entities.Post;
import com.paca.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class CreatePostFormValidator implements Validator{


	
	@Override
	public boolean supports(Class<?> aClass) { 
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) { 
		Post post = (Post) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "Error.empty");
		
		
		if (post.getText().length() < 1 || post.getText().length() > 30) {
			errors.rejectValue("text", "Error.post.create.text.length");
		}
		
		if (post.getTitle().length() < 2 || post.getTitle().length() > 20) {
			errors.rejectValue("title", "Error.post.create.title.length");
		}

	}

}
