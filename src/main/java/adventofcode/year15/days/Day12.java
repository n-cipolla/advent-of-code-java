package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day12 extends Day2015 {
    public Day12() {
        super(12);
    }

    public static void main(String[] args) {
        new Day12().printParts();
    }

    @Override
    public Object part1() {
        File input = Day12.getResource("day12.txt", 2015);
        File test = Day12.getResource("test12.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        int intCount = 0;
        char[] arr = lines.get(0).toCharArray();

        for(int i = 0; i < arr.length; ++i) {
            boolean isNegative = false;
            if(String.valueOf(arr[i]).matches("[0-9]")) {
                if(arr[i-1] == '-') {
                    isNegative = true;
                }
                int count = 1;
                StringBuilder sb = new StringBuilder();
                sb.append(arr[i]);
                while(String.valueOf(arr[i+count]).matches("[0-9]")) {
                    sb.append(arr[i+count]);
                    count++;
                }
                i += count - 1;
                if(isNegative) intCount -= Integer.parseInt(sb.toString());
                else intCount += Integer.parseInt(sb.toString());
            }
        }
        return intCount;
    }

    @Override
    public Object part2() {
        File input = Day12.getResource("day12.txt", 2015);
        File test = Day12.getResource("test12.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        return "Not yet implemented";
    }
}