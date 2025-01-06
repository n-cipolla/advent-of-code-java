package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;

public class Day17 extends Day2015 {
    public Day17() {
        super(17);
    }

    public static void main(String[] args) {
        new Day17().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day17.txt", 2015);
        File test = Day01.getResource("test17.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        final int TARGET_VOLUME = 150;

        ArrayList<Integer> numbers = new ArrayList<>();
        for(String s : lines) numbers.add(Integer.parseInt(s));

        var pset = Utils.powerset(numbers);

        int counter = 0;

        for(ArrayList<Integer> v : pset) {
            if(TARGET_VOLUME == sum(v)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day17.txt", 2015);
        File test = Day01.getResource("test17.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        final int TARGET_VOLUME = 150;

        ArrayList<Integer> numbers = new ArrayList<>();
        for(String s : lines) numbers.add(Integer.parseInt(s));

        var pset = Utils.powerset(numbers);

        int counter = 0;
        int min = 100000;

        for(ArrayList<Integer> v : pset) {
            if(TARGET_VOLUME == sum(v)) {
                counter++;
                if(v.size() < min) min = v.size();
            }
        }
        ArrayList<ArrayList<Integer>> ideal = new ArrayList<>();
        for(var v : pset) {
            if(TARGET_VOLUME == sum(v) && v.size() == min) {
                ideal.add(v);
            }
        }
        return ideal.size();
    }

    private static int sum(ArrayList<Integer> input) {
        int total = 0;
        for(int i : input) {
            total += i;
        }
        return total;
    }
}