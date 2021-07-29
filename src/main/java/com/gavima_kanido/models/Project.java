package com.gavima_kanido.models;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String projectId;
    private String projectName;
    private String customer;
    private String description;
    private int budget;
    private List<User> member = new ArrayList<User>();



    public Project(String projectId, String projectName, String customer, String description, int budget, List<User> member) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.customer = customer;
        this.description = description;
        this.budget = budget;
        this.member = member;
    }

    public String getName() {
        return this.projectName.substring(0, 1).toUpperCase() + this.projectName.substring(1);
    }

    public List<User> getMember() {
        return this.member;
    }

    public String getId() {
        return this.projectId;
    }
   

}
