package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;

public class Day17 extends Day2021 {
    public Day17() {
        super(17);
    }

    public static void main(String[] args) {
        new Day17().printParts();
    }

    @Override
    public Object part1() {
        File input = Day17.getResource("day17.txt", 2021);
        File test = Day17.getResource("test17.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        return 2021;
    }

    @Override
    public Object part2() {
//        File input = Day05.getResource("day05.txt", 2021);
//        File test = Day05.getResource("test05.txt", 2021);
//
//        var lines = Utils.realOrTest(input, test);
        return 2021;
    }
}