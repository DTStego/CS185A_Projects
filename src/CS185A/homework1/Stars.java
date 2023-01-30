package CS185A.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Stars
{
    /**
     * Program accepts a grid of '#' and '-' characters. '-' indicates character is a star or a part of a larger star.
     * The first line of input will contain the row and column amounts separated by a space. The objective is to find
     * the amount of stars in the given input. Note that you can chain two different grids, separated by a row column
     * declaration.
     *
     * Example input:
     * 10 20
     * #################---
     * ##-###############--
     * #---################
     * ##-#################
     * ########---#########
     * #######-----########
     * ########---#########
     * ##################--
     * #################---
     * ##################-#
     * 3 10
     * #-########
     * ----------
     * #-########
     *
     * Output:
     * Case 1: 4
     * Case 2: 1
     */
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        int currentCase = 1;

        // Read the row column information and create a char 2d array to store the grid.
        while ((input = br.readLine()) != null)
        {
            if (input.equals("")) return;
            int row = Integer.parseInt(input.split(" ")[0]);
            int column = Integer.parseInt(input.split(" ")[1]);

            char[][] array = new char[row][column];

            for (int i = 0; i < row; i++)
            {
                if (column == 0) { break; }

                array[i] = br.readLine().toCharArray();
            }

            System.out.println("Case " + currentCase + ": " + countStars(array));
            currentCase++;
        }
    }

    /**
     * @param space The character 2D array that contains stars ('-') and empty space ('#').
     * @return The amount of stars in the 2D array.
     */
    public static int countStars(char[][] space)
    {
        int starCount = 0;

        // Iterate through the grid and increase starCount when a star is found. Use recursion to consume star.
        for (int i = 0; i < space.length; i++)
        {
            for (int j = 0; j < space[i].length; j++)
            {
                if (space[i][j] == '-')
                {
                    starCount++;
                    recurseConsumeStar(space, i, j);
                }
            }
        }

        return starCount;
    }

    /**
     * Consume the star given the position of a star (reached iteratively) by changing surrounding star elements to '#'.
     * @param space The character 2D array that contains stars ('-') and empty space ('#').
     * @param rowIndex The current row that the pointer is at.
     * @param columnIndex The current index that the pinter is at.
     */
    public static void recurseConsumeStar(char[][] space, int rowIndex, int columnIndex)
    {
        // Exit condition if recursion parameters are out of bounds of the 2D array.
        if (rowIndex < 0 || columnIndex < 0 || rowIndex >= space.length || columnIndex >= space[0].length)
            return;

        // Ignore empty space.
        if (space[rowIndex][columnIndex] == '#')
            return;

        // Consume the star element by replacing it with '#'. The star is already counted in countStars().
        if (space[rowIndex][columnIndex] == '-')
            space[rowIndex][columnIndex] = '#';

        /*
         * Check for other star elements down, left, and right of the current star element.
         * Since the first star element (from countStars()) was found by iterating through the 2D array
         * left to right, we can ignore going up since it would've been reached earlier.
         */
        recurseConsumeStar(space, rowIndex + 1, columnIndex);
        recurseConsumeStar(space, rowIndex, columnIndex - 1);
        recurseConsumeStar(space, rowIndex, columnIndex + 1);
    }

    public static void printArray(char[][] array)
    {
        for (char[] chars : array)
        {
            System.out.println(Arrays.toString(chars));
        }
    }
}
