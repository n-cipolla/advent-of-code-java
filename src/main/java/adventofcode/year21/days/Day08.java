package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day08 extends Day2021 {
    public Day08() {
        super(8);
    }

    public static void main(String[] args) {
        new Day08().printParts();
    }

    @Override
    public Object part1() {
        File input = Day08.getResource("day08.txt", 2021);
        File test = Day08.getResource("test08.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        ArrayList<String> outputs = new ArrayList<String>();
        for(String line : lines) {
            var arr = line.split(" \\| ");
            outputs.add(arr[1]);
        }

        ArrayList<String> allStrings = new ArrayList<>();
        for(String s : outputs) {
            var arr = s.split(" ");
            allStrings.addAll(List.of(arr));
        }

        int count = 0;
        for(String s : allStrings) {
            if(s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7) count++;
        }

        return count;
    }

    @Override
    public Object part2() {
        File input = Day05.getResource("day08.txt", 2021);
        File test = Day05.getResource("test08.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        ArrayList<String> outs = new ArrayList<>();
        ArrayList<String> ins = new ArrayList<>();
        for(String line : lines) {
            var arr = line.split(" \\| ");
            outs.add(arr[1]);
            ins.add(arr[0]);
        }

        // you can find the top bar by figuring out what the 7 and the 1 are...the extra letter in 7 is the top
        // you can find EITHER the top left OR middle by finding out the two in 4 which aren't in 1
        // you can find EITHER bottom OR bottom left by finding the remaining two in 8
        // 0, 3, 4, 7 , 8, 9 contain "one"
        // 2 and 5 contain the same middle three
        // 6 chars long is either 9 or 6
        for(int i = 0; i < ins.size(); i++) {
            var tmp = ins.get(i).split(" ");
            ArrayList<String> digitsAsSegments = new ArrayList<>(List.of(tmp));
            String one = null;
            String two = null;
            String three = null;
            String four = null;
            String five = null;
            String six = null;
            String seven = null;
            String eight = null;
            String nine = null;
            String zero = null;

            for(String s : digitsAsSegments) {
                if(s.length() == 2) one = s;
                else if(s.length() == 3) seven = s;
                else if(s.length() == 4) four = s;
                else if(s.length() == 7) eight = s;
            }

            String top = extractUnique(one, seven);

        }



        return "Incomplete";
    }

    private String extractUnique(String shorter, String longer) {
        String ret = longer;
        for(char c : shorter.toCharArray()) {
            ret = ret.replace(Character.toString(c), "");
        }
        return ret;
    }

    private String onlyInCommon(String first, String second) {
        for(char c : first.toCharArray()) {
            if(second.contains(Character.toString(c))) return Character.toString(c);
        }
        return "";
    }
}