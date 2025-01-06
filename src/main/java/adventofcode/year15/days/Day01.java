package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day01 extends Day2015 {
    public Day01() {
        super(1);
    }

    public static void main(String[] args) {
        new Day01().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day01.txt", 2015);
        File test = Day01.getResource("test01.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        var str = lines.get(0);

        int left = 0;
        int right = 0;
        for(char c : str.toCharArray()) {
            if(c == '(') left++;
            else right++;
        }
        return left - right;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day01.txt", 2015);
        File test = Day01.getResource("test01.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        String str = lines.get(0);

        int floor = 0;
        for(int i = 0; i < str.length(); i++) {
            if(floor < 0) return i;
            else {
                if(str.charAt(i) == '(') floor++;
                else floor--;
            }
        }
        return 0;
    }
}