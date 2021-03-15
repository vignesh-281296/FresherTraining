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
     * Method is used to create new employee
     */
    private void createEmployee() throws SQLException, ClassNotFoundException {
        scanner.nextLine();
        System.out.println("Enter Your name");
        String name = scanner.nextLine();
        System.out.println("Enter your desgination");
        String desgination = scanner.nextLine();
        System.out.println("Enter your email");
        String email = validateEmail(scanner.next());
        System.out.println("Enter your phone number");
        long phoneNumber = validatePhoneNumber(scanner.nextLong());
        System.out.println("Enter your salary");
        long salary = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter Date Of Birth in given format yyyy-MM-dd");
        String date = scanner.next(); 
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
     * Method used to create new address 
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
                    addressDetails = getAddressDetails("homeaddress");
                    break;
                case 2 :
                    addressDetails = getAddressDetails("currentnaddress");
                    break;
                case 3 :
                    addressDetails = getAddressDetails("permanentaddress");
                    break;
                case 4 :
                    addressDetails = getAddressDetails("temporaryaddress");
                    break;
                case 5 :
                    addressDetails = getAddressDetails("officeaddress");
                    break;
                case 6 :
                    System.out.println("Thank you");
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        }
        return addressDetails;
    }

    /**
     * Method is used to get employee address details
     * @param addressMode type of employee address
     * @return employee address details
     */
    private String[] getAddressDetails(String addressMode) throws SQLException, ClassNotFoundException {
        String[] addressDetails = new String[7];
        scanner.nextLine();
        System.out.println("Enter your door no");
        addressDetails[0] = scanner.nextLine();
        System.out.println("Enter your street name");
        addressDetails[1] = scanner.nextLine();
        System.out.println("Enter your city");
        addressDetails[2] = scanner.nextLine();
        System.out.println("Enter your district");
        addressDetails[3] = scanner.nextLine();
        System.out.println("Enter your state");
        addressDetails[4] = scanner.nextLine();
        System.out.println("Enter your country");
        addressDetails[5] = scanner.nextLine();
        addressDetails[6] = addressMode;
        return addressDetails;
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
     * Method to update employee details 
     */
    private void updateOperation() throws SQLException, ClassNotFoundException {
        System.out.println("Please select your type of address");
        int updateChoice = 0;
        String updateChoiceDetail = "Enter 1 to update employee details" 
                                     + "\n Enter 2 to update address details"
                                     + "\n Enter 3 to quit";
        while (3 != updateChoice) {
            System.out.println(updateChoiceDetail);
            updateChoice = scanner.nextInt();
            switch (updateChoice) {
                case 1 :
                    updateEmployeeDetails();
                    break;
                case 2 :
                    updateEmployeeAddress();
                    break;
                case 3 :
                    System.out.println("Thank you");
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Method to update employee details
     */
    private void updateEmployeeDetails() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            scanner.nextLine();
            System.out.println("Enter your name");
            String name = scanner.nextLine();
            System.out.println("Entr your desgination");
            String desgination = scanner.nextLine();
            System.out.println("Enter your email id");
            String email = scanner.nextLine();
            System.out.println("Enter your phone number");
            long phoneNumber = scanner.nextLong();
            System.out.println("Enter your salary");
            long salary = scanner.nextLong();
            System.out.println("Enter Date Of Birth in given format yyyy-MM-dd");
            String date = scanner.next(); 
            Date dob = Date.valueOf(date);
            boolean updateEmployeeResult = employeeController.updateEmployee(name, desgination, email, phoneNumber, 
                                                               salary, dob, empId);  
            if (updateEmployeeResult) {
                System.out.println("Updated Successfully");
            } else {
                System.out.println("Unsuccessful");
              }
        } else {
            System.out.println("Employee id doesn't exist");
          }
    }

    /**
     * Method to update employee address operation
     */
    private void updateEmployeeAddress() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            System.out.println("Please select your type of address");   
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
                        if (employeeController.isExistAddressType(empId, "homeaddress")) {
                            String[] addressDetails = getAddressDetails("homeaddress");
                            if (employeeController.updateEmployeeAddress(empId, addressDetails)) {
                                System.out.println("Home address updated successfully");
                            } else {
                                System.out.println("unsuccessful");
                              }
                        } else {
                            System.out.println("Address type does not exist");
                          }
                        break;
                    case 2 :
                        if (employeeController.isExistAddressType(empId, "currentnaddress")) {
                            String[] addressDetails = getAddressDetails("currentnaddress'");
                            if (employeeController.updateEmployeeAddress(empId, addressDetails)) {
                                System.out.println("Current address updated successfully");
                            } else {
                                System.out.println("unsuccessful");
                              }
                        } else {
                            System.out.println("Address type does not exist");
                          }
                        break;
                    case 3 :
                        if (employeeController.isExistAddressType(empId, "permanentaddress")) {
                            String[] addressDetails = getAddressDetails("permanentaddress");
                            if (employeeController.updateEmployeeAddress(empId, addressDetails)) {
                                System.out.println("Permanent address updated successfully");
                            } else {
                                System.out.println("unsuccessful");
                              }
                        } else {
                            System.out.println("Address type does not exist");
                          }
                        break;
                    case 4 :
                        if (employeeController.isExistAddressType(empId, "temporaryaddress")) {
                            String[] addressDetails = getAddressDetails("temporaryaddress");
                            if (employeeController.updateEmployeeAddress(empId, addressDetails)) {
                                System.out.println("Temporary address updated successfully");
                            } else {
                                System.out.println("unsuccessful");
                              }
                        } else {
                            System.out.println("Address type does not exist");
                          }
                        break;
                    case 5 :
                        if (employeeController.isExistAddressType(empId, "officeaddress")) {
                            String[] addressDetails = getAddressDetails("officeaddress");
                            if (employeeController.updateEmployeeAddress(empId, addressDetails)) {
                                System.out.println("Office address updated successfully");
                            } else {
                                System.out.println("unsuccessful");
                              }
                        } else {
                            System.out.println("Address type does not exist");
                          }
                        break;
                    case 6 :
                        System.out.println("Thank you");
                        break;
                    default :
                        System.out.println("Invalid choice");
                }
            }    
        } else {
            System.out.println("employee does not exist");
          }
    }

    /**
     * Method to validate phone number
     * @param phoneNumber employee phone number
     */
    private long validatePhoneNumber(long phoneNumber) throws SQLException, ClassNotFoundException {
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
     * @param emailId employee email-id
     */ 
    private String validateEmail(String emailId) throws SQLException, ClassNotFoundException {
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
                case 2 : 
                    updateOperation();
                    break;
                case 3 : 
                    deleteEmployee();
                    break;
                case 4 :
                    getSpecificEmployee(); 
                    break;
                case 5 : 
                    displayAllEmployee();
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