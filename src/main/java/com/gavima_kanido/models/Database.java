package com.gavima_kanido.models;

import java.sql.Connection;


import com.gavima_kanido.utils.ConnectionUtil;

public class Database {

    private Connection dbcon; 

    public Database() {
        this.dbcon = ConnectionUtil.getConnection();
    }


    public Connection getDB() {
        return this.dbcon;
    }
 

}

