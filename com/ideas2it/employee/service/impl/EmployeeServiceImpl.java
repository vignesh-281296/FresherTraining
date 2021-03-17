package com.ideas2it.employee.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;

/**
 * This class for employee business logic
 *
 * @author vignesh r
 * @created at 13-03-2021
 */
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(); 

    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails) 
            throws SQLException, ClassNotFoundException {
        
        List<Address> addressDetails = new ArrayList<Address>();
        for (int i = 0; i < employeeAddressDetails.size(); i++) {
            Address address = new Address(0, employeeAddressDetails.get(i)[0], 
                    employeeAddressDetails.get(i)[1], employeeAddressDetails.get(i)[2], 
                    employeeAddressDetails.get(i)[3], employeeAddressDetails.get(i)[4],
                    employeeAddressDetails.get(i)[5], employeeAddressDetails.get(i)[6]);
            addressDetails.add(address);
        }
        Employee employee = new Employee(0, name, desgination, emailId, phoneNumber, 
                salary, dob, addressDetails); 
        return employeeDao.insertEmployee(employee); 
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int id) 
            throws SQLException, ClassNotFoundException {
        return employeeDao.isEmpIdExist(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int id) 
            throws SQLException, ClassNotFoundException {
        return employeeDao.deleteEmployee(id);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public String getSpecificEmployee(int id) 
            throws SQLException, ClassNotFoundException {
        Employee employee = employeeDao.getSpecificEmployee(id);
        String employeeDetails = employee.toString();
        for (Address addresses : employee.getAddress()) {
            employeeDetails = employeeDetails + addresses;    
        }     
        return employeeDetails;
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public List<String> getAllEmployee() 
            throws SQLException, ClassNotFoundException {
        List<String> employeeDetails = new ArrayList<String>();
        for (Employee employees : employeeDao.getAllEmployee()) {
            employeeDetails.add(employees.toString());
            for (Address addresses : employees.getAddress()) {
                employeeDetails.add(addresses.toString());
            }
        }
        return employeeDetails;         
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(String name, String desgination, 
            String email, long phoneNumber, long salary, Date dob, int id) 
            throws SQLException, ClassNotFoundException {
        return employeeDao.updateEmployee(name, desgination, email, phoneNumber, 
                salary, dob, id);     
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployeeAddress(int id, String[] addressDetails, int addressOption) 
            throws SQLException, ClassNotFoundException {
        Address address = new Address(0, addressDetails[0], addressDetails[1], 
                addressDetails[2],addressDetails[3], addressDetails[4], 
                addressDetails[5], addressDetails[6]);
        return employeeDao.updateEmployeeAddress(id, address, addressOption);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean addEmployeeAddress(int employeeId, String[] addressDetails) 
            throws SQLException, ClassNotFoundException {
        Address address = new Address(employeeId, addressDetails[0], addressDetails[1], 
                addressDetails[2],addressDetails[3], addressDetails[4], 
                addressDetails[5], addressDetails[6]);
        return employeeDao.addEmployeeAddress(address);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean isExistAddressType(int id, String addressType) 
            throws SQLException, ClassNotFoundException {
        return employeeDao.isExistAddressType(id, addressType);
    }

    /** 
     * {inheritDoc}
     */
    @Override   
    public boolean validatePhoneNumber(long phoneNumber) 
            throws SQLException, ClassNotFoundException {
        return Long.toString(phoneNumber).matches("[7-9][0-9]{9}");       
    }

    /** 
     * {inheritDoc}
     */
    @Override    
    public boolean validateEmail(String emailId) 
            throws SQLException, ClassNotFoundException {	
        return emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
                              "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    /** 
     * {inheritDoc}
     */
    @Override 
    public List<String> getAddressDetails(int id) 
           throws SQLException, ClassNotFoundException {
        List<String> addressDetails = new ArrayList<String>();
        for (Address addresses : employeeDao.getAddressDetails(id)) {
            addressDetails.add(addresses.toString());
        }
        return addressDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getDeletedEmployee() 
            throws SQLException, ClassNotFoundException {
        List<String> employeeDetails = new ArrayList<String>();
        for (Employee employees : employeeDao.getDeletedEmployee()) {
            employeeDetails.add(employees.toString());
        }
        return employeeDetails;    
    }

     /** 
     * {inheritDoc}s
     */
    @Override 
    public boolean restoreEmployee(int id) 
           throws SQLException, ClassNotFoundException {
        return employeeDao.restoreEmployee(id);
    }  
}