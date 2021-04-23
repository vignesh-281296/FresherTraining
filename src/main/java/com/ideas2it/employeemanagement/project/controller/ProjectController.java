package com.ideas2it.employeemanagement.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.employee.controller.EmployeeController;
import com.ideas2it.employeemanagement.project.service.ProjectService;
import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * This class send data to employee serivce
 *
 * @author vignesh r
 * @version 1.0 25-03-2021
 */
public class ProjectController extends HttpServlet {
	
    private ProjectService projectService = new ProjectServiceImpl(); 
    private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeController.class);
   
    /**
     * Post pass requests and performs the actions
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
    	try {
    	PrintWriter out =  res.getWriter();
    	String action = req.getParameter("action");
    	switch(action) {
    	case "create_project" :   		
    		createProject(req, res);
    		break;
    	case "specific_project" :   		
    		specificProjectDetails(req, res);
    		break;
    	case "assign_employees" : 
    		assignEmployee(req, res);
    		break;
    	case "update_project" : 
    		updateProject(req, res);
    		break;	
    	 default :
    		 errorPage(req, res);	 
    	}	
    	} catch(IOException | ServletException e) {
    		logger.logError(e);
    	}
    }
    
    /**
     * Get request and performs actions according to their path
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			String action = req.getParameter("action");
			switch (action) {
			case "display_all_project":
				getAllprojectDetails(req, res);
				break;
			case "delete_project":
				deleteProject(req, res);
				break;
			case "restore_project":
				restoreEmployee(req, res);
				break;
			case "assign_employee_details":
				employeeDetails(req, res);
				break;
			case "get_assigned_project_details":
				getAssignedprojectDetails(req, res);
				break;
			case "assigned_employee_details":
				displayAssignedEmployeeDetails(req, res);
				break;
			case "update_project_detail":
				updateProjectDetails(req, res);
				break;
			default:
				errorPage(req, res);
			}
		} catch (IOException | ServletException e) {
			logger.logError(e);
		}
	}
    
    /**
     * It is used to create project
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet 
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void createProject(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	try {
    	String projectName = req.getParameter("project_name");
    	String projectManagerName = req.getParameter("project_manager_name");
    	Date startDate = Date.valueOf(req.getParameter("start_date"));
    	Date endDate = Date.valueOf(req.getParameter("end_date"));
    	
    	projectService.createProject(projectName, projectManagerName,
    			startDate, endDate);
    	
    	req.setAttribute("message","created Successfully");
    	
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("/project.jsp").forward(req, res);
    }
    
    /**
     * It will get the individual project details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
	private void specificProjectDetails(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			int projectId = Integer.parseInt(req.getParameter("project_id"));
			if (projectService.isProjectExist(projectId)) {
				req.setAttribute("projectDetails", projectService.getSpecificProject(projectId));
			} else {
				req.setAttribute("message", "Project id doesn't exist");
			}
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/specific_project.jsp").forward(req, res);
	}
    
    /**
     * It is used to delete project
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void deleteProject(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	projectService.deleteProject(projectId);
        req.setAttribute("message","Deleted Successfully");
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("/delete_project.jsp").forward(req, res);
    } 
    
    /**
     * It is used to restore project detail
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void restoreEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	projectService.restoreProject(projectId);
        req.setAttribute("message","Restore Successfully");
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("/restore_project.jsp").forward(req, res);
    } 
    
    /**
     * It is used to get all project details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void getAllprojectDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
    	try {
    		req.setAttribute("projects",projectService.getAllProject());
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	    req.getRequestDispatcher("/display_all_project.jsp").forward(req, res);
    }
    
    /**
     * It will get employee details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void employeeDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	    int projectId = Integer.parseInt(req.getParameter("id"));
    	    req.setAttribute("employeeDetails", projectService.getAllEmployeeDetails());
    	    req.setAttribute("assignprojectDetails", projectService.getAssignedProjectDetails(projectId));
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("/assign_employee.jsp").forward(req, res);
    }
    
    /**
     * It is used to assign employee
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void assignEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	String[] employeeId = req.getParameterValues("employees");
    	List<Integer> employeeIds = new ArrayList<Integer>();
    	if (null != employeeId) {
    	    for (int index=0; index < employeeId.length; index++) {
    		    employeeIds.add(Integer.parseInt(employeeId[index]));
    	    }
    	}
    	 projectService.assignProject(projectId, employeeIds);
         req.setAttribute("message","Assigned Succesfully");
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}	
    	
    	//req.getRequestDispatcher("/project?action=assign_employee_details").forward(req, res);
    	req.getRequestDispatcher("/project.jsp").forward(req, res);
    }
    
    /**
     * It is used to get assigned project details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void getAssignedprojectDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	req.setAttribute("assignprojectDetails", projectService.getAssignedProjectDetails(projectId));
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("/unassign_project.jsp").forward(req, res);
    }
    
    /**
     * It is used to un assign project
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    /*private void unAssignEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	int empId = Integer.parseInt(req.getParameter("assigned_employee"));
    	if ( projectService.unAssignProject(projectId, empId)) {
    		req.setAttribute("message", "Unassigned Successfully");
    	} else {
    		req.setAttribute("message", "Unsuccessful");
   	    }
    	req.getRequestDispatcher("/project?action=get_assigned_project_details").forward(req, res);
    }*/
    
    /**
     * It will get all assigned employee details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void displayAssignedEmployeeDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	req.setAttribute("assignedEmployeeDetails", projectService.getAssignedProjectDetails(projectId));
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("assigned_project_details.jsp").forward(req, res);
    }
    
    /**
     * It will get project details for update project
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void updateProjectDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	req.setAttribute("projectDetails", projectService.getSpecificProject(projectId));
    	} catch(EmployeeManagementException e) {
    		req.setAttribute("message",e.getMessage());
    	}
    	req.getRequestDispatcher("/create_project.jsp").forward(req, res);
    }
    
    /**
     * It is used to update project details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
	private void updateProject(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int projectId = Integer.parseInt(req.getParameter("id"));
			String projectName = req.getParameter("project_name");
			String projectManagerName = req.getParameter("project_manager_name");
			Date startDate = Date.valueOf(req.getParameter("start_date"));
			Date endDate = Date.valueOf(req.getParameter("end_date"));

			projectService.updateProject(projectId, projectName, projectManagerName, startDate, endDate);
			req.setAttribute("message", "Updated successfully");
		} catch (EmployeeManagementException fe) {
			req.setAttribute("message", fe.getMessage());
		}
		req.getRequestDispatcher("/project.jsp").forward(req, res);
	}
    
    private void errorPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	res.sendRedirect("404_page.jsp");
    }
}