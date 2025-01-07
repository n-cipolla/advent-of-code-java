package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Day06 extends Day2021 {
    public Day06() {
        super(6);
    }

    public static void main(String[] args) {
        new Day06().printParts();
    }

    @Override
    public Object part1() {
        File input = Day06.getResource("day06.txt", 2021);
        File test = Day06.getResource("test06.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        String[] str = lines.get(0).split(",");
        ArrayList<Integer> lanternfish = new ArrayList<>();
        for(String s : str) lanternfish.add(Integer.parseInt(s));

        for(int days = 0; days < 80; days++) {
            for(int i = 0; i < lanternfish.size(); ++i) {
                if(lanternfish.get(i) > 0) {
                    lanternfish.set(i, lanternfish.get(i) - 1);
                } else if(lanternfish.get(i) == 0) {
                    lanternfish.set(i, 6);
                    lanternfish.add(9);
                }
            }
        }

        return lanternfish.size();
    }

    @Override
    public Object part2() {
        File input = Day05.getResource("day06.txt", 2021);
        File test = Day05.getResource("test06.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        String[] str = lines.get(0).split(",");
        ArrayList<Integer> lanternfish = new ArrayList<>();
        for(String s : str) lanternfish.add(Integer.parseInt(s));

        HashMap<Integer, Long> fishCounts = new HashMap<>();
        for(int i = 0; i < 9; i++) fishCounts.put(i, (long)0);

        for(int fish : lanternfish) fishCounts.put(fish, fishCounts.get(fish)+1);

        for(int days = 0; days < 256; days++) {
            long old0 = fishCounts.get(0);
            long old1 = fishCounts.get(1);
            long old2 = fishCounts.get(2);
            long old3 = fishCounts.get(3);
            long old4 = fishCounts.get(4);
            long old5 = fishCounts.get(5);
            long old6 = fishCounts.get(6);
            long old7 = fishCounts.get(7);
            long old8 = fishCounts.get(8);
            fishCounts.put(0, old1);
            fishCounts.put(1, old2);
            fishCounts.put(2, old3);
            fishCounts.put(3, old4);
            fishCounts.put(4, old5);
            fishCounts.put(5, old6);
            fishCounts.put(6, old7 + old0);
            fishCounts.put(7, old8);
            fishCounts.put(8, old0);
        }

        System.out.println(fishCounts);

        long total = 0;
        for(int i = 0; i < fishCounts.keySet().size(); ++i) {
            total += fishCounts.get(i);
        }
        return total;
    }
}