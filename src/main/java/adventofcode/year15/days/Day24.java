package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Day24 extends Day2015 {
    public Day24() {
        super(24);
    }

    public static void main(String[] args) {
        new Day24().printParts();
    }

    @Override
    public Object part1() {
        File in = Day24.getResource("day24.txt", 2015);
        File test = Day24.getResource("test24.txt", 2015);

        var input_String = Utils.realOrTest(in, test);
        ArrayList<Integer> input = new ArrayList<>();
        for(String s : input_String) input.add(Integer.parseInt(s));
        int total = 0;
        for(Integer i : input) total += i;
        int goal = total/3;
        var results = findSubsetsWithSum(input, goal);

        int minSize = 100000;
        for(var v : results) {
            if(v.size() < minSize) minSize = v.size();
        }
        long min = Long.MAX_VALUE;
        for(var v : results) {
            if(v.size() == minSize) {
                if(calcQE(v) < min) min = calcQE(v);
            }
        }
        return min;
    }

    @Override
    public Object part2() {
        File in = Day24.getResource("day24.txt", 2015);
        File test = Day24.getResource("test24.txt", 2015);

        var input_String = Utils.realOrTest(in, test);
        ArrayList<Integer> input = new ArrayList<>();
        for(String s : input_String) input.add(Integer.parseInt(s));
        int total = 0;
        for(Integer i : input) total += i;
        int goal = total/4;
        var results = findSubsetsWithSum(input, goal);

        int minSize = 100000;
        for(var v : results) {
            if(v.size() < minSize) minSize = v.size();
        }
        long min = Long.MAX_VALUE;
        for(var v : results) {
            if(v.size() == minSize) {
                if(calcQE(v) < min) min = calcQE(v);
            }
        }
        return min;
    }

    private static long calcQE(List<Integer> input) {
        long total = 1;
        for(var v : input) {
            total *= v;
        }
        return total;
    }

    private static List<List<Integer>> findSubsetsWithSum(ArrayList<Integer> numbers, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findSubsets(numbers, targetSum, 0, new ArrayList<>(), result);
        return result;
    }

    private static void findSubsets(ArrayList<Integer> numbers, int target, int index, List<Integer> currentSubset, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }

        for (int i = index; i < numbers.size(); i++) {
            if (target - numbers.get(i) >= 0) {
                currentSubset.add(numbers.get(i));
                findSubsets(numbers, target - numbers.get(i), i + 1, currentSubset, result);
                currentSubset.remove(currentSubset.size() - 1);
            }
        }
    }
}