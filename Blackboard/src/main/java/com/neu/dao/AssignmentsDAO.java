package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.pojo.Assignments;
import com.neu.pojo.Courses;


public class AssignmentsDAO extends DAO{
	public Assignments createAssignment(Assignments assignment) throws Exception {
		try {
			begin();			
			getSession().save(assignment);
			commit();
			return assignment;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating assignment: " + e.getMessage());
		}
	}
	
	public List<Assignments> getAssignments(Courses course) throws Exception {
		try {
			begin();
			Query q1 = getSession().createQuery("from Assignments where course=:course");
			//q1.setLong("courseID", course.getCourseID());	
			q1.setEntity("course", course);
			List<Assignments> assignments =q1.list();
			commit();
			return assignments;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get assignments ", e);
		}
	}
	
	public Assignments getAssignmentWithAssignmentID(Long assignmentID) throws Exception {
		try {
			begin();
			Query q1 = getSession().createQuery("from Assignments where assignmentID=:assignmentID");
			q1.setLong("assignmentID", assignmentID);	
			//q1.setEntity("course", course);
			Assignments assignments =(Assignments) q1.uniqueResult();
			commit();
			return assignments;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get assignments ", e);
		}
	}
}
