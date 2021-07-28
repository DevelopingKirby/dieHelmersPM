package com.gavima_kanido.models;

public class Project {
    private String projectName;
    private String customer;
    private String description;
    private int budget;
    private User[] member;



    public Project(String projectName, String customer, String description, int budget, User[] member) {
        this.projectName = projectName;
        this.customer = customer;
        this.description = description;
        this.budget = budget;
        this.member = member;
    }
   

}
