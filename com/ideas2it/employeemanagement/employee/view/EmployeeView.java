package com.ideas2it.employeemanagement.employee.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.ideas2it.employeemanagement.employee.controller.EmployeeController;

/**
 * employee view
 *
 * @author vignesh r
 * @version 1.0 13-03-2021
 */ 
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /**
     * It used to create new employee
     */
    private void createEmployee() {
        scanner.skip("\r\n");
        System.out.println("Enter Your name");
        String name = scanner.nextLine();
        System.out.println("Enter your desgination");
        String desgination = scanner.nextLine();
        System.out.println("Enter your email");
        String email = validateEmail(scanner.next());
        System.out.println("Enter your phone number");
        long phoneNumber = validatePhoneNumber(scanner.nextLong());
        System.out.println("Enter your salary");
        float salary = scanner.nextFloat();
        System.out.println("Enter Date Of Birth in given format yyyy-MM-dd");
        Date dob = Date.valueOf(validateDob());
        List<String[]> addressDetails = createAddress();
        boolean employee = employeeController.createEmployee(name, desgination, 
                email, phoneNumber, salary, dob , addressDetails);
        if (employee) {
            System.out.println("Employee created successfully");         
        } else {
            System.out.println("Unsuccessful");
        }            
    }

    /**
     * It is used to  create new address 
     */
    private List<String[]> createAddress() {
        System.out.println("Please select your type of address");
        int addressChoice = 0;
        String addressChoiceDetail = "Enter 1 to permanent address" 
                + "\nEnter 2 to temporary address"
                + "\nEnter 3 to quit";
        List<String[]> addressess = new ArrayList<String[]>();
        int flag = 0; 
        while (3 != addressChoice) {
            System.out.println(addressChoiceDetail);
            addressChoice = scanner.nextInt();
            switch (addressChoice) {
                case 1 :
                    if (0 == flag) {
                        addressess.add(getAddressDetails("permanentaddress"));
                        flag = 1;
                    } else {
                        System.out.println("Permanent address already entered");
                    }
                    break;
                case 2 :
                    addressess.add(getAddressDetails("temporaryaddress"));
                    break;
                case 3 :
                    if (flag == 1) {
                        System.out.println("Thank you");
                    } else {
                        System.out.println("Atleast you need to give permanent address");
                        addressess.add(getAddressDetails("permanentaddress"));
                    } 
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        }
        return addressess;
    }

    /**
     * It is used to get employee address details
     * @param addressMode type of employee address
     * @return employee address details
     */
    private String[] getAddressDetails(String addressMode) {
        String[] addressDetails = new String[7];
        scanner.skip("\r\n");
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
     * It is used to display individual employee details
     */
    private void displayIndividualEmployee() {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            System.out.println(employeeController.getSpecificEmployee(empId));
        } else {
            System.out.println("empid doesn't exist");
        }
    }

    /**
     * It is used display all employee details
     */
    private void displayAllEmployee() {
        if (employeeController.getAllEmployee().size() > 0) {
            for (String employees : employeeController.getAllEmployee()) {
                System.out.println(employees);
            }    
        } else {
            System.out.println("No records");
        }
    }

    /**
     * It is used to delete employee
     */
    private void deleteEmployee() {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            if (employeeController.deleteEmployee(empId)) {
                System.out.println("Delete successfully");
            } else {
                System.out.println("unsuccessfull");
            }
        } else {
            System.out.println("Employee id doesn't exists");
        }
    }

    /**
     * It is used to restore employee
     */
    private void restoreEmployee() {
        if (employeeController.getDeletedEmployeeDetails().size() > 0) {
            for (String employee : employeeController.getDeletedEmployeeDetails()) {
                System.out.println(employee + "\n");
            }
            System.out.println("Enter 1 to restore Employee Details \nEnter 2 to quit");
            int restoreChoice = scanner.nextInt();
            if (1 == restoreChoice) {
                System.out.println("Enter your employee id");
                int empId = scanner.nextInt();
                if (employeeController.isEmployeeDeleted(empId)) {
                    if (employeeController.restoreEmployee(empId)) {
                        System.out.println("Restore successfully");
                     } else {
                        System.out.println("Unsuccessful");
                     }
                } else {
                    System.out.println("Employee id doesn't exist");
                }      
            } else if (2 == restoreChoice) {
                System.out.println("Thank you");
            } 
        } else {
            System.out.println("No records");
        }
    }

    /**
     * It performs update operation 
     */
    private void updateOperation() {
        System.out.println("Please select your update choice");
        int updateChoice = 0;
        String updateChoiceDetail = "Enter 1 to update employee details" 
                + "\nEnter 2 to update address details"
                + "\nEnter 3 to add address"
                + "\nEnter 4 to delete address"
                + "\nEnter 5 to quit";
        while (5 != updateChoice) {
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
                    addEmployeeAddress();
                    break;
                case 5 :
                    System.out.println("Thank you");
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * It is used to get employee details to update specific employee
     */
    private String[] getEmployeeDetails() {
        String[] employeeDetails = new String[6];
        employeeDetails[0] = null;
        employeeDetails[1] = null;
        employeeDetails[2] = null;
        employeeDetails[3] = null;
        employeeDetails[4] = null;
        employeeDetails[5] = null;
        System.out.println("Please select your which employee details need to update?");
        int updateEmployeeDetailChoice = 0;
        String updateEmployeeChoiceDetail = "Enter 1 to update employee name" 
                + "\nEnter 2 to update desgination"
                + "\nEnter 3 to update email"
                + "\nEnter 4 to update phone number"
                + "\nEnter 5 to update salary"
                + "\nEnter 6 to update date of birth"
                + "\nEnter 7 to quit";
        while (7 != updateEmployeeDetailChoice) {
            System.out.println(updateEmployeeChoiceDetail);
            updateEmployeeDetailChoice = scanner.nextInt();
            switch (updateEmployeeDetailChoice) {
                case 1 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your name");
                    employeeDetails[0] = scanner.nextLine();
                    break;
                case 2 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your desgination");
                    employeeDetails[1] = scanner.nextLine();
                    break;
                case 3 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your Email");
                    employeeDetails[2] = scanner.nextLine();
                    break;
                case 4 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your phone number");
                    employeeDetails[3] = scanner.nextLine();
                    break;
                case 5 :
                    scanner.skip("\r\n");
                    System.out.println("Enter your salary");
                    employeeDetails[4] = scanner.nextLine();
                    break;
                case 6 :
                    scanner.skip("\r\n");
                    System.out.println("Enter valid date format (yyyy-MM-dd)");
                    employeeDetails[5] = scanner.nextLine();
                    break;
                case 7 :
                    System.out.println("Thank you");
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        } 
        return employeeDetails;   
    }

    /**
     * It is used to update employee details
     */
    private void updateEmployeeDetails() {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
           String[] employeeDetails = getEmployeeDetails();
           if (employeeController.updateEmployee(empId, employeeDetails)) {
               System.out.println("Updated successfully");
           } else {
               System.out.println("Unsuccessful");
           }
        } else {
            System.out.println("Employee id doesn't exist");
        }
    }

    /**
     * It is used to select type of address to update
     */
    private String addressType() {
        System.out.println("Enter 1 permanent address \nEnter 2 temporart address");
        int addressTypeChoice = scanner.nextInt();
        String addressType = null;
        if (1 == addressTypeChoice) {
            addressType = "permanentaddress";    
        } else if (2 == addressTypeChoice) {
            addressType = "temporaryaddress";
        }
        return addressType;
    }

    /**
     * It is used to update employee address details
     */
    private void updateEmployeeAddress() {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            if (employeeController.getEmployeeAddressDetails(empId).size() > 0) {
                Map<Integer, String> addresses = employeeController.getEmployeeAddressDetails(empId);
                addresses.forEach((addressId, address) -> {
                    System.out.println(address + "\n");
                });
                List<Integer> addressIds = new ArrayList<>(addresses.keySet());
                System.out.println("select your address no to update ?");
                int addressOption = scanner.nextInt();
                if (addressIds.size() >= addressOption && 0 != addressOption) {
                    int addressId = addressIds.get(addressOption - 1);
                    int addressIndex = addressOption - 1;
                    String addressType = addressType();
                    String[] addressDetail = getAddressDetails(addressType);
                    if (employeeController.updateEmployeeAddress(addressIndex, addressId, addressDetail, empId)) {
                        System.out.println("Update your address successfully");
                    } else {
                        System.out.println("Unsuccessful");
                    }
                } else {
                    System.out.println("You Entered invalid option");
                }
            } else {
                System.out.println("No records exists");
            }
        } else {
            System.out.println("Employee id doesn't exist");
        }	
    }

    /**
     * It is used to assign employee to project
     */
    private void assignEmployee() {
        System.out.println("Project details");
        for (String projectDetails : employeeController.getAllProjectDetails()) {
            System.out.println(projectDetails + "\n");
        }
        System.out.println("Enter your Employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            getAssignEmployeeDetails(empId);    
        } else {
            System.out.println("Employee Id doesn't exist");
        }
    }

    /**
     * It is used to get assign employee to project details
     */
    private void getAssignEmployeeDetails(int empId) {
        List<Integer> projectIds = new ArrayList<Integer>();
        String choiceDetails = "Enter 1 project id \nEnter 2 exist";
        int choice = 0;
        int flag = 0;
        while (2 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    System.out.println("Enter your project id");
                    int projectId = scanner.nextInt();
                    if (employeeController.isProjectExist(projectId)) {
                        flag = 1;
                        projectIds.add(projectId);
                    } else {
                        System.out.println("Project id doesn't exist");
                    } 
                    break;
                case 2 :
                    if (0 == flag) {
                        System.out.println("Project doesn't assigned");
                    } else {
                        System.out.println("Thank you");
                    }
                    break;
                default :
                        System.out.println("Invalid choice");
            }
        }
        
        if (1 == flag) {
            if (employeeController.assignEmployee(empId, projectIds)) {
                System.out.println("Assigned Successfully");
            } else {
                System.out.println("Unsuccessful");
            }
        } else {
            System.out.println("Unsuccessful");
        }  
            
    }

    /**
     * It is used to add employee address
     */
    private void addEmployeeAddress() {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            String[] addressDetails = getAddressDetails("temporaryaddress");
            if (employeeController.addEmployeeAddress(empId, addressDetails)) {
                System.out.println("Address Added successfully");
            } else {
                System.out.println("Unsuccessful");
            }
        } else {
            System.out.println("Employee id doesn't exist");
        }    
    }

    /**
     * It is used to display assigned employee details
     */
    private void displayAssignedEmployeeDetails() {
        System.out.println("Enter your Employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            for (String employees : employeeController.getAssignedEmployee(empId)) {
                System.out.println(employees + "\n");
            }
        } else {
            System.out.println("Employee id doesn't exist");
        }
    }

    /**
     * It is used to unassign employee
     */
    public void unAssignEmployee() {
        System.out.println("Enter your Employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmployeeExist(empId)) {
            System.out.println("Enter your project id");
            int projectId = scanner.nextInt();
            if (employeeController.isProjectExist(projectId)) {
                if (employeeController.unAssignEmployee(empId, projectId)) {
                    System.out.println("Unassigned successfully");
                } else {
                    System.out.println("Unsuccessful");
                }
            } else {
                System.out.println("project id doesn't exist");
            }
        } else {
            System.out.println("Employee id doesn't exist");
        }  
    }

    /**
     * It is used to validate phone number
     * @param phoneNumber employee phone number
     * @return phone number
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
     * It is used to  validate email id
     * @param emailId employee email-id
     * @return email
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
     * It is used to validate date of birth
     * @return date of birth
     */
    private String validateDob() {
        String dob = scanner.next();
         if (employeeController.validateDob(dob)) {
            return dob;
        } else {
            System.out.print("Invalid Date of birth\n"
                    + "Enter Valid Date of birth: ");
            return validateDob();
        }
    }

    /**
     * It performs CRUD operation
     */  
    public void operation() {
        int choice = 0;
        String choiceDetails = "Enter 1 to create employee \nEnter 2 to Update employee \nEnter 3 to Delete employee"
                + "\nEnter 4 to Display Individual Employee \nEnter 5 to Display All Employee"
                + "\nEnter 6 to restore  \nEnter 7 to assign employee to project" 
                + "\nEnter 8 to display assigned employee project \nEnter 9 to unassign employee"  
                + "\nEnter 10 to Exit \nEnter your choice";    
        while(10 != choice) {
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
                    displayIndividualEmployee();
                    break;
                case 5 :
                    displayAllEmployee(); 
                    break;
                case 6 : 
                    restoreEmployee();
                    break;
                case 7 : 
                    assignEmployee();
                    break;
                case 8 : 
                    displayAssignedEmployeeDetails();
                    break;
                case 9 : 
                    unAssignEmployee();
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