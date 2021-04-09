package com.ideas2it.employeemanagement.project.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.project.controller.ProjectController;

/**
 * project view
 *
 * @author vignesh r
 * @version 1.0 24-03-2021
 */
public class ProjectView {

    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();

    /**
     * It is used to create project
     */
    private void createProject() {
        scanner.skip("\r\n");
        System.out.println("Enter your project name");
        String name = scanner.nextLine();
        System.out.println("Enter your project manager name");
        String managerName = scanner.nextLine();
        System.out.println("Enter your project start date");
        Date startDate = Date.valueOf(validateDate());
        System.out.println("Enter your project end date");
        Date endDate = Date.valueOf(validateDate());
        boolean result = projectController.createProject(name,
                managerName, startDate, endDate);
        if (result) {
            System.out.println("created Successfully");
        } else {
            System.out.println("Unsuccessful");
        } 
    }

    /**
     * It is used to validate date
     */
    private String validateDate() {
        String date = scanner.next();
         if (projectController.validateDate(date)) {
            return date;
        } else {
            System.out.print("Invalid Date of birth\n"
                    + "Enter Valid Date: ");
            return validateDate();
        }
    }

    /**
     * It is used to display individual project details
     */
    private void displayIndividualProject() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectExist(projectId)) {
            System.out.println("\n" + projectController.getSpecificProject(projectId));
        } else {
            System.out.println("Project doesn't exist");
        }
    }

    /**
     * It is used to get all project details
     */
    private void displayAllProject() {
        if (projectController.getAllProject().size() > 0) {
            for (String projects : projectController.getAllProject()) {
                System.out.println(projects);
            }
        } else {
            System.out.println("No records");
        }
    }

    /**
     * It is used to delete project
     */
    private void deleteProject() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectExist(projectId)) {
            if (projectController.deleteProject(projectId)) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Unsuccessful");
            }
        } else {
            System.out.println("project id doesn't exist");
        }    
    }

    /**
     * It is used to restore project
     */
    private void restoreProject() {
        for (String projectDetails : projectController.getDeletedProjectDetails()) {
            System.out.println(projectDetails);
        }
        if (projectController.getDeletedProjectDetails().size() > 0) {
            System.out.println("Enter 1 to restore Project Details \nEnter 2 to quit");
            int restoreChoice = scanner.nextInt();   
            if (1 == restoreChoice) {
                System.out.println("Enter your project id");
                int projectId = scanner.nextInt();
                if (projectController.isProjectDeleted(projectId)) {
                    if (projectController.restoreProject(projectId)) {
                        System.out.println("Restored successfully");
                    } else {
                        System.out.println("Unsuccessful");
                    }    
                } else {
                    System.out.println("Project id doesn't exist");
                }
            } else if (2 == restoreChoice){
                System.out.println("Thank you");
            }
        } else {
            System.out.println("No records");
        }
    }

    /**
     * It is used to update project details
     */
    private void updateProjectDetails() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectExist(projectId)) {
           String[] projectDetails = getProjectDetails();
           if (projectController.updateProject(projectId, projectDetails)) {
               System.out.println("Updated successfully");
           } else {
               System.out.println("Unsuccessful");
           }
        } else {
            System.out.println("Project id doesn't exist");
        }
    }

    /**
     * It is used to get project details to update specific project
     */
    private String[] getProjectDetails() {
        String[] projectDetails = new String[4];
        projectDetails[0] = null;
        projectDetails[1] = null;
        projectDetails[2] = null;
        projectDetails[3] = null;
        System.out.println("Please select your which project details need to update?");
        int updateProjectDetailChoice = 0;
        String updateProjectChoiceDetail = "Enter 1 to update project name" 
                + "\nEnter 2 to update manager name"
                + "\nEnter 3 to update start date"
                + "\nEnter 4 to update end date"
                + "\nEnter 5 to quit";
        while (5 != updateProjectDetailChoice) {
            System.out.println(updateProjectChoiceDetail);
            updateProjectDetailChoice = scanner.nextInt();
            switch (updateProjectDetailChoice) {
                case 1 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your project name");
                    projectDetails[0] = scanner.nextLine();
                    break;
                case 2 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your project manager name");
                    projectDetails[1] = scanner.nextLine();
                    break;
                case 3 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your project start date format (yyyy-MM-dd)");
                    projectDetails[2] = scanner.next();
                    break;
                case 4 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your project end date format (yyyy-MM-dd)");
                    projectDetails[3] = scanner.nextLine();
                    break;
                case 5 :
                    System.out.println("Thank you");
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        } 
        return projectDetails;   
    }

    /**
     * It is used to assign project to employees
     */
    private void assignProject() {
        System.out.println("Employee List");
        for (String employeeDetails : projectController.getAllEmployeeDetails()) {
            System.out.println(employeeDetails + "\n");
        }
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectExist(projectId)) {
            getAssignProjectDetails(projectId);    
        } else {
            System.out.println("Project Id doesn't exist");
        }
    }

    /**
     * It is used to get assign project details
     */
    private void getAssignProjectDetails(int projectId) {
        List<Integer> employeeIds = new ArrayList<Integer>();
        String choiceDetails = "Enter 1 employee id \nEnter 2 exist";
        int choice = 0;
        int flag = 0;
        while (2 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    System.out.println("Enter your employee id");
                    int employeeId = scanner.nextInt();
                    if (projectController.isEmployeeExist(employeeId)) {
                        flag = 1;
                        employeeIds.add(employeeId);
                    } else {
                        System.out.println("Employee id doesn't exist");
                    } 
                    break;
                case 2 :
                    if (0 == flag) {
                        System.out.println("Employee doesn't assigned");
                    } else {
                        System.out.println("Thank you");
                    }
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        }
        if (1 == flag) {
            if (projectController.assignProject(projectId, employeeIds)) {
                System.out.println("Assigned Successfully");
            } else {
                System.out.println("Unsuccessful");
            }    
        } else {
            System.out.println("Unsuccessful");
        } 
            
    }

    /**
     * It is used to display assigned project details
     */
    private void displayAssignedProject() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectExist(projectId)) {
            for (String projectDetails : projectController.getAssignedProject(projectId)) {
                System.out.println(projectDetails + "\n");
            }
        } else {
            System.out.println("Project id doesn't exist");
        }  
    }

    /**
     * It is used to unassign project
     */
    public void unAssignProject() {
        System.out.println("Enter your Employee id");
        int employeeId = scanner.nextInt();
        if (projectController.isEmployeeExist(employeeId)) {
            System.out.println("Enter your project Id");
            int projectId = scanner.nextInt();
            if (projectController.isProjectExist(projectId)) {
                if (projectController.unAssignProject(projectId, employeeId)) {
                    System.out.println("Unassigned successfully");
                } else {
                    System.out.println("Unsuccessful");
                }
            } else {
                System.out.println("project id doesn't exist");
            }
        } else {
            System.out.println("employee id doesn't exist");   
        }
    }

    /**
     * It performs CRUD operation
     */  
    public void operation() {
        int choice = 0;
        String choiceDetails = "Enter 1 to create project \nEnter 2 to Update Project \nEnter 3 to Delete Project"
                + "\nEnter 4 to Display Individual Project \nEnter 5 to Display All Project"
                + "\nEnter 6 to restore \nEnter 7 to Assign project to employee \nEnter 8 to Display assigned project employee"
                + " \nEnter 9 to UnAssign project \nEnter 10 to Exit \nEnter your choice";    
        while(10 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 : 
                     createProject();
                     break;
                case 2 :
                     updateProjectDetails(); 
                     break;
                case 3 :
                     deleteProject(); 
                     break;
                case 4 : 
                     displayIndividualProject();
                     break;
                case 5 : 
                     displayAllProject();
                     break;
                case 6 : 
                     restoreProject();
                     break;
                 case 7 : 
                     assignProject();
                     break;
                case 8 : 
                     displayAssignedProject();
                     break;
                case 9 : 
                    unAssignProject();
                    break;     
                case 10 :
                    System.out.println("Thank You");	
                    break;
                default :
                    System.out.println("Invalid Choice");        
            }
        }   
    }       
}