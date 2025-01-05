package main.java.adventofcode.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Arrays;

public abstract class Day {
    protected final int year;
    protected final int day;

    private Object solutionPart1;
    private Object solutionPart2;

    public Day(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public abstract Object part1();
    public abstract Object part2();

    public void printParts() {
        solutionPart1 = part1();
        if(solutionPart1 instanceof Optional) solutionPart1 = ((Optional<?>) solutionPart1).get();
        System.out.println("Part 1: " + solutionPart1);
        solutionPart2 = part2();
        if(solutionPart2 instanceof Optional) solutionPart2 = ((Optional<?>) solutionPart2).get();
        System.out.println("Part 2: " + solutionPart2);
    }

    public static File getResource(String path, int year) {
        String currentDirectoryPath = Paths.get("").toAbsolutePath().toString();
        return new File(currentDirectoryPath + "/src/main/java/resources/year" + year + "/" + path);
    }
}
