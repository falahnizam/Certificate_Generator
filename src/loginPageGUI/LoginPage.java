package loginPageGUI;
//SID :- 2228945
//NAME:- Falah Nizam


import UserLogin.Login;
import preANDcust.UserChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;


    public LoginPage() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initializeGUI();
            }
        });
    }

    private void initializeGUI() {
        setTitle("Login page");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // login method here
                boolean loggedIn = Login.login(username, password);

                if (loggedIn) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                    // Open the UserChoice page after successful login
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new UserChoice().setVisible(true);
                        }
                    });
                    dispose(); // Close the login page
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the create account window
                new CreateAcGUI();
            }
        });
        panel.add(createAccountButton);

        add(panel);
        setVisible(true);
    }
}



