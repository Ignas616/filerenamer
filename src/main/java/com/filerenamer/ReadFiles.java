package com.filerenamer;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

public class ReadFiles {

    private final static String SEPARATOR = ".";
    private final static String CHAPTER_INDEX_REGEX = "[^\\d\\.]| \\.|\\.$";

    public static void main(String[] args) throws Exception {
        readAll("C:\\eclipse-workspaces\\intelliJworkspace\\filerenamer\\test", "Renamed");
    }

    static void readAll(String path, String filenamePrefix) throws Exception {
        if (path == null) {
            path = ".";
        }

        ReadFiles rf = new ReadFiles();
        File mainDirectory = new File(path);
        rf.readSubdirs(mainDirectory, filenamePrefix);
    }

    private void readSubdirs(File mainDirectory, String filenamePrefix) throws Exception {
        File[] subdirArray = mainDirectory.listFiles();
        if (subdirArray != null) {
            Arrays.sort(subdirArray, Comparator.comparing(File::getName, new FilenameComparator()));
            for (File subdir : subdirArray) {
                if (subdir.isDirectory()) {
                    String chapterIndexAsString = subdir.getName().replaceAll(CHAPTER_INDEX_REGEX,"");
                    System.out.println("reading directory: " + subdir.getName());
                    renameFiles(subdir, filenamePrefix, chapterIndexAsString);
                }
            }
        }
    }

    private void renameFiles(File subdir, String filenamePrefix, String chapterIndex) throws IOException {
        File[] filesArray = subdir.listFiles();
        if (filesArray != null) {
            Arrays.sort(filesArray, Comparator.comparing(File::getName, new FilenameComparator()));
            int page = 0;
            for (File file : filesArray) {
                if (file.isFile()) {
                    page++;
                    String formattedPageNumber = String.format("%02d", page);
                    String fileExtension = FilenameUtils.getExtension(file.getName());
                    String newFilename = filenamePrefix + "_c" + chapterIndex +"_p"+ formattedPageNumber + SEPARATOR + fileExtension;

                    //System.out.println("reading file: " + file.getName());

                    File newFile = new File(file.getParent(), newFilename);
                    Files.move(file.toPath(), newFile.toPath());
                    //System.out.println("renaming to : " + newFilename);
                }
            }
        }
    }
}
