package CS185A.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Robots
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Will use to create an N x N character array.
        String input = br.readLine();

        if (input.equals(""))
            return;

        int n = Integer.parseInt(input);

        char[][] array = new char[n][n];

        for (int i = 0; i < n; i++)
        {
            array[i] = br.readLine().toCharArray();
        }

        int numOfRoutes;

        if ((numOfRoutes = recurseSearch(array, 0, 0)) > 0)
        {
            System.out.println((int) (numOfRoutes % (Math.pow(2, 31) - 1)));
            return;
        }

        printArray(array);

        if (recurseSearchWithGodPowers(array, 0, 0))
        {
            System.out.println("THE GAME IS A LIE");
            return;
        }

        System.out.println("INCONCEIVABLE");
    }

    public static int recurseSearch(char[][] array, int rowIndex, int columnIndex)
    {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex >= array.length || columnIndex >= array[0].length)
            return 0;

        if (array[rowIndex][columnIndex] == '#')
            return 0;

        if (rowIndex == array.length - 1 && columnIndex == array[0].length - 1)
            return 1;


        return recurseSearch(array, rowIndex + 1, columnIndex)
                + recurseSearch(array, rowIndex, columnIndex + 1);
    }

    public static boolean recurseSearchWithGodPowers(char[][] array, int rowIndex, int columnIndex)
    {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex >= array.length || columnIndex >= array[0].length)
            return false;

        if (array[rowIndex][columnIndex] == '#')
            return false;

        if (array[rowIndex][columnIndex] == '.')
            array[rowIndex][columnIndex] = '#';

        if (rowIndex == array.length - 1 && columnIndex == array[0].length - 1)
            return true;

        return recurseSearchWithGodPowers(array, rowIndex + 1, columnIndex)
                || recurseSearchWithGodPowers(array, rowIndex, columnIndex + 1)
                || recurseSearchWithGodPowers(array, rowIndex - 1, columnIndex)
                || recurseSearchWithGodPowers(array, rowIndex, columnIndex - 1);
    }

    public static void printArray(char[][] array)
    {
        for (char[] chars : array)
        {
            System.out.println(Arrays.toString(chars));
        }
    }
}
