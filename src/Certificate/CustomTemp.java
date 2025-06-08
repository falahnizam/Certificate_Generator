package Certificate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import preANDcust.UserChoice;
import resources.ImageLoader;
import userFeedback.UserFeedback;


public class CustomTemp {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static String feedback= "";
    private static String username ="";

//    UserInput details
    private static String type = "";
    private static String content = "";
    private static String userName = "";
    private static String dateInput = "";
    private static String sign="";
//    Storing UserInput for Certificate customization
    private static int contentSize;
    private static int titleSize;
    private static int dateSize;
    private static int signSize;
    private static int nameSize;

//    Name
    private static String fontName[];
    private static String contentName="";
    private static String titleName="";
    private static String dateName="";
    private static String signName="";
    private static String nameName="";
//    Color
    private static Color contentColor;
    private static Color titleColor;
    private static Color dateColor;
    private static Color signColor;
    private static Color nameColor;

    private static int number;
    private static String certificateName="";


    public static void CustomTempInput() {
        Scanner scanner = new Scanner(System.in);
//        To access all the available fonts in java
        fontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

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

        // UserInput for Certificate customization
        System.out.print("Enter font size for title: ");
        while (true){
            try {
                titleSize = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Invalid input! please enter a number: ");
                scanner.nextLine();
            }
        }
        System.out.println("Enter font size for content: ");
        while (true){
            try {
                contentSize = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Invalid input! please enter a number: ");
                scanner.nextLine();
            }
        }

        System.out.println("Enter font size for date: ");
        while (true) {
            try {
                dateSize = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Invalid input! please enter a number: ");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.println("Enter font size for sign: ");
            try {
                signSize = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Invalid input! please enter a number: ");
                scanner.nextLine();
            }
        }
        while (true){
            System.out.println("Enter font size for Name: ");
            try {
                nameSize = scanner.nextInt();
                scanner.nextLine();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Invalid input! please enter a number: ");
                scanner.nextLine();
            }
        }

        // Selecting colors for different components
        contentColor = pickColor(scanner, "content");
        signColor = pickColor(scanner, "sign");
        titleColor = pickColor(scanner, "title");
        dateColor = pickColor(scanner, "date");
        nameColor = pickColor(scanner, "name");

        // Selecting fonts for different components
        contentName = selectFont(scanner, "content");
        titleName = selectFont(scanner, "title");
        dateName = selectFont(scanner, "date");
        signName = selectFont(scanner, "signature");
        nameName = selectFont(scanner, "Name");

        // Print the selected Fonts
        System.out.println("Selected content font: " + contentName);
        System.out.println("Selected title font: " + titleName);
        System.out.println("Selected date font: " + dateName);
        System.out.println("Selected signature font: " + signName);

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
//        back to userchoice page after successfully creating pdf
        System.out.println("Enter your feedback please!: ");
        feedback = scanner.nextLine();

        System.out.println("Enter your name please! :");
        username = scanner.nextLine();
        UserFeedback.saveFeedback(username,feedback);
        new UserChoice().setVisible(true);
    }

    private static String selectFont(Scanner scanner, String component) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontName = ge.getAvailableFontFamilyNames();
        int validFontCount = 0; // Valid fonts are counted
        System.out.println("Select font " );
        for (int i = 0; i < fontName.length; i++) {
            //Create font object
            Font font = new Font(fontName[i], Font.PLAIN,12);
            if (font.canDisplay('a')) { // check if font can display chara
                validFontCount++;
                System.out.println((i + 1) + " -> " + fontName[i]); // Corrected index display
            }
        }
        System.out.println("Select font for " + component);
        System.out.print("\nEnter the corresponding number: ");
        int fontChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newLine
        if (fontChoice < 1 || fontChoice > fontName.length) {
            System.out.println("Invalid Choice, Using default font.");
            return fontName[0]; // Default font
        } else {
            int validFontIndex = 0;
            for ( int i = 0; i < fontName.length; i++) {
                Font font = new Font(fontName[i], Font.PLAIN,12);
                if (font.canDisplay('a')) {
                    validFontIndex++;
                    if (validFontIndex ==fontChoice);
                    return fontName[i];
                }
            }
        }
        System.out.println("Error! Font selection Failed , Using default font. ");
        return fontName[0];
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
            System.out.println("0-> Return to main menu");

            System.out.print("User Input: ");
            int choice;
            try {
                choice = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Invalid Input ! please enter a number:  ");
                scanner.next();
                continue;
            }
            String templatePath = "";
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
                case 0:
                    System.exit(0); // Return to main menu
                default:
                    System.out.println("Invalid choice");
                    continue;
            }

            // Load the selected template image
            selectedTemplate = ImageLoader.loadImage(templatePath);

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

    private static Color pickColor(Scanner scanner, String component) {
        System.out.println("Pick a "+ component + " Color");
        return JColorChooser.showDialog(null,"Pick a "+component+"color",Color.BLACK);
    }
    private static void certificateDetails1(BufferedImage templateImage, String type, String content, String userName, Date date, String sign) {
        Font titleFont = new Font(titleName, Font.BOLD, titleSize);
        Color titleColor1 = titleColor ;
        int titleX = 300;
        int titleY = 220;
        Font contentFont = new Font(contentName, Font.PLAIN, titleSize);
        Color contentColor1 = contentColor;
        int contentX = 220;
        int contentY = 300;
        Font userFont = new Font(nameName, Font.PLAIN, nameSize);
        Color userColor = nameColor ;
        int userX = 380;
        int userY = 360;
        Font dateFont = new Font(dateName, Font.BOLD, dateSize);
        Color dateColor1 = dateColor;
        int dateX = 220;
        int dateY = 480;
        Font signFont = new Font(signName, Font.ITALIC, signSize);
        Color signColor1 = signColor;
        int signX = 596;
        int signY = 480;

        // Draw certificate details for Template 1
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor1, titleX, titleY,
                contentFont, contentColor1, contentX, contentY,
                userFont, userColor, userX, userY,
                dateFont, dateColor1, dateX, dateY,
                signFont, signColor1, signX, signY);
    }
    private static void certificateDetails2(BufferedImage templateImage, String type, String content, String userName, Date date, String sign){
        // Template parameters for Template 1
        Font titleFont = new Font(titleName, Font.BOLD, titleSize);
        Color titleColor2 = titleColor ;
        int titleX = 74;
        int titleY = 81;
        Font contentFont = new Font(contentName, Font.ITALIC, contentSize);
        Color contentColor2 = contentColor;
        int contentX = 150;
        int contentY = 230;
        Font userFont = new Font(nameName, Font.PLAIN, nameSize);
        Color userColor2 = nameColor;
        int userX = 347;
        int userY = 194;
        Font dateFont = new Font(dateName, Font.PLAIN,dateSize );
        Color dateColor2 = dateColor;
        int dateX = 133;
        int dateY = 431;
        Font signFont = new Font(signName, Font.PLAIN,signSize );
        Color signColor2 = signColor;
        int signX = 659;
        int signY = 431;

        // Draw certificate details for Template 2
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor2, titleX, titleY,
                contentFont, contentColor2, contentX, contentY,
                userFont, userColor2, userX, userY,
                dateFont, dateColor2, dateX, dateY,
                signFont, signColor2, signX, signY);

    }
    private static void certificateDetails3(BufferedImage templateImage, String type, String content, String userName, Date date, String sign){
        Font titleFont3 = new Font(titleName, Font.BOLD, titleSize);
        Color titleColor3 = titleColor;
        int titleX = 41;
        int titleY = 161;
        Font contentFont3 = new Font(contentName, Font.PLAIN, contentSize);
        Color contentColor3 = contentColor;
        int contentX = 150;
        int contentY = 333;
        Font userFont3 = new Font(nameName, Font.PLAIN, nameSize);
        Color userColor3 = nameColor;
        int userX = 350;
        int userY = 265;
        Font dateFont3 = new Font(dateName, Font.PLAIN, dateSize);
        Color dateColor3 = dateColor;
        int dateX = 157;
        int dateY = 449;
        Font signFont3 = new Font(signName, Font.PLAIN, signSize);
        Color signColor3 = signColor;
        int signX = 605 ;
        int signY = 449;

        // Draw certificate details for Template 3
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont3, titleColor3, titleX, titleY,
                contentFont3, contentColor3, contentX, contentY,
                userFont3, userColor3, userX, userY,
                dateFont3, dateColor3, dateX, dateY,
                signFont3, signColor3, signX, signY);
    }
    private static void certificateDetails4(BufferedImage templateImage, String type, String content, String userName, Date date, String sign){
        Font titleFont = new Font(contentName, Font.BOLD, contentSize);
        Color titleColor4 = titleColor;
        int titleX = 169;
        int titleY = 178;
        Font contentFont = new Font(contentName, Font.PLAIN, contentSize);
        Color contentColor4 = titleColor4;
        int contentX = 253;
        int contentY = 343;
        Font userFont = new Font(nameName, Font.PLAIN, nameSize);
        Color userColor4 = nameColor;
        int userX = 150;
        int userY = 375;
        Font dateFont = new Font(dateName, Font.PLAIN, dateSize);
        Color dateColor4 = dateColor;
        int dateX = 526;
        int dateY = 510;
        Font signFont = new Font(signName ,Font.PLAIN, signSize);
        Color signColor4 = signColor ;
        int signX = 174;
        int signY = 510;

        // Draw certificate details for Template 44
        certificateDetails(templateImage, type, content, userName, date, sign,
                titleFont, titleColor4, titleX, titleY,
                contentFont, contentColor4, contentX, contentY,
                userFont, userColor4, userX, userY,
                dateFont, dateColor4, dateX, dateY,
                signFont, signColor4, signX, signY);
    }

    private static void certificateDetails(BufferedImage templateImage, String type, String content, String userName, Date date, String sign,
                                           Font titleFont, Color titleColor, int titleX, int titleY,
                                           Font contentFont, Color contentColor, int contentX, int contentY,
                                           Font userFont, Color userColor, int userX, int userY,
                                           Font dateFont, Color dateColor, int dateX, int dateY,
                                           Font signFont, Color signColor, int signX, int signY) {
        Graphics2D g2d = templateImage.createGraphics();
        // Antialiasing image
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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

        g2d.dispose();
    }
}
