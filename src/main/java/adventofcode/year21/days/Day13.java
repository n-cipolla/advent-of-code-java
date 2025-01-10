package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;

public class Day13 extends Day2021 {
    public Day13() {
        super(13);
    }

    public static void main(String[] args) {
        new Day13().printParts();
    }

    public record Point(int col, int row) {}

    @Override
    public Object part1() {
        File input = Day13.getResource("day13.txt", 2021);
        File test = Day13.getResource("test13.txt", 2021);

        ArrayList<Point> points = new ArrayList<>();
        var lines = Utils.realOrTest(input, test);
        ArrayList<String> folds = new ArrayList<>();
        boolean seenBreak = false;
        for(String line : lines) {
            if(line.isEmpty()) seenBreak = true;
            if(!seenBreak) {
                points.add(new Point(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1])));
            } else {
                folds.add(line);
            }
        }
        int colMax = 0;
        int rowMax = 0;
        for(Point p : points) {
            if(p.col > colMax) colMax = p.col;
            if(p.row > rowMax) rowMax = p.row;
        }

        colMax++;
        rowMax++;

        char[][] grid = Utils.makeGridOfChars(rowMax, colMax, '.');
        for(Point p : points) {
            grid[p.row][p.col] = '#';
        }

        for(int i = 1; i < 2; ++i) { // use this to fold only along the first fold
//        for(int i = 1; i < folds.size(); i++) {
            String coord = folds.get(i).split(" ")[2];
            String axis = coord.split("=")[0];
            int value = Integer.parseInt(coord.split("=")[1]);
            if(axis.equals("y")) grid = foldHamburger(grid, value);
            if(axis.equals("x")) grid = foldHotdog(grid, value);
        }

        int counter = 0;
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                if(grid[i][j] == '#') counter++;
            }
        }

        return counter;
    }

    // assume a fold of y=7
    // 8,0, -> 6,0
    // 10,1 -> 4,1
    public static char[][] foldHamburger(char[][] grid, int row) {
        char[][] ret = new char[grid.length - (row+1)][grid[0].length];
        // loop through rows of old grid mapping values to new grid
        for(int r = row+1; r < grid.length; r++) {
            for(int col = 0; col < grid[0].length; col++) {
                ret[row - (r - row)][col] = grid[r][col];
            }
        }

        // add the values which were already there
        for(int r = 0; r < row; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == '#') {
                    ret[r][c] = grid[r][c];
                }
            }
        }
        return ret;
    }

    // assume a fold of x=5
    // 0,6 -> 0,4
    public static char[][] foldHotdog(char[][] grid, int col) {
        char[][] ret = new char[grid.length][grid[0].length - (col+1)];
        // loop through columns of old grid, mapping values to new grid
        for(int row = 0; row < grid.length; row++) {
            for(int c = col+1; c < grid[0].length; c++) {
                ret[row][col - (c - col)] = grid[row][c];
            }
        }

        // add the values which were already there
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < col; c++) {
                if(grid[r][c] == '#') {
                    ret[r][c] = grid[r][c];
                }
            }
        }

        return ret;
    }

    @Override
    public Object part2() {
        File input = Day13.getResource("day13.txt", 2021);
        File test = Day13.getResource("test13.txt", 2021);

        ArrayList<Point> points = new ArrayList<>();
        var lines = Utils.realOrTest(input, test);
        ArrayList<String> folds = new ArrayList<>();
        boolean seenBreak = false;
        for(String line : lines) {
            if(line.isEmpty()) seenBreak = true;
            if(!seenBreak) {
                points.add(new Point(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1])));
            } else {
                folds.add(line);
            }
        }
        int colMax = 0;
        int rowMax = 0;
        for(Point p : points) {
            if(p.col > colMax) colMax = p.col;
            if(p.row > rowMax) rowMax = p.row;
        }

        colMax++;
        rowMax++;

        char[][] grid = Utils.makeGridOfChars(rowMax, colMax, '.');
        for(Point p : points) {
            grid[p.row][p.col] = '#';
        }

//        for(int i = 1; i < 2; ++i) { // use this to fold only along the first fold
        for(int i = 1; i < folds.size(); i++) {
            String coord = folds.get(i).split(" ")[2];
            String axis = coord.split("=")[0];
            int value = Integer.parseInt(coord.split("=")[1]);
            if(axis.equals("y")) grid = foldHamburger(grid, value);
            if(axis.equals("x")) grid = foldHotdog(grid, value);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '#') {
                    System.out.print(grid[i][j]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();

//        Utils.printGrid(grid);
        return "see grid.";
    }
}