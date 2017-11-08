package com.neu.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="submission_table")
public class Submission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "submissionID", unique = true, nullable = false )
	private long submissionID;
	
	@ManyToOne
	@JoinColumn(name = "personID", nullable = false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "assignmentID", nullable = false)
	private Assignments assignment;
	
	private Date submissionDate;
	
	private double assignmentGrade;
	
	private String submittedFilename;
	
	@Transient
	private CommonsMultipartFile submittedFile;

	public long getSubmissionID() {
		return submissionID;
	}

	public void setSubmissionID(long submissionID) {
		this.submissionID = submissionID;
	}

	

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Assignments getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignments assignment) {
		this.assignment = assignment;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public double getAssignmentGrade() {
		return assignmentGrade;
	}

	public void setAssignmentGrade(double assignmentGrade) {
		this.assignmentGrade = assignmentGrade;
	}

	public String getSubmittedFilename() {
		return submittedFilename;
	}

	public void setSubmittedFilename(String submittedFilename) {
		this.submittedFilename = submittedFilename;
	}

	public CommonsMultipartFile getSubmittedFile() {
		return submittedFile;
	}

	public void setSubmittedFile(CommonsMultipartFile submittedFile) {
		this.submittedFile = submittedFile;
	}
	
}
