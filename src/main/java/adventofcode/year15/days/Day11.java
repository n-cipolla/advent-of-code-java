package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.*;

public class Day11 extends Day2015 {
    public Day11() {
        super(11);
    }

    public static void main(String[] args) {
        new Day11().printParts();
    }

    @Override
    public Object part1() {
        File in = Day11.getResource("day11.txt", 2015);
        File test = Day11.getResource("test11.txt", 2015);

        var lines = Utils.realOrTest(in, test);

        String input = lines.get(0);

        char[] password = input.toCharArray();

        int last = password.length-1;

        while(!check(constructPassword(password))) {

            password[last] = (char)(password[last]+1);

            for(int i = last; i >= 0 && password[i] > 'z'; --i) {
                password[i] = 'a';
                password[i-1] = (char) (password[i-1] + 1);
            }
        }
        return constructPassword(password);
    }

    @Override
    public Object part2() {
        File in = Day11.getResource("day11.txt", 2015);
        File test = Day11.getResource("test11.txt", 2015);

        var lines = Utils.realOrTest(in, test);

        String input = lines.get(0);

        char[] password = input.toCharArray();

        int last = password.length-1;

        while(!check(constructPassword(password))) {

            password[last] = (char)(password[last]+1);

            for(int i = last; i >= 0 && password[i] > 'z'; --i) {
                password[i] = 'a';
                password[i-1] = (char) (password[i-1] + 1);
            }
        }

        last = password.length-1;

        // Do this for a guaranteed reset of the password ONCE (usefull for 2nd time)
         password[last] = (char)(password[last]+1);

         for(int i = last; i >= 0 && password[i] > 'z'; --i) {
            password[i] = 'a';
            password[i-1] = (char) (password[i-1] + 1);
         }

        while(!check(constructPassword(password))) {

            password[last] = (char)(password[last]+1);

            for(int i = last; i >= 0 && password[i] > 'z'; --i) {
                password[i] = 'a';
                password[i-1] = (char) (password[i-1] + 1);
            }
        }
        return constructPassword(password);
    }

    private static boolean check(String password) {
        if (password.contains("i") || password.contains("o") || password.contains("l")) return false;

        boolean increasingStraight = false;
        for(int i = 0; i < password.length()-2; i++) {
            char first = password.charAt(i);
            char second = password.charAt(i+1);
            char third = password.charAt(i+2);
            if((first+2 == second+1) && (first+2 == third)) {
                increasingStraight = true;
                break;
            }
        }

        boolean doubles = false;
        int doubleCount = 0;
        HashSet<Character> setOfDoubles = new HashSet<Character>();

        for(int i = 0; i<password.length()-1; ++i) {
            char first = password.charAt(i);
            char next = password.charAt(i+1);
            if(first == next && !setOfDoubles.contains(first)) {
                doubleCount++;
                setOfDoubles.add(first);
            }
        }
        if(doubleCount >= 2) doubles = true;

        if(increasingStraight && doubles) {
//            System.out.println("password: " + password + " passed");
//            System.out.println("Double letters are: " + setOfDoubles);
            return true;
        }
        else return false;
    }

    private static String constructPassword(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for(char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}