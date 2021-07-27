package com.gavima_kanido.models;

public class User {

    //TODO add personaldata
    
    private String userRef;
    private String firstName;
    private String name;
    private String street;
    private String city;
    private String country;
    private String superiorName;
    private String department;
    private String team;
    private int zip;
    private String phoneNumber;
    private String eMail;
 
   

    public User(String userRef, String firstName, String name, String street, String city, String country, String superiorName, String department, String team, int zip, String phoneNumber, String eMail) {
        this.userRef = userRef;
        this.firstName = firstName;
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.superiorName = superiorName;
        this.department = department;
        this.team = team;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

    

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public String getSuperiorName() {
        return this.superiorName;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getTeam() {
        return this.team;
    }

    public int getZip() {
        return this.zip;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEMail() {
        return this.eMail;
    }

    public String getUserRef () {
        return this.userRef;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

}
