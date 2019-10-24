package com;

import com.filemover.MoveFiles;
import com.filerenamer.ReadFiles;
import com.foldercreator.CreateFolder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.File;

public class FileTool {

    private static final String[] MAIN_OPTIONS = {"Move", "Rename 2nd num.", "Rename 1st num.", "Create Folder(s)"};
    private static final String[] FOLDER_OPTIONS = {"Create many folders", "Create 1 folder"};

    private static String chapterName;

    public static void main(String[] args) throws Exception {

        File mainDirectory = new File( ".");
        int showChoice = showChoice();
        if (showChoice == 0) {
            MoveFiles.moveAllFromSubDirs(mainDirectory);
        } else if (showChoice == 1){
            getFileName(args);
            ReadFiles.readAll(chapterName, 2);
        } else if (showChoice == 2){
            getFileName(args);
            ReadFiles.readAll(chapterName, 1);
        } else if (showChoice == 3){
            showFolderCreationChoice();
        } else {
            exitProgram();
        }
        JOptionPane.showMessageDialog(null, "Done!");
        exitProgram();
    }

    private static void exitProgram(){
        System.exit(1);
    }

    private static int showChoice() {
        return JOptionPane.showOptionDialog(null, "Select Action: to rename files in sub-folders, move them from sub-folders to root or create Volume folders",
                "Select Action",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, MAIN_OPTIONS, MAIN_OPTIONS[0]);
    }

    private static void showFolderCreationChoice() {
        int folderChoice = JOptionPane.showOptionDialog(null, "Select Action: create many folders or create 1 folder",
                "Select Action", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, FOLDER_OPTIONS, FOLDER_OPTIONS[0]);

        int numberOfFolders = getNumberOfFolders(folderChoice);


        if (folderChoice == 0 && numberOfFolders >0 ) {
            CreateFolder.createManyFolders(numberOfFolders);
        } else if (folderChoice == 1 && numberOfFolders >0 ){
            CreateFolder.createFolder(numberOfFolders);
        }

    }

    private static int getNumberOfFolders(int folderChoice) {
        String numberOfFolders = null;
        if (folderChoice == 0) {
            numberOfFolders = JOptionPane.showInputDialog("Write number of folders: ");
        }

        if (folderChoice == 1) {
            numberOfFolders = JOptionPane.showInputDialog("Write folder number: ");
        }
        try {
            return Integer.valueOf(numberOfFolders);
        } catch (Exception e){
            JOptionPane.showConfirmDialog(null, "Error happened so nothing was created");
            return -1;
        }
    }

    private static void getFileName(String[] args) {
        if (args == null || ArrayUtils.isEmpty(args)) {
            chapterName = JOptionPane.showInputDialog("Write chapter name");
        }

        if (StringUtils.isBlank(chapterName)) {
            chapterName = "chapter";
        }
    }

}
