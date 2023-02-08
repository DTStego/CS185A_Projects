package CS185A.homework2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

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

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("THE GAME IS A LIE", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase2() throws IOException
    {
        String testCase = "1\n" +
                "#\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("INCONCEIVABLE", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase3() throws IOException
    {
        String testCase = "5\n" +
                "#....\n" +
                ".....\n" +
                ".....\n" +
                ".....\n" +
                ".....\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("INCONCEIVABLE", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase4() throws IOException, InterruptedException
    {
        String testCase = "6\n" +
                "....#.\n" +
                ".....#\n" +
                "..##..\n" +
                "#####.\n" +
                "......\n" +
                "......\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("4", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase5() throws IOException
    {
        String testCase = "5\n" +
                ".....\n" +
                "#..#.\n" +
                "#..#.\n" +
                "...#.\n" +
                ".....\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("6", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase6() throws IOException
    {
        String testCase = "10\n" +
                "..########\n" +
                "#.########\n" +
                "..########\n" +
                ".#########\n" +
                "..........\n" +
                "#####.####\n" +
                "#####.####\n" +
                "#####.####\n" +
                "#####.####\n" +
                "#####.####\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("INCONCEIVABLE", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase7() throws IOException
    {
        String testCase = "4\n" +
                "....\n" +
                "....\n" +
                "....\n" +
                "....\n";



        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("20", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase8() throws IOException
    {
        String testCase = "1\n" +
                ".\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("1", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase9() throws IOException
    {
        String testCase = "2\n" +
                "..\n" +
                "..";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("2", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase10() throws IOException
    {
        String testCase = "3\n" +
                "...\n" +
                ".#.\n" +
                "...\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("2", outputStreamCaptor.toString().trim());
    }

    @Test
    void testCase11() throws IOException
    {
        String testCase = "6\n" +
                "......\n" +
                "#####.\n" +
                "......\n" +
                ".#####\n" +
                ".....#\n" +
                "......\n";

        InputStream is = new ByteArrayInputStream(testCase.getBytes());

        Robots.setEnvironment(is);

        assertEquals("THE GAME IS A LIE", outputStreamCaptor.toString().trim());
    }
}
