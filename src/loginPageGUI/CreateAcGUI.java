package loginPageGUI;

import UserLogin.CreateAc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAcGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public CreateAcGUI() {
        setTitle("Create Account");
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Dispose on close since it's a popup window
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

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get username and password entered by the user
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Call the method to create account with the provided username and password
                CreateAc.createAccount(username, password);

                // Display a message to the user indicating that the account has been created
                JOptionPane.showMessageDialog(CreateAcGUI.this, "ACCOUNT CREATED SUCCESSFULLY!");

                // Dispose the window after account creation
                dispose();
            }
        });
        panel.add(createAccountButton);

        add(panel);
        setVisible(true);
    }
}
