package main.java.adventofcode.year24.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year24.Day2024;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day05 extends Day2024 {
    public Day05() {
        super(5);
    }

    public static void main(String[] args) {
        new Day05().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day05.txt", 2024);
        File test = Day01.getResource("test05.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        ArrayList<String> sequences = new ArrayList<>();
        int index = 0;

        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).isEmpty()) {
                index = i;
                break;
            } else {
                sequences.add(lines.get(i));
            }
        }

        ArrayList<ArrayList<Integer>> pages = new ArrayList<>();
        for(int i = index+1; i < lines.size(); i++) {
            var tmp = lines.get(i);
            var arr = tmp.split(",");
            var tmpLst = new ArrayList<Integer>();
            for(var v : arr) tmpLst.add(Integer.parseInt(v));
            pages.add(tmpLst);
        }

        HashMap<Integer, ArrayList<Integer>> instructions = new HashMap<>();
        for(String s : sequences) {
            var numbers = s.split("\\|");
            if(instructions.containsKey(Integer.parseInt(numbers[0]))) {
                var tmp = instructions.get(Integer.parseInt(numbers[0]));
                tmp.add(Integer.parseInt(numbers[1]));
                instructions.put(Integer.parseInt(numbers[0]), tmp);
            } else {
                var tmp = new ArrayList<Integer>();
                tmp.add(Integer.parseInt(numbers[1]));
                instructions.put(Integer.parseInt(numbers[0]), tmp);
            }
        }

        ArrayList<ArrayList<Integer>> valid = new ArrayList<>();

        for(ArrayList<Integer> page : pages) {
            for(int i = 0; i < page.size(); i++) {
                boolean good = true;
                for(int j = i; j < page.size(); j++) {
                    if(!(instructions.containsKey(page.get(i)) && instructions.get(page.get(i)).containsAll(page.subList(j, page.size()-1)))) {
                        good = false;
                        break;
                    }
                }
                if(good) valid.add(page);
            }
        }
        System.out.println(valid);
        int total = 0;
        for(ArrayList<Integer> page : valid) {
            total += page.get(page.size()/2);
        }

        return total + "is incorrect.";
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day05.txt", 2024);
        File test = Day01.getResource("test05.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        ArrayList<String> sequences = new ArrayList<>();
        int index = 0;

        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).isEmpty()) {
                index = i;
                break;
            } else {
                sequences.add(lines.get(i));
            }
        }

        ArrayList<ArrayList<Integer>> pages = new ArrayList<>();
        for(int i = index+1; i < lines.size(); i++) {
            var tmp = lines.get(i);
            var arr = tmp.split(",");
            var tmpLst = new ArrayList<Integer>();
            for(var v : arr) tmpLst.add(Integer.parseInt(v));
            pages.add(tmpLst);
        }

        HashMap<Integer, ArrayList<Integer>> instructions = new HashMap<>();
        for(String s : sequences) {
            var numbers = s.split("\\|");
            if(instructions.containsKey(Integer.parseInt(numbers[0]))) {
                var tmp = instructions.get(Integer.parseInt(numbers[0]));
                tmp.add(Integer.parseInt(numbers[1]));
                instructions.put(Integer.parseInt(numbers[0]), tmp);
            } else {
                var tmp = new ArrayList<Integer>();
                tmp.add(Integer.parseInt(numbers[1]));
                instructions.put(Integer.parseInt(numbers[0]), tmp);
            }
        }
        return "Not yet implemented.";
    }
}