package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    public Connection con = null;

    private String url = null;

    public ConnectionDB() {
        url = "jdbc:mysql://localhost:3306/apsdatabase?user=root&password=";
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

    public void consultar(){
        Statement s = null;
        Connection connection = getConnection();

        try {
                s = (Statement) connection.createStatement();
        } catch (SQLException e) {
                e.printStackTrace();
        }

        ResultSet r = null;
        try {
            r = s.executeQuery("Select * from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (r.next()){
                System.out.println(r.getString("userName") + "  " + r.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
