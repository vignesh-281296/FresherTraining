package com.ideas2it.employeemanagement.project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;
import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.sessionfactory.DatabaseConnection;

/**
 * It is used to store and retrives datas to database
 *
 * @author vignesh r
 * @version 1.0 24-03-2021
 */
public class ProjectDaoImpl implements ProjectDao {

     private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
   
    /**
     * {inheritDoc}
     */
    @Override
    public boolean insertProject(Project project) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("insert into project (name, manager_name, start_date," 
                    + "end_date) values (?, ?, ?, ?)"); 
            prepareStatement.setString(1, project. getName());
            prepareStatement.setString(2, project.getManagerName());
            prepareStatement.setDate(3, project.getStartDate());
            prepareStatement.setDate(4, project.getEndDate());
            int projectResult = prepareStatement.executeUpdate();
            if (1 == projectResult) {
                count = true;
            } else {
                count = false;
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }      
        return count;           
    } 

    /**
     * {inheritDoc}
     */
    @Override
    public boolean isProjectIdExist(int id) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select id from project Where id = ? and is_delete = true");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            count = resultSet.next();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return count;            
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Project getSpecificProject(int id) {
        Project project = null;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select * from project where id = ? and is_delete = true");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                project = new Project(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getDate(5));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return project;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Project> getAllProject() {
        List<Project> projects = new ArrayList<Project>();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select * from project where is_delete = true");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
               Project project = new Project(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getDate(5));
                projects.add(project);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return projects;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean deleteProject(int id) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update project set is_delete = false where id = ?");
            prepareStatement.setInt(1, id);
            int result = prepareStatement.executeUpdate();
            if (1 == result) {
                prepareStatement = connection.prepareStatement
                    ("Delete from project_employee where project_id = ?");
                prepareStatement.setInt(1, id);
                prepareStatement.executeUpdate();
                count = true;
            } else {
                count = false;
            }
            connection.close(); 
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return count;     
    }

    /**
     * {inheritDoc}
     */
    @Override
    public List<Project> getDeletedProject() {
        List<Project> projects = new ArrayList<Project>();
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                ("select * from project where is_delete = false");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Project project =  new Project(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getDate(5));
                projects.add(project);    
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }    
        return projects;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean checkDeletedProjectId(int id) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("select id from project Where id = ? and is_delete = false");
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            count = resultSet.next();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }  
        return count;            
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean restoreProject(int id) {
        int count = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update Project set is_delete = true where id = ?");
            prepareStatement.setInt(1, id);
            int result = prepareStatement.executeUpdate();
            if (1 == result) {
                count = 1;
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } 
        return 0 != count;    
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean updateProject(int id, Project project) {
        int count = 0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("update project set name = ?, manager_name = ?, "
                    + "start_date = ?, end_date = ?  where id = ?");
            prepareStatement.setString(1, project.getName());
            prepareStatement.setString(2, project.getManagerName());
            prepareStatement.setDate(3, project.getStartDate());
            prepareStatement.setDate(4, project.getEndDate());
            prepareStatement.setInt(5, id);
            count = prepareStatement.executeUpdate();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }   
        return 0 != count;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean assignProject(Project project) {
        boolean count = true;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement
                    ("insert ignore into project_employee (employee_id, project_id)" 
                    + " values (?, ?)");
            for (Employee employee : project.getEmployees()) { 
                prepareStatement.setInt(1, employee.getId());
                prepareStatement.setInt(2, project. getId());
                prepareStatement.addBatch();
            }
            int projectResult[] = prepareStatement.executeBatch();
            
            if (0 != projectResult.length) {
                count = true;
            } else {
                count = false;
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }      
        return count;           
    }

    /**
     * {inheritDoc}
     */
    @Override
    public Project getAssignedProject(int id) {
        Project project = null;
         try {
             Connection connection = databaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement
                     ("select p.id as project_id, p.name, p.manager_name, p.start_date, p.end_date, " 
                      + "e.id as employee_id, e.name, e.desgination, e.email,e.phone_number, e.salary, "
                      + "e.dob from  project p "
                      + "left join project_employee as pe on p.id = pe.project_id "
                      + "left join employee e on e.id = pe.employee_id where p.id =?");
             prepareStatement.setInt(1, id);
             ResultSet resultSet = prepareStatement.executeQuery();
             if (resultSet.next()) {
                 project = new Project(resultSet.getInt(1),
                         resultSet.getString(2), resultSet.getString(3),
                         resultSet.getDate(4), resultSet.getDate(5));
                 int projectId = resultSet.getInt(1);
                 List<Employee> employeeDetails = new ArrayList<Employee>();
                 while (projectId == resultSet.getInt(1)) {
                    if (0 != resultSet.getInt(6)) {   
                        Employee employee = new Employee(resultSet.getInt(6),
                                resultSet.getString(7), resultSet.getString(8),
                                resultSet.getString(9), resultSet.getLong(10),
                                resultSet.getFloat(11), resultSet.getDate(12));
                         employeeDetails.add(employee);
                    }
                     if (!resultSet.next()) {
                         break;
                     }
                 }
                 project.setEmployees(employeeDetails);
                 connection.close();
             }
         } catch(SQLException e) {
             e.printStackTrace();
        }
        return project; 
    } 
}