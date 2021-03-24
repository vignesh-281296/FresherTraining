package com.ideas2it.employeemanagement.project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.project.dao.ProjectDao;
import com.ideas2it.employeemanagement.project.model.Project;
import com.ideas2it.employeemanagement.employee.sessionfactory.DatabaseConnection;

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
}