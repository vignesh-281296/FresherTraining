package com.ideas2it.employee.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;

/**
 * This class is used retrives datas from POJO
 *
 * @author vignesh r
 */
public class EmployeeServiceImpl implements EmployeeService {
    
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(); 
    int empId;

    /**
     * {inheritDoc}
     */	
    public boolean insertEmployee(String name, String desgination, String emailId,
                              long phoneNumber, long salary, Date dob) {
        empId++;
        Employee employee = new Employee(empId, name, desgination, emailId, phoneNumber, salary, dob); 
        return employeeDao.insertEmployee(employee); 
    }

    /**
     * {inheritDoc}
     */
    public boolean insertAddress(String doorNo, String streetName,
                                 String city, String district, String state, 
                                 String country, String addressMode) {
        Address address = new Address(empId, doorNo, streetName, city, district, 
                                      state, country, addressMode); 
        return employeeDao.insertAddress(address); 
    }

    /**
     * {inheritDoc}
     */
    public List<String> getAllEmployee() {
        List<String> employee = new ArrayList<String>();
        for (Employee employees : employeeDao.getAllEmployee()) {
            employee.add(employees.toString());
        }
        return employee; 
    }

    /**
     * {inheritDoc}
     */
    public List<String> getAllEmployeeAddress() {
        List<String> address = new ArrayList<String>();
        for (Address employeeAddress : employeeDao.getAllEmployeeAddress()) {
            address.add(employeeAddress.toString());
        }
        return address; 
    }

    /**
     * {inheritDoc}
     */
    public boolean isEmpIdExist(int id) {
        return employeeDao.isEmpIdExist(id);
    }

    /**
     * {inheritDoc}
     */
    public boolean deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    /**
     * {inheritDoc}
     */
    public String getEmployee(int id) {

        return employeeDao.getEmployee(id).toString();
    }
    
    /**
     * {inheritDoc}
     */
    public String getAddress(int id, String addressType) {
        return employeeDao.getAddress(id, addressType).toString(); 
    }
}