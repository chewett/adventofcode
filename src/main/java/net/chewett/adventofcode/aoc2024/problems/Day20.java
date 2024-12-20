package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 20: Race Condition ---
 * The Historians are quite pixelated again. This time, a massive, black building looms over you - you're right outside
 * the CPU!
 *
 * While The Historians get to work, a nearby program sees that you're idle and challenges you to a race. Apparently,
 * you've arrived just in time for the frequently-held race condition festival!
 *
 * The race takes place on a particularly long and twisting code path; programs compete to see who can finish in the
 * fewest picoseconds. The winner even gets their very own mutex!
 *
 * They hand you a map of the racetrack (your puzzle input). For example:
 *
 * ###############
 * #...#...#.....#
 * #.#.#.#.#.###.#
 * #S#...#.#.#...#
 * #######.#.#.###
 * #######.#.#...#
 * #######.#.###.#
 * ###..E#...#...#
 * ###.#######.###
 * #...###...#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * The map consists of track (.) - including the start (S) and end (E) positions (both of which also count as track) -
 * and walls (#).
 *
 * When a program runs through the racetrack, it starts at the start position. Then, it is allowed to move up, down,
 * left, or right; each such move takes 1 picosecond. The goal is to reach the end position as quickly as possible. In
 * this example racetrack, the fastest time is 84 picoseconds.
 *
 * Because there is only a single path from the start to the end and the programs all go the same speed, the races
 * used to be pretty boring. To make things more interesting, they introduced a new rule to the races: programs are
 * allowed to cheat.
 *
 * The rules for cheating are very strict. Exactly once during a race, a program may disable collision for up to
 * 2 picoseconds. This allows the program to pass through walls as if they were regular track. At the end of the cheat,
 * the program must be back on normal track again; otherwise, it will receive a segmentation fault and get disqualified.
 *
 * So, a program could complete the course in 72 picoseconds (saving 12 picoseconds) by cheating for the two moves
 * marked 1 and 2:
 *
 * ###############
 * #...#...12....#
 * #.#.#.#.#.###.#
 * #S#...#.#.#...#
 * #######.#.#.###
 * #######.#.#...#
 * #######.#.###.#
 * ###..E#...#...#
 * ###.#######.###
 * #...###...#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * Or, a program could complete the course in 64 picoseconds (saving 20 picoseconds) by cheating for the two moves
 * marked 1 and 2:
 *
 * ###############
 * #...#...#.....#
 * #.#.#.#.#.###.#
 * #S#...#.#.#...#
 * #######.#.#.###
 * #######.#.#...#
 * #######.#.###.#
 * ###..E#...12..#
 * ###.#######.###
 * #...###...#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * This cheat saves 38 picoseconds:
 *
 * ###############
 * #...#...#.....#
 * #.#.#.#.#.###.#
 * #S#...#.#.#...#
 * #######.#.#.###
 * #######.#.#...#
 * #######.#.###.#
 * ###..E#...#...#
 * ###.####1##.###
 * #...###.2.#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * This cheat saves 64 picoseconds and takes the program directly to the end:
 *
 * ###############
 * #...#...#.....#
 * #.#.#.#.#.###.#
 * #S#...#.#.#...#
 * #######.#.#.###
 * #######.#.#...#
 * #######.#.###.#
 * ###..21...#...#
 * ###.#######.###
 * #...###...#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * Each cheat has a distinct start position (the position where the cheat is activated, just before the first move that
 * is allowed to go through walls) and end position; cheats are uniquely identified by their start position and end
 * position.
 *
 * In this example, the total number of cheats (grouped by the amount of time they save) are as follows:
 *
 * There are 14 cheats that save 2 picoseconds.
 * There are 14 cheats that save 4 picoseconds.
 * There are 2 cheats that save 6 picoseconds.
 * There are 4 cheats that save 8 picoseconds.
 * There are 2 cheats that save 10 picoseconds.
 * There are 3 cheats that save 12 picoseconds.
 * There is one cheat that saves 20 picoseconds.
 * There is one cheat that saves 36 picoseconds.
 * There is one cheat that saves 38 picoseconds.
 * There is one cheat that saves 40 picoseconds.
 * There is one cheat that saves 64 picoseconds.
 * You aren't sure what the conditions of the racetrack will be like, so to give yourself as many options as possible,
 * you'll need a list of the best cheats. How many cheats would save you at least 100 picoseconds?
 *
 * --- Part Two ---
 * The programs seem perplexed by your list of cheats. Apparently, the two-picosecond cheating rule was deprecated
 * several milliseconds ago! The latest version of the cheating rule permits a single cheat that instead lasts at most
 * 20 picoseconds.
 *
 * Now, in addition to all the cheats that were possible in just two picoseconds, many more cheats are possible.
 * This six-picosecond cheat saves 76 picoseconds:
 *
 * ###############
 * #...#...#.....#
 * #.#.#.#.#.###.#
 * #S#...#.#.#...#
 * #1#####.#.#.###
 * #2#####.#.#...#
 * #3#####.#.###.#
 * #456.E#...#...#
 * ###.#######.###
 * #...###...#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * Because this cheat has the same start and end positions as the one above, it's the same cheat, even though the path
 * taken during the cheat is different:
 *
 * ###############
 * #...#...#.....#
 * #.#.#.#.#.###.#
 * #S12..#.#.#...#
 * ###3###.#.#.###
 * ###4###.#.#...#
 * ###5###.#.###.#
 * ###6.E#...#...#
 * ###.#######.###
 * #...###...#...#
 * #.#####.#.###.#
 * #.#...#.#.#...#
 * #.#.#.#.#.#.###
 * #...#...#...###
 * ###############
 * Cheats don't need to use all 20 picoseconds; cheats can last any amount of time up to and including 20 picoseconds
 * (but can still only end when the program is on normal track). Any cheat time not used is lost; it can't be saved for
 * another cheat later.
 *
 * You'll still need a list of the best cheats, but now there are even more to choose between. Here are the quantities
 * of cheats in this example that save 50 picoseconds or more:
 *
 * There are 32 cheats that save 50 picoseconds.
 * There are 31 cheats that save 52 picoseconds.
 * There are 29 cheats that save 54 picoseconds.
 * There are 39 cheats that save 56 picoseconds.
 * There are 25 cheats that save 58 picoseconds.
 * There are 23 cheats that save 60 picoseconds.
 * There are 20 cheats that save 62 picoseconds.
 * There are 19 cheats that save 64 picoseconds.
 * There are 12 cheats that save 66 picoseconds.
 * There are 14 cheats that save 68 picoseconds.
 * There are 12 cheats that save 70 picoseconds.
 * There are 22 cheats that save 72 picoseconds.
 * There are 4 cheats that save 74 picoseconds.
 * There are 3 cheats that save 76 picoseconds.
 * Find the best cheats using the updated cheating rules. How many cheats would save you at least 100 picoseconds?
 */
public class Day20 {

    /**
     * Given the input this moves through the maze from start to finish logging the picoseconds it took to get to
     * each location
     * @param input The maze to traverse
     * @return The time taken to get to each location
     */
    private Discrete2DPositionGrid<Long> getDistancesForAllPoints(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Long> distanceGrid = new Discrete2DPositionGrid<>(Long.MAX_VALUE);

        Point p = input.getPositionOfValueAssumingOnlyOne('S');
        Point end = input.getPositionOfValueAssumingOnlyOne('E');

        long curPicoseconds = 0;
        while(!p.equals(end)) {
            distanceGrid.setValueAtPosition(p, curPicoseconds);
            long picosAtPoint = curPicoseconds;

            List<Point> cardinalDirections = input.getDirectlyAdjacentPoints(p);
            for(Point cardinalDirection : cardinalDirections) {
                //There can only be one point to move to since the maze is a single line
                if((input.getValueAtPosition(cardinalDirection) == '.' || input.getValueAtPosition(cardinalDirection) == 'E') &&
                        distanceGrid.getValueAtPosition(cardinalDirection) > picosAtPoint
                ) {
                    p = cardinalDirection;
                    curPicoseconds++;
                }
            }
        }

        //Set the end point to a value
        distanceGrid.setValueAtPosition(p, curPicoseconds);

        return distanceGrid;
    }

    /**
     * Work out the number of ways you can cheat and get equal or more than an 100 picosecond advantage
     * @param input Map of the maze
     * @return Number of ways you can cheat and get equal or more than an 100 picosecond advantage
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Long> distanceGrid = this.getDistancesForAllPoints(input);

        List<Point> allPositionsVisited = distanceGrid.getAllPositionsAValueIsStoredAt();

        long timeSaved100OrMore = 0;
        Map<Long, Integer> cheatSavesTime = new HashMap<>();
        for(Point cheatPoint : allPositionsVisited) {
            long myPicoseconds = distanceGrid.getValueAtPosition(cheatPoint);

            // Get all cardinal directions
            List<Point> locsToCheck = new ArrayList<>();
            locsToCheck.add(new Point(cheatPoint.x, cheatPoint.y+2));
            locsToCheck.add(new Point(cheatPoint.x, cheatPoint.y-2));
            locsToCheck.add(new Point(cheatPoint.x+2, cheatPoint.y));
            locsToCheck.add(new Point(cheatPoint.x-2, cheatPoint.y));

            //Check each cardinal direction to see if there is another location we move through, and we save time
            for(Point locToCheck : locsToCheck) {
                if(input.getValueAtPosition(locToCheck) == '.' || input.getValueAtPosition(locToCheck) == 'E') {
                    long timeSaved = distanceGrid.getValueAtPosition(locToCheck) - myPicoseconds - 2;

                    if(timeSaved > 0) {
                        cheatSavesTime.put(timeSaved, cheatSavesTime.getOrDefault(timeSaved, 0) + 1);
                    }
                    if(timeSaved >= 100) {
                        timeSaved100OrMore++;
                    }
                }
            }
        }

        return timeSaved100OrMore;
    }

    /**
     * Solve part two with the newly redefined way of cheating
     * @param input Map of the maze
     * @return Number of ways you can cheat with a greater than 100 picosecond advantage
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Long> distanceGrid = this.getDistancesForAllPoints(input);
        List<Point> allPositionsVisited = distanceGrid.getAllPositionsAValueIsStoredAt();

        long pathsTimeSaved100rMore = 0;
        for(Point cheatPoint : allPositionsVisited) {
            //TODO: Create my own point class with this manhattan distance function in it
            List<Point> allEndPoints = input.generateAllPointsInTheManhattenRegionOfThis(cheatPoint, 20);
            long thisPicoseconds = distanceGrid.getValueAtPosition(cheatPoint);
            for(Point endPoint : allEndPoints) {
                if(input.getValueAtPosition(endPoint) == '.' || input.getValueAtPosition(endPoint) == 'E') {
                    int distanceTravelled =  Math.abs(cheatPoint.x - endPoint.x) + Math.abs(cheatPoint.y - endPoint.y);
                    long timeSaved = distanceGrid.getValueAtPosition(endPoint) - thisPicoseconds - distanceTravelled;
                    if(timeSaved >= 100) {
                        pathsTimeSaved100rMore++;
                    }
                }
            }
        }

        return pathsTimeSaved100rMore;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 20);

        Day20 d = new Day20();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of ways to cheat equal or more than 100 picoseconds is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("With the new way to cheat the number of ways to cheat equal or more than 100 picoseconds is " + partTwo);
    }
}


