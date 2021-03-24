package com.ideas2it.employeemanagement.employee.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.employee.model.Address;

/** 
 * Employee of pojo class contain employee details.
 *
 * @author vignesh r.
 * version 1.0 01-03-2021.
 */
public class Employee {
    
    private int id;
    private String name;
    private String desgination;
    private String email;
    private long phoneNumber;
    private float salary;
    private Date dob;
    private List<Address> address = new ArrayList<Address>();

    public Employee(int id, String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob) {
        this.id = id;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
    }

    public Employee(int id, String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob, List<Address> address) {
        this.id = id;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.address = address;
    }

    public Employee(String name, String desgination, String email, 
                    long phoneNumber, float salary, Date dob, List<Address> address) {
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
        this.address = address;
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

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String toString() {
        return "Employee-Id - " + id + "\nName - " + name
                + "\nDesgination - " + desgination + "\nEmail - "
                + email + "\nPhone Number - " + phoneNumber 
                + "\nSalary - " + salary + "\nDOB -" + dob;      
    }      
} 