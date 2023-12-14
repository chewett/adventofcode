package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ListHelpers;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 10: Pipe Maze ---
 * You use the hang glider to ride the hot air from Desert Island all the way up to the floating metal island. This
 * island is surprisingly cold and there definitely aren't any thermals to glide on, so you leave your hang glider
 * behind.
 *
 * You wander around for a while, but you don't find any people or animals. However, you do occasionally find
 * signposts labeled "Hot Springs" pointing in a seemingly consistent direction; maybe you can find someone at the hot
 * springs and ask them where the desert-machine parts are made.
 *
 * The landscape here is alien; even the flowers and trees are made of metal. As you stop to admire some metal grass,
 * you notice something metallic scurry away in your peripheral vision and jump into a big pipe! It didn't look like
 * any animal you've ever seen; if you want a better look, you'll need to get ahead of it.
 *
 * Scanning the area, you discover that the entire field you're standing on is densely packed with pipes; it was hard
 * to tell at first because they're the same metallic silver color as the "ground". You make a quick sketch of all of
 * the surface pipes you can see (your puzzle input).
 *
 * The pipes are arranged in a two-dimensional grid of tiles:
 *
 * | is a vertical pipe connecting north and south.
 * - is a horizontal pipe connecting east and west.
 * L is a 90-degree bend connecting north and east.
 * J is a 90-degree bend connecting north and west.
 * 7 is a 90-degree bend connecting south and west.
 * F is a 90-degree bend connecting south and east.
 * . is ground; there is no pipe in this tile.
 * S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the
 * pipe has.
 * Based on the acoustics of the animal's scurrying, you're confident the pipe that contains the animal is one large,
 * continuous loop.
 *
 * For example, here is a square loop of pipe:
 *
 * .....
 * .F-7.
 * .|.|.
 * .L-J.
 * .....
 * If the animal had entered this loop in the northwest corner, the sketch would instead look like this:
 *
 * .....
 * .S-7.
 * .|.|.
 * .L-J.
 * .....
 * In the above diagram, the S tile is still a 90-degree F bend: you can tell because of how the adjacent pipes
 * connect to it.
 *
 * Unfortunately, there are also many pipes that aren't connected to the loop! This sketch shows the same loop as above:
 *
 * -L|F7
 * 7S-7|
 * L|7||
 * -L-J|
 * L|-JF
 * In the above diagram, you can still figure out which pipes form the main loop: they're the ones connected to S,
 * pipes those pipes connect to, pipes those pipes connect to, and so on. Every pipe in the main loop connects to its
 * two neighbors (including S, which will have exactly two pipes connecting to it, and which is assumed to connect
 * back to those two pipes).
 *
 * Here is a sketch that contains a slightly more complex main loop:
 *
 * ..F7.
 * .FJ|.
 * SJ.L7
 * |F--J
 * LJ...
 * Here's the same example sketch with the extra, non-main-loop pipe tiles also shown:
 *
 * 7-F7-
 * .FJ|7
 * SJLL7
 * |F--J
 * LJ.LJ
 * If you want to get out ahead of the animal, you should find the tile in the loop that is farthest from the
 * starting position. Because the animal is in the pipe, it doesn't make sense to measure this by direct distance.
 * Instead, you need to find the tile that would take the longest number of steps along the loop to reach from the
 * starting point - regardless of which way around the loop the animal went.
 *
 * In the first example with the square loop:
 *
 * .....
 * .S-7.
 * .|.|.
 * .L-J.
 * .....
 * You can count the distance each tile in the loop is from the starting point like this:
 *
 * .....
 * .012.
 * .1.3.
 * .234.
 * .....
 * In this example, the farthest point from the start is 4 steps away.
 *
 * Here's the more complex loop again:
 *
 * ..F7.
 * .FJ|.
 * SJ.L7
 * |F--J
 * LJ...
 * Here are the distances for each tile on that loop:
 *
 * ..45.
 * .236.
 * 01.78
 * 14567
 * 23...
 * Find the single giant loop starting at S. How many steps along the loop does it take to get from the starting
 * position to the point farthest from the starting position?
 *
 * --- Part Two ---
 * You quickly reach the farthest point of the loop, but the animal never emerges. Maybe its nest is within the
 * area enclosed by the loop?
 *
 * To determine whether it's even worth taking the time to search for such a nest, you should calculate how many
 * tiles are contained within the loop. For example:
 *
 * ...........
 * .S-------7.
 * .|F-----7|.
 * .||.....||.
 * .||.....||.
 * .|L-7.F-J|.
 * .|..|.|..|.
 * .L--J.L--J.
 * ...........
 * The above loop encloses merely four tiles - the two pairs of . in the southwest and southeast (marked I below).
 * The middle . tiles (marked O below) are not in the loop. Here is the same loop again with those regions marked:
 *
 * ...........
 * .S-------7.
 * .|F-----7|.
 * .||OOOOO||.
 * .||OOOOO||.
 * .|L-7OF-J|.
 * .|II|O|II|.
 * .L--JOL--J.
 * .....O.....
 * In fact, there doesn't even need to be a full tile path to the outside for tiles to count as outside the loop -
 * squeezing between pipes is also allowed! Here, I is still within the loop and O is still outside the loop:
 *
 * ..........
 * .S------7.
 * .|F----7|.
 * .||OOOO||.
 * .||OOOO||.
 * .|L-7F-J|.
 * .|II||II|.
 * .L--JL--J.
 * ..........
 * In both of the above examples, 4 tiles are enclosed by the loop.
 *
 * Here's a larger example:
 *
 * .F----7F7F7F7F-7....
 * .|F--7||||||||FJ....
 * .||.FJ||||||||L7....
 * FJL7L7LJLJ||LJ.L-7..
 * L--J.L7...LJS7F-7L7.
 * ....F-J..F7FJ|L7L7L7
 * ....L7.F7||L7|.L7L7|
 * .....|FJLJ|FJ|F7|.LJ
 * ....FJL-7.||.||||...
 * ....L---J.LJ.LJLJ...
 * The above sketch has many random bits of ground, some of which are in the loop (I) and some of which are outside
 * it (O):
 *
 * OF----7F7F7F7F-7OOOO
 * O|F--7||||||||FJOOOO
 * O||OFJ||||||||L7OOOO
 * FJL7L7LJLJ||LJIL-7OO
 * L--JOL7IIILJS7F-7L7O
 * OOOOF-JIIF7FJ|L7L7L7
 * OOOOL7IF7||L7|IL7L7|
 * OOOOO|FJLJ|FJ|F7|OLJ
 * OOOOFJL-7O||O||||OOO
 * OOOOL---JOLJOLJLJOOO
 * In this larger example, 8 tiles are enclosed by the loop.
 *
 * Any tile that isn't part of the main loop can count as being enclosed by the loop. Here's another example with many
 * bits of junk pipe lying around that aren't connected to the main loop at all:
 *
 * FF7FSF7F7F7F7F7F---7
 * L|LJ||||||||||||F--J
 * FL-7LJLJ||||||LJL-77
 * F--JF--7||LJLJ7F7FJ-
 * L---JF-JLJ.||-FJLJJ7
 * |F|F-JF---7F7-L7L|7|
 * |FFJF7L7F-JF7|JL---7
 * 7-L-JL7||F7|L7F-7F7|
 * L.L7LFJ|||||FJL7||LJ
 * L7JLJL-JLJLJL--JLJ.L
 * Here are just the tiles that are enclosed by the loop marked with I:
 *
 * FF7FSF7F7F7F7F7F---7
 * L|LJ||||||||||||F--J
 * FL-7LJLJ||||||LJL-77
 * F--JF--7||LJLJIF7FJ-
 * L---JF-JLJIIIIFJLJJ7
 * |F|F-JF---7IIIL7L|7|
 * |FFJF7L7F-JF7IIL---7
 * 7-L-JL7||F7|L7F-7F7|
 * L.L7LFJ|||||FJL7||LJ
 * L7JLJL-JLJLJL--JLJ.L
 * In this last example, 10 tiles are enclosed by the loop.
 *
 * Figure out whether you have time to search for the nest by calculating the area within the loop. How many tiles are
 * enclosed by the loop?
 */
public class Day10 {

    /**
     * Works out what the starting letter S actually is in terms of pipe characters
     * This is needed for part two because of how I do the flood fill so it's important to find first and store
     * @param input Map of the data
     * @return Character that S represents
     */
    private char workOutStartingLetter(Discrete2DPositionGrid<Character> input) {
        List<Point> positions = input.getPositionsOfValue('S');
        if(positions.size() != 1) {
            throw new RuntimeException("Something is wrong and we have found the wrong number of starting values");
        }

        Point startingPosition = positions.get(0);

        Point north = input.getNorth(startingPosition);
        Point south = input.getSouth(startingPosition);
        Point east = input.getEast(startingPosition);
        Point west = input.getWest(startingPosition);

        boolean validNorth = false;
        boolean validSouth = false;
        boolean validEast = false;
        boolean validWest = false;

        if(north != null &&
                (input.getValueAtPosition(north) == '|' ||
                input.getValueAtPosition(north) == '7'
                || input.getValueAtPosition(north) == 'F')) {
            validNorth = true;
        }

        if(south != null &&
                (input.getValueAtPosition(south) == '|' ||
                        input.getValueAtPosition(south) == 'L'
                        || input.getValueAtPosition(south) == 'J')) {
            validSouth = true;
        }

        if(east != null &&
                (input.getValueAtPosition(east) == '-' ||
                    input.getValueAtPosition(east) == '7'
                    || input.getValueAtPosition(east) == 'J')) {
            validEast = true;
        }

        if(west != null &&
                (input.getValueAtPosition(west) == '-' ||
                        input.getValueAtPosition(west) == 'F'
                || input.getValueAtPosition(west) == 'L' )) {
            validWest = true;
        }


        if(validSouth && validEast) {
            return 'F';
        }else if(validSouth && validNorth) {
            return '|';
        }else if(validSouth && validWest) {
            return '7';
        }else if(validEast && validWest) {
            return '-';
        }else if(validNorth && validEast) {
            return 'L';
        }else if(validNorth && validWest) {
            return 'J';
        }

        throw new RuntimeException("Found a pairing that was not expected");
    }

    /**
     * Get the distances between the start point and the furthest point from the start
     * @param startingChar Character that the start represents
     * @param input Grid
     * @return Longest distance from the start
     */
    private Discrete2DPositionGrid<Integer> getDistances(char startingChar, Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Integer> distances = new Discrete2DPositionGrid<>(-1);

        Stack<Point> pointsToVisit = new Stack<>();

        //Essentially we just use a stack of points to visit, put the start point there, and keep going until none are left
        pointsToVisit.add(input.getPositionsOfValue('S').get(0));
        distances.setValueAtPosition(pointsToVisit.peek(), 0);
        while(!pointsToVisit.isEmpty()) {
            Point curPoint = pointsToVisit.pop();
            char curChar = input.getValueAtPosition(curPoint);
            //Now this isn't needed for the problem input as there are only ever two pipes connected to the start
            //However for part two I do some bad things to make it easy to expand, which means more than two might be
            //connected. By keeping track of what it was it means that I can ignore my "bad things".
            //Bad things are detailed in part two!
            if(curChar == 'S') {
                curChar = startingChar;
            }

            int curTravel = distances.getValueAtPosition(curPoint);
            int nextVal = curTravel + 1;

            Point north = input.getNorth(curPoint);
            //This check isn't needed for part one but due to the "bad things" in part two I need to check type I am
            if(north != null && (curChar == '|' || curChar == 'J' || curChar == 'L')) {
                //If I am the above types, then moving north is valid and I can see what's north
                char charAtNorth = input.getValueAtPosition(north);
                //And then all these positions are valid to move into, so if they are I do it!
                if(charAtNorth == '|' || charAtNorth == '7' || charAtNorth == 'F') {
                    //I only mark the next value as the next "step" if I haven't visited it or I am lower than the previous visit
                    if(distances.getValueAtPosition(north) == -1 || distances.getValueAtPosition(north) > nextVal) {
                        distances.setValueAtPosition(north, nextVal);
                        pointsToVisit.add(north);
                    }
                }
            }

            //I then do the same for south, east, and west
            Point south = input.getSouth(curPoint);
            if(south != null && (curChar == '|' || curChar == '7' || curChar == 'F')) {
                char charAtSouth = input.getValueAtPosition(south);
                if(charAtSouth == '|' || charAtSouth == 'L' || charAtSouth == 'J') {
                    if(distances.getValueAtPosition(south) == -1 || distances.getValueAtPosition(south) > nextVal) {
                        distances.setValueAtPosition(south, nextVal);
                        pointsToVisit.add(south);
                    }
                }
            }

            Point east = input.getEast(curPoint);
            if(east != null && (curChar == '-' || curChar == 'F' || curChar == 'L')) {
                char charAtEast = input.getValueAtPosition(east);
                if(charAtEast == '-' || charAtEast == '7' || charAtEast == 'J') {
                    if(distances.getValueAtPosition(east) == -1 || distances.getValueAtPosition(east) > nextVal) {
                        distances.setValueAtPosition(east, nextVal);
                        pointsToVisit.add(east);
                    }
                }
            }

            Point west = input.getWest(curPoint);
            if(west != null && (curChar == '-' || curChar == 'J' || curChar == '7')) {
                char charAtWest = input.getValueAtPosition(west);
                if(charAtWest == '-' || charAtWest == 'F' || charAtWest == 'L') {
                    if(distances.getValueAtPosition(west) == -1 || distances.getValueAtPosition(west) > nextVal) {
                        distances.setValueAtPosition(west, nextVal);
                        pointsToVisit.add(west);
                    }
                }
            }
        }

        //At this point I have all my distances in the array
        return distances;
    }

    /**
     * Follows the pipes and works out the furthest value from the pipe iteratively
     * @param input List of pipes
     * @return Furthest value from the pipe
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        char startingChar = this.workOutStartingLetter(input);
        Discrete2DPositionGrid<Integer> distances = this.getDistances(startingChar, input);
        return ListHelpers.findMax(distances.getAllValuesStored());
    }

    /**
     * Part two is a little more complex because you need to find the enclosed sections of the pipes
     * Alongside this, you have to treat non/gapped but parallel pipes as "openings" which is hard to handle.
     *
     * To do this what I do is:
     *   * Double the size of the array filling it all with pipes.
     *   * Horizontal new points are marked with -
     *   * Vertical new points are marked with |
     *   * This means this automatically continues vertical/horizontal pipes, and gives a "gap" between two parallel pipes
     *   * This is the "bad thing" that means I need some extra checks in my pipe following distance calculations
     *
     *
     * @param input
     * @return
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        char startingChar = this.workOutStartingLetter(input);
        Discrete2DPositionGrid<Character> doubleSize = new Discrete2DPositionGrid<>('.');

        //First double the size of the array. This turns || into |.| which means I can flood it and automatically
        //Get the values into the parallel pipes.
        //I could do it more intelligently only filling the area with - or | if there is two pipes that it can connect
        //But this is faster to do so I went with that!
        for(int y = 0; y <= input.getMaxY(); y++) {
            for(int x = 0; x <= input.getMaxX(); x++) {
                int newX = x * 2;
                int newY = y * 2;

                char thisChar = input.getValueAtPosition(x, y);
                doubleSize.setValueAtPosition(newX, newY, thisChar);
                doubleSize.setValueAtPosition(newX+1, newY, '-');
                doubleSize.setValueAtPosition(newX, newY+1, '|');
            }
        }

        //Now work out the distances for the double size grid
        Discrete2DPositionGrid<Integer> distances = this.getDistances(startingChar, doubleSize);
        Discrete2DPositionGrid<Character> floodedMap = new Discrete2DPositionGrid<>('.');

        //What I am going to do here is "flood" the grid with values. Starting at the top right of the grid I then
        //Slowly "flood" to each point filling it as it finds it. This will identify all locations that can be reached
        //From outside and allow me to identify nests internally
        Set<Point> visited = new HashSet<>();
        Stack<Point> pointsToFlood = new Stack<>();
        pointsToFlood.add(new Point(-1, -1));
        while(pointsToFlood.size() > 0) {
            Point newFlood = pointsToFlood.pop();
            //Only go +5 around the grid
            if(newFlood.x > -5 && newFlood.y > -5 && newFlood.x <= doubleSize.getMaxX() + 5 && newFlood.y <= doubleSize.getMaxY() + 5){
                visited.add(newFlood);
                floodedMap.setValueAtPosition(newFlood, 'X');

                List<Point> newFloods = distances.getDirectlyAdjacentRegardlessOfSize(newFlood);
                for(Point newFloodAttempt : newFloods) {
                    //Ignore previously visited floods and places where the pipes are (where distance isn't -1
                    if(!visited.contains(newFloodAttempt) && distances.getValueAtPosition(newFloodAttempt) == -1) {
                        pointsToFlood.add(newFloodAttempt);
                    }
                }
            }
        }

        //And also flood all the pipes now
        for(int distancesY = 0; distancesY <= distances.getMaxY(); distancesY++) {
            for(int distancesX = 0; distancesX <= distances.getMaxX(); distancesX++) {
                if(distances.getValueAtPosition(distancesX, distancesY) != -1) {
                    floodedMap.setValueAtPosition(distancesX, distancesY, 'O');
                }
            }
        }

        //At this point we have found all the points accessible from the outside and also the original pipes
        //Now we just shrink the map back to the original size
        Discrete2DPositionGrid<Character> shrunkMap = new Discrete2DPositionGrid<>('X');
        for(int floodedY = 0; floodedY <= distances.getMaxY(); floodedY += 2) {
            for(int floodedX = 0; floodedX <= distances.getMaxX(); floodedX +=2) {
                int newX = floodedX / 2;
                int newY = floodedY / 2;

                if(floodedMap.getValueAtPosition(floodedX, floodedY) == '.') {
                    shrunkMap.setValueAtPosition(newX, newY, 'I');
                }
            }
        }

        //And then we can count how many I's there are, and this will be the inside areas!
        return shrunkMap.countInstancesOfValue('I');
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 10);

        Day10 d = new Day10();
        long partOne = d.solvePartOne(input);
        System.out.println("The furthest distance through the loop is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of tiles enclosed by the loop is " + partTwo);
    }
}


