package com.ideas2it.employeemanagement.employee.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.employeemanagement.employee.dao.impl.EmployeeDaoImpl;
import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.employee.service.EmployeeService;
import com.ideas2it.employeemanagement.employee.service.impl.EmployeeServiceImpl;
import com.ideas2it.exceptions.EmployeeManagementException;
import com.ideas2it.loggers.EmployeeManagementLogger;

/**
 * It will send data to employee service
 */
@Controller
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private  EmployeeManagementLogger logger = new EmployeeManagementLogger(EmployeeDaoImpl.class);
    
//	ApplicationContext context =   new ClassPathXmlApplicationContext("EmployeeManagement-servlet.xml");
//
//	private EmployeeService employeeService = (EmployeeService)context.getBean("employeeService");
   
   /**
    * It will redirect to employee create form
    * @param employee employee object
    * @return create employee page
    */
    @GetMapping("/get_employee_form")
    public String getEmployeeForm(@ModelAttribute("employee") Employee employee) {
    	return "create_employee";
    }
    
    /**
     * It is used to create employee
     * @param employee employee object
     * @return  modelAndView object
     
     */
    @PostMapping("/create_employee")
	private ModelAndView createEmployee(Employee employee, @RequestParam("temporary") String address) {
        ModelAndView modelAndView = new ModelAndView();
		try {
			List<Address> addresses = new ArrayList<Address>();
			if(0 == Integer.parseInt(address)) {
				addresses.add(employee.getAddressess().get(0));
				employee.setAddressess(addresses);
			}
			employeeService.insertEmployee(employee);
			modelAndView.addObject("message", "create Successfully");
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message",  e.getMessage());
		}
		modelAndView.setViewName("employee");
		return modelAndView;
	}

	/**
	 * It will get individual employee details
	 * @param id employee id
	 * @return  modelAndView object
	 */
    @GetMapping("/single_employee_detail/{id}")
	private ModelAndView getSpecificEmployeeDetails(@PathVariable int id) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("employee", employeeService.getSpecificEmployee(id));	
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("specific_employee");
		return modelAndView;
	}

	/**
	 * It is used to delete employee
	 * @param id employee id 
	 * @return modelAndView object
	 */
    @GetMapping("/delete_employee/{id}")
	private ModelAndView deleteEmployee(@PathVariable int id) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			employeeService.deleteEmployee(id);
			modelAndView.addObject("message", "Deleted Successfully");
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("delete_employee");
		return modelAndView;
	}

	/**
	 * It is used to restore employee 
	 * @param id employee id
	 * @return  modelAndView object
	 */
    @GetMapping("/restore_employee/{id}")
	private ModelAndView restoreEmployee(@PathVariable int id){
    	ModelAndView modelAndView = new ModelAndView();
		try {
			employeeService.restoreEmployee(id);
			modelAndView.addObject("message", "Restore Successfully");
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("restore_employee");
		return modelAndView;
	}

	/**
	 * It is used to get all employee details 
	 * @return modelAndView object
	 */
    @GetMapping("/get_all_employee")
	private ModelAndView getAllEmployeeDetails() {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("employees", employeeService.getAllEmployee());
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("display_all_employee");
		return modelAndView;
	}

	/**
	 * It is used to get project details
	 * @param id employee id
	 * @return modelAndView object
	 */
    @GetMapping("/get_assign_project/{id}")
	private ModelAndView ProjectsDetails(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("projectDetails", employeeService.getAllProjectDetails());
			modelAndView.addObject("assignProjectDetails", employeeService.getAssignedEmployeeDetails(id));
			modelAndView.addObject("id", id);
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("assign_project");
		return modelAndView;
	}

	/**
	 * It is used to assign project 
	 * @param id employee id
	 * @param projectId
	 * @return  modelAndView object
	 */
    @PostMapping("/assign_project")
	private ModelAndView assignProject(@RequestParam int id, @RequestParam(value = "projects", required = false)String[] projectId) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			List<Integer> projectIds = new ArrayList<Integer>();
			if (null != projectId) {
				for (int index = 0; index < projectId.length; index++) {
					projectIds.add(Integer.parseInt(projectId[index]));
				}
			}
			employeeService.assignEmployee(id, projectIds);
			modelAndView.addObject("message", "Assigned Project Updated Succesfully");
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("employee");
		return modelAndView;
	}

	/**
	 * It is used to get assigned employee details 
	 * @param id employee id
	 * @return  modelAndView object
	 */
    @GetMapping("/get_assigned_project_details/{id}")
	private ModelAndView getAssignedEmployeeDetails(@PathVariable int id) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("assignEmployeeDetails", employeeService.getAssignedEmployeeDetails(id));
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("assigned_employee_details");
		return modelAndView;
	}

	/**
	 * It will get employee detail to update
	 * @param id employee id
	 * @return  modelAndView object
	 */
    @GetMapping("/get_employee_detail/{id}")
	private ModelAndView updateEmployeeDetails(@PathVariable int id) {
    	System.out.println("iam here");
    	ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("employee", employeeService.getSpecificEmployee(id));
			
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("create_employee");
		return modelAndView;
	}

	/**
	 * It is used to update employee
	 * @param employee employee object
	 * @return modelAndView object
	 */
    @PostMapping("/update_employee")
	private ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee, @RequestParam("temporary") String address) {
    	ModelAndView modelAndView = new ModelAndView();
		try {
			List<Address> addresses = new ArrayList<Address>();
			if(0 == Integer.parseInt(address)) {
				addresses.add(employee.getAddressess().get(0));
				employee.setAddressess(addresses);
			}
			employeeService.updateEmployee(employee);
			modelAndView.addObject("message", "Updated Successfully");
		} catch (EmployeeManagementException e) {
			logger.logError(e);
			modelAndView.addObject("message", e.getMessage());
		}
		modelAndView.setViewName("employee");
		return modelAndView;
	}
    
    /**
     * It will redirect to index page
     * @return index page
     */
    @GetMapping("/")
    public String indexPage() {
    	return "index";
    }
    
    /**
     * It will redirect to employee page
     * @return employee page
     */
    @GetMapping("/employee")
    public String employeePage() {
    	return "employee";
    }
}