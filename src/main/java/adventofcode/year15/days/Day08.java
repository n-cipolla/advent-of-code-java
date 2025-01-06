package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day08 extends Day2015 {
    public Day08() {
        super(8);
    }

    public static void main(String[] args) {
        new Day08().printParts();
    }

    @Override
    public Object part1() {
        File in = Day01.getResource("day08.txt", 2015);
        File test = Day01.getResource("test08.txt", 2015);

        var input = Utils.realOrTest(in, test);
        int count = 0;
        for(String s : input) {
            count += s.toCharArray().length;
        }
        System.out.println("Number of Characters in String Code: " + count);

        int newCount = count;
        for(String s : input) {
            //System.out.println(s);
            newCount -= 2;
            //System.out.println("Sub 2 for start and end quotes");
            s = s.substring(1, s.length()-1);
            //System.out.println(s);
            for(int i = 0; i < s.toCharArray().length; ++i) {
                char[] arr = s.toCharArray();
                if(arr[i] == '\\' && arr[i+1] == 'x') {
                    newCount -= 3;
                    if(i+4 >= s.toCharArray().length) {break;} else {i += 3;}
                    //System.out.println("subtracting 3 for a hex char");
                }
                if (arr[i] == '\\' && arr[i+1] == '\\') {
                    newCount--;
                    //System.out.println("sub 1 for double slash");
                    if(i+2 >= s.toCharArray().length) {break;} else {i++;}
                }
                if (arr[i] == '\\' && arr[i+1] == '\"') {
                    newCount--;
                    //System.out.println("sub 1 for slash quote");
                    if(i+2 >= s.toCharArray().length) {break;} else {i++;}
                }
            }
        }
//        System.out.println("Total char count: "+count);
//        System.out.println("Total char count in memory: "+newCount);
//        System.out.println("Difference in count is: " + (count - newCount));

        return count - newCount;
    }

    @Override
    public Object part2() {
        File in = Day01.getResource("day08.txt", 2015);
        File test = Day01.getResource("test08.txt", 2015);

        var input = Utils.realOrTest(in, test);
        int count = 0;
        for(String s : input) {
            count += s.toCharArray().length;
        }
        System.out.println("Number of Characters in String Code: " + count);

        int newCount = count;
        for(String s : input) {
            //System.out.println(s);
            newCount -= 2;
            //System.out.println("Sub 2 for start and end quotes");
            s = s.substring(1, s.length()-1);
            //System.out.println(s);
            for(int i = 0; i < s.toCharArray().length; ++i) {
                char[] arr = s.toCharArray();
                if(arr[i] == '\\' && arr[i+1] == 'x') {
                    newCount -= 3;
                    if(i+4 >= s.toCharArray().length) {break;} else {i += 3;}
                    //System.out.println("subtracting 3 for a hex char");
                }
                if (arr[i] == '\\' && arr[i+1] == '\\') {
                    newCount--;
                    //System.out.println("sub 1 for double slash");
                    if(i+2 >= s.toCharArray().length) {break;} else {i++;}
                }
                if (arr[i] == '\\' && arr[i+1] == '\"') {
                    newCount--;
                    //System.out.println("sub 1 for slash quote");
                    if(i+2 >= s.toCharArray().length) {break;} else {i++;}
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s : input) {
            // Takes off the start and end quotes
            s = s.substring(1, s.length()-1);

            // Adds the first bit of escape chars for the start quote
            sb.append("\"\\\"");

            // Deals with the un fun stuff lol
            for(char c : s.toCharArray()) {
                if(String.valueOf(c).matches("[A-z]")) {
                    sb.append(c);
                }
                if(String.valueOf(c).matches("[0-9]")) {
                    sb.append(c);
                }
                if(String.valueOf(c).equals("\\")) {
                    sb.append("\\");
                }
                if(String.valueOf(c).equals("\"")) {
                    sb.append("\\\"");
                }
            }

            // Adds the final bit of escape chars for the close quote
            sb.append("\\\"\"");

            // Appends a new line for reading
            //sb.append("\n");
        }
//        System.out.println(sb);
//        System.out.println("Total char count in new strings: "+sb.length());
//        System.out.println("Difference is: " + (sb.length() - count));
        return sb.length() - count;
    }
}