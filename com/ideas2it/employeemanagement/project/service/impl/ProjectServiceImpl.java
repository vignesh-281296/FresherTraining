package com.ideas2it.employeemanagement.project.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeemanagement.project.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.ProjectService;

/**
 * This class for project business logic
 *
 * @author vignesh  r
 * @version 1.0 24-03-2021
 */
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

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getDeletedProject() {
        List<String> projectDetails = new ArrayList<String>();
        for (Project projects : projectDao.getDeletedProject()) {
            projectDetails.add(projects.toString());
        }
        return projectDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean checkDeletedProjectId(int id) {
        return projectDao.checkDeletedProjectId(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean restoreProject(int id) {
        return projectDao.restoreProject(id);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean updateProject(int id, String[] projectDetails) {
        Project project = projectDao.getSpecificProject(id);
        if (null == projectDetails[0]) {
            project.setName(project.getName());
        } else {
            project.setName(projectDetails[0]);
        }
 
        if (null == projectDetails[1]) {
            project.setManagerName(project.getManagerName());
        } else {
            project.setManagerName(projectDetails[1]);
        }

        if (null == projectDetails[2]) {
            project.setStartDate(project.getStartDate());
        } else {
            Date startDate = Date.valueOf(projectDetails[2]);
            project.setStartDate(startDate);
        }

        if (null == projectDetails[3]) { 
            project.setEndDate(project.getEndDate());
        } else {
            Date endDate = Date.valueOf(projectDetails[3]);
            project.setEndDate(endDate);
        }

        Project projectDetail = new Project(id, project.getName(), project.getManagerName(),
               project.getStartDate(), project.getEndDate());
        return projectDao.updateProject(id, projectDetail);       
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public List<String> getAllEmployee() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<String> employeeDetails = new ArrayList<String>();
        List<Employee> employee = employeeService.getAllEmployeeDetails();
        for (Employee employees : employee) {
            employeeDetails.add("\nEmployee Id - "  + employees.getId() + "\nEmployee Name - "
                    + employees.getName() + "\nPhone Number - " + employees.getPhoneNumber());
        }
        return employeeDetails; 
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean assignProject(int id, List<Integer> employeeIds) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> employeeDetails = new ArrayList<Employee>();
        Project projectDetails = projectDao.getSpecificProject(id);
        for (Integer employeeId : employeeIds) {
            employeeDetails.add(employeeService.getSpecificEmployeeDetails(employeeId));    
        } 
        Project project = new Project(id, projectDetails.getName(),
                projectDetails.getManagerName(), projectDetails.getStartDate(), projectDetails.getEndDate(),
                employeeDetails);
        return projectDao.assignProject(project);   
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean isEmpIdExist(int employeeId) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        return employeeService.isEmpIdExist(employeeId);
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public List<String> getAssignedProject(int id) {
        Project project = projectDao.getAssignedProject(id);
        List<String> projectDetails =  new ArrayList<String>();
        projectDetails.add(project.toString());
        List<Employee> employees = project.getEmployees();
        if (!employees.isEmpty()){
            for (Employee employee : employees) {
                projectDetails.add(employee.toString());    
            } 
        } else {
            projectDetails.add("No employee assigned to this project");
        }      
        return projectDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Project> getAllProjectDetails() {
        return projectDao.getAllProject();
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProjectDetails(int id) {
        return projectDao.getSpecificProject(id);
    }

}