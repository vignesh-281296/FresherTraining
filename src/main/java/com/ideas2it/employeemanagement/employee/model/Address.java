package com.ideas2it.employeemanagement.employee.model;

import java.sql.Date;

import com.ideas2it.employeemanagement.employee.model.Employee;

/** 
 * Address for pojo.
 *
 * @author vignesh r.
 * version 1.0 10-03-2021.
 */
public class Address {
    
    private int id;
    private int employeeId;
    private String doorNo;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String country;
    private String addressMode;
    private boolean isDelete;
    private Employee employee;
    
    public Address() {
    }

    public Address(int id, String doorNo, String streetName,
                   String city, String district, String state, 
                   String country, String addressMode, boolean isDelete) {
        this.id = id;
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressMode = addressMode;
        this.isDelete = isDelete;
    }

    public Address(String doorNo, String streetName,
                   String city, String district, String state, 
                   String country, String addressMode, boolean isDelete) {
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressMode = addressMode;
        this.isDelete = isDelete;
    }

    /*public Address(String doorNo, String streetName,
                   String city, String district, String state, 
                   String country, String addressMode) {
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressMode = addressMode;
    }*/

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAddressMode() {
        return addressMode;
    }

    public void setAddressMode(String addressMode) {
        this.addressMode = addressMode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        return  doorNo  
                + "\n" + streetName 
                + "\n" + city 
                + "\n" + district 
                + "\n" + state   
                + "\n" + country
                + "\n" + addressMode;
    }
} 