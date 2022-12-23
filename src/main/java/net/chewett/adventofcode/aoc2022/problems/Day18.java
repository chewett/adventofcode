package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete3DPositionGrid;
import net.chewett.adventofcode.datastructures.Point3D;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 18: Boiling Boulders ---
 * You and the elephants finally reach fresh air. You've emerged near the base of a large volcano that seems to be
 * actively erupting! Fortunately, the lava seems to be flowing away from you and toward the ocean.
 *
 * Bits of lava are still being ejected toward you, so you're sheltering in the cavern exit a little longer. Outside
 * the cave, you can see the lava landing in a pond and hear it loudly hissing as it solidifies.
 *
 * Depending on the specific compounds in the lava and speed at which it cools, it might be forming obsidian! The
 * cooling rate should be based on the surface area of the lava droplets, so you take a quick scan of a droplet as it
 * flies past you (your puzzle input).
 *
 * Because of how quickly the lava is moving, the scan isn't very good; its resolution is quite low and, as a result,
 * it approximates the shape of the lava droplet with 1x1x1 cubes on a 3D grid, each given as its x,y,z position.
 *
 * To approximate the surface area, count the number of sides of each cube that are not immediately connected to another
 * cube. So, if your scan were only two adjacent cubes like 1,1,1 and 2,1,1, each cube would have a single side covered
 * and five sides exposed, a total surface area of 10 sides.
 *
 * Here's a larger example:
 *
 * 2,2,2
 * 1,2,2
 * 3,2,2
 * 2,1,2
 * 2,3,2
 * 2,2,1
 * 2,2,3
 * 2,2,4
 * 2,2,6
 * 1,2,5
 * 3,2,5
 * 2,1,5
 * 2,3,5
 * In the above example, after counting up all the sides that aren't connected to another cube, the total surface area
 * is 64.
 *
 * What is the surface area of your scanned lava droplet?
 *
 * --- Part Two ---
 * Something seems off about your calculation. The cooling rate depends on exterior surface area, but your calculation
 * also included the surface area of air pockets trapped in the lava droplet.
 *
 * Instead, consider only cube sides that could be reached by the water and steam as the lava droplet tumbles into the
 * pond. The steam will expand to reach as much as possible, completely displacing any air on the outside of the lava
 * droplet but never expanding diagonally.
 *
 * In the larger example above, exactly one cube of air is trapped within the lava droplet (at 2,2,5), so the exterior
 * surface area of the lava droplet is 58.
 *
 * What is the exterior surface area of your scanned lava droplet?
 */
public class Day18 {

    private Discrete3DPositionGrid<Character> parseInput(List<String> input) {
        Discrete3DPositionGrid<Character> grid = new Discrete3DPositionGrid<>(' ');
        for(String in : input) {
            String[] coordinatesPart = in.split(",");
            Point3D p = new Point3D(Integer.parseInt(coordinatesPart[0]), Integer.parseInt(coordinatesPart[1]), Integer.parseInt(coordinatesPart[2]));
            grid.setValueAtPosition(p, '#');
        }
        return grid;
    }


    /**
     * Works out the number of faces the lava droplet has
     * @param input Locations of the lava droplet parts
     * @return Number of faces the lava droplet has
     */
    public long solvePartOne(List<String> input) {
        Discrete3DPositionGrid<Character> grid = this.parseInput(input);

        int sides = 0;
        List<Point3D> neighbours = grid.getDirectlyAdjacentNeighboursOfPointsWithValue('#');
        for(Point3D neigh : neighbours) {
            if(grid.getValueAtPosition(neigh) == ' ') {
                sides++;
            }
        }

        return sides;
    }

    /**
     * Works out the exterior number of faces the lava droplet has
     *
     * This works by determining a starting point, and then "flowing around" trackinge every point the water could
     * reach around the droplet. Once we know a list of valid points we can then cross reference them with all
     * the faces
     *
     * @param input Locations of hte lava droplet parts
     * @return Number of exterior faces the lava droplet has
     */
    public long solvePartTwo(List<String> input) {
        Discrete3DPositionGrid<Character> grid = this.parseInput(input);

        //Define a min and max area to flood with water
        int minX = grid.getMinX() - 2;
        int minY = grid.getMinY() - 2;
        int minZ = grid.getMinZ() - 2;
        int maxX = grid.getMaxX() + 2;
        int maxY = grid.getMaxY() + 2;
        int maxZ = grid.getMaxZ() + 2;

        Queue<Point3D> waterFlowing = new LinkedList<>();
        //Add start point
        waterFlowing.add(new Point3D(minX, minY, minZ));

        Set<Point3D> waterPoints = new HashSet<>();
        Set<Point3D> checked = new HashSet<>();

        while(waterFlowing.size() > 0) {
            Point3D pointToReview = waterFlowing.poll();

            if( grid.getValueAtPosition(pointToReview) == ' ') {
                //Only process this point if it's an air space
                waterPoints.add(pointToReview);

                List<Point3D> nextPoints = pointToReview.getDirectlyAdjacentNeighbours();
                for(Point3D nextPoint : nextPoints) {
                    if(!waterPoints.contains(nextPoint) &&
                        //Keep inside the bounds defined above
                        nextPoint.getX() >= minX && nextPoint.getX() <= maxX &&
                        nextPoint.getY() >= minY && nextPoint.getY() <= maxY &&
                        nextPoint.getZ() >= minZ && nextPoint.getZ() <= maxZ &&
                        !checked.contains(nextPoint)
                    ) {
                        checked.add(nextPoint);
                        waterFlowing.add(nextPoint);
                    }
                }
            }
        }

        //Now only add up sides that could be exposed to water!
        int sides = 0;
        List<Point3D> neighbours = grid.getDirectlyAdjacentNeighboursOfPointsWithValue('#');
        for(Point3D pointToCheck : neighbours) {
            if(waterPoints.contains(pointToCheck)) {
                sides++;
            }
        }

        return sides;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 18);

        Day18 d = new Day18();
        long partOne = d.solvePartOne(input);
        System.out.println("The surface area of the droplet is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The exterior surface area of the droplet is "+ partTwo);

    }


}
