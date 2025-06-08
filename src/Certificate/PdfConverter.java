package Certificate;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class PdfConverter {
    protected static void saveImageToFile(BufferedImage image, String outputFile) {
        try {
            // Save the BufferedImage to a file
            File output = new File(outputFile);
            ImageIO.write(image, "png", output);
            System.out.println("Image saved successfully: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
    protected static void convertImageToPDF(String imagePath, String pdfPath,int numOfCopies) {
        try {
            // Create PdfWriter
            PdfWriter writer = new PdfWriter(pdfPath);

            // Create PdfDocument
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Create Document
            Document document = new Document(pdfDocument);

            // Create ImageData
            com.itextpdf.io.image.ImageData imageData = com.itextpdf.io.image.ImageDataFactory.create(imagePath);

            // Create Image
            Image image = new Image(imageData);
//            Add image to doc multiple time according to the numbr of copies
            for (int i = 0; i < numOfCopies; i++){
                document.add(image);
            }
                // Add Image to Document
            document.add(image);

            // Close Document
            document.close();

            System.out.println("PDF created successfully: " + pdfPath);
        } catch (IOException e) {
            System.err.println("Error converting image to PDF: " + e.getMessage());
        }
    }

}
