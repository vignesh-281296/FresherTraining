import java.util.Scanner;

import com.ideas2it.employeemanagement.employee.view.EmployeeView;
import com.ideas2it.employeemanagement.project.view.ProjectView;

/**
 * CRUD operation using collection
 *
 * @author vignesh r
 * @version 1.0 03-03-2021
 */
public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your type of address");
        int option = 0;
        String optionDetails = "Enter 1 to Employee Management" 
                + "\nEnter 2 to Project Management"
                + "\nEnter 3 to quit"; 
        while (3 != option) {
            System.out.println(optionDetails);
            option = scanner.nextInt();
            switch (option) {
                case 1 :
                    EmployeeView employeeView = new EmployeeView();
	            employeeView.operation();
                    break;
                case 2 :
                    ProjectView projectView = new ProjectView();
	            projectView.operation();
                    break;
                case 3 :
                    System.out.println("Thank you");
                    break;
                default :
                    System.out.println("Invalid choice");
            }
        }
	
    }
}