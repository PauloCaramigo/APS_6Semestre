package Interface;

import java.sql.*;

import javax.swing.JPanel;

import com.mysql.cj.xdevapi.Statement;

import System.ConnectionDB;

public class Graphics extends JPanel {
    ConnectionDB bd = new ConnectionDB();
    Connection connectionDB = bd.getConnection();
    
    Statement s = null;
    ResultSet r = null;

    public Graphics() {
        try {
            s = (Statement) connectionDB.createStatement();
            r = s.executeQuery(// SQL COMMAND);

            while(r.next()){
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
