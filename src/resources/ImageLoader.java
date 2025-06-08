package resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {
    public static BufferedImage loadImage(String imagePath) {
        try {
            // Get the URL of the resource using getResource method
            URL resource = ImageLoader.class.getResource(imagePath);
            if (resource == null) {
                throw new IllegalArgumentException("Resource not found: " + imagePath);
            }
            // Load the image from the URL
            return ImageIO.read(resource);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            return null;
        }
    }
}
