package com.ideas2it.employee.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern; 
import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;

/**
 * class is used to perform crud operations.
 *
 * @author vignesh r.
 * @version 1.0 01-03-2021.
 */
public class EmployeeView {
  
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();

    /**
     * Method is used to create new employee details
     */
    private void createEmployee() {
        scanner.nextLine();
        System.out.println("Enter Your name");
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
        Date dob = ValidateDate();
        if (employeeController.createEmployee(name, city, email, phoneNumber, salary, dob)) {
            System.out.println("Create Successfully");
        } else {
            System.out.println("Unsuccessful");
          }          
    }

    /** 
     * Method is used to display created employee details
     */
    private void getAllEmployee() {
        List<String> employees = employeeController.displayAllEmployee();
        if (0 < employees.size()) {
            for (String employee : employees) {
                System.out.println(employee);
                System.out.println("===============================");
            }
        } else {
            System.out.println("No Data Exist in Collection");    
          }
    }

    /**
     *Method to display individual employee detail
     */
    private void getEmployee() {
        System.out.println("Enter your Employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            System.out.println(employeeController.getEmployee(empId));
        } else {
            System.out.println("Emp Id does not exist");
          }
    }

    /**
     * Method is perform the deletion of employee details
     */   
    private void deleteEmployee() {
        System.out.println("Enter your Employee Id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            if (employeeController.deleteEmployee(empId)) {
                System.out.println("Delete Successfully" + empId);
            } else {
                System.out.println("Unsuccesfull");
              }               
        } else {
            System.out.println("Emp ID does not exist");
          }
    }

    /**
     * Method to update employee record based on employee id
     */
    private void updateEmployee() {
        System.out.println("Enter your Employee id");
        int empId = scanner.nextInt();
        if (!employeeController.isEmpIdExist(empId)) {
            System.out.println("Employee id does not exist");
        } else {
            System.out.println("Enter your Update choice");
            String updationOptionStatement ="Enter the field to update\n 1. Name\n 2. City\n"
                                             + "3. Email\n 4. Phone\n 5.Salary\n"
                                             + "6.Date of Birth\n 7. Cancel Updation Enter your choice ";
        int option = 0;
            while (7 != option) {
                System.out.println(updationOptionStatement);
                option = scanner.nextInt();
                switch (option) {
                    case 1 :
                        scanner.nextLine();
                        System.out.println("Enter your name");
                        String name = scanner.nextLine();
                        employeeController.updateName(empId, name);
                        System.out.println("Updated SuccessFully");
                        break;
                    case 2 :
                        System.out.println("Enter your City");
                         String city = scanner.nextLine();
                         employeeController.updateCity(empId, city);
                         System.out.println("Updated SuccessFully");
                         break;
                    case 3 :
                        System.out.println("Enter your Email");
                        String emailId = scanner.nextLine();
                        employeeController.updateEmail(empId, emailId);
                        System.out.println("Updated SuccessFully");
                        break;
                    case 4 :
                        System.out.println("Enter your Phone Number");
                        long updatePhoneNumber = scanner.nextLong();
                        employeeController.updatePhoneNumber(empId, updatePhoneNumber);
                        System.out.println("Updated SuccessFully");
                        break;
                    case 5 :
                        System.out.println("Enter your Salary");
                        long updateSalary = scanner.nextLong();
                        employeeController.updateSalary(empId, updateSalary);
                        System.out.println("Updated SuccessFully");
                        break;
                    case 6 :
                        System.out.println("Enter Date Of Birth in given format dd/mm/yyyy");
                        Date dob = ValidateDate();
                        employeeController.updateDob(empId, dob);
                        System.out.println("Updated SuccessFully");
                        break;
                    case 7 :
                        System.out.println("Update Cancelled");
                        break;
                    default :
                        System.out.println("Invalid Choice");
                } 
            }
             
        }                    
    }

    /**
     * Method to check date validation
     */
    private Date ValidateDate() {     
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
        } catch (ParseException exception) {
            System.out.println("Enter valid date format");
            return ValidateDate();
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
                                + "\n Enter 4 to Display Individual Employee \n Enter 5 to Display Employee"
                                + "\n Enter 6 to Exit \n Entr your choice";    
        while(6 != choice) {
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
                    getEmployee();
                    break;
                case 5 :
                    getAllEmployee();
                    break;    
                case 6 :
                    System.out.println("Thank You");
                    break;
                default :
                    System.out.println("Invalid Choice");        
            }
        }   
    }
}