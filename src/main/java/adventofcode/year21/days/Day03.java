package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Day03 extends Day2021 {
    public Day03() {
        super(3);
    }

    public static void main(String[] args) {
        new Day03().printParts();
    }

    @Override
    public Object part1() {
        File input = Day03.getResource("day03.txt", 2021);
        File test = Day03.getResource("test03.txt", 2021);

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        var lineStrings = Utils.realOrTest(input, test);

        for(int i = 0; i < lineStrings.get(0).length(); ++i) {
            HashMap<Integer, Integer> bitCounts = new HashMap<>();
            bitCounts.put(0,0);
            bitCounts.put(1,0);
            for (String lineString : lineStrings) {
                if (lineString.charAt(i) == '0') bitCounts.put(0, bitCounts.get(0) + 1);
                else bitCounts.put(1, bitCounts.get(1) + 1);
            }
            if(bitCounts.get(0) > bitCounts.get(1)) gamma.append(0);
            else gamma.append(1);
        }

        for(char c : gamma.toString().toCharArray()) {
            if(c == '1') epsilon.append('0');
            else epsilon.append('1');
        }

        int epsilonNum = Integer.parseInt(epsilon.toString(),2);
        int gammaNum = Integer.parseInt(gamma.toString(), 2);

        return gammaNum * epsilonNum;
    }

    @Override
    public Object part2() {
        File input = Day03.getResource("day03.txt", 2021);
        File test = Day03.getResource("test03.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        ArrayList<String> ox = new ArrayList<>(lines);

        for(int i = 0; i < ox.get(0).length(); ++i) {
            HashMap<Integer, Integer> bitCounts = new HashMap<>();
            bitCounts.put(0,0);
            bitCounts.put(1,0);
            int finalI = i;
            for (String s : ox) {
                if (s.charAt(i) == '1') bitCounts.put(1, bitCounts.get(1) + 1);
                else bitCounts.put(0, bitCounts.get(0) + 1);
            }
            if(bitCounts.get(0) > bitCounts.get(1)) {
                ox.removeIf(entry -> entry.charAt(finalI) == '1');
            }
            else {
                ox.removeIf(entry -> entry.charAt(finalI) == '0');
            }
            if(ox.size() == 1) break;
        }

        ArrayList<String> co2 = new ArrayList<>(lines);

        for(int i = 0; i < lines.get(0).length(); ++i) {
            HashMap<Integer, Integer> bitCounts = new HashMap<>();
            bitCounts.put(0,0);
            bitCounts.put(1,0);
            int finalI = i;
            for (String s : co2) {
                if (s.charAt(i) == '1') bitCounts.put(1, bitCounts.get(1) + 1);
                else bitCounts.put(0, bitCounts.get(0) + 1);
            }
            if(bitCounts.get(0) < bitCounts.get(1) || (bitCounts.get(0).equals(bitCounts.get(1)))) {
                co2.removeIf(entry -> entry.charAt(finalI) == '1');
            }
            else {
                co2.removeIf(entry -> entry.charAt(finalI) == '0');
            }
            if(co2.size() == 1) break;
        }

        int oxNum = Integer.parseInt(ox.get(0), 2);
        int co2Num = Integer.parseInt(co2.get(0), 2);

        return oxNum * co2Num;
    }
}