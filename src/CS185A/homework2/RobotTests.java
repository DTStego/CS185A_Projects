package CS185A.homework2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotTests
{
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp()
    {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown()
    {
        System.setOut(standardOut);
    }

    @Test
    void testCase1() throws IOException
    {
        String testCase = "7\n" +
                ".....##\n" +
                "####.##\n" +
                ".......\n" +
                "##.####\n" +
                "...####\n" +
                "#......\n" +
                "###....\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes(StandardCharsets.UTF_8));

        Robots.setEnvironment(is);

        assertEquals("THE GAME IS A LIE", outputStreamCaptor.toString().trim());
    }
}