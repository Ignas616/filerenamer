package com;

import com.filemover.MoveFiles;
import com.filerenamer.ReadFiles;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.File;

public class FileTool {

    private static String chapterName;

    public static void main(String[] args) throws Exception {

        File mainDirectory = new File( ".");
        int showChoice = showChoice();
        if (showChoice == 1) {
            MoveFiles.moveAllFromSubDirs(mainDirectory);
        } else if (showChoice == 2){
            ReadFiles.readAll(chapterName, 1);
        } else {
            getFileName(args);
            ReadFiles.readAll(chapterName, 2);
        }
        JOptionPane.showMessageDialog(null, "Done!");
    }

    private static int showChoice() {
        String[] options = {"Rename 2nd num.", "Move", "Rename 1st num."};
        //Integer[] options = {1, 3, 5, 7, 9, 11};
        //Double[] options = {3.141, 1.618};
        //Character[] options = {'a', 'b', 'c', 'd'};
        return JOptionPane.showOptionDialog(null, "Select Action: to rename files in sub-folders or mve them from sub-folders to root",
                "Select Action",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
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
