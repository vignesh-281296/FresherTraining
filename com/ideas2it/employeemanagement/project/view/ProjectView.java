package com.ideas2it.employeemanagement.project.view;

import java.sql.Date;
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
    public void getSpecificProjectDetails() {
        System.out.println("Enter your project id");
        int projectId = scanner.nextInt();
        if (projectController.isProjectIdExist(projectId)) {
            System.out.println(projectController.getSpecificProject(projectId));
        } else {
            System.out.println("Project id doesn't exist");
        }
    }

    /**
     * It is used to get specific project details
     */
    public void getAllProjectDetails() {
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
    public void deleteProject() {
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
     * It performs CRUD operation
     */  
    public void operation() {
        int choice = 0;
        String choiceDetails = "Enter 1 to create project \nEnter 2 to Update employee \nEnter 3 to Delete employee"
                + "\nEnter 4 to Display Individual Employee \nEnter 5 to Display Employee"
                + "\nEnter 6 to restore  \nEnter 7 to Exit \nEnter your choice";    
        while(7 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 : 
                     createProject();
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
                case 7 :
                    System.out.println("Thank You");	
                    break;
                default :
                    System.out.println("Invalid Choice");        
            }
        }   
    }       
}