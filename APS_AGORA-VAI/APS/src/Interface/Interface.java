package Interface;

import System.*;

import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interface extends JFrame {
    getInfo getInfo = new getInfo();
    CompareImages get = new CompareImages();
    public static void main(String[] args) {
        Interface window = new Interface();
    }

    public Interface() {
        Container window = getContentPane();
        setLayout(null);

        // Set elements
        JLabel login = new JLabel("Login");
        login.setBounds(10, 50, 300, 30);

        JTextField loginUser = new JTextField(50);
        loginUser.setBounds(50, 50, 300, 30);

        JLabel passwd = new JLabel("Senha");
        passwd.setBounds(10, 100, 300, 30);

        JButton pwd = new JButton("Anexar");
        pwd.setBounds(50, 100, 300, 30);

        
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fotos", "jpg", "jpeg","png");
        fileChooser.setFileFilter(filter);

        JLabel imageName = new JLabel();
        imageName.setBounds(50, 130, 300, 30);

        JButton btn = new JButton("Login");
        btn.setBounds(50, 180, 80, 30);

        // Settings of button
        pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(fileChooser);
                System.out.println(result);
                if (result == JFileChooser.APPROVE_OPTION) {
                    imageName.setText(fileChooser.getSelectedFile().getName());

                }
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean confirmLogin = false;
                try {
                    // Declare images
                    BufferedImage imgSelected = ImageIO.read(fileChooser.getSelectedFile());
                    BufferedImage biometryUser = ImageIO.read(new File(getInfo.getBiometry(loginUser.getText())));

                    // Compare Images to login  
                    confirmLogin = get.compareImage(imgSelected, biometryUser);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }

                if (confirmLogin) {
                    window.remove(login);
                    window.remove(loginUser);
                    window.remove(passwd);
                    window.remove(pwd);
                    window.remove(imageName);
                    window.remove(btn);

                    window.repaint();
                }
            }
        });
        
        // Add elements
        window.add(login);
        window.add(loginUser);

        window.add(passwd);
        window.add(pwd);
        window.add(imageName);

        window.add(btn);


        // Settings of window
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Tela de login");
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
