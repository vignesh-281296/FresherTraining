import java.util.Collection;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Practice {
    
    static Map <Integer, Employee> userDetails = new HashMap<Integer, Employee>(); 
    public static void createEmployee(int empId, int employeeValue) {
        userDetails.put(empId, employeeValue);
    }
    public static void deleteEmployee(int id) {
        userDetails.remove(id);
    }
    public static void updateEmployee(int values, int id) {
        userDetails.userList.get(id).setUsername(values);
    } 
    public static void display() {
        for (Object i : userDetails) {
            System.out.println(i);
        }
    }
    
    public static void main(String args[]) {
        Scanner userInput = new Scanner(System.in);
        boolean exitFlag = true;
        int empId = 1;
        String choiceDetails = "Enter 1 to create employee \n Enter 2 to delete employee \n Enter 3 to display Employee"
                                + "\n Enter 4 to Update employee";
        do {
            System.out.print(choiceDetails);
            int choice = userInput.nextInt();
            if (1 == choice) {
                System.out.print("Enter employee value");
                int employeeValue = userInput.nextInt();
                createEmployee(employeeValue);
                empId++;
            } else if (2 == choice) {
                System.out.print("Enter Employee id");
                 int employeeId = userInput.nextInt();
                 if (userDetails.size() > employeeId) {
                     deleteEmployee(employeeId);
                 } else {
                     System.out.print("Employee id does not exist");
                   }
              } else if(4 == choice) {
                  System.out.print("Enter employee value");
                  int empId = userInput.nextInt();
                  System.out.print("Enter employee value");
                  int employeeValue = userInput.nextInt();
                  updateEmployee(empId, employeeValue);
                } else if (3 == choice) {
                    display();
                    exitFlag = false;    
                  }   
        } while (exitFlag);
    }
}