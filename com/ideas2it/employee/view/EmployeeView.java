package com.ideas2it.employee.view;

import java.util.Scanner;
import java.sql.Date;

import com.ideas2it.employee.controller.EmployeeController;

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
        boolean employee = employeeController.createEmployee(name, desgination, email, phoneNumber, 
                                                             salary, dob);
        if (employee) {
            addressOperation();     
        }            
    }

    /**
     * Method to address operation to enter type of address
     */
    private void addressOperation() {
        System.out.println("Please select your address choice");
        int addressChoice = 0;
        String addressChoiceDetail = "Enter 1 to home address" 
                                      + "\n Enter 2 to current address"
                                      + "\n Enter 3 to permanent address"
                                      + "\n Enter 4 to temporary address"
                                      + "\n Enter 5 to office address"
                                      + "\n Enter 6 to quit";
        while (6 != addressChoice) {
            System.out.println(addressChoiceDetail);
            addressChoice = scanner.nextInt();
                switch (addressChoice) {
                    case 1 :
                        createAddress("homeaddress");
                        System.out.println("Home address created successfully");
                        break;
                    case 2 :
                        createAddress("currentnaddress");
                        System.out.println("Current address created successfully");
                        break;
                    case 3 :
                        createAddress("permanentaddress");
                        System.out.println("Permanent address created successfully");
                        break;
                    case 4 :
                        createAddress("temporaryaddress");
                        System.out.println("Temporary address created successfully");
                        break;
                    case 5 :
                        createAddress("officeaddress");
                        System.out.println("Office address created successfully");
                        break;
                    case 6 :
                        System.out.println("Thank you");
                        break;
                    default :
                        System.out.println("Invalid choice");
                }
        }
    }

    /**
     * Method is used to create employee address
     * @param employeeId employee id
     * @param addressMode type of employee address
     * @return true if employee address created or false
     */
    private boolean createAddress(String addressMode) {
        System.out.println("Enter your door no");
        String doorNo = scanner.nextLine();
        System.out.println("Enter your street name");
        String streetName = scanner.nextLine();
        System.out.println("Enter your city");
        String city = scanner.nextLine();
        System.out.println("Enter your district");
        String district = scanner.nextLine();
        System.out.println("Enter your state");
        String state = scanner.nextLine();
        System.out.println("Enter your country");
        String country = scanner.nextLine();
        return employeeController.createAddress(doorNo, streetName, city,
                                                district, state, country, addressMode);
    }

    /**
     * Method to display all employee details
     */
    private void getAllEmployee() {
        for(String employees : employeeController.getAllEmployee()) {
            System.out.println(employees + "\n");
        }
        getAllEmployeeAddress();
    }

    /**
     * Method to display all employee address details
     */
    private void getAllEmployeeAddress() {
        for(String address : employeeController.getAllEmployeeAddress()) {
            System.out.println(address + "\n");
        }
    }

    /**
     * Method to delete employee
     */
    private void deleteEmployee() {
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
     * Method to display particular employee based upon id
     * @param id employee id
     */
    private void getEmployee() {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            System.out.println("Employee");
            System.out.println(employeeController.getEmployee(empId));
            System.out.println("===========================");
            System.out.println("Employee Address");
            getAddress(empId);
        } else {
            System.out.println("Employee id doesn't exist");
          }
    }

    /**
     * Method to display particular employee address based upon id
     * @param id employee id
     */
    private void getAddress(int id) {
        System.out.println("Please select your address choice to display address");   
        int addressDisplayOption = 0;
        String addressDisplayChoice = "Enter 1 to home address" 
                                      + "\n Enter 2 to current address"
                                      + "\n Enter 3 to permanent address"
                                      + "\n Enter 4 to temporary address"
                                      + "\n Enter 5 to office address"
                                      + "\n Enter 6 to quit";
        while (6 != addressDisplayOption) {
            System.out.println(addressDisplayChoice);
            addressDisplayOption = scanner.nextInt();
                switch (addressDisplayOption) {
                    case 1 :
                        System.out.println(employeeController.getAddress(id, "homeaddress"));
                        break;
                    case 2 :
                        employeeController.getAddress(id, "currentnaddress");
                        break;
                    case 3 :
                        employeeController.getAddress(id, "permanentaddress");
                        break;
                    case 4 :
                        employeeController.getAddress(id, "temporaryaddress");
                        break;
                    case 5 :
                        employeeController.getAddress(id, "officeaddress");
                        break;
                    case 6 :
                        System.out.println("Thank you");
                        break;
                    default :
                        System.out.println("Invalid choice");
                }
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
                case 3 : 
                    deleteEmployee();
                    break;
                case 4 :
                    getAllEmployee();
                    break;
                case 5 :
                    getEmployee();
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