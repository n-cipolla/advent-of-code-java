package main.java.adventofcode.year17.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year17.Day2017;

import java.io.File;
import java.util.ArrayList;

public class Day02 extends Day2017 {
    public Day02() {
        super(2);
    }

    public static void main(String[] args) {
        new Day02().printParts();
    }

    @Override
    public Object part1() {
        File in = Day02.getResource("Day02.txt", 2017);
        File test = Day02.getResource("Day02Test.txt", 2017);

        var input = Utils.realOrTest(in, test);
        int total = 0;
        for(String row : input) {
            String[] strings = row.split("\\s+");
            ArrayList<Integer> nums = new ArrayList<>();
            for(String s : strings) {
                nums.add(Integer.parseInt(s));
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(Integer num : nums) {
                if (num < min) min = num;
                if (num > max) max = num;
            }
            int diff = max - min;
            total += diff;
        }
        return total;
    }

    @Override
    public Object part2() {
        File in = Day02.getResource("Day02.txt", 2017);
        File test = Day02.getResource("Day02Test.txt", 2017);

        var input = Utils.realOrTest(in, test);

        int total = 0;
        for(String row : input) {
            String[] strings = row.split("\\s+");
            ArrayList<Integer> nums = new ArrayList<>();
            for(String s : strings) {
                nums.add(Integer.parseInt(s));
            }

            for(int i = 0; i < nums.size(); ++i) {
                for(int j = i+1; j < nums.size(); ++j) {
                    if(nums.get(i) % nums.get(j) == 0) {
                        total += nums.get(i) / nums.get(j);
                        break;
                    } else if (nums.get(j) % nums.get(i) == 0) {
                        total += nums.get(j) / nums.get(i);
                        break;
                    }
                }
            }
        }

        return total;
    }
}