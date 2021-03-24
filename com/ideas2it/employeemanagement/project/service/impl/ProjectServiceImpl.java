package com.ideas2it.employeemanagement.project.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeemanagement.project.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {
    private ProjectDaoImpl projectDao = new ProjectDaoImpl();

    /**
     * {inheritDoc}
     */
    @Override
    public boolean createProject(String name, String managerName, 
            Date startDate, Date endDate) {
        List<Employee> employees = new ArrayList<Employee>();
        Project project = new Project(name, managerName, startDate, endDate, employees);
        return projectDao.insertProject(project);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean validateDob(String date) {	
        return date.matches("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isProjectIdExist(int id) {
        return projectDao.isProjectIdExist(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String getSpecificProject(int id) {
        return projectDao.getSpecificProject(id).toString();
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getAllProject() {
        List<String> projectDetails = new ArrayList<String>();
        for (Project projects : projectDao.getAllProject()) {
            projectDetails.add(projects.toString());
        }
        return projectDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteProject(int id) {
        return projectDao.deleteProject(id);
    }
}