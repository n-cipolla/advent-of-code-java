package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day10 extends Day2015 {
    public Day10() {
        super(10);
    }

    public static void main(String[] args) {
        new Day10().printParts();
    }

    @Override
    public Object part1() {
        File in = Day01.getResource("day10.txt", 2015);
        File test = Day01.getResource("test10.txt", 2015);

        var lines = Utils.realOrTest(in, test);

        String input = lines.get(0);

        for(int lcv = 0; lcv < 40; lcv++) {
            char[] arr = input.toCharArray();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < arr.length; ++i) {
                int count = 1;
                for(int j = i+1; j<arr.length; ++j) {
                    if(arr[i] == arr[j]) {
                        count++;
                    } else break;
                }
                if(count != 0) sb.append(String.valueOf(count));
                sb.append(arr[i]);
                if(count > 1) i += count-1;
            }
            input = sb.toString();
        }
        return input.length();
    }

    @Override
    public Object part2() {
        File in = Day01.getResource("day10.txt", 2015);
        File test = Day01.getResource("test10.txt", 2015);

        var lines = Utils.realOrTest(in, test);

        String input = lines.get(0);

        for (int lcv = 0; lcv < 50; lcv++) {
            char[] arr = input.toCharArray();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < arr.length; ++i) {
                int count = 1;
                for (int j = i + 1; j < arr.length; ++j) {
                    if (arr[i] == arr[j]) {
                        count++;
                    } else break;
                }
                if (count != 0) sb.append(String.valueOf(count));
                sb.append(arr[i]);
                if (count > 1) i += count - 1;
            }
            input = sb.toString();
        }
        return input.length();
    }
}