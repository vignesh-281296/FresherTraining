package com.ideas2it.employee.view;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import com.ideas2it.employee.model.Employee;

/**
 * contain method to create,delete,update,display.
 *
 * @author vignesh r.
 * version1.0 01-03-2021.
 */
public class EmployeeView {

    Map<Integer, Employee> employeeDetails = new HashMap<Integer, Employee>();    
    Scanner scanner = new Scanner(System.in);
    int empId = 1;

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
        String email = scanner.nextLine();
        System.out.println("Enter your phone number");
        long phoneNumber = validatePhoneNumber(scanner.nextLong());
        System.out.println("Enter your salary");
        long salary = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter Date Of Birth in given format dd/mm/yyyy");
        //String date = scanner.nextLine(); 
        Date dob = isValidateDate();
        Employee employee = new Employee(name, city, email, phoneNumber, salary, dob);
        if (null == employeeDetails.put(empId, employee)) {
            System.out.println("Create Successfully Employee - " + empId);
            empId++; 
        } else {
            System.out.println("Unsuccessful");
          }      
    }

    /**
     * display employee records
     */
    private void displayAllEmployee() { 
        
        employeeDetails.forEach((id, employee) -> System.out.println("Emp ID- "+ id +"\n" +employee));
    }

    /**
     * update employee record based on employee id
     */
    private void updateEmployee() {
        System.out.println("Enter the field to update\n 1. update Name\n 2. update City\n" +
                           "3. update Email\n 4. update Phone\n 5.update Salary\n Enter your choice");
        int option = scanner.nextInt();
        System.out.println("Enter your Employee id");
        int id = scanner.nextInt();
        Employee employee = employeeDetails.get(id);
        if (null != employee) {
            switch(option) {
                case 1 :
                    System.out.println("Enter your name");
                    scanner.nextLine();
                    String updateName = scanner.nextLine();
                    employee.setName(updateName);
                    System.out.println("Updated SuccessFully");
                    break;
                case 2 :
                    System.out.println("Enter your City");
                    scanner.nextLine();
                    String updateCity = scanner.nextLine();
                    employee.setCity(updateCity);
                    System.out.println("Updated SuccessFully");
                    break;
                case 3 :
                    System.out.println("Enter your Email");
                    scanner.nextLine();
                    String updateEmail = scanner.nextLine();
                    employee.setEmail(updateEmail);
                    System.out.println("Updated SuccessFully");
                    break;
                case 4 :
                    System.out.println("Enter your Phone Number");
                    long updatePhoneNumber = scanner.nextLong();
                    employee.setPhoneNumber(updatePhoneNumber);
                    System.out.println("Updated SuccessFully");
                    break;
                case 5 :
                    System.out.println("Enter your Salary");
                    long updateSalary = scanner.nextLong();
                    employee.setSalary(updateSalary);
                    System.out.println("Updated SuccessFully");
                    break;
                default :
                    System.out.println("Invalid Choice");
            }
            
        } else {
            System.out.println("Emmployee Id does not exist");
          }
         
    }

    /**
     * delete the employee records based upon emp id
     */
    private void deleteEmployee() {
        System.out.println("Enter your emp id");
        int id = scanner.nextInt();
        Employee employee = employeeDetails.get(id);
        if (null != employee) {
            if (null != employeeDetails.remove(id)) {
                System.out.println("Deleted Successfully");
            } else {
                System.out.println("Unsuccessful");
              }
        } else {
            System.out.println("Employee Id does not exist");
          }
    }

    /**
     * Method for Phone Number validataion 
     *
     * @param Phone Number
     */
     private long validatePhoneNumber(long phoneNumber) {
         if (Long.toString(phoneNumber).matches("[6-9][0-9]{9}")) {
             return phoneNumber;
         } else {
             System.out.print("Invalid Phone Number\n"
                                   + "Enter Valid Phone Number: ");
             return validatePhoneNumber(scanner.nextLong());
         }
     }

    

    /**
     * Method for date validation
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
     * Method for CRUD operation
     */  
    public void operation() {
        int choice = 0;
        String choiceDetails = "Enter 1 to create employee \n Enter 2 to Update employee \n Enter 3 to Delete employee" +
                                "\n Enter 4 to Display Employee \n Enter 5 to Exit";
        while (5 != choice) {
            System.out.println(choiceDetails);
            choice = scanner.nextInt();
            switch(choice) {
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
                    displayAllEmployee();
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