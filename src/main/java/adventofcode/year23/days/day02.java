package main.java.adventofcode.year23.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year23.Day2023;

import java.io.File;
import java.util.HashMap;
import java.util.Collection;

public class day02 extends Day2023 {
    public day02() {
        super(2);
    }

    public static void main(String[] args) {
        new day02().printParts();
    }

    @Override
    public Object part1() {
        File in = day02.getResource("day02.txt", 2023);
        File test = day02.getResource("test02.txt", 2023);

        var input = Utils.realOrTest(in, test);

        HashMap<String, Integer> limits = new HashMap<>();
        limits.put("red", 12);
        limits.put("green", 13);
        limits.put("blue", 14);

        HashMap<Integer, String> IDs = new HashMap<Integer, String>();
        for(String s : input) {
            String[] idArray = s.split(": ");
            IDs.put(Integer.parseInt(idArray[0].split(" ")[1]), idArray[1]);
        }

        int sum = 0;

        for(Integer key : IDs.keySet()) {
            boolean valid = true;
            String[] games = IDs.get(key).split("; ");
            for(String game : games) {
                String[] draws = game.split(", ");
                for(String s : draws) {
                    String[] numCol = s.split(" ");
                    if(numCol[1].equals("red") || numCol[1].equals("blue") || numCol[1].equals("green")) {
                        if(Integer.parseInt(numCol[0]) > limits.get(numCol[1])) {
                            valid = false;
                        }
                    }
                }
            }
            if(valid) {sum += key;}
        }

        return sum;
    }

    @Override
    public Object part2() {
        File in = day02.getResource("day02.txt", 2023);
        File test = day02.getResource("test02.txt", 2023);

        var input = Utils.realOrTest(in, test);

        HashMap<Integer, String> IDs = new HashMap<Integer, String>();
        for(String s : input) {
            String[] idArray = s.split(": ");
            IDs.put(Integer.parseInt(idArray[0].split(" ")[1]), idArray[1]);
        }

        int sum = 0;

        for(Integer key : IDs.keySet()) {
            HashMap<String, Integer> counts = new HashMap<>();
            counts.put("red", 0);
            counts.put("green", 0);
            counts.put("blue", 0);

            String[] games = IDs.get(key).split("; ");
            for(String game : games) {
                String[] draws = game.split(", ");
                for(String s : draws) {
                    String[] numColor = s.split(" ");
                    if(numColor[1].equals("red") && Integer.parseInt(numColor[0]) > counts.get("red")) {
                        counts.put("red", Integer.parseInt(numColor[0]));
                    }
                    else if(numColor[1].equals("blue") && Integer.parseInt(numColor[0]) > counts.get("blue")) {
                        counts.put("blue", Integer.parseInt(numColor[0]));
                    }
                    else if(numColor[1].equals("green") && Integer.parseInt(numColor[0]) > counts.get("green")){
                        counts.put("green", Integer.parseInt(numColor[0]));
                    }
                }
            }
            sum += calcPower(counts.values());
        }

        return sum;
    }

    private static int calcPower(Collection<Integer> input) {
        int prod = 1;
        for(int i : input) prod *= i;
        return prod;
    }
}