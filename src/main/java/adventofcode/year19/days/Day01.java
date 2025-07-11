package main.java.adventofcode.year19.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year19.Day2019;

import java.io.File;
import java.util.ArrayList;

public class Day01 extends Day2019 {
    public Day01() {
        super(1);
    }

    public static void main(String[] args) {
        new Day01().printParts();
    }

    @Override
    public Object part1() {
        File in = Day01.getResource("day01.txt", 2019);
        File test = Day01.getResource("test01.txt", 2019);

        var input = Utils.realOrTest(in, test);
        ArrayList<Integer> nums = new ArrayList<>();
        for(String s : input) {
            nums.add(Integer.parseInt(s));
        }

        int total = 0;
        for(Integer i : nums) {
            total += (int)Math.floor((double) i /3) - 2;
        }
        return total;
    }

    @Override
    public Object part2() {
        File in = Day01.getResource("day01.txt", 2019);
        File test = Day01.getResource("test01.txt", 2019);

        var input = Utils.realOrTest(in, test);
        ArrayList<Integer> nums = new ArrayList<>();
        for(String s : input) {
            nums.add(Integer.parseInt(s));
        }

        int total = 0;
        for(Integer i : nums) {
            int amountToAdd = (int)Math.floor((double) i /3) - 2;
            total += amountToAdd;
            while(amountToAdd > 6) {
                amountToAdd = (int)Math.floor((double) amountToAdd/3) - 2;
                total += amountToAdd;
            }
        }
        return total;
    }
}
// 5005908 too low