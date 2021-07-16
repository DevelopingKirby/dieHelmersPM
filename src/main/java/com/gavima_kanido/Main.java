package com.gavima_kanido;

import java.sql.Connection;
import java.sql.SQLException;


import com.gavima_kanido.utils.ConnectionUtil;

public class Main {

    public static Connection db = ConnectionUtil.getConnection();


    public static void main(String[] args)  {       
        if (db == null) {

            System.err.println("\u001B[31mNo Databaseconnection could be established. The Application was terminated.");
            System.exit(0);


        } else {
            try {
                db.close();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
            JFXLauncher.main(args);
        }
    }
    
}
