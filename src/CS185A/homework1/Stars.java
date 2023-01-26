package CS185A.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Stars
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        int currentCase = 1;

        while ((input = br.readLine()) != null)
        {
            if (input.equals("")) return;
            int row = Integer.parseInt(input.split(" ")[0]);
            int column = Integer.parseInt(input.split(" ")[1]);

            char[][] array = new char[row][column];

            for (int i = 0; i < row; i++)
            {
                array[i] = br.readLine().toCharArray();
            }

            System.out.println("Case " + currentCase + ": " + countStars(array));
            currentCase++;
        }
    }

    public static int countStars(char[][] space)
    {
        int starCount = 0;

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

    public static void recurseConsumeStar(char[][] space, int rowIndex, int columnIndex)
    {
        if (rowIndex < 0 || columnIndex < 0 || rowIndex >= space.length || columnIndex >= space[0].length)
            return;

        if (space[rowIndex][columnIndex] == '#')
            return;

        if (space[rowIndex][columnIndex] == '-')
            space[rowIndex][columnIndex] = '#';

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
