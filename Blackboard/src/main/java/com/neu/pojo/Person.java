package com.neu.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personID", unique = true, nullable = false)
	private long personID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "type")
	private String type;

	@Column(name = "email")
	private String email;

	private Boolean verified;

	// Mapping of students attending courses
	@ManyToMany(mappedBy = "enrolledStudents")
	private List<Courses> studentCourses = new ArrayList<Courses>();

	@OneToMany(mappedBy = "person")
	private List<Assignments> assignments = new ArrayList<Assignments>();

	@OneToMany(mappedBy = "person")
	private List<Submission> submission = new ArrayList<Submission>();

	@OneToMany(mappedBy = "person")
	private List<CourseGrade> courseGrade = new ArrayList<CourseGrade>();
	
	public Person() {
	}

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public List<Courses> getStudentCourses() {
		return studentCourses;
	}

	public List<Assignments> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignments> assignments) {
		this.assignments = assignments;
	}

	public void setStudentCourses(List<Courses> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public List<Submission> getSubmission() {
		return submission;
	}

	public void setSubmission(List<Submission> submission) {
		this.submission = submission;
	}

	public List<CourseGrade> getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(List<CourseGrade> courseGrade) {
		this.courseGrade = courseGrade;
	}

}
