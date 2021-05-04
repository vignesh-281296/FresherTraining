package com.ideas2it.employeemanagement.employee.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ideas2it.employeemanagement.employee.model.Address;
import com.ideas2it.employeemanagement.project.model.Project;

/** 
 * Employee for pojo.
 *
 * @author vignesh r.
 * version 1.0 01-03-2021.
 */
@Component
public class Employee {
    
    private int id;
    private String name;
    private String desgination;
    private String email;
    private long phoneNumber;
    private float salary;
    private Date dob;
    private boolean isDelete;
    private List<Address> addressess;
    private List<Project> projects;

    public Employee(){
    }

     public Employee(int id, String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob) {
        this.id = id;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.isDelete = isDelete;
    }

    public Employee(String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob, boolean isDelete) {
        //this.id = id;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.isDelete = isDelete;
    }

    /*public Employee(int id, String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob, List<Address> addressess) {
        //this.id = id;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.addressess = addressess;
    }*/

    public Employee(String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob, boolean isDelete, List<Address> addressess) {
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.isDelete = isDelete;
        this.addressess = addressess;
    }

    /*public Employee(int id, String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob, List<Address> address, 
                    List<Project> project) {
        this.id = id;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.project = project;
    }*/

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

    public String getDesgination() {
        return desgination;
    }

    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public List<Address> getAddressess() {
        return addressess;
    }

    public void setAddressess(List<Address> address) {
        this.addressess = address;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String toString() {
        return "Employee-Id - " + id + "\nName - " + name
                + "\nDesgination - " + desgination + "\nEmail - "
                + email + "\nPhone Number - " + phoneNumber 
                + "\nSalary - " + salary + "\nDOB -" + dob;      
    }

	public void show() {
		// TODO Auto-generated method stub
		System.out.println("hi");	
	}
  
} 