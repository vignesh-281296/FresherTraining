package com.ideas2it.employeemanagement.project.service;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;

/**
 * It contains the method signature
 *
 * @author vignesh r 
 * @version 1.0 24-03-2021
 */

public interface ProjectService {

    /**
     * It will used to create project
     * @param name project name
     * @param managerName project manager name
     * @param startDate project start date
     * @param endDate project end date
     * @param employee list of employee details
     * @return boolean value
     */
    public boolean createProject(String name, String managerName, 
            Date startDate, Date endDate);

    /**
     * It is used to validate date
     * @param date
     * @return boolean value
     */
    public boolean validateDob(String date);

    /**
     * It is used to check project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectIdExist(int id);

    /**
     * It is used to get specific project details
     * @param id project id
     */
    public String getSpecificProject(int id);

    /**
     * It is used to get all project details
     */
    public List<String> getAllProject();

    /**
     * It is used to delete specific employee
     * @param id project id
     */
    public boolean deleteProject(int id);
}