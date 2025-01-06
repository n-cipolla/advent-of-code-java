package main.java.adventofcode.year24.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year24.Day2024;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Day03 extends Day2024 {
    public Day03() {
        super(3);
    }

    public static void main(String[] args) {
        new Day03().printParts();
    }

    @Override
    public Object part1() {
        File input = Day01.getResource("day03.txt", 2024);
        File test = Day01.getResource("test03.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        StringBuilder sb = new StringBuilder();
        for(String line : lines) {
            sb.append(line);
        }
        String line = sb.toString();

        Iterator<String> iter = new Iterator<String>() {
            final char[] chars = line.toCharArray();
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < chars.length;
            }

            @Override
            public String next() {
                if(!hasNext()) throw new NoSuchElementException("No more characters in string.");
                var ret = chars[index];
                index++;
                return String.valueOf(ret);
            }
        };

        ArrayList<String> parts = new ArrayList<>();

        while(iter.hasNext()) {
            String next = iter.next();
            if(next.equals("m")) {
                next = iter.next();
                if(next.equals("u")) {
                    next = iter.next();
                    if(next.equals("l")) {
                        next = iter.next();
                        if(next.equals("(")) {
                            StringBuilder combo = new StringBuilder();
                            combo.append("(");
                            while(iter.hasNext()) {
                                String num = iter.next();
                                if(checkValidNumber(num) || num.equals(",") || num.equals(")")) {
                                    combo.append(num);
                                    if(num.equals(")")) break;
                                } else {
                                    combo.replace(0, combo.length(), "");
                                    break;
                                }
                            }
                            parts.add(combo.toString());
                        }
                    }
                }
            }
        }

        parts.removeIf(item -> item == null || item.trim().isEmpty());

        int total = 0;
        int index = 0;
        for(String part : parts) {
            part = part.replace("(", "");
            part = part.replace(")", "");
            String[] nums = part.split(",");
            if(nums.length == 2) {
                total += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                index++;
            }
        }

        return total;
    }

    @Override
    public Object part2() {
        File input = Day01.getResource("day03.txt", 2024);
        File test = Day01.getResource("test03.txt", 2024);

        var lines = Utils.realOrTest(input, test);

        StringBuilder sb = new StringBuilder();
        for(String line : lines) {
            sb.append(line);
        }
        String line = sb.toString();

        Iterator<String> iter = new Iterator<String>() {
            final char[] chars = line.toCharArray();
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < chars.length;
            }

            @Override
            public String next() {
                if(!hasNext()) throw new NoSuchElementException("No more elements in the iterator");
                var ret = chars[index];
                index++;
                return String.valueOf(ret);
            }
        };

        boolean active = true;
        ArrayList<String> parts = new ArrayList<>();
        while(iter.hasNext()) {
            String next = iter.next();
            if(next.equals("d")) {
                next = iter.next();
                if(next.equals("o")) {
                    // Branch for "don't()"
                    next = iter.next();
                    if(next.equals("n")) {
                        next = iter.next();
                        if(next.equals("'")) {
                            next = iter.next();
                            if(next.equals("t")) {
                                next = iter.next();
                                if(next.equals("(")) {
                                    next = iter.next();
                                    if(next.equals(")")) {
                                        active = false;
//                                        System.out.println("Don't()");
                                    }
                                }
                            }
                        }
                    }
                    // Branch for "do()"
                    else if(next.equals("(")) {
                        next = iter.next();
                        if(next.equals(")")) {
                            active = true;
//                            System.out.println("Do()");
                        }
                    }
                }
            }


            if(next.equals("m")) {
                if(iter.next().equals("u")) {
                    if(iter.next().equals("l")) {
                        if(iter.next().equals("(")) {
                            StringBuilder combo = new StringBuilder();
                            combo.append("(");
                            while(iter.hasNext()) {
                                String num = iter.next();
                                if(checkValidNumber(num) || num.equals(",") || num.equals(")")) {
                                    combo.append(num);
                                    if(num.equals(")")) break;
                                } else {
                                    combo.replace(0, combo.length(), "");
                                    break;
                                }
                            }
                            if(active) {
                                parts.add(combo.toString());
                            }
                        }
                    }
                }
            }
        }

        parts.removeIf(item -> item == null || item.trim().isEmpty());

        int total = 0;
        int index = 0;
        for(String part : parts) {
            part = part.replace("(", "");
            part = part.replace(")", "");
            String[] nums = part.split(",");
            if(nums.length == 2) {
                total += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                index++;
            }
        }
        return String.valueOf(total);
    }

    public static boolean checkValidNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}