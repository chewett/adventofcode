package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.aoc2019.MapArea;
import net.chewett.adventofcode.aoc2019.OxygenRefillingModel;
import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;

/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/15
 * Go have a try yourself!
 *
 */
public class Day15 {

    public long solvePartOne(String input) {
        IntcodeComputer icc = IntcodeComputer.getFullyConfiguredComputer();
        Intcode ic = new Intcode(input);
        icc.initIntcode(ic);

        MapArea ma = new MapArea();
        ma.setMapData(0, 0, '.');

        //Init a queue of points to visit
        Queue<Point> pointsToVisitNext = new LinkedList<>(ma.findAdjacentUnexploredPoints(0, 0));
        //Track current position
        int x = 0;
        int y = 0;

        Point pointToVisit = pointsToVisitNext.remove();
        Point oxygenPoint = new Point(1,1);
        //While we have points to visit, keep going
        while(pointToVisit != null) {
            while(pointToVisit.x == x && pointToVisit.y == y) {
                pointToVisit = pointsToVisitNext.remove();
            }

            int direction = ma.getDirectionToMoveToPoint(x, y, (int)pointToVisit.getX(), (int)pointToVisit.getY());
            Point newPointImAimingFor = ma.getPointInDirection(new Point(x, y), direction);
            icc.addToInput(direction);
            icc.runIntcode();
            long moveResult = icc.getOutput();
            //Ooops we hit a wall and didn't move
            if(moveResult == 0) {
                ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '#');
                //If we were moving onto the point we are trying to visit, then we stop and find a new point to visit
                //Otherwise we need to keep moving until we get to the point we are trying to visit
                if(newPointImAimingFor.x == pointToVisit.x && newPointImAimingFor.y == pointToVisit.y) {
                    if(pointsToVisitNext.size() > 0) {
                        pointToVisit = pointsToVisitNext.remove();
                    }else{
                        pointToVisit = null;
                    }
                }
            //We moved to the new location
            }else if(moveResult == 1) {
                x = newPointImAimingFor.x;
                y = newPointImAimingFor.y;

                ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '.');
                //If we were moving onto the point we are trying to visit, then we stop and find a new point to visit
                //Otherwise we need to keep moving until we get to the point we are trying to visit
                if(newPointImAimingFor.equals(pointToVisit)) {
                    pointsToVisitNext.addAll(ma.findAdjacentUnexploredPoints(x, y));
                    pointToVisit = pointsToVisitNext.remove();
                    while(pointToVisit.x == x && pointToVisit.y == y) {
                        if(pointsToVisitNext.size() > 0) {
                            pointToVisit = pointsToVisitNext.remove();
                        }else{
                            pointToVisit = null;
                        }
                    }
                }
            //We found the oxygen point
            }else if(moveResult == 2) {
                x = newPointImAimingFor.x;
                y = newPointImAimingFor.y;
                ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, 'O');
                oxygenPoint = newPointImAimingFor;
            }
        }

        return ma.calculateCostBetweenPoints(0, 0, oxygenPoint.x, oxygenPoint.y);
    }

    public long solvePartTwo(String input) {
        IntcodeComputer icc = IntcodeComputer.getFullyConfiguredComputer();
        Intcode ic = new Intcode(input);
        icc.initIntcode(ic);

        MapArea ma = new MapArea();
        ma.setMapData(0, 0, '.');

        //Init a queue of points to visit
        Queue<Point> pointsToVisitNext = new LinkedList<>(ma.findAdjacentUnexploredPoints(0, 0));
        //Track current position
        int x = 0;
        int y = 0;

        Point pointToVisit = pointsToVisitNext.remove();
        //While we have points to visit, keep going
        while(pointToVisit != null) {
            while(pointToVisit.x == x && pointToVisit.y == y) {
                pointToVisit = pointsToVisitNext.remove();
            }

            int direction = ma.getDirectionToMoveToPoint(x, y, (int)pointToVisit.getX(), (int)pointToVisit.getY());
            Point newPointImAimingFor = ma.getPointInDirection(new Point(x, y), direction);
            icc.addToInput(direction);
            icc.runIntcode();
            long moveResult = icc.getOutput();
            //Ooops we hit a wall and didn't move
            if(moveResult == 0) {
                ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '#');
                //If we were moving onto the point we are trying to visit, then we stop and find a new point to visit
                //Otherwise we need to keep moving until we get to the point we are trying to visit
                if(newPointImAimingFor.x == pointToVisit.x && newPointImAimingFor.y == pointToVisit.y) {
                    if(pointsToVisitNext.size() > 0) {
                        pointToVisit = pointsToVisitNext.remove();
                    }else{
                        pointToVisit = null;
                    }
                }
            //We moved to the new location
            }else if(moveResult == 1) {
                x = newPointImAimingFor.x;
                y = newPointImAimingFor.y;

                ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '.');
                //If we were moving onto the point we are trying to visit, then we stop and find a new point to visit
                //Otherwise we need to keep moving until we get to the point we are trying to visit
                if(newPointImAimingFor.equals(pointToVisit)) {
                    pointsToVisitNext.addAll(ma.findAdjacentUnexploredPoints(x, y));
                    pointToVisit = pointsToVisitNext.remove();
                    while(pointToVisit.x == x && pointToVisit.y == y) {
                        if(pointsToVisitNext.size() > 0) {
                            pointToVisit = pointsToVisitNext.remove();
                        }else{
                            pointToVisit = null;
                        }
                    }
                }
            //We found the oxygen point
            }else if(moveResult == 2) {
                x = newPointImAimingFor.x;
                y = newPointImAimingFor.y;
                ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, 'O');
            }
        }

        OxygenRefillingModel orm = new OxygenRefillingModel(ma);
        return orm.getMinutesToFillAreaWithOxygen();
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2019, 15);

        Day15 d = new Day15();
        long partOne = d.solvePartOne(input);
        System.out.println("Fewest moves to move from the start to the repair location is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("Minutes to fill the area with oxygen: " + partTwo);
    }
}


