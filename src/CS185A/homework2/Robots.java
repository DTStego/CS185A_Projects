package CS185A.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Robots
{
    // Store blocked locations for a grid in a "row_column" format for lookup.
    static HashSet<String> blockedLocations = new HashSet<>();

    /**
     *  Given a grid (char array) of size n x n with some obstacles marked on it where the robot cannot walk,
     *  count the different ways the robot could go from the top left corner to the bottom right corner.
     *  If none, test if it were possible if it could walk up and left as well. However, your program does
     *  not handle very large numbers, so the answer should be given modulo 2^31 - 1.
     *
     * Example input:
7
......#
####...
.#.....
.#...#.
.#.....
.######
.#.....
     * Output: THE GAME IS A LIE
     */
    public static void main(String[] args) throws IOException
    {
        setEnvironment(System.in);
    }

    public static void setEnvironment(InputStream inputStream) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        // Will use to create an N x N character array.
        String input = br.readLine();

        if (input.equals("") || input.equals("0"))
            return;

        int n = Integer.parseInt(input);

        char[][] array = new char[n][n];

        for (int i = 0; i < n; i++)
        {
            array[i] = br.readLine().toCharArray();

            if (array[i].length != n)
                throw new IllegalArgumentException("One of your lines is not of 'n' size.");

            for (int j = 0; j < array[i].length; j++)
            {
                if (array[i][j] == '#')
                {
                    blockedLocations.add(i + "_" + j);
                }
            }
        }

        // Add in borders to blockedLocations.
        for (int i = 0; i < n; i++)
        {
            blockedLocations.add("-1_" + i);
            blockedLocations.add(i + "_-1");
            blockedLocations.add(n + "_" + i);
            blockedLocations.add(i + "_" + n);
        }

        int numOfRoutes;

        // Edge case: 1\n#. print INCONCEIVABLE.
        if (array[0][0] == '#')
        {
            System.out.println("INCONCEIVABLE");
            return;
        }


        if ((numOfRoutes = recurseSearch(array, 0, 0)) > 0)
        {
            System.out.println((int) (numOfRoutes % (Math.pow(2, 31) - 1)));
            return;
        }

        if (recurseSearchWithGodPowers(array, 0, 0))
        {
            System.out.println("THE GAME IS A LIE");
            return;
        }

        System.out.println("INCONCEIVABLE");
    }

    public static int recurseSearch(char[][] array, int rowIndex, int columnIndex)
    {
        if (rowIndex == array.length - 1 && columnIndex == array[0].length - 1)
            return 1;

        int paths = 0;

        if (!blockedLocations.contains(rowIndex + 1 + "_" + columnIndex))
            paths += recurseSearch(array, rowIndex + 1, columnIndex);
        if (!blockedLocations.contains(rowIndex + "_" + (columnIndex + 1)))
            paths += recurseSearch(array, rowIndex, columnIndex + 1);

        return paths;
    }

    public static boolean recurseSearchWithGodPowers(char[][] array, int rowIndex, int columnIndex)
    {
//        System.out.println();
//        System.out.println(rowIndex + " " + columnIndex);
//        printArray(array);

        if (array[rowIndex][columnIndex] == '.')
        {
            array[rowIndex][columnIndex] = '#';
            blockedLocations.add(rowIndex + "_" + columnIndex);
        }

        if (rowIndex == array.length - 1 && columnIndex == array[0].length - 1)
            return true;

        boolean accessible = false;

        if (!blockedLocations.contains(rowIndex + 1 + "_" + columnIndex))
            if (recurseSearchWithGodPowers(array, rowIndex + 1, columnIndex))
                accessible = true;
        if (!blockedLocations.contains(rowIndex + "_" + (columnIndex + 1)))
            if (recurseSearchWithGodPowers(array, rowIndex, columnIndex + 1))
                accessible = true;
        if (!blockedLocations.contains(rowIndex - 1 + "_" + columnIndex))
            if (recurseSearchWithGodPowers(array, rowIndex - 1, columnIndex))
                accessible = true;
        if (!blockedLocations.contains(rowIndex + "_" + (columnIndex - 1)))
            if (recurseSearchWithGodPowers(array, rowIndex, columnIndex - 1))
                accessible = true;

        return accessible;
    }

    public static void printArray(char[][] array)
    {
        for (char[] chars : array)
        {
            System.out.println(Arrays.toString(chars));
        }
    }
}
