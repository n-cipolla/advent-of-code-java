package main.java.adventofcode.year23.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year23.Day2023;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class day01 extends Day2023 {
    public day01() {
        super(1);
    }

    public static void main(String[] args) {
        new day01().printParts();
    }

    @Override
    public Object part1() {
        File in = day01.getResource("day01.txt", 2023);
        File test = day01.getResource("test01.txt", 2023);

        var input = Utils.realOrTest(in, test);

        ArrayList<Integer> nums = new ArrayList<>();
        for(String s : input) {
            String justNums = s.replaceAll("[a-zA-Z]", "");
            String number = String.valueOf(justNums.charAt(0)) + String.valueOf(justNums.charAt(justNums.length()-1));
            nums.add(Integer.parseInt(number));
        }

        int sum = 0;
        for(int num : nums) {
            sum += num;
        }

        return sum;
    }

    @Override
    public Object part2() {
        File in = day01.getResource("day01.txt", 2023);
        File test = day01.getResource("test01.txt", 2023);

        HashMap<String, String> numbers = new HashMap<>();
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");

        var input = Utils.realOrTest(in, test);

        ArrayList<Integer> nums = new ArrayList<>();

        for(String s : input) {
            for(String key : numbers.keySet()) {
                if(s.contains(key)) {
                    s = s.replaceAll(key, key+numbers.get(key)+key);
                }
            }
            String justNums = s.replaceAll("[a-zA-Z]", "");
            String number = String.valueOf(justNums.charAt(0)) + String.valueOf(justNums.charAt(justNums.length()-1));
            nums.add(Integer.parseInt(number));
        }

        int sum = 0;
        for(int num : nums) {
            sum += num;
        }

        return sum;
    }
}