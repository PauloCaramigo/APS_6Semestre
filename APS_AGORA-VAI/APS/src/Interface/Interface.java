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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interface extends JFrame {
    getInfo getInfo = new getInfo();
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Word documents", "doc");
        fileChooser.setFileFilter(filter);

        JButton btn = new JButton("Login");
        btn.setBounds(50, 150, 80, 30);

        // Settings of button
        pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(fileChooser);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    //String file = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompareImages get = new CompareImages();
                boolean confirmLogin = false;
                try {
                    BufferedImage imgSelected = ImageIO.read(fileChooser.getSelectedFile());

                    File file = new File("../Images/BiometryShiro.jpg");
                    System.out.println(file);
                    BufferedImage biometryUser = ImageIO.read(file);

                    confirmLogin = get.compareImage(imgSelected, biometryUser);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println(confirmLogin);
            }
        });
        
        // Add elements
        add(login);
        add(loginUser);

        add(passwd);
        add(pwd);

        add(btn);


        // Settings of window
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Tela de login");
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
