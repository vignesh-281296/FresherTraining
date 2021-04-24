package com.ideas2it.employeemanagement.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

//import com.ideas2it.loggers.EmployeeManagementLogger;
//import org.apache.log4j.Logger;

/**
 * It will send data to employee service
 */
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeController.class);
   
    /**
     * Post pass requests and performs the actions
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)  {
    	
    	try {
			PrintWriter out =  res.getWriter();
			String action = req.getParameter("action");
	    	switch(action) {
	    	case "create_employee" :
				createEmployee(req, res);
	    		break;
	    	case "specific_employee" :
	    		specificEmployeeDetails(req, res);
	    		break;
	    	case "assign_projects" :
	    		assignProject(req, res);
	    		break;
	    	case "update_employee" :
	    		updateEmployee(req, res);
	    		break;	
	    	 default :
	    		 errorPage(req, res);	 
	    	}
		} catch (IOException | ServletException e) {
			logger.logError(e);
		}
    }
    
    /**
     * Get request and performs actions according to their path
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	
		try {
			//PrintWriter out = res.getWriter();
			String action = req.getParameter("action");
			switch (action) {
			case "delete_employee":
				deleteEmployee(req, res);
				break;
			case "restore_employee":
				restoreEmployee(req, res);
				break;
			case "display_all_employee":
				//logger.logError("log");
				getAllEmployeeDetails(req, res);
				break;
			case "assign_project_details":
				ProjectsDetails(req, res);
				break;
			case "get_assigned_employee_details":
				getAssignedEmployeeDetails(req, res);
				break;
			case "assigned_employee_details":
				displayAssignedEmployeeDetails(req, res);
				break;
			case "update_employee_details":
				updateEmployeeDetails(req, res);
				break;
			default:
				errorPage(req, res);
			}
		} catch (IOException | ServletException e) {
			logger.logError(e);
		}
	}
    
    /**
     * It is used to create employee
     * @param req provides HttpServletRequest information for HttpServlet
     * @param res provides HttpServletResponse information for HttpServlet
     * @throws IOException
     * @throws ServletException
     */
	private void createEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		try {
			String name = req.getParameter("name");
			String desgination = req.getParameter("desgination");
			String email = req.getParameter("email");
			long phoneNumber = Long.parseLong(req.getParameter("phone_number"));
			float salary = Float.parseFloat(req.getParameter("salary"));
			Date dob = Date.valueOf(req.getParameter("dob"));

			List<List<String>> addressess = new ArrayList<List<String>>();
			List<String> permanentAddress = new ArrayList<String>();
			permanentAddress.add(req.getParameter("door_no"));
			permanentAddress.add(req.getParameter("street_name"));
			permanentAddress.add(req.getParameter("city"));
			permanentAddress.add(req.getParameter("district"));
			permanentAddress.add(req.getParameter("state"));
			permanentAddress.add(req.getParameter("country"));
			permanentAddress.add("permanentaddress");
			addressess.add(permanentAddress);
			int temporaryOption = Integer.parseInt(req.getParameter("temporary"));

			if (1 == temporaryOption) {
				List<String> temporaryAddressDetails = new ArrayList<String>();
				temporaryAddressDetails.add(req.getParameter("temporary_door_no"));
				temporaryAddressDetails.add(req.getParameter("temporary_street_name"));
				temporaryAddressDetails.add(req.getParameter("temporary_city"));
				temporaryAddressDetails.add(req.getParameter("temporary_district"));
				temporaryAddressDetails.add(req.getParameter("temporary_state"));
				temporaryAddressDetails.add(req.getParameter("temporary_country"));
				temporaryAddressDetails.add("temporaryaddress");
				addressess.add(temporaryAddressDetails);
			}

			employeeService.insertEmployee(name, desgination, email, phoneNumber, salary, dob, addressess);
			req.setAttribute("message", "created Successfully");
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/employee.jsp").forward(req, res);
	}

	/**
	 * It will get individual employee details
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void specificEmployeeDetails(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("employee_id"));
			if (employeeService.isEmployeeExist(empId)) {
				req.setAttribute("employee", employeeService.getSpecificEmployee(empId));
			} else {
				req.setAttribute("message", "Employee id doesn't exist");
			}
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/specific_employee.jsp").forward(req, res);
	}

	/**
	 * It is used to delete employee
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("id"));
			employeeService.deleteEmployee(empId);
			req.setAttribute("message", "Deleted Successfully");
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/delete_employee.jsp").forward(req, res);
	}

	/**
	 * It is used to restore employee details
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void restoreEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("id"));
			employeeService.restoreEmployee(empId);
			req.setAttribute("message", "Restore Successfully");
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/restore_employee.jsp").forward(req, res);
	}

	/**
	 * It is used to get all employee details
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAllEmployeeDetails(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			req.setAttribute("employees", employeeService.getAllEmployee());
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/display_all_employee.jsp").forward(req, res);
	}

	/**
	 * It is used to get project details
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ProjectsDetails(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			req.setAttribute("projectDetails", employeeService.getAllProjectDetails());
			int empId = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("assignProjectDetails", employeeService.getAssignedEmployeeDetails(empId));
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/assign_project.jsp").forward(req, res);
	}

	/**
	 * It is used to assign project
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void assignProject(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("id"));

			String[] projectId = req.getParameterValues("projects");
			List<Integer> projectIds = new ArrayList<Integer>();
			if (null != projectId) {
				for (int index = 0; index < projectId.length; index++) {
					projectIds.add(Integer.parseInt(projectId[index]));
				}
			}
			employeeService.assignEmployee(empId, projectIds);
			req.setAttribute("message", "Assigned Succesfully");
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/employee.jsp").forward(req, res);
	}

	/**
	 * It is used to get assigned employee details
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAssignedEmployeeDetails(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("assignEmployeeDetails", employeeService.getAssignedEmployeeDetails(empId));
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/unassign_employee.jsp").forward(req, res);
	}

	/**
	 * It is used to un assign employee
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	/*
	 * private void unAssignEmployee(HttpServletRequest req, HttpServletResponse
	 * res) throws ServletException, IOException { int empId =
	 * Integer.parseInt(req.getParameter("id")); int projectId =
	 * Integer.parseInt(req.getParameter("assigned_project")); if (
	 * employeeService.unAssignEmployee(empId, projectId)) {
	 * req.setAttribute("message", "Unassigned Successfully"); } else {
	 * req.setAttribute("message", "Unsuccessful"); }
	 * req.getRequestDispatcher("/employee?action=get_assigned_employee_details").
	 * forward(req, res); }
	 */

	/**
	 * It is used to display assigned employee details
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayAssignedEmployeeDetails(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("assignedEmployeeDetails", employeeService.getAssignedEmployeeDetails(empId));
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("assigned_employee_details.jsp").forward(req, res);
	}

	/**
	 * It will get employee detail to update
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateEmployeeDetails(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			int emptId = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("employee", employeeService.getSpecificEmployee(emptId));
			req.setAttribute("permanentAddress", employeeService.getSpecificEmployee(emptId).getAddressess().get(0));
			if (1 < employeeService.getSpecificEmployee(emptId).getAddressess().size()) {
				req.setAttribute("temporaryAddress",
						employeeService.getSpecificEmployee(emptId).getAddressess().get(1));
			}
		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		// System.out.println(employeeService.getSpecificEmployee(emptId).getAddressess().get(1));
		req.getRequestDispatcher("/create_employee.jsp").forward(req, res);
	}

	/**
	 * It is used to update employee
	 * 
	 * @param req provides HttpServletRequest information for HttpServlet
	 * @param res provides HttpServletResponse information for HttpServlet
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int empId = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String desgination = req.getParameter("desgination");
			String email = req.getParameter("email");
			long phoneNumber = Long.parseLong(req.getParameter("phone_number"));
			float salary = Float.parseFloat(req.getParameter("salary"));
			Date dob = Date.valueOf(req.getParameter("dob"));

			List<List<String>> addressess = new ArrayList<List<String>>();
			List<String> permanentAddress = new ArrayList<String>();
			permanentAddress.add(req.getParameter("permanent_address_id"));
			permanentAddress.add(req.getParameter("door_no"));
			permanentAddress.add(req.getParameter("street_name"));
			permanentAddress.add(req.getParameter("city"));
			permanentAddress.add(req.getParameter("district"));
			permanentAddress.add(req.getParameter("state"));
			permanentAddress.add(req.getParameter("country"));
			permanentAddress.add("permanentaddress");
			addressess.add(permanentAddress);
			System.out.println(req.getParameter("permanent_address_id"));
			int temporaryOption = Integer.parseInt(req.getParameter("temporary"));

			if (1 == temporaryOption) {
				List<String> temporaryAddressDetails = new ArrayList<String>();
				temporaryAddressDetails.add(req.getParameter("temporary_address_id"));
				temporaryAddressDetails.add(req.getParameter("temporary_door_no"));
				temporaryAddressDetails.add(req.getParameter("temporary_street_name"));
				temporaryAddressDetails.add(req.getParameter("temporary_city"));
				temporaryAddressDetails.add(req.getParameter("temporary_district"));
				temporaryAddressDetails.add(req.getParameter("temporary_state"));
				temporaryAddressDetails.add(req.getParameter("temporary_country"));
				temporaryAddressDetails.add("temporaryaddress");
				addressess.add(temporaryAddressDetails);
				System.out.println(req.getParameter("temporary_address_id"));
			}

			employeeService.updateEmployee(empId, name, desgination, email, phoneNumber, salary, dob, addressess);

			req.setAttribute("message", "Updated Successfully");

		} catch (EmployeeManagementException e) {
			req.setAttribute("message", e.getMessage());
		}
		req.getRequestDispatcher("/employee.jsp").forward(req, res);
	}

	private void errorPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("404_page.jsp");
	}
}