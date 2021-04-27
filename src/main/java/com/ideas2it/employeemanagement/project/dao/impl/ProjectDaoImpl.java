package com.ideas2it.employeemanagement.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;


/**
 * It is used to store and retrives datas to database
 *
 * @author vignesh r
 * @version 1.0 24-03-2021
 */
public class ProjectDaoImpl implements ProjectDao {
	private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
	
    /**
     * {inheritDoc}
     * @throws CreationFailsException 
     */
    @Override
    public boolean insertProject(Project project) throws EmployeeManagementException {
        int count = 0;
        Session session = null; 
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            count = (Integer) session.save(project);
            session.getTransaction().commit();
        } catch(HibernateException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("create fails");  
        } finally{
        	DatabaseConnection.sessionClose(session);
        }
        return 0 != count;            
    }

    /**
     * {inheritDoc}
     * @throws EmployeeManagementException 
     */
    @Override
    public boolean isProjectExist(int id) throws EmployeeManagementException {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("isDelete", true));
            project = (Project) criteria.uniqueResult();
        } catch(HibernateException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("something went wrong");
        } finally {
        	DatabaseConnection.sessionClose(session);
        }
        return null != project;
    } 

    /**
     * {inheritDoc}
     * @throws FetchException 
     */
    @Override
    public Project getSpecificProject(int id) throws EmployeeManagementException {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            project = session.get(Project.class, id);    
        }catch(HibernateException e) {
        	logger.logError(e);
            throw new EmployeeManagementException("something went wrong");
        } finally {
        	DatabaseConnection.sessionClose(session);
        }
        return project;
    }

    /**
     * {inheritDoc}
     * @throws FetchException 
     */
    @Override
    public Project getSpecificProjectWithEmployee(int id) throws EmployeeManagementException {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            project = session.get(Project.class, id);
            for (Employee employee : project.getEmployees()){}    
        }catch(HibernateException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("something went wrong");
        } finally {
        	DatabaseConnection.sessionClose(session);
        }
        return project;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Project> getAllProject() throws EmployeeManagementException {
        List<Project> projects = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            projects = session.createCriteria(Project.class).list();   
        } catch(HibernateException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("something went wrong");
        } finally {
        	DatabaseConnection.sessionClose(session);
        }
        return projects;
    }

    /**
     * {inheritDoc}
     */
    /*public boolean isProjectDeleted(int id) {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("isDelete", false));
            project = (Project) criteria.uniqueResult();
        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			}
        }
        return null != project;
    }*/

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateProject(Project project) {
        int count = 0;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(project);
            session.getTransaction().commit();
            count = 1;
        } catch(HibernateException e) {
        	logger.logError(e);
            e.printStackTrace();
            count = 0;    
        } finally{
        	DatabaseConnection.sessionClose(session); 
        }
        return 0 != count;
    }
}