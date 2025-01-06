package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Day15 extends Day2015 {
    public Day15() {
        super(15);
    }

    public static void main(String[] args) {
        new Day15().printParts();
    }

    @Override
    public Object part1() {
        return "Originally implemented in as a one part solution which was not separated.";
    }

    @Override
    public Object part2() {
        File in = Day01.getResource("day15.txt", 2015);
        File test = Day01.getResource("test15.txt", 2015);

        var input = Utils.realOrTest(in, test);

        HashMap<String, Integer> amounts = new HashMap<>();
        for(int i = 0; i < input.size(); ++i) {
            amounts.put(input.get(i).split(":")[0], 0);
        }

        HashMap<String, ArrayList<Integer>> properties = new HashMap<>();
        int counter = 0;
        for(String i : amounts.keySet()) {
            properties.put(i, calcProperties(input.get(counter)));
            counter++;
        }

        HashMap<Integer, String> encodings = new HashMap<>();
        for(int i = 0; i < properties.keySet().size(); ++i) {
            encodings.put(i, input.get(i).split(":")[0]);
        }

        // Hardcoded unfortunately (works with exactly 4 things)
        HashSet<ArrayList<Integer>> possibilities = new HashSet<>();
        for(int i = 0; i <= 100; ++i) {
            for(int j = 0; j <= 100-i; ++j) {
                for(int k = 0; k <= 100-j-i; ++k) {
                    for(int l = 0; l <= 100-k-j-i; ++l) {
                        // System.out.println("i: " + i+", j: " + j + ", k: " + k + ", l: " + l);
                        if(i + j + k + l == 100) {
                            ArrayList<Integer> tmp = new ArrayList<>();
                            tmp.add(i); tmp.add(j); tmp.add(k); tmp.add(l);
                            possibilities.add(tmp);
                        }
                    }
                }
            }
        }

        purge(possibilities);

        // reconfiguring properties into cdftc
        ArrayList<ArrayList<Integer>> cdftc = new ArrayList<>();
        ArrayList<Integer> cap = new ArrayList<>();
        ArrayList<Integer> dur = new ArrayList<>();
        ArrayList<Integer> fla = new ArrayList<>();
        ArrayList<Integer> tex = new ArrayList<>();
        ArrayList<Integer> cal = new ArrayList<>();

        for(var v : properties.keySet()) {
            var working = properties.get(v);
            cap.add(working.get(0));
            dur.add(working.get(1));
            fla.add(working.get(2));
            tex.add(working.get(3));
            cal.add(working.get(4));
        }
        cdftc.add(cap); cdftc.add(dur); cdftc.add(fla); cdftc.add(tex); cdftc.add(cal);

        int maxScore = 0;
        for(var combo : possibilities) {
            int result = calcMaxValue(combo, cdftc);
            if(result > maxScore) maxScore = result;
        }
        return maxScore;
    }

    private static ArrayList<Integer> calcProperties(String input) {
        var ret = new ArrayList<Integer>();
        input = input.replace(",", "");
        var arr = input.split(" ");
        for(int i = 2; i <= arr.length; i+=2) {
            ret.add(Integer.valueOf(arr[i]));
        }
        return ret;
    }

    private static int calcMaxValue(ArrayList<Integer> combo, ArrayList<ArrayList<Integer>> cdftc) {

        int total = 1;
        boolean calories = true;

        for(int i = 0; i < cdftc.size(); ++i) {
            if(i == cdftc.size()-1) {
                // code for calorie counting here!
                int num = 0;
                for(int j = 0; j < combo.size(); ++j) {
                    int tmp = combo.get(j) * cdftc.get(i).get(j);
                    num += combo.get(j) * cdftc.get(i).get(j);
                    calories = num == 500;
                }

            } else {
                int num = 0;
                for(int j = 0; j < combo.size(); ++j) {
                    int tmp = combo.get(j) * cdftc.get(i).get(j);
                    num += combo.get(j) * cdftc.get(i).get(j);
                }
                // System.out.println(num);
                if(num < 0) num = 0;
                total *= num;
            }
        }

        if(calories) return total;
        else return 0;
    }

    private static void purge(HashSet<ArrayList<Integer>> possibilities) {
        possibilities.removeIf(Day15::hasDuplicates);
    }

    private static boolean hasDuplicates(ArrayList<Integer> list) {
        HashSet<Integer> seen = new HashSet<>();
        for(var elem : list) {
            if(!seen.add(elem)) return true;
        }
        return false;
    }
}