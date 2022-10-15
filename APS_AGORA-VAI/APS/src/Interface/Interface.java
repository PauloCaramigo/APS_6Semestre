package Interface;

import System.*;
import System.getInfo;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Interface extends JFrame {
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

        JPasswordField pwd = new JPasswordField(17);
        pwd.setBounds(50, 100, 300, 30);

        JButton btn = new JButton("Login");
        btn.setBounds(50, 150, 80, 30);

        // Settings of button
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getInfo get = new getInfo();
                boolean confirmLogin = get.ConfirmLogin(loginUser.getText() , new String(pwd.getPassword()));

                if (confirmLogin) {
                    JOptionPane.showMessageDialog(null, "Login feito com exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Login mal sucessido, usuário ou senha incorretos.");
                }
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
