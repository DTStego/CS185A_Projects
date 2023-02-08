package CS185A.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Robots
{
    // Store blocked locations for a grid in a "row_column" format for lookup.
    static HashSet<String> blockedLocations;

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
        blockedLocations = new HashSet<>();

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

        long numOfRoutes;

        // Edge case: 1\n#. print INCONCEIVABLE.
        if (array[0][0] == '#')
        {
            System.out.println("INCONCEIVABLE");
            return;
        }

        // Memoization technique
        long[][] memoryArray = new long[n][n];
        memoryArray[0][0] = 1;

        if ((numOfRoutes = recurseSearch(array, 0, 0, memoryArray)) > 0)
        {
            System.out.println(numOfRoutes);
            return;
        }

        if (recurseSearchWithGodPowers(array, 0, 0))
        {
            System.out.println("THE GAME IS A LIE");
            return;
        }

        System.out.println("INCONCEIVABLE");
    }

    public static long recurseSearch(char[][] array, int rowIndex, int columnIndex, long[][] memoryArray)
    {
//        System.out.println();
//        System.out.println(rowIndex + 1 + " " + (columnIndex + 1));
//        printArray(array);

        if (array[rowIndex][columnIndex] == '*')
            return 0;

        if (array[rowIndex][columnIndex] == '.')
        {
            long sum = 0;
            // Switch to * is affected if memoryArray[row][column] = 0
            // Add to sum if memoryArray[row][column] != 0

            if (!blockedLocations.contains(rowIndex - 1 + "_" + columnIndex))
            {
                if (memoryArray[rowIndex - 1][columnIndex] == 0)
                    return 0;

                sum += memoryArray[rowIndex - 1][columnIndex];
            }

            if (!blockedLocations.contains(rowIndex + "_" + (columnIndex - 1)))
            {
                sum += memoryArray[rowIndex][columnIndex - 1];
            }

            array[rowIndex][columnIndex] = '*';

            if (rowIndex == 0 && columnIndex == 0)
                sum = 1;

//            System.out.println("Sum: " + sum);
            memoryArray[rowIndex][columnIndex] = sum;
        }

        if (rowIndex == array.length - 1 && columnIndex == array[0].length - 1)
            return 1;

        if (!blockedLocations.contains(rowIndex + 1 + "_" + columnIndex))
            recurseSearch(array, rowIndex + 1, columnIndex, memoryArray);
        if (!blockedLocations.contains(rowIndex + "_" + (columnIndex + 1)))
            recurseSearch(array, rowIndex, columnIndex + 1, memoryArray);

        return memoryArray[memoryArray.length - 1][memoryArray[0].length - 1];
    }

    public static boolean recurseSearchWithGodPowers(char[][] array, int rowIndex, int columnIndex)
    {
//        System.out.println();
//        System.out.println(rowIndex + 1 + " " + (columnIndex + 1));
//        printArray(array);

        if (array[rowIndex][columnIndex] == '*' || array[rowIndex][columnIndex] == '.')
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
