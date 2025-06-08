package preANDcust;

import Certificate.CustomTemp;
import Certificate.PreBuiltTemp;
import loginPageGUI.LoginPage;

import javax.swing.*;
import java.awt.*;


public class UserChoice extends JFrame {

    public UserChoice() {
//        interaction occurs on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        setTitle("Template Page");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

//        Button to se prebuilt temp
        JButton prebuiltButton = new JButton("View Prebuilt Templates");
        prebuiltButton.addActionListener(e -> {
            dispose();
            // Handle viewing prebuilt templates
            PreBuiltTemp.prebuiltInput();

        });
        panel.add(prebuiltButton);
//      button to see custom temp
        JButton customButton = new JButton("View Custom Templates");
        customButton.addActionListener(e -> {
//            close the current window
                dispose();
                // Handle viewing custom templates
                CustomTemp.CustomTempInput();
        });
        panel.add(customButton);

        JButton backButton = new JButton("Log out ");
        backButton.addActionListener(e -> {
                // Open the login page
                new LoginPage();
                dispose(); // Close the template page
        });
        panel.add(backButton);

//        add the panel to jframe
        add(panel);
//        Make the Jframe visible
        setVisible(true);
    }
}
