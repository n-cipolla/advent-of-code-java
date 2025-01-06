package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Day19 extends Day2015 {
    public Day19() {
        super(19);
    }

    public static void main(String[] args) {
        new Day19().printParts();
    }

    @Override
    public Object part1() {
        File in = Day19.getResource("day19.txt", 2015);
        File compound = Day19.getResource("day19compound.txt", 2015);
        File test = Day19.getResource("test19.txt", 2015);

        String start = Utils.readLines(compound).get(0);
        var input = Utils.realOrTest(in, test);

        HashSet<String> compounds = new HashSet<>();
        for(var v : input) {
            compounds.add(v.split(" ")[0]);
        }

        HashMap<String, ArrayList<String>> replacements = new HashMap<>();
        for(var v : compounds) {
            var lst = new ArrayList<String>();
            for(int i = 0; i < input.size(); ++i) {
                if(input.get(i).startsWith(v)) {
                    lst.add(input.get(i).split(" ")[2]);
                }
            }
            replacements.put(v, lst);
        }

        System.out.println(start);

        ArrayList<String> startList = new ArrayList<>();
        for(int i = 0; i < start.length(); ++i) {
            if(compounds.contains(start.substring(i, i+1))) {
                startList.add(start.substring(i, i+1));
            } else if(compounds.contains(start.substring(i, i+2))) {
                startList.add(start.substring(i, i+2)); i+=1;
            } else {
                startList.add(start.substring(i, i+1));
            }
        }
        System.out.println(startList);

        HashSet<String> options = new HashSet<>();

        int index = -1;
        for(String s : startList) {
            index++;
            if(replacements.containsKey(s)) {
                for(String rep : replacements.get(s)) {
                    @SuppressWarnings("unchecked")
                    ArrayList<String> arr = (ArrayList<String>) startList.clone();
                    arr.set(index, rep);
                    options.add(convertToString(arr));
                }
            }
        }
        return options.size();
    }

    @Override
    public Object part2() {
        File in = Day19.getResource("day19.txt", 2015);
        File compound = Day19.getResource("day19compound.txt", 2015);
        File test = Day19.getResource("test19.txt", 2015);

        String start = Utils.readLines(compound).get(0);
        var input = Utils.realOrTest(in, test);
        return "Not yet implemented.";
    }

    public static String convertToString(ArrayList<String> input) {
        StringBuilder sb = new StringBuilder();
        for(var v : input) sb.append(v);
        return sb.toString();
    }
}