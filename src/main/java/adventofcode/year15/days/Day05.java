package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day05 extends Day2015 {
    public Day05() {
        super(5);
    }

    public static void main(String[] args) {
        new Day05().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day05.txt", 2015);
        File test = Day01.getResource("test05.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        String[] list = new String[lines.size()];

        for(int i = 0; i < lines.size(); i++) list[i] = lines.get(i);

        int oldNice = 0;
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');


        for(String s : list) {
            int vowelCount = 0;
            boolean tripleVowel = false;
            boolean repeat = false;
            boolean containsBadStrings = false;
            HashMap<Character, Integer> word = new HashMap<>();

            // Handles counting vowels
            for (char c : s.toCharArray()) {
                if(word.containsKey(c)) {
                    word.put(c, word.get(c) + 1);
                } else {
                    word.put(c, 1);
                }
            }
            for (char v : vowels) {
                if (word.get(v) != null) {
                    vowelCount += word.get(v);
                }
                tripleVowel = vowelCount >= 3;
            }

            // Handles double letters
            char[] charArray = s.toCharArray();
            for(int i = 0; i < charArray.length-1; i++) {
                if(charArray[i] == charArray[i+1]) repeat = true;
            }

            // Handles forbidden substrings
            if(s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy")) containsBadStrings = true;

            if(!containsBadStrings) {
                if(tripleVowel && repeat) {
                    oldNice++;
                }
            }
        }
        return oldNice;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day05.txt", 2015);
        File test = Day01.getResource("test05.txt", 2015);

        var lines = Utils.realOrTest(input, test);

        String[] list = new String[lines.size()];

        for(int i = 0; i < lines.size(); i++) list[i] = lines.get(i);

        int oldNice = 0;
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');


        for(String s : list) {
            int vowelCount = 0;
            boolean tripleVowel = false;
            boolean repeat = false;
            boolean containsBadStrings = false;
            HashMap<Character, Integer> word = new HashMap<>();

            // Handles counting vowels
            for (char c : s.toCharArray()) {
                if(word.containsKey(c)) {
                    word.put(c, word.get(c) + 1);
                } else {
                    word.put(c, 1);
                }
            }
            for (char v : vowels) {
                if (word.get(v) != null) {
                    vowelCount += word.get(v);
                }
                tripleVowel = vowelCount >= 3;
            }

            // Handles double letters
            char[] charArray = s.toCharArray();
            for(int i = 0; i < charArray.length-1; i++) {
                if(charArray[i] == charArray[i+1]) repeat = true;
            }

            // Handles forbidden substrings
            if(s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy")) containsBadStrings = true;

            if(!containsBadStrings) {
                if(tripleVowel && repeat) {
                    oldNice++;
                }
            }
        }

        HashSet<String> pairs = new HashSet<>();
        HashSet<String> spacers = new HashSet<>();
        for(String s : list) {
            // Handles conditon 2
            boolean spacer = false;
            HashMap<Character, Integer> word = new HashMap<>();
            for (char c : s.toCharArray()) {
                if(word.containsKey(c)) {
                    word.put(c, word.get(c) + 1);
                } else {
                    word.put(c, 1);
                }
            }
            for(char c : word.keySet()) {
                if(word.get(c) >= 2) {
                    String regex = ".*" + c + "." + c + ".*";
                    spacer = s.matches(regex);
                    if(spacer) {
                        spacers.add(s);
                    }
                }
            }

            // Handles conditon 1
            for(int i = 0; i<s.length()-1; ++i) {
                String sub = s.substring(i, i+2);
                for(int j = i+2; j<s.length()-1; ++j) {
                    var arr = s.toCharArray();
                    if(arr[j] == sub.charAt(0) && arr[j+1] == sub.charAt(1)) {
                        pairs.add(s);
                    }
                }
            }
        }
        spacers.retainAll(pairs);
        return spacers.size();
    }
}