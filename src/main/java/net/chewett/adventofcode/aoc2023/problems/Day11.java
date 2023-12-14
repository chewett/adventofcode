package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 11: Cosmic Expansion ---
 * You continue following signs for "Hot Springs" and eventually come across an observatory. The Elf within turns out
 * to be a researcher studying cosmic expansion using the giant telescope here.
 *
 * He doesn't know anything about the missing machine parts; he's only visiting for this research project. However, he
 * confirms that the hot springs are the next-closest area likely to have people; he'll even take you straight there
 * once he's done with today's observation analysis.
 *
 * Maybe you can help him with the analysis to speed things up?
 *
 * The researcher has collected a bunch of data and compiled the data into a single giant image (your puzzle input).
 * The image includes empty space (.) and galaxies (#). For example:
 *
 * ...#......
 * .......#..
 * #.........
 * ..........
 * ......#...
 * .#........
 * .........#
 * ..........
 * .......#..
 * #...#.....
 * The researcher is trying to figure out the sum of the lengths of the shortest path between every pair of galaxies.
 * However, there's a catch: the universe expanded in the time it took the light from those galaxies to reach the
 * observatory.
 *
 * Due to something involving gravitational effects, only some space expands. In fact, the result is that any rows or
 * columns that contain no galaxies should all actually be twice as big.
 *
 * In the above example, three columns and two rows contain no galaxies:
 *
 *    v  v  v
 *  ...#......
 *  .......#..
 *  #.........
 * >..........<
 *  ......#...
 *  .#........
 *  .........#
 * >..........<
 *  .......#..
 *  #...#.....
 *    ^  ^  ^
 * These rows and columns need to be twice as big; the result of cosmic expansion therefore looks like this:
 *
 * ....#........
 * .........#...
 * #............
 * .............
 * .............
 * ........#....
 * .#...........
 * ............#
 * .............
 * .............
 * .........#...
 * #....#.......
 * Equipped with this expanded universe, the shortest path between every pair of galaxies can be found. It can help
 * to assign every galaxy a unique number:
 *
 * ....1........
 * .........2...
 * 3............
 * .............
 * .............
 * ........4....
 * .5...........
 * ............6
 * .............
 * .............
 * .........7...
 * 8....9.......
 * In these 9 galaxies, there are 36 pairs. Only count each pair once; order within the pair doesn't matter. For each
 * pair, find any shortest path between the two galaxies using only steps that move up, down, left, or right exactly
 * one . or # at a time. (The shortest path between two galaxies is allowed to pass through another galaxy.)
 *
 * For example, here is one of the shortest paths between galaxies 5 and 9:
 *
 * ....1........
 * .........2...
 * 3............
 * .............
 * .............
 * ........4....
 * .5...........
 * .##.........6
 * ..##.........
 * ...##........
 * ....##...7...
 * 8....9.......
 * This path has length 9 because it takes a minimum of nine steps to get from galaxy 5 to galaxy 9 (the eight
 * locations marked # plus the step onto galaxy 9 itself). Here are some other example shortest path lengths:
 *
 * Between galaxy 1 and galaxy 7: 15
 * Between galaxy 3 and galaxy 6: 17
 * Between galaxy 8 and galaxy 9: 5
 * In this example, after expanding the universe, the sum of the shortest path between all 36 pairs of galaxies is 374.
 *
 * Expand the universe, then find the length of the shortest path between every pair of galaxies. What is the sum of
 * these lengths?
 *
 * --- Part Two ---
 * The galaxies are much older (and thus much farther apart) than the researcher initially estimated.
 *
 * Now, instead of the expansion you did before, make each empty row or column one million times larger. That is,
 * each empty row should be replaced with 1000000 empty rows, and each empty column should be replaced with 1000000
 * empty columns.
 *
 * (In the example above, if each empty row or column were merely 10 times larger, the sum of the shortest paths
 * between every pair of galaxies would be 1030. If each empty row or column were merely 100 times larger, the sum
 * of the shortest paths between every pair of galaxies would be 8410. However, your universe will need to expand far
 * beyond these values.)
 *
 * Starting with the same initial image, expand the universe according to these new rules, then find the length of
 * the shortest path between every pair of galaxies. What is the sum of these lengths?
 */
public class Day11 {

    /**
     * Helper function for the Manhattan distance
     * @param p1 First point
     * @param p2 Second point
     * @return Manhanttan distance
     */
    public int getManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    /**
     * This is the "naive" way of doing it that won't work for part two, but I have kept it here since it's fun
     * to see part two compared to part one.
     * @param input Input of galaxies
     * @return Sum of the distances between each one
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {

        int offsetRows = 0;
        Discrete2DPositionGrid<Character> expandedRows = new Discrete2DPositionGrid<>('.');
        //Loop over each row and see if we find a galaxy, if we don't instead of adding one row, we add two
        for(int originalY = 0; originalY <= input.getMaxY(); originalY++) {
            boolean foundGalaxy = false;
            for(int originalX = 0; originalX <= input.getMaxX(); originalX++) {
                if(input.getValueAtPosition(originalX, originalY) == '#') {
                    foundGalaxy = true;
                }
            }

            for(int originalX = 0; originalX <= input.getMaxX(); originalX++) {
                expandedRows.setValueAtPosition(originalX, originalY+offsetRows, input.getValueAtPosition(originalX, originalY));
                //If we didn't find one, add a second row!
                if(!foundGalaxy) {
                    expandedRows.setValueAtPosition(originalX, originalY+offsetRows+1, '.');
                }
            }

            if(!foundGalaxy) {
                offsetRows++;
            }
        }

        //As above but loop over the columns and try to find empty columns
        int offsetCols = 0;
        Discrete2DPositionGrid<Character> expandedCols = new Discrete2DPositionGrid<>('.');
        for(int originalX = 0; originalX <= expandedRows.getMaxX(); originalX++) {
            boolean foundGalaxy = false;
            for(int originalY = 0; originalY <= expandedRows.getMaxY(); originalY++) {
                if(expandedRows.getValueAtPosition(originalX, originalY) == '#') {
                    foundGalaxy = true;
                }
            }

            for(int originalY = 0; originalY <= expandedRows.getMaxY(); originalY++) {
                expandedCols.setValueAtPosition(originalX+offsetCols, originalY, expandedRows.getValueAtPosition(originalX, originalY));
                //Again if we find an empty column we then add this on
                if(!foundGalaxy) {
                    expandedCols.setValueAtPosition(originalX+offsetCols+1, originalY, '.');
                }
            }

            if(!foundGalaxy) {
                offsetCols++;
            }
        }

        //Now we just sum the manhattan distances between them all and we have what we have!
        long totalSum = 0;
        List<Point> galaxies = expandedCols.getPositionsOfValue('#');
        for(int p1Id = 0; p1Id < galaxies.size(); p1Id++) {
            for(int p2Id = p1Id+1; p2Id < galaxies.size(); p2Id++) {
                totalSum += this.getManhattanDistance(galaxies.get(p1Id), galaxies.get(p2Id));

            }
        }

        return totalSum;
    }

    /**
     * This now uses the slightly smarter way of instead of just calculating manhattan distance we factor in the empty
     * rows and handle them differently
     * @param input Input of galaxies
     * @return Sum of the distances between each one
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {

        //Keep track of the rows and columms which are empty and are "multiplier" rows/columns as I have called them
        Set<Integer> multRows = new HashSet<>();
        Set<Integer> multCols = new HashSet<>();

        //Do the same as before, instead this time just log the multipler rows
        for(int originalY = 0; originalY <= input.getMaxY(); originalY++) {
            boolean foundGalaxy = false;
            for(int originalX = 0; originalX <= input.getMaxX(); originalX++) {
                if(input.getValueAtPosition(originalX, originalY) == '#') {
                    foundGalaxy = true;
                }
            }

            if(!foundGalaxy) {
                multRows.add(originalY);
            }
        }

        //And then log the multiplier columns
        for(int originalX = 0; originalX <= input.getMaxX(); originalX++) {
            boolean foundGalaxy = false;
            for(int originalY = 0; originalY <= input.getMaxY(); originalY++) {
                if(input.getValueAtPosition(originalX, originalY) == '#') {
                    foundGalaxy = true;
                }
            }

            if(!foundGalaxy) {
                multCols.add(originalX);
            }
        }

        //This is the hardcoded multiplier factor
        long multMult = 1000000;

        long totalSum = 0;
        List<Point> galaxies = input.getPositionsOfValue('#');
        for(int p1Id = 0; p1Id < galaxies.size(); p1Id++) {
            for(int p2Id = p1Id+1; p2Id < galaxies.size(); p2Id++) {
                Point p1 = galaxies.get(p1Id);
                Point p2 = galaxies.get(p2Id);

                //First we calculate the manhattan distance
                totalSum += this.getManhattanDistance(p1, p2);

                //Then we (if there is a difference in column) work out if we crossed a mult col
                if(p1.x != p2.x) {
                    //Enforce the ordering just to make it a bit easier to handle
                    if(p1.x > p2.x) {
                        Point tmp = p2;
                        p2 = p1;
                        p1 = tmp;
                    }

                    //Loop from one to another, seeing if we reach any mult cols
                    for(int x = p1.x; x < p2.x; x++) {
                        if(multCols.contains(x)) {
                            //If we do we add the mult value, minus one because the manhattan distance has already counted it once
                            totalSum += multMult - 1;
                        }
                    }
                }

                //Then we (if there is a difference in row) work out if we crossed a mult row
                if(p1.y != p2.y) {
                    //Enforce the ordering just to make it a bit easier to handle
                    if(p1.y > p2.y) {
                        Point tmp = p2;
                        p2 = p1;
                        p1 = tmp;
                    }

                    //Loop from one to another, seeing if we reach any mult rows
                    for(int y = p1.y; y < p2.y; y++) {
                        if(multRows.contains(y)) {
                            //If we do we add the mult value, minus one because the manhattan distance has already counted it once
                            totalSum += multMult - 1;
                        }
                    }
                }
            }
        }

        return totalSum;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 11);

        Day11 d = new Day11();
        long partOne = d.solvePartOne(input);
        System.out.println("The sum of the distances between each galaxy is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The real sum of the distances between each galaxy is " + partTwo);
    }
}


