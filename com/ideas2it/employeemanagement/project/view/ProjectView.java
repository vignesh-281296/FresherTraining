package com.ideas2it.employeemanagement.project.view;

import java.sql.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
                    + "Enter Valid Date of birth: ");
            return validateDate();
        }
    }

    /**
     * It is used to get specific project details
     */
    private void getSpecificProjectDetails() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectIdExist(projectId)) {
            System.out.println(projectController.getSpecificProject(projectId));
        } else {
            System.out.println("Project id doesn't exist");
        }
    }

    /**
     * It is used to get project details
     */
    private void getAllProjectDetails() {
        if (projectController.getAllProject().size() > 0) {
            for (String projectDetails : projectController.getAllProject()){
                System.out.println(projectDetails + "\n");
            }
        } else {
            System.out.println("Project id doesn't exist");
        }
    }

    /**
     * It is used to delete specific project
     */
    private void deleteProject() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectIdExist(projectId)) {
            if (projectController.deleteProject(projectId)) {
                System.out.println("Delete Successfully");
            } else {
                System.out.println("Unsucessfull");
            }
        } else {
            System.out.println("Project id doesn't exist");
        }
    }
  
    /**
     * It is used to restore project 
     */
    private void restoreProject() {
        if (projectController.getDeletedProject().size() > 0) {
            for (String projects : projectController.getDeletedProject()) {
                System.out.println(projects + "\n");
            }
            System.out.println("Enter 1 to restore Employee Details \nEnter 2 to quit");
            int restoreChoice = scanner.nextInt();
            if (1 == restoreChoice) {
                System.out.println("Enter your project id");
                int projectId = scanner.nextInt();
                if (projectController.checkDeletedProjectId(projectId)) {
                    if (projectController.restoreProject(projectId)) {
                        System.out.println("Restore Successfully");
                    } else {
                        System.out.println("Unsuccessful");
                    }
                } else {
                    System.out.println("No project id exist");
                }
            } else if (2 == restoreChoice) {
                System.out.println("Thank you");
            }
        } else {
            System.out.println("No project to restore");
        }
    }

    /**
     * It is used to update project details
     */
    private void updateProjectDetails() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectIdExist(projectId)) {
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
        if (projectController.isProjectIdExist(projectId)) {
            List<Integer> employeeIds = new ArrayList<Integer>();
            String choiceDetails = "Enter 1 employee id \nEnter 2 exist";
            int choice = 0;
            while (2 != choice) {
                System.out.println(choiceDetails);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 :
                        System.out.println("Enter your employee id");
                        int employeeId = scanner.nextInt();
                        if (projectController.isEmpIdExist(employeeId)) {
                            employeeIds.add(employeeId);
                        } else {
                            System.out.println("Employee id doesn't exist");
                        } 
                        break;
                    case 2 :
                        System.out.println("Thank you");
                        break;
                    default :
                        System.out.println("Invalid choice");
                }
            }
            if (projectController.assignProject(projectId, employeeIds)) {
                System.out.println("Assigned Successfully");
            } else {
                System.out.println("Unsuccessful");
            }
        } else {
            System.out.println("Project Id doesn't exist");
        }
    }

    /**
     * It is used to get assigned project details
     */
    private void getAssignProject() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectIdExist(projectId)) {
            for (String projectDetails : projectController.getAssignProject(projectId)) {
                System.out.println(projectDetails);
            }
        } else {
            System.out.println("Project id doesn't exist");
        }  
    }

    /**
     * It performs CRUD operation
     */  
    public void operation() {
        int choice = 0;
        String choiceDetails = "Enter 1 to create project \nEnter 2 to Update Project \nEnter 3 to Delete Project"
                + "\nEnter 4 to Display Individual Project \nEnter 5 to Display Project"
                + "\nEnter 6 to restore \nEnter 7 to Assign project \nEnter 8 to Display assign project"
                + " \nEnter 9 to Exit \nEnter your choice";    
        while(9 != choice) {
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
                    getSpecificProjectDetails();
                    break;
                case 5 : 
                    getAllProjectDetails();
                    break;
                case 6 : 
                    restoreProject();
                    break;
                case 7 : 
                    assignProject();
                    break;
                case 8 : 
                    getAssignProject();
                    break;   
                case 9 :
                    System.out.println("Thank You");	
                    break;
                default :
                    System.out.println("Invalid Choice");        
            }
        }   
    }       
}