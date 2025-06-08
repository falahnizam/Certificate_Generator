package UserLogin;

import java.sql.*;

public class Login {
    private static final String URL = "jdbc:mysql://localhost:3306/userlogin";
    private static final String USER = "myuser";
    private static final String PASSWORD = "xxxx";

    public static boolean login(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // SQL query to check login
            String query = "SELECT * FROM login_id WHERE username = ? AND password = ?";
            // Executing the query
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Return true if result set has at least one row
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}