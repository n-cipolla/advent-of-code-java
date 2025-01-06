package main.java.adventofcode.year15.days;

import main.java.adventofcode.common.Utils;
import main.java.adventofcode.year15.Day2015;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Day14 extends Day2015 {
    public Day14() {
        super(14);
    }

    public static void main(String[] args) {
        new Day14().printParts();
    }

    @Override
    public Object part1() {
        File input = Day14.getResource("day14.txt", 2015);
        File test = Day14.getResource("test14.txt", 2015);

        var lines = Utils.realOrTest(input, test);
        HashMap<String, Integer> hm = new HashMap<>();
        for(String s : lines) {
            hm.put(s.split(" ")[0], calcDistance(s, 2503));
        }
        int max = 0;
        for(int v : hm.values()) {
            if(v > max) max = v;
        }
        return max;
    }

    @Override
    public Object part2() {
        File in = Day14.getResource("day14.txt", 2015);
        File test = Day14.getResource("test14.txt", 2015);

        var input = Utils.realOrTest(in, test);

        HashMap<Reindeer, Integer> reindeerDistanceTraveled = new HashMap<>();
        HashMap<Reindeer, Integer> pointsCounter = new HashMap<>();

        Day14 outer = new Day14();
        for(String s : input) {
            String[] arr = s.split(" ");
            String name = arr[0];
            int speed = Integer.parseInt(arr[3]);
            int duration = Integer.parseInt(arr[6]);
            int rest = Integer.parseInt(arr[13]);
            Reindeer r = new Reindeer(name, speed, duration, rest);
            reindeerDistanceTraveled.put(r, 0);
            pointsCounter.put(r,0);
        }
        int totalRaceTime = 2503;
        int raceDuration = 0;

        // populates the hash set of each reindeer
        for(Reindeer r : reindeerDistanceTraveled.keySet()) {
            r.populateHashSet(totalRaceTime);
        }

        for(int i = 0; i < totalRaceTime; ++i) {
            // Move all reindeer up by 1 second
            for(Reindeer r : reindeerDistanceTraveled.keySet()) {
                r.advance(reindeerDistanceTraveled, raceDuration);
            }
            raceDuration++;

            // calculate the reindeer who's gone the furthest
            int furthest = 0;
            for (int v : reindeerDistanceTraveled.values()) {
                if(v > furthest) furthest = v;
            }

            // Add a point to the reindeer(s) in first place
            for (Reindeer r : reindeerDistanceTraveled.keySet()) {
                if(reindeerDistanceTraveled.get(r) == furthest) {
                    var tmp = pointsCounter.get(r);
                    tmp++;
                    pointsCounter.put(r, tmp);
                }
            }
        }
        // Calculate the largest points overall
        int pointsMax = 0;
        for(int v : pointsCounter.values()) {
            if (v > pointsMax) pointsMax = v;
        }
        String rName = "";
        for(Reindeer r : pointsCounter.keySet()) {
            if(pointsCounter.get(r) == pointsMax) {
                rName = r.getName();
            }
        }
        return pointsMax;
    }

    private static int calcDistance(String in, int time) {
        String[] arr = in.split(" ");
        String name = arr[0];
        int speed = Integer.parseInt(arr[3]);
        int duration = Integer.parseInt(arr[6]);
        int rest = Integer.parseInt(arr[13]);

        int dist = 0;
        int lapSumTime = rest+duration;

        int totalLaps = time / lapSumTime;

        for(int i = 0; i < totalLaps; ++i) {
            dist += speed * duration;
        }

        // This is free since the remainder lap is never more than a burst + rest + burst
        // in which case we'd need to add 2 * speed * duration
        // but since the remainder is always less than a "speed + rest lap" we just add one more for the remainder
        dist += speed * duration;

//        System.out.println(name + " has made it " + dist + " km in " + time + " seconds.");
        return dist;
    }

    static class Reindeer {
        private final String name;
        private final int speed;
        private final int duration;
        private final int rest;
        boolean racing;
        public HashSet<Integer> resting;

        public Reindeer(String name, int speed, int duration, int rest) {
            this.name = name;
            this.speed = speed;
            this.duration = duration;
            this.rest = rest;
            this.racing = true;
            this.resting = new LinkedHashSet<Integer>();
        }

        public String getName() {
            return this.name;
        }
        public int getSpeed() {
            return this.speed;
        }
        public void populateHashSet(int time) {
            int counter = 0;
            for(int i = 0; i < time; ++i) {
                if(counter < duration) {
                    this.resting.add(i);
                    counter++;
                } else {
                    i += rest - 1;
                    counter = 0;
                }
            }
        }

        @Override
        public String toString() {
            return name + ": speed of " + speed + " km/s for " + duration + " seconds, then rests for " + rest + " seconds.";
        }

        public void advance(HashMap<Reindeer, Integer> distances, int raceDuration) {
            for(Reindeer r : distances.keySet()) {
                boolean atRest = false;

                atRest = !r.resting.contains(raceDuration);

                if(atRest) {
                    distances.replace(r, distances.get(r));
                } else {
                    distances.replace(r, distances.get(r) + r.getSpeed());
                }
            }
        }
    }

}