package com.foldercreator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class CreateFolderTest {

    @Test
    public void getFolderNameString() {

        String folderNameString = CreateFolder.getFolderNameString(1);
        Assert.assertEquals("Volume 01", folderNameString);

        folderNameString = CreateFolder.getFolderNameString(10);
        Assert.assertEquals("Volume 10", folderNameString);
    }

    @Test
    public void createFolderTest() throws IOException {

        String folderNameString = CreateFolder.getFolderNameString(5);
        CreateFolder.createFolder(5);


        Path path = Paths.get(folderNameString);

        boolean exists =   Files.exists(path);

        assertTrue(exists);

        if (exists) {
            Files.delete(path);
        }
    }
}