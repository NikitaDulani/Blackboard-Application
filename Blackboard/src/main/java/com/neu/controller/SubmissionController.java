package com.neu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AssignmentsDAO;
import com.neu.dao.CoursesDAO;
import com.neu.dao.SubmissionDAO;
import com.neu.dao.UserDAO;
import com.neu.pojo.Assignments;
import com.neu.pojo.Courses;
import com.neu.pojo.Person;
import com.neu.pojo.Submission;
import com.neu.pojo.User;
import com.neu.validator.SubmissionValidator;


@Controller
public class SubmissionController {
	
	@Autowired
	@Qualifier("submissionDAO")
	SubmissionDAO submissionDAO;
	
	@Autowired
	@Qualifier("assignmentsDAO")
	AssignmentsDAO assignmentsDAO;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("submissionValidator")
	SubmissionValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/assignment/view/student/*", method = RequestMethod.GET)
	protected ModelAndView goToStudentAssignmentView(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return new ModelAndView("error");
		}
		String[] uri = request.getRequestURI().split("student/");
		Long selectedAssignmentID = Long.parseLong(uri[1]);
		System.out.println("Student selected Assignment ID: " + selectedAssignmentID);
		session.setAttribute("selectedAssignmentID", selectedAssignmentID);
		
		Person person = userDAO.getPersonWithID(user.getPersonID());
		
		Assignments assignment = assignmentsDAO.getAssignmentWithAssignmentID(selectedAssignmentID);
		
		Submission submission = submissionDAO.getSubmissionsWithAssignmentAndStudent(assignment, person);
		if(submission!=null){
			session.setAttribute("assignmentGrade", submission.getAssignmentGrade());
		}
		
		return new ModelAndView("assignment-viewstudent","submission",new Submission());
	}
	
	@RequestMapping(value="/submitAssignment", method = RequestMethod.POST)
	protected ModelAndView submitAssignment(HttpServletRequest request,  @ModelAttribute("submission") Submission submission, BindingResult result) throws Exception{
		
		try {
			CommonsMultipartFile submittedFile = submission.getSubmittedFile();
			String fileName = submittedFile.getOriginalFilename();
			File localFile = new File("C:/assignments/", fileName);

			submittedFile.transferTo(localFile);
			String filePath = localFile.getPath();
			submission.setSubmittedFilename(filePath);
			
			HttpSession session = (HttpSession) request.getSession();
			long selectedAssignmentID = (Long)session.getAttribute("selectedAssignmentID");
			Assignments assignment = assignmentsDAO.getAssignmentWithAssignmentID(selectedAssignmentID);
			submission.setAssignment(assignment);
			submission.setSubmissionDate(new java.sql.Date(new java.util.Date().getTime()));
			
			User user = (User)session.getAttribute("user");
			submission.setPerson(user);
			Submission s = submissionDAO.createSubmission(submission);
			
			//user.getStudentAssignments().add(a);
			//userDAO.update(user);
			return new ModelAndView("success");
			
		}
		
		catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "Error while creating new assignment");
		}
	}
	
	@RequestMapping(value = "/assignment/view/professor/*", method = RequestMethod.GET)
	protected String goToAssignmentView(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		String[] uri = request.getRequestURI().split("professor/");
		Long selectedAssignmentID = Long.parseLong(uri[1]);
		System.out.println("Assignment ID: " + selectedAssignmentID);
		session.setAttribute("selectedAssignmentID", selectedAssignmentID);
		
		Assignments assignment = assignmentsDAO.getAssignmentWithAssignmentID(selectedAssignmentID);
		List<Submission> submissions= submissionDAO.getSubmissionsWithAssignment(assignment);
		session.setAttribute("submissions", submissions);
		
//		List<String> studentNames = new ArrayList<String>();
//		for (Submission submission:submissions){
//			User student = (User) submission.getUser();
//			
//		}
		return "assignment-viewprofessor";
	}
	
	@RequestMapping(value = "/submission/view/professor/*", method = RequestMethod.GET)
	protected void viewSubmissionFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		ServletContext context = request.getSession().getServletContext();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			//return "error";
		}
		String[] uri = request.getRequestURI().split("professor/");
		Long selectedPersonID = Long.parseLong(uri[1]);
		System.out.println("Selected Student ID: " + selectedPersonID);
		session.setAttribute("selectedPersonID", selectedPersonID);
		Person person = userDAO.getPersonWithID(selectedPersonID);
		
		Long assignmentID = (Long) session.getAttribute("selectedAssignmentID");
		Assignments assignment = assignmentsDAO.getAssignmentWithAssignmentID(assignmentID);
		
		Submission submission = submissionDAO.getSubmissionsWithAssignmentAndStudent(assignment, person);
		
		String submittedFilePath = submission.getSubmittedFilename();
		
		File submittedFile = new File(submittedFilePath);
		   FileInputStream inputStream = new FileInputStream(submittedFile);

		   // get MIME type of the file
		   String mimeType = context.getMimeType(submittedFilePath);
		   if (mimeType == null) {
		       // set to binary type if MIME mapping not found
		       mimeType = "application/pdf";
		   }
		   System.out.println("MIME type: " + mimeType);


		   String headerKey = "Content-Disposition";

		   //response.addHeader("Content-Disposition", "attachment;filename=report.pdf");
		   response.setContentType("application/pdf");

		   // get output stream of the response
		   OutputStream outStream = response.getOutputStream();

		   byte[] buffer = new byte[4*1024];
		   int bytesRead = -1;

		   // write bytes read from the input stream into the output stream
		   while ((bytesRead = inputStream.read(buffer)) != -1) {
		       outStream.write(buffer, 0, bytesRead);
		   }

		   inputStream.close();
		   outStream.close();
	}
	
	@RequestMapping(value = "/submission/grade/professor/*", method = RequestMethod.POST)
	protected String submitAssignmentGrades(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("errorMessage", "Please log in first");
			return "error";
		}
		String[] uri = request.getRequestURI().split("professor/");
		Long personID = Long.parseLong(uri[1]);
		System.out.println("Selected student ID for grading: " + personID);
		session.setAttribute("selectedPersonID", personID);
		Person person = userDAO.getPersonWithID(personID);
		
		Long selectedAssignmentID = (Long) session.getAttribute("selectedAssignmentID");
		Assignments assignment = assignmentsDAO.getAssignmentWithAssignmentID(selectedAssignmentID);
		
		Submission submission = submissionDAO.getSubmissionsWithAssignmentAndStudent(assignment, person);
		Double assignmentGrade = Double.parseDouble(request.getParameter("assignmentGrade"));
		submission.setAssignmentGrade(assignmentGrade);
		
		submissionDAO.update(submission);
		return "success";
	}
}
