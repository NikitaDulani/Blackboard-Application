package com.neu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.CourseGradeDAO;
import com.neu.dao.CoursesDAO;
import com.neu.dao.UserDAO;
import com.neu.pojo.Assignments;
import com.neu.pojo.CourseGrade;
import com.neu.pojo.Courses;
import com.neu.pojo.Person;
import com.neu.pojo.Submission;
import com.neu.pojo.User;

@Controller
public class CourseGradeController {
	
	@Autowired
	@Qualifier("courseGradeDAO")
	CourseGradeDAO courseGradeDAO;
	
	@Autowired
	@Qualifier("coursesDAO")
	CoursesDAO coursesDAO;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDAO;
	
	@RequestMapping(value = "/viewstudentsforgrades", method = RequestMethod.GET)
	protected String viewStudentsForGrades(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		long selectedCourseID = (Long)session.getAttribute("selectedCourseID");
		//Courses course = coursesDAO.get(selectedCourseID);
		List<Object[]> students = userDAO.getStudentsinCourse(selectedCourseID);
		session.setAttribute("students", students);
		return "display-studentsforgrades";
	}
	
	@RequestMapping(value = "/coursegrade/*", method = RequestMethod.GET)
	protected ModelAndView assignCourseGrade(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			session.setAttribute("errorMessage", "Please log in first");
			return new ModelAndView("error");
		}
		System.out.println("Assign grade to a student");
		
		String[] uri = request.getRequestURI().split("coursegrade/");
		Long studentID = Long.parseLong(uri[1]);
		System.out.println("Student ID for grades: " + studentID);
		session.setAttribute("selectedStudentID", studentID);
		return new ModelAndView("assign-coursegrade","courseGrade",new CourseGrade());
	}
	
	@RequestMapping(value = "/gradeassigned", method = RequestMethod.POST)
	protected String submitCourseGrades(HttpServletRequest request, @ModelAttribute("courseGrade") CourseGrade courseGrade, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		Long personID = (Long)session.getAttribute("selectedStudentID");
		System.out.println("Selected student ID for grading: " + personID);
		
		Person person = userDAO.getPersonWithID(personID);
		
		Long selectedCourseID = (Long) session.getAttribute("selectedCourseID");
		Courses course = coursesDAO.get(selectedCourseID);
		
		courseGrade.setCourses(course);
		courseGrade.setPerson(person);
		
		CourseGrade cg = courseGradeDAO.saveCourseGrade(courseGrade);
		return "success";
	}
	
	@RequestMapping(value = "/grades", method = RequestMethod.GET)
	protected String viewMyGrades(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
			
		if(user.getType().equals("student")){
			Person person = userDAO.getPersonWithID(user.getPersonID());
			List<CourseGrade> studentGrades = courseGradeDAO.getStudentGrades(person);
			session.setAttribute("studentGrades", studentGrades);
			return "display-mygrades";
		}
		else
		return "display-courses";
	}
}
