package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.aoc2023.Day17Pos;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 17: Clumsy Crucible ---
 * The lava starts flowing rapidly once the Lava Production Facility is operational. As you leave, the reindeer
 * offers you a parachute, allowing you to quickly reach Gear Island.
 *
 * As you descend, your bird's-eye view of Gear Island reveals why you had trouble finding anyone on your way up:
 * half of Gear Island is empty, but the half below you is a giant factory city!
 *
 * You land near the gradually-filling pool of lava at the base of your new lavafall. Lavaducts will eventually
 * carry the lava throughout the city, but to make use of it immediately, Elves are loading it into large crucibles
 * on wheels.
 *
 * The crucibles are top-heavy and pushed by hand. Unfortunately, the crucibles become very difficult to steer at
 * high speeds, and so it can be hard to go in a straight line for very long.
 *
 * To get Desert Island the machine parts it needs as soon as possible, you'll need to find the best way to get
 * the crucible from the lava pool to the machine parts factory. To do this, you need to minimize heat loss while
 * choosing a route that doesn't require the crucible to go in a straight line for too long.
 *
 * Fortunately, the Elves here have a map (your puzzle input) that uses traffic patterns, ambient temperature,
 * and hundreds of other parameters to calculate exactly how much heat loss can be expected for a crucible entering
 * any particular city block.
 *
 * For example:
 *
 * 2413432311323
 * 3215453535623
 * 3255245654254
 * 3446585845452
 * 4546657867536
 * 1438598798454
 * 4457876987766
 * 3637877979653
 * 4654967986887
 * 4564679986453
 * 1224686865563
 * 2546548887735
 * 4322674655533
 * Each city block is marked by a single digit that represents the amount of heat loss if the crucible enters that
 * block. The starting point, the lava pool, is the top-left city block; the destination, the machine parts factory,
 * is the bottom-right city block. (Because you already start in the top-left block, you don't incur that block's
 * heat loss unless you leave that block and then return to it.)
 *
 * Because it is difficult to keep the top-heavy crucible going in a straight line for very long, it can move at
 * most three blocks in a single direction before it must turn 90 degrees left or right. The crucible also can't
 * reverse direction; after entering each city block, it may only turn left, continue straight, or turn right.
 *
 * One way to minimize heat loss is this path:
 *
 * 2>>34^>>>1323
 * 32v>>>35v5623
 * 32552456v>>54
 * 3446585845v52
 * 4546657867v>6
 * 14385987984v4
 * 44578769877v6
 * 36378779796v>
 * 465496798688v
 * 456467998645v
 * 12246868655<v
 * 25465488877v5
 * 43226746555v>
 * This path never moves more than three consecutive blocks in the same direction and incurs a heat loss of only 102.
 *
 * Directing the crucible from the lava pool to the machine parts factory, but not moving more than three consecutive
 * blocks in the same direction, what is the least heat loss it can incur?
 *
 * --- Part Two ---
 * The crucibles of lava simply aren't large enough to provide an adequate supply of lava to the machine parts
 * factory. Instead, the Elves are going to upgrade to ultra crucibles.
 *
 * Ultra crucibles are even more difficult to steer than normal crucibles. Not only do they have trouble going in
 * a straight line, but they also have trouble turning!
 *
 * Once an ultra crucible starts moving in a direction, it needs to move a minimum of four blocks in that direction
 * before it can turn (or even before it can stop at the end). However, it will eventually start to get wobbly: an
 * ultra crucible can move a maximum of ten consecutive blocks without turning.
 *
 * In the above example, an ultra crucible could follow this path to minimize heat loss:
 *
 * 2>>>>>>>>1323
 * 32154535v5623
 * 32552456v4254
 * 34465858v5452
 * 45466578v>>>>
 * 143859879845v
 * 445787698776v
 * 363787797965v
 * 465496798688v
 * 456467998645v
 * 122468686556v
 * 254654888773v
 * 432267465553v
 * In the above example, an ultra crucible would incur the minimum possible heat loss of 94.
 *
 * Here's another example:
 *
 * 111111111111
 * 999999999991
 * 999999999991
 * 999999999991
 * 999999999991
 * Sadly, an ultra crucible would need to take an unfortunate path like this one:
 *
 * 1>>>>>>>1111
 * 9999999v9991
 * 9999999v9991
 * 9999999v9991
 * 9999999v>>>>
 * This route causes the ultra crucible to incur the minimum possible heat loss of 71.
 *
 * Directing the ultra crucible from the lava pool to the machine parts factory, what is the least heat loss it can
 * incur?
 */
public class Day17 {

    //Store the minimum and maximum moves so everything can access it
    private int minMove = -1;
    private int maxMove = -1;

    /**
     * Given the current grid and current position this works out all the new positions we could move
     * @param input Current grid
     * @param cur Current position
     * @return All possible potions we could move to
     */
    private List<Day17Pos> getAllMoves(Discrete2DPositionGrid<Integer> input, Day17Pos cur) {
        List<Day17Pos> nextMoves = new ArrayList<>();

        //////////////////////////////////////////////////
        //move left

        //Work out the next direction from this direction
        int newLeftDir = 0;
        int nextXMod = 0;
        int nextYMod = 0;
        if(cur.direction == Directions.SOUTH) {
            newLeftDir = Directions.EAST;
            nextXMod = 1;
        }else if(cur.direction == Directions.NORTH) {
            newLeftDir = Directions.WEST;
            nextXMod = -1;
        }else if(cur.direction == Directions.WEST) {
            newLeftDir = Directions.SOUTH;
            nextYMod = 1;
        }else if(cur.direction == Directions.EAST) {
            newLeftDir = Directions.NORTH;
            nextYMod = -1;
        }

        int nextX = cur.p.x;
        int nextY = cur.p.y;
        int accDistance = cur.distance;
        //Part two there is a set of locations that we can't move so we jump past these
        for(int i = 1; i < this.minMove; i++) {
            nextX += nextXMod;
            nextY += nextYMod;
            accDistance += input.getValueAtPosition(nextX, nextY);
        }
        for(int i = this.minMove; i <= this.maxMove; i++) {
            nextX += nextXMod;
            nextY += nextYMod;
            if(input.pointInsideGraph(nextX, nextY)) {
                accDistance += input.getValueAtPosition(nextX, nextY);
                nextMoves.add(new Day17Pos(accDistance, new Point(nextX, nextY), newLeftDir));
            }
        }

        //////////////////////////////////////////////////
        //move right

        //Work out the next direction from this direction
        int newRightDir = 0;
        nextXMod = 0;
        nextYMod = 0;
        if(cur.direction == Directions.SOUTH) {
            newRightDir = Directions.WEST;
            nextXMod = -1;
        }else if(cur.direction == Directions.NORTH) {
            newRightDir = Directions.EAST;
            nextXMod = 1;
        }else if(cur.direction == Directions.WEST) {
            newRightDir = Directions.NORTH;
            nextYMod = -1;
        }else if(cur.direction == Directions.EAST) {
            newRightDir = Directions.SOUTH;
            nextYMod = 1;
        }

        nextX = cur.p.x;
        nextY = cur.p.y;
        accDistance = cur.distance;
        //Part two there is a set of locations that we can't move so we jump past these
        for(int i = 1; i < this.minMove; i++) {
            nextX += nextXMod;
            nextY += nextYMod;
            accDistance += input.getValueAtPosition(nextX, nextY);
        }
        for(int i = this.minMove; i <= this.maxMove; i++) {
            nextX += nextXMod;
            nextY += nextYMod;
            if(input.pointInsideGraph(nextX, nextY)) {
                accDistance += input.getValueAtPosition(nextX, nextY);
                nextMoves.add(new Day17Pos(accDistance, new Point(nextX, nextY), newRightDir));
            }
        }

        return nextMoves;
    }

    /**
     * Given an input work out the minimum moves from top left to bottom right
     * @param input Grid to move on
     * @return Minimum number of steps to move from left top to right bottom
     */
    public int solve(Discrete2DPositionGrid<Integer> input) {
        PriorityQueue<Day17Pos> processQueue = new PriorityQueue<>();

        //Init the values
        processQueue.add(new Day17Pos(0, new Point(0, 0), Directions.SOUTH));
        processQueue.add(new Day17Pos(0, new Point(0, 0), Directions.EAST));

        Set<String> seenPoints = new HashSet<>();
        Map<String, Integer> seenPointDistances = new HashMap<>();

        int minDistanceFound = Integer.MAX_VALUE;
        while(!processQueue.isEmpty()) {
            Day17Pos cur = processQueue.poll();

            String visitString = cur.getVisitString();
            if(seenPoints.contains(visitString)) {
                continue;
            }
            seenPoints.add(cur.getVisitString());

            //If the distance is already longer than the min distance found, stop
            if(cur.distance > minDistanceFound) {
                continue;
            }

            //If we have found the end, log it
            if(cur.p.x == input.getMaxX() && cur.p.y == input.getMaxY()) {
                minDistanceFound = Math.min(minDistanceFound, cur.distance);
            }else {
                //Otherwise continue!
                List<Day17Pos> moves = this.getAllMoves(input, cur);
                for (Day17Pos m : moves) {
                    if (!seenPoints.contains(m.getVisitString())) {
                        processQueue.add(m);
                    }
                }
            }

        }

        return minDistanceFound;
    }


    /**
     * Attempts to solve this for part one where you can move 1-3 spaces at a time
     * @param input Grid to move on
     * @return Minimum number of steps to move from left top to right bottom
     */
    public long solvePartOne(Discrete2DPositionGrid<Integer> input) {
        this.minMove = 1;
        this.maxMove = 3;
        return this.solve(input);
    }

    /**
     * Attempts to solve this for part one where you can move 4-10 spaces at a time
     * @param input Grid to move on
     * @return Minimum number of steps to move from left top to right bottom
     */
    public long solvePartTwo(Discrete2DPositionGrid<Integer> input) {
        this.minMove = 4;
        this.maxMove = 10;
        return this.solve(input);
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Integer> input = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2023, 17);

        Day17 d = new Day17();
        long partOne = d.solvePartOne(input);
        System.out.println("The least heat loss from the standard crucible is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The least heat loss from the ultra crucible is " + partTwo);
    }
}


