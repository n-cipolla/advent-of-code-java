package main.java.adventofcode.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> readLines(File resource) {
        List<String> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    public static ArrayList<Integer> stringToIntArrayList(List<String> input) throws NumberFormatException {
        ArrayList<Integer> ret = new ArrayList<>();
        try {
            for(String s : input) {
                ret.add(Integer.parseInt(s));
            }
        } catch (NumberFormatException e) {throw new NumberFormatException(e.getMessage());}

        return ret;
    }

    public static List<String> realOrTest(File real, File test) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter [t] for testing or [r] for real input: ");
        String input = scanner.nextLine();
        if(input.equals("t")) {
            return readLines(test);
        } else if (input.equals("r")) {
            return readLines(real);
        } else throw new IllegalArgumentException("Enter only [t] or [r].");
    }

    public static ArrayList<ArrayList<Integer>> powerset(ArrayList<Integer> input) {
        var ret = new ArrayList<ArrayList<Integer>>();
        int n = input.size();
        double size = Math.pow(2, input.size());

        for(int i = 0; i < (1 << n); ++i) {
            ArrayList<Integer> subset = new ArrayList<>();
            for(int j = 0; j < n; ++j) {
                if((i & (1 << j)) > 0) {
                    subset.add(input.get(j));
                }
            }
            ret.add(subset);
        }
        return ret;
    }

    public static void printGrid(char[][] input, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printGrid(char[][] input) {
        int size = input.length;
        printGrid(input, size);
    }

    public static void printRectGrid(int[][] input) {
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static ArrayList<Integer> fourNeighbours(int[][] input, int row, int col) {
        ArrayList<Integer> ret = new ArrayList<>();
        try {ret.add(input[row-1][col]);} catch (IndexOutOfBoundsException ignored) {}
        try {ret.add(input[row+1][col]);} catch (IndexOutOfBoundsException ignored) {}
        try {ret.add(input[row][col-1]);} catch (IndexOutOfBoundsException ignored) {}
        try {ret.add(input[row][col+1]);} catch (IndexOutOfBoundsException ignored) {}
        return ret;
    }

    public static ArrayList<Integer> eightNeighbours(int[][] input, int row, int col) {
        ArrayList<Integer> ret = fourNeighbours(input, row, col);
        try {ret.add(input[row-1][col-1]);} catch (IndexOutOfBoundsException ignored) {}
        try {ret.add(input[row-1][col+1]);} catch (IndexOutOfBoundsException ignored) {}
        try {ret.add(input[row+1][col-1]);} catch (IndexOutOfBoundsException ignored) {}
        try {ret.add(input[row+1][col+1]);} catch (IndexOutOfBoundsException ignored) {}
        return ret;
    }

    public static int[][] deepCopy(int[][] original) {
        if (original == null) return null;

        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone(); // Clone each row to ensure a deep copy
        }
        return copy;
    }

    public static void printGrid(int[][] input, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printGrid(int[][] input) {
        int size = input.length;
        printGrid(input, size);
    }

    public static void printGridNoZeroes(int[][] input) {
        int size = input.length;
        printGrid(input, size);
    }

    /**
     * Instantiates a 2x2 array of the filler character
     *
     * @param numRows the number of rows; e.g., arr[rows][]
     * @param numCols the number of columns; e.g., arr[][cols]
     * @param filler the character used to fill the grid by default
     * @return a 2x2 array of size {@code numRows} x {@code numCols} populated with the {@code filler} character.
     */
    public static char[][] makeGridOfChars(int numRows, int numCols, char filler) {
        char[][] ret = new char[numRows][numCols];
        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numCols; ++j) {
                ret[i][j] = filler;
            }
        }
        return ret;
    }

    /**
     * Instantiates a 2x2 array of the filler character
     *
     * @param numRows the number of rows; e.g., arr[rows][]
     * @param numCols the number of columns; e.g., arr[][cols]
     * @param filler the integer used to fill the grid by default
     * @return a 2x2 array of size {@code numRows} x {@code numCols} populated with the {@code filler} number.
     */
    public static int[][] makeGridOfInts(int numRows, int numCols, int filler) {
        int[][] ret = new int[numRows][numCols];
        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numCols; ++j) {
                ret[i][j] = filler;
            }
        }
        return ret;
    }
}
