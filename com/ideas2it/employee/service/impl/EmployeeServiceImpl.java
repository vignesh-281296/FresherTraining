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
 */
public class EmployeeServiceImpl implements EmployeeService {
    
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(); 

    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, long salary, Date dob, List<String[]> employeeAddressDetails) throws SQLException, ClassNotFoundException {
        Address employeeAddress;
        List<Address> addressDetails = new ArrayList<Address>();
        for (int i = 0; i < employeeAddressDetails.size(); i++) {
            employeeAddress = new Address(0, employeeAddressDetails.get(i)[0], employeeAddressDetails.get(i)[1],
                    employeeAddressDetails.get(i)[2], employeeAddressDetails.get(i)[3],
                    employeeAddressDetails.get(i)[4],employeeAddressDetails.get(i)[5],
                    employeeAddressDetails.get(i)[6]);
            addressDetails.add(employeeAddress);
        }
        Employee employee = new Employee(0, name, desgination, emailId, phoneNumber, salary, dob, addressDetails); 
        return employeeDao.insertEmployee(employee); 
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int id) throws SQLException, ClassNotFoundException {
        return employeeDao.isEmpIdExist(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        return employeeDao.deleteEmployee(id);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public String getSpecificEmployee(int id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDao.getSpecificEmployee(id);     
        return getEmployeeDetails(employee);
    }

    private String getEmployeeDetails(Employee employee) {    
        String employeeDetails = employee.toString();
        List<Address> employeeAddress = employee.getAddress();
        for (int i = 0; i < employeeAddress.size(); i++) {
            employeeDetails = employeeDetails + employeeAddress.get(i) + "\n";
        }
        return employeeDetails;
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public List<String> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> employees = new ArrayList<Employee>();
        List<String> employeeDetails = new ArrayList<String>();
        employees = employeeDao.getAllEmployee();
        for (int i = 0; i < employees.size(); i++) {
            employeeDetails.add(getEmployeeDetails(employees.get(i)));
        }
        return employeeDetails;
              
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(String name, String desgination, String email, long phoneNumber,
                                  long salary, Date dob, int id) throws SQLException, ClassNotFoundException {
        return employeeDao.updateEmployee(name, desgination, email, phoneNumber, salary, dob, id);     
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployeeAddress(int id, String[] addressDetails) throws SQLException, ClassNotFoundException {
        Address address = new Address(0, addressDetails[0], addressDetails[1], addressDetails[2],addressDetails[3],
                addressDetails[4], addressDetails[5], addressDetails[6]);
        return employeeDao.updateEmployeeAddress(id, address);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean isExistAddressType(int id, String addressType) throws SQLException, ClassNotFoundException {
        return employeeDao.isExistAddressType(id, addressType);
    }

    /** 
     * {inheritDoc}
     */
    @Override   
    public boolean validatePhoneNumber(long phoneNumber) throws SQLException, ClassNotFoundException {
        return Long.toString(phoneNumber).matches("[7-9][0-9]{9}");       
    }

    /** 
     * {inheritDoc}
     */
    @Override    
    public boolean validateEmail(String emailId) throws SQLException, ClassNotFoundException {	
        return emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
                              "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
}