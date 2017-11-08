package com.neu.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="assignments_table")
public class Assignments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assignmentID", unique = true, nullable = false )
	private long assignmentID;
	
//	@ManyToMany
//	@JoinTable(
//			name = "students_assignment_table",
//			joinColumns = {@JoinColumn(name="assignmentID", nullable = false, updatable = false)},
//			inverseJoinColumns = {@JoinColumn(name="personID" )}
//			)
//	private Set<Person> studentsAssignments = new HashSet<Person>();
	
	@ManyToOne
	@JoinColumn(name = "personID", nullable = false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "courseID", nullable = false)
	private Courses course;
	
	@OneToMany(mappedBy = "assignment")
    private List<Submission> submission  = new ArrayList<Submission>();
	
	
	private String title;
	
	private Date dueDate;
	
	private String assignmentFilename;
	
	@Transient
	private CommonsMultipartFile assignmentFile;

	public long getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(long assignmentID) {
		this.assignmentID = assignmentID;
	}

//	public Set<Person> getStudentsAssignments() {
//		return studentsAssignments;
//	}
//
//	public void setStudentsAssignments(Set<Person> studentsAssignments) {
//		this.studentsAssignments = studentsAssignments;
//	}

	
	
	public String getTitle() {
		return title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public String getAssignmentFilename() {
		return assignmentFilename;
	}

	public void setAssignmentFilename(String assignmentFilename) {
		this.assignmentFilename = assignmentFilename;
	}

	public CommonsMultipartFile getAssignmentFile() {
		return assignmentFile;
	}

	public void setAssignmentFile(CommonsMultipartFile assignmentFile) {
		this.assignmentFile = assignmentFile;
	}

	public List<Submission> getSubmission() {
		return submission;
	}

	public void setSubmission(List<Submission> submission) {
		this.submission = submission;
	}
	
}
