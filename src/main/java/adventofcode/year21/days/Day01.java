package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;

public class Day01 extends Day2021 {
    public Day01() {
        super(1);
    }

    public static void main(String[] args) {
        new Day01().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day01.txt", 2021);
        File test = Day01.getResource("test01.txt", 2021);

        var linesstring = Utils.realOrTest(input, test);
        ArrayList<Integer> lines = new ArrayList<>();
        for(String s : linesstring) lines.add(Integer.parseInt(s));

        int increasingCount = 0;
        for(int i = 1; i < lines.size(); i++) {
            if(lines.get(i) > lines.get(i - 1)) increasingCount++;
        }

        return increasingCount;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day01.txt", 2021);
        File test = Day01.getResource("test01.txt", 2021);

        var linesstring = Utils.realOrTest(input, test);
        ArrayList<Integer> lines = new ArrayList<>();
        for(String s : linesstring) lines.add(Integer.parseInt(s));

        int increasingCount = 0;
        ArrayList<Integer> sums = new ArrayList<>();

        for(int i = 0; i < lines.size()-2; i++) {
            sums.add(lines.get(i) + lines.get(i+1) + lines.get(i+2));
        }

        for(int i = 1; i < sums.size(); i++) {
            if(sums.get(i) > sums.get(i - 1)) increasingCount++;
        }

        return increasingCount;
    }
}