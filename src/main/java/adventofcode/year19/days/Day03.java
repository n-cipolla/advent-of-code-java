package main.java.adventofcode.year19.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year19.Day2019;

import java.io.File;
import java.util.ArrayList;

public class Day03 extends Day2019 {
    public Day03() {
        super(3);
    }

    public static void main(String[] args) {
        new Day03().printParts();
    }

    @Override
    public Object part1() {
        File in = Day03.getResource("day03.txt", 2019);
        File test = Day03.getResource("test03.txt", 2019);

        var wires = Utils.realOrTest(in, test);
        for(String wire : wires) {

        }


        return 2019;
    }

    @Override
    public Object part2() {
        File in = Day03.getResource("day03.txt", 2019);
        File test = Day03.getResource("test03.txt", 2019);

        var input = Utils.realOrTest(in, test);
        return 2019;
    }
}