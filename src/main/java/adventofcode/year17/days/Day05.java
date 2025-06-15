package main.java.adventofcode.year17.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year17.Day2017;

import java.io.File;
import java.util.ArrayList;

public class Day05 extends Day2017 {
    public Day05() {
        super(5);
    }

    public static void main(String[] args) {
        new Day05().printParts();
    }

    @Override
    public Object part1() {
        File in = Day05.getResource("Day05.txt", 2017);
        File test = Day05.getResource("Day05Test.txt", 2017);

        var strings = Utils.realOrTest(in, test);
        ArrayList<Integer> input = new ArrayList<>();
        for (String string : strings) {
            input.add(Integer.parseInt(string));
        }

        int start = 0;

        ArrayList<ArrayList<Integer>> steps = new ArrayList<>();

        for(var i = start; i < input.size() * 2; i += 0) {
            int tmp = input.get(i);
            i += tmp;
            if(i >= input.size()) {break;}

            

            }

        System.out.println(steps.size());
        return steps.size();
    }

    @Override
    public Object part2() {
        File in = Day05.getResource("Day05.txt", 2017);
        File test = Day05.getResource("Day05Test.txt", 2017);

        var strings = Utils.realOrTest(in, test);

        ArrayList<Integer> input = new ArrayList<>();
        for (String string : strings) {
            input.add(Integer.parseInt(string));
        }

        int start = 0;
        System.out.println(input);

        ArrayList<ArrayList<Integer>> steps = new ArrayList<>();

        for(int pointer = start; pointer < input.size(); pointer += 0) {
            int tmp = input.get(pointer);
            if(tmp >= 3) input.set(pointer, tmp - 1);
            else input.set(pointer, tmp + 1);
            steps.add(input);

            pointer += tmp;
        }
        return steps.size();
    }
}