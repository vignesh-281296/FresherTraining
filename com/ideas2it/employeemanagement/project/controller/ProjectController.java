package com.ideas2it.employeemanagement.project.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;

public class ProjectController {

    private ProjectServiceImpl projectService = new ProjectServiceImpl(); 
   
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
            Date startDate, Date endDate) {
        return projectService.createProject(name, managerName, startDate, endDate);
    }

     /**
     * It is used to check validate dob
     * @param dob Employee date of birth
     * @return boolean value
     */
    public boolean validateDate(String date) {
        return projectService.validateDob(date);
    }

    /**
     * It is used to check project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectIdExist(int id){
        return projectService.isProjectIdExist(id);
    }

     public String getSpecificProject(int id) {
        return projectService.getSpecificProject(id);
    }

    /**
     * It is used to get all project details
     */
    public List<String> getAllProject() {
        return projectService.getAllProject();
    }

    /**
     * It is used to delete specific employee
     * @param id project id
     */
    public boolean deleteProject(int id) {
        return projectService.deleteProject(id);
    }
}