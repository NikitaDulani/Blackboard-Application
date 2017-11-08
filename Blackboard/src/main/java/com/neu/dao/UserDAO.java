package com.neu.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.exception.UserException;
import com.neu.pojo.Courses;
import com.neu.pojo.Person;
import com.neu.pojo.User;

public class UserDAO extends DAO{
	
	public UserDAO(){
	}
	
	public User get(String userName, String password) throws UserException{
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :userName and password = :password");
			q.setString("userName", userName);
			q.setString("password", password);
			User user = (User)q.uniqueResult();
			commit();
			return user;
		}
		catch(HibernateException e){
			rollback();
			throw new UserException("Could not get user " + userName, e);
		}
	}
	
	public User register(User u) throws UserException{
		try {
			begin();
			User user = new User();
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setPassword(u.getPassword());
			user.setType(u.getType());
			user.setVerified(false);
			user.setEmail(u.getEmail());
			user.setUserName(u.getUserName());
			
			getSession().save(user);
			commit();
			return user;
		}
		catch(HibernateException e){
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void update(User user) throws UserException {
        try {
            begin();
            getSession().update(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not save the user", e);
        }
    }
	
	public Person getPersonWithID(Long personID) throws UserException{
		try {
			begin();
			Query q = getSession().createQuery("from Person where personID = :personID");
			q.setLong("personID", personID);
			Person user = (Person)q.uniqueResult();
			commit();
			return user;
		}
		catch(HibernateException e){
			rollback();
			throw new UserException("Could not get user " + personID, e);
		}
	}
	
	
	public List<Object[]> getStudentsinCourse(long courseID) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("select p.personID, p.firstName, p.lastName from Person p join p.studentCourses c where c.courseID= :courseID and type='student'");
			q.setLong("courseID", courseID);
			List<Object[]> personList = q.list();
			commit();
			return personList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get students enrolled " + courseID, e);
		}
	}
}
