package com.ideas2it.employeemanagement.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ideas2it.employeemanagement.employee.dao.EmployeeDao;
import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.ProjectService;
import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * This class for employee business logic
 *
 * @author vignesh r
 * @version 1.0 created at 13-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;
	private ProjectService projectService;

	public EmployeeServiceImpl(EmployeeDao employeeDao, ProjectService projectService) {
		this.employeeDao = employeeDao;
		this.projectService = projectService;
	}

	public EmployeeServiceImpl() {
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public void insertEmployee(Employee employee) throws EmployeeManagementException {
		employeeDao.insertEmployee(employee);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public boolean validatePhoneNumber(long phoneNumber) {
		return Long.toString(phoneNumber).matches("[7-9][0-9]{9}");
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public boolean validateEmail(String emailId) {
		return emailId.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+" + "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public boolean validateDob(String dob) {
		return dob.matches("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public Employee getSpecificEmployee(int id) throws EmployeeManagementException {
		return employeeDao.getSpecificEmployeeWithAddressess(id);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployee() throws EmployeeManagementException {
		return employeeDao.getAllEmployee();
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public void deleteEmployee(int id) throws EmployeeManagementException {
		Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
		employee.setIsDelete(false);
		employee.getAddressess().forEach((address) -> {
			address.setIsDelete(false);
		});
		employee.setProjects(null);
		if (!(employeeDao.updateEmployee(employee))) {
			throw new EmployeeManagementException("Delete Unsuccessful");
		}
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public void restoreEmployee(int id) throws EmployeeManagementException {
		Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
		employee.setIsDelete(true);
		employee.getAddressess().forEach((address) -> {
			address.setIsDelete(true);
		});
		if (!(employeeDao.updateEmployee(employee))) {
			throw new EmployeeManagementException("Restore Unsuccessful");
		}
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public void updateEmployee(Employee employee) throws EmployeeManagementException {
		Employee employeeDetails = employeeDao.getSpecificEmployeeWithAddressess(employee.getId());
		employeeDetails.setName(employee.getName());
		employeeDetails.setDesgination(employee.getDesgination());
		employeeDetails.setEmail(employee.getEmail());
		employeeDetails.setPhoneNumber(employee.getPhoneNumber());
		employeeDetails.setSalary(employee.getSalary());
		employeeDetails.setDob(employee.getDob());
		employeeDetails.setAddressess(employee.getAddressess());
		if (!(employeeDao.updateEmployee(employeeDetails))) {
			throw new EmployeeManagementException("Update Unsuccessful");
		}
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Project> getAllProjectDetails() throws EmployeeManagementException {
		ProjectService projectService = new ProjectServiceImpl();
		return projectService.getAllProject();
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public void assignEmployee(int empId, List<Integer> projectIds) throws EmployeeManagementException {
		//ProjectService projectService = new ProjectServiceImpl();
		Employee employeeDetails = employeeDao.getSpecificEmployeeWithProjects(empId);
		List<Project> projectDetails = new ArrayList<Project>();
		for (Integer projectId : projectIds) {
			projectDetails.add(projectService.getSpecificProjectDetails(projectId));
		}
		employeeDetails.setProjects(projectDetails);
		if (!(employeeDao.updateEmployee(employeeDetails))) {
			throw new EmployeeManagementException("Assign Unsuccessful");
		}
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public Employee getSpecificEmployeeDetails(int id) throws EmployeeManagementException {
		return employeeDao.getSpecificEmployee(id);
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Employee> getAllEmployeeDetails() throws EmployeeManagementException {
		return employeeDao.getAllEmployee();
	}

	/**
	 * {inheritDoc}
	 */
	@Override
	public List<Project> getAssignedEmployeeDetails(int id) throws EmployeeManagementException {
		Employee employee = employeeDao.getSpecificEmployeeWithProjects(id);
		return employee.getProjects();
	}
}