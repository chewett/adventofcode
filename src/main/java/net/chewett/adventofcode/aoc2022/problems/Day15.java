package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 15: Beacon Exclusion Zone ---
 * You feel the ground rumble again as the distress signal leads you to a large network of subterranean tunnels. You
 * don't have time to search them all, but you don't need to: your pack contains a set of deployable sensors that you
 * imagine were originally built to locate lost Elves.
 *
 * The sensors aren't very powerful, but that's okay; your handheld device indicates that you're close enough to the
 * source of the distress signal to use them. You pull the emergency sensor system out of your pack, hit the big button
 * on top, and the sensors zoom off down the tunnels.
 *
 * Once a sensor finds a spot it thinks will give it a good reading, it attaches itself to a hard surface and begins
 * monitoring for the nearest signal source beacon. Sensors and beacons always exist at integer coordinates. Each
 * sensor knows its own position and can determine the position of a beacon precisely; however, sensors can only lock
 * on to the one beacon closest to the sensor as measured by the Manhattan distance. (There is never a tie where two
 * beacons are the same distance to a sensor.)
 *
 * It doesn't take long for the sensors to report back their positions and closest beacons (your puzzle input). For
 * example:
 *
 * Sensor at x=2, y=18: closest beacon is at x=-2, y=15
 * Sensor at x=9, y=16: closest beacon is at x=10, y=16
 * Sensor at x=13, y=2: closest beacon is at x=15, y=3
 * Sensor at x=12, y=14: closest beacon is at x=10, y=16
 * Sensor at x=10, y=20: closest beacon is at x=10, y=16
 * Sensor at x=14, y=17: closest beacon is at x=10, y=16
 * Sensor at x=8, y=7: closest beacon is at x=2, y=10
 * Sensor at x=2, y=0: closest beacon is at x=2, y=10
 * Sensor at x=0, y=11: closest beacon is at x=2, y=10
 * Sensor at x=20, y=14: closest beacon is at x=25, y=17
 * Sensor at x=17, y=20: closest beacon is at x=21, y=22
 * Sensor at x=16, y=7: closest beacon is at x=15, y=3
 * Sensor at x=14, y=3: closest beacon is at x=15, y=3
 * Sensor at x=20, y=1: closest beacon is at x=15, y=3
 * So, consider the sensor at 2,18; the closest beacon to it is at -2,15. For the sensor at 9,16, the closest beacon
 * to it is at 10,16.
 *
 * Drawing sensors as S and beacons as B, the above arrangement of sensors and beacons looks like this:
 *
 *                1    1    2    2
 *      0    5    0    5    0    5
 *  0 ....S.......................
 *  1 ......................S.....
 *  2 ...............S............
 *  3 ................SB..........
 *  4 ............................
 *  5 ............................
 *  6 ............................
 *  7 ..........S.......S.........
 *  8 ............................
 *  9 ............................
 * 10 ....B.......................
 * 11 ..S.........................
 * 12 ............................
 * 13 ............................
 * 14 ..............S.......S.....
 * 15 B...........................
 * 16 ...........SB...............
 * 17 ................S..........B
 * 18 ....S.......................
 * 19 ............................
 * 20 ............S......S........
 * 21 ............................
 * 22 .......................B....
 * This isn't necessarily a comprehensive map of all beacons in the area, though. Because each sensor only identifies
 * #its closest beacon, if a sensor detects a beacon, you know there are no other beacons that close or closer to that
 * sensor. There could still be beacons that just happen to not be the closest beacon to any sensor. Consider the
 * sensor at 8,7:
 *
 *                1    1    2    2
 *      0    5    0    5    0    5
 * -2 ..........#.................
 * -1 .........###................
 *  0 ....S...#####...............
 *  1 .......#######........S.....
 *  2 ......#########S............
 *  3 .....###########SB..........
 *  4 ....#############...........
 *  5 ...###############..........
 *  6 ..#################.........
 *  7 .#########S#######S#........
 *  8 ..#################.........
 *  9 ...###############..........
 * 10 ....B############...........
 * 11 ..S..###########............
 * 12 ......#########.............
 * 13 .......#######..............
 * 14 ........#####.S.......S.....
 * 15 B........###................
 * 16 ..........#SB...............
 * 17 ................S..........B
 * 18 ....S.......................
 * 19 ............................
 * 20 ............S......S........
 * 21 ............................
 * 22 .......................B....
 * This sensor's closest beacon is at 2,10, and so you know there are no beacons that close or closer (in any positions
 * marked #).
 *
 * None of the detected beacons seem to be producing the distress signal, so you'll need to work out where the distress
 * beacon is by working out where it isn't. For now, keep things simple by counting the positions where a beacon cannot
 * possibly be along just a single row.
 *
 * So, suppose you have an arrangement of beacons and sensors like in the example above and, just in the row where y=10,
 * you'd like to count the number of positions a beacon cannot possibly exist. The coverage from all sensors near that
 * row looks like this:
 *
 *                  1    1    2    2
 *        0    5    0    5    0    5
 *  9 ...#########################...
 * 10 ..####B######################..
 * 11 .###S#############.###########.
 * In this example, in the row where y=10, there are 26 positions where a beacon cannot be present.
 *
 * Consult the report from the sensors you just deployed. In the row where y=2000000, how many positions cannot contain
 * a beacon?
 *
 * --- Part Two ---
 * Your handheld device indicates that the distress signal is coming from a beacon nearby. The distress beacon is not
 * detected by any sensor, but the distress beacon must have x and y coordinates each no lower than 0 and no larger
 * than 4000000.
 *
 * To isolate the distress beacon's signal, you need to determine its tuning frequency, which can be found by
 * multiplying its x coordinate by 4000000 and then adding its y coordinate.
 *
 * In the example above, the search space is smaller: instead, the x and y coordinates can each be at most 20. With
 * this reduced search area, there is only a single position that could have a beacon: x=14, y=11. The tuning frequency
 * for this distress beacon is 56000011.
 *
 * Find the only possible position for the distress beacon. What is its tuning frequency?
 */
public class Day15 {

    /**
     * Simple manhattan distance function
     * @param p1 Point one to compare against
     * @param p2 Point two to compare against
     * @return Manhattan distance between the points
     */
    public int getManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }


    /**
     * Attempts to find how many spots along the line Y value provided which cannot hide a beacon
     * @param input List of sensors and the beacons they find
     * @param yValueToCheck Y value to check for blind spots
     * @return Number spots which cannot hide a beacon
     */
    public long solvePartOne(List<String> input, int yValueToCheck) {
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');

        for(String str : input) {
            String[] parts = str.split("( x=|, y=|: )");
            Point sensor = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            Point beacon = new Point(Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
            grid.setValueAtPosition(sensor, 'S');
            grid.setValueAtPosition(beacon, 'B');

            int distance = this.getManhattanDistance(sensor, beacon);
            //Work out how far left and right this sensor would live on
            int distanceLeftRight = distance - Math.abs(yValueToCheck - sensor.y);

            for(int xToSplat = sensor.x - distanceLeftRight; xToSplat <= sensor.x + distanceLeftRight; xToSplat++) {
                if(grid.getValueAtPosition(xToSplat, yValueToCheck) == '.') {
                    //Mark each point on the grid where it can be hiding
                    grid.setValueAtPosition(xToSplat, yValueToCheck, '#');
                }
            }
        }

        //Then count the numbers!
        return grid.countInstancesOfValue('#');
    }

    /**
     * Work out the distress beacons tuning frequency
     * @param input List of sensors and the beacons they find
     * @param maxVal Maximum X and Y signal the beacon lives on
     * @return The distress beacon tuning frequency
     */
    public long solvePartTwo(List<String> input, int maxVal) {
        //Store a list of points that could possibly hide the beacon
        Set<Point> points = new HashSet<>();
        //And where the sensors are and how far their search distance is
        List<Point> allSensors = new ArrayList<>();
        List<Integer> sensorDistances = new ArrayList<>();

        for(String str : input) {
            String[] parts = str.split("( x=|, y=|: )");
            Point sensor = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            Point beacon = new Point(Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
            allSensors.add(sensor);
            int beaconDistance = this.getManhattanDistance(sensor, beacon);
            sensorDistances.add(beaconDistance);

            //Find the manhattan distance to all the hiding spots
            int distanceToHidingSpot = beaconDistance + 1;

            //Now find and store all the possible points that could be hiding it
            for(int yToSplat = sensor.y - distanceToHidingSpot; yToSplat <= sensor.y + distanceToHidingSpot; yToSplat++) {
                int xDistanceToMove = distanceToHidingSpot - Math.abs(yToSplat - sensor.y);
                points.add(new Point(sensor.x - xDistanceToMove, yToSplat));
                points.add(new Point(sensor.x + xDistanceToMove, yToSplat));
            }
        }

        //Now just iterate over all those points and check to see if any of them have no covering beacons
        for(Point p : points) {
            if(p.x < 0 || p.y < 0 || p.x >= maxVal || p.y >= maxVal) {
                continue;
            }

            boolean beaconCoversThis = false;
            for(int beaconIndex = 0; beaconIndex < allSensors.size(); beaconIndex++) {
                Point beacon = allSensors.get(beaconIndex);
                if(this.getManhattanDistance(p, beacon) <= sensorDistances.get(beaconIndex)) {
                    beaconCoversThis = true;
                }
            }

            //No beacon covers this so its clearly the singular point!
            if(!beaconCoversThis) {
                return (p.x * 4000000L) + p.y;
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 15);

        Day15 d = new Day15();
        long partOne = d.solvePartOne(input, 2000000);
        System.out.println("The number of spots that can hide a beacon at Y=2000000 is " + partOne);

        long partTwo = d.solvePartTwo(input, 4000000);
        System.out.println("The tuning frequency for the distress beacon is "+ partTwo);

    }


}
