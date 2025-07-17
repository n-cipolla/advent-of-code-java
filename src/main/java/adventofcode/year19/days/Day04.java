package main.java.adventofcode.year19.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year19.Day2019;

import java.io.File;
import java.util.HashMap;

public class Day04 extends Day2019 {
    public Day04() {
        super(4);
    }

    public static void main(String[] args) {
        new Day04().printParts();
    }

    @Override
    public Object part1() {
        File in = Day04.getResource("day04.txt", 2019);
        File test = Day04.getResource("test04.txt", 2019);

        var input = Utils.realOrTest(in, test);
        int lowerBound = Integer.parseInt(input.get(0).split("-")[0]);
        int upperBound = Integer.parseInt(input.get(0).split("-")[1]);

        int count = 0;
        for(int i = lowerBound; i <= upperBound; i++) {
            String num = ((Integer)i).toString();
            boolean consecutive = false;
            boolean noDecrease = true;
            for(int j = 0; j < 5; j++) {
                if(num.charAt(j) == num.charAt(j+1)) {
                    consecutive = true;
                }

                if(num.charAt(j+1) < num.charAt(j)) {
                    noDecrease = false;
                }
            }

            if(consecutive && noDecrease) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Object part2() {
        File in = Day04.getResource("day04.txt", 2019);
        File test = Day04.getResource("test04.txt", 2019);

        var input = Utils.realOrTest(in, test);
        int lowerBound = Integer.parseInt(input.get(0).split("-")[0]);
        int upperBound = Integer.parseInt(input.get(0).split("-")[1]);

//        checkIndividual(112233);
//        checkIndividual(123444);
//        checkIndividual(111122);

        int count = 0;
        for(int i = lowerBound; i <= upperBound; i++) {
            String num = ((Integer)i).toString();
            boolean consecutive = false;
            boolean noDecrease = true;

            for(int j = 0; j < 5; j++) {
                if (num.charAt(j + 1) < num.charAt(j)) {
                    noDecrease = false;
                    break;
                }
            }

            for(int j = 0; j < 10; j++) {

            }

            if(consecutive && noDecrease) {
                count++;
            }
        }

        return count;
    }

    public static void checkIndividual(int num) {
        String strNum = ((Integer) num).toString();
        boolean consecutive = false;
        boolean noDecrease = true;



        if(consecutive && noDecrease) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }

}

//972 too low