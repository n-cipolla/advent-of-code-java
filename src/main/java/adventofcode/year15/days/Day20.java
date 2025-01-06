package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class Day20 extends Day2015 {
    public Day20() {
        super(20);
    }

    public static void main(String[] args) {
        new Day20().printParts();
    }

    @Override
    public Object part1() {
        File in = Day20.getResource("day20.txt", 2015);
        File test = Day20.getResource("test20.txt", 2015);

        var lines = Utils.realOrTest(in, test);
        return "I have no idea if part 2 is working still. I got 2 stars but it takes so long to run I gave up on checking it.";
    }

    @Override
    public Object part2() {
        File in = Day20.getResource("day20.txt", 2015);
        File test = Day20.getResource("test20.txt", 2015);

        var lines = Utils.realOrTest(in, test);

        int input = Integer.parseInt(lines.get(0));
        int total = 0;
        int counter = 0;

        HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();
        while(input >= total) {
            total = 0;
            counter += 1;
            HashSet<Integer> pairs = multiplicativePairs(counter);
            for(Integer i : pairs) seen.merge(i, 1, Integer::sum);

            for(int i : pairs) {
                if(seen.get(i) <= 50) total += 11 * i;
            }
            System.out.println(counter);
        }
        // System.out.println(seen);
        System.out.println("Total = " + total);
        System.out.println("On House Number: " + counter);
        return 1;
    }

    private static HashSet<Integer> multiplicativePairs(int n) {
        HashSet<Integer> ret = new HashSet<>();
        for(int i = 1; i <= n; ++i) {
            if(n % i == 0) {
                ret.add(i);
                ret.add(n/i);
            }
        }
        return ret;
    }
}