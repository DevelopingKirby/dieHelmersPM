package com.gavima_kanido.handler;

import java.sql.SQLException;

import com.gavima_kanido.utils.DatabaseOperationUtil;

public class CreateProjectHandler {

    public int createProject(String project_name_input, String customer_name_input, String project_description_input, int project_budget_input) {
        int row = 0;
        
        if (project_name_input.isEmpty() || customer_name_input.isEmpty() || project_description_input.isEmpty() || Integer.toString(project_budget_input).isEmpty() || !(Integer.toString(project_budget_input).matches("-?\\d+"))) {
            row = 2;
        } else {
            try {
                row = DatabaseOperationUtil.addProject(project_name_input, customer_name_input, project_description_input, project_budget_input);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        
        return row;
    }

    

}
