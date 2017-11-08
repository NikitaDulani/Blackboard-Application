package com.neu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.dao.AssignmentsDAO;
import com.neu.dao.CoursesDAO;
import com.neu.dao.UserDAO;
import com.neu.pojo.Assignments;
import com.neu.pojo.Courses;
import com.neu.pojo.Person;
import com.neu.pojo.User;

@Controller
public class CoursesController {

	@Autowired
	@Qualifier("coursesDAO")
	CoursesDAO coursesDAO;

	@Autowired
	@Qualifier("assignmentsDAO")
	AssignmentsDAO assignmentsDAO;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDAO;

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	protected String goToDisplayCourses(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		List<Object[]> courses = coursesDAO.list(user.getPersonID());
		for (int i = 0; i < courses.size(); i++) {

		}
		session.setAttribute("courses", courses);
		return "display-courses";
	}

	@RequestMapping(value = "/course/view/*", method = RequestMethod.GET)
	protected String goToCourseView(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		String[] uri = request.getRequestURI().split("view/");
		Long selectedCourseID = Long.parseLong(uri[1]);
		System.out.println("Course ID: " + selectedCourseID);
		session.setAttribute("selectedCourseID", selectedCourseID);
		Courses course = coursesDAO.get(selectedCourseID);

		List<Assignments> assignments = assignmentsDAO.getAssignments(course);
		session.setAttribute("assignments", assignments);
		if (user.getType().equals("student")) {
			return "course-viewstudent";
		} else
			return "course-viewprofessor";
	}

	@RequestMapping(value = "/sendemailtostudents", method = RequestMethod.GET)
	protected String goTosendEmail(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}

		// Long selectedCourseID = (Long)
		// session.getAttribute("selectedCourseID");
		// Courses course = coursesDAO.get(selectedCourseID);
		// List<Object[]> students =
		// userDAO.getStudentsinCourse(selectedCourseID);
		// session.setAttribute("students", students);

		return "send-email";
	}

	@RequestMapping(value = "/submitemail", method = RequestMethod.POST)
	protected String submitEmail(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}

		Long selectedCourseID = (Long) session.getAttribute("selectedCourseID");
		Courses course = coursesDAO.get(selectedCourseID);
		List<Object[]> students = userDAO.getStudentsinCourse(selectedCourseID);

		String subject = request.getParameter("subject");
		String message = request.getParameter("message");

		List<Long> personIDs = new ArrayList<Long>();
		for (Object[] student : students) {
			personIDs.add((Long) student[0]);
		}

		List<Person> persons = new ArrayList<Person>();
		for (Long personID : personIDs) {
			Person person = userDAO.getPersonWithID(personID);
			persons.add(person);
		}
		try {
		for (Person person : persons) {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthentication("blackboardwebapplication@gmail.com", "web12345");
			email.setSSLOnConnect(true);
			email.setFrom(user.getEmail());
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(person.getEmail());
			email.send();
		}
		} catch (Exception e) {
            System.out.println("Email Exception" + e.getMessage());
        }
		return "success";
	}
}
