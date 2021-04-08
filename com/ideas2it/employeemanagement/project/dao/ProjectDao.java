package com.ideas2it.employeemanagement.project.dao;

import java.util.List;

import com.ideas2it.employeemanagement.project.model.Project;

/**
 * It will perform crud operation and interact with database
 *
 * @author vignesh r 
 * @version 1.0 24-03-2021
 */
public interface ProjectDao {

    /**
     * It is used to create project
     * @param project project details
     * @return boolean value
     */
    public boolean insertProject(Project project);

    /**
     * check wheather the project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectExist(int id);

    /**
     * get individual project basic details
     * @param id project id
     * @return basic project details
     */
    public Project getSpecificProject(int id);
 
    /**
     * get all project details
     * @return project details
     */
    public List<Project> getAllProject();

    /**
     * check whether the deleted project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectDeleted(int id);
    
    /**
     * It is usedd to update project details
     * @param project project details
     * @return boolean value
     */
    public boolean updateProject(Project project);

    /**
     * get specific project details with employee
     * @param id project id
     * @return project with employee details
     */
    public Project getSpecificProjectWithEmployee(int id);
}
