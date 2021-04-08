package com.ideas2it.employeemanagement.project.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Employee;

/** 
 * project for pojo.
 *
 * @author vignesh r.
 * version 1.0 01-03-2021.
 */
public class Project {
    
    private int id;
    private String name;
    private String managerName;
    private Date startDate;
    private Date endDate;
    private boolean isDelete;
    private List<Employee> employees;

    public Project() {}

    public Project(int id, String name, String managerName, Date startDate, 
                    Date endDate, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
    }

     public Project(String name, String managerName, Date startDate, 
                    Date endDate, boolean isDelete, List<Employee> employees) {
        this.name = name;
        this.managerName = managerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDelete = isDelete;
        this.employees = employees;
    }

    public Project(int id, String name, String managerName, Date startDate, 
                    Date endDate, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDelete = isDelete;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String toString() {
        return "Project id -" + id + "\nProject Name - " + name
                + "\nProject Manager - " + managerName + "\nStart Date - "
                + startDate + "\nEnd Date - " + endDate;      
    }      
} 