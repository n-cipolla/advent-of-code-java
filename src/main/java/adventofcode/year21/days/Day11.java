package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;

public class Day11 extends Day2021 {
    public Day11() {
        super(11);
    }

    public static void main(String[] args) {
        new Day11().printParts();
    }

    @Override
    public Object part1() {
        File input = Day11.getResource("day11.txt", 2021);
        File test = Day11.getResource("test11.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        int rows = lines.size();
        int cols = lines.get(0).length();

        int[][] grid = Utils.makeGridOfInts(rows, cols, 0);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = Integer.parseInt(String.valueOf(lines.get(row).charAt(col)));
            }
        }


        int flashes = 0;

        for(int i = 0; i < 100; i++) {
            increaseAll(grid);
            for(int row = 0; row < rows; row++) {
                for(int col = 0; col < cols; col++) {
                    flashes += (flash(grid, row, col));
                }
            }
            setNegOnesToZeros(grid);
        }

        return flashes;
    }

    private static void setNegOnesToZeros(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == -1) grid[i][j] = 0;
                if(grid[i][j] > 9) grid[i][j] = 0;
            }
        }
    }

    private static void increaseAll(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[i][j]++;
            }
        }
    }

    // goal is to flash all nums greater than 9
    private static int flash(int[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            // If out of bounds, exit
            return 0;
        }

        // If the cell is 10, flash it
        if (grid[row][col] >= 10) {
            // Mark the cell as flashed (use -1 as the flash indicator)
            grid[row][col] = -1;
            int counter = 1;  // This cell has flashed

            // Increment neighbors
            incrementNeighbor(grid, row - 1, col + 1); // Top-right
            incrementNeighbor(grid, row - 1, col - 1); // Top-left
            incrementNeighbor(grid, row + 1, col + 1); // Bottom-right
            incrementNeighbor(grid, row + 1, col - 1); // Bottom-left
            incrementNeighbor(grid, row - 1, col);     // Top
            incrementNeighbor(grid, row + 1, col);     // Bottom
            incrementNeighbor(grid, row, col - 1);     // Left
            incrementNeighbor(grid, row, col + 1);     // Right

            // Recursively check the neighbors
            counter += flash(grid, row - 1, col + 1); // Top-right
            counter += flash(grid, row - 1, col - 1); // Top-left
            counter += flash(grid, row + 1, col + 1); // Bottom-right
            counter += flash(grid, row + 1, col - 1); // Bottom-left
            counter += flash(grid, row - 1, col);     // Top
            counter += flash(grid, row + 1, col);     // Bottom
            counter += flash(grid, row, col - 1);     // Left
            counter += flash(grid, row, col + 1);     // Right

            return counter;
        }

        // Return 0 if the cell is not 9 and doesn't flash
        return 0;
    }

    // Increment a single neighbor, ensuring no flashing if it's already flashed
    private static void incrementNeighbor(int[][] grid, int row, int col) {
        if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length) {
            if (grid[row][col] != -1) { // Only increment if not already flashed
                grid[row][col]++;
            }
        }
    }

    @Override
    public Object part2() {
        File input = Day11.getResource("day11.txt", 2021);
        File test = Day11.getResource("test11.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        int rows = lines.size();
        int cols = lines.get(0).length();

        int[][] grid = Utils.makeGridOfInts(rows, cols, 0);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = Integer.parseInt(String.valueOf(lines.get(row).charAt(col)));
            }
        }


        int index = 0;

        while(true) {
            index++;
            increaseAll(grid);
            for(int row = 0; row < rows; row++) {
                for(int col = 0; col < cols; col++) {
                    int change = flash(grid,row,col);
                    if(change == 100) return index;
                }
            }
            setNegOnesToZeros(grid);
        }
    }
}