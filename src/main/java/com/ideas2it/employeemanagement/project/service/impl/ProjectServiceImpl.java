package com.ideas2it.employeemanagement.project.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, String> getSpecificProject(int id) {
    	Map<String, String> projectDetails = new LinkedHashMap<String, String>(); 
        Project project = projectDao.getSpecificProject(id);
        projectDetails.put("projectId", "" + project.getId());
        projectDetails.put("projectname", "" + project.getName());
        projectDetails.put("managername", "" + project.getManagerName());
        projectDetails.put("startdate", "" + project.getStartDate());
        projectDetails.put("enddate", "" + project.getEndDate()); 
        projectDetails.put("isdelete", "" + project.getIsDelete());
        return projectDetails;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<String> getAllProject() {
        List<String> projectDetails = new ArrayList<String>();
        for (Project project : projectDao.getAllProject()) {
                 projectDetails.add(project.getId() + "," + project.getName() + "," 
           			   + project.getManagerName() + "," + project.getStartDate()
         			   + "," + project.getEndDate() + "," + project.getIsDelete());
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
    public boolean updateProject(int id, String projectName, String projectManagerName,
    		Date startDate, Date endDate) {
        Project project = projectDao.getSpecificProject(id);
        project.setName(projectName);
        project.setManagerName(projectManagerName);
        project.setStartDate(startDate); 
        project.setEndDate(endDate);
    
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
        List<String> employeeDetails = employeeService.getAllEmployee();
        return employeeDetails;
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
    public List<String> getAssignedProjectDetails(int id) {
        Project project = projectDao.getSpecificProjectWithEmployee(id);
        List<String> projectDetails =  new ArrayList<String>();
        //projectDetails.add(project.toString());
        List<Employee> employees = project.getEmployees();
        if (!employees.isEmpty()){
            for (Employee employee : employees) {
                projectDetails.add(employee.getId() + "," + employee.getName() + "," 
         			   + employee.getDesgination() + "," + employee.getEmail()
         			   + "," + employee.getPhoneNumber() + "," + employee.getDob()
         			   + "," + employee.getSalary() + "," + employee.getIsDelete());    
            } 
        }      
        return projectDetails;
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