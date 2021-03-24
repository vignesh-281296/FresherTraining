package com.ideas2it.employeemanagement.employee.model;

import java.sql.Date;

/** 
 * Address of pojo class contain employee address details.
 *
 * @author vignesh r.
 * version 1.0 10-03-2021.
 */
public class Address {
    
    private int id;
    private int empId;
    private String doorNo;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String country;
    private String addressMode;

    public Address(int id, int empId, String doorNo, String streetName,
                   String city, String district, String state, 
                   String country, String addressMode) {
        this.id = id;
        this.empId = empId;
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressMode = addressMode;
    }

    public Address(int empId, String doorNo, String streetName,
                   String city, String district, String state, 
                   String country, String addressMode) {
        this.empId = empId;
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressMode = addressMode;
    }

    public Address(String doorNo, String streetName,
                   String city, String district, String state, 
                   String country, String addressMode) {
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.addressMode = addressMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public String getAddressMode() {
        return addressMode;
    }

    public void setAddressMode(String addressMode) {
        this.addressMode = addressMode;
    }

    public String toString() {
        return "\nEmployee-Id - " + empId + "\nDoor No - " + doorNo  
                + "\nstreet name - " + streetName + "\nCity - " + city 
                + "\nDistrict - " + district + "\nState - " + state   
                + "\nCountry -" + country + "\nAddress Mode - " + addressMode;
    }
} 