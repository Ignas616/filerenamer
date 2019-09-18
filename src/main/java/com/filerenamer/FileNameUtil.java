package com.filerenamer;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;


class FileNameUtil {

    private final static String SEPARATOR = ".";
    private final static String CHAPTER_INDEX_REGEX = "[^\\d.]| \\.|\\.$";

    static String removeLetters(File subdir) {
        return leaveOnlyDigitsAndDot(subdir.getName());
    }

    static String leaveOnlyDigitsAndDot(String subdirName) {
        return subdirName.replaceAll(CHAPTER_INDEX_REGEX, "_");
    }

    private static String leaveOnlyDigits(String string) {
        return string.replace(".", "");
    }

    static String getNewFileName(String filenamePrefix, String chapterIndex, int page, File file) {
        String formattedPageNumber = String.format("%02d", page);
        String fileExtension = FilenameUtils.getExtension(file.getName());
        return filenamePrefix + "_c" + chapterIndex +"_p"+ formattedPageNumber + SEPARATOR + fileExtension;
    }

    static String getChapterIndexAsString(String chapterIndexAsString, int numIndex) {
        String chapterIndexReturn = "";

        String[] splited = chapterIndexAsString.split("_");
        int indexOrder = 0;

        for (String splitString : splited) {
           if (StringUtils.isNotBlank(splitString) && indexOrder < numIndex) {
               if (isNumeric(splitString)) {
                   chapterIndexReturn = splitString;
               } else if (isNumeric(leaveOnlyDigits(splitString))) {
                   chapterIndexReturn = leaveOnlyDigits(splitString);
               }
               indexOrder++;
           }
        }



        return replaceDecimalWithLetters(chapterIndexReturn);
    }

    private static String replaceDecimalWithLetters(String string){
        String[] splited = string.split("\\.");
        if (splited.length<2){
            return string;
        } else {
            int index = "a".charAt(0) + Integer.valueOf(splited[1]) - 1;
             return splited[0] + (char) index;
        }
    }


    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
