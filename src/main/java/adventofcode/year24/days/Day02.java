package main.java.adventofcode.year24.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year24.Day2024;

import java.io.File;
import java.util.ArrayList;

public class Day02 extends Day2024 {
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
        ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>();
        for(String line : lines) {
            String[] parts = line.split(" ");
            ArrayList<Integer> tmp = new ArrayList<>();
            for(String part : parts) {
                tmp.add(Integer.parseInt(part.trim()));
            }
            numbers.add(tmp);
        }
        int safe = 0;
        for (ArrayList<Integer> report : numbers) {
            if((checkDecreasing(report) || checkIncreasing(report)) && checkDifferences(report)) safe++;
        }
        return safe;
    }

    @Override
    public Object part2() {
        return "Part 2 Not Yet Implemented";
    }

    public static boolean checkDifferences(ArrayList<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if (!(diff >= 1 && diff <= 3)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIncreasing(ArrayList<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i + 1) > report.get(i)) {
                // Do nothing
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDecreasing(ArrayList<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i + 1) < report.get(i)) {
                // Do nothing
            } else {
                return false;
            }
        }
        return true;
    }
}