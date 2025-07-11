package main.java.adventofcode.year23.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year23.Day2023;

import java.io.File;
import java.util.ArrayList;

public class day04 extends Day2023 {
    public day04() {
        super(4);
    }

    private static ArrayList<Integer> winning(String card) {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        String chunk1 = card.split(":")[0];
        card = card.replace(chunk1 + ": ", "");
        String winners = card.split("\\|")[0];
        for(int i = 0; i < winners.length()-1; i=i+3) {
            ret.add(Integer.parseInt(winners.substring(i, i+2).strip()));
        }
        return ret;
    }

    private static int cardNum(String card) {
        String chunk1 = card.split(":")[0];
        return Integer.parseInt(chunk1.split(" ")[chunk1.split(" ").length-1]);
    }

    private static ArrayList<Integer> myNums(String card) {
        var ret = new ArrayList<Integer>();

        String chunk1 = card.split(":")[0];
        card = card.replace(chunk1 + ": ", "");
        String chunk2 = card.split("\\|")[0];
        String myNums = card.replace(chunk2 + "| ", "");
        for(int i = 0; i < myNums.length()-1; i=i+3) {
            ret.add(Integer.parseInt(myNums.substring(i, i+2).strip()));
        }
        return ret;
    }

    public static void main(String[] args) {
        new day04().printParts();
    }

    @Override
    public Object part1() {
        File in = day04.getResource("day04.txt", 2023);
        File test = day04.getResource("test04.txt", 2023);

        var input = Utils.realOrTest(in, test);

        int total = 0;
        for(String card : input) {
           var winning = winning(card);
           var myNums = myNums(card);
           int winningCount = 0;
           for(int num : myNums) {
              if(winning.contains(num)) winningCount++;
           }
           total += (int) Math.pow(2, winningCount-1);
        }
        return total;
    }

    @Override
    public Object part2() {
        File in = day04.getResource("day04.txt", 2023);
        File test = day04.getResource("test04.txt", 2023);

        var input = Utils.realOrTest(in, test);

        for(String card : input) {
            var winning = winning(card);
            var myNums = myNums(card);
            int matches = 0;
            for(int num : myNums) {
                if(winning.contains(num)) matches++;
            }
        }

        return 2023;
    }
}