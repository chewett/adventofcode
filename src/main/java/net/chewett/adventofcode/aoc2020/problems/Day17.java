package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.datastructures.Discrete3DPositionGrid;
import net.chewett.adventofcode.datastructures.Discrete4DPositionGrid;
import net.chewett.adventofcode.datastructures.Point3D;
import net.chewett.adventofcode.datastructures.Point4D;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.List;

/**
 * --- Day 17: Conway Cubes ---
 * As your flight slowly drifts through the sky, the Elves at the Mythical Information Bureau at the North Pole contact
 * you. They'd like some help debugging a malfunctioning experimental energy source aboard one of their super-secret
 * imaging satellites.
 *
 * The experimental energy source is based on cutting-edge technology: a set of Conway Cubes contained in a pocket
 * dimension! When you hear it's having problems, you can't help but agree to take a look.
 *
 * The pocket dimension contains an infinite 3-dimensional grid. At every integer 3-dimensional coordinate (x,y,z),
 * there exists a single cube which is either active or inactive.
 *
 * In the initial state of the pocket dimension, almost all cubes start inactive. The only exception to this is a small
 * flat region of cubes (your puzzle input); the cubes in this region start in the specified active (#) or inactive (.)
 * state.
 *
 * The energy source then proceeds to boot up by executing six cycles.
 *
 * Each cube only ever considers its neighbors: any of the 26 other cubes where any of their coordinates differ by at
 * most 1. For example, given the cube at x=1,y=2,z=3, its neighbors include the cube at x=2,y=2,z=2, the cube at
 * x=0,y=2,z=3, and so on.
 *
 * During a cycle, all cubes simultaneously change their state according to the following rules:
 *
 * If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube
 * becomes inactive.
 * If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains
 * inactive.
 * The engineers responsible for this experimental energy source would like you to simulate the pocket dimension and
 * determine what the configuration of cubes should be at the end of the six-cycle boot process.
 *
 * For example, consider the following initial state:
 *
 * .#.
 * ..#
 * ###
 * Even though the pocket dimension is 3-dimensional, this initial state represents a small 2-dimensional slice of it.
 * (In particular, this initial state defines a 3x3x1 region of the 3-dimensional space.)
 *
 * Simulating a few cycles from this initial state produces the following configurations, where the result of each
 * cycle is shown layer-by-layer at each given z coordinate (and the frame of view follows the active cells in each
 * cycle):
 *
 * Before any cycles:
 *
 * z=0
 * .#.
 * ..#
 * ###
 *
 *
 * After 1 cycle:
 *
 * z=-1
 * #..
 * ..#
 * .#.
 *
 * z=0
 * #.#
 * .##
 * .#.
 *
 * z=1
 * #..
 * ..#
 * .#.
 *
 *
 * After 2 cycles:
 *
 * z=-2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 * z=-1
 * ..#..
 * .#..#
 * ....#
 * .#...
 * .....
 *
 * z=0
 * ##...
 * ##...
 * #....
 * ....#
 * .###.
 *
 * z=1
 * ..#..
 * .#..#
 * ....#
 * .#...
 * .....
 *
 * z=2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 *
 * After 3 cycles:
 *
 * z=-2
 * .......
 * .......
 * ..##...
 * ..###..
 * .......
 * .......
 * .......
 *
 * z=-1
 * ..#....
 * ...#...
 * #......
 * .....##
 * .#...#.
 * ..#.#..
 * ...#...
 *
 * z=0
 * ...#...
 * .......
 * #......
 * .......
 * .....##
 * .##.#..
 * ...#...
 *
 * z=1
 * ..#....
 * ...#...
 * #......
 * .....##
 * .#...#.
 * ..#.#..
 * ...#...
 *
 * z=2
 * .......
 * .......
 * ..##...
 * ..###..
 * .......
 * .......
 * .......
 * After the full six-cycle boot process completes, 112 cubes are left in the active state.
 *
 * Starting with your given initial configuration, simulate six cycles. How many cubes are left in the active state
 * after the sixth cycle?
 *
 * Your puzzle answer was 368.
 *
 * --- Part Two ---
 * For some reason, your simulated results don't match what the experimental energy source engineers expected.
 * Apparently, the pocket dimension actually has four spatial dimensions, not three.
 *
 * The pocket dimension contains an infinite 4-dimensional grid. At every integer 4-dimensional coordinate (x,y,z,w),
 * there exists a single cube (really, a hypercube) which is still either active or inactive.
 *
 * Each cube only ever considers its neighbors: any of the 80 other cubes where any of their coordinates differ by at
 * most 1. For example, given the cube at x=1,y=2,z=3,w=4, its neighbors include the cube at x=2,y=2,z=3,w=3, the cube
 * at x=0,y=2,z=3,w=4, and so on.
 *
 * The initial state of the pocket dimension still consists of a small flat region of cubes. Furthermore, the same rules
 * for cycle updating still apply: during each cycle, consider the number of active neighbors of each cube.
 *
 * For example, consider the same initial state as in the example above. Even though the pocket dimension is
 * 4-dimensional, this initial state represents a small 2-dimensional slice of it. (In particular, this initial state
 * defines a 3x3x1x1 region of the 4-dimensional space.)
 *
 * Simulating a few cycles from this initial state produces the following configurations, where the result of each
 * cycle is shown layer-by-layer at each given z and w coordinate:
 *
 * Before any cycles:
 *
 * z=0, w=0
 * .#.
 * ..#
 * ###
 *
 *
 * After 1 cycle:
 *
 * z=-1, w=-1
 * #..
 * ..#
 * .#.
 *
 * z=0, w=-1
 * #..
 * ..#
 * .#.
 *
 * z=1, w=-1
 * #..
 * ..#
 * .#.
 *
 * z=-1, w=0
 * #..
 * ..#
 * .#.
 *
 * z=0, w=0
 * #.#
 * .##
 * .#.
 *
 * z=1, w=0
 * #..
 * ..#
 * .#.
 *
 * z=-1, w=1
 * #..
 * ..#
 * .#.
 *
 * z=0, w=1
 * #..
 * ..#
 * .#.
 *
 * z=1, w=1
 * #..
 * ..#
 * .#.
 *
 *
 * After 2 cycles:
 *
 * z=-2, w=-2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 * z=-1, w=-2
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=0, w=-2
 * ###..
 * ##.##
 * #...#
 * .#..#
 * .###.
 *
 * z=1, w=-2
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=2, w=-2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 * z=-2, w=-1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=-1, w=-1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=0, w=-1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=1, w=-1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=2, w=-1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=-2, w=0
 * ###..
 * ##.##
 * #...#
 * .#..#
 * .###.
 *
 * z=-1, w=0
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=0, w=0
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=1, w=0
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=2, w=0
 * ###..
 * ##.##
 * #...#
 * .#..#
 * .###.
 *
 * z=-2, w=1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=-1, w=1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=0, w=1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=1, w=1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=2, w=1
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=-2, w=2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 *
 * z=-1, w=2
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=0, w=2
 * ###..
 * ##.##
 * #...#
 * .#..#
 * .###.
 *
 * z=1, w=2
 * .....
 * .....
 * .....
 * .....
 * .....
 *
 * z=2, w=2
 * .....
 * .....
 * ..#..
 * .....
 * .....
 * After the full six-cycle boot process completes, 848 cubes are left in the active state.
 *
 * Starting with your given initial configuration, simulate six cycles in a 4-dimensional space. How many cubes are left
 * in the active state after the sixth cycle?
 */
public class Day17 {

    /**
     * Giving the starting array of characters this will simulate the bootup and return the number of cubes
     * in the active state
     * @param startingArray Holding a character array showing the initial state
     * @return The number of cubes in the active state at the end of the bootup process
     */
    public long solvePartOne(List<List<Character>> startingArray) {
        Discrete3DPositionGrid<Character> grid = new Discrete3DPositionGrid<>('0');

        //Initialize the grid with the input data
        int y = 0;
        for(List<Character> row: startingArray) {
            int x = 0;
            for(char c : row) {
                if(c == '#') {
                    grid.setValueAtPosition(x, y, 0, c);
                }
                x++;
            }
            y++;
        }

        //Simulate the process for 6 cycles
        int simulationTime = 0;
        while(simulationTime < 6) {
            //Find everything touching a # and list them
            List<Point3D> allPossibleNeighbours = grid.getNeighboursOfPointsWithValue('#');
            Discrete3DPositionGrid<Character> newGrid = new Discrete3DPositionGrid<>('0');

            //Iterate over them slowly adding data to the newly created grid
            for(Point3D pos : allPossibleNeighbours) {
                int activeNeighbours = grid.countNeighboursWithValue(pos, '#');
                if(grid.getValueAtPosition(pos) == '#' && (activeNeighbours == 3 || activeNeighbours == 2)) {
                    newGrid.setValueAtPosition(pos, '#');
                }else{
                    if(activeNeighbours == 3) {
                        newGrid.setValueAtPosition(pos, '#');
                    }
                }
            }
            simulationTime++;
            grid = newGrid;
        }

        //Finally count the instances of that value
        return grid.countInstancesOfValue('#');
    }

    /**
     * Giving the starting array of characters this will simulate the bootup and return the number of cubes
     * in the active state
     * @param startingArray Holding a character array showing the initial state
     * @return The number of cubes in the active state at the end of the bootup process
     */
    public long solvePartTwo(List<List<Character>> startingArray) {
        Discrete4DPositionGrid<Character> grid = new Discrete4DPositionGrid<>('0');

        //Initialize the grid with the input data
        int y = 0;
        for(List<Character> row: startingArray) {
            int x = 0;
            for(char c : row) {
                if(c == '#') {
                    grid.setValueAtPosition(x, y, 0, 0, c);
                }
                x++;
            }
            y++;
        }

        //Simulate the process for 6 cycles
        int simulationTime = 0;
        while(simulationTime < 6) {
            //Find everything touching a # and list them
            List<Point4D> allPossibleNeighbours = grid.getNeighboursOfPointsWithValue('#');
            Discrete4DPositionGrid<Character> newGrid = new Discrete4DPositionGrid<>('0');

            //Iterate over them slowly adding data to the newly created grid
            for(Point4D pos : allPossibleNeighbours) {
                int activeNeighbours = grid.countNeighboursWithValue(pos, '#');
                if(grid.getValueAtPosition(pos) == '#' && (activeNeighbours == 3 || activeNeighbours == 2)) {
                    newGrid.setValueAtPosition(pos, '#');
                }else{
                    if(activeNeighbours == 3) {
                        newGrid.setValueAtPosition(pos, '#');
                    }
                }
            }
            simulationTime++;
            grid = newGrid;
        }

        return grid.countInstancesOfValue('#');
    }

    public static void main(String[] args) {
        List<List<Character>> startingArray = ProblemLoader.loadProblemIntoXYCharList(2020, 17);
        Day17 d = new Day17();
        long p1 = d.solvePartOne(startingArray);
        System.out.println("Cubes active after the bootup in three dimensional space " + p1);
        long p2 = d.solvePartTwo(startingArray);
        System.out.println("Cubes active after bootup in four dimensional space " + p2);
    }

}
