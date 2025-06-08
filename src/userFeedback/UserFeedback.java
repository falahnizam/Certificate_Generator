package userFeedback;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import UserLogin.Login;
public class UserFeedback {
    public  static void saveFeedback(String username, String feedback) {
//        Get the current date and time
        LocalDateTime now = LocalDateTime.now();
//        Format date and time
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(dateTimeFormatter);
        // Construct the content to be saved in the file
        String content = "Username: " + username + "\n" +
                "Date and Time: " + formattedDateTime + "\n" +
                "Feedback: " + feedback + "\n\n";
        // File path where feedback will be saved
        String filePath = "feedback.txt";
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(content);
            System.out.println("Feedback saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving feedback: " + e.getMessage());
        }
    }
}
