package com.neu.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.CourseGrade;

public class CourseGradeValidator implements Validator{
	public boolean supports(Class classes) {
		return classes.equals(CourseGrade.class);
	}
	public void validate(Object object, Errors errors) {
		CourseGrade courseGrade = (CourseGrade) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseGrade", "error.invalid.courseGrade", "Grade is required");
	}
}
