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

import com.ideas2it.employeemanagement.project.service.impl.ProjectServiceImpl;

/**
 * This class send data to employee serivce
 *
 * @author vignesh r
 * @version 1.0 25-03-2021
 */
public class ProjectController extends HttpServlet {
    private ProjectServiceImpl projectService = new ProjectServiceImpl(); 
   
    /**
     * Post pass requests and performs the actions
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	doGet(req, res);
    }
    
    /**
     * Get request and performs actions according to their path
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	PrintWriter out =  res.getWriter();
    	String action = req.getParameter("action");
    	switch(action) {
    	case "create_project" :   		
    		createProject(req, res);
    		break;
    	case "specific_project" :   		
    		specificProjectDetails(req, res);
    		break;
    	case "display_all_project" :   		
    		getAllprojectDetails(req, res);
    		break;	
    	case "delete_project" :   		
    		deleteProject(req, res);
    		break;	
    	case "restore_project" :   		
    		restoreEmployee(req, res);
    		break;
    	case "assign_employee_details" :   		
    		employeeDetails(req, res);
    		break;
    	case "assign_employees" : 
    		assignEmployee(req, res);
    		break;
    	case "get_assigned_project_details" : 
    		getAssignedprojectDetails(req, res);
    		break;
    	case "unassign_project" : 
    		unAssignEmployee(req, res);
    		break;
    	case "assigned_employee_details" : 
    		displayAssignedEmployeeDetails(req, res);
    		break;	
    	case "update_project_detail" : 
    		updateProjectDetails(req, res);
    		break;
    	case "update_project" : 
    		updateProject(req, res);
    		break;	
    	 default :
    	     out.println("failure");	 
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
    	String projectName = req.getParameter("project_name");
    	String projectManagerName = req.getParameter("project_manager_name");
    	Date startDate = Date.valueOf(req.getParameter("start_date"));
    	Date endDate = Date.valueOf(req.getParameter("end_date"));
    	
    	boolean project = projectService.createProject(projectName, projectManagerName,
    			startDate, endDate);
    	
    	if (project) {
    		req.setAttribute("message","created Successfully");
    	} else {
    		req.setAttribute("message","UnSuccessfully");
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
    private void specificProjectDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int projectId = Integer.parseInt(req.getParameter("project_id"));
    	boolean isProjectExist = projectService.isProjectExist(projectId);
    	if (isProjectExist) {
    	    Map<String, String> projectDetails = 	projectService.getSpecificProject(projectId);
            req.setAttribute("message", "your details in below table");
    	    req.setAttribute("projectDetails", projectDetails);
    	}else {
    		req.setAttribute("message","Project Id doesn't exist");
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
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	if(projectService.deleteProject(projectId)) {
    		req.setAttribute("message","Deleted Successfully");
    	} else {
    		req.setAttribute("message","Unsuccessful");
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
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	if (projectService.restoreProject(projectId)) {
    		req.setAttribute("message","Restore Successfully");
    	} else {
    		req.setAttribute("message","Unsuccessful");
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
    		req.setAttribute("projects",projectService.getAllProject());
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
    	req.setAttribute("employeeDetails", projectService.getAllEmployeeDetails());
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
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	String[] employeeId = req.getParameterValues("employees");
    	List<Integer> employeeIds = new ArrayList<Integer>();
    	for (int index=0; index < employeeId.length; index++) {
    		employeeIds.add(Integer.parseInt(employeeId[index]));
    	}
    	boolean result = projectService.assignProject(projectId, employeeIds);
    	if (result) {
    		req.setAttribute("message","Assigned Succesfully");
    		
    	} else {
    		req.setAttribute("message","Unsuccessful");
    	}
    	req.getRequestDispatcher("/project?action=assign_employee_details").forward(req, res);
    }
    
    /**
     * It is used to get assigned project details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void getAssignedprojectDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	req.setAttribute("assignprojectDetails", projectService.getAssignedProjectDetails(projectId));
    	req.getRequestDispatcher("/unassign_project.jsp").forward(req, res);
    }
    
    /**
     * It is used to un assign project
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void unAssignEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	int empId = Integer.parseInt(req.getParameter("assigned_employee"));
    	if ( projectService.unAssignProject(projectId, empId)) {
    		req.setAttribute("message", "Unassigned Successfully");
    	} else {
    		req.setAttribute("message", "Unsuccessful");
   	    }
    	req.getRequestDispatcher("/project?action=get_assigned_project_details").forward(req, res);
    }
    
    /**
     * It will get all assigned employee details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void displayAssignedEmployeeDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	req.setAttribute("assignedEmployeeDetails", projectService.getAssignedProjectDetails(projectId));
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
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	Map<String, String> projectDetails = 	projectService.getSpecificProject(projectId);
    	req.setAttribute("projectDetails", projectDetails);
    	req.getRequestDispatcher("/update_project.jsp").forward(req, res);
    }
    
    /**
     * It is used to update project details
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws ServletException
     * @throws IOException
     */
    private void updateProject(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	int projectId = Integer.parseInt(req.getParameter("id"));
    	String projectName = req.getParameter("project_name");
    	String projectManagerName = req.getParameter("project_manager_name");
    	Date startDate = Date.valueOf(req.getParameter("start_date"));
    	Date endDate = Date.valueOf(req.getParameter("end_date"));
    	
    	boolean updateProject = projectService.updateProject(projectId, projectName,
    			projectManagerName, startDate, endDate);
    	
    	if (updateProject) {
    		req.setAttribute("message", "Updated successfully");
    	} else {
    		req.setAttribute("message", "Unsuccessful");
    	}
    	req.getRequestDispatcher("/project.jsp").forward(req, res);
    }
}