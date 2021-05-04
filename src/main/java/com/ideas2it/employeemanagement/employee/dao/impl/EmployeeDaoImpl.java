package com.ideas2it.employeemanagement.employee.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * It is used to store and retrieves data to database
 *
 * @author vignesh r
 * @created at 13-03-2021
 */
public class EmployeeDaoImpl implements EmployeeDao {
	private EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
	// public EmployeeDaoImpl() {}

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
		} catch (HibernateException e) {
			logger.logError(e);
			throw new EmployeeManagementException("creation Unsuccessful");
		} finally {
			DatabaseConnection.sessionClose(session);
		}
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
			for (Address address : employee.getAddressess()) {
			}
		} catch (HibernateException e) {
			logger.logError(e);
			throw new EmployeeManagementException("something went wrong");
		} finally {
			DatabaseConnection.sessionClose(session);
		}
		return employee;
	}

	/**
	 * {inheritDoc}
	 * 
	 * @throws FetchException
	 */
	@Override
	public Employee getSpecificEmployeeWithProjects(int id) throws EmployeeManagementException {
		Employee employee = null;
		Session session = null;
		try {
			session = DatabaseConnection.getSessionFactory().openSession();
			employee = session.get(Employee.class, id);
			for (Project project : employee.getProjects()) {
			}
		} catch (HibernateException e) {
			logger.logError(e);
			throw new EmployeeManagementException("something went wrong");
		} finally {
			DatabaseConnection.sessionClose(session);
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
		} catch (HibernateException e) {
			logger.logError(e);
			throw new EmployeeManagementException("something went wrong");
		} finally {
			DatabaseConnection.sessionClose(session);
		}
		return employee;
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployee() throws EmployeeManagementException {
		Session session = null;
		List<Employee> employees = null;
		String getAllEmployeesQuery = "FROM Employee";
		try {
			session = DatabaseConnection.getSessionFactory().openSession();
			//employees = session.createCriteria(Employee.class).list();
			employees = session.createQuery(getAllEmployeesQuery).getResultList();
			for (Employee employee : employees) {
				for (Address addresses : employee.getAddressess()) {
				}
			}
		} catch (Exception e) {
			logger.logError(e);
			throw new EmployeeManagementException("something went wrong");
		} finally {
			DatabaseConnection.sessionClose(session);
		}
		return employees;
	}

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
		} catch (HibernateException e) {
			logger.logError(e);
			count = 0;
		} finally {
			DatabaseConnection.sessionClose(session);
		}
		return 0 != count;
	}
}