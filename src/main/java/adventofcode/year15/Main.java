package main.java.adventofcode.year15;

import main.java.adventofcode.common.Day;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, InvocationTargetException, NoSuchMethodException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the day: ");
        String input = scanner.nextLine();
        String day = zeroPadDay(input);
        Day instance = (Day) Class.forName("main.java.adventofcode.year15.days.Day" + day).getDeclaredConstructor().newInstance();
        instance.printParts();
        System.out.println();
    }

    private static String zeroPadDay(String day) throws IllegalArgumentException {
        if(day.length() == 2) return day;
        if(day.length() == 1) return ("0" + day);
        else throw new IllegalArgumentException("Invalid day: " + day + ". Please use a zero-padded day under 26.");
    }
}

