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
            long phoneNumber, float salary, Date dob, List<String[]> employeeAddressDetails) {  
        List<Address> addressDetails = new ArrayList<Address>();
        for (int index = 0; index < employeeAddressDetails.size(); index++) {
            Address address = new Address(employeeAddressDetails.get(index)[0], 
                    employeeAddressDetails.get(index)[1], employeeAddressDetails.get(index)[2], 
                    employeeAddressDetails.get(index)[3], employeeAddressDetails.get(index)[4],
                    employeeAddressDetails.get(index)[5], employeeAddressDetails.get(index)[6]);
            addressDetails.add(address);
        }
        Employee employee = new Employee(name, desgination, emailId, phoneNumber, 
                salary, dob, addressDetails); 
        return employeeDao.insertEmployee(employee); 
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int id) {
        return employeeDao.isEmpIdExist(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public String getSpecificEmployee(int id) {
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
    public List<String> getAllEmployee() {
        List<String> employees = new ArrayList<String>();
        for (Employee employee : employeeDao.getAllEmployee()) {
            String employeeDetails = employee.toString();
            for (Address addresses : employee.getAddress()) {
                employeeDetails =  employeeDetails + addresses.toString();
            }
            employees.add(employeeDetails);
        }
        return employees;         
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployee(int id, String[] employeeDetails) {
        Employee employee = employeeDao.getEmployee(id);
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
        Employee employees = new Employee(id, employee.getName(), employee.getDesgination(),
               employee.getEmail(), employee.getPhoneNumber(), employee.getSalary(),
               employee.getDob());
        return employeeDao.updateEmployee(id, employees);       
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateEmployeeAddress(int addressId, String[] addressDetails) {
        Address address = new Address(addressDetails[0], addressDetails[1], 
                addressDetails[2],addressDetails[3], addressDetails[4], 
                addressDetails[5], addressDetails[6]);
        return employeeDao.updateEmployeeAddress(addressId, address);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean addEmployeeAddress(int employeeId, String[] addressDetails) {
        Address address = new Address(employeeId, addressDetails[0], addressDetails[1], 
                addressDetails[2],addressDetails[3], addressDetails[4], 
                addressDetails[5], addressDetails[6]);
        return employeeDao.addEmployeeAddress(address);
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
    public Map<Integer, String> getAddressDetails(int id) {
        Map<Integer, String> addressDetails = new LinkedHashMap<Integer, String>();
        int index = 0;
        for (Address address : employeeDao.getAddressDetails(id)) {
            index++;
            addressDetails.put(address.getId(), "Address-no- " + Integer.toString(index) 
                   + address.toString());     
        } 
        return addressDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getDeletedEmployee() {
        List<String> employeeDetails = new ArrayList<String>();
        for (Employee employees : employeeDao.getDeletedEmployee()) {
            employeeDetails.add(employees.toString());
        }
        return employeeDetails;    
    }

     /** 
     * {inheritDoc}
     */
    @Override 
    public boolean restoreEmployee(int id) {
        return employeeDao.restoreEmployee(id);
    }

     /**
     * {inheritDoc}
     */
    @Override
    public boolean checkDeletedEmpId(int id) {
        return employeeDao.checkDeletedEmpId(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteAddress(int id) {
        return employeeDao.deleteAddress(id);
    }
}