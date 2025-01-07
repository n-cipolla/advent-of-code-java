package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class Day04 extends Day2021 {
    public Day04() {
        super(4);
    }

    public static void main(String[] args) {
        new Day04().printParts();
    }

    @Override
    public Object part1() {
        File input = Day04.getResource("day04.txt", 2021);
        File test = Day04.getResource("test04.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        ArrayList<Integer> calls = new ArrayList<>();
        for(String s : lines.get(0).split(",")) calls.add(Integer.parseInt(s));

        lines.remove(lines.get(1));
        lines.remove(lines.get(0));

        lines.removeIf(String::isEmpty);

        int numCards = lines.size()/5;
        int cardSize = 5;

        ArrayList<ArrayList<String>> stringCards = new ArrayList<>();

        for(int i = 0; i < numCards; i++) {
            stringCards.add(new ArrayList<>(lines.subList(i*5, (i+1)*5)));
        }

        ArrayList<ArrayList<int[]>> cards = new ArrayList<>();
        for(int i = 0; i < numCards; i++) {
            ArrayList<int[]> card = new ArrayList<>();
            ArrayList<String> current = stringCards.get(i);
            for(String s : current) {
                var split = s.split("\\s+");
                ArrayList<Integer> decidingOnName = new ArrayList<>();
                for (String string : split) {
                    if (!string.isEmpty()) {
                        decidingOnName.add(Integer.parseInt(string.trim()));
                    }
                }
                int[] row = new int[5];
                for(int j = 0; j < 5; ++j) row[j] = decidingOnName.get(j);
                card.add(row);
            }
            cards.add(card);
        }

//        ArrayList<int[]> winningCard = null;
//        for(int i = 0; i < calls.size(); ++i) {
//            int currentNum = calls.get(i);
//            if(i < 4) {
//                replace(cards, currentNum);
//            } else {
//                replace(cards, currentNum);
//                if(checkBingo(cards).isEmpty()) {
//                    winningCard = checkBingo(cards).get();
//                    break;
//                }
//            }
//        }

        return 2021;
    }




    @Override
    public Object part2() {
//        File input = Day04.getResource("day04.txt", 2021);
//        File test = Day04.getResource("test04.txt", 2021);
//
//        var lines = Utils.realOrTest(input, test);
        return 2021;
    }
}