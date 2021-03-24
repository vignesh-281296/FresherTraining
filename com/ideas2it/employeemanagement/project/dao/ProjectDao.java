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

    public boolean insertProject(Project project);

    public boolean isProjectIdExist(int id);

    /**
     * It is used to get specific project details
     * @param id project id
     */
    public Project getSpecificProject(int id);

    /**
     * It is used to get all project details
     */
    public List<Project> getAllProject();

    /**
     * It is used to delete specific employee
     * @param id project id
     */
    public boolean deleteProject(int id);
}
