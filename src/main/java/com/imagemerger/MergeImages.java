package com.imagemerger;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MergeImages {

    public static void mergeImages(String chapterNumber) {

        File dir = new File( "c:/temp"); // replace with your directory
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            try {
                // Read in all images
                BufferedImage[] images = new BufferedImage[files.length];
                int totalHeight = 0, maxWidth = 0;
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();
                    if (StringUtils.containsIgnoreCase(fileName, ".jpg") && !StringUtils.containsIgnoreCase(fileName, "combined")) {
                        images[i] = ImageIO.read(files[i]);
                        totalHeight += images[i].getHeight();
                        maxWidth = Math.max(maxWidth, images[i].getWidth());
                    }
                }

                // Create new image with combined height and maximum width
                BufferedImage combined = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = combined.getGraphics();

                // Draw all images onto the new image
                int currentHeight = 0;
                for (BufferedImage image : images) {
                    g.drawImage(image, 0, currentHeight, null);
                    currentHeight += image.getHeight();
                }

                // Save as new image
                ImageIO.write(combined, "JPG", new File(dir, "combined_c"+chapterNumber+".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage mergeImagesVertically(BufferedImage img1, BufferedImage img2) {
        int width = Math.max(img1.getWidth(), img2.getWidth());
        int height = img1.getHeight() + img2.getHeight();

        BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = mergedImage.createGraphics();
        g2d.drawImage(img1, 0, 0, null);
        g2d.drawImage(img2, 0, img1.getHeight(), null);
        g2d.dispose();

        return mergedImage;
    }
}
