package com.imagemerger;

import com.filerenamer.FileNameComparator;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import static com.filerenamer.FileNameUtil.getChapterIndexAsString;
import static com.filerenamer.FileNameUtil.removeLetters;


public class MergeImages {

    String[] postfix = {"", "a", "b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","t","u","v","x","y","z"};
    private static final int MAX_HEIGHT = 30000;

    String chapterNumber = "";
    int postfixCount = 0;
    File dir;

    public void mergeAll( File mainDirectory) {
        readSubdirs(mainDirectory);
    }

    private void readSubdirs(File mainDirectory) {
        File[] subdirArray = mainDirectory.listFiles();
        if (subdirArray != null) {
            Arrays.sort(subdirArray, Comparator.comparing(File::getName, new FileNameComparator()));
            for (File subdir : subdirArray) {
                if (subdir.isDirectory()) {
                    String chapterIndexAsDigits = removeLetters(subdir);
                    String chapterIndex = getChapterIndexAsString(chapterIndexAsDigits, 2);
                    postfixCount =0;
                    mergeImages(chapterIndex, subdir);
                }
            }
        }
    }
    public void mergeImages(String chapterText, File mainDirectory) {
        this.chapterNumber = chapterText;

        dir = mainDirectory; // replace with your directory
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            Arrays.sort(files, Comparator.comparing(File::getName, new FileNameComparator()));
            try {
                // Read in all images
                BufferedImage[] images = new BufferedImage[files.length];
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();
                    if (StringUtils.containsIgnoreCase(fileName, ".jpg") && !StringUtils.containsIgnoreCase(fileName, "combined")) {
                        images[i] = ImageIO.read(files[i]);
                    }
                }

                BufferedImage combinedImage = images[0];

                for (int i = 1; i < images.length; i++) {
                    if (images[i] != null) {
                        combinedImage = mergeImagesVertically(combinedImage, images[i]);
                    }
                }

                // Save as new image
                if (postfixCount>0){
                    postfixCount++;
                    chapterNumber = chapterNumber+postfix[postfixCount];
                }
                SaveAsNewImage(chapterNumber, combinedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void SaveAsNewImage(String chapterPostfix, BufferedImage combinedImage) throws IOException {
        ImageIO.write(combinedImage, "JPG", new File(dir, "combined_c"+chapterPostfix+".jpg"));
    }

    public BufferedImage mergeImagesVertically(BufferedImage img1, BufferedImage img2) throws IOException {

        int width = Math.max(img1.getWidth(), img2.getWidth());
        int height = img1.getHeight() + img2.getHeight();


        if (height> MAX_HEIGHT){
            postfixCount++;
            SaveAsNewImage(chapterNumber+postfix[postfixCount], img1);
            return img2;
        }

        BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = mergedImage.createGraphics();
        g2d.drawImage(img1, 0, 0, null);
        g2d.drawImage(img2, 0, img1.getHeight(), null);
        g2d.dispose();

        return mergedImage;
    }
}
