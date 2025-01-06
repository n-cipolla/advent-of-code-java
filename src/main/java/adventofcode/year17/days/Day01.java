package main.java.adventofcode.year17.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year17.Day2017;

import java.io.File;

public class Day01 extends Day2017 {
    public Day01() {
        super(1);
    }

    public static void main(String[] args) {
        new Day01().printParts();
    }

    @Override
    public Object part1() {
        File in = Day01.getResource("Day01.txt", 2017);
        File test = Day01.getResource("Day01Test.txt", 2017);

        var input = Utils.realOrTest(in, test);
        return 2017;
    }

    @Override
    public Object part2() {
        File in = Day01.getResource("Day01.txt", 2017);
        File test = Day01.getResource("Day01Test.txt", 2017);

        var input = Utils.realOrTest(in, test);
        return 2017;
    }
}