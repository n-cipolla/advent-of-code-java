package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.awt.*;
import java.io.File;
import java.util.HashSet;

public class Day03 extends Day2015 {
    public Day03() {
        super(3);
    }

    public static void main(String[] args) {
        new Day03().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day03.txt", 2015);
        File test = Day01.getResource("test03.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        String directions = lines.get(0);

        char[] dir = directions.toCharArray();

        HashSet<Point> sHouses = new HashSet<>();
        Point sLocation = new Point(0,0);
        sHouses.add(new Point());

        for(char c : dir) {
                switch (c) {
                    case '^' :
                        sLocation.translate(0, 1);
                        sHouses.add(new Point(sLocation));
                        break;

                    case '>' :
                        sLocation.translate(1,0);
                        sHouses.add(new Point(sLocation));
                        break;

                    case '<' :
                        sLocation.translate(-1, 0);
                        sHouses.add(new Point(sLocation));
                        break;

                    case 'v' :
                        sLocation.translate(0, -1);
                        sHouses.add(new Point(sLocation));
                        break;
                }
        }
        return sHouses.size();
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day03.txt", 2015);
        File test = Day01.getResource("test03.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        String directions = lines.get(0);

        char[] dir = directions.toCharArray();

        HashSet<Point> sHouses = new HashSet<>();
        HashSet<Point> rHouses = new HashSet<>();
        Point rLocation = new Point(0,0);
        Point sLocation = new Point(0,0);
        rHouses.add(new Point());
        sHouses.add(new Point());
        int counter = 0;        //santa moves on even positions, robosanta moves on odds

        for(char c : dir) {
            if(counter % 2 == 0) {
                switch (c) {
                    case '^' :
                        sLocation.translate(0, 1);
                        sHouses.add(new Point(sLocation));
                        break;

                    case '>' :
                        sLocation.translate(1,0);
                        sHouses.add(new Point(sLocation));
                        break;

                    case '<' :
                        sLocation.translate(-1, 0);
                        sHouses.add(new Point(sLocation));
                        break;

                    case 'v' :
                        sLocation.translate(0, -1);
                        sHouses.add(new Point(sLocation));
                        break;
                }
            } else {
                switch (c) {
                    case '^' :
                        rLocation.translate(0, 1);
                        rHouses.add(new Point(rLocation));
                        break;

                    case '>' :
                        rLocation.translate(1,0);
                        rHouses.add(new Point(rLocation));
                        break;

                    case '<' :
                        rLocation.translate(-1, 0);
                        rHouses.add(new Point(rLocation));
                        break;

                    case 'v' :
                        rLocation.translate(0, -1);
                        rHouses.add(new Point(rLocation));
                        break;
                }
            }
            counter++;
        }
        sHouses.addAll(rHouses);

        return sHouses.size();
    }
}