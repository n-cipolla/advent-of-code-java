package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;

public class Day02 extends Day2021 {
    public Day02() {
        super(2);
    }

    public static void main(String[] args) {
        new Day02().printParts();
    }

    @Override
    public Object part1() {
        File input = Day02.getResource("day02.txt", 2021);
        File test = Day02.getResource("test02.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        int depth = 0;
        int horiz = 0;
        for(String line : lines) {
            if(line.contains("forward")) horiz += Integer.parseInt(line.split(" ")[1]);
            else if(line.contains("up")) depth -= Integer.parseInt(line.split(" ")[1]);
            else if(line.contains("down")) depth += Integer.parseInt(line.split(" ")[1]);
        }
        return depth * horiz;
    }

    @Override
    public Object part2() {
        File input = Day02.getResource("day02.txt", 2021);
        File test = Day02.getResource("test02.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        int depth = 0;
        int horiz = 0;
        int aim = 0;
        for(String line : lines) {
            if(line.contains("up")) {
                aim -= Integer.parseInt(line.split(" ")[1]);
            } else if (line.contains("down")) {
                aim += Integer.parseInt(line.split(" ")[1]);
            } else if (line.contains("forward")) {
                horiz += Integer.parseInt(line.split(" ")[1]);
                depth += aim * Integer.parseInt(line.split(" ")[1]);
            }
        }
        return depth * horiz;
    }
}