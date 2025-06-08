package Certificate;
import preANDcust.UserChoice;
import resources.ImageLoader;
import userFeedback.UserFeedback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;


public class PreBuiltTemp {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

//  variable to store users feedback and username
    private static String feedback= "";
    private static String username ="";

    // Variables for certificate details
    private static String type="";
    private static String content="";
    private static String userName="";
    private static String dateInput="";
    private static String sign="";
    private static int number;
    private static String certificateName="";

    public static void prebuiltInput() {
        Scanner scanner = new Scanner(System.in);
        // Input certificate details
        System.out.print("Enter certificate name: ");
        type = scanner.nextLine();
        System.out.print("Enter content of certificate: ");
        content = scanner.nextLine();
        System.out.print("Enter recipient name: ");
        userName = scanner.nextLine();

        // Defining date format
        System.out.print("Enter Date in (dd/MM/yyyy Format): ");
        Date date = null;//Declaration of date variable
        do {
            dateInput = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);

            try {
                // Parse the user input date
                date = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. " +
                        "Please enter the date in dd/MM/yyyy format.");
            }
        } while (date == null);

        System.out.print("Enter signature: ");
        sign = scanner.nextLine();



//        Display templates and allow the user to choose
        BufferedImage selectedTemplates = chooseTemplate(scanner,date);
        // Display the customized certificate
        CertificateViewer.displayCertificate(selectedTemplates);
//        Saving JFrame to image
        PdfConverter.saveImageToFile(selectedTemplates, "certificate.png");
        while (true) {
            System.out.print("Number of copies need: ");
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Invalid input ! enter a digit");
                scanner.nextLine();
            }
        }

//        Asking user to input certificate name for pdf
        System.out.println("Enter a certificate name: ");
        certificateName = scanner.nextLine();
//        Converting image to pdf
        PdfConverter.convertImageToPDF("certificate.png",certificateName+"certificate.pdf",number);

        System.out.println("Enter your feedback please!: ");
        feedback = scanner.nextLine();

        System.out.println("Enter your name please! :");
        username = scanner.nextLine();
        UserFeedback.saveFeedback(username,feedback);
        new UserChoice().setVisible(true);

    }


    private static BufferedImage chooseTemplate(Scanner scanner, Date date) {
        BufferedImage selectedTemplate = null;
        boolean templateChosen = false;

        while (!templateChosen) {
            // Display templates and allow the user to choose
            System.out.println("CHOOSE A TEMPLATE");
            System.out.println("1-> temp1");
            System.out.println("2-> temp2");
            System.out.println("3-> temp3");
            System.out.println("4-> temp4");

            System.out.print("User Input: ");
            int choice;
            try {
                choice = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input ! please enter a number:  ");
                scanner.next();
                continue;
            }
//            to store template path
            String templatePath;

            // Choice of template
            switch (choice) {
                case 1:
                    templatePath = "temp01.png";
                    break;
                case 2:
                    templatePath = "temp02.png";
                    break;
                case 3:
                    templatePath = "temp03.png";
                    break;
                case 4:
                    templatePath = "temp04.png";
                    break;
                default:
                    System.out.println("Invalid choice");
                    continue;
            }

            // Load the selected template image
            selectedTemplate = ImageLoader.loadImage(templatePath);
            System.out.println(templatePath);

            // Overlay certificate details onto the template image
            switch (choice) {
                case 1:
                    certificateDetails1(selectedTemplate, type, content, userName, date, sign);
                    break;
                case 2:
                    certificateDetails2(selectedTemplate, type, content, userName, date, sign);
                    break;
                case 3:
                    certificateDetails3(selectedTemplate, type, content, userName, date, sign);
                    break;
                case 4:
                    certificateDetails4(selectedTemplate, type, content, userName, date, sign);
                    break;
            }
            CertificateViewer.displayCertificate(selectedTemplate);

            // Ask the user whether they want to keep the template
            System.out.println("Do you want to keep this template? (y/n): ");
            String keepTemplate = scanner.next();


            // Check the user's input
            if (keepTemplate.equalsIgnoreCase("y")) {
                templateChosen = true;
            } else if (keepTemplate.equalsIgnoreCase("n")) {
                continue;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");

            }
        }

        return selectedTemplate;
    }
    private static void certificateDetails1(BufferedImage templateImage, String type, String content, String userName, Date date, String sign) {
        // Template parameters for Template 1
//        Title size , color and their position in image
        Font titleFont = new Font("Castellar", Font.BOLD, 40);
        Color titleColor = Color.BLACK;
        int titleX = 220;
        int titleY = 220;
//        Similarly for content
        Font contentFont = new Font("Ariel", Font.PLAIN, 30);
        Color contentColor = Color.BLACK;
        int contentX = 220;
        int contentY = 300;
//        ~Title
        Font userFont = new Font("Cooper Black", Font.PLAIN, 29);
        Color userColor = Color.BLACK;
        int userX = 380;
        int userY = 360;
//        ~Title
        Font dateFont = new Font("Old Antic Bold", Font.BOLD, 22);
        Color dateColor = Color.BLACK;
        int dateX = 220;
        int dateY = 480;
        //        ~Title
        Font signFont = new Font("Freestyle Script", Font.ITALIC, 29);
        Color signColor = Color.BLACK;
        int signX = 596;
        int signY = 480;

        // Draw certificate details for Template 1
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor, titleX, titleY,
                contentFont, contentColor, contentX, contentY,
                userFont, userColor, userX, userY,
                dateFont, dateColor, dateX, dateY,
                signFont, signColor, signX, signY);
    }
    private static void certificateDetails2(BufferedImage templateImage, String type, String content, String userName, Date date, String sign) {
        // Template parameters for Template 2
        Font titleFont = new Font("Castellar", Font.BOLD, 40);
        Color titleColor = Color.YELLOW;
        int titleX = 74;
        int titleY = 81;
        Font contentFont = new Font("MS Gothic", Font.ITALIC, 27);
        Color contentColor = Color.BLACK;
        int contentX = 150;
        int contentY = 230;
        Font userFont = new Font("Gadugi", Font.PLAIN, 36);
        Color userColor = Color.BLACK;
        int userX = 347;
        int userY = 194;
        Font dateFont = new Font("Georgia", Font.PLAIN,22 );
        Color dateColor = Color.GREEN;
        int dateX = 133;
        int dateY = 431;
        Font signFont = new Font("Courier New", Font.PLAIN,28 );
        Color signColor = Color.DARK_GRAY;
        int signX = 659;
        int signY = 431;

        // Draw certificate details for Template 2
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor, titleX, titleY,
                contentFont, contentColor, contentX, contentY,
                userFont, userColor, userX, userY,
                dateFont, dateColor, dateX, dateY,
                signFont, signColor, signX, signY);
    }


    private static void certificateDetails3(BufferedImage templateImage, String type, String content, String userName, Date date, String sign) {
//        set fontsize color and style at designated postion
        Font titleFont = new Font("Ariel", Font.BOLD, 40);
        Color titleColor = Color.DARK_GRAY;
        int titleX = 41;
        int titleY = 161;
        Font contentFont = new Font("Calibri", Font.PLAIN, 25);
        Color contentColor = Color.BLACK;
        int contentX = 150;
        int contentY = 333;
        Font userFont = new Font("Arial", Font.PLAIN, 30);
        Color userColor = Color.BLACK;
        int userX = 350;
        int userY = 265;
        Font dateFont = new Font("Georgia", Font.PLAIN, 28);
        Color dateColor = Color.BLACK;
        int dateX = 157;
        int dateY = 449;
        Font signFont = new Font("Verdana", Font.PLAIN, 22);
        Color signColor = Color.BLACK;
        int signX = 605 ;
        int signY = 449;

        // Draw certificate details for Template 3
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor, titleX, titleY,
                contentFont, contentColor, contentX, contentY,
                userFont, userColor, userX, userY,
                dateFont, dateColor, dateX, dateY,
                signFont, signColor, signX, signY);
    }

    private static void certificateDetails4(BufferedImage templateImage, String type, String content, String userName, Date date, String sign) {
        Font titleFont = new Font("Ariel Black", Font.BOLD, 50);
        Color titleColor = Color.BLACK;
        int titleX = 169;
        int titleY = 178;
        Font contentFont = new Font("Lucidia Handwriting", Font.PLAIN, 25);
        Color contentColor = Color.red;
        int contentX = 253;
        int contentY = 343;
        Font userFont = new Font("Elephant", Font.PLAIN, 28);
        Color userColor = Color.BLACK;
        int userX = 150;
        int userY = 375;
        Font dateFont = new Font("Wide Latin", Font.PLAIN, 12);
        Color dateColor = Color.BLACK;
        int dateX = 526;
        int dateY = 510;
        Font signFont = new Font("Wide Latin", Font.PLAIN, 10);
        Color signColor = Color.BLACK;
        int signX = 174;
        int signY = 510;

        // Draw certificate details for Template 44
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor, titleX, titleY,
                contentFont, contentColor, contentX, contentY,
                userFont, userColor, userX, userY,
                dateFont, dateColor, dateX, dateY,
                signFont, signColor, signX, signY);
    }

//    this method ensures drawing certificate details over image
    private static void certificateDetails(BufferedImage templateImage, String type, String content, String userName, Date date, String sign,
                                           Font titleFont, Color titleColor, int titleX, int titleY,
                                           Font contentFont, Color contentColor, int contentX, int contentY,
                                           Font userFont, Color userColor, int userX, int userY,
                                           Font dateFont, Color dateColor, int dateX, int dateY,
                                           Font signFont, Color signColor, int signX, int signY) {
        Graphics2D g2d = templateImage.createGraphics();
        // Antialiasing image
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
// from the given parameter certificateDetails'n' it draws on the image
        g2d.setFont(titleFont);
        g2d.setColor(titleColor);
        g2d.drawString(type, titleX, titleY);

        g2d.setFont(contentFont);
        g2d.setColor(contentColor);
        g2d.drawString(content, contentX, contentY);

        g2d.setFont(userFont);
        g2d.setColor(userColor);
        g2d.drawString(userName, userX, userY);

        g2d.setFont(dateFont);
        g2d.setColor(dateColor);
        g2d.drawString(simpleDateFormat.format(date), dateX, dateY);

        g2d.setFont(signFont);
        g2d.setColor(signColor);
        g2d.drawString(sign, signX, signY);
//        to free up resources object g2d
        g2d.dispose();
    }


}

