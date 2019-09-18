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
public class FileNameNum1UtilTest {

    @Parameter(value = 0)
    public String input;
    @Parameter(value = 1)
    public String expected;

    @Parameterized.Parameters(name = "test {index} : {0} should become {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"vol 1 chapter12", "1"},
                {"chapter63.5 some random par 23", "63e"}
        });
    }


    @Test
    public void getChapterIndexAsStringTest() throws Exception {
        String chapterIndexAsString = getChapterIndexAsString(leaveOnlyDigitsAndDot(input), 1);
        Assert.assertEquals(expected, chapterIndexAsString);
    }
}