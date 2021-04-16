package com.ideas2it.employeemanagement.employee.service.impl;

import java.sql.Date;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;

import com.ideas2it.employeemanagement.employee.service.impl.*;
/**
 * This class for employee business logic
 *
 * @author vignesh r
 * @version 1.0 created at 13-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService { 
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(); 

    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, float salary, Date dob, List<List<String>> employeeAddressDetails) {  
        List<Address> addressDetails = new ArrayList<Address>();
        for (List<String> address : employeeAddressDetails) {
        	addressDetails.add(new Address(address.get(0),address.get(1),address.get(2),
        			address.get(3), address.get(4), address.get(5), address.get(6),true));
        }
        Employee employee = new Employee(name, desgination, emailId, phoneNumber, 
                salary, dob, true, addressDetails); 
        return employeeDao.insertEmployee(employee); 
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
    public boolean isEmployeeExist(int id) {
        return employeeDao.isEmployeeExist(id);
    }

    /**
     * {inheritDoc}
     */ 
    public Map<String, String> getSpecificEmployee(int id) {
    	Map<String, String> employeeDetails = new LinkedHashMap<String, String>(); 
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        employeeDetails.put("employeeId", "" + employee.getId());
        employeeDetails.put("name", "" + employee.getName());
        employeeDetails.put("desgination", "" + employee.getDesgination());
        employeeDetails.put("email", "" + employee.getEmail());
        employeeDetails.put("phoneNumber", "" + employee.getPhoneNumber());
        employeeDetails.put("salary", "" + employee.getSalary());
        employeeDetails.put("dob", "" + employee.getDob());
        employeeDetails.put("permanentAddressId", "" + employee.getAddressess().get(0).getId());
        employeeDetails.put("permanentAddressDoorNo", "" + employee.getAddressess().get(0).getDoorNo());
        employeeDetails.put("permanentAddressStreetName", "" + employee.getAddressess().get(0).getStreetName());
        employeeDetails.put("permanentAddressCity", "" + employee.getAddressess().get(0).getCity());
        employeeDetails.put("permanentAddressDistrict", "" + employee.getAddressess().get(0).getDistrict());
        employeeDetails.put("permanentAddressState", "" + employee.getAddressess().get(0).getState());
        employeeDetails.put("permanentAddressCountry", "" + employee.getAddressess().get(0).getCountry());
        //employeeDetails.put("permanentAddress", "" + employee.getAddressess().get(0).toString());
        if (employee.getAddressess().size() > 1) {
        	 employeeDetails.put("temporaryAddressId", "" + employee.getAddressess().get(1).getId());
        	 employeeDetails.put("temporaryAddressDoorNo", "" + employee.getAddressess().get(1).getDoorNo());
        	 employeeDetails.put("temporaryAddressStreetName", "" + employee.getAddressess().get(1).getStreetName());
        	 employeeDetails.put("temporaryAddressCity", "" + employee.getAddressess().get(1).getCity());
        	 employeeDetails.put("temporaryAddressDistrict", "" + employee.getAddressess().get(1).getDistrict());
        	 employeeDetails.put("temporaryAddressState", "" + employee.getAddressess().get(1).getState());
        	 employeeDetails.put("temporaryAddressCountry", "" + employee.getAddressess().get(1).getCountry());
        }
        return  employeeDetails;
    }

    /**
     * {inheritDoc}
     */ 
    public List<String> getAllEmployee() {
        
        //List<List<String>> employees = new ArrayList<List<String>>();
    	List<String> employees = new ArrayList<String>();
        employeeDao.getAllEmployee().forEach((employee) -> {
        	   employees.add( employee.getId() + "," + employee.getName() + "," 
        			   + employee.getDesgination() + "," + employee.getEmail()
        			   + "," + employee.getPhoneNumber() + "," + employee.getDob()
        			   + "," + employee.getSalary() + "," + employee.getIsDelete());
	    });

        return employees;
}

    /**
     * {inheritDoc}
     */ 
    public boolean deleteEmployee(int id) {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        employee.setIsDelete(false);
        employee.getAddressess().forEach((address) -> {
	    address.setIsDelete(false);
	    });
        employee.setProjects(null); 
        return employeeDao.updateEmployee(employee);
    }

    /**
     * {inheritDoc}
     */ 
    public List<List<String>> getDeletedEmployeeDetails() {
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
    }

    /**
     * {inheritDoc}
     */ 
    public boolean isEmployeeDeleted(int id) {
        return employeeDao.isEmployeeDeleted(id);
    }

    /**
     * {inheritDoc}
     */ 
    public boolean restoreEmployee(int id) {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        employee.setIsDelete(true);
        employee.getAddressess().forEach((address) -> {
	    address.setIsDelete(true);
	}); 
        return employeeDao.updateEmployee(employee);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(int id, String name, String desgination,
			String email, long phoneNumber, float salary, Date dob, List<List<String>>addressess) {
    	Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        employee.setId(id);
        employee.setName(name);
        employee.setDesgination(desgination);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setSalary(salary);
        employee.setDob(dob);
        List<Address> oldAddresess = employee.getAddressess();
        List<Address>addressDetails = new ArrayList<Address>();
        
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
        	    Address addressValues = new Address(Integer.parseInt(address.get(0)), address.get(1),address.get(2),address.get(3),
        			    address.get(4), address.get(5), address.get(6), address.get(7),true);
        	     addressValues.setEmployee(employee);
        	     addressDetails.add(addressValues);
            	 addressValues.setEmployee(employee);
        }
        employee.setAddressess(addressDetails);
        }      
        return employeeDao.updateEmployee(employee);       
    }

    /**
     * {inheritDoc}
     */ 
    public Map<Integer, String>  getEmployeeAddressDetails(int id) {
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
    }

    /**
     * {inheritDoc}
     */ 
    public boolean updateEmployeeAddress(int addressIndex, int addressId, String[] addressDetail, int id) {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        employee.setId(id);
        List<Address> addressDetails = employee.getAddressess();
        addressDetails.set(addressIndex, new Address(addressId, addressDetail[0], addressDetail[1], 
                addressDetail[2],addressDetail[3], addressDetail[4], 
                addressDetail[5], addressDetail[6], true));
        employee.setAddressess(addressDetails);
        return employeeDao.updateEmployee(employee);
    }

    /**
     * {inheritDoc}
     */ 
    public List<String> getAllProjectDetails() {
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        List<String> projects = projectService.getAllProject();
        return projects;
    }

    /**
     * {inheritDoc}
     */ 
    public boolean isProjectExist(int projectId) {
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        return projectService.isProjectExist(projectId);
    }

    /**
     * {inheritDoc}
     */ 
    public boolean assignEmployee(int empId, List<Integer> projectIds) {
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        Employee employeeDetails = employeeDao.getSpecificEmployeeWithProjects(empId);
        List<Project> projectDetails = employeeDetails.getProjects();
        for (Integer projectId : projectIds) {
            projectDetails.add(projectService.getSpecificProjectDetails(projectId));
        }
        employeeDetails.setProjects(projectDetails);
        return employeeDao.updateEmployee(employeeDetails);     
    }

    /**
     * {inheritDoc}
     */ 
    public boolean addEmployeeAddress(int employeeId, String[] addressDetail) {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(employeeId);
        List<Address> addressDetails = employee.getAddressess();
        addressDetails.add(new Address(addressDetail[0], addressDetail[1], 
                addressDetail[2],addressDetail[3], addressDetail[4], 
                addressDetail[5], addressDetail[6], true));
        employee.setAddressess(addressDetails);
        return  employeeDao.updateEmployee(employee);             
    }

    /**
     * {inheritDoc}
     */ 
    public Employee getSpecificEmployeeDetails(int id) {
        return employeeDao.getSpecificEmployee(id);
    }

    /**
     * {inheritDoc}
     */ 
    public List<Employee> getAllEmployeeDetails() {
        return employeeDao.getAllEmployee();
    }
    
    public List<String> getAssignedEmployeeDetails(int id) {
        Employee employee = employeeDao.getSpecificEmployeeWithProjects(id);
        //List<String> employeeDetails =  new ArrayList<String>();
       // employeeDetails.add(employee.toString());
        List<Project> projectDetails = employee.getProjects();
        List<String> projects = new ArrayList<String>();
        projectDetails.forEach((project) -> {
        	projects.add(project.getId() + "," + project.getName() + "," 
       			   + project.getManagerName() + "," + project.getStartDate()
     			   + "," + project.getEndDate() + "," + project.getIsDelete());
	    });
           
        return projects;
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public List<String> getAssignedEmployee(int id) {
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
    }

    /**
     * {inheritDoc}
     */ 
    public boolean unAssignEmployee(int id, int projectId) {
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
    } 
}