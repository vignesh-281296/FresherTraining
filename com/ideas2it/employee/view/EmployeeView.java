package com.ideas2it.employee.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;

/**
 * This class is used to perform CRUD operations
 *
 * @author vignesh r
 * @version 1.0 13-03-2021
 */ 
public class EmployeeView {

    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /**
     * Method is used to create new employee details
     */
    private void createEmployee() throws SQLException, ClassNotFoundException {
        scanner.nextLine();
        System.out.println("Enter Your name");
        String name = scanner.nextLine();
        System.out.println("Enter your desgination");
        String desgination = scanner.nextLine();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your phone number");
        long phoneNumber = scanner.nextLong();
        System.out.println("Enter your salary");
        long salary = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter Date Of Birth in given format yyyy-MM-dd");
        String date = scanner.nextLine(); 
        Date dob = Date.valueOf(date);
        String address[] = addressOperation();
        List<String[]> addressDetails = new ArrayList<String[]>();
        addressDetails.add(address); 
        boolean employee = employeeController.createEmployee(name, desgination, email, phoneNumber, 
                                                             salary, dob, addressDetails);
        if (employee) {
            System.out.println("Employee created successfully");         
        } else {
            System.out.println("Unsuccessful");
          }            
    }

    /**
     * Method to address operation to enter type of address
     */
    private String[] addressOperation() throws SQLException, ClassNotFoundException {
        System.out.println("Please select your type of address");
        int addressChoice = 0;
        String addressChoiceDetail = "Enter 1 to home address" 
                                      + "\n Enter 2 to current address"
                                      + "\n Enter 3 to permanent address"
                                      + "\n Enter 4 to temporary address"
                                      + "\n Enter 5 to office address"
                                      + "\n Enter 6 to quit";
        String[] addressDetails = {};
        while (6 != addressChoice) {
            System.out.println(addressChoiceDetail);
            addressChoice = scanner.nextInt();
                switch (addressChoice) {
                    case 1 :
                        addressDetails = createAddress("homeaddress");
                        break;
                    case 2 :
                        addressDetails = createAddress("currentnaddress");
                        break;
                    case 3 :
                        addressDetails = createAddress("permanentaddress");
                        break;
                    case 4 :
                        addressDetails = createAddress("temporaryaddress");
                        break;
                    case 5 :
                        addressDetails = createAddress("officeaddress");
                        break;
                    case 6 :
                        System.out.println("Thank you");
                        break;
                    default :
                        System.out.println("Invalid choice");
                        break;
                }
        }
        return addressDetails;
    }

    /**
     * Method is used to store employee address
     * @param addressMode type of employee address
     * @return employee address details
     */
    private String[] createAddress(String addressMode) throws SQLException, ClassNotFoundException {
        String[] address = new String[7];
        scanner.nextLine();
        System.out.println("Enter your door no");
        address[0] = scanner.nextLine();
        System.out.println("Enter your street name");
        address[1] = scanner.nextLine();
        System.out.println("Enter your city");
        address[2] = scanner.nextLine();
        System.out.println("Enter your district");
        address[3] = scanner.nextLine();
        System.out.println("Enter your state");
        address[4] = scanner.nextLine();
        System.out.println("Enter your country");
        address[5] = scanner.nextLine();
        address[6] = addressMode;
        return address;
    }

    /**
     * Method to delete employee
     */
    private void deleteEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your Employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            if (employeeController.deleteEmployee(empId)) {
                System.out.println("Deleted Successfully");
            } else {
                System.out.println("Unsuccessful");
              }
            
        } else {
            System.out.println("Employee id doesn't exist");
          }
    }

    /**
     * Method to display individual employee detail
     */
    private void getSpecificEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            System.out.println("==== Employee =====");
            System.out.println(employeeController.getSpecificEmployee(empId));
        } else {
            System.out.println("Employee id doesn't exist");
          }
    } 

     /**
     * Method to display all employee details
     */
    private void displayAllEmployee() throws SQLException, ClassNotFoundException {
        for (String employees : employeeController.getAllEmployee()) {
            System.out.println(employees + "\n");
        }   
    } 

    /**
     * Method to CRUD operation
     */  
    public void operation() throws SQLException, ClassNotFoundException {
        int choice = 0;
        String choiceDetails = "Enter 1 to create employee \n Enter 2 to Update employee \n Enter 3 to Delete employee"
                                + "\n Enter 4 to Display Individual Employee \n Enter 5 to Display Employee"
                                + "\n Enter 6 to Exit \n Enter your choice";    
        while(6 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 : 
                    createEmployee();
                    break;
                case 3 : 
                    deleteEmployee();
                    break;
                case 4 :
                    displayAllEmployee(); 
                    break;
                case 5 : 
                    getSpecificEmployee();
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