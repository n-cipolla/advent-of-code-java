package main.java.adventofcode.year23.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year23.Day2023;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class day08 extends Day2023 {
    public day08() {
        super(8);
    }

    public static void main(String[] args) {
        new day08().printParts();
    }

    private static HashMap<String, String> leftSetup(List<String> input) {
        var ret = new HashMap<String, String>();
        for(var v : input) {
            ret.put(v.substring(0,3), v.substring(7,10));
        }
        return ret;
    }

    private static HashMap<String, String> rightSetup(List<String> input) {
        var ret = new HashMap<String, String>();
        for(var v : input) {
            ret.put(v.substring(0,3), v.substring(12,15));
        }
        return ret;
    }

    private static String findNext(HashMap<String, String> left, HashMap<String, String> right, String line, char direction) {
        if(direction == 'L') {
            return left.get(line.substring(0,3));
        } else {
            return right.get(line.substring(0,3));
        }
    }

    private static long calculateGCD(long a, long b) {
        if (a == 0 || b == 0) {
            return a + b;
        } else {
            long absNumber1 = Math.abs(a);
            long absNumber2 = Math.abs(b);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);
            return calculateGCD(biggerValue % smallerValue, smallerValue);
        }
    }

    private static long calculateLCM(ArrayList<Long> numbers) {
        long lcm = 1;
        for(long number : numbers) {
            if(number == 0) return 0;
            lcm = Math.abs(lcm * number) / calculateGCD(lcm, number);
        }
        return lcm;
    }

     private static int findIndex(ArrayList<String> input, String line) {
        for(int i = 0; i < input.size(); ++i) {
           if(input.get(i).substring(0,3).equals(line)) return i;
        }
        return 0;
     }

    @Override
    public Object part1() {
        File in = day08.getResource("day08.txt", 2023);
        File test = day08.getResource("test08.txt", 2023);

        var input = Utils.realOrTest(in, test);
        String instructions = input.get(0);

        int startIndex = 0;

        for(int i = 0; i < input.size(); ++i) {
           if(input.get(i).startsWith("AAA")) startIndex = i;
        }
        String next = "";
        int counter = 0;
        int iteration = 0;
        while(!next.equals("ZZZ")) {
           char dir = instructions.charAt(counter);
//           next = findNext(input, startIndex, dir);
//           startIndex = findIndex((ArrayList<String>) input, next);
           if(counter == instructions.length()-1) {
              counter = 0;
           } else {
              counter++;
           }
           iteration++;
        }
        return iteration;
    }

    @Override
    public Object part2() {
        File in = day08.getResource("day08.txt", 2023);
        File test = day08.getResource("test08.txt", 2023);

        var input = Utils.realOrTest(in, test);

        String instructions = input.get(0);

        input.remove(0);  // Removes the instructions
        input.remove(0);  // Removes the blank line

        HashMap<String, String> left = leftSetup(input);
        HashMap<String, String> right = rightSetup(input);

        ArrayList<String> starts = new ArrayList<>();
        for(int i = 0; i < input.size(); ++i) {
            if(input.get(i).charAt(2) == 'A') starts.add(input.get(i).substring(0,3));
        }

        ArrayList<Integer> steps = new ArrayList<>();

        for(int i = 0; i < starts.size(); ++i) {

            String next = starts.get(i);
            int counter = 0;
            int iteration = 0;

            while(next.charAt(2) != 'Z') {
                char dir = instructions.charAt(counter);
                next = findNext(left, right, next, dir);

                if(counter == instructions.length()-1) {
                    counter = 0;
                } else {
                    counter++;
                }
                iteration++;
            }
            steps.add(iteration);
        }

        ArrayList<Long> longSteps = new ArrayList<>();
        for(int i = 0; i < steps.size(); ++i) {
            longSteps.add(Long.valueOf(steps.get(i)));
        }
        return calculateLCM(longSteps);
    }
}