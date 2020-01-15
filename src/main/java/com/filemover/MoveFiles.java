package com.filemover;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MoveFiles {


    public static void moveAllFromSubDirs(File mainDirectory) {
        MoveFiles rf = new MoveFiles();
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

    public static void copyJarToVolumeFolders(File mainDirectory) {
        File[] subdirArray = mainDirectory.listFiles();
        File jarFile = getJar(mainDirectory);
        if (subdirArray != null) {
            for (File subDir : subdirArray) {
                if (subDir.isDirectory() && subDir.getName() != null && subDir.getName().startsWith("Volume ")) {
                    copyJarFile(jarFile, subDir);
                }
            }
        }
    }

    private static File getJar(File mainDirectory) {
        File[] files = mainDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName() != null && file.getName().endsWith("jar")) {
                    return file;
                }
            }
        }
        return new File("filetool.jar");

    }

    private static void copyJarFile(File jarFile, File subDir) {
        File newFile = new File(subDir.getPath() + "/" + jarFile.getName());
        if (!newFile.exists()) {
            try {
                Files.copy(jarFile.toPath(), newFile.toPath());
            } catch (IOException e) {
                System.out.println("Could not copy to : " + newFile.getAbsolutePath());
            }
        }
    }


}
