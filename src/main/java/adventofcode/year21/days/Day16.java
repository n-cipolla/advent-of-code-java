package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Day16 extends Day2021 {
    public Day16() {
        super(16);
    }

    public static void main(String[] args) {
        new Day16().printParts();
    }

    @Override
    public Object part1() {
        File in = Day16.getResource("day16.txt", 2021);
        File test = Day16.getResource("test16.txt", 2021);
        File dict = Day16.getResource("dict16.txt", 2021);
        List<String> conversions = Utils.readLines(dict);

        HashMap<String, String> hexToBinary = new HashMap<>();
        for(String s : conversions) {
            String hex = s.split(" ")[0];
            String bin = s.split(" ")[2];
            hexToBinary.put(hex, bin);
        }

        var lines = Utils.realOrTest(in, test);
        StringBuilder sb = new StringBuilder();
        for(char c : lines.get(0).toCharArray()) {
            sb.append(hexToBinary.get(Character.toString(c)));
        }

        while(sb.charAt(sb.length()-1) == '0') {
            sb.replace(sb.length()-1, sb.length(), "");
        }

        String input = sb.toString();

        // first three bits encode the packet version
        // the next three bits encode the packet type ID

        int version = Integer.parseInt(input.substring(0, 3), 2);
        int packetID = Integer.parseInt(input.substring(3, 6),2);


        StringBuilder result = new StringBuilder();
        if(packetID == 4) {
        // Packets with type ID 4 represent a literal value
            for(int i = 6; i < input.length(); i+=5) {
                if(input.charAt(i) == '1') {
                    result.append(input, i+1, i+5);
                } else if (input.charAt(i) == '0'){
                    result.append(input, i+1, i+5);
                    break;
                }
            }
        } else {
        // Every other type of packet represents an operator that performs some calculation on one or more sub-packets
            if(input.charAt(6) == '1') {
                // If the length type ID is 1, then the next 11 bits are a number
                // that represents the number of sub-packets immediately contained
                // by this packet.


            } else if (input.charAt(6) == '0') {
                // If the length type ID is 0, then the next 15 bits are a number
                // that represents the total length in bits of the sub-packets
                // contained by this packet.
                int length = Integer.parseInt(input.substring(7, 22), 2);
                System.out.println(length);
            }
        }

        return 2021;
    }

    @Override
    public Object part2() {
//        File input = Day05.getResource("day05.txt", 2021);
//        File test = Day05.getResource("test05.txt", 2021);
//
//        var lines = Utils.realOrTest(input, test);
        return 2021;
    }
}