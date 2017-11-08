package com.neu.validator;

import org.springframework.validation.Validator;

import com.neu.pojo.User;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class UserValidator implements Validator{

	public boolean supports(Class classes) {
		// TODO Auto-generated method stub
		return classes.equals(User.class);
	}

	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "E-Mail Address is Required");
		
	}

}
