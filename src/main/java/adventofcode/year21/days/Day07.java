package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Day07 extends Day2021 {
    public Day07() {
        super(7);
    }

    public static void main(String[] args) {
        new Day07().printParts();
    }

    @Override
    public Object part1() {
        File input = Day07.getResource("day07.txt", 2021);
        File test = Day07.getResource("test07.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        String[] str = lines.get(0).split(",");
        ArrayList<Integer> positions = new ArrayList<>();
        for(String s : str) positions.add(Integer.parseInt(s));

        int min = 10000000;
        for(int i = 0; i < positions.size(); ++i) {
            int fuelCost = 0;
            for(int j = 0; j < positions.size(); ++j) {
                fuelCost += Math.abs(positions.get(i) - positions.get(j));
            }
            if(fuelCost < min) min = fuelCost;
        }

        return min;
    }

    @Override
    public Object part2() {
        File input = Day07.getResource("day07.txt", 2021);
        File test = Day07.getResource("test07.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        String[] str = lines.get(0).split(",");
        ArrayList<Integer> positions = new ArrayList<>();
        for(String s : str) positions.add(Integer.parseInt(s));

        int max = Collections.max(positions);

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < max; ++i) {
            int fuelCost = 0;
            for(int j = 0; j < positions.size(); ++j) {
                int diff = Math.abs(i - positions.get(j));
                fuelCost += (diff * diff + diff)/2;
            }
            if(fuelCost < min) {
                min = fuelCost;
//                System.out.println(positions.get(i));
            }
        }

        return min;
    }
}