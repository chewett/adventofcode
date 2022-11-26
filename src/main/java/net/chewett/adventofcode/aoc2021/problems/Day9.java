package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 9: Smoke Basin ---
 * These caves seem to be lava tubes. Parts are even still volcanically active; small hydrothermal vents release smoke
 * into the caves that slowly settles like rain.
 *
 * If you can model how the smoke flows through the caves, you might be able to avoid it and be that much safer. The
 * submarine generates a heightmap of the floor of the nearby caves for you (your puzzle input).
 *
 * Smoke flows to the lowest point of the area it's in. For example, consider the following heightmap:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * Each number corresponds to the height of a particular location, where 9 is the highest and 0 is the lowest a location
 * can be.
 *
 * Your first goal is to find the low points - the locations that are lower than any of its adjacent locations. Most
 * locations have four adjacent locations (up, down, left, and right); locations on the edge or corner of the map have
 * three or two adjacent locations, respectively. (Diagonal locations do not count as adjacent.)
 *
 * In the above example, there are four low points, all highlighted: two are in the first row (a 1 and a 0), one is in
 * the third row (a 5), and one is in the bottom row (also a 5). All other locations on the heightmap have some lower
 * adjacent location, and so are not low points.
 *
 * The risk level of a low point is 1 plus its height. In the above example, the risk levels of the low points are 2,
 * 1, 6, and 6. The sum of the risk levels of all low points in the heightmap is therefore 15.
 *
 * Find all of the low points on your heightmap. What is the sum of the risk levels of all low points on your heightmap?
 *
 * --- Part Two ---
 * Next, you need to find the largest basins so you know what areas are most important to avoid.
 *
 * A basin is all locations that eventually flow downward to a single low point. Therefore, every low point has a basin,
 * although some basins are very small. Locations of height 9 do not count as being in any basin, and all other
 * locations will always be part of exactly one basin.
 *
 * The size of a basin is the number of locations within the basin, including the low point. The example above has four
 * basins.
 *
 * The top-left basin, size 3:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * The top-right basin, size 9:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * The middle basin, size 14:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * The bottom-right basin, size 9:
 *
 * 2199943210
 * 3987894921
 * 9856789892
 * 8767896789
 * 9899965678
 * Find the three largest basins and multiply their sizes together. In the above example, this is 9 * 14 * 9 = 1134.
 *
 * What do you get if you multiply together the sizes of the three largest basins?
 */
public class Day9 {

    /**
     * Find all the points where there is a local minimum around them
     * @param grid Grid representing levels of the sea floor
     * @return The sum of all the riskiest points
     */
    public long solvePartOne(Discrete2DPositionGrid<Integer> grid) {
        int maxX = grid.getMaxX();
        int maxY = grid.getMaxY();
        int riskLevels = 0;

        for(int x = 0; x < maxX+1; x++) {
            for(int y = 0; y < maxY+1; y++) {
                int curNumber = grid.getValueAtPosition(x, y);
                boolean mostRisky = true;

                //Find all the adjacent points and check to see if they are more or less risky
                List<Point> adjacentPoints = grid.getDirectlyAdjacentPoints(new Point(x, y));
                for(Point adjacentPoint : adjacentPoints) {
                    mostRisky = mostRisky && curNumber < grid.getValueAtPosition(adjacentPoint);
                }

                //If this is the riskiest point then sum the risk values
                if(mostRisky) {
                    riskLevels += curNumber + 1;
                }
            }
        }

        return riskLevels;
    }


    /**
     * Find the three largest basins and multiply their sizes together
     * @param grid Grid representing levels of the sea floor
     * @return The three largest basins multiplied together
     */
    public long solvePartTwo(Discrete2DPositionGrid<Integer> grid) {
        //Store a mapping of what isn't and what elements have already been determined as a basin (to ignore)
        Discrete2DPositionGrid<Integer> basinFound = new Discrete2DPositionGrid<>(0);

        int maxX = grid.getMaxX();
        int maxY = grid.getMaxY();
        int currentBasinNumber = 1;
        Map<Integer, Integer> basinSizeMap = new HashMap<>();

        //go over the entire X/Y region of the seafloor
        for(int x = 0; x < maxX+1; x++) {
            for (int y = 0; y < maxY + 1; y++) {

                //Get the value
                int currentValue = grid.getValueAtPosition(x, y);
                if(currentValue == 9) {
                    //Store that this isn't a basin, so set -1
                    basinFound.setValueAtPosition(x, y, -1);

                //If the basinFound value is 0 then this is a new basin and therefore we should find its size
                }else if(basinFound.getValueAtPosition(x, y) == 0) {
                    //Time to find a basin!

                    //Keep track of the points contained in the basin
                    List<Point> containedInBasin = new ArrayList<>();
                    //Keep track of a queue of points to check to see if its in the basin
                    Queue<Point> basinCoordSearching = new LinkedList<>();
                    //Add the initial start point
                    basinCoordSearching.add(new Point(x, y));

                    //Keep going until we have exhausted all the basin check points
                    while(basinCoordSearching.size() > 0) {
                        Point currentPointToInvestigate = basinCoordSearching.poll();
                        int pointValue = grid.getValueAtPosition(currentPointToInvestigate);
                        //If its not 9, then its in the basin!
                        if(pointValue != 9) {
                            containedInBasin.add(currentPointToInvestigate);
                            List<Point> adjacentPoints = grid.getDirectlyAdjacentPoints(currentPointToInvestigate);
                            for(Point p : adjacentPoints) {
                                //If a point around it isn't in our list already and isn't already in our check list add it!
                                if(!containedInBasin.contains(p) && !basinCoordSearching.contains(p)) {
                                    basinCoordSearching.add(p);
                                }
                            }
                        }
                    }

                    //Once we have run out of points to check its time to calculate the size
                    basinSizeMap.put(currentBasinNumber, containedInBasin.size());
                    //We also mark up all the points which have been allocated to a basin so we don't double count
                    for(Point p : containedInBasin) {
                        basinFound.setValueAtPosition(p, currentBasinNumber);
                    }
                    currentBasinNumber++;
                }

            }
        }

        //And then just get all the sizes and then sort if for the highest
        List<Integer> allBasinSizes = new ArrayList<>();
        for(Map.Entry<Integer, Integer> e : basinSizeMap.entrySet()) {
            allBasinSizes.add(e.getValue());
        }
        Collections.sort(allBasinSizes);

        //Take the highest three and multiply them
        return allBasinSizes.get(currentBasinNumber - 2) *
                allBasinSizes.get(currentBasinNumber - 3) *
                allBasinSizes.get(currentBasinNumber - 4);
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Integer> grid = ProblemLoader.loadProblemIntoDiscrete2DPositionGrid(2021, 9);

        Day9 d = new Day9();
        long partOne = d.solvePartOne(grid);
        System.out.println("The sum of all the risky points is " + partOne);

        long partTwo = d.solvePartTwo(grid);
        System.out.println("The multiplication of the three largest basins is " + partTwo);
    }

}
