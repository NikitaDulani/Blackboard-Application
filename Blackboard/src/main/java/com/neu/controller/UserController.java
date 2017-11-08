package com.neu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Qualifier;

import com.neu.pojo.Courses;
import com.neu.pojo.User;
import com.neu.validator.UserValidator;
import com.neu.dao.CoursesDAO;
import com.neu.dao.UserDAO;
import com.neu.exception.UserException;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("coursesDAO")
	CoursesDAO coursesDAO;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		List<Object[]> courses = coursesDAO.list(user.getPersonID());
		session.setAttribute("courses", courses);
		return "user-home";
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			System.out.print("loginUser");
			User user = userDAO.get(request.getParameter("userName"), request.getParameter("password"));	
			if(user == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error");
			}
			session.setAttribute("user", user);
			
			//add courses to session, to display on home page
			List<Object[]> courses = coursesDAO.list(user.getPersonID());
			//session.setAttribute("courses", courses);
			//request.setAttribute("courses", courses);
			return new ModelAndView("user-home", "courses",courses);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("error");
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}
	
	@RequestMapping(value="/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception{
		validator.validate(user, result);
		if(result.hasErrors()){
			return new ModelAndView("register-user", "user", user);
		}
		
		try {
			User u = userDAO.register(user);
			request.getSession().setAttribute("user", u);
			return new ModelAndView("user-home","user",u);
			
		}
		
		catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	protected String logout(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		return "logout";
	}
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.GET)
	protected String sendEmail(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		List<Object[]> courses = coursesDAO.list(user.getPersonID());
		session.setAttribute("courses", courses);
		return "display-courses";
	}
}
