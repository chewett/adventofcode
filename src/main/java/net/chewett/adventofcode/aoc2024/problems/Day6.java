package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;

/**
 * --- Day 6: Guard Gallivant ---
 * The Historians use their fancy device again, this time to whisk you all away to the North Pole prototype suit
 * manufacturing lab... in the year 1518! It turns out that having direct access to history is very convenient for a
 * group of historians.
 *
 * You still have to be careful of time paradoxes, and so it will be important to avoid anyone from 1518 while The
 * Historians search for the Chief. Unfortunately, a single guard is patrolling this part of the lab.
 *
 * Maybe you can work out where the guard will go ahead of time so that The Historians can search safely?
 *
 * You start by making a map (your puzzle input) of the situation. For example:
 *
 * ....#.....
 * .........#
 * ..........
 * ..#.......
 * .......#..
 * ..........
 * .#..^.....
 * ........#.
 * #.........
 * ......#...
 * The map shows the current position of the guard with ^ (to indicate the guard is currently facing up from the
 * perspective of the map). Any obstructions - crates, desks, alchemical reactors, etc. - are shown as #.
 *
 * Lab guards in 1518 follow a very strict patrol protocol which involves repeatedly following these steps:
 *
 * If there is something directly in front of you, turn right 90 degrees.
 * Otherwise, take a step forward.
 * Following the above protocol, the guard moves up several times until she reaches an obstacle (in this case, a
 * pile of failed suit prototypes):
 *
 * ....#.....
 * ....^....#
 * ..........
 * ..#.......
 * .......#..
 * ..........
 * .#........
 * ........#.
 * #.........
 * ......#...
 * Because there is now an obstacle in front of the guard, she turns right before continuing straight in her new
 * facing direction:
 *
 * ....#.....
 * ........>#
 * ..........
 * ..#.......
 * .......#..
 * ..........
 * .#........
 * ........#.
 * #.........
 * ......#...
 * Reaching another obstacle (a spool of several very long polymers), she turns right again and continues downward:
 *
 * ....#.....
 * .........#
 * ..........
 * ..#.......
 * .......#..
 * ..........
 * .#......v.
 * ........#.
 * #.........
 * ......#...
 * This process continues for a while, but the guard eventually leaves the mapped area (after walking past a tank
 * of universal solvent):
 *
 * ....#.....
 * .........#
 * ..........
 * ..#.......
 * .......#..
 * ..........
 * .#........
 * ........#.
 * #.........
 * ......#v..
 * By predicting the guard's route, you can determine which specific positions in the lab will be in the patrol path.
 * Including the guard's starting position, the positions visited by the guard before leaving the area are marked
 * with an X:
 *
 * ....#.....
 * ....XXXXX#
 * ....X...X.
 * ..#.X...X.
 * ..XXXXX#X.
 * ..X.X.X.X.
 * .#XXXXXXX.
 * .XXXXXXX#.
 * #XXXXXXX..
 * ......#X..
 * In this example, the guard will visit 41 distinct positions on your map.
 *
 * Predict the path of the guard. How many distinct positions will the guard visit before leaving the mapped area?
 *
 * --- Part Two ---
 * While The Historians begin working around the guard's patrol route, you borrow their fancy device and step outside
 * the lab. From the safety of a supply closet, you time travel through the last few months and record the nightly
 * status of the lab's guard post on the walls of the closet.
 *
 * Returning after what seems like only a few seconds to The Historians, they explain that the guard's patrol area
 * is simply too large for them to safely search the lab without getting caught.
 *
 * Fortunately, they are pretty sure that adding a single new obstruction won't cause a time paradox. They'd like to
 * place the new obstruction in such a way that the guard will get stuck in a loop, making the rest of the lab safe
 * to search.
 *
 * To have the lowest chance of creating a time paradox, The Historians would like to know all of the possible
 * positions for such an obstruction. The new obstruction can't be placed at the guard's starting position - the guard
 * is there right now and would notice.
 *
 * In the above example, there are only 6 different positions where a new obstruction would cause the guard to get
 * stuck in a loop. The diagrams of these six situations use O to mark the new obstruction, | to show a position
 * where the guard moves up/down, - to show a position where the guard moves left/right, and + to show a position
 * where the guard moves both up/down and left/right.
 *
 * Option one, put a printing press next to the guard's starting position:
 *
 * ....#.....
 * ....+---+#
 * ....|...|.
 * ..#.|...|.
 * ....|..#|.
 * ....|...|.
 * .#.O^---+.
 * ........#.
 * #.........
 * ......#...
 * Option two, put a stack of failed suit prototypes in the bottom right quadrant of the mapped area:
 *
 *
 * ....#.....
 * ....+---+#
 * ....|...|.
 * ..#.|...|.
 * ..+-+-+#|.
 * ..|.|.|.|.
 * .#+-^-+-+.
 * ......O.#.
 * #.........
 * ......#...
 * Option three, put a crate of chimney-squeeze prototype fabric next to the standing desk in the bottom right quadrant:
 *
 * ....#.....
 * ....+---+#
 * ....|...|.
 * ..#.|...|.
 * ..+-+-+#|.
 * ..|.|.|.|.
 * .#+-^-+-+.
 * .+----+O#.
 * #+----+...
 * ......#...
 * Option four, put an alchemical retroencabulator near the bottom left corner:
 *
 * ....#.....
 * ....+---+#
 * ....|...|.
 * ..#.|...|.
 * ..+-+-+#|.
 * ..|.|.|.|.
 * .#+-^-+-+.
 * ..|...|.#.
 * #O+---+...
 * ......#...
 * Option five, put the alchemical retroencabulator a bit to the right instead:
 *
 * ....#.....
 * ....+---+#
 * ....|...|.
 * ..#.|...|.
 * ..+-+-+#|.
 * ..|.|.|.|.
 * .#+-^-+-+.
 * ....|.|.#.
 * #..O+-+...
 * ......#...
 * Option six, put a tank of sovereign glue right next to the tank of universal solvent:
 *
 * ....#.....
 * ....+---+#
 * ....|...|.
 * ..#.|...|.
 * ..+-+-+#|.
 * ..|.|.|.|.
 * .#+-^-+-+.
 * .+----++#.
 * #+----++..
 * ......#O..
 * It doesn't really matter what you choose to use as an obstacle so long as you and The Historians can put it
 * into position without the guard noticing. The important thing is having enough options that you can find one
 * that minimizes time paradoxes, and in this example, there are 6 different positions you could choose.
 *
 * You need to get the guard stuck in a loop by adding a single new obstruction. How many different positions
 * could you choose for this obstruction?
 */
public class Day6 {

    /**
     * Determines how many locations the guard visits before they leave the mapped area
     * @param input Map of the area including the guard
     * @return Number of locations the guard visits
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        Point guard = input.getPositionOfValueAssumingOnlyOne('^');
        int currentDirection = Directions.NORTH;

        Map<Point, Set<Integer>> visitedPoints = new HashMap<>();
        visitedPoints.put(guard, new HashSet<>());
        visitedPoints.get(guard).add(Directions.NORTH);

        //Keep going until we loop or we leave the area (Actually in part 1 we never leave the area)
        boolean alreadyVisitedOrLeftArea = false;
        while (!alreadyVisitedOrLeftArea) {

            // Use my cheeky direction class to make this code a lot nicer
            Point move = Directions.getDirectionModifier(currentDirection);

            Point nextLocation = new Point(guard.x + move.x, guard.y + move.y);
            if(input.getValueAtPosition(nextLocation) == '#') {
                currentDirection = Directions.getDirectionRightOfThis(currentDirection);
            }else if(input.getValueAtPosition(nextLocation) == ' ') {
                alreadyVisitedOrLeftArea = true;
            }else{
                guard = nextLocation;
            }

            if(!visitedPoints.containsKey(guard)) {
                visitedPoints.put(guard, new HashSet<>());
            }

            //This handles loops but actually the first input never loops... doh!
            if(visitedPoints.get(guard).contains(currentDirection)) {
                alreadyVisitedOrLeftArea = true;
            }else{
                visitedPoints.get(guard).add(currentDirection);
            }
        }

        return visitedPoints.size();
    }

    /**
     * Helper function to simulate the guard moving and determine if it loops or not
     * @param input The input with the guard and all blockages
     * @return True if the guard is now looping, false if they leave the area
     */
    public boolean doesLoop(Discrete2DPositionGrid<Character> input) {
        Point guard = input.getPositionOfValueAssumingOnlyOne('^');
        int currentDirection = Directions.NORTH;

        Map<Point, Set<Integer>> visitedPoints = new HashMap<>();
        visitedPoints.put(guard, new HashSet<>());
        visitedPoints.get(guard).add(Directions.NORTH);

        //Pretty much the code in part 1
        boolean alreadyVisited = false;
        while (!alreadyVisited) {
            Point move = Directions.getDirectionModifier(currentDirection);

            Point nextLocation = new Point(guard.x + move.x, guard.y + move.y);
            if(input.getValueAtPosition(nextLocation) == '#') {
                currentDirection = Directions.getDirectionRightOfThis(currentDirection);
            }else if(input.getValueAtPosition(nextLocation) == ' ') {
                return false;
            }else{
                guard = nextLocation;
            }

            if(!visitedPoints.containsKey(guard)) {
                visitedPoints.put(guard, new HashSet<>());
            }

            if(visitedPoints.get(guard).contains(currentDirection)) {
                alreadyVisited = true;
            }else{
                visitedPoints.get(guard).add(currentDirection);
            }
        }

        return true;
    }

    /**
     * Works out how many locations we can put the blockage so that it makes the guard loop
     * @param input The guard position and all blockages
     * @return The number of places we could put a blockage and cause the guard to loop
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {

        int blockagesCouldCauseALoop = 0;
        //Now this is super bruteforce but I think only blockages placed on his path will affect his move
        //So I think I can refactor this to only handle cases where it will actually cause a difference
        for(int x = input.getMinX(); x <= input.getMaxX(); x++) {
            for(int y = input.getMinY(); y <= input.getMaxY(); y++) {
                //If we could put a blockage here, lets try
                if(input.getValueAtPosition(x, y) == '.') {
                    Discrete2DPositionGrid<Character> newGrid = input.clone();
                    newGrid.setValueAtPosition(x, y, '#');

                    if(this.doesLoop(newGrid)) {
                        blockagesCouldCauseALoop++;
                    }
                }
            }
        }

       return blockagesCouldCauseALoop;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 6);

        Day6 d = new Day6();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of locations the guard visits before leaving is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of places that a blockage could be placed is " + partTwo);
    }
}


