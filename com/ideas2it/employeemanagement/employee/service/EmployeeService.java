package com.ideas2it.employeemanagement.employee.service;

import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * It contains the method signature
 *
 * @author vignesh r 
 * @version 1.0 17-03-2021
 */
public interface EmployeeService {

    /**
     * It is used to pass employee details Dao
     * @param name employee name
     * @param desgination employee email-id
     * @param emailId employee email-id
     * @param phoneNumber employee phonenumber
     * @param salary employee salary
     * @param dob employee date of birth
     * @param employeeAddressDetails lists of employee address
     * @return boolean value
     */
    public boolean insertEmployee(String name, String desgination, String emailId,
            long phoneNumber, float salary, Date dob, List<String[]> employeeAddressDetails);

    /**
     * It is used to check wheather employee id exist or not
     * @param id employeeId
     * @return boolean value
     */
    public boolean isEmpIdExist(int id);

    /**
     * It is used to delete employee based upon employee id
     * @param id employee id
     * @return boolean  
     */
    public boolean deleteEmployee(int id);

    /**
     * It is used to get individual employee details
     * @param id employee id
     * @return individual employee details
     */
    public String getSpecificEmployee(int id);

    /**
     * It is used to get all employee
     * @return all employee details  
     */
    public List<String> getAllEmployee();

    /**
     * It is used to update employee details
     * @id employee id
     * @employeeDetails
     * @return boolean value
     */
    public boolean updateEmployee(int id, String[] employeeDetails);

   /**
     * It is used to update employee address details 
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int addressId, String[] addressDetails); 

    /**
     * It is used to add employee address details
     * @param id employee id
     * @param addressDetails employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(int employeeId, String[] addressDetails);

    /**
     * It is used to validate phone number
     * @param phone number Employee phone number
     * @return boolean value
     */
    public boolean validatePhoneNumber(long phoneNumber);

     /**
     * It is used to validate email-id
     * @param emailId Employee email-id
     * @return boolean value
     */
    public boolean validateEmail(String emailId);

    /**
     * It is used to validate date
     * @param dob Employee date of birth
     * @return boolean value
     */
    public boolean validateDob(String dob);

    /**
     * It is used to get specific address details
     * @param id employee id
     * @return address details
     */
    public Map<Integer, String> getAddressDetails(int id);

    /**
     * It is used to get delete employee details
     * @return employee details
     */
    public List<String> getDeletedEmployee();

    /**
     * It is used to restore the deleted employee details
     * @param id employee id
     * @return boolean value
     */
    public boolean restoreEmployee(int id);

    /**
     * It is used to check wheather deleted employee id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean checkDeletedEmpId(int id);

    /**
     * It is used to delete address based upon address id
     * @param id address id
     * @return boolean value 
     */
    public boolean deleteAddress(int id);
}
