package com.System;

import java.sql.*;

public class RunQuery {
    ConnectionDB bd = new ConnectionDB();
    Connection connectionDB = bd.getConnection();
    
    Statement s = null;
    ResultSet r = null;

    public boolean ConfirmLogin(String user, String pwd) {
        s = null;
        r = null;

        try {
                s = (Statement) connectionDB.createStatement();
                r = s.executeQuery("SELECT password FROM users WHERE userName = '" + user + "'");

                while(r.next()){
                    if (pwd.equals(r.getString("password"))){ 
                        return true;
                    } else {
                        return false;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getBiometry(String user) {
        s = null;
        r = null;

        try {
            s = (Statement) connectionDB.createStatement();
            r = s.executeQuery("SELECT biometry FROM users WHERE userName = '" + user + "'");
            while(r.next()){
                return r.getString("biometry");
            }
            return "Ocorreu um erro ao puxar os dados";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Ocorreu um erro ao puxar os dados";
        }
    }

    public Boolean verifyInfo(String info, String field){
        s = null;
        r = null;

        try {
            s = (Statement) connectionDB.createStatement();
            r = s.executeQuery("SELECT " + field + " FROM users WHERE " + field + " = '" + info + "'");
            while(r.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getInfo(String userName, String field, String table){
        s = null;
        r = null;

        try {
            s = (Statement) connectionDB.createStatement();
            if (table.equals("users")) r = s.executeQuery("SELECT " + field + " FROM " + table + " WHERE userName = '" + userName + "'");
            else r = s.executeQuery("SELECT " + field + " FROM " + table + " WHERE estado = '" + userName + "'");
            while(r.next()){
                return r.getString(field);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Ocorreu um erro ao puxar os dados";
        }
        return "Ocorreu um erro ao puxar os dados";
    }

    public Boolean updateInfo(String userName, String field, String info) {
        s = null;

        try {
            s = (Statement) connectionDB.createStatement();
            s.execute("UPDATE users SET " + field + " = '" + info + "' WHERE userName = '" + userName +"'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertNewUser(String userName, String password, String email, String biometry, String state) {
        s = null;

        try {
            s = (Statement) connectionDB.createStatement();
            s.execute("INSERT INTO users(userName, password, email, biometry, permission, state)  VALUES ('"+ userName + "', '" + password +"', '"+ email +
            "', '"  + biometry +"', 1,'" + state + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean updatePermission(String userName, int permission) {
        s = null;

        try {
            s = (Statement) connectionDB.createStatement();
            s.execute("UPDATE users SET permission = " + permission + " WHERE userName = '" + userName +"'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getAll(String field, String table){
        s = null;
        r = null;
        String info = "", row = "";

        try {
            s = (Statement) connectionDB.createStatement();
            r = s.executeQuery("SELECT " + field + " FROM " + table);
            while(r.next()){
                if (field.equals("*")){
                    row = "";
                    for (int i=1; i < 8; i++) {
                        row += r.getString(i) + ";";  
                    }
                    info += row + "--";
                }
                else{
                    info = info + r.getString(field) + ";";
                }
            }
            return info;

        } catch (SQLException e) {
            e.printStackTrace();
            return "Ocorreu um erro ao puxar os dados";
        }
    }
}
