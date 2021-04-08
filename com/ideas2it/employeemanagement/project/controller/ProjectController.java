package com.ideas2it.employeemanagement.project.controller;

import java.sql.Date;
import java.util.List;

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;

/**
 * This class send data to employee serivce
 *
 * @author vignesh r
 * @version 1.0 25-03-2021
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
     * It is used to check wheather the project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectExist(int id) {
        return projectService.isProjectExist(id);
    }

    /**
     * It is used to get individual project details
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
     * It is used to delete project
     * @param id project id
     * @return boolean value
     */
    public boolean deleteProject(int id) {
        return projectService.deleteProject(id);
    }

    /**
     * It is used to get deleted project details
     * @return deleted project details
     */
    public List<String> getDeletedProjectDetails() {
        return projectService.getDeletedProjectDetails();
    }

    /**
     * It is used to check wheather the project is delete
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectDeleted(int id) {
       return projectService.isProjectDeleted(id);
    }

    /**
     * It is used to restore project
     * @param id project id
     * @return booleanvalue
     */
    public boolean restoreProject(int id) {
        return projectService.restoreProject(id);    
    }

    /**
     * It is used to update project details
     * @param projectId 
     * @param projectDetails
     * @return boolean value
     */
    public boolean updateProject(int projectId, String[] projectDetails) {
        return projectService.updateProject(projectId, projectDetails);
    }

    /**
     * It is used to get all employee details
     * @return employee details
     */
    public List<String> getAllEmployeeDetails() {
        return projectService.getAllEmployeeDetails();
    }

    /**
     * It is used to check wheather the employee id is exist or not
     * @param employeeId 
     * @return boolean value
     */
    public boolean isEmployeeExist(int employeeId) {
        return projectService.isEmployeeExist(employeeId);
    }

    /**
     * It is used to assign project to employee
     * @param id project id
     * @param employeeIds multiple employee id
     */
    public boolean assignProject(int id, List<Integer> employeeIds) {
        return projectService.assignProject(id, employeeIds);
    }

    /**
     * It is used to get assigned project details
     * @param id project id
     * @return project details
     */
    public List<String> getAssignedProject(int id) {
        return projectService.getAssignedProject(id);
    }

    /**
     * It is used to unassign project
     * @param id project id
     * @param employeeId
     * @return boolean value
     */
    public boolean unAssignProject(int id, int employeeId) {
        return projectService.unAssignProject(id, employeeId);
    }
}