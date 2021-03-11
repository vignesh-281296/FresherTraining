package com.ideas2it.employee.model;

import java.sql.Date;

/** 
 * Employee of pojo class contain employee details.
 *
 * @author vignesh r.
 * version 1.0 01-03-2021.
 */
public class Employee {
    
    private int empId;
    private String name;
    private String desgination;
    private String email;
    private long phoneNumber;
    private long salary;
    private Date dob;

    public Employee(int empId, String name, String desgination, String email, long phoneNumber, long salary, Date dob) {
        this.empId = empId;
        this.name = name;
        this.desgination = desgination;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
    }

    public int getEmpID() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String toString() {
        return "Employee-Id - " + empId + "\n Name - " + name + "\n Desgination - " + desgination + "\n Email - "
                        + email + "\n Phone Number - " + phoneNumber 
                        + "\n Salary - " + salary + "\n DOB -" + dob; 
    }       

} 