package com.filemover;

import java.io.File;

public class MoveFiles {


    public static void moveAllFromSubDirs() throws Exception {

        MoveFiles rf = new MoveFiles();
        File mainDirectory = new File( ".");
        rf.readSubdirs(mainDirectory);
    }

    private void readSubdirs(File mainDirectory) {
        File[] subdirArray = mainDirectory.listFiles();
        if (subdirArray != null) {
            for (File subdir : subdirArray) {
                if (subdir.isDirectory()) {
                    moveFiles(mainDirectory, subdir);
                }
            }
        }
    }

    private static void moveFiles(File maindir, File subdir) {
        File[] filesArray = subdir.listFiles();
        if (filesArray != null) {
            for (File file : filesArray) {
                if (file.isFile()) {
                    file.renameTo(new File(maindir, file.getName()));
                }
            }
        }
    }
}
