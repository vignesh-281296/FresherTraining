package com.ideas2it.employee.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.ideas2it.employee.controller.EmployeeController;

/**
 * It performs CRUD operations
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
    private void createEmployee() throws SQLException, ClassNotFoundException {
        scanner.skip("\r\n");
        System.out.println("Enter Your name");
        String name = scanner.nextLine();
        System.out.println("Enter your desgination");
        String desgination = scanner.nextLine();
        System.out.println("Enter your email");
        String email = validateEmail(scanner.next());
        System.out.println("Enter your phone number");f
        long phoneNumber = validatePhoneNumber(scanner.nextLong());
        System.out.println("Enter your salary");
        float salary = scanner.nextFloat();
        //scanner.nextLine();
        System.out.println("Enter Date Of Birth in given format yyyy-MM-dd");
        Date dob = Date.valueOf(validateDate());
        List<String[]> addressDetails = createAddress();
        boolean employee = employeeController.createEmployee(name, desgination, 
                email, phoneNumber, salary, dob, addressDetails);
        if (employee) {
            System.out.println("Employee created successfully");         
        } else {
            System.out.println("Unsuccessful");
        }            
    }

    /**
     * It is used to  create new address 
     */
    private List<String[]> createAddress() throws SQLException, ClassNotFoundException {
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
    private String[] getAddressDetails(String addressMode) 
            throws SQLException, ClassNotFoundException {
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
     * It is used to delete employee
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
     * It is used to display individual employee details
     */
    private void getSpecificEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
            System.out.println("==== Employee =====");
            System.out.println(employeeController.getSpecificEmployee(empId) + "\n");
        } else {
            System.out.println("Employee id doesn't exist");
        }
    } 

     /**
     * It is used to display all employee details
     */
    private void displayAllEmployee() throws SQLException, ClassNotFoundException {
        for (String employees : employeeController.getAllEmployee()) {
            System.out.println(employees + "\n \n");
        }   
    }

    /**
     * It performs update operation 
     */
    private void updateOperation() throws SQLException, ClassNotFoundException {
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
                case 4 :
                    deleteAddress();
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
     * It is used to update employee details
     */
    private void updateEmployeeDetails() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
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
     * It is used to get employee details to update specific employee
     */
    private String[] getEmployeeDetails() throws SQLException, ClassNotFoundException {
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
     * It is used to update employee address details
     */
    private void updateEmployeeAddress() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.getAddressDetails(empId).size() > 0) {
            if (employeeController.isEmpIdExist(empId)) {
                Map<Integer, String> addresses = employeeController.getAddressDetails(empId);
                addresses.forEach((addressId, address) -> {
                    System.out.println(address);
                });
                List<Integer> addressIds = new ArrayList<>(addresses.keySet());
                System.out.println("select your address no to update ?");
                int addressOption = scanner.nextInt();
                if (addressIds.size() >= addressOption) {
                    int addressId = addressIds.get(addressOption - 1);
                    String[] addressDetails = getAddressDetails(null);
                    if (employeeController.updateEmployeeAddress(addressId, addressDetails)) {
                        System.out.println("Update your address successfully");
                    } else {
                        System.out.println("Unsuccessful");
                    }
                } else {
                    System.out.println("You Entered invalid option");
                }
            } else {
                System.out.println("Employee id doesn't exist");
            }
        } else {
            System.out.println("No records exists");
        }	
    }

    /**
     * It is used to add employee address
     */
    private void addEmployeeAddress() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.isEmpIdExist(empId)) {
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
     * It is used to delete specific address
     */
    private void deleteAddress() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your employee id");
        int empId = scanner.nextInt();
        if (employeeController.getAddressDetails(empId).size() > 0) {
            if (employeeController.isEmpIdExist(empId)) {
                Map<Integer, String> addresses = employeeController.getAddressDetails(empId);
                addresses.forEach((addressId, address) -> {
                    System.out.println(address);
                }); 
                List<Integer> addressIds = new ArrayList<>(addresses.keySet());
                System.out.println("select your address no to delete ?");
                int addressOption = scanner.nextInt();
                if (addressIds.size() >= addressOption) {
                    int addressId = addressIds.get(addressOption - 1);
                    if (employeeController.deleteAddress(addressId)) {
                        System.out.println("Deleted your address successfully");
                    } else {
                        System.out.println("Unsuccessful");
                    }
                } else {
                    System.out.println("You Entered invalid option");
                }
            } else {
                System.out.println("Employee id doesn't exist");
            }
        } else {
            System.out.println("No records exists");
        }	
    }

    /**
     * It is used to validate phone number
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
     * It is used to  validate email id
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
     * It is used to validate date
     */
    private String validateDate() {
        String dob = scanner.next();
        if (!dob.matches("^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")) {
            System.out.println("Enter valid date format (yyyy-MM-dd)");
            return validateDate();
        }
        return dob;
    }

    /**
     * It is used to restore the deleted employee details
     */
    private void restoreDeletedEmployeeDetails() throws SQLException, ClassNotFoundException { 
        if (employeeController.getDeletedEmployee().size() > 0) {
            for (String employeeDetails : employeeController.getDeletedEmployee()) {
                System.out.println(employeeDetails + "\n");
            }
            System.out.println("Enter 1 to restore Employee Details \nEnter 2 to quit");
            int restoreChoice = scanner.nextInt();
            if (1 == restoreChoice) {
                System.out.println("Enter you Employee id");
                int empId = scanner.nextInt();
                if (employeeController.checkDeletedEmpId(empId)){
                    if (employeeController.restoreEmployee(empId)) {
                        System.out.println("Restore Successfully");
                    } else {
                        System.out.println("Unsuccessful");
                    }
                } else {
                    System.out.println("Employee id doesn't exists");
                }
            } else if(2 == restoreChoice) {
                System.out.println("Thank you");    
            }
        } else {
            System.out.println("No Employee to restore");
        }
    }    

    /**
     * It performs CRUD operation
     */  
    public void operation() throws SQLException, ClassNotFoundException {
        int choice = 0;
        String choiceDetails = "Enter 1 to create employee \nEnter 2 to Update employee \nEnter 3 to Delete employee"
                + "\nEnter 4 to Display Individual Employee \nEnter 5 to Display Employee"
                + "\nEnter 6 restore  \nEnter 7 to Exit \nEnter your choice";    
        while(7 != choice) {
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
                    restoreDeletedEmployeeDetails();
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