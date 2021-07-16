package com.gavima_kanido.handler;

import java.sql.*;

import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;
import com.gavima_kanido.utils.HashUtil;

public class LoginHandler {

    public static User logIn(String txtUsername, String txtPassword) {
        String email = txtUsername;
        char[] password = txtPassword.toCharArray();
        String userRef = null;
        String pwdToken = null;
        User user = null;

        HashUtil hashUtil = new HashUtil();

        try {
            System.out.println(hashUtil.hash(password));
            pwdToken = DatabaseOperationUtil.getPasswordToken(email);
            
            if ( pwdToken != null && hashUtil.authenticate(password, pwdToken)) {
                
                
                userRef = DatabaseOperationUtil.getUserRef(email, hashUtil.hash(password));

                System.out.println("Wir sind sehr sehr geile typen!");

                int privileges = DatabaseOperationUtil.getPrivileges(userRef);
                // user = DatabaseOperationUtil.getPerson(userRef, privileges);
                user = new User(); // zum Testen, MF
                

            } else {
                System.out.println("Wir wurden nicht authenticated aber irgendwie hat die Datenbankabfrage geklappt lol");
                return null;
            }
        } catch (SQLException e) {
            
        }
        return user;
    }
}