package System;

import java.sql.*;

public class getInfo {
    ConnectionDB bd = new ConnectionDB();
    Connection connectionDB = bd.getConnection();
    
    Statement s = null;
    ResultSet r = null;

    public boolean ConfirmLogin(String user, String pwd) {
        s = null;
        r = null;

        try {
                s = (Statement) connectionDB.createStatement();
                r = s.executeQuery("SELECT password FROM users WHERE name = '" + user + "'");

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
                System.out.println(r.getString("biometry"));
                return r.getString("biometry");
            }
            return "Ocorreu um erro ao puxar os dados";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Ocorreu um erro ao puxar os dados";
        }
    }
}
