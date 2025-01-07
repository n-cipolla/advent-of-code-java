package main.java.adventofcode.year21.days;

import jdk.jshell.execution.Util;
import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.nio.file.Paths;

public class Day05 extends Day2021 {
    public Day05() {
        super(5);
    }

    public static void main(String[] args) {
        new Day05().printParts();
    }

    @Override
    public Object part1() {
        File input = Day05.getResource("day05.txt", 2021);
        File test = Day05.getResource("test05.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        int maxX = 0;
        int maxY = 0;

        for(String line : lines) {
            String[] leftRight = line.split(" -> ");
            int leftX = Integer.parseInt(leftRight[0].split(",")[0]);
            int rightX = Integer.parseInt(leftRight[1].split(",")[0]);
            int leftY = Integer.parseInt(leftRight[0].split(",")[1]);
            int rightY = Integer.parseInt(leftRight[1].split(",")[1]);

            if(leftX > maxX) maxX = leftX;
            if(rightX > maxX) maxX = rightX;
            if(leftY > maxY) maxY = leftY;
            if(rightY > maxY) maxY = rightY;
        }

        int[][] grid = Utils.makeGridOfInts(maxY+1, maxX+1, 0);

        for(String line : lines) {
            String[] leftRight = line.split(" -> ");
            int leftX = Integer.parseInt(leftRight[0].split(",")[0]);
            int rightX = Integer.parseInt(leftRight[1].split(",")[0]);
            int leftY = Integer.parseInt(leftRight[0].split(",")[1]);
            int rightY = Integer.parseInt(leftRight[1].split(",")[1]);

            if (leftX == rightX) {
                // Vertical Line
                if(leftY < rightY) {
                    for(int i = leftY; i <= rightY; i++) {
                        grid[i][leftX] = grid[i][leftX] + 1;
                    }
                } else if (rightY < leftY) {
                    for(int i = rightY; i <= leftY; i++) {
                        grid[i][leftX] = grid[i][leftX] + 1;
                    }
                }
            } else if (leftY == rightY) {
                // Horizontal Line
                if(leftX < rightX) {
                    for(int i = leftX; i <= rightX; i++) {
                        grid[leftY][i] = grid[leftY][i] + 1;
                    }
                } else if(rightX < leftX) {
                    for(int i = rightX; i <= leftX; i++) {
                        grid[leftY][i] = grid[leftY][i] + 1;
                    }
                }
            } else {
                continue;
            }
        }

        int total = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] >= 2) total++;
            }
        }

        return total;
    }

    @Override
    public Object part2() {
        File input = Day05.getResource("day05.txt", 2021);
        File test = Day05.getResource("test05.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        int maxX = 0;
        int maxY = 0;

        for(String line : lines) {
            String[] leftRight = line.split(" -> ");
            int leftX = Integer.parseInt(leftRight[0].split(",")[0]);
            int rightX = Integer.parseInt(leftRight[1].split(",")[0]);
            int leftY = Integer.parseInt(leftRight[0].split(",")[1]);
            int rightY = Integer.parseInt(leftRight[1].split(",")[1]);

            if(leftX > maxX) maxX = leftX;
            if(rightX > maxX) maxX = rightX;
            if(leftY > maxY) maxY = leftY;
            if(rightY > maxY) maxY = rightY;
        }

        int[][] grid = Utils.makeGridOfInts(maxY+1, maxX+1, 0);

        for(String line : lines) {
            String[] leftRight = line.split(" -> ");
            int leftX = Integer.parseInt(leftRight[0].split(",")[0]);
            int rightX = Integer.parseInt(leftRight[1].split(",")[0]);
            int leftY = Integer.parseInt(leftRight[0].split(",")[1]);
            int rightY = Integer.parseInt(leftRight[1].split(",")[1]);

            if (leftX == rightX) {
                // Vertical Line
                if(leftY < rightY) {
                    for(int i = leftY; i <= rightY; i++) {
                        grid[i][leftX] = grid[i][leftX] + 1;
                    }
                } else if (rightY < leftY) {
                    for(int i = rightY; i <= leftY; i++) {
                        grid[i][leftX] = grid[i][leftX] + 1;
                    }
                }
            } else if (leftY == rightY) {
                // Horizontal Line
                if(leftX < rightX) {
                    for(int i = leftX; i <= rightX; i++) {
                        grid[leftY][i] = grid[leftY][i] + 1;
                    }
                } else if(rightX < leftX) {
                    for(int i = rightX; i <= leftX; i++) {
                        grid[leftY][i] = grid[leftY][i] + 1;
                    }
                }
            } else {
                // Diagonal Lines
                if (leftX < rightX && leftY > rightY) {
                    // This is '/' kind of diagonal from L -> R; B -> T
                    for(int x = leftX, y = leftY; x <= rightX && y >= rightY; x++, y--) {
                        grid[y][x] = grid[y][x]+1;
                    }
                } else if (leftX < rightX && leftY < rightY) {
                    // This is '\' kind of diagonal from L -> R; T -> B
                    for(int x = leftX, y = leftY; x <= rightX && y <= rightY; x++, y++) {
                        grid[y][x] = grid[y][x]+1;
                    }
                } else if (leftX > rightX && leftY < rightY) {
                    // This is a different '/' diagonal (should cover 8,0 -> 0,8)
                    for(int x = leftX, y = leftY; x >= rightX && y <= rightY; x--, y++) {
                        grid[y][x] = grid[y][x]+1;
                    }
                } else if (leftX > rightX && leftY > rightY) {
                    // This is a different '\' diagonal (should cover the case (6,4) -> (2,0))
                    for(int x = leftX, y = leftY; x >= rightX && y >= rightY; x--, y--) {
                        grid[y][x] = grid[y][x]+1;
                    }
                    }
            }
        }

//        Utils.printGridNoZeroes(grid);

        int total = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] >= 2) total++;
            }
        }

        return total;
    }
}