package main.java.adventofcode.year24.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year24.Day2024;

import java.io.File;
import java.util.ArrayList;


public class Day04 extends Day2024 {
    public Day04() {
        super(4);
    }

    public static void main(String[] args) {
        new Day04().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day04.txt", 2024);
        File test = Day01.getResource("test04.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        // Initialize the Variables
        int count = 0;

        // Standard then Reverse Reading Order
        for (String line : lines) {
            count += countOccurrences(generateSubstrings(line, 4), "XMAS");
            count += countOccurrences(generateSubstrings(reverse(line), 4), "XMAS");
        }

        // Make Vertical Grid
        int height = lines.size();
        int width = lines.get(0).length();

        ArrayList<String> transposed = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            StringBuilder vert = new StringBuilder();
            for (int j = 0; j < width; j++) {
                vert.append(lines.get(j).charAt(i));
            }
            transposed.add(vert.toString());
            vert.setLength(0);
        }

        // Standard then Reverse Reading Order (vertical)
        for (String line : transposed) {
            count += countOccurrences(generateSubstrings(line, 4), "XMAS");
            count += countOccurrences(generateSubstrings(reverse(line), 4), "XMAS");
        }

        // Make a grid of diagonals_forward starting in the top left (forward is / kind of diagonal)
        // (working)
        ArrayList<String> diagonals_forward = new ArrayList<>();

        for (int i = 0; i < width-1; i++) {
            StringBuilder diag = new StringBuilder();
            int xPos = i;
            int yPos = 0;
            while (xPos >= 0) {
                diag.append(lines.get(yPos).charAt(xPos));
                xPos--;
                yPos++;
            }
            diagonals_forward.add(diag.toString());
            diag.setLength(0);
        }

        // Work up, starting from bottom right (working)
        for (int i = width; i > 0; i--) {
            StringBuilder diag = new StringBuilder();
            int xPos = i;
            int yPos = lines.size() - 1;
            while (xPos <= lines.get(0).length() - 1) {
                diag.append(lines.get(yPos).charAt(xPos));
                xPos++;
                yPos--;
            }
            diagonals_forward.add(diag.toString());
            diag.setLength(0);
        }

        // Standard then Reverse Reading Order (diagonals_forward)
        for (String line : diagonals_forward) {
            count += countOccurrences(generateSubstrings(line, 4), "XMAS");
            count += countOccurrences(generateSubstrings(reverse(line), 4), "XMAS");
        }

        // Make grid of diagonals_backward starting in the top right (diagonal is \ kind of diagonal)
        // (working)
        ArrayList<String> diagonals_backward = new ArrayList<>();

        for (int i = lines.get(0).length() - 1; i >= 0; i--) {
            StringBuilder diag = new StringBuilder();
            int xPos = i;
            int yPos = 0;
            while (xPos <= lines.get(i).length() - 1) {
                diag.append(lines.get(yPos).charAt(xPos));
                xPos++;
                yPos++;
            }
            diagonals_backward.add(diag.toString());
            diag.setLength(0);
        }

        // Work up, starting from bottom left (working)
        for (int i = 0; i < width - 1; i++) {
            StringBuilder diag = new StringBuilder();
            int xPos = 0; // 0
            int yPos = lines.size() - 1 - i;
            while (yPos <= lines.get(0).length() - 1) {
                diag.append(lines.get(yPos).charAt(xPos));
                xPos++;
                yPos++;
            }
            diagonals_backward.add(diag.toString());
            diag.setLength(0);
        }

        // Standard then Reverse Reading Order (diagonals_backward)
        for (String line : diagonals_backward) {
            count += countOccurrences(generateSubstrings(line, 4), "XMAS");
            count += countOccurrences(generateSubstrings(reverse(line), 4), "XMAS");
        }
        return String.valueOf(count);
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day04.txt", 2024);
        File test = Day01.getResource("test04.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        int count = 0;

        int width = lines.get(0).length();
        int height = lines.size();

        for(int h = 1; h < height-1; h++) {
            for(int w = 1; w < width-1; w++) {
                if(lines.get(h).charAt(w) == 'A') {
//                    if(h-1 < 0 || h+1 >= height || w-1 < 0 || w+1 >= width) break;
                    char upLeft = lines.get(h-1).charAt(w-1);
                    char upRight= lines.get(h-1).charAt(w+1);
                    char downLeft = lines.get(h+1).charAt(w-1);
                    char downRight= lines.get(h+1).charAt(w+1);

                    if(upLeft == 'M' && upRight == 'M' && downLeft == 'S' && downRight == 'S') count++;
                    else if(upLeft == 'M' && downLeft == 'M' && upRight == 'S' && downRight == 'S') count++;
                    else if(upRight == 'M' && downRight == 'M' && upLeft == 'S' && downLeft == 'S') count++;
                    else if(downLeft == 'M' && downRight == 'M' && upLeft == 'S' && upRight == 'S') count++;
                }
            }
        }
        return String.valueOf(count);
    }

    public static ArrayList<String> generateSubstrings(String s, int size) {
        ArrayList<String> ret = new ArrayList<>();
        for(int i = 0; i < s.length() - size + 1; i++) {
            ret.add(s.substring(i, i + size));
        }
        return ret;
    }

    public static int countOccurrences(ArrayList<String> list, String s) {
        int count = 0;
        for(String str : list) {
            if(str.equals(s)) count++;
        }
        return count;
    }

    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = s.length()-1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}