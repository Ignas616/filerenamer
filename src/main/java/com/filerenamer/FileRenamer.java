package com.filerenamer;

import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;

public class FileRenamer {

    private static String chapterName;

    public static void main(String[] args) throws Exception {
        getFileName(args);
        ReadFiles.readAll(chapterName);

    }

    private static void getFileName(String[] args) {
        if (args == null || ArrayUtils.isEmpty(args)){
            chapterName = JOptionPane.showInputDialog("Write chapter name");
        } else if (args.length > 1){
            System.out.println("Please use the following command line: java -jar file.jar <filename(optional)>");
            chapterName = "chapter";
        } else {
            chapterName = args[0];
        }
    }

}
