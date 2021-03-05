package com.ideas2it.employee.view;

import java.util.List;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern; 
import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;

/**
 * contain method to create,delete,update,display.
 *
 * @author vignesh r.
 * version1.0 01-03-2021.
 */
public class EmployeeView {
  
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();

    /**
     * create employee record
     */
    private void createEmployee() {
        System.out.println("Enter Your name");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter your city");
        String city = scanner.nextLine();
        System.out.println("Enter your email");
        String email = validateEmail(scanner.next());
        System.out.println("Enter your phone number");
        long phoneNumber = validatePhoneNumber(scanner.nextLong());
        System.out.println("Enter your salary");
        long salary = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter Date Of Birth in given format dd/mm/yyyy"); 
        Date dob = isValidateDate();
        if (employeeController.createEmployee(name, city, email, phoneNumber, salary, dob)) {
            System.out.println("Create Successfully");
        } else {
            System.out.println("Unsuccessful");
          }          
    }

    /** 
     * Method to display all employee
     */
    private void getAllEmployee() {
        List<String> employee = employeeController.displayAllEmployee();
        if (employee.size() > 0) {
            for (String employees : employee) {
                System.out.println(employees);
                System.out.println("===============================");
            }
        } else {
            System.out.println("No Data Exist in Collection");    
          }
    }

    /**
     * Method to delete employee
     */   
    private void deleteEmployee() {
        System.out.println("Enter your Employee Id");
        int id = scanner.nextInt();
        if (employeeController.empIdExist(id)) {
            employeeController.deleteEmployee(id);
            System.out.println("Delete Successfully");    
        } else {
            System.out.println("Emp ID does not exist");
          }
    }

    /**
     * update employee record based on employee id
     */
    private void updateEmployee() {
        System.out.println("Enter the field to update\n 1. update Name\n 2. update City\n"
                           + "3. update Email\n 4. update Phone\n 5.update Salary\n"
                           + "6.update Date of Birth\n Enter your choice" );
        int option = scanner.nextInt();
        System.out.println("Enter your Employee id");
        int id = scanner.nextInt();
        if (employeeController.empIdExist(id)) {
            switch(option) {
                case 1 :
                    System.out.println("Enter your name");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    employeeController.updateName(id, name);
                    System.out.println("Updated SuccessFully");
                    break;
                case 2 :
                    System.out.println("Enter your City");
                    scanner.nextLine();
                    String city = scanner.nextLine();
                    employeeController.updateCity(id, city);
                    System.out.println("Updated SuccessFully");
                    break;
                case 3 :
                    System.out.println("Enter your Email");
                    scanner.nextLine();
                    String emailId = scanner.nextLine();
                    employeeController.updateEmail(id, emailId);
                    System.out.println("Updated SuccessFully");
                    break;
                case 4 :
                    System.out.println("Enter your Phone Number");
                    long updatePhoneNumber = scanner.nextLong();
                    employeeController.updatePhoneNumber(id, updatePhoneNumber);
                    System.out.println("Updated SuccessFully");
                    break;
                case 5 :
                    System.out.println("Enter your Salary");
                    long updateSalary = scanner.nextLong();
                    employeeController.updateSalary(id, updateSalary);
                    System.out.println("Updated SuccessFully");
                    break;
                case 6 :
                    System.out.println("Enter Date Of Birth in given format dd/mm/yyyy");
                    Date dob = isValidateDate();
                    employeeController.updateDob(id, dob);
                    System.out.println("Updated SuccessFully");
                    break;
                default :
                    System.out.println("Invalid Choice");
            }            
        } else {
            System.out.println("Employee Id does not exist");
          }         
    }

    /**
     * Method to date validation
     */
    private Date isValidateDate() {
        
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
        } catch (ParseException exception) {
            System.out.println("Enter valid date format");
            return isValidateDate();
        }                  
    }

    /**
     * Method to validate phone number
     */
    private long validatePhoneNumber(long phoneNumber) {
        if (employeeController.validatePhoneNumber(phoneNumber)) {
            return phoneNumber;
        } else {
            System.out.print("Invalid Phone Number\n"
                                   + "Enter Valid Phone Number: ");
            return validatePhoneNumber(scanner.nextLong());
          }
    }

    /**
     * Method to validate email id
     */ 
    private String validateEmail(String emailId) {
        if (employeeController.validateEamil(emailId)) {
            return emailId;
        } else {
            System.out.print("Invalid EmailID\n"
                                   + "Enter Valid Email ID: ");
            return validateEmail(scanner.next());
          }
    } 
    
    /**
     * Method to CRUD operation
     */  
    public void operation() {
        int choice = 0;
        String choiceDetails = "Enter 1 to create employee \n Enter 2 to Update employee \n Enter 3 to Delete employee"
                                + "\n Enter 4 to Display Employee \n Enter 5 to Exit";
        while (5 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 : 
                    createEmployee();
                    break;
                case 2 :
                    updateEmployee();
                    
                    break;
                case 3 :
                    deleteEmployee();
                    break;
                case 4 :
                    getAllEmployee();
                    break;    
                case 5 :
                    System.out.println("Thank You");
                    break;
                default :
                    System.out.println("Invalid Choice");        
            }
        }
    }
}