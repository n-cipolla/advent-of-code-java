package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day15 extends Day2021 {
    public Day15() {
        super(15);
    }

    public static void main(String[] args) {
        new Day15().printParts();
    }

    public record Point(int row, int col) {}

    @Override
    public Object part1() {
        File input = Day15.getResource("day15.txt", 2021);
        File test = Day15.getResource("test15.txt", 2021);


        var lines = Utils.realOrTest(input, test);
        int[][] grid = Utils.makeGridOfInts(lines.size(), lines.get(0).length(), 0);

        for(int row = 0; row < lines.size(); row++) {
            for(int col = 0; col < lines.get(0).length(); col++) {
                grid[row][col] = Integer.parseInt(Character.toString(lines.get(row).charAt(col)));
            }
        }

        HashMap<Point, ArrayList<Integer>> edges = new HashMap<>();
        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[row].length; col++) {
                edges.put(new Point(row, col), new ArrayList<>(Utils.fourNeighbours(grid, row, col)));
            }
        }

        HashSet<ArrayList<Point>> paths = findAllPaths(edges, 0, 0, grid.length, grid[0].length);

        return 2021;
    }

    public static HashSet<ArrayList<Point>> findAllPaths(HashMap<Point, ArrayList<Integer>> edges, int rowStart, int colStart, int rowEnd, int colEnd) {
        HashSet<ArrayList<Point>> allPaths = new HashSet<>();
        ArrayList<Point> currentPath = new ArrayList<>();
        Point start = new Point(rowStart, colStart);
        Point end = new Point(rowEnd, colEnd);

        depthFirstSearch(edges, start, end, allPaths, currentPath);
        return allPaths;
    }

    public static void depthFirstSearch(HashMap<Point, ArrayList<Integer>> edges, Point node, Point end,
                                                    HashSet<ArrayList<Point>> allPaths, ArrayList<Point> currentPath) {
        currentPath.add(node);

        if(node.equals(end)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            if(!currentPath.contains(node)) {
//                depthFirstSearch();
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    @Override
    public Object part2() {
//        File input = Day05.getResource("day15.txt", 2021);
//        File test = Day05.getResource("test15.txt", 2021);
//
//        var lines = Utils.realOrTest(input, test);
        return 2021;
    }
}