package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Day06 extends Day2015 {
    public Day06() {
        super(6);
    }

    public static void main(String[] args) {
        new Day06().printParts();
    }

    @Override
    public Object part1() {
        File input = Day06.getResource("day06.txt", 2015);
        File test = Day06.getResource("test06.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        final int GRID_SIZE = 1000;
        var lights = makeGridOff(GRID_SIZE, GRID_SIZE);

        for(String s : lines) {
            parse1(lights, s);
        }

        int on = 0;
        for(int i = 0; i < lights.length; ++i) {
            for(int j = 0; j < lights.length; ++j) {
                if(lights[i][j] > 0) {on++;}
            }
        }
        return on;
    }

    @Override
    public Object part2() {
        File input = Day06.getResource("day06.txt", 2015);
        File test = Day06.getResource("test06.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        final int GRID_SIZE = 1000;
        var lights = makeGridOff(GRID_SIZE, GRID_SIZE);

        for(String s : lines) {
            parse2(lights, s);
        }

        int bright = 0;
        for(int i = 0; i < lights.length; ++i) {
            for(int j = 0; j < lights.length; ++j) {
                bright += lights[i][j];
            }
        }

        return bright;
    }

    private static int[][] makeGridOff(int length, int height) {
        var ret = new int[length][height];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                ret[i][j] = 0;
            }
        }
        return ret;
    }

    private static void parse1(int[][] lights, String message) {
        var arr = message.split("[,\\s]+");
        ArrayList<String> words = new ArrayList<>();
        Collections.addAll(words, arr);

        if (words.contains("turn")) {
            int xStart = Integer.parseInt(words.get(2));
            int xStop = Integer.parseInt(words.get(5)) + 1;
            int yStart = Integer.parseInt(words.get(3));
            int yStop = Integer.parseInt(words.get(6)) + 1;
            if (words.contains("on")) {
                for (int i = xStart; i < xStop; ++i) {
                    for (int j = yStart; j < yStop; ++j) {
                        lights[i][j] = 1;
                    }
                }
            } else if (words.contains("off")) {
                for (int i = xStart; i < xStop; ++i) {
                    for (int j = yStart; j < yStop; ++j) {
                        lights[i][j] = 0;
                    }
                }
            }
        } else if (words.contains("toggle")) {
            int xStart = Integer.parseInt(words.get(1));
            int xStop = Integer.parseInt(words.get(4)) + 1;
            int yStart = Integer.parseInt(words.get(2));
            int yStop = Integer.parseInt(words.get(5)) + 1;
            for (int i = xStart; i < xStop; ++i) {
                for (int j = yStart; j < yStop; ++j) {
                    if(lights[i][j] == 0) lights[i][j] = 1;
                    else lights[i][j] = 0;
                }
            }
        }
    }

    private static void parse2(int[][] lights, String message) {
        var arr = message.split("[,\\s]+");
        ArrayList<String> words = new ArrayList<>();
        Collections.addAll(words, arr);

        if (words.contains("turn")) {
            int xStart = Integer.parseInt(words.get(2));
            int xStop = Integer.parseInt(words.get(5)) + 1;
            int yStart = Integer.parseInt(words.get(3));
            int yStop = Integer.parseInt(words.get(6)) + 1;
            if (words.contains("on")) {
                for (int i = xStart; i < xStop; ++i) {
                    for (int j = yStart; j < yStop; ++j) {
                        lights[i][j] += 1;
                    }
                }
            } else if (words.contains("off")) {
                for (int i = xStart; i < xStop; ++i) {
                    for (int j = yStart; j < yStop; ++j) {
                        if(lights[i][j] != 0) lights[i][j] -= 1;
                    }
                }
            }
        } else if (words.contains("toggle")) {
            int xStart = Integer.parseInt(words.get(1));
            int xStop = Integer.parseInt(words.get(4)) + 1;
            int yStart = Integer.parseInt(words.get(2));
            int yStop = Integer.parseInt(words.get(5)) + 1;
            for (int i = xStart; i < xStop; ++i) {
                for (int j = yStart; j < yStop; ++j) {
                    lights[i][j] += 2;
                }
            }
        }
    }
}