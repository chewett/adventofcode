package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.aoc2019.intcode.instructions.JumpIfFalseInstruction;
import net.chewett.adventofcode.aoc2023.Day23State;
import net.chewett.adventofcode.aoc2023.Link;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ListHelpers;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.apache.commons.math3.analysis.function.Cos;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 23: A Long Walk ---
 * The Elves resume water filtering operations! Clean water starts flowing over the edge of Island Island.
 *
 * They offer to help you go over the edge of Island Island, too! Just hold on tight to one end of this impossibly
 * long rope and they'll lower you down a safe distance from the massive waterfall you just created.
 *
 * As you finally reach Snow Island, you see that the water isn't really reaching the ground: it's being absorbed by
 * the air itself. It looks like you'll finally have a little downtime while the moisture builds up to snow-producing
 * levels. Snow Island is pretty scenic, even without any snow; why not take a walk?
 *
 * There's a map of nearby hiking trails (your puzzle input) that indicates paths (.), forest (#), and steep slopes
 * (^, >, v, and <).
 *
 * For example:
 *
 * #.#####################
 * #.......#########...###
 * #######.#########.#.###
 * ###.....#.>.>.###.#.###
 * ###v#####.#v#.###.#.###
 * ###.>...#.#.#.....#...#
 * ###v###.#.#.#########.#
 * ###...#.#.#.......#...#
 * #####.#.#.#######.#.###
 * #.....#.#.#.......#...#
 * #.#####.#.#.#########v#
 * #.#...#...#...###...>.#
 * #.#.#v#######v###.###v#
 * #...#.>.#...>.>.#.###.#
 * #####v#.#.###v#.#.###.#
 * #.....#...#...#.#.#...#
 * #.#########.###.#.#.###
 * #...###...#...#...#.###
 * ###.###.#.###v#####v###
 * #...#...#.#.>.>.#.>.###
 * #.###.###.#.###.#.#v###
 * #.....###...###...#...#
 * #####################.#
 * You're currently on the single path tile in the top row; your goal is to reach the single path tile in the bottom
 * row. Because of all the mist from the waterfall, the slopes are probably quite icy; if you step onto a slope tile,
 * your next step must be downhill (in the direction the arrow is pointing). To make sure you have the most scenic hike
 * possible, never step onto the same tile twice. What is the longest hike you can take?
 *
 * In the example above, the longest hike you can take is marked with O, and your starting position is marked S:
 *
 * #S#####################
 * #OOOOOOO#########...###
 * #######O#########.#.###
 * ###OOOOO#OOO>.###.#.###
 * ###O#####O#O#.###.#.###
 * ###OOOOO#O#O#.....#...#
 * ###v###O#O#O#########.#
 * ###...#O#O#OOOOOOO#...#
 * #####.#O#O#######O#.###
 * #.....#O#O#OOOOOOO#...#
 * #.#####O#O#O#########v#
 * #.#...#OOO#OOO###OOOOO#
 * #.#.#v#######O###O###O#
 * #...#.>.#...>OOO#O###O#
 * #####v#.#.###v#O#O###O#
 * #.....#...#...#O#O#OOO#
 * #.#########.###O#O#O###
 * #...###...#...#OOO#O###
 * ###.###.#.###v#####O###
 * #...#...#.#.>.>.#.>O###
 * #.###.###.#.###.#.#O###
 * #.....###...###...#OOO#
 * #####################O#
 * This hike contains 94 steps. (The other possible hikes you could have taken were 90, 86, 82, 82, and 74 steps long.)
 *
 * Find the longest hike you can take through the hiking trails listed on your map. How many steps long is the longest
 * hike?
 *
 * --- Part Two ---
 * As you reach the trailhead, you realize that the ground isn't as slippery as you expected; you'll have no problem
 * climbing up the steep slopes.
 *
 * Now, treat all slopes as if they were normal paths (.). You still want to make sure you have the most scenic hike
 * possible, so continue to ensure that you never step onto the same tile twice. What is the longest hike you can take?
 *
 * In the example above, this increases the longest hike to 154 steps:
 *
 * #S#####################
 * #OOOOOOO#########OOO###
 * #######O#########O#O###
 * ###OOOOO#.>OOO###O#O###
 * ###O#####.#O#O###O#O###
 * ###O>...#.#O#OOOOO#OOO#
 * ###O###.#.#O#########O#
 * ###OOO#.#.#OOOOOOO#OOO#
 * #####O#.#.#######O#O###
 * #OOOOO#.#.#OOOOOOO#OOO#
 * #O#####.#.#O#########O#
 * #O#OOO#...#OOO###...>O#
 * #O#O#O#######O###.###O#
 * #OOO#O>.#...>O>.#.###O#
 * #####O#.#.###O#.#.###O#
 * #OOOOO#...#OOO#.#.#OOO#
 * #O#########O###.#.#O###
 * #OOO###OOO#OOO#...#O###
 * ###O###O#O###O#####O###
 * #OOO#OOO#O#OOO>.#.>O###
 * #O###O###O#O###.#.#O###
 * #OOOOO###OOO###...#OOO#
 * #####################O#
 * Find the longest hike you can take through the surprisingly dry hiking trails listed on your map. How many steps
 * long is the longest hike?
 */
public class Day23 {

    /**
     * Attempts to hike around snow island from start to finish taking the most scenic route
     * But you can only go down steps as they are icy!
     * @param input Map of snow island
     * @return Longest hike we can go on
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        //work out the start and finish
        Point start = null;
        Point end = null;
        for(int x=0; x <= input.getMaxX(); x++) {
            if(input.getValueAtPosition(x, 0) == '.') {
                start = new Point(x, 0);
            }
            if(input.getValueAtPosition(x, input.getMaxY()) == '.') {
                end = new Point(x, input.getMaxY());
            }
        }
        if(start == null || end == null) {
            throw new RuntimeException("Start or end is null for some reason");
        }

        //Keep track of the costs
        Stack<Discrete2DPositionGrid<Integer>> costsQueue = new Stack<>();

        Discrete2DPositionGrid<Integer> costs = new Discrete2DPositionGrid<>(-1);
        costs.setValueAtPosition(start, 0);
        costsQueue.add(costs);

        //Keep going from point to point keeping track of the points we have visited and the costs
        List<Integer> allDistances = new ArrayList<>();
        Stack<Point> pointsToExplode = new Stack<>();
        pointsToExplode.add(start);
        while(!pointsToExplode.isEmpty()) {
            Point curPos = pointsToExplode.pop();
            costs = costsQueue.pop();
            int currentCost = costs.getValueAtPosition(curPos);
            if(currentCost > 10000) {
                throw new RuntimeException("This has ran for way too long (probably) so stop for now");
            }

            //If we have reached the end, mark it
            if(curPos.equals(end)) {
                allDistances.add(currentCost);
            }

            //Find the adjacent points
            List<Point> nextPosList = input.getDirectlyAdjacentPoints(curPos);
            for(Point nextPoint : nextPosList) {
                //If we haven't visited this then we can try visiting it
                if(costs.getValueAtPosition(nextPoint) == -1) {
                    char nextPosChar = input.getValueAtPosition(nextPoint);
                    if(nextPosChar == '.') {
                        //For each point we move onto it and set up the new state and cost
                        pointsToExplode.push(nextPoint);
                        Discrete2DPositionGrid<Integer> newGrid = costs.clone();
                        newGrid.setValueAtPosition(nextPoint, currentCost+1);
                        costsQueue.push(newGrid);

                    //If the next step is a step, then we move two rather than just one so we have to do additional cost checks
                    }else if(nextPosChar == 'v') {
                        Point nextNextPoint = new Point(nextPoint.x, nextPoint.y+1);
                        if(costs.getValueAtPosition(nextNextPoint) == -1) {
                            pointsToExplode.push(nextNextPoint);
                            Discrete2DPositionGrid<Integer> newGrid = costs.clone();
                            newGrid.setValueAtPosition(nextPoint, currentCost + 1);
                            newGrid.setValueAtPosition(nextNextPoint, currentCost + 2);
                            costsQueue.push(newGrid);
                        }
                    }else if(nextPosChar == '>') {
                        Point nextNextPoint = new Point(nextPoint.x+1, nextPoint.y);
                        if(costs.getValueAtPosition(nextNextPoint) == -1) {
                            pointsToExplode.push(nextNextPoint);
                            Discrete2DPositionGrid<Integer> newGrid = costs.clone();
                            newGrid.setValueAtPosition(nextPoint, currentCost + 1);
                            newGrid.setValueAtPosition(nextNextPoint, currentCost + 2);
                            costsQueue.push(newGrid);
                        }
                    }else if(nextPosChar == '<') {
                        Point nextNextPoint = new Point(nextPoint.x-1, nextPoint.y);
                        if(costs.getValueAtPosition(nextNextPoint) == -1) {
                            pointsToExplode.push(nextNextPoint);
                            Discrete2DPositionGrid<Integer> newGrid = costs.clone();
                            newGrid.setValueAtPosition(nextPoint, currentCost + 1);
                            newGrid.setValueAtPosition(nextNextPoint, currentCost + 2);
                            costsQueue.push(newGrid);
                        }
                    }else if(nextPosChar == '^') {
                        Point nextNextPoint = new Point(nextPoint.x, nextPoint.y-1);
                        if(costs.getValueAtPosition(nextNextPoint) == -1) {
                            pointsToExplode.push(nextNextPoint);
                            Discrete2DPositionGrid<Integer> newGrid = costs.clone();
                            newGrid.setValueAtPosition(nextPoint, currentCost + 1);
                            newGrid.setValueAtPosition(nextNextPoint, currentCost + 2);
                            costsQueue.push(newGrid);
                        }
                    }
                }
            }
        }

        return ListHelpers.findMax(allDistances);
    }

    /**
     * Attempts to hike around snow island from start to finish taking the most scenic route
     * This time we can go up and down steps!
     * @param input Map of snow island
     * @return Longest hike we can go on
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        input = input.clone();

        //Replace all the steps with standard points
        for(Point p : input.getPositionsOfValue('v')) {
            input.setValueAtPosition(p, '.');
        }
        for(Point p : input.getPositionsOfValue('<')) {
            input.setValueAtPosition(p, '.');
        }
        for(Point p : input.getPositionsOfValue('>')) {
            input.setValueAtPosition(p, '.');
        }
        for(Point p : input.getPositionsOfValue('^')) {
            input.setValueAtPosition(p, '.');
        }

        //Work out start or end
        Point start = null;
        Point end = null;
        for(int x=0; x <= input.getMaxX(); x++) {
            if(input.getValueAtPosition(x, 0) == '.') {
                start = new Point(x, 0);
            }
            if(input.getValueAtPosition(x, input.getMaxY()) == '.') {
                end = new Point(x, input.getMaxY());
            }
        }
        if(start == null || end == null) {
            throw new RuntimeException("Start or end is null for some reason");
        }

        //Create a mapping of point to various links we can move through
        Map<Point, Set<Link>> links = new HashMap<>();
        List<Point> allPathLocations = input.getPositionsOfValue('.');
        for(Point singularPoint : allPathLocations) {
            links.put(singularPoint, new HashSet<>());
        }
        for(Point singularPoint : allPathLocations) {
            List<Point> adjacentPoints = input.getDirectlyAdjacentPoints(singularPoint, '.');
            for(Point p : adjacentPoints) {
                Link l = new Link(singularPoint, p, 1);
                links.get(singularPoint).add(l);
                links.get(p).add(l);
            }
        }

        //Now we have all the links we are going to "compress" them by removing nodes with only one in and one out
        boolean mergedSomething = true;
        while(mergedSomething) {
            mergedSomething = false;

            //Go through each point
            for(Map.Entry<Point, Set<Link>> e : links.entrySet()) {
                Queue<Link> pointSet = new LinkedList<>(e.getValue());
                //If there are only two links then we can remove it and merge in other links
                if(pointSet.size() == 2) {
                    Link firstLink = pointSet.poll();
                    Link secondLink = pointSet.poll();

                    if(firstLink == null || secondLink == null) {
                        throw new RuntimeException("Error there should always be two items from this");
                    }

                    //Create a merged link from the two other links removing this main node
                    Link newLink = firstLink.merge(secondLink);
                    //Remove this point entirely from the map
                    links.remove(e.getKey());
                    //Remove the two links on the "other" parts
                    links.get(firstLink.getOtherPoint(e.getKey())).remove(firstLink);
                    links.get(secondLink.getOtherPoint(e.getKey())).remove(secondLink);
                    //Add the new link in
                    links.get(firstLink.getOtherPoint(e.getKey())).add(newLink);
                    links.get(secondLink.getOtherPoint(e.getKey())).add(newLink);

                    mergedSomething = true;
                    break;
                }
            }
        }

        //Now we have a minimised path just so we don't have too much to handle
        //This is still a NP-hard problem but we can solve it relatively fast since its "small enough"
        Stack<Day23State> statesToCheck = new Stack<>();
        Set<Point> startSet = new HashSet<>();
        startSet.add(start);
        statesToCheck.add(new Day23State(0, start, startSet));
        List<Integer> maxDistances = new ArrayList<>();
        while(!statesToCheck.isEmpty()) {
            Day23State stateToCheck = statesToCheck.pop();
            //If we have reached the end, log it
            if(stateToCheck.getCur().equals(end)) {
                maxDistances.add(stateToCheck.getCost());
                continue;
            }

            //Otherwise find out what links we can travel on
            Set<Link> moves = links.get(stateToCheck.getCur());
            for(Link link : moves) {
                Point otherState = link.getOtherPoint(stateToCheck.getCur());
                if(!stateToCheck.getVisited().contains(otherState)) {
                    //If we haven't visited the end, then lets set up the new state
                    Set<Point> newVisited = new HashSet<>(stateToCheck.getVisited());
                    newVisited.add(otherState);
                    statesToCheck.add(new Day23State(stateToCheck.getCost() + link.getCost(), otherState, newVisited));
                }
            }
        }

        return ListHelpers.findMax(maxDistances);
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 23);

        Day23 d = new Day23();
        long partOne = d.solvePartOne(input);
        System.out.println("The longest path only going down steps " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The longest path going up and down steps is " + partTwo);
    }
}


