package main.java.adventofcode.year17.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year17.Day2017;

import java.io.File;
import java.util.HashSet;

public class Day04 extends Day2017 {
    public Day04() {
        super(4);
    }

    public static void main(String[] args) {
        new Day04().printParts();
    }

    @Override
    public Object part1() {
        File in = Day04.getResource("Day04.txt", 2017);
        File test = Day04.getResource("Day04Test.txt", 2017);

        var input = Utils.realOrTest(in, test);

        int valid = 0;
        for(String line : input) {
            String[] words = line.split(" ");
            HashSet<String> unique = new HashSet<String>();
            boolean invalid = false;
            for(String word : words) {
                if(!unique.contains(word)) {
                    unique.add(word);
                } else {
                    invalid = true;
                }
            }
            if(!invalid) valid++;
        }
        return valid;
    }

    @Override
    public Object part2() {
        File in = Day04.getResource("Day04.txt", 2017);
        File test = Day04.getResource("Day04Test.txt", 2017);

        var input = Utils.realOrTest(in, test);
        return "Not yet implemented";
    }
}