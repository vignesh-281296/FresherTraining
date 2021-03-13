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
 * This class is used retrives datas from POJO
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
        Employee employees = employeeDao.getSpecificEmployee(id);
        String employeeDetails = employees.toString();
        List<Address> employeeAddress = employees.getAddress();
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
        List<String> employeeDetails = new ArrayList<String>();
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeDao.getAllEmployee();                           
        for (int i = 0; i < employees.size(); i++) {
            employeeDetails.add(employees.get(i).toString());
        }
       
        return employeeDetails;      
    }
}