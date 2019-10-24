package com.foldercreator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFolder {


    public static void createFolder(int numIndex)  {
        String folderName = getFolderNameString(numIndex);
        Path path = Paths.get(folderName);

        boolean exists =   Files.exists(path);
        if (!exists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error happened so nothing was created");
            }
        }

    }

    static String getFolderNameString(int numIndex) {
        return "Volume " + String.format("%02d", numIndex);
    }

    public static void createManyFolders(int number) {
        for (int i = 1; i <= number; i++){
            createFolder(i);
        }
    }
}
