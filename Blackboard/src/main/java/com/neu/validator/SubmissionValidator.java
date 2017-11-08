package com.neu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.Submission;

public class SubmissionValidator implements Validator{
	public boolean supports(Class classes) {
		return classes.equals(Submission.class);
	}
	public void validate(Object object, Errors errors) {
		Submission submission = (Submission) object;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Title is required");
	}
}
