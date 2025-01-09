package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.HashMap;

public class Day14 extends Day2021 {
    public Day14() {
        super(14);
    }

    public static void main(String[] args) {
        new Day14().printParts();
    }

    @Override
    public Object part1() {
        File input = Day14.getResource("day14.txt", 2021);
        File test = Day14.getResource("test14.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        String start = lines.get(0);
        lines.remove(1);
        lines.remove(0);

        HashMap<String, String> rules = new HashMap<>();
        for(String line : lines) {
            var tmp = line.split(" -> ");
            rules.put(tmp[0], tmp[1]);
        }

        String current = start;
        for(int i = 0; i < 10; i++) {
            int offset = 1;
            StringBuilder withReplacements = new StringBuilder(current);
            for(int j = 0; j < current.length()-1; j++) {
                String key = Character.toString(current.charAt(j)) + current.charAt(j + 1);
                if(rules.containsKey(key)) {
                    withReplacements.insert(j + offset, rules.get(key));
                    offset++;
                }
            }
            current = withReplacements.toString();
        }

        HashMap<Character, Integer> counts = new HashMap<>();
        for(char c : current.toCharArray()) {
            if(counts.containsKey(c)) {
                counts.put(c, counts.get(c)+1);
            } else {
                counts.put(c, 1);
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(Character key : counts.keySet()) {
            if(counts.get(key) < min) min = counts.get(key);
            if(counts.get(key) > max) max = counts.get(key);
        }

        return max - min;
    }

    @Override
    public Object part2() {
        File input = Day14.getResource("day14.txt", 2021);
        File test = Day14.getResource("test14.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        String start = lines.get(0);
        lines.remove(1);
        lines.remove(0);

        HashMap<String, String> rules = new HashMap<>();
        for(String line : lines) {
            var tmp = line.split(" -> ");
            rules.put(tmp[0], tmp[1]);
        }

        String current = start;
        for(int i = 0; i < 40; i++) {
            System.out.println(i);
            int offset = 1;
            StringBuilder withReplacements = new StringBuilder(current);
            for(int j = 0; j < current.length()-1; j++) {
                String key = Character.toString(current.charAt(j)) + current.charAt(j + 1);
                if(rules.containsKey(key)) {
                    withReplacements.insert(j + offset, rules.get(key));
                    offset++;
                }
            }
            current = withReplacements.toString();
        }

        HashMap<Character, Long> counts = new HashMap<>();
        for(char c : current.toCharArray()) {
            if(counts.containsKey(c)) {
                counts.put(c, counts.get(c)+1);
            } else {
                counts.put(c, 1L);
            }
        }

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for(Character key : counts.keySet()) {
            if(counts.get(key) < min) min = counts.get(key);
            if(counts.get(key) > max) max = counts.get(key);
        }

        return max - min;
    }
}