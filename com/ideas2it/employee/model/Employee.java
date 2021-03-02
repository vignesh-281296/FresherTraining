package com.ideas2it.employee.model;

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

    public Employee(String name, String city, String email, long phoneNumber, long salary) {
        this.name = name;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
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

    public String toString() {
        return name + "\n" + city + "\n" + email + "\n" + phoneNumber + "\n" + salary; 
    }       

} 