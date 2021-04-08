package com.ideas2it.employeemanagement.project.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;

/**
 * It is used to store and retrives datas to database
 *
 * @author vignesh r
 * @version 1.0 24-03-2021
 */
public class ProjectDaoImpl implements ProjectDao {
   
    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertProject(Project project) {
        int count = 0;
        Session session = null; 
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            count = (Integer) session.save(project);
            session.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();   
        } finally{
           if (null != session) {
                session.close();
            }
        }
        return 0 != count;            
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isProjectExist(int id) {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("isDelete", true));
            project = (Project) criteria.uniqueResult();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return null != project;
    } 

    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProject(int id) {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            project = session.get(Project.class, id);    
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return project;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProjectWithEmployee(int id) {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            project = session.get(Project.class, id);
            for (Employee employee : project.getEmployees()){}    
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return project;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Project> getAllProject() {
        List<Project> projects = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            projects = session.createCriteria(Project.class).list();   
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return projects;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isProjectDeleted(int id) {
        Project project = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Project.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("isDelete", false));
            project = (Project) criteria.uniqueResult();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return null != project;
    }

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
        } catch(Exception e) {
            e.printStackTrace();
            count = 0;    
        } finally{
            if (null != session) {
                session.close();
            }    
        }
        return 0 != count;
    }
}