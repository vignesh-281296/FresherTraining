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
import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.employeemanagement.project.model.Project;

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
            long phoneNumber, float salary, Date dob, List<String[]> employeeAddressDetails) {  
        List<Address> addressDetails = new ArrayList<Address>();
        for (int index = 0; index < employeeAddressDetails.size(); index++) {
            Address address = new Address(employeeAddressDetails.get(index)[0], 
                    employeeAddressDetails.get(index)[1], employeeAddressDetails.get(index)[2], 
                    employeeAddressDetails.get(index)[3], employeeAddressDetails.get(index)[4],
                    employeeAddressDetails.get(index)[5], employeeAddressDetails.get(index)[6], true);
            addressDetails.add(address);
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
    public String getSpecificEmployee(int id) {
        Employee employee = employeeDao.getSpecificEmployeeWithAddressess(id);
        String employeeDetails = employee.toString();
        for (Address address : employee.getAddressess()) {
            employeeDetails = employeeDetails + "\n" + address + "\n \n";
        }
        return  employeeDetails;
    }

    /**
     * {inheritDoc}
     */ 
    public List<String> getAllEmployee() {
        List<String> employees = new ArrayList<String>();   
        for (Employee employee : employeeDao.getAllEmployee()) {
            String employeeDetails = "";
            if (true == employee.getIsDelete()) {    
                employeeDetails = employee.toString();
            }
            for (Address addresses : employee.getAddressess()) {
                if (true == addresses.getIsDelete()) {
                    employeeDetails =  employeeDetails + "\n" + addresses.toString() + "\n";
                }    
            }
            employees.add(employeeDetails);
        }
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
    public List<String> getDeletedEmployeeDetails() {
        List<String> employees = new ArrayList<String>();
        for (Employee employeeDetails : employeeDao.getAllEmployee()) {
            if (false == employeeDetails.getIsDelete()) {
                employees.add(employeeDetails.toString());
            }
        }
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
    public boolean updateEmployee(int id, String[] employeeDetails) {
        Employee employee = employeeDao.getSpecificEmployee(id);
        employee.setId(id);
        if (null == employeeDetails[0]) {
            employee.setName(employee.getName());
        } else {
            employee.setName(employeeDetails[0]);
        }
 
        if (null == employeeDetails[1]) {
            employee.setDesgination(employee.getDesgination());
        } else {
            employee.setDesgination(employeeDetails[1]);
        }

        if (null == employeeDetails[2]) {
            employee.setEmail(employee.getEmail());
        } else {
            employee.setEmail(employeeDetails[2]);
        }

        if (null == employeeDetails[3]) { 
            employee.setPhoneNumber(employee.getPhoneNumber());
        } else {
            long phoneNumber = Long.parseLong(employeeDetails[3]);
            employee.setPhoneNumber(phoneNumber);
        }

        if (null == employeeDetails[4]) { 
            employee.setSalary(employee.getSalary());
        } else {
            float salary  = Float.parseFloat(employeeDetails[4]);
            employee.setSalary(salary);
        }
        if (null == employeeDetails[5]) { 
            employee.setDob(employee.getDob());
        } else {
            Date dob = Date.valueOf(employeeDetails[5]);
            employee.setDob(dob);
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
        return projectService.getAllProject();
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