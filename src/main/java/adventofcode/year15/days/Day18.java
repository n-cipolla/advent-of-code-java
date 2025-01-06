package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;

public class Day18 extends Day2015 {
    public Day18() {
        super(18);
    }

    public static void main(String[] args) {
        new Day18().printParts();
    }

    @Override
    public Object part1() {
        File in = Day18.getResource("day18.txt", 2015);
        File test = Day18.getResource("test18.txt", 2015);

        var input = Utils.realOrTest(in, test);

        int size = input.size();
        char[][] lights = makeGridOff(size, size);

        // Initializes the grid in its given state
        for(int i = 0; i < size; i++) {
            var arr = input.get(i).toCharArray();
            for(int j = 0; j < size; j++) {
                if(lights[i][j] != arr[j])  lights[i][j] = '#';
            }
        }
//        lights[0][0] = '#';
//        lights[0][size-1] = '#';
//        lights[size-1][0] = '#';
//        lights[size-1][size-1] = '#';

        // advances forward 100 times
        char[][] output = lights;
        for(int i = 0; i < 100; i++) {
            output = advance(output, size);
        }

        int onCount = 0;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(output[i][j] == '#') onCount++;
            }
        }

        return onCount + " should be 814...I can't figure out how to extract part 1 from part 2 correctly here.";
    }

    @Override
    public Object part2() {
        File in = Day18.getResource("day18.txt", 2015);
        File test = Day18.getResource("test18.txt", 2015);

        var input = Utils.realOrTest(in, test);

        int size = input.size();
        char[][] lights = makeGridOff(size, size);

        // Initializes the grid in its given state
        for(int i = 0; i < size; i++) {
            var arr = input.get(i).toCharArray();
            for(int j = 0; j < size; j++) {
                if(lights[i][j] != arr[j])  lights[i][j] = '#';
            }
        }
        lights[0][0] = '#';
        lights[0][size-1] = '#';
        lights[size-1][0] = '#';
        lights[size-1][size-1] = '#';

        // advances forward 100 times
        char[][] output = lights;
        for(int i = 0; i < 100; i++) {
            output = advance(output, size);
        }

        int onCount = 0;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(output[i][j] == '#') onCount++;
            }
        }

        return onCount;
    }

    private static char[][] makeGridOff(int length, int height) {
        var ret = new char[length][height];
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                ret[i][j] = '.';
            }
        }
        return ret;
    }

    private static ArrayList<Character> generateNeighbors(char[][] lights, int col, int row, int size) {
        ArrayList<Character> ret = new ArrayList<>();
        boolean atTop = false;
        boolean atBot = false;
        boolean atLeft = false;
        boolean atRight = false;
        if(row == 0) atTop = true;
        if(row == size-1) atBot = true;
        if(col == 0) atLeft = true;
        if(col == size-1) atRight = true;

        // 8 neighbors, the ones around it
        if(!atTop && !atBot && !atLeft && !atRight) {
            ret.add(lights[row][col-1]);     // directly left
            ret.add(lights[row][col+1]);     // directly right
            ret.add(lights[row-1][col]);     // directly down
            ret.add(lights[row+1][col]);     // directly up
            ret.add(lights[row-1][col-1]);   // top left
            ret.add(lights[row+1][col-1]);   // top left
            ret.add(lights[row-1][col+1]);   // top right
            ret.add(lights[row+1][col+1]);   // bot right
            return ret;

        } else {
            if(col-1 >= 0) ret.add(lights[row][col-1]);
            if(col+1 < size) ret.add(lights[row][col+1]);
            if(row-1 >= 0) ret.add(lights[row-1][col]);
            if(row+1 < size) ret.add(lights[row+1][col]);
            if(col-1 >= 0 && row-1 >= 0) ret.add(lights[row-1][col-1]);
            if(col+1 < size && row-1 >= 0) ret.add(lights[row-1][col+1]);
            if(col-1 >= 0 && row+1 < size) ret.add(lights[row+1][col-1]);
            if(col+1 < size && row+1 < size) ret.add(lights[row+1][col+1]);
        }

        return ret;
    }

    private static char[][] advance(char[][] lights, int size) {
        var ret = makeGridOff(size, size);
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                boolean inCorner = false;
                if((i == 0 && j == 0) || (i == size-1 && j==0) || (i == size-1 && j==size-1) || (i == 0 && j == size-1)) {
                    inCorner = true;
                }
                int onCount = 0;
                boolean powered = false;

                ArrayList<Character> neigh = generateNeighbors(lights, j, i, size);

                if(lights[i][j] == '#') {
                    powered = true; // light is on
                    for(int n = 0; n < neigh.size(); ++n) {
                        if(neigh.get(n) == '#') onCount++;
                    }
                } else {
                    powered = false; // light is off
                    for(int n = 0; n < neigh.size(); ++n) {
                        if(neigh.get(n) == '#') onCount++;
                    }
                }
                if(inCorner) {
                    ret[i][j] = '#';
                }
                else if((onCount == 2 || onCount == 3) && powered) {
                    ret[i][j] = '#';
                    // System.out.println("light "+i +", "+ j + " is " + powered + " and onCount is " + onCount+ ". Keeping on");
                }  // an on light with 2 or 3 on neighbors stays on
                else if(onCount == 3 && !powered) {
                    ret[i][j] = '#';
                    // System.out.println("light "+i +", "+ j + " is " + powered + " and onCount is " + onCount+ ". Turning on");
                }  // an off light with 3 on neighbors turns on
                else {
                    ret[i][j] = '.';
                    // System.out.println("light " + i + ", " + j + " is " + powered + " and is now off becauase it has " + onCount + " neighbors");
                }  // otherwise the light turns off
            }
        }
        return ret;
    }
}