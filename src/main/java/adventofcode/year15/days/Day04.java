package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.security.MessageDigest;

public class Day04 extends Day2015 {
    public Day04() {
        super(4);
    }

    public static void main(String[] args) {
        new Day04().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day04.txt", 2015);
        File test = Day01.getResource("test04.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        String in = lines.get(0);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            long index = 0;
            while(true) {
                String key = String.format("%s%d", in, index++);
                byte[] digest = md.digest(key.getBytes());
                if(bytesToHexString(digest).startsWith("00000")) {
                    break;
                }
            }
            return String.format("%d", index-1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day04.txt", 2015);
        File test = Day01.getResource("test04.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        String in = lines.get(0);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            long index = 0;
            while(true) {
                String key = String.format("%s%d", in, index++);
                byte[] digest = md.digest(key.getBytes());
                if(bytesToHexString(digest).startsWith("000000")) {
                    break;
                }
            }
            return String.format("%d", index-1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String bytesToHexString(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (byte value : b) {
            result.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}