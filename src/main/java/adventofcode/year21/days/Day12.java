package main.java.adventofcode.year21.days;

import main.java.adventofcode.common.MultiGraph;
import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year21.Day2021;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day12 extends Day2021 {
    public Day12() {
        super(12);
    }

    public static void main(String[] args) {
        new Day12().printParts();
    }

    @Override
    public Object part1() {
        File input = Day12.getResource("day12.txt", 2021);
        File test = Day12.getResource("test12.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        MultiGraph<String> caves = new MultiGraph<>();
        caves.addNode("start");
        for(String s : lines) {
            var arr = s.split("-");
            caves.addNode(arr[0], arr[1]);
        }

        HashSet<ArrayList<String>> paths = findAllPaths(caves.getEdges(), "start", "end");

        return paths.size();
    }

    public static HashSet<ArrayList<String>> findAllPaths(HashMap<String, ArrayList<String>> edges, String start, String end) {
        HashSet<ArrayList<String>> allPaths = new HashSet<>();
        ArrayList<String> currentPath = new ArrayList<>();

        depthFirstSearch(edges, start, end, allPaths, currentPath);
        return allPaths;
    }

    public static void depthFirstSearch(HashMap<String, ArrayList<String>> edges, String node, String end,
                                        HashSet<ArrayList<String>> allPaths, ArrayList<String> currentPath) {
        currentPath.add(node);

        if(node.equals(end)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for(String neighbour : edges.get(node)) {
                if(isUppercase(neighbour) || !currentPath.contains(neighbour)) {
                    depthFirstSearch(edges, neighbour, end, allPaths, currentPath);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public static boolean isUppercase(String s) {
        return Character.isUpperCase(s.charAt(0));
    }

    public static boolean hasLowerCaseDuplicates(ArrayList<String> list) {
        ArrayList<String> seen = new ArrayList<>();
        for(String s : list) {
            if(seen.contains(s) && s.matches("^[a-z]+$")) {
                return false;
            } else {
                seen.add(s);
            }
        }
        return true;
    }

    @Override
    public Object part2() {
        File input = Day12.getResource("day12.txt", 2021);
        File test = Day12.getResource("test12.txt", 2021);

        var lines = Utils.realOrTest(input, test);

        MultiGraph<String> caves = new MultiGraph<>();
        caves.addNode("start");
        for(String s : lines) {
            var arr = s.split("-");
            caves.addNode(arr[0], arr[1]);
        }

        HashSet<ArrayList<String>> paths = findAllPaths2(caves.getEdges(), "start", "end");
        for(var p : paths) System.out.println(p);
        return paths.size();
    }

    public static HashSet<ArrayList<String>> findAllPaths2(HashMap<String, ArrayList<String>> edges, String start, String end) {
        HashSet<ArrayList<String>> allPaths = new HashSet<>();
        ArrayList<String> currentPath = new ArrayList<>();

        depthFirstSearch2(edges, start, end, allPaths, currentPath);
        return allPaths;
    }

    public static void depthFirstSearch2(HashMap<String, ArrayList<String>> edges, String node, String end,
                                        HashSet<ArrayList<String>> allPaths, ArrayList<String> currentPath) {
        currentPath.add(node);

        if(node.equals(end)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for(String neighbour : edges.get(node)) {
                if(isUppercase(neighbour)) {
                    depthFirstSearch2(edges, neighbour, end, allPaths, currentPath);
                } else if (isLowerCase(neighbour)) {
                    if(!seenTwice(neighbour, currentPath) && !containsOnlyOneDuplicateLC(currentPath) && !neighbour.equals("start")) {
                        depthFirstSearch2(edges, neighbour, end, allPaths, currentPath);
                    }
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

    /**
     *
     * @param path the list to check
     * @return <code>true</code> if the list already contains a lowercase duplicate
     */
    public static boolean containsOnlyOneDuplicateLC(ArrayList<String> path) {
        HashMap<String, Integer> counts = new HashMap<>();
        for(String s : path) {
            if(!counts.containsKey(s)) {
                counts.put(s, 1);
            } else {
                counts.put(s, counts.get(s) + 1);
            }
        }

        int numLCDuplicates = 0;
        for(String s : counts.keySet()) {
            if(counts.get(s) > 1 && s.matches("^[a-z]+$")) {
                numLCDuplicates++;
            }
        }
        return numLCDuplicates > 1;
    }

    public static boolean isLowerCase(String s) {
        return Character.isLowerCase(s.charAt(0));
    }

    /**
     *
     * @param s check string
     * @param current list to check
     * @return <code>true</code> if the string has been seen more than twice, otherwise false
     */
    public static boolean seenTwice(String s, ArrayList<String> current) {
        int count = 0;
        for(String cave : current) {
            if(s.equals(cave)) {count++;}
        }
        return count >= 2;
    }
}