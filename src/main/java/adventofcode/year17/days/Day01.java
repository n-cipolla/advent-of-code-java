package main.java.adventofcode.year17.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year17.Day2017;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Day01 extends Day2017 {
    public Day01() {
        super(1);
    }

    public static void main(String[] args) {
        new Day01().printParts();
    }

    @Override
    public Object part1() {
        File in = Day01.getResource("Day01.txt", 2017);
        File test = Day01.getResource("Day01Test.txt", 2017);
        var input = Utils.realOrTest(in, test).get(0);

        ArrayList<Integer> digits = new ArrayList<Integer>();
        for(int i = 0; i < in.length(); ++i) {
            digits.add(Character.getNumericValue(input.charAt(i)));
        }

        int sum = 0;
        for(int i = 0; i < digits.size()-1; ++i) {
            if(digits.get(i) == digits.get(i+1)) {
                sum+= digits.get(i);
            }
        }
        if(digits.get(0) == digits.get(digits.size()-1)) {
            sum += digits.get(0);
        }

        return sum;
    }

    @Override
    public Object part2() {
        File in = Day01.getResource("Day01.txt", 2017);
        File test = Day01.getResource("Day01Test.txt", 2017);

        var input = Utils.realOrTest(in, test).get(0);

        ArrayList<Integer> digits = new ArrayList<>();
        for(var i = 0; i < input.length(); i++) {
            digits.add(Character.getNumericValue(input.charAt(i)));
        }

        int sum2 = 0;
        int wrap = digits.size()/2;
        ArrayList<Integer> doubledDigits = new ArrayList<>(digits);
        doubledDigits.addAll(digits);
        for(var i = 0; i < digits.size(); ++i) {
            if(doubledDigits.get(i) == doubledDigits.get(i + wrap)) {
                System.out.println("Digit at position: " + i + "(" + doubledDigits.get(i) + ") matches digit at position: " + doubledDigits.get(i + wrap) + "(" + doubledDigits.get(i) + ")");
                sum2 += digits.get(i);
            }
        }
        return sum2;
    }
}