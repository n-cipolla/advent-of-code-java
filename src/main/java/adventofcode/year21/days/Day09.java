package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Day09 extends Day2021 {
    public Day09() {
        super(9);
    }

    public static void main(String[] args) {
        new Day09().printParts();
    }

    @Override
    public Object part1() {
        File input = Day09.getResource("day09.txt", 2021);
        File test = Day09.getResource("test09.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        int rows = lines.size();
        int cols = lines.get(0).length();

        int[][] grid = Utils.makeGridOfInts(rows, cols, 0);

        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(0).length(); j++) {
                grid[i][j] = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
            }
        }

        int total = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                var neighbours = Utils.fourNeighbours(grid, i, j);
                if(Collections.min(neighbours) > grid[i][j]) {
//                    System.out.println(neighbours + " | " + grid[i][j]);
                    total += grid[i][j] + 1;
                }
            }
        }

        return total;
    }

    @Override
    public Object part2() {
        File input = Day05.getResource("day09.txt", 2021);
        File test = Day05.getResource("test09.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        int rows = lines.size();
        int cols = lines.get(0).length();

        int[][] grid = Utils.makeGridOfInts(rows, cols, 0);

        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(0).length(); j++) {
                grid[i][j] = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
            }
        }

        ArrayList<Tuple> lowPoints = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                var neighbours = Utils.fourNeighbours(grid, i, j);
                if(Collections.min(neighbours) > grid[i][j]) {
                    lowPoints.add(new Tuple(i, j));
                }
            }
        }

        ArrayList<Integer> poolSize = new ArrayList<>();
        for(Tuple t : lowPoints) {
            var workingGrid = Utils.deepCopy(grid);
            floodFill(workingGrid, t.row, t.col, 9, -1);
            poolSize.add(countNegs(workingGrid));
        }

        Collections.sort(poolSize);
        return poolSize.get(poolSize.size()-1) * poolSize.get(poolSize.size()-2) * poolSize.get(poolSize.size()-3);
    }

    private static void floodFill(int[][] grid, int row, int col, int boundary, int newValue) {
        // Boundary checks
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return; // Out of bounds
        }
        if (grid[row][col] == 9) {
            return; // Hit a boundary
        }
        if (grid[row][col] == newValue) {
            return; // Already filled with the new value
        }

        grid[row][col] = newValue;

        floodFill(grid, row - 1, col, boundary, newValue);  // Up
        floodFill(grid, row + 1, col, boundary, newValue);  // Down
        floodFill(grid, row, col - 1, boundary, newValue);  // Left
        floodFill(grid, row, col + 1, boundary, newValue);  // Right
    }

    private static int countNegs(int[][] grid) {
        int total = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] < 0) {total++;}
            }
        }
        return total;
    }

    private record Tuple(int row, int col) {
        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
}