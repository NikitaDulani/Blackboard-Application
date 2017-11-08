package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.exception.UserException;
import com.neu.pojo.Assignments;
import com.neu.pojo.Person;
import com.neu.pojo.Submission;
import com.neu.pojo.User;

public class SubmissionDAO extends DAO{
	public Submission createSubmission(Submission submission) throws Exception {
		try {
			begin();			
			getSession().save(submission);
			commit();
			return submission;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating submission: " + e.getMessage());
		}
	}
	
	public List<Submission> getSubmissionsWithAssignment(Assignments assignment) throws Exception {
		try {
			begin();
			Query q1 = getSession().createQuery("from Submission where assignment=:assignment");
			q1.setEntity("assignment", assignment);
			List<Submission> submission = q1.list();
			commit();
			return submission;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get submissions ", e);
		}
	}
	
	public Submission getSubmissionsWithAssignmentAndStudent(Assignments assignment, Person person) throws Exception {
		try {
			begin();
			Query q1 = getSession().createQuery("from Submission where assignment=:assignment and person=:person");
			q1.setEntity("assignment", assignment);
			q1.setEntity("person", person);
			q1.setMaxResults(1);
			Submission submission = (Submission) q1.uniqueResult();
			commit();
			return submission;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get submissions ", e);
		}
	}
	
	public void update(Submission submission) throws Exception {
        try {
            begin();
            getSession().update(submission);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save the assignment grade", e);
        }
    }
}
