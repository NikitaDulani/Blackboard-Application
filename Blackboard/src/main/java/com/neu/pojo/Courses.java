package com.neu.pojo;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="courses_table")
public class Courses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "courseID", unique = true, nullable = false )
	private long courseID;
	
	@ManyToMany
	@JoinTable(
			name = "enrolledStudents_course_table",
			joinColumns = {@JoinColumn(name="courseID", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name="personID" )}
			)
	private Set<Person> enrolledStudents = new HashSet<Person>();
	
    @OneToMany(mappedBy = "course")
    private List<Assignments> assignments  = new ArrayList<Assignments>();
    
    @OneToMany(mappedBy = "courses")
	private List<CourseGrade> courseGrade = new ArrayList<CourseGrade>();
    
	@Column
	private int courseCode;
	
	@Column
	private String courseName;
	
	@Column
	private String courseTerm;

	public long getCourseID() {
		return courseID;
	}

	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}

	public Set<Person> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Set<Person> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTerm() {
		return courseTerm;
	}

	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}

	public List<Assignments> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignments> assignments) {
		this.assignments = assignments;
	}

	public List<CourseGrade> getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(List<CourseGrade> courseGrade) {
		this.courseGrade = courseGrade;
	}

}
