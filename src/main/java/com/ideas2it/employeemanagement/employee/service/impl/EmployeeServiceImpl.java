package com.ideas2it.employeemanagement.employee.service.impl;

import java.sql.Date;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

//import com.ideas2it.employeemanagement.employee.service.impl.*;
/**
 * This class for employee business logic
 *
 * @author vignesh r
 * @version 1.0 created at 13-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService { 
    private EmployeeDao employeeDao = new EmployeeDaoImpl(); 
    private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
    
    /**
     * {inheritDoc}
     */
    @Override
	public void insertEmployee(String name, String desgination, String emailId, long phoneNumber, float salary,
			Date dob, List<List<String>> employeeAddressDetails) throws EmployeeManagementException {
		try {
			List<Address> addressDetails = new ArrayList<Address>();
			for (List<String> address : employeeAddressDetails) {
				addressDetails.add(new Address(address.get(0), address.get(1), address.get(2), address.get(3),
						address.get(4), address.get(5), address.get(6), true));
			}
			Employee employee = new Employee(name, desgination, emailId, phoneNumber, salary, dob, true,
					addressDetails);
			employeeDao.insertEmployee(employee);
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("creation Unsuccessful");
		}
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
        return emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
                              "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
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
    public boolean isEmployeeExist(int id) throws EmployeeManagementException {
    	return employeeDao.isEmployeeExist(id);
    }

    /**
     * {inheritDoc}
     */ 
    @Override
    public Employee getSpecificEmployee(int id) throws EmployeeManagementException {
        return  employeeDao.getSpecificEmployeeWithAddressess(id);
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
		try {
			Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
			employee.setIsDelete(false);
			employee.getAddressess().forEach((address) -> {
				address.setIsDelete(false);
			});
			employee.setProjects(null);
			if (!(employeeDao.updateEmployee(employee))) {
				throw new EmployeeManagementException("Delete Unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Delete Unsuccessful");
		}
	}

    /**
     * {inheritDoc}
     */ 
    /*public List<List<String>> getDeletedEmployeeDetails() throws FetchException {
        List<List<String>> employees = new ArrayList<List<String>>();
    	
        employeeDao.getAllEmployee().forEach((employee) -> {
    	    if (false == employee.getIsDelete()) {
		        List<String> employeeDetails = new ArrayList<String>();
		        employeeDetails.add("" + employee.getId());
		        employeeDetails.add("" + employee.getName());
		        employeeDetails.add("" + employee.getDesgination());
		        employeeDetails.add("" + employee.getEmail());
		        employeeDetails.add("" + employee.getPhoneNumber());
		        employeeDetails.add("" + employee.getDob());
		        employeeDetails.add("" + employee.getSalary());
		        employees.add(employeeDetails);
    	    }
	    });

        return employees;
    }*/

    /**
     * {inheritDoc}
     */ 
   /* public boolean isEmployeeDeleted(int id) {
        return employeeDao.isEmployeeDeleted(id);
    }*/

    /**
     * {inheritDoc}
     */ 
    @Override
	public void restoreEmployee(int id) throws EmployeeManagementException {
		try {
			Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
			employee.setIsDelete(true);
			employee.getAddressess().forEach((address) -> {
				address.setIsDelete(true);
			});
			if (!(employeeDao.updateEmployee(employee))) {
				throw new EmployeeManagementException("Restore Unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Restore Unsuccessful");
		}
	}

    /** 
     * {inheritDoc}
     */
    @Override
	public void updateEmployee(int id, String name, String desgination, String email, long phoneNumber, float salary,
			Date dob, List<List<String>> addressess) throws EmployeeManagementException {
		try {
			Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
			employee.setId(id);
			employee.setName(name);
			employee.setDesgination(desgination);
			employee.setEmail(email);
			employee.setPhoneNumber(phoneNumber);
			employee.setSalary(salary);
			employee.setDob(dob);
			List<Address> oldAddresess = employee.getAddressess();
			List<Address> addressDetails = new ArrayList<Address>();

			if (1 == addressess.size()) {
				List<String> address = addressess.get(0);
				oldAddresess.get(0).setId(Integer.parseInt(address.get(0)));
				oldAddresess.get(0).setDoorNo(address.get(1));
				oldAddresess.get(0).setStreetName(address.get(2));
				oldAddresess.get(0).setCity(address.get(3));
				oldAddresess.get(0).setDistrict(address.get(4));
				oldAddresess.get(0).setState(address.get(5));
				oldAddresess.get(0).setCountry(address.get(6));
				oldAddresess.get(0).setAddressMode(address.get(7));
				oldAddresess.get(0).setEmployee(employee);
				employee.setAddressess(oldAddresess);
			} else {
				for (List<String> address : addressess) {
					Address addressValues = new Address(Integer.parseInt(address.get(0)), address.get(1),
							address.get(2), address.get(3), address.get(4), address.get(5), address.get(6),
							address.get(7), true);
					addressValues.setEmployee(employee);
					addressDetails.add(addressValues);
					addressValues.setEmployee(employee);
				}
				employee.setAddressess(addressDetails);
			}
			if (!(employeeDao.updateEmployee(employee))) {
				throw new EmployeeManagementException("Update Unsuccessful");
			}
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Update Unsuccessful");
		}
		
	}

    /**
     * {inheritDoc}
     */ 
   /* public Map<Integer, String>  getEmployeeAddressDetails(int id) throws FetchException {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
         Map<Integer, String> addressDetails = new LinkedHashMap<Integer, String>();
        int index = 0;
        for (Address address : employee.getAddressess()) {
            index++;
            if (true == address.getIsDelete()) {
                addressDetails.put(address.getId(), "Address-no- " + Integer.toString(index) 
                        + address.toString());
            }     
        } 
        return addressDetails;
    }*/

    /**
     * {inheritDoc} 
     */ 
   /* public boolean updateEmployeeAddress(int addressIndex, int addressId, String[] addressDetail, int id) throws EmployeeManagementException {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        employee.setId(id);
        List<Address> addressDetails = employee.getAddressess();
        addressDetails.set(addressIndex, new Address(addressId, addressDetail[0], addressDetail[1], 
                addressDetail[2],addressDetail[3], addressDetail[4], 
                addressDetail[5], addressDetail[6], true));
        employee.setAddressess(addressDetails);
        return employeeDao.updateEmployee(employee);
    }*/

    /**
     * {inheritDoc}
     */ 
    @Override
    public List<Project> getAllProjectDetails() throws EmployeeManagementException{
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        return projectService.getAllProject();
    }

    /**
     * {inheritDoc}
     */ 
   /* public void isProjectExist(int projectId) throws NoIdExistException{
        ProjectService projectService = new ProjectServiceImpl();
        if (!(projectService.isProjectExist(projectId))) {
        	throw new NoIdExistException("Project id doesn't exist");
        }
    }*/

    /**
     * {inheritDoc} 
     */ 
	@Override
	public void assignEmployee(int empId, List<Integer> projectIds) throws EmployeeManagementException {
		try {
			ProjectServiceImpl projectService = new ProjectServiceImpl();
			Employee employeeDetails = employeeDao.getSpecificEmployeeWithProjects(empId);
			List<Project> projectDetails = new ArrayList<Project>();
			for (Integer projectId : projectIds) {
				projectDetails.add(projectService.getSpecificProjectDetails(projectId));
			}
			employeeDetails.setProjects(projectDetails);
			if (!(employeeDao.updateEmployee(employeeDetails))) {
				throw new EmployeeManagementException("Assign Unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Assign Unsuccessful");
		}
	}

    /**
     * {inheritDoc} 
     */ 
    /*public boolean addEmployeeAddress(int employeeId, String[] addressDetail) throws FetchException {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(employeeId);
        List<Address> addressDetails = employee.getAddressess();
        addressDetails.add(new Address(addressDetail[0], addressDetail[1], 
                addressDetail[2],addressDetail[3], addressDetail[4], 
                addressDetail[5], addressDetail[6], true));
        employee.setAddressess(addressDetails);
        return  employeeDao.updateEmployee(employee);             
    }*/

    /**
     * {inheritDoc}
     */ 
    @Override
    public Employee getSpecificEmployeeDetails(int id) throws EmployeeManagementException{
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

    /** 
     * {inheritDoc}
     *
   /* public List<String> getAssignedEmployee(int id) throws EmployeeManagementException {
        Employee employee = employeeDao.getSpecificEmployeeWithProjects(id);
        List<String> employeeDetails =  new ArrayList<String>();
        employeeDetails.add(employee.toString());
        List<Project> projectDetails = employee.getProjects();
        if (!projectDetails.isEmpty()) {
            for (Project project : projectDetails) {
                employeeDetails.add(project.toString());    
            } 
        } else {
            employeeDetails.add("No employee assigned to this project");
        }      
        return employeeDetails;
    }*/

    /**
     * {inheritDoc}
     */ 
   /* public boolean unAssignEmployee(int id, int projectId) {
        Employee employee = employeeDao.getSpecificEmployeeWithProjects(id);
        List<Project> projectDetails = employee.getProjects();
        for (Project project : projectDetails) {
            if (projectId == project.getId()) {
                projectDetails.remove(project);
                break;
            }
        }
        employee.setProjects(projectDetails);
        return employeeDao.updateEmployee(employee);
    } */
}