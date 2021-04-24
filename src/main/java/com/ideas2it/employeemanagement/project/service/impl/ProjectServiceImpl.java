package com.ideas2it.employeemanagement.project.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.dao.impl.ProjectDaoImpl;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.project.service.ProjectService;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * This class for project business logic
 *
 * @author vignesh  r
 * @version 1.0 24-03-2021
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao = new ProjectDaoImpl();
    private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
    
    /**
     * {inheritDoc}
     */
    @Override
	public boolean createProject(String name, String managerName, Date startDate, Date endDate)
			throws EmployeeManagementException {
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
    public boolean isProjectExist(int id) throws  EmployeeManagementException {
    	return projectDao.isProjectExist(id);
    		   
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProject(int id) throws EmployeeManagementException {
        return projectDao.getSpecificProject(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Project> getAllProject() throws EmployeeManagementException{
        return projectDao.getAllProject();
    }

    /**
     * {inheritDoc}
     */
    @Override
	public void deleteProject(int id) throws EmployeeManagementException {
		try {
			Project project = projectDao.getSpecificProjectWithEmployee(id);
			project.setIsDelete(false);
			project.setEmployees(null);
			if (!(projectDao.updateProject(project))) {
				throw new EmployeeManagementException("Delete Unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Delete Unsuccessful");
		}
	}

    /**
     * {inheritDoc}
     */
    /*public List<String> getDeletedProjectDetails() {
        List<String> projectDetails = new ArrayList<String>();
        for (Project project : projectDao.getAllProject()) {
            if (false == project.getIsDelete()) {
                projectDetails.add(project.toString() + "\n");
            }
        }
        return projectDetails;
    }*/

    /**
     * {inheritDoc}
     */
    /*public boolean isProjectDeleted(int id) {
        return projectDao.isProjectDeleted(id);
    }*/

    /**
     * {inheritDoc}
     */
    @Override
	public void restoreProject(int id) throws EmployeeManagementException {
		try {
			Project project = projectDao.getSpecificProjectWithEmployee(id);
			project.setIsDelete(true);
			if (!(projectDao.updateProject(project))) {
				throw new EmployeeManagementException("Restore Unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Restore Unsuccessful");
		}
	}

   /** 
     * {inheritDoc} 
     */
    @Override
	public void updateProject(int id, String projectName, String projectManagerName, Date startDate, Date endDate)
			throws EmployeeManagementException {
		try {
			Project project = projectDao.getSpecificProject(id);
			project.setName(projectName);
			project.setManagerName(projectManagerName);
			project.setStartDate(startDate);
			project.setEndDate(endDate);

			Project projectDetail = new Project(id, project.getName(), project.getManagerName(), project.getStartDate(),
					project.getEndDate(), true);
			if (!(projectDao.updateProject(projectDetail))) {
				throw new EmployeeManagementException("Update Unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Update Unsuccessful");
		}
	}
   
    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProjectDetails(int id) throws EmployeeManagementException {
        return projectDao.getSpecificProject(id);
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployeeDetails() throws EmployeeManagementException {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> employeeDetails = employeeService.getAllEmployee();
        return employeeDetails;
    } 

    /**
     * {inheritDoc}
     */
   /* public boolean isEmployeeExist(int employeeId) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        return employeeService.isEmployeeExist(employeeId);
    } */

    /** 
     * {inheritDoc}
     */
    @Override
	public void assignProject(int id, List<Integer> employeeIds) throws EmployeeManagementException {
		try {
			EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
			Project projectDetails = projectDao.getSpecificProjectWithEmployee(id);
			List<Employee> employeeDetails = new ArrayList<Employee>();
			for (Integer employeeId : employeeIds) {
				employeeDetails.add(employeeService.getSpecificEmployeeDetails(employeeId));
			}
			projectDetails.setEmployees(employeeDetails);
			if (!(projectDao.updateProject(projectDetails))) {
				throw new EmployeeManagementException("Assign unsuccessful");
			}
		} catch (NullPointerException e) {
			logger.logError(e);
			throw new EmployeeManagementException("Assign Unsuccessful");
		}
	}
    
    /** 
     * {inheritDoc}
     */
    @Override
    public List<Employee> getAssignedProjectDetails(int id) throws EmployeeManagementException{
        Project project = projectDao.getSpecificProjectWithEmployee(id); 
        return project.getEmployees();
    }

    /** 
     * {inheritDoc}
     */
    /*public List<String> getAssignedProject(int id) {
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
    }*/

    /**
     * {inheritDoc}
     */
   /* public boolean unAssignProject(int id, int employeeId) {
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
    }*/ 
    
}