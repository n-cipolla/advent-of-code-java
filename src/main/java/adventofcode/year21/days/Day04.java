package main.java.adventofcode.year21.days;

import com.sun.security.jgss.GSSUtil;
import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.*;

public class Day04 extends Day2021 {
    public Day04() {
        super(4);
    }

    public static void main(String[] args) {
        new Day04().printParts();
    }

    private static class Tuple {
        public int n;
        public boolean b;

        public Tuple(int n, boolean b) {
            this.n = n;
            this.b = b;
        }

        @Override
        public String toString() {
            return "n=" + n + " : b=" + (b ? "T" : "F");
        }
    }

    @Override
    public Object part1() {
        File input = Day04.getResource("day04.txt", 2021);
        File test = Day04.getResource("test04.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        ArrayList<Integer> calls = new ArrayList<>();
        for (String s : lines.get(0).split(",")) calls.add(Integer.parseInt(s));

        lines.remove(lines.get(1));
        lines.remove(lines.get(0));

        int numCards = lines.size() / 5;

        ArrayList<ArrayList<Tuple>> cards = new ArrayList<>();

        for (int i = 0; i < numCards; ++i) {
            ArrayList<Tuple> card = new ArrayList<>();
            for (int j = i * 6; j < lines.size(); j++) {
                if (lines.get(j).isEmpty()) {
                    break;
                } else {
                    ArrayList<String> arr = new ArrayList<String>(Arrays.asList(lines.get(j).split("\\s+")));
                    arr.removeIf(String::isEmpty);
                    for (var v : arr) card.add(new Tuple(Integer.parseInt(v), false));
                }
            }
            cards.add(card);
        }

        Optional<Integer> cardIndexInCards = Optional.of(0);
        int winningNum = 0;
        for (int num : calls) {
            cardIndexInCards = checkCards(cards, num);
            if (cardIndexInCards.isPresent()) {
                winningNum = num;
                break;
            }
        }

        ArrayList<Tuple> winningCard = cards.get(cardIndexInCards.get());

        int total = 0;
        for (Tuple t : winningCard) {
            if (!t.b) total += t.n;
        }
        return total * winningNum;
    }

    @Override
    public Object part2() {
        File input = Day04.getResource("day04.txt", 2021);
        File test = Day04.getResource("test04.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        ArrayList<Integer> calls = new ArrayList<>();
        for (String s : lines.get(0).split(",")) calls.add(Integer.parseInt(s));

        lines.remove(lines.get(1));
        lines.remove(lines.get(0));

        int numCards = lines.size() / 5;

        ArrayList<ArrayList<Tuple>> cards = new ArrayList<>();

        for (int i = 0; i < numCards; ++i) {
            ArrayList<Tuple> card = new ArrayList<>();
            for (int j = i * 6; j < lines.size(); j++) {
                if (lines.get(j).isEmpty()) {
                    break;
                } else {
                    ArrayList<String> arr = new ArrayList<String>(Arrays.asList(lines.get(j).split("\\s+")));
                    arr.removeIf(String::isEmpty);
                    for (var v : arr) card.add(new Tuple(Integer.parseInt(v), false));
                }
            }
            cards.add(card);
        }

        int winningNum = 0;
        ArrayList<BSTuple> winningCards = new ArrayList<>();

        for (int num : calls) {
            var indices = checkCards2(cards, num);
            if (indices.isPresent()) {
                winningNum = num;
                var indexes = indices.get();
                Collections.sort(indexes);
//                System.out.println("removing card(s) at indices: " + indexes);
                for(int i = indexes.size() - 1; i >= 0; i--) {
                    winningCards.add(new BSTuple(cards.get(indexes.get(i)), winningNum));
//                    System.out.println("Removing card at index: " + i);
                    cards.remove(cards.get(indexes.get(i)));
                }
            }
            if(cards.isEmpty()) break;
        }
        var lastVictory = winningCards.get(winningCards.size()-1).list;

        int total = 0;
        for (Tuple t : lastVictory) {
            if (!t.b) total += t.n;
        }
        return total * winningNum;
    }

    record BSTuple(ArrayList<Tuple> list, int num) {}

    private static void printCard(ArrayList<Tuple> card) {
        int count = 0;
        for (Tuple t : card) {
            if (count % 5 == 0) System.out.println();
            System.out.print(t + ", ");
            count++;
        }
        System.out.println();
    }

    private static Optional<ArrayList<Integer>> checkCards2(ArrayList<ArrayList<Tuple>> cards, int num) {
        // Toggling
        for (ArrayList<Tuple> card : cards) {
            for (Tuple t : card) if (t.n == num) t.b = true;
        }

        ArrayList<Integer> ret = new ArrayList<>();

        // Card checking
        for (int index = 0; index < cards.size(); index++) {
            // Check rows
            for (int i = 0; i < 25; i += 5) {
                if (!cards.get(index).isEmpty()) {
                    ArrayList<Tuple> subList = new ArrayList<>(cards.get(index).subList(i, i + 5));
                    if (checkBingo(subList)) ret.add(index);
                }
            }

            // Check cols
            for (int i = 0; i < 5; i++) {
                if (!cards.get(index).isEmpty()) {
                    ArrayList<Tuple> subList = new ArrayList<>();
                    subList.add(cards.get(index).get(i));
                    subList.add(cards.get(index).get(i + 5));
                    subList.add(cards.get(index).get(i + 10));
                    subList.add(cards.get(index).get(i + 15));
                    subList.add(cards.get(index).get(i + 20));
                    if (checkBingo(subList)) ret.add(index);
                }
            }
        }
        return (!ret.isEmpty() ? Optional.of(ret) : Optional.empty());
    }

    // Bingo's occur when... (array style)
    // 0-4, 5-9, 10-14, 15-19, 20-24 are all marked
    // 0, 5, 10, 15, 20 are all marked
    // 1, 6, 11, 16, 21 are all marked
    // etc.
    private static Optional<Integer> checkCards(ArrayList<ArrayList<Tuple>> cards, int num) {
        // Toggling
        for (ArrayList<Tuple> card : cards) {
            for (Tuple t : card) if (t.n == num) t.b = true;
        }

        // Card checking
        for (int index = 0; index < cards.size(); index++) {
            // Check rows
            for (int i = 0; i < 25; i += 5) {
                if (!cards.get(index).isEmpty()) {
                    ArrayList<Tuple> subList = new ArrayList<>(cards.get(index).subList(i, i + 5));
                    if (checkBingo(subList)) return Optional.of(index);
                }
            }

            // Check cols
            for (int i = 0; i < 5; i++) {
                if (!cards.get(index).isEmpty()) {
                    ArrayList<Tuple> subList = new ArrayList<>();
                    subList.add(cards.get(index).get(i));
                    subList.add(cards.get(index).get(i + 5));
                    subList.add(cards.get(index).get(i + 10));
                    subList.add(cards.get(index).get(i + 15));
                    subList.add(cards.get(index).get(i + 20));
                    if (checkBingo(subList)) return Optional.of(index);
                }
            }
        }
        return Optional.empty();
    }

    private static boolean checkBingo(ArrayList<Tuple> line) {
        int count = 0;
        for (Tuple t : line) {
            if (t.b) count++;
        }
        return count == 5;
    }
}

//27225 too high (pt2)