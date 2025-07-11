package main.java.adventofcode.year19.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year19.Day2019;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Day02 extends Day2019 {
    public Day02() {
        super(2);
    }

    public static void main(String[] args) {
        new Day02().printParts();
    }

    @Override
    public Object part1() {
        File in = Day02.getResource("day02.txt", 2019);
        File test = Day02.getResource("test02.txt", 2019);

        var input = Utils.realOrTest(in, test);

        ArrayList<Integer> nums = parse(input);
        nums.set(1, 12);
        nums.set(2, 2);

        for(int i = 0; i < nums.size(); i+=4) {
            if(nums.get(i) == 1) {
                nums.set(nums.get(i+3), nums.get(nums.get(i+1)) + nums.get(nums.get(i+2)));
            } else if(nums.get(i) == 2) {
                nums.set(nums.get(i+3), nums.get(nums.get(i+1)) * nums.get(nums.get(i+2)));
            } else if(nums.get(i) == 99) {
                break;
            }
        }

        return nums.get(0);
    }

    public static ArrayList<Integer> parse(List<String> input) {
        var ret = new ArrayList<Integer>();
        for(String s : input.get(0).split(",")) {
            ret.add(Integer.parseInt(s));
        }
        return ret;
    }

    @Override
    public Object part2() {
        File in = Day02.getResource("day02.txt", 2019);
        File test = Day02.getResource("test02.txt", 2019);

        var input = Utils.realOrTest(in, test);
        ArrayList<Integer> nums = parse(input);

        int TARGET = 19690720;
//        int TARGET = 3895705;
        int noun = 0;
        int verb = 0;

        for(int n = 0; n < 100; n++) {
            for(int v = 0; v < 100; v++) {
                nums = parse(input);
                nums.set(1, n);
                nums.set(2, v);
                System.out.println(nums);

                for(int i = 0; i < nums.size(); i+=4) {
                    if(nums.get(i) == 1) {
                        nums.set(nums.get(i+3), nums.get(nums.get(i+1)) + nums.get(nums.get(i+2)));
                    } else if(nums.get(i) == 2) {
                        nums.set(nums.get(i+3), nums.get(nums.get(i+1)) * nums.get(nums.get(i+2)));
                    } else if(nums.get(i) == 99) {
                        if (nums.get(0) == TARGET) {
                            noun = n;
                            verb = v;
                            break;
                        }
                    }
                }
            }
        }
        return 100 * noun + verb;
    }
}