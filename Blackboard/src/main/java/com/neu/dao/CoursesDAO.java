package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.exception.UserException;
import com.neu.pojo.Courses;
import com.neu.pojo.Person;

public class CoursesDAO extends DAO {

	public List<Object[]> list(long personID) throws UserException {

		try {
			begin();
			// Query q = getSession().createQuery("select c.courseName from
			// Courses c join c.enrolledStudents p WHERE p.personID=:personID");
			// q.setLong("personID", personID);
			// List<String> studentCourses = q.list();
			Query q = getSession().createQuery(
					"select c.courseID, c.courseName from Courses c join c.enrolledStudents p WHERE p.personID=:personID");
			q.setLong("personID", personID);
			List<Object[]> studentCourses = q.list();
			return studentCourses;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not Display courses", e);
		}

	}

	public Courses get(long courseID) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Courses where courseID= :courseID");
			q.setLong("courseID", courseID);
			Courses course = (Courses) q.uniqueResult();
			commit();
			return course;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get course " + courseID, e);
		}
	}

}
