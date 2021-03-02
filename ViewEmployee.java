import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.ideas2it.employee.model.Employee;

/**
 * contain method to create,delete,update,display.
 *
 * @author vignesh r.
 * version1.0 01-03-2021.
 */
public class ViewEmployee {

    static Map<Integer, Employee> employeeDetails = new HashMap<Integer, Employee>();    
    static Scanner userInput = new Scanner(System.in);
    static int empId = 1;

    /**
     * create employee record
     */
    public static void createEmployee() {
        System.out.println("Enter Your name");
        userInput.nextLine();
        String name = userInput.nextLine();
        System.out.println("Enter your city");
        String city = userInput.nextLine();
        System.out.println("Enter your email");
        String email = userInput.nextLine();
        System.out.println("Enter your phone number");
        long phoneNumber = userInput.nextLong();
        System.out.println("Enter your salary");
        long salary = userInput.nextLong();
        Employee employee = new Employee(name, city, email, phoneNumber, salary);
        if (null == employeeDetails.put(empId, employee)) {
            System.out.println("Create Successfully");
            empId++; 
        } else {
            System.out.println("Unsuccessful");
          }      
    }

    /**
     * display employee records
     */
    public static void displayEmployee() { 
        Set<Integer> id = employeeDetails.keySet();  
        for (int idKeys : id) {
            System.out.println(employeeDetails.get(idKeys));
        }
    }

    /**
     * update employee record based on employee id
     */
    public static void updateEmployee() {
        System.out.println("Enter the field to update\n 1. update Name\n 2. update City\n" +
                           "3. update Email\n 4. update Phone\n 5.update Salary\n Enter your choice");
        int updateChoice = userInput.nextInt();
        System.out.println("Enter your Employee id");
        int id = userInput.nextInt();
        boolean isUpdated = false;
        Employee employee = employeeDetails.get(id);
        if (null != employee) {
            switch(updateChoice) {
                case 1 :
                    System.out.println("Enter your name");
                    userInput.nextLine();
                    String updateName = userInput.nextLine();
                    employee.setName(updateName);
                    isUpdated = true;
                    break;
                case 2 :
                    System.out.println("Enter your City");
                    userInput.nextLine();
                    String updateCity = userInput.nextLine();
                    employee.setCity(updateCity);
                    isUpdated = true;
                    break;
                case 3 :
                    System.out.println("Enter your Email");
                    userInput.nextLine();
                    String updateEmail = userInput.nextLine();
                    employee.setEmail(updateEmail);
                    isUpdated = true;
                    break;
                case 4 :
                    System.out.println("Enter your Phone Number");
                    long updatePhoneNumber = userInput.nextLong();
                    employee.setPhoneNumber(updatePhoneNumber);
                    isUpdated = true;
                    break;
                case 5 :
                    System.out.println("Enter your Salary");
                    long updateSalary = userInput.nextLong();
                    employee.setSalary(updateSalary);
                    isUpdated = true;
                    break;
                default :
                    System.out.println("Invalid Choice");
            }
            
        } else {
            System.out.println("Emmployee Id does not exist");
          }
          if (isUpdated) {
              System.out.println("Updated SuccessFully");
          } else {
              System.out.println("Update Unsuccessful");
            }
    }

    /**
     * delete the employee records based upon emp id
     */
    public static void deleteEmployee() {
        System.out.println("Enter your emp id");
        int id = userInput.nextInt();
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
  
 

    public static void main(String[] args) {
        boolean exitFlag = true;
        String choiceDetails = "Enter 1 to create employee \n Enter 2 to Update employee \n Enter 3 to Delete employee" +
                                "\n Enter 4 to Display Employee";
        do {
            System.out.println(choiceDetails);
            int choice = userInput.nextInt();
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
                displayEmployee();
                exitFlag = false;
                break;
                default :
                System.out.println("Invalid Choice"); 
            }
        } while (exitFlag);
    }
}