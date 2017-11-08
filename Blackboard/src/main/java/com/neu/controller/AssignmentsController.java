package com.neu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
import com.neu.dao.UserDAO;
import com.neu.pojo.Assignments;
import com.neu.pojo.Courses;
import com.neu.pojo.Submission;
import com.neu.pojo.User;
import com.neu.validator.AssignmentValidator;

@Controller
public class AssignmentsController {
	
	@Autowired
	@Qualifier("assignmentsDAO")
	AssignmentsDAO assignmentsDAO;
	
	@Autowired
	@Qualifier("coursesDAO")
	CoursesDAO coursesDAO;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("assignmentValidator")
	AssignmentValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/create-assignment", method = RequestMethod.GET)
	protected ModelAndView goToCreateAssignment(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			session.setAttribute("errorMessage", "Please log in first");
			return new ModelAndView("error");
		}
		System.out.println("create new assignment");
		return new ModelAndView("create-assignment","assignment",new Assignments());
	}
	
	@RequestMapping(value="/create-assignment", method = RequestMethod.POST)
	protected ModelAndView createNewAssignment(HttpServletRequest request,  @ModelAttribute("assignment") Assignments assignment, BindingResult result) throws Exception{
		validator.validate(assignment, result);
		if(result.hasErrors()){
			return new ModelAndView("create-assignment");
		}
		
		try {
			CommonsMultipartFile assignmentFile = assignment.getAssignmentFile();
			String fileName = assignmentFile.getOriginalFilename();
			File localFile = new File("C:/assignments/", fileName);

			assignmentFile.transferTo(localFile);
			String filePath = localFile.getPath();
			assignment.setAssignmentFilename(filePath);
			
			HttpSession session = (HttpSession) request.getSession();
			long selectedCourseID = (Long)session.getAttribute("selectedCourseID");
			Courses course = coursesDAO.get(selectedCourseID);
			assignment.setCourse(course);
			
			User user = (User)session.getAttribute("user");
			assignment.setPerson(user);;
			Assignments a = assignmentsDAO.createAssignment(assignment);
			
			return new ModelAndView("success");
			
		}
		
		catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "Error while creating new assignment");
		}
	}

	@RequestMapping(value = "/viewquestions", method = RequestMethod.GET)
	protected void goToViewQuestions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		ServletContext context = request.getSession().getServletContext();
		User user = (User)session.getAttribute("user");
		if(user == null){
			session.setAttribute("errorMessage", "Please log in first");
			//return new ModelAndView("error");
		}
		System.out.println("View questions posted");
		
		Long assignmentID = (Long)session.getAttribute("selectedAssignmentID");
		Assignments assignment = assignmentsDAO.getAssignmentWithAssignmentID(assignmentID);
		String questionsFilePath = assignment.getAssignmentFilename();
		
		File questionsFile = new File(questionsFilePath);
		   FileInputStream inputStream = new FileInputStream(questionsFile);

		   // get MIME type of the file
		   String mimeType = context.getMimeType(questionsFilePath);
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
}
