package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day02 extends Day2015 {
    public Day02() {
        super(2);
    }

    public static void main(String[] args) {
        new Day02().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day02.txt", 2024);
        File test = Day01.getResource("test02.txt", 2024);

        var lines = Utils.realOrTest(input, test);
        return 1;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day02.txt", 2024);
        File test = Day01.getResource("test02.txt", 2024);

        var lines = Utils.realOrTest(input, test);
        return 1;
    }
}