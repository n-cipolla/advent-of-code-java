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
}
