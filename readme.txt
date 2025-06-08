                   Certificate Generator
DESCRIPTION

The project focus on certificate generation where users are provided with two choices either they can create a
certificate from a pre-built template or Custom template , and the user provide information required for certificate and print it
as pdf

INSTALLATION

First set up the sql
1-> to set up the sql first they need to run the local server for that they need to run the query
"mysqld --console" before that they need ensure they opened "bin"file on mysql file using cmd (i am providing link to mysql to download )
2-> they can use my database or either create database for them ,if they are creating new one change the URL,USERNAME and PASSWORD ,am using
default port 3306
2-> need to setup the database using query
"create databases 'database name'"
"use database name"
"create tables 'name'"
"create table name(
   username VARCHAR(50) NOT NULL
   password VARCHAR(50) NOT NULL
);"
these will create a database, make sure to change the name of the URL from Login class if u gave the same name as mine dont need to change it userlogin
and i have added jdbc connector "mysql-connector-j-8.3.0 " jar file to connect mysql to our java programme


Mysql Download
-https://dev.mysql.com/downloads/mysql/

EXPLANATION OF THE CODE

I have created multiple class over the programme so lets go through one by one

PACKAGE(UserLogin)
               i)Login
import java.sql.*; -> importing classes for database connectivity and manupliation
we create three constant variable (URL, username and password) they are used to establish connection with database
we create method called login which has a parameter username and password
we establish connection with database using DriverManager.getConnection(URL, USER, PASSWORD)
after that we execute the sql query "SELECT * FROM login_id WHERE username = ? AND password = ?"
here the "?" placeholders indicates the values of password and username will be provided after the execution of the query
we set the query parameter with "PreparedStatement" method which are "username" & "password"
then we are executing the query using ,method called executeQuery which return resultset.next which make sure at least one row is matching or not if not its turn to be false otherwise true
if there is any exception caught , it returns the false

MENTIONS
- JDBC connector download link
https://dev.mysql.com/downloads/connector/j/




reference
-baeldung. “Connect Java to a MySQL Database | Baeldung.” Www.baeldung.com, 1 Mar. 2020, www.baeldung.com/java-connect-mysql.
-“Connect Java to a MySQL Database.” Stack Overflow, stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database.
-“MySQL :: MySQL Connector/J Developer Guide :: 7.1 Connecting to MySQL Using the JDBC DriverManager Interface.” Dev.mysql.com, dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-connect-drivermanager.html.



                ii)Create Account
Creating account is similar as the login the changes are in the Query "INSERT INTO login_id (username, password) VALUES (?, ?)"
where we insert the value to the parameter "username" & "password"
and here we are using executeUpdate() instead of executeQuery(); which will execute the sql "INSERT" query
for verifying weather the account creation was successful or nor by checking number of rows effected if more than 1 row
is effected it mean account creation was successful otherwise its unsuccessful


MENTIONS
- JDBC connector download link
https://dev.mysql.com/downloads/connector/j/

reference
-baeldung. “Connect Java to a MySQL Database | Baeldung.” Www.baeldung.com, 1 Mar. 2020, www.baeldung.com/java-connect-mysql.
-“Connect Java to a MySQL Database.” Stack Overflow, stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database.
-“MySQL :: MySQL Connector/J Developer Guide :: 7.1 Connecting to MySQL Using the JDBC DriverManager Interface.” Dev.mysql.com, dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-connect-drivermanager.html.


2)PACKAGE(loginPageGUI)
we have created connection with database now we need user interface where user can create or login

                        i)LoginPage
we have imported UserLogin page, preANDcust were user are given choice to make between prebuilt and custom template and
we have other important imports for building GUI
LoginPage class extends JFrame which means GUI window
then created constructor LoginPage which run initializeGui method
SwingUtilities.invokeLater(new Runnable() {} ) as we know that swing is single threaded it avoid concurrency issues
in the initializeGui method we make login page interface by setting size,exit on close and all
then we create a jpanel with 3x2 grid layout , we create JLabels which are used to display labels for username and password field
JTextFields (usernameField) and JPasswordField (passwordField) are created to accept user input for username and password
Using JButton we create login, create account button
i have used ActionListener to handle user interaction if user enter password and username then clicks login
actionPerformed method is called where user inputed username and password are stored in string calls Login.login method to authenticate the user if the
logging was successful  it heads to shows a new jframe where user can choose between pre-built and custom template and dispose the login page
otherwise logging is unsuccessful prints a error message

if the user select to create account it opens up the create account window

refernces
-GeeksforGeeks. “Introduction to Java Swing.” GeeksforGeeks, 15 Feb. 2022, www.geeksforgeeks.org/introduction-to-java-swing/.
-“ItemListener vs ChangeListener vs ActionListener.” Stack Overflow, stackoverflow.com/questions/53123562/itemlistener-vs-changelistener-vs-actionlistener. Accessed 26 Apr. 2024.
-“Java ActionListener - Javatpoint.” Www.javatpoint.com, www.javatpoint.com/java-actionlistener.
-JDK 22 Documentation.” Oracle Help Center, docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JTextField.html. Accessed 26 Apr. 2024.
-Waseem, Mohammad. “Swing in Java: Creating GUI Using Java Swing.” Edureka, 1 July 2019, www.edureka.co/blog/java-swing/.



                    ii)CreateAcGUI
It's very similar to Login page GUI ,we use same library as login page we create constructor called CreateAcGUI where the JFrame size, and all are setted here we are using dispose on close for close operation since its pop-up window
similar we create 3x2 grid layout and we create JLabels which are used to display labels for "username" & "password"
then we create JTextField to accept user input and store it in a String variable called "username" and "password" ,
we create a button for "Create Account" using JButton
when the user click on "Create Account" and actionPerformed method is called where we call CreateAc.createAccount(username, password) method which gives the stored parameter
shows a account creation successful in window

refernces
-GeeksforGeeks. “Introduction to Java Swing.” GeeksforGeeks, 15 Feb. 2022, www.geeksforgeeks.org/introduction-to-java-swing/.
-“ItemListener vs ChangeListener vs ActionListener.” Stack Overflow, stackoverflow.com/questions/53123562/itemlistener-vs-changelistener-vs-actionlistener. Accessed 26 Apr. 2024.
-“Java ActionListener - Javatpoint.” Www.javatpoint.com, www.javatpoint.com/java-actionlistener.
-JDK 22 Documentation.” Oracle Help Center, docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JTextField.html. Accessed 26 Apr. 2024.
-Waseem, Mohammad. “Swing in Java: Creating GUI Using Java Swing.” Edureka, 1 July 2019, www.edureka.co/blog/java-swing/.


PACKAGE(preANDcust)
                    i)userChoice
here we are importing package called certificate where we have used two classes such as CustomTemp and preBuiltTemp so after user choose custom template or prebuilt template we can call these methods
as well as we used loginPageGUI package to use LoginPage method

this one is also similiar to loginPage and CreateAcGUI as you can see we created
UserChoice class extends JFrame which indicates its  aGUI window
then we create constructor called UserChoice which runs the method called "createAndShowGUI"
SwingUtilities.invokeLater(new Runnable() {} ) as we know that swing is single threaded to avoid concurrency issues we use this method
createANDGUI methods set the title, size , default close and location of jframe
then we create three button use JButton(View Prebuilt Templates,View Custom Templates,Back to Login Page)
for the user to interact with the each button we use addActionListener as per the user interaction
we can call each method; if user click custom temp we call "CustomTemp.CustomTempInput()" similarly for pre-builtTemplate we call "PreBuiltTemp.prebuiltInput()"
dispose are places above the section code to close the userChoice window
and for the third option if user to Log out we can open login page


refernces
-GeeksforGeeks. “Introduction to Java Swing.” GeeksforGeeks, 15 Feb. 2022, www.geeksforgeeks.org/introduction-to-java-swing/.
-“ItemListener vs ChangeListener vs ActionListener.” Stack Overflow, stackoverflow.com/questions/53123562/itemlistener-vs-changelistener-vs-actionlistener. Accessed 26 Apr. 2024.
-“Java ActionListener - Javatpoint.” Www.javatpoint.com, www.javatpoint.com/java-actionlistener.
-JDK 22 Documentation.” Oracle Help Center, docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/JTextField.html. Accessed 26 Apr. 2024.
-Waseem, Mohammad. “Swing in Java: Creating GUI Using Java Swing.” Edureka, 1 July 2019, www.edureka.co/blog/java-swing/.

PACKAGE(resources)

I created package called resource where copied the images neeeded for template creation
this will improve the portablity and scalablity of the project, the other dont need to change the img directory on thier
device to use this code we should make sure the image is saved in png or jpeg format format

                i)ImageLoader
 "import javax.imageio.ImageIO" the package is used for reading and writing image
 "import java.awt.image.BufferedImage;" this imports an buffered image class from "import java.awt.image" package which represent image
 in memory
 "import java.io.IOException" this package is used to handle input/output error
 "import java.net.URL;" the line import URL class which is used to obtain image resource of the pacified file path
we create method "public static BufferedImage loadImage(String imagePath)" inside ImageLoader class which return buffered image from the URL and it takes String parameter "imagePath" represents
the path of the image file
"URL resource = ImageLoader.class.getResource(imagePath);" we use"getResource" to obtain a URL of the resource specified by
"imagePath"
if the resource is null we throw an exception with an error message
"return ImageIO.read(resource);" this line read the image data from the resource URL using "ImageIO.read" method and
return the bufferedImage object
we use try catch method to find any exception

resources

-“ItemListener vs ChangeListener vs ActionListener.” Stack Overflow, stackoverflow.com/questions/53123562/itemlistener-vs-changelistener-vs-actionlistener. Accessed 26 Apr. 2024.
-Class GetResource() Method in Java with Examples.” GeeksforGeeks, 27 Dec. 2019, www.geeksforgeeks.org/class-getresource-method-in-java-with-examples/. Accessed 26 Apr. 2024
PACKAGE(userFeedback)
-“Load Java Image inside Package from a Class in a Different Package.” Stack Overflow, stackoverflow.com/questions/12153590/load-java-image-inside-package-from-a-class-in-a-different-package. Accessed 26 Apr. 2024.
                       i)UserFeedback
Here we are saving users feedback to file,
so we can improve our product based on user interaction smoothness

"import java.io.IOException" this package is used to handle input/output error during file operation
"import java.io.FileWriter" use to write character on a file
"import java.time.LocalDateTime" & "import java.time.format.DateTimeFormatter" these classes are used to get the current
data and time wnd format it

then i created "saveFeedback" method which has parameter "String username, String feedback"
which is user inputed username and the feedback they given
"LocalDateTime.now();" we can get current date and time use this method
then we format the date in year - month - day , hour - min -second
using "DateTimeFormatter"
then we concatenating the username, date and time with the feedback
the feedbacks will be saved as feedback.txt
"FileWriter" creates object to write the file on specified filepath and , the true parameter in "FileWriter" indicate file
should be opened in append mode , allowing new content to be append to new content
if an exception occur to save feedback it displays and error message otherwise successful save the feedback


resources
“DateTimeFormatter (Java Platform SE 8 ).” Docs.oracle.com, docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html.
“Java Files.” Www.w3schools.com, www.w3schools.com/java/java_files.asp.
“Java.io.File Class in Java.” GeeksforGeeks, 12 Dec. 2016, www.geeksforgeeks.org/file-class-in-java/.



PACKAGE(Certificate)

             i)PdfConvertor
To convert an image to an pdf we need to download an external library called itext7(https://itextpdf.com/)
you can download and integrate it with our project
"import com.itextpdf.kernel.pdf.PdfDocument;
 import com.itextpdf.kernel.pdf.PdfWriter;
 import com.itextpdf.layout.Document;
 import com.itextpdf.layout.element.Image;"
 this line import necessary classes from iText library for working with pdf document all the classes are provided by
 iText for PDF manipulation

"import javax.imageio.ImageIO; - used to read and write images
 import java.awt.image.BufferedImage; - used store image in store
 import java.io.File; - represent a file on filesystem
 import java.io.IOException; - used to handle input and output exception"

in these "PdfConvertor" class we two method one is for saving jframe to an image and other one is
for  converting image to pdf and save it

first we have static method "saveImageToFile" to change JFrame to image with two parameters "image, outputFile"
the method is declared as protected which means it can only be accessed in package
and saves the image on specified file
we use try and catch method if code throw exception it prints error
"File output = new File(outputFile);
             ImageIO.write(image, "png", output);"
the code create file with the given file path "outputFile"
and Save the buffered image in png format in the given file path

then the 2nd method convertImageToPDF which has three parameter "imagePath, pdfPath, numOfCopies"
image path parameter take input of image , "pdfPath" parameter the file path of the output PDF and also the number of copies need
the method is declared as protected so it can only be used within the subclasses or package

"PdfWriter writer = new PdfWriter(pdfPath);"
"PdfDocument pdfDocument = new PdfDocument(writer);"
These line create a object for writing pdf content to the specified output file
"            Document document = new Document(pdfDocument);"
the line create pdf document which represent the "pdfDocument" and provide method for adding content to it
"com.itextpdf.io.image.ImageData imageData = com.itextpdf.io.image.ImageDataFactory.create(imagePath);"
com.itextpdf.io.image.ImageData this part specifies the class which represent image date in a format suitable for use in
pdf format then we create an "ImageData" object , then we call "create" method of the ImageDataFactory class , the "create"
method requires a parameter which is file path of the image , it read the image file and converts it into format which supports pdf document

"Image image = new Image(imageData);" this line create an image from the "imageData" object and this image is used to add
on pdf document

to have multiple copies the certificate we generated we use for loop,
and add the image to document

as you can see we have 2nd line of "document.add(image);" here because at least one pdf doc with image will be created
  document.close();
  we closes the document
 after the pdf creation if the creation was successful we will print an success message and pdf path along with it
 otherwise an unsuccessful message


resources
-“IText Jump-Start Tutorial : Chapter 1.” Kb.itextpdf.com, kb.itextpdf.com/itext/itext-7-jump-start-tutorial-chapter-1. Accessed 26 Apr. 2024.
-“Create Image from Jframe.” Stack Overflow, stackoverflow.com/questions/58228791/create-image-from-jframe. Accessed 26 Apr. 2024.
        ii)PrebuiltTemp

"import preANDcust.UserChoice;
 import resources.ImageLoader;
 import userFeedback.UserFeedback;"
  we have imported necessary  packages and classes for user feedback, image loading

  "import java.text.ParseException;
   import java.text.SimpleDateFormat;
   import java.util.Date;
   import java.util.InputMismatchException;
   import java.util.Scanner;
   import java.awt.image.BufferedImage;
   import javax.swing.*;
   import java.awt.*; "

   these packages and classes are used to date parsing,take user input and other classes to draw over the blank certificate
   "SimpleDateFormat" object is createed to parse date enter by user

   variable to store users feedback and username
     "  private static String feedback= "";
       private static String username =""; "

       Variables to store certificate details
           private static String type="";
           private static String content="";
           private static String userName="";
           private static String dateInput="";
           private static String sign="";
           private static int number;
           private static String certificateName="";
       I created an empty string to prevent collapsing of terminal if user input "SPACE" trim will prevent it from happening
        created  methods "chooseTemplate","certificateDetails n"(n -> each certificate  details thats stored and its hard coded )
       , "certificateDetails" and "displayCertificate" these are method in PrebuiltTemp class

     A "scanner" object is created to read user input
     and prompt the user to enter certificate details
     such as certificate name, content,recipient name, signature
     we prompt the uer to enter date in format (dd/MM/yyyy format)
     store the date in "dateInput" variable
     and we create "dateFormat" object, the object is used parse string into date and vice verse and the format string
     expected to be in the format of "dd/MM/yyyy"
     we set "dateFormat.setLenient(false);" this line sets the leniency of date format parsing to false
     this will strictly enforce the format and detecting invalid dates
     then we parse the user input inside a try and catch method if user input format is wron
     its inside do-while until user input the date in specified format

     after that we call method to display templates ad allow user to choose on
     BufferedImage selectedTemplates = chooseTemplate(scanner,date);

     this method display the customized certificate
     displayCertificate(selectedTemplates);

     this method saves Jframe to image for saving to pdf
       PdfConverter.saveImageToFile(selectedTemplates,

       after that users are prompt to enter the number of copies they need, if the user input mismatch or if they enter
       anything else other than a number it will print error message ask the user to input valid input , the loop wont break until user input valid input

       then user are prompt to enter certificate name , otherwise newly created pdf replaces the old one

       then we call another method convertImageToPDF from PdfConvertor class
       where convert image to pdf , which has paramter "imagePath", "pdfPath" which is concatinated with certificate name which user input
       ,"number"  - number of copies needed

       then we prompt the user to input name and feedback
       call saveFeedback method from UserFeedback class to save feedback and username
       "new UserChoice().setVisible(true);" - this line create anew window were the user can navigate to the other parts application

       lets explain each methods I mentioned above
       "chooseTemplate" - method which is private static method which take two parameter "scanner" and "date" and returns
       "BufferedImage" its private static so don't we need an instance of class to be called

       then we create BufferedImage variable which store selected template and it intializes to null
       "BufferedImage selectedTemplate = null;"

then the line declares boolean varibale called "templatechoose" which intialized as false for controlling the loop until the user select
template

then we create a while loop it continues until user selects a template if the user input mismatches or they input string
any character instead of integer it prints error message sask the user to input correctly again
and loop continues - using try catch (InputMismatchException)

then we declare String variable called "templatePath" to store template path
use "switch" statement to compare user input value and assigns the corresponding template file
the file path are saved in resources package for scalablity and portablity

and the selected template is loaded to variable "selectedTemplate" by calling "ImageLoader.loadImage(templatePath);"
after that we overlay the certificate details onto the template image
by calling cerificaedDetailsm(m = 1 -4) methods after that certificate is displayed using  "displayCertificate(selectedTemplate)" method
and the program then prompt the uer to decide whether they want to keep the template or not
if yes return the "selectedTemplate" if "no" is user input then the loop continues

method certificateDetails1 which has parameter "BufferedImage templateImage, String type, String content, String userName, Date date, String sign"
" Font titleFont = new Font("Castellar", Font.BOLD, 40);"
line create a font object for the title with font style Castellar ,in bold and size of 40

"Color titleColor = Color.BLACK;"
color of text will be black

int titleX = 300;
int titleY = 220;
and they are designated at the (300,200) co-ordinates

similarly for all other parameter

"certificateDetails(templateImage, type, content, userName, date, sign,
                 titleFont, titleColor, titleX, titleY,
                 contentFont, contentColor, contentX, contentY,
                 userFont, userColor, userX, userY,
                 dateFont, dateColor, dateX, dateY,
                 signFont, signColor, signX, signY);"
then we call this method which is responsible for overlaying details on certificate

this is same for the other three method certificateDetails2,certificateDetails3 and certificateDetails4 these methods
specify the parameter we overlay it using "certificateDetails" method

"certificateDetails" method consists fof number of parameter "BufferedImage templateImage, String type, String content, String userName, Date date, String sign,
                                                                                                        Font titleFont, Color titleColor, int titleX, int titleY,
                                                                                                        Font contentFont, Color contentColor, int contentX, int contentY,
                                                                                                        Font userFont, Color userColor, int userX, int userY,
                                                                                                        Font dateFont, Color dateColor, int dateX, int dateY,
                                                                                                        Font signFont, Color signColor, int signX, int signY"
"Graphics2D g2d = templateImage.createGraphics();"
we create "g2d" object which allow us to draw onto template image
" g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
this line improve the smoothness of line and edges in the graphics

"g2d.setFont(titleFont);
g2d.setColor(titleColor);
g2d.drawString(type, titleX, titleY);"
these lien se the font and colour of the title are draw at the designate coordinate
these is simliar for all other parameters
at the end we dispose it with to free up resources "g2d.dispose();"


now "displayCertificate" method which is responsible for displaying certificate details
 which take parameter "BufferedImage" name "certificateImage" as input
 if the "certificateImage != null "  ensure there is an image to display
 this creates a JFrame ,where the window will display image
 set the the name for the window and its size
then we create JLabel "label" set its icon to be "certificateImage" converted into 'ImageIcon'
 then we center the label within the frame using " JLabel label = new JLabel(new ImageIcon(certificateImage));"
  else if the "certificateImage " then prints error message


REFERENCES
-“Drawing on Jframe.” Stack Overflow, stackoverflow.com/questions/2134840/drawing-on-jframe. Accessed 26 Apr. 2024.
-Intro to Drawing Graphics.
 “Java 2D Graphics | Think Java | Trinket.” Books.trinket.io, books.trinket.io/thinkjava/appendix-b.html.
 Orozco-Fletcher, Michael. “Java Lesson 21: Drawing and Coloring Shapes on the JFrame.” Medium, 16 Dec. 2021, medium.com/@michael71314/java-lesson-21-drawing-and-coloring-shapes-on-the-jframe-d740970e1d68. Accessed 26 Apr. 2024.


         iii)CustomTemp

These class have same infrastructure as the previous class "PrebuiltTemp" they have imports as "PrebuiltTemp"
however here we declare some addition variables so the users can select fontsize color, font type etc
 "fontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();"
stores all the the font style in fontname
then ask user input for the certificate details and stores in the variables
and their error handling calls the same methods for certification creation as "prebuiltTemp"additionally I added
methods such as "selectFont" where it has two parameter "Scanner scanner, String component"
GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
this line retrieves local graphics environment which provides information about the available font system
"fontName = ge.getAvailableFontFamilyNames();"
it obtain all the array on available font name from graphics environment
then font iterates through available font on font family
then we created new object called "font" if font can display "a" then it means the font supports as character since some
font style arent available on java then it prints out all the font name available
ask the user to input the number , if the font fails it returns a defualt font and print an error message

then we have another new method called "pickColor" where parameter are "Scanner scanner, String component "
which allow the user to choose color from a dialog box
"JColorChooser.showDialog(null,"Pick a "+component+"color",Color.BLACK)"



