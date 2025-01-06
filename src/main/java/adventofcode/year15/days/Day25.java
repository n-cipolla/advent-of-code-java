package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day25 extends Day2015 {
    public Day25() {
        super(25);
    }

    public static void main(String[] args) {
        new Day25().printParts();
    }

    @Override
    public Object part1() {
        File in = Day25.getResource("day25.txt", 2015);
        File test = Day25.getResource("test25.txt", 2015);

        final int ROW = Integer.parseInt(Utils.readLines(in).get(0));
        final int COLUMN = Integer.parseInt(Utils.readLines(in).get(1));

        long seed = 20151125;

        // Gets the index of the first number at the top of the column
        long index = sumOfNatNums(COLUMN);
//        System.out.println(index);
        long tmp = COLUMN;
        for(int i = 0; i < ROW-1; ++ i) {
            // System.out.println("Adding: " + tmp);
            index += tmp;
            tmp++;
        }
//        System.out.println(index);

        // to get from row 1 to row 2 in the same column
        // take the column header and multiply it by the
        // (2-1)*column header
        // this is the index of the square in the grid

        // long index = sumOfNatNums(5);
        // System.out.println(index);
        // index += 5 * (1-1);
        // System.out.println(index);

        // This calculates the index-th number in the grid
        for(int i = 0; i < index; ++i) {
            if(i != 0) seed = calcNext(seed);
        }
        return seed;
    }

    @Override
    public Object part2() {
        File in = Day25.getResource("day25.txt", 2015);
        File test = Day25.getResource("test25.txt", 2015);

        var input = Utils.realOrTest(in, test);
        return 1;
    }

    // (n^2 + n)/2 <- sum of natural numbers

    private static long sumOfNatNums(long n) {
        return (n*(n + 1)) / 2;
    }

    private static long calcNext(long n) {
        n *= 252533;
        n %= 33554393;
        return n;
    }
}