package main.java.adventofcode.year17.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year17.Day2017;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.util.List;

public class Day03 extends Day2017 {
    public Day03() {
        super(3);
    }

    public static void main(String[] args) {
        new Day03().printParts();
    }

    @Override
    public Object part1() {
        File in = Day03.getResource("Day03.txt", 2017);
        File test = Day03.getResource("Day03Test.txt", 2017);

        var input = Integer.parseInt(Utils.realOrTest(in, test).get(0));

        // Solution from: https://math.stackexchange.com/questions/4199847/finding-the-formula-for-the-ulam-spiral-starting-with-0-as-a-bijective-functio
        input--;

        double k = Math.ceil(0.5 * (Math.sqrt(input) - 1));
        double d = Math.pow(2*k + 1, 2) - input;

        if (d >= 0 && d <= 2 * k + 1) {
            return (Math.abs(-1 * k) + Math.abs(k + 1 - d));
        } else if (d > 2 * k + 1 && d <= 4 * k + 1) {
            return (Math.abs(-3 * k - 1 + d) + Math.abs(-1 * k));
        } else if (d > 4 * k + 1 && d <= 6 * k + 1) {
            return (Math.abs(k) + Math.abs(-5 * k - 1 + d));
        } else if (d > 6 * k + 1 && d < 8 * k + 1) {
            return (Math.abs(7 * k - 1 + d) + Math.abs(k));
        }
        return 0;
    }

    @Override
    public Object part2() {
        File in = Day03.getResource("Day03.txt", 2017);
        File test = Day03.getResource("Day03Test.txt", 2017);

        var input = Utils.realOrTest(in, test);
        return "Not yet implemented";
    }

    public class PointUtils {
        /**
         * Gets all points in a 1-wide radius from the specified point
         *
         * @param center The central point.
         * @return A list of points in a 1-wide radius.
         */
        public static List<Point> getPointsInRadius(Point center) {
            List<Point> points = new ArrayList<>();

            for(int dx = -1; dx <= 1; dx++) {
                for(int dy = -1; dy <= 1; dy++) {
                    if(dx == 0 && dy == 0) {
                        continue;
                    }
                    points.add(new Point(center.x + dx, center.y + dy));
                }
            }
            return points;
        }
    }
}

