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
        Project project = new Project(name, managerName, startDate, endDate, true, employees);
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
    public boolean isProjectExist(int id) {
        return projectDao.isProjectExist(id);    
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
        for (Project project : projectDao.getAllProject()) {
             if (true == project.getIsDelete()) {
                 projectDetails.add(project.toString() + "\n");
             }
        }
        return projectDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteProject(int id) {
        Project project = projectDao.getSpecificProjectWithEmployee(id);
        project.setIsDelete(false);
        project.setEmployees(null);
        return projectDao.updateProject(project);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getDeletedProjectDetails() {
        List<String> projectDetails = new ArrayList<String>();
        for (Project project : projectDao.getAllProject()) {
            if (false == project.getIsDelete()) {
                projectDetails.add(project.toString() + "\n");
            }
        }
        return projectDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isProjectDeleted(int id) {
        return projectDao.isProjectDeleted(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean restoreProject(int id) {
        Project project = projectDao.getSpecificProjectWithEmployee(id);
        project.setIsDelete(true);
        return projectDao.updateProject(project);    
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
               project.getStartDate(), project.getEndDate(), true);
        return projectDao.updateProject(projectDetail);       
    }
   
    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProjectDetails(int id) {
        return projectDao.getSpecificProject(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getAllEmployeeDetails() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> employeeDetails = employeeService.getAllEmployeeDetails();
        List<String> employees = new ArrayList<String>();
        for (Employee employee : employeeDetails) {
            if (true == employee.getIsDelete()) {
                employees.add(employee.toString() + "\n"); 
            }
        } 
        return employees;
    } 

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isEmployeeExist(int employeeId) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        return employeeService.isEmployeeExist(employeeId);
    } 

    /** 
     * {inheritDoc}
     */
    @Override
    public boolean assignProject(int id, List<Integer> employeeIds) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Project projectDetails = projectDao.getSpecificProjectWithEmployee(id);
        List<Employee> employeeDetails = projectDetails.getEmployees();
        for (Integer employeeId : employeeIds) {
            employeeDetails.add(employeeService.getSpecificEmployeeDetails(employeeId));    
        }
        projectDetails.setEmployees(employeeDetails); 
        return projectDao.updateProject(projectDetails);   
    }

    /** 
     * {inheritDoc}
     */
    @Override
    public List<String> getAssignedProject(int id) {
        Project project = projectDao.getSpecificProjectWithEmployee(id);
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
    public boolean unAssignProject(int id, int employeeId) {
        Project project = projectDao.getSpecificProjectWithEmployee(id);
        List<Employee> employeeDetails = project.getEmployees();
        for (Employee employee : employeeDetails) {
            if (employeeId == employee.getId()) {
                employeeDetails.remove(employee);
                break;
            }
        }
        project.setEmployees(employeeDetails);
        return projectDao.updateProject(project);
    }  
}