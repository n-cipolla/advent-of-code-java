package main.java.adventofcode.year24.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year24.Day2024;

import java.io.File;
import java.util.ArrayList;

public class Day06 extends Day2024 {
    public Day06() {
        super(6);
    }

    public static void main(String[] args) {
        new Day06().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day06.txt", 2024);
        File test = Day01.getResource("test06.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        int rows = lines.size();
        int cols = lines.get(0).length();

        var grid = makeGrid(rows, cols, '.');

        // (0,0) is top left
        int guardDown = 0;
        int guardAcross = 0;

        for (int j = 0; j < lines.size(); j++) {
            for (int i = 0; i < lines.get(j).length(); i++) {
                if (lines.get(i).charAt(j) != '.') {
                    grid[i][j] = lines.get(i).charAt(j);
                    if (grid[i][j] == '^') {
                        guardDown = i;
                        guardAcross = j;
                    }
                }
            }
        }

        // grid[down][across]
        // where 0,0 is top-left
        // where 1,0 is below top-left
        // where 0,1 is to the right of top-left

        try {
            while(true) {
                if (grid[guardDown][guardAcross] == '^') {
                    // Facing UP
                    char above = grid[guardDown-1][guardAcross];
                    if (above != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardDown--;
                        grid[guardDown][guardAcross] = '^';
                    } else {
                        grid[guardDown][guardAcross] = '>';
                    }

                } else if (grid[guardDown][guardAcross] == '>') {
                    // Facing RIGHT
                    char right = grid[guardDown][guardAcross+1];
                    if(right != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardAcross++;
                        grid[guardDown][guardAcross] = '>';
                    } else {
                        grid[guardDown][guardAcross] = 'v';
                    }

                } else if (grid[guardDown][guardAcross] == 'v') {
                    // Facing DOWN
                    char down = grid[guardDown+1][guardAcross];
                    if (down != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardDown++;
                        grid[guardDown][guardAcross] = 'v';
                    } else {
                        grid[guardDown][guardAcross] = '<';
                    }
                }

                else if (grid[guardDown][guardAcross] == '<') {
                    // Facing LEFT
                    char left = grid[guardDown][guardAcross - 1];
                    if(left != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardAcross--;
                        grid[guardDown][guardAcross] = '<';
                    } else {
                        grid[guardDown][guardAcross] = '^';
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // do nothing, keep going - this is supposed to happen
        }

        int count = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '^' || grid[i][j] == '>' || grid[i][j] == 'v' || grid[i][j] == '<' || grid[i][j] == 'X') {
                    count++;
                }
            }
        }

        return count;

    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day06.txt", 2024);
        File test = Day01.getResource("test06.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        int rows = lines.size();
        int cols = lines.get(0).length();

        var grid = makeGrid(rows, cols, '.');

        // (0,0) is top left
        int guardDown = 0;
        int guardAcross = 0;

        for (int j = 0; j < lines.size(); j++) {
            for (int i = 0; i < lines.get(j).length(); i++) {
                if (lines.get(i).charAt(j) != '.') {
                    grid[i][j] = lines.get(i).charAt(j);
                    if (grid[i][j] == '^') {
                        guardDown = i;
                        guardAcross = j;
                    }
                }
            }
        }

        var cloneDown = guardDown;
        var cloneAcross = guardAcross;

        var freshCopy = copyArray(grid);

        // grid[down][across]
        // where 0,0 is top-left
        // where 1,0 is below top-left
        // where 0,1 is to the right of top-left

        try {
            while(true) {
                if (grid[guardDown][guardAcross] == '^') {
                    // Facing UP
                    char above = grid[guardDown-1][guardAcross];
                    if (above != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardDown--;
                        grid[guardDown][guardAcross] = '^';
                    } else {
                        grid[guardDown][guardAcross] = '>';
                    }

                } else if (grid[guardDown][guardAcross] == '>') {
                    // Facing RIGHT
                    char right = grid[guardDown][guardAcross+1];
                    if(right != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardAcross++;
                        grid[guardDown][guardAcross] = '>';
                    } else {
                        grid[guardDown][guardAcross] = 'v';
                    }

                } else if (grid[guardDown][guardAcross] == 'v') {
                    // Facing DOWN
                    char down = grid[guardDown+1][guardAcross];
                    if (down != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardDown++;
                        grid[guardDown][guardAcross] = 'v';
                    } else {
                        grid[guardDown][guardAcross] = '<';
                    }
                }

                else if (grid[guardDown][guardAcross] == '<') {
                    // Facing LEFT
                    char left = grid[guardDown][guardAcross - 1];
                    if(left != '#') {
                        grid[guardDown][guardAcross] = 'X';
                        guardAcross--;
                        grid[guardDown][guardAcross] = '<';
                    } else {
                        grid[guardDown][guardAcross] = '^';
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // do nothing, keep going - this is supposed to happen
        }


        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '^' || grid[i][j] == '>' || grid[i][j] == 'v' || grid[i][j] == '<' || grid[i][j] == 'X') {
                    count++;
                    var tmp = new ArrayList<Integer>();
                    tmp.add(i);
                    tmp.add(j);
                    visited.add(tmp);
                }
            }
        }

        // removes the start point
        visited.remove(buildAL(cloneDown, cloneAcross));


        count = 0;
        // Part 2
        // Valid Obstacles at
        // 6,3
        // 7,6
        // 7,7
        // 8,1
        // 8,3
        // 9,7

        for(ArrayList<Integer> coord : visited) {
            int downCoord = coord.get(0);
            int acrossCoord = coord.get(1);

            var testGrid = copyArray(freshCopy);
            guardDown = cloneDown;
            guardAcross = cloneAcross;

            testGrid[downCoord][acrossCoord] = '#';
            //TODO fix coordinate checking...checking for a loop can be done by seeing if you've visited that spot in that direction - my implementation is broken still :(
            try {
//                System.out.println("Checking Obstacle at (" + downCoord + "," + acrossCoord + ")");
//                System.out.println(grid[downCoord][acrossCoord]);
                ArrayList<Triple> newVisited = new ArrayList<>();
                while(true) {
                    char facing = grid[guardDown][guardAcross];

                    if(newVisited.contains(new Triple(guardDown, guardAcross, facing))) {
                        count++;
                        System.out.println("Valid Obstacle at (" + downCoord + "," + acrossCoord + ")" );
                        break;
                    }

                    if (testGrid[guardDown][guardAcross] == '^') {
                        // Facing UP
                        char above = testGrid[guardDown-1][guardAcross];
                        if (above != '#') {
                            testGrid[guardDown][guardAcross] = 'X';
                            newVisited.add(new Triple(guardDown, guardAcross, facing));
                            guardDown--;
                            testGrid[guardDown][guardAcross] = '^';
                        } else {
                            testGrid[guardDown][guardAcross] = '>';
                        }

                    } else if (testGrid[guardDown][guardAcross] == '>') {
                        // Facing RIGHT
                        char right = testGrid[guardDown][guardAcross+1];
                        if(right != '#') {
                            testGrid[guardDown][guardAcross] = 'X';
                            newVisited.add(new Triple(guardDown, guardAcross, facing));
                            guardAcross++;
                            testGrid[guardDown][guardAcross] = '>';
                        } else {
                            testGrid[guardDown][guardAcross] = 'v';
                        }

                    } else if (testGrid[guardDown][guardAcross] == 'v') {
                        // Facing DOWN
                        char down = testGrid[guardDown+1][guardAcross];
                        if (down != '#') {
                            testGrid[guardDown][guardAcross] = 'X';
                            newVisited.add(new Triple(guardDown, guardAcross, facing));
                            guardDown++;
                            testGrid[guardDown][guardAcross] = 'v';
                        } else {
                            testGrid[guardDown][guardAcross] = '<';
                        }
                    }

                    else if (testGrid[guardDown][guardAcross] == '<') {
                        // Facing LEFT
                        char left = testGrid[guardDown][guardAcross - 1];
                        if(left != '#') {
                            testGrid[guardDown][guardAcross] = 'X';
                            newVisited.add(new Triple(guardDown, guardAcross, facing));
                            guardAcross--;
                            testGrid[guardDown][guardAcross] = '<';
                        } else {
                            testGrid[guardDown][guardAcross] = '^';
                        }
                    }
//                    printGrid(grid);
//                    System.out.println();
                }
            } catch (IndexOutOfBoundsException e) {
                // do nothing, keep going - this is supposed to happen
            }
        }

        return "Not implemented.";
    }

    public static record Triple(int first, int second, char third) {}

    public static ArrayList<Integer> buildAL(int a, int b) {
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(a);
        ret.add(b);
        return ret;
    }

    public static char[][] makeGrid(int rows, int cols, char filler) {
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = filler;
            }
        }
        return grid;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static char[][] copyArray(char[][] a) {
        var ret = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                ret[i][j] = a[i][j];
            }
        }
        return ret;
    }
}