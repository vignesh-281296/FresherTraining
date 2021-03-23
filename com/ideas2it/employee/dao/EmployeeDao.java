package com.ideas2it.employee.dao;

import java.sql.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

/**
 * It contain method signature
 */
public interface EmployeeDao {
    
    /**
     * It is used to create employee
     * @param employee it is an Employee details
     * @return boolean value
     */ 
    public boolean insertEmployee(Employee employee);

    /**
     * It is used to check wheather id is exist or not
     * @param id employee id
     * @return boolean value
     */
    public boolean isEmpIdExist(int id);

    /**
     * It is used to delete employee details 
     * @param id employee id
     * @return boolean value
     */
    public boolean deleteEmployee(int id);

    /**
     * It is used to get specific employee details
     * @param id employee id
     * @return individual employee detail
     */
    public Employee getSpecificEmployee(int id); 
 
    /**
     * It is used to get all employee details
     * @return all employee details
     */
    public List<Employee> getAllEmployee(); 

    /**
     * It is used to update employee details
     * @id employee id
     * @employee employee details 
     * @return boolean value
     */
    public boolean updateEmployee(int id, Employee employee); 

    /**
     * It is used to update employee address details 
     * @param addressId employee id
     * @param address employee address details
     * @return boolean value
     */
    public boolean updateEmployeeAddress(int addressId, Address address);

    /**
     * It is used to add your employee address details
     * @param address employee address details
     * @return boolean value
     */
    public boolean addEmployeeAddress(Address address);

    /**
     * It is used to get specific address details
     * @param id employee id
     * @return address details
     */
     public List<Address> getAddressDetails(int id); 

    /**
     * It is used to get deleted employee details
     * @return employee details
     */
    public List<Employee> getDeletedEmployee(); 

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
     * It is used to delete address details 
     * @param id address id
     * @return boolean value
     */
    public boolean deleteAddress(int id);

    /**
     * It is used to get single employee details only
     * @param id employee id
     * @return single employee details
     */
    public Employee getEmployee(int id);
}