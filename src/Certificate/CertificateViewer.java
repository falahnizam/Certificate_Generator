package Certificate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.*;

public class CertificateViewer {
    public static void displayCertificate(BufferedImage certificateImage) {
        // Display the customized certificate to the user using JFrame
        if (certificateImage != null) {
//            SwingUtilities.invokeLater(() ->{
                //            Created frame
                JFrame frame = new JFrame();
                try {
//                Set the title of window
                    frame.setTitle("Pre-Built Template");
//                frame size
                    frame.setSize(1000, 1200);
//                default close operation
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JLabel label = new JLabel(new ImageIcon(certificateImage));
                    frame.getContentPane().add(label, BorderLayout.CENTER);
                    frame.setLocationRelativeTo(null); // Center the frame on the screen
                    frame.setVisible(true);
                }catch (Exception e) {
                    Logger logger = Logger.getLogger(CertificateViewer.class.getName());
                    logger.log(Level.SEVERE, "An error occurred while displaying the certificate",e);
                }
//            });

        }else
            System.out.println("No template selected ");
    }

}