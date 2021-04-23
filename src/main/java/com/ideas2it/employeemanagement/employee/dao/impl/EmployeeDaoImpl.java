package com.ideas2it.employeemanagement.employee.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Session;

//import com.ideas2it.employeemanagement.employee.controller.EmployeeController;
import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;


/**
 * It is used to store and retrives datas to database
 *
 * @author vignesh r
 * @created at 13-03-2021 
 */  
public class EmployeeDaoImpl implements EmployeeDao {
	 private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
    
    /**
     * {inheritDoc}
     */
    @Override           
    public void insertEmployee(Employee employee) throws EmployeeManagementException {
        
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch(HibernateException e) {
        	logger.logError(e);
            throw new EmployeeManagementException("creation Unsuccessful");
        } finally{
			try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
    }

    /**
     * {inheritDoc}
     * @throws EmployeeManagementException 
     */
    @Override
    public boolean isEmployeeExist(int id) throws EmployeeManagementException {
        Employee employee = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("isDelete", true));
            employee = (Employee) criteria.uniqueResult();
        } catch(HibernateException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("something went wrong");
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
        return null != employee;
    }

    /**
     * {inheritDoc}
     */
    @Override  
    public Employee getSpecificEmployeeWithAddressess(int id) throws EmployeeManagementException {
        Employee employee = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            employee = session.get(Employee.class, id);
            for (Address address : employee.getAddressess()) {}   
        }catch(HibernateException e) {
        	logger.logError(e);
            throw new EmployeeManagementException("something went wrong");
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
        return employee;
    }

    /**
     * {inheritDoc}
     * @throws FetchException 
     */
    @Override  
    public Employee getSpecificEmployeeWithProjects(int id) throws EmployeeManagementException {
        Employee employee = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            employee = session.get(Employee.class, id);
            for (Project project : employee.getProjects()) {}   
        }catch(HibernateException e) {
        	logger.logError(e);
        	throw new EmployeeManagementException("something went wrong");
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
        return employee;
    }

    /**
     * {inheritDoc}
     */
    @Override  
    public Employee getSpecificEmployee(int id) throws EmployeeManagementException {
        Employee employee = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            employee = session.get(Employee.class, id);   
        }catch(HibernateException e) {
        	logger.logError(e);
           throw new EmployeeManagementException("something went wrong");
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
        return employee;
    }

    /**
     * {inheritDoc}
     * @throws FetchException 
     */
    @Override
    public List<Employee> getAllEmployee() throws EmployeeManagementException {
        Session session = null;
        List<Employee> employees = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            employees = session.createCriteria(Employee.class).list();
            for (Employee employee : employees) {
                for (Address addresses : employee.getAddressess()) {}   
            }            
        } catch(Exception e) {
        	logger.logError(e);
        	 throw new EmployeeManagementException("something went wrong");
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (Exception e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
        return employees;
    }

    /**
     * {inheritDoc}
     */
    /*public boolean isEmployeeDeleted(int id) {
        Employee employee = null;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.eq("isDelete", false));
            employee = (Employee) criteria.uniqueResult();
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
        return null != employee;
    }*/

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(Employee employee) {
        int count = 0;
        Session session = null;
        try {
            session = DatabaseConnection.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            count = 1;
        } catch(HibernateException e) {
        	logger.logError(e);
            e.printStackTrace();
            count = 0;
        } finally {
        	try {
				if (null != session) {
					session.close();
				}
			} catch (HibernateException e) {
				logger.logError(e);
				e.printStackTrace();
			}
        }
        return 0 != count;    
    }
}