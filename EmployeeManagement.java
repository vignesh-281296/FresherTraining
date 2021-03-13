import java.sql.SQLException;

import com.ideas2it.employee.view.EmployeeView;

/**
 * CRUD operation using collection
 * @author vignesh r
 * @version 1.0 03-03-2021
 */
public class EmployeeManagement {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	EmployeeView employeeView = new EmployeeView();
	employeeView.operation();
    }
}