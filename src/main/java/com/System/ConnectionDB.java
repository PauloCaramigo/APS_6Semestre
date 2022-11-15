package com.System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public Connection con = null;

    private String url = null;

    public ConnectionDB() {
        url = "jdbc:mysql://localhost/apsdatabase?user=root&password=12345";
    }
        
    /*
        Retorna uma java.sql.Connection.
        @return con
    */
    public Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url);
            } else if (con.isClosed()) {
                con = null;
                return getConnection();
            }

        } catch (ClassNotFoundException e) {
            e.getMessage();
            e.printStackTrace();

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();

        }

        return con;
    }
}
