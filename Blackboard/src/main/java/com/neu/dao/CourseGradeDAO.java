package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.Assignments;
import com.neu.pojo.CourseGrade;
import com.neu.pojo.Person;
import com.neu.pojo.Submission;


public class CourseGradeDAO extends DAO{
	public CourseGrade saveCourseGrade(CourseGrade courseGrade) throws Exception {
		try {
			begin();			
			getSession().save(courseGrade);
			commit();
			return courseGrade;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while saving course Grade: " + e.getMessage());
		}
	}
	
	public List<CourseGrade> getStudentGrades(Person person) throws Exception {
		try {
			begin();
			Query q1 = getSession().createQuery("from CourseGrade where person=:person");
			q1.setEntity("person", person);
			List<CourseGrade> submission = q1.list();
			commit();
			return submission;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get student grades ", e);
		}
	}
}
