package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;

public class Day23 extends Day2015 {
    public Day23() {
        super(23);
    }

    public static void main(String[] args) {
        new Day23().printParts();
    }

    @Override
    public Object part1() {
        File in = Day23.getResource("day23.txt", 2015);
        File test = Day23.getResource("test23.txt", 2015);

        var input = Utils.realOrTest(in, test);

        int registerA = 0;
        int registerB = 0;

        for(int i = 0; i < input.size(); ++i) {
            String line = input.get(i);
            String instruction = line.substring(0, 3);
            String register = "";
            if(!instruction.equals("jmp")) {
                register = line.split(" ")[1];
                if(register.contains(",")) register = register.replace(",", "");
            }
            // System.out.println("Line: " + line + "\n" + "Instruction: " + instruction + "\n" + "Register: " + register);

            if (instruction.equals("hlf")) {
                System.out.println("half");
                if(register.equals("a")) registerA /= 2;
                else registerB /= 2;
            } else if (instruction.equals("tpl")) {
                System.out.println("triple");
                if(register.equals("a")) registerA *= 3;
                else registerB *= 3;
            } else if (instruction.equals("inc")) {
                System.out.println("increment");
                if(register.equals("a")) registerA++;
                else registerB++;
            } else if (instruction.equals("jmp")) {
                System.out.println("jump");
                String offset = line.split(" ")[1];
                int numericalOffset = 0;
                if(offset.charAt(0) == '-') numericalOffset = Integer.parseInt(offset.substring(1)) * -1;
                else numericalOffset = Integer.parseInt(offset.substring(1));
                i += numericalOffset-1;
                if(i >= input.size()) break;
            } else if (instruction.equals("jie")) {
                System.out.println("jump if even");
                System.out.println(register);
                if((register.equals("a") && registerA % 2 == 0) || (register.equals("b") && registerB % 2 == 0)) {
                    String offset = line.split(", ")[1];
                    int numericalOffset = 0;
                    if(offset.charAt(0) == '-') numericalOffset = Integer.parseInt(offset.substring(1)) * -1;
                    else numericalOffset = Integer.parseInt(offset.substring(1));
                    i += numericalOffset-1;
                    if(i >= input.size()) break;
                } else {
                    // Do nothing!
                }
            } else if (instruction.equals("jio")) {
                System.out.println("jump if one");
                if((register.equals("a") && registerA == 1) || (register.equals("b") && registerB == 1)) {
                    String offset = line.split(", ")[1];
                    System.out.println("Offset: "+offset);
                    int numericalOffset = 0;
                    if(offset.charAt(0) == '-') numericalOffset = Integer.parseInt(offset.substring(1)) * -1;
                    else numericalOffset = Integer.parseInt(offset.substring(1));
                    i += numericalOffset-1;
                    if(i >= input.size()) break;
                } else {
                    // Do nothing!
                }

            } else {
                System.out.println("Uh Oh");
            }
        }
        return registerB;
    }

    @Override
    public Object part2() {
        File in = Day23.getResource("day23.txt", 2015);
        File test = Day23.getResource("test23.txt", 2015);

        var input = Utils.realOrTest(in, test);

        int registerA = 0;
        int registerB = 1;

        for(int i = 0; i < input.size(); ++i) {
            String line = input.get(i);
            String instruction = line.substring(0, 3);
            String register = "";
            if(!instruction.equals("jmp")) {
                register = line.split(" ")[1];
                if(register.contains(",")) register = register.replace(",", "");
            }
            // System.out.println("Line: " + line + "\n" + "Instruction: " + instruction + "\n" + "Register: " + register);

            if (instruction.equals("hlf")) {
                System.out.println("half");
                if(register.equals("a")) registerA /= 2;
                else registerB /= 2;
            } else if (instruction.equals("tpl")) {
                System.out.println("triple");
                if(register.equals("a")) registerA *= 3;
                else registerB *= 3;
            } else if (instruction.equals("inc")) {
                System.out.println("increment");
                if(register.equals("a")) registerA++;
                else registerB++;
            } else if (instruction.equals("jmp")) {
                System.out.println("jump");
                String offset = line.split(" ")[1];
                int numericalOffset = 0;
                if(offset.charAt(0) == '-') numericalOffset = Integer.parseInt(offset.substring(1)) * -1;
                else numericalOffset = Integer.parseInt(offset.substring(1));
                i += numericalOffset-1;
                if(i >= input.size()) break;
            } else if (instruction.equals("jie")) {
                System.out.println("jump if even");
                System.out.println(register);
                if((register.equals("a") && registerA % 2 == 0) || (register.equals("b") && registerB % 2 == 0)) {
                    String offset = line.split(", ")[1];
                    int numericalOffset = 0;
                    if(offset.charAt(0) == '-') numericalOffset = Integer.parseInt(offset.substring(1)) * -1;
                    else numericalOffset = Integer.parseInt(offset.substring(1));
                    i += numericalOffset-1;
                    if(i >= input.size()) break;
                } else {
                    // Do nothing!
                }
            } else if (instruction.equals("jio")) {
                System.out.println("jump if one");
                if((register.equals("a") && registerA == 1) || (register.equals("b") && registerB == 1)) {
                    String offset = line.split(", ")[1];
                    System.out.println("Offset: "+offset);
                    int numericalOffset = 0;
                    if(offset.charAt(0) == '-') numericalOffset = Integer.parseInt(offset.substring(1)) * -1;
                    else numericalOffset = Integer.parseInt(offset.substring(1));
                    i += numericalOffset-1;
                    if(i >= input.size()) break;
                } else {
                    // Do nothing!
                }

            } else {
                System.out.println("Uh Oh");
            }
        }
        return registerB;
    }
}