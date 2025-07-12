package main.java.adventofcode.year19.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year19.Day2019;

import java.io.File;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Day03 extends Day2019 {
    public Day03() {
        super(3);
    }

    public static void main(String[] args) {
        new Day03().printParts();
    }

    public static void printPoints(HashSet<Point> points) {
        for(Point p : points) {
            System.out.print("(" + p.x + ", " + p.y + ") ");
        }
        System.out.println();
    }

    @Override
    public Object part1() {
        File in = Day03.getResource("day03.txt", 2019);
        File test = Day03.getResource("test03.txt", 2019);

        var wires = Utils.realOrTest(in, test);

        ArrayList<HashSet<Point>> positions = new ArrayList<>();

        for(String wire : wires) {
            String[] individuals = wire.split(",");
            HashSet<Point> points = new HashSet<>();
            Point position = new Point(0,0);
            for(String dir : individuals) {
                char first = dir.charAt(0);
                int amount = Integer.parseInt(dir.substring(1));
                if(first == 'U') {
                    for(int y = amount; y > 0; y--) {
                        points.add(new Point((int)position.getX(), (int)position.getY()+y));
                    }
                    position.translate(0, amount);
                } else if(first == 'R') {
                    for(int x = amount; x > 0; x--) {
                        points.add(new Point((int)position.getX()+x, (int)position.getY()));
                    }
                    position.translate(amount, 0);
                } else if(first == 'L') {
                    for(int x = amount; x > 0; x--) {
                        points.add(new Point((int)position.getX()-x, (int)position.getY()));
                    }
                    position.translate(-amount, 0);
                } else if(first == 'D'){
                    for(int y = amount; y > 0; y--) {
                        points.add(new Point((int)position.getX(), (int)position.getY()-y));
                    }
                    position.translate(0, -amount);
                }
            }
            positions.add(points);
        }

        var set1 = positions.get(0);
        var set2 = positions.get(1);

        set1.retainAll(set2);
        set1.remove(new Point(0,0));

        ArrayList<Integer> distances = new ArrayList<>();
        for(Point p : set1) {
            distances.add((int)(Math.abs(p.getX()) + Math.abs(p.getY())));
        }
        System.out.println(distances);
        return Collections.min(distances);
    }

    @Override
    public Object part2() {
        File in = Day03.getResource("day03.txt", 2019);
        File test = Day03.getResource("test03.txt", 2019);

        var wires = Utils.realOrTest(in, test);

        ArrayList<HashSet<Point>> positions = new ArrayList<>();

        for(String wire : wires) {
            String[] individuals = wire.split(",");
            HashSet<Point> points = new HashSet<>();
            Point position = new Point(0,0);
            for(String dir : individuals) {
                char first = dir.charAt(0);
                int amount = Integer.parseInt(dir.substring(1));
                if(first == 'U') {
                    for(int y = amount; y > 0; y--) {
                        points.add(new Point((int)position.getX(), (int)position.getY()+y));
                    }
                    position.translate(0, amount);
                } else if(first == 'R') {
                    for(int x = amount; x > 0; x--) {
                        points.add(new Point((int)position.getX()+x, (int)position.getY()));
                    }
                    position.translate(amount, 0);
                } else if(first == 'L') {
                    for(int x = amount; x > 0; x--) {
                        points.add(new Point((int)position.getX()-x, (int)position.getY()));
                    }
                    position.translate(-amount, 0);
                } else if(first == 'D'){
                    for(int y = amount; y > 0; y--) {
                        points.add(new Point((int)position.getX(), (int)position.getY()-y));
                    }
                    position.translate(0, -amount);
                }
            }
            positions.add(points);
        }

        var set1 = positions.get(0);
        var set2 = positions.get(1);

        set1.retainAll(set2);
        set1.remove(new Point(0,0));

        HashMap<Point, Integer> stepCounts = new HashMap<>();
        for (Point p : set1) {
            stepCounts.put(p, 0);
        }

        for(Point p : stepCounts.keySet()) {
            // Find no. steps to get there on wire 1
            Point position = new Point(0,0);
            var wire1 = wires.get(0);
            int steps1 = 0;
            outerloop:
            for(String s : wire1.split(",")) {
                int amount = Integer.parseInt(s.substring(1));
                if(s.charAt(0) == 'U') {
                    for(int y = amount; y > 0; y--) {
                        position.translate(0, 1);
                        steps1++;
                        if(position.equals(p)) {
                            break outerloop;
                        }
                    }
                } else if(s.charAt(0) == 'R') {
                    for(int x = amount; x > 0; x--) {
                        position.translate(1,0);
                        steps1++;
                        if(position.equals(p)) {
                            break outerloop;
                        }
                    }
                } else if(s.charAt(0) == 'D') {
                    for (int y = amount; y > 0; y--) {
                        position.translate(0, -1);
                        steps1++;
                        if (position.equals(p)) {
                            break outerloop;
                        }
                    }
                } else {
                    for (int x = amount; x > 0; x--) {
                        position.translate(-1, 0);
                        steps1++;
                        if (position.equals(p)) {
                            break outerloop;
                        }
                    }
                }
            }
            
            // Find no. steps to get there on wire 2
            position = new Point(0,0);
            var wire2 = wires.get(1);
            int steps2 = 0;
            outerloop:
            for(String s : wire2.split(",")) {
                int amount = Integer.parseInt(s.substring(1));
                if(s.charAt(0) == 'U') {
                    for(int y = amount; y > 0; y--) {
                        position.translate(0, 1);
                        steps2++;
                        if(position.equals(p)) {
                            break outerloop;
                        }
                    }
                } else if(s.charAt(0) == 'R') {
                    for(int x = amount; x > 0; x--) {
                        position.translate(1,0);
                        steps2++;
                        if(position.equals(p)) {
                            break outerloop;
                        }
                    }
                } else if(s.charAt(0) == 'D') {
                    for (int y = amount; y > 0; y--) {
                        position.translate(0, -1);
                        steps2++;
                        if (position.equals(p)) {
                            break outerloop;
                        }
                    }
                } else {
                    for (int x = amount; x > 0; x--) {
                        position.translate(-1, 0);
                        steps2++;
                        if (position.equals(p)) {
                            break outerloop;
                        }
                    }
                }
            }
            
            // Sum
            int sum = steps1 + steps2;

            // Store in stepCounts: `stepCounts.put(p, sum)`
            stepCounts.put(p, sum);
        }

        return Collections.min(stepCounts.values());
    }
}