package System;

import java.sql.*;

public class getInfo {
    public static void main(String[] args) {
    }

    public boolean ConfirmLogin(String user, String pwd) {
        ConnectionDB bd = new ConnectionDB();
        Connection connectionDB = bd.getConnection();

        Statement s = null;
        ResultSet r = null;

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
}
