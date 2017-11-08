package com.neu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.Assignments;

public class AssignmentValidator implements Validator{
	public boolean supports(Class classes) {
		return classes.equals(Assignments.class);
	}
	public void validate(Object object, Errors errors) {
		Assignments assignment = (Assignments) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Title is required");
	}
}
