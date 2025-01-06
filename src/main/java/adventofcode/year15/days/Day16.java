package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

public class Day16 extends Day2015 {
    public Day16() {
        super(16);
    }

    public static void main(String[] args) {
        new Day16().printParts();
    }

    /*
     * children: 3
     * cats: 7
     * samoyeds: 2
     * pomeranians: 3
     * akitas: 0
     * vizslas: 0
     * goldfish: 5
     * trees: 3
     * cars: 2
     * perfumes: 1
     */

    @Override
    public Object part1() {
        File in = Day16.getResource("day16.txt", 2015);
        File test = Day16.getResource("test16.txt", 2015);

        var input = Utils.realOrTest(in, test);

        HashSet<Integer> children = new HashSet<>();
        HashSet<Integer> cats = new HashSet<>();
        HashSet<Integer> samoyeds = new HashSet<>();
        HashSet<Integer> pomer = new HashSet<>();
        HashSet<Integer> aki = new HashSet<>();
        HashSet<Integer> viz = new HashSet<>();
        HashSet<Integer> gf = new HashSet<>();
        HashSet<Integer> trees = new HashSet<>();
        HashSet<Integer> cars = new HashSet<>();
        HashSet<Integer> perf = new HashSet<>();
        HashSet<Integer> sue = new HashSet<>();


        for (var v : input) {
            v = v.replace(":", "");
            v = v.replace(",", "");
            int num = Integer.parseInt(v.split(" ")[1]);

            // Regular cases:
            if (v.contains("children 3") || !v.contains("children")) children.add(num);
            if (v.contains("samoyeds 2") || !v.contains("samoyed")) samoyeds.add(num);
            if (!v.contains("akitas") || v.contains("akitas 0")) aki.add(num);
            if (!v.contains("vizslas") || v.contains("vizslas 0")) viz.add(num);
            if (v.contains("cars 2") || !v.contains("cars")) cars.add(num);
            if (v.contains("perfumes 1") || !v.contains("perfumes")) perf.add(num);
            if (v.contains("trees 3") || !v.contains("trees")) trees.add(num);
            if (v.contains("cats 7") || !v.contains("cats")) cats.add(num);
            if (v.contains("pomeranians 3") || !v.contains("pomeranians")) pomer.add(num);
            if (v.contains("goldfish 5") || !v.contains("goldfish")) gf.add(num);

            sue.add(num);


            sue.retainAll(children);
            sue.retainAll(samoyeds);
            sue.retainAll(aki);
            sue.retainAll(viz);
            sue.retainAll(cars);
            sue.retainAll(perf);
            sue.retainAll(trees);
            sue.retainAll(cats);
            sue.retainAll(pomer);
            sue.retainAll(gf);

        }
        return sue;
    }

    @Override
    public Object part2() {
        File in = Day16.getResource("day16.txt", 2015);
        File test = Day16.getResource("test16.txt", 2015);

        var input = Utils.realOrTest(in, test);

        HashSet<Integer> children = new HashSet<>();
        HashSet<Integer> cats = new HashSet<>();
        HashSet<Integer> samoyeds = new HashSet<>();
        HashSet<Integer> pomer = new HashSet<>();
        HashSet<Integer> aki = new HashSet<>();
        HashSet<Integer> viz = new HashSet<>();
        HashSet<Integer> gf = new HashSet<>();
        HashSet<Integer> trees = new HashSet<>();
        HashSet<Integer> cars = new HashSet<>();
        HashSet<Integer> perf = new HashSet<>();
        HashSet<Integer> sue = new HashSet<>();


        for(var v : input) {
            v = v.replace(":", "");
            v = v.replace(",", "");
            int num = Integer.parseInt(v.split(" ")[1]);
            //System.out.println("Sue " + num);

            // Regular cases:
            if(v.contains("children 3") || !v.contains("children")) children.add(num);
            if(v.contains("samoyeds 2") || !v.contains("samoyed")) samoyeds.add(num);
            if(!v.contains("akitas") || v.contains("akitas 0")) aki.add(num);
            if(!v.contains("vizslas") || v.contains("vizslas 0")) viz.add(num);
            if(v.contains("cars 2") || !v.contains("cars")) cars.add(num);
            if(v.contains("perfumes 1") || !v.contains("perfumes")) perf.add(num);

            // Weird ones:
            if(v.contains("cats")) {
                var stringList = new ArrayList<String>(Arrays.asList(v.split(" ")));
                int index = stringList.indexOf("cats");
                index++;
                int amt = Integer.parseInt(stringList.get(index));
                if(amt > 7) cats.add(num);
            } if(!v.contains("cats")) cats.add(num);

            if(v.contains("pomeranians")) {
                var stringList = new ArrayList<String>(Arrays.asList(v.split(" ")));
                int index = stringList.indexOf("pomeranians");
                index++;
                int amt = Integer.parseInt(stringList.get(index));
                if(amt < 3) pomer.add(num);
            } if(!v.contains("pomeranians")) pomer.add(num);

            if(v.contains("goldfish")) {
                var stringList = new ArrayList<String>(Arrays.asList(v.split(" ")));
                int index = stringList.indexOf("goldfish");
                index++;
                int amt = Integer.parseInt(stringList.get(index));
                if(amt < 5) gf.add(num);
            } if(!v.contains("goldfish")) gf.add(num);

            if(v.contains("trees")) {
                var stringList = new ArrayList<String>(Arrays.asList(v.split(" ")));
                int index = stringList.indexOf("trees");
                index++;
                int amt = Integer.parseInt(stringList.get(index));
                if(amt > 3) trees.add(num);
            } if(!v.contains("trees")) trees.add(num);

            sue.add(num);
        }

        sue.retainAll(children);
        sue.retainAll(cats);
        sue.retainAll(samoyeds);
        sue.retainAll(pomer);
        sue.retainAll(aki);
        sue.retainAll(viz);
        sue.retainAll(gf);
        sue.retainAll(trees);
        sue.retainAll(cars);
        sue.retainAll(perf);

        return sue;
    }
}