package com.filerenamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

import static com.filerenamer.FileNameUtil.getChapterIndexAsString;
import static com.filerenamer.FileNameUtil.getNewFileName;
import static com.filerenamer.FileNameUtil.removeLetters;

public class ReadFiles {


    static void readAll(String filenamePrefix) throws Exception {

        ReadFiles rf = new ReadFiles();
        File mainDirectory = new File( ".");
        rf.readSubdirs(mainDirectory, filenamePrefix);
    }

    private void readSubdirs(File mainDirectory, String filenamePrefix) throws Exception {
        File[] subdirArray = mainDirectory.listFiles();
        if (subdirArray != null) {
            Arrays.sort(subdirArray, Comparator.comparing(File::getName, new FilenameComparator()));
            for (File subdir : subdirArray) {
                if (subdir.isDirectory()) {
                    String chapterIndexAsDigits = removeLetters(subdir);
                    String chapterIndex = getChapterIndexAsString(chapterIndexAsDigits);
                    System.out.println("reading directory: " + subdir.getName());
                    renameFiles(subdir, filenamePrefix, chapterIndex);
                }
            }
        }
    }




    private static void renameFiles(File subdir, String filenamePrefix, String chapterIndex) throws IOException {
        File[] filesArray = subdir.listFiles();
        if (filesArray != null) {
            Arrays.sort(filesArray, Comparator.comparing(File::getName, new FilenameComparator()));
            int page = 0;
            for (File file : filesArray) {
                if (file.isFile()) {
                    page++;
                    String newFilename = getNewFileName(filenamePrefix, chapterIndex, page, file);
                    //System.out.println("reading file: " + file.getName());

                    File newFile = new File(file.getParent(), newFilename);
                    Files.move(file.toPath(), newFile.toPath());
                    //System.out.println("renaming to : " + newFilename);
                }
            }
        }
    }


}
