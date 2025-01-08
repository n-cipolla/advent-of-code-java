package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Day10 extends Day2021 {
    public Day10() {
        super(10);
    }

    public static void main(String[] args) {
        new Day10().printParts();
    }

    @Override
    public Object part1() {
        File input = Day10.getResource("day10.txt", 2021);
        File test = Day10.getResource("test10.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        HashMap<Character, Character> matchingBrackets = new HashMap<>();
        matchingBrackets.put(']', '[');
        matchingBrackets.put('}', '{');
        matchingBrackets.put(')', '(');
        matchingBrackets.put('>', '<');

        int total = 0;
        for(String line : lines) {
            Stack<Character> stack = new Stack<>();
            for(char c : line.toCharArray()) {
                if(matchingBrackets.containsValue(c)) {
                    stack.push(c);
                } else if (matchingBrackets.containsKey(c)) {
                    char top = stack.pop();
                    if(top != matchingBrackets.get(c)) {
                        if(c == ')') {
                            total += 3;
                            break;
                        }
                        else if(c == ']') {
                            total += 57;
                            break;
                        }
                        else if(c == '}') {
                            total += 1197;
                            break;
                        }
                        else if(c == '>') {
                            total += 25137;
                            break;
                        }
                    }
                }
            }
        }

        return total;
    }

    @Override
    public Object part2() {
        File input = Day05.getResource("day10.txt", 2021);
        File test = Day05.getResource("test10.txt", 2021);

        var lines = Utils.realOrTest(input, test);
        HashMap<Character, Character> matchingBrackets = new HashMap<>();
        matchingBrackets.put(']', '[');
        matchingBrackets.put('}', '{');
        matchingBrackets.put(')', '(');
        matchingBrackets.put('>', '<');

        HashMap<Character, Character> reversedBrackets = new HashMap<>();
        reversedBrackets.put('[', ']');
        reversedBrackets.put('{', '}');
        reversedBrackets.put('(', ')');
        reversedBrackets.put('<', '>');

        HashMap<Character, Integer> points = new HashMap<>();
        points.put(')', 1);
        points.put(']', 2);
        points.put('}', 3);
        points.put('>', 4);

        ArrayList<Integer> indicesToRemove = new ArrayList<>();
        int index = -1;
        for(String line : lines) {
            index++;
            Stack<Character> stack = new Stack<>();
            for(char c : line.toCharArray()) {
                if(matchingBrackets.containsValue(c)) {
                    stack.push(c);
                } else if (matchingBrackets.containsKey(c)) {
                    char top = stack.pop();
                    if(top != matchingBrackets.get(c)) {
                        indicesToRemove.add(index);
                    }
                }
            }
        }
        Collections.sort(indicesToRemove);
        for(int i = indicesToRemove.size()-1; i >= 0; i--) {
            lines.remove(lines.get(indicesToRemove.get(i)));
        }

        ArrayList<Long> scores = new ArrayList<>();
        for(String line : lines) {
            String extras = complete(line, matchingBrackets, reversedBrackets);
            long score = 0;
            for(char c : extras.toCharArray()) {
                score *= 5;
                score += points.get(c);
            }
            scores.add(score);
        }
        System.out.println(scores);
        Collections.sort(scores);
        return scores.get(scores.size()/2);
    }

    // keys are closing; values are opening
    private static String complete(String line, HashMap<Character, Character> matchingBrackets, HashMap<Character, Character> reversedBrackets) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(char c : line.toCharArray()) {
            if(matchingBrackets.containsValue(c)) {
                stack.push(c);
            } else if (matchingBrackets.containsKey(c)) {
                stack.pop();
            }
        }
        while(!stack.isEmpty()) {
            sb.append(reversedBrackets.get(stack.pop()));
        }
        return sb.toString();
    }
}