package com.ideas2it.employee.model;

import java.text.SimpleDateFormat;
import java.util.Date;
/** 
 * Employee of pojo class contain employee details.
 *
 * @author vignesh r.
 * version1.0 01-03-2021.
 */
public class Employee {

    private String name;
    private String city;
    private String email;
    private long phoneNumber;
    private long salary;
    private Date dob;

    public Employee(String name, String city, String email, long phoneNumber, long salary, Date dob) {
        this.name = name;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        return "Name - " + name + "\n" + "City - " + city + "\n" + "Email - "
                              + email + "\n" + "Phone Number - " + phoneNumber 
                              + "\n" + "Salary - " + salary + "\n" + "DOB -" 
                              + new SimpleDateFormat("dd/MM/yyy").format(dob); 
    }       

} 