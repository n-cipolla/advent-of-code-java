package main.java.adventofcode.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> readLines(File resource) {
        List<String> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    public static List<String> realOrTest(File real, File test) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter [t] for testing or [r] for real input: ");
        String input = scanner.nextLine();
        if(input.equals("t")) {
            return readLines(test);
        } else if (input.equals("r")) {
            return readLines(real);
        } else throw new IllegalArgumentException("Enter only [t] or [r].");
    }

    public static ArrayList<ArrayList<Integer>> powerset(ArrayList<Integer> input) {
        var ret = new ArrayList<ArrayList<Integer>>();
        int n = input.size();
        double size = Math.pow(2, input.size());

        for(int i = 0; i < (1 << n); ++i) {
            ArrayList<Integer> subset = new ArrayList<>();
            for(int j = 0; j < n; ++j) {
                if((i & (1 << j)) > 0) {
                    subset.add(input.get(j));
                }
            }
            ret.add(subset);
        }
        return ret;
    }

    public static void printGrid(char[][] input, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printGrid(char[][] input) {
        int size = input.length;
        printGrid(input, size);
    }
}
