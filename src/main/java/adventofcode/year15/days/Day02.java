package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day02 extends Day2015 {
    public Day02() {
        super(2);
    }

    public static void main(String[] args) {
        new Day02().printParts();
    }

    @Override
    public Object part1() {
        File input = Day02.getResource("day02.txt", 2015);
        File test = Day02.getResource("test02.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        String[] dims = new String[lines.size()];

        for(int i = 0; i < dims.length; i++) {
            dims[i] = lines.get(i).trim();
        }

        int paperTotal = 0;

        for(String s : dims) {
            String[] dimension = s.split("x");
            int l = Integer.parseInt(dimension[0]);
            int w = Integer.parseInt(dimension[1]);
            int h = Integer.parseInt(dimension[2]);

            int smallest = Math.min(Math.min(l*h, h*w), l*w);
            paperTotal += smallest + 2*l*h + 2*l*w + 2*h*w;
        }

        return paperTotal;
    }

    @Override
    public Object part2() {
        File input = Day02.getResource("day02.txt", 2015);
        File test = Day02.getResource("test02.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        String[] dims = new String[lines.size()];

        for(int i = 0; i < dims.length; i++) {
            dims[i] = lines.get(i).trim();
        }

        int ribbonTotal = 0;

        for(String s : dims) {
            String[] dimension = s.split("x");
            int l = Integer.parseInt(dimension[0]);
            int w = Integer.parseInt(dimension[1]);
            int h = Integer.parseInt(dimension[2]);

            int perim = 2 * Math.min(Math.min(l+w,w+h), h+l);
            int vol = l*w*h;
            ribbonTotal += perim + vol;
        }

        return ribbonTotal;
    }
}