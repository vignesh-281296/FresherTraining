package com.ideas2it.employeemanagement.project.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;

/**
 * This class send data to employee serivce
 */
public class ProjectController {

    private ProjectServiceImpl projectService = new ProjectServiceImpl(); 
   
    /**
     * It will used to create project
     * @param name project name
     * @param managerName project manager name
     * @param startDate project start date
     * @param endDate project end date
     * @return boolean value
     */
    public boolean createProject(String name, String managerName, 
            Date startDate, Date endDate) {
        return projectService.createProject(name, managerName, startDate, endDate);
    }

     /**
     * It is used to check validate date
     * @param date project date
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

     /**
      * It is used to get specific project details
      * @param id project id
      * @return project details
      */
     public String getSpecificProject(int id) {
        return projectService.getSpecificProject(id);
    }

    /**
     * It is used to get all project details
     * @return project details
     */
    public List<String> getAllProject() {
        return projectService.getAllProject();
    }

    /**
     * It is used to delete specific employee
     * @param id project id
     * @return boolean value
     */
    public boolean deleteProject(int id) {
        return projectService.deleteProject(id);
    }

    /**
     * It is used to get all delete project details
     * @return deleted project details
     */
    public List<String> getDeletedProject() {
        return projectService.getDeletedProject();
    }

    /**
     * It is used to check wheather the deleted
     * project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean checkDeletedProjectId(int id) {
        return projectService.checkDeletedProjectId(id);
    }

    /**
     * It is used to restore the project
     * @param id project id
     * @return boolean value
     */
    public boolean restoreProject(int id) {
        return projectService.restoreProject(id);
    }

    /**
     * It is used to update specific project details
     * @param id project id
     * @param projectDetails
     * @return boolean value
     */
    public boolean updateProject(int id, String[] projectDetails) {
        return projectService.updateProject(id, projectDetails);
    }

    /** 
     * It is used to get all employee details
     * @return employee details
     */
    public List<String> getAllEmployeeDetails() {
        return projectService.getAllEmployee();
    }

    /**
     * It is used to assign project to employees
     * @param projectId project id
     * @param employeeIds employee id
     * @return boolean value
     */
    public boolean assignProject(int projectId, List<Integer> employeeIds) {
        return projectService.assignProject(projectId, employeeIds);
    }

    /**
    * It is used to check wheather the employee id is exist or not
    * @param empId employee id
    * @return boolean value
    */
    public boolean isEmpIdExist(int employeeId) {
        return projectService.isEmpIdExist(employeeId);
    }

    /**
     * It is used to get assigned project details
     * @param id project id
     * @return project detail
     */
    public List<String> getAssignProject(int id) {
        return projectService.getAssignProject(id);
    }
}