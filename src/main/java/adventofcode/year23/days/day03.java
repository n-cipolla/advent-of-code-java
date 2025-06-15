package main.java.adventofcode.year23.days;

import com.sun.security.jgss.GSSUtil;
import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year23.Day2023;

import java.io.File;
import java.util.*;

public class day03 extends Day2023 {
    public day03() {
        super(3);
    }

    public static void main(String[] args) {
        new day03().printParts();
    }

    private static String[][] makeStringGrid(int rows, int cols) {
        String[][] grid = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = "_";
            }
        }
        return grid;
    }

    private static void printGrid(String[][] input) {
        for(int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public Object part1() {
        File in = day03.getResource("day03.txt", 2023);
        File test = day03.getResource("test03.txt", 2023);

        var input = Utils.realOrTest(in, test);

        int sum = 0;

        String[][] grid = makeStringGrid(input.size(), input.get(0).length());

        // grid[rowIndex][columnIndex]
        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) == '.') {
                    grid[i][j] = ".";
                } else if(String.valueOf(input.get(i).charAt(j)).matches("[0-9]")) {
                    grid[i][j] = String.valueOf(getEntireNumber(input, i, j));
                }
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j].equals("_")) {
                    // Gets neighbors and their positions
                    // 1 2 3
                    // 4 * 5
                    // 6 7 8
                    HashMap<Integer, String> strNeighbors = neighboursWithLocations(grid, i, j);

                    // Replaces .'s with 0's
                    for(Integer key : strNeighbors.keySet()) {
                        if(strNeighbors.get(key).equals(".")) {strNeighbors.put(key, "0");}
                    }

                    // Finds the sum, accounting for repeating numbers
                    int topSum;
                    int midSum = Integer.parseInt(strNeighbors.get(4)) + Integer.parseInt(strNeighbors.get(5));
                    int botSum;

                    if(strNeighbors.get(2).equals("0")) {
                        topSum = Integer.parseInt(strNeighbors.get(1)) + Integer.parseInt(strNeighbors.get(3));
                    } else {
                        topSum = Integer.parseInt(strNeighbors.get(2));
                    }

                    if(strNeighbors.get(7).equals("0")) {
                        botSum = Integer.parseInt(strNeighbors.get(6)) + Integer.parseInt(strNeighbors.get(8));
                    } else {
                        botSum = Integer.parseInt(strNeighbors.get(7));
                    }

                    sum += topSum + midSum + botSum;
                }
            }
        }

        return sum;
    }

    private static String getEntireNumber(List<String> input, int row, int col) {
        String workingRow = input.get(row);
        String num = String.valueOf(workingRow.charAt(col));
        int originalCol = col;
        while(col-1 >= 0 && Character.isDigit(workingRow.charAt(col-1))) {
            col--;
            num = workingRow.charAt(col) + num;
        }
        while(originalCol+1 < workingRow.length() && Character.isDigit(workingRow.charAt(originalCol+1))) {
            originalCol++;
            num = num + workingRow.charAt(originalCol);
        }
        return num;
    }

    private static HashMap<Integer, String> neighboursWithLocations(String[][] grid, int row, int col) {
        HashMap<Integer, String> neighbours = new HashMap<>();

        try {neighbours.put(1, grid[row-1][col-1]);} catch (IndexOutOfBoundsException e) {neighbours.put(1, "0");}
        try {neighbours.put(2, grid[row-1][col]);} catch (IndexOutOfBoundsException e) {neighbours.put(2, "0");}
        try {neighbours.put(3, grid[row-1][col+1]);} catch (IndexOutOfBoundsException e) {neighbours.put(3, "0");}

        try {neighbours.put(4, grid[row][col-1]);} catch (IndexOutOfBoundsException e) {neighbours.put(4, "0");}
        try {neighbours.put(5, grid[row][col+1]);} catch (IndexOutOfBoundsException e) {neighbours.put(5, "0");}

        try {neighbours.put(6, grid[row+1][col-1]);} catch (IndexOutOfBoundsException e) {neighbours.put(6, "0");}
        try {neighbours.put(7, grid[row+1][col]);} catch (IndexOutOfBoundsException e) {neighbours.put(7, "0");}
        try {neighbours.put(8, grid[row+1][col+1]);} catch (IndexOutOfBoundsException e) {neighbours.put(8, "0");}

        return neighbours;
    }

    @Override
    public Object part2() {
        File in = day03.getResource("day03.txt", 2023);
        File test = day03.getResource("test03.txt", 2023);

        var input = Utils.realOrTest(in, test);
        int sum = 0;

        String[][] grid = makeStringGrid(input.size(), input.get(0).length());

        // grid[rowIndex][columnIndex]
        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) == '.') {
                    grid[i][j] = ".";
                } else if(String.valueOf(input.get(i).charAt(j)).matches("[0-9]")) {
                    grid[i][j] = String.valueOf(getEntireNumber(input, i, j));
                } else {
                    grid[i][j] = String.valueOf(input.get(i).charAt(j));
                }
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j].equals("*")) {
                    // Gets neighbors and their positions
                    // 1 2 3
                    // 4 * 5
                    // 6 7 8
                    HashMap<Integer, String> strNeighbors = neighboursWithLocations(grid, i, j);

                    // Replaces .'s with 0's
                    for(Integer key : strNeighbors.keySet()) {
                        if(strNeighbors.get(key).equals(".")) {strNeighbors.put(key, "0");}
                    }

                    // Replaces strings with numbers because im losing my mind
                    HashMap<Integer, Integer> intNeigh = new HashMap<>();
                    for(Integer key : strNeighbors.keySet()) {
                        intNeigh.put(key, Integer.parseInt(strNeighbors.get(key)));
                    }

                    int neighCount = 0;
                    int first = intNeigh.get(1);
                    int second = intNeigh.get(2);
                    int third = intNeigh.get(3);
                    int fourth = intNeigh.get(4);
                    int fifth = intNeigh.get(5);
                    int sixth = intNeigh.get(6);
                    int seventh = intNeigh.get(7);
                    int eighth = intNeigh.get(8);

                    if(second == 0) {
                        if(first != 0 && first != third) neighCount++;
                        if(third != 0) neighCount++;
                    }

                    if(second != 0) {
                        neighCount++;
                    }

                    if(fourth != 0) neighCount++;
                    if(fifth != 0) neighCount++;

                    if(seventh == 0) {
                        if(sixth != 0 && sixth != eighth) neighCount++;
                        if(eighth != 0) neighCount++;
                    }

                    if(seventh != 0) {
                        neighCount++;
                    }

                    if(neighCount == 2) {
                        HashMap<Integer, Integer> counts = new HashMap<>();
                        for(Integer value : intNeigh.values()) {
                            if(counts.containsKey(value) && value != 0) {
                                counts.put(value, counts.get(value)+1);
                            } else if (value != 0) {
                                counts.put(value, 1);
                            }
                        }
                        if(counts.size() == 2) {
                            sum += product(counts);
                        }
                        else if (counts.size() == 1) {
                            sum += square(counts);
                        }
                    }
                }
            }
        }
        return sum;
    }

    private static int product(HashMap<Integer, Integer> map) {
        int prod = 1;
        for(Integer key : map.keySet()) {
            prod *= key;
        }
        return prod;
    }

    private static int square(HashMap<Integer, Integer> map) {
        int square = 1;
        for(Integer key : map.keySet()) {
            square *= key * key;
        }
        return square;
    }
}



// 72115466 too low