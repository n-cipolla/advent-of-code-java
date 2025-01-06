package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day19 extends Day2015 {
    public Day19() {
        super(19);
    }

    public static void main(String[] args) {
        new Day19().printParts();
    }

    @Override
    public Object part1() {
        File input = Day19.getResource("day19.txt", 2015);
        File compound = Day19.getResource("day19compound.txt", 2015);
        File test = Day19.getResource("test19.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        return 1;
    }

    @Override
    public Object part2() {
        File input = Day19.getResource("day19.txt", 2015);
        File test = Day19.getResource("test19.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        return 1;
    }
}