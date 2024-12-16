package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.aoc2024.DirectionPositionCost;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * --- Day 16: Reindeer Maze ---
 * It's time again for the Reindeer Olympics! This year, the big event is the Reindeer Maze, where the Reindeer
 * compete for the lowest score.
 *
 * You and The Historians arrive to search for the Chief right as the event is about to start. It wouldn't hurt
 * to watch a little, right?
 *
 * The Reindeer start on the Start Tile (marked S) facing East and need to reach the End Tile (marked E). They can
 * move forward one tile at a time (increasing their score by 1 point), but never into a wall (#). They can also
 * rotate clockwise or counterclockwise 90 degrees at a time (increasing their score by 1000 points).
 *
 * To figure out the best place to sit, you start by grabbing a map (your puzzle input) from a nearby kiosk. For
 * example:
 *
 * ###############
 * #.......#....E#
 * #.#.###.#.###.#
 * #.....#.#...#.#
 * #.###.#####.#.#
 * #.#.#.......#.#
 * #.#.#####.###.#
 * #...........#.#
 * ###.#.#####.#.#
 * #...#.....#.#.#
 * #.#.#.###.#.#.#
 * #.....#...#.#.#
 * #.###.#.#.#.#.#
 * #S..#.....#...#
 * ###############
 * There are many paths through this maze, but taking any of the best paths would incur a score of only 7036. This can
 * be achieved by taking a total of 36 steps forward and turning 90 degrees a total of 7 times:
 *
 *
 * ###############
 * #.......#....E#
 * #.#.###.#.###^#
 * #.....#.#...#^#
 * #.###.#####.#^#
 * #.#.#.......#^#
 * #.#.#####.###^#
 * #..>>>>>>>>v#^#
 * ###^#.#####v#^#
 * #>>^#.....#v#^#
 * #^#.#.###.#v#^#
 * #^....#...#v#^#
 * #^###.#.#.#v#^#
 * #S..#.....#>>^#
 * ###############
 * Here's a second example:
 *
 * #################
 * #...#...#...#..E#
 * #.#.#.#.#.#.#.#.#
 * #.#.#.#...#...#.#
 * #.#.#.#.###.#.#.#
 * #...#.#.#.....#.#
 * #.#.#.#.#.#####.#
 * #.#...#.#.#.....#
 * #.#.#####.#.###.#
 * #.#.#.......#...#
 * #.#.###.#####.###
 * #.#.#...#.....#.#
 * #.#.#.#####.###.#
 * #.#.#.........#.#
 * #.#.#.#########.#
 * #S#.............#
 * #################
 * In this maze, the best paths cost 11048 points; following one such path would look like this:
 *
 * #################
 * #...#...#...#..E#
 * #.#.#.#.#.#.#.#^#
 * #.#.#.#...#...#^#
 * #.#.#.#.###.#.#^#
 * #>>v#.#.#.....#^#
 * #^#v#.#.#.#####^#
 * #^#v..#.#.#>>>>^#
 * #^#v#####.#^###.#
 * #^#v#..>>>>^#...#
 * #^#v###^#####.###
 * #^#v#>>^#.....#.#
 * #^#v#^#####.###.#
 * #^#v#^........#.#
 * #^#v#^#########.#
 * #S#>>^..........#
 * #################
 * Note that the path shown above includes one 90 degree turn as the very first move, rotating the Reindeer from facing
 * East to facing North.
 *
 * Analyze your map carefully. What is the lowest score a Reindeer could possibly get?
 *
 * --- Part Two ---
 * Now that you know what the best paths look like, you can figure out the best spot to sit.
 *
 * Every non-wall tile (S, ., or E) is equipped with places to sit along the edges of the tile. While determining
 * which of these tiles would be the best spot to sit depends on a whole bunch of factors (how comfortable the seats
 * are, how far away the bathrooms are, whether there's a pillar blocking your view, etc.), the most important factor
 * is whether the tile is on one of the best paths through the maze. If you sit somewhere else, you'd miss all the
 * action!
 *
 * So, you'll need to determine which tiles are part of any best path through the maze, including the S and E tiles.
 *
 * In the first example, there are 45 tiles (marked O) that are part of at least one of the various best paths through
 * the maze:
 *
 * ###############
 * #.......#....O#
 * #.#.###.#.###O#
 * #.....#.#...#O#
 * #.###.#####.#O#
 * #.#.#.......#O#
 * #.#.#####.###O#
 * #..OOOOOOOOO#O#
 * ###O#O#####O#O#
 * #OOO#O....#O#O#
 * #O#O#O###.#O#O#
 * #OOOOO#...#O#O#
 * #O###.#.#.#O#O#
 * #O..#.....#OOO#
 * ###############
 * In the second example, there are 64 tiles that are part of at least one of the best paths:
 *
 * #################
 * #...#...#...#..O#
 * #.#.#.#.#.#.#.#O#
 * #.#.#.#...#...#O#
 * #.#.#.#.###.#.#O#
 * #OOO#.#.#.....#O#
 * #O#O#.#.#.#####O#
 * #O#O..#.#.#OOOOO#
 * #O#O#####.#O###O#
 * #O#O#..OOOOO#OOO#
 * #O#O###O#####O###
 * #O#O#OOO#..OOO#.#
 * #O#O#O#####O###.#
 * #O#O#OOOOOOO..#.#
 * #O#O#O#########.#
 * #O#OOO..........#
 * #################
 * Analyze your map further. How many tiles are part of at least one of the best paths through the maze?
 */
public class Day16 {

    /**
     * Find the length of the shortest path from start to end
     * @param input Grid
     * @return The length of the shortest path
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        Point start = input.getPositionOfValueAssumingOnlyOne('S');
        Point end = input.getPositionOfValueAssumingOnlyOne('E');

        Queue<DirectionPositionCost> explorePoints = new LinkedList<>();
        //This assumes the visit costs for the directions are the same which isn't actually true but it works for my input
        //Need to change this to make it more thorough as a technical exercise
        Discrete2DPositionGrid<Long> visitCosts = new Discrete2DPositionGrid<>(Long.MAX_VALUE);

        explorePoints.add(new DirectionPositionCost(Directions.EAST, start, 0));

        long minCost = Long.MAX_VALUE;
        while(explorePoints.size() > 0) {
            DirectionPositionCost curPoint = explorePoints.poll();

            //Yay we have finished :)
            if(curPoint.pos.equals(end)) {
                minCost = Math.min(minCost, curPoint.cost);
                continue;
            }

            //We visited here in a better speed so ignore it
            if(curPoint.cost > visitCosts.getValueAtPosition(curPoint.pos)) {
                continue;
            }

            visitCosts.setValueAtPosition(curPoint.pos, curPoint.cost);

            List<Point> adjacentPoints = input.getDirectlyAdjacentPoints(curPoint.pos);
            for(Point adjacentPoint : adjacentPoints) {
                //A non # is the only possible location to visit
                if(input.getValueAtPosition(adjacentPoint) != '#') {
                    int directionFromCurrentPoint = Directions.getDirectionFromPointToPoint(curPoint.pos, adjacentPoint);
                    int turnsNeeded = Directions.getTurnsNeededToMoveFromDirectionToDirection(curPoint.direction, directionFromCurrentPoint);
                    explorePoints.add(new DirectionPositionCost(directionFromCurrentPoint, adjacentPoint, curPoint.cost + 1 + (1000 * turnsNeeded)));
                }
            }
        }

        return minCost;
    }

    /**
     * Works out all the paths on the "best" lengths
     * @param input Grid
     * @return
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        Point start = input.getPositionOfValueAssumingOnlyOne('S');
        Point end = input.getPositionOfValueAssumingOnlyOne('E');

        //TODO: Convert this to a priority queue
        Queue<DirectionPositionCost> explorePoints = new LinkedList<>();
        Discrete2DPositionGrid<Long> visitCostsNorth = new Discrete2DPositionGrid<>(Long.MAX_VALUE);
        Discrete2DPositionGrid<Long> visitCostsSouth = new Discrete2DPositionGrid<>(Long.MAX_VALUE);
        Discrete2DPositionGrid<Long> visitCostsEast = new Discrete2DPositionGrid<>(Long.MAX_VALUE);
        Discrete2DPositionGrid<Long> visitCostsWest = new Discrete2DPositionGrid<>(Long.MAX_VALUE);

        Set<String> allFinalPoints = new HashSet<>();

        explorePoints.add(new DirectionPositionCost(Directions.EAST, start, 0, start.x + "," + start.y));

        long minCost = Long.MAX_VALUE;
        while(explorePoints.size() > 0) {
            DirectionPositionCost curPoint = explorePoints.poll();

            //Nasty way to handle this. Consider moving to something else
            Discrete2DPositionGrid<Long> visitCosts;
            if(curPoint.direction == Directions.NORTH) {
                visitCosts = visitCostsNorth;
            }else if(curPoint.direction == Directions.SOUTH) {
                visitCosts = visitCostsSouth;
            }else if(curPoint.direction == Directions.EAST) {
                visitCosts = visitCostsEast;
            }else if(curPoint.direction == Directions.WEST) {
                visitCosts = visitCostsWest;
            }else{
                throw new RuntimeException("Unexpected direction: " + curPoint.direction);
            }

            //We visited here in a better speed so ignore it
            if(curPoint.cost > visitCosts.getValueAtPosition(curPoint.pos)) {
                continue;
            }

            visitCosts.setValueAtPosition(curPoint.pos, curPoint.cost);

            //Yay we have finished :)
            if(curPoint.pos.equals(end)) {
                //Store all the places we visited (it's split by a |) so we track it more easily
                if(curPoint.cost < minCost) {
                    minCost = curPoint.cost;
                    allFinalPoints = new HashSet<>(Arrays.stream(curPoint.cameFrom.split("\\|")).toList());
                    allFinalPoints.add(curPoint.pos.toString());
                }else if(curPoint.cost == minCost) {
                    allFinalPoints.addAll(Arrays.stream(curPoint.cameFrom.split("\\|")).toList());
                }
                continue;
            }

            List<Point> adjacentPoints = input.getDirectlyAdjacentPoints(curPoint.pos);
            String visitedSoFar = curPoint.cameFrom + "|" + curPoint.pos.x +","+curPoint.pos.y;

            for(Point adjacentPoint : adjacentPoints) {
                //A non # is the only possible location to visit
                if(input.getValueAtPosition(adjacentPoint) != '#') {
                    int directionFromCurrentPoint = Directions.getDirectionFromPointToPoint(curPoint.pos, adjacentPoint);
                    int turnsNeeded = Directions.getTurnsNeededToMoveFromDirectionToDirection(curPoint.direction, directionFromCurrentPoint);
                    long newCost = curPoint.cost + 1 + (1000 * turnsNeeded);

                    if(newCost <= minCost) {

                        Discrete2DPositionGrid<Long> visitChecker;
                        if(directionFromCurrentPoint == Directions.NORTH) {
                            visitChecker = visitCostsNorth;
                        }else if(directionFromCurrentPoint == Directions.SOUTH) {
                            visitChecker = visitCostsSouth;
                        }else if(directionFromCurrentPoint == Directions.EAST) {
                            visitChecker = visitCostsEast;
                        }else if(directionFromCurrentPoint == Directions.WEST) {
                            visitChecker = visitCostsWest;
                        }else{
                            throw new RuntimeException("Unexpected direction: " + directionFromCurrentPoint);
                        }

                        if(newCost <= visitChecker.getValueAtPosition(adjacentPoint)) {
                            explorePoints.add(new DirectionPositionCost(directionFromCurrentPoint, adjacentPoint,
                                    newCost, visitedSoFar));
                        }
                    }
                }
            }
        }

        return allFinalPoints.size();
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 16);

        Day16 d = new Day16();
        long partOne = d.solvePartOne(input);
        System.out.println("The quickest path to the end costs " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of 'best' possible locations to sit at is " + partTwo);
    }
}


