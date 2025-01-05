package main.java.adventofcode.year24.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year24.Day2024;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Day01 extends Day2024 {
    public Day01() {
        super(1);
    }

    public static void main(String[] args) {
        new Day01().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day01.txt", 2024);
        File test = Day01.getResource("test01.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();

        for(String line : lines) {
            String[] parts = line.split(" {3}");
            left.add(parts[0]);
            right.add(parts[1]);
        }

        Collections.sort(left);
        Collections.sort(right);

        int total = 0;
        for(int i = 0; i < left.size(); i++) {
            total += Math.abs(Integer.parseInt(left.get(i)) - Integer.parseInt(right.get(i)));
        }

        return total;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day01.txt", 2024);
        File test = Day01.getResource("test01.txt", 2024);

        var lines = Utils.realOrTest(input, test);


        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for(String line : lines) {
            String[] words = line.split(" {3}");
            left.add(Integer.valueOf(words[0]));
            right.add(Integer.valueOf(words[1]));
        }

        Collections.sort(left);
        Collections.sort(right);

        int sum = 0;
        for (int left_num : left) {
            int occur = 0;
            if (right.contains(left_num)) {
                // find the number of occurrances
                occur = countOccur(left_num, right);
            }
            sum += left_num * occur;
        }

        return sum;
    }

    public static int countOccur(int left_num, ArrayList<Integer> right) {
        int count = 0;
        for (int right_num : right) {
            if (left_num == right_num) {
                count++;
            }
        }
        return count;
    }
}
