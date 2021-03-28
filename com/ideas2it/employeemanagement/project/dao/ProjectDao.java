package com.ideas2it.employeemanagement.project.dao;

import java.util.List;

import com.ideas2it.employeemanagement.project.model.Project;

/**
 * It contains the method signature
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
     * It will check wheather the project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean isProjectIdExist(int id);

    /**
     * It is used to get specific project details
     * @param id project id
     * @return project details
     */
    public Project getSpecificProject(int id);

    /**
     * It is used to get all project details
     * @return project details
     */
    public List<Project> getAllProject();

    /**
     * It is used to delete specific employee
     * @param id project id
     * @return boolean values
     */
    public boolean deleteProject(int id);

    /**
     * It is used to get deleted project details
     * @return deleted project details
     */
    public List<Project> getDeletedProject();

    /**
     * It will to check wheather the deleted
     * project id is exist or not
     * @param id project id
     * @return boolean value
     */
    public boolean checkDeletedProjectId(int id);

    /**
     * It is used to restore the project
     * @param id project id
     * @return boolean value
     */
    public boolean restoreProject(int id);

    /**
     * It is used to update specific project details
     * @param id project id
     * @param project project details
     * @return boolean value
     */
    public boolean updateProject(int id, Project project);

    /**
     * It is used to assign project to employee
     * @param project
     * @return boolean value
     */
    public boolean assignProject(Project project);

    /**
     * It is used to get assigned project details
     * @param id project id
     * @return project detail
     */
    public Project getAssignedProject(int id);
}
