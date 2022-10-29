package Interface;

import java.sql.*;
import java.awt.*;
import javax.swing.*;

import System.ConnectionDB;

public class Graphics {
    ConnectionDB bd = new ConnectionDB();
    Connection connectionDB = bd.getConnection();
    
    Statement s = null;
    ResultSet r = null;

    public Graphics(Container g) {
        s = null;
        r = null;

        try {
            s = (Statement) connectionDB.createStatement();
            r = s.executeQuery("SELECT * FROM images WHERE permission <= 3");

            

            int num = 50;
            while(r.next()){
                JLabel image = new JLabel();
                
                int width = (int) (g.getWidth()*0.3);
                int height = (int) (g.getHeight()*0.4);

                image.setBounds(50, num, width, height);

                ImageIcon icon = new ImageIcon(r.getString("address"));
                icon.setImage(icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), 1));
                image.setIcon(icon);

                g.add(image);

                num += height + 50;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
