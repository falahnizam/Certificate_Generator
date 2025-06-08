package UserLogin;

import java.sql.*;

public class CreateAc {

    private static final String URL = "jdbc:mysql://localhost:3306/userlogin";
    private static final String USER = "myuser";
    private static final String PASSWORD = "xxxx";

    // Method to create Account
    public static void createAccount(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO login_id (username, password) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                int rows = preparedStatement.executeUpdate();

                // Checking whether the account creation was successful or not
                if (rows > 0) {
                    System.out.println("User account created successfully");
                } else {
                    System.out.println("Unsuccessful");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
