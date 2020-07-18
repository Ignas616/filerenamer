package com.filerenamer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static com.filerenamer.FileNameUtil.getChapterIndexAsString;
import static com.filerenamer.FileNameUtil.leaveOnlyDigitsAndDot;

@RunWith(value = Parameterized.class)
public class FileNameNum2UtilTest {

    @Parameter()
    public String input;
    @Parameter(value = 1)
    public String expected;

    @Parameterized.Parameters(name = "test {index} : {0} should become {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chapter12", "12"},
                {".141", "141"},
                {"ch.14", "14"},
                {"chapter .23.", "23"},
                {"..25..", "25"},
                {"23.1", "23a"},
                {"23.2", "23b"},
                {"ch 23.3", "23c"},
                {"cg23.4", "23d"},
                {"sdf 23.6 sdf df -gf", "23f"},
                {"23.9", "23i"},
                {"chapter 43 df .df", "43"},
                {"Volume 1 chapter 44", "44"},
                {"Volume21chapter41", "41"},
                {"Volume61chapter61.5", "61e"},
                {"Volume61chapter63.5 some random par 23", "63e"},
                {"Stop. Volume3 chapter 46", "46"},
                {"Volume3 chapter 46", "46"}
        });
    }


    @Test
    public void getChapterIndexAsStringTest() {
        String chapterIndexAsString = getChapterIndexAsString(leaveOnlyDigitsAndDot(input), 2);
        Assert.assertEquals(expected, chapterIndexAsString);
    }
}