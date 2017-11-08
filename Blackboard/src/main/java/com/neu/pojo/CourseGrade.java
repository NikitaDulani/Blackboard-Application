package com.neu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="coursegrade_table")
public class CourseGrade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gradeID", unique = true, nullable = false )
	private long gradeID;
	
	@ManyToOne
	@JoinColumn(name = "personID", nullable = false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "courseID", nullable = false)
	private Courses courses;
	
	private double courseGrade;

	public long getGradeID() {
		return gradeID;
	}

	public void setGradeID(long gradeID) {
		this.gradeID = gradeID;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public double getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(double courseGrade) {
		this.courseGrade = courseGrade;
	}
	
}
