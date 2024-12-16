package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 14: Restroom Redoubt ---
 * One of The Historians needs to use the bathroom; fortunately, you know there's a bathroom near an unvisited location
 * on their list, and so you're all quickly teleported directly to the lobby of Easter Bunny Headquarters.
 *
 * Unfortunately, EBHQ seems to have "improved" bathroom security again after your last visit. The area outside the
 * bathroom is swarming with robots!
 *
 * To get The Historian safely to the bathroom, you'll need a way to predict where the robots will be in the future.
 * Fortunately, they all seem to be moving on the tile floor in predictable straight lines.
 *
 * You make a list (your puzzle input) of all of the robots' current positions (p) and velocities (v), one robot per
 * line. For example:
 *
 * p=0,4 v=3,-3
 * p=6,3 v=-1,-3
 * p=10,3 v=-1,2
 * p=2,0 v=2,-1
 * p=0,0 v=1,3
 * p=3,0 v=-2,-2
 * p=7,6 v=-1,-3
 * p=3,0 v=-1,-2
 * p=9,3 v=2,3
 * p=7,3 v=-1,2
 * p=2,4 v=2,-3
 * p=9,5 v=-3,-3
 * Each robot's position is given as p=x,y where x represents the number of tiles the robot is from the left wall and y
 * represents the number of tiles from the top wall (when viewed from above). So, a position of p=0,0 means the robot
 * is all the way in the top-left corner.
 *
 * Each robot's velocity is given as v=x,y where x and y are given in tiles per second. Positive x means the robot is
 * moving to the right, and positive y means the robot is moving down. So, a velocity of v=1,-2 means that each second,
 * the robot moves 1 tile to the right and 2 tiles up.
 *
 * The robots outside the actual bathroom are in a space which is 101 tiles wide and 103 tiles tall (when viewed from
 * above). However, in this example, the robots are in a space which is only 11 tiles wide and 7 tiles tall.
 *
 * The robots are good at navigating over/under each other (due to a combination of springs, extendable legs, and
 * quadcopters), so they can share the same tile and don't interact with each other. Visually, the number of robots on
 * each tile in this example looks like this:
 *
 * 1.12.......
 * ...........
 * ...........
 * ......11.11
 * 1.1........
 * .........1.
 * .......1...
 * These robots have a unique feature for maximum bathroom security: they can teleport. When a robot would run into
 * an edge of the space they're in, they instead teleport to the other side, effectively wrapping around the edges.
 * Here is what robot p=2,4 v=2,-3 does for the first few seconds:
 *
 * Initial state:
 * ...........
 * ...........
 * ...........
 * ...........
 * ..1........
 * ...........
 * ...........
 *
 * After 1 second:
 * ...........
 * ....1......
 * ...........
 * ...........
 * ...........
 * ...........
 * ...........
 *
 * After 2 seconds:
 * ...........
 * ...........
 * ...........
 * ...........
 * ...........
 * ......1....
 * ...........
 *
 * After 3 seconds:
 * ...........
 * ...........
 * ........1..
 * ...........
 * ...........
 * ...........
 * ...........
 *
 * After 4 seconds:
 * ...........
 * ...........
 * ...........
 * ...........
 * ...........
 * ...........
 * ..........1
 *
 * After 5 seconds:
 * ...........
 * ...........
 * ...........
 * .1.........
 * ...........
 * ...........
 * ...........
 * The Historian can't wait much longer, so you don't have to simulate the robots for very long. Where will the robots
 * be after 100 seconds?
 *
 * In the above example, the number of robots on each tile after 100 seconds has elapsed looks like this:
 *
 * ......2..1.
 * ...........
 * 1..........
 * .11........
 * .....1.....
 * ...12......
 * .1....1....
 * To determine the safest area, count the number of robots in each quadrant after 100 seconds. Robots that are exactly
 * in the middle (horizontally or vertically) don't count as being in any quadrant, so the only relevant robots are:
 *
 * ..... 2..1.
 * ..... .....
 * 1.... .....
 *
 * ..... .....
 * ...12 .....
 * .1... 1....
 * In this example, the quadrants contain 1, 3, 4, and 1 robot. Multiplying these together gives a total safety factor
 * of 12.
 *
 * Predict the motion of the robots in your list within a space which is 101 tiles wide and 103 tiles tall. What will
 * the safety factor be after exactly 100 seconds have elapsed?
 *
 * Your puzzle answer was 224969976.
 *
 * --- Part Two ---
 * During the bathroom break, someone notices that these robots seem awfully similar to ones built and used at the
 * North Pole. If they're the same type of robots, they should have a hard-coded Easter egg: very rarely, most of the
 * robots should arrange themselves into a picture of a Christmas tree.
 *
 * What is the fewest number of seconds that must elapse for the robots to display the Easter egg?
 */
public class Day14 {


    /**
     * Simulate the robots moving and then work out where they are
     * @param input Robot locations and velocities
     * @return Safety factor
     */
    public long solvePartOne(List<String> input) {
        List<Pair<Point>> robots = new ArrayList<>();

        for(String line : input) {
            String[] part = line.split("[=, ]");
            robots.add(new Pair<Point>(
                new Point(Integer.parseInt(part[1]), Integer.parseInt(part[2])),
                new Point(Integer.parseInt(part[4]), Integer.parseInt(part[5]))
            ));
        }

        int WIDTH = 11; // 11 was the example
        WIDTH = 101;
        int HEIGHT = 7; // 7 was the example
        HEIGHT = 103;

        for(int i = 0; i < 100; i++) {
            for(Pair<Point> robot : robots) {
                robot.a.x += robot.b.x;
                robot.a.y += robot.b.y;

                //Make sure it's in our area properly
                //Use while loops since if the robot moves loads it could go massively out...
                while(robot.a.x > WIDTH-1) {
                    robot.a.x -= WIDTH;
                }
                while(robot.a.x < 0) {
                    robot.a.x += WIDTH;
                }
                while(robot.a.y > HEIGHT-1) {
                    robot.a.y -= HEIGHT;
                }
                while(robot.a.y < 0) {
                    robot.a.y += HEIGHT;
                }
            }
        }

        int quadOne = 0; int quadTwo = 0; int quadThree = 0; int quadFour = 0;

        //Work out the middle point
        //Since we are zero-indexed this is actually the middle index and you don't need to add one
        int WIDTH_MIDDLE = (WIDTH / 2);
        int HEIGHT_MIDDLE = (HEIGHT / 2);

        for(Pair<Point> robot : robots) {
            if(robot.a.x < WIDTH_MIDDLE && robot.a.y < HEIGHT_MIDDLE ) {
                quadOne++;
            }else if(robot.a.x > WIDTH_MIDDLE && robot.a.y < HEIGHT_MIDDLE ) {
                quadTwo++;
            }else if(robot.a.x < WIDTH_MIDDLE && robot.a.y > HEIGHT_MIDDLE) {
                quadThree++;
            }else if(robot.a.x > WIDTH_MIDDLE && robot.a.y > HEIGHT_MIDDLE) {
                quadFour++;
            }
        }

        return quadOne * quadTwo * quadThree * quadFour;
    }

    /**
     * Work out at what second the Christmas tree appears
     *
     * Now I'm not entirely sure how others solved this. Originally I tried stopping when each quadrant had symmetrical
     * values, then I got the right answer by stopping when no robots overlap.
     *
     * But this feels like a quirk and not a "good" way to answer it? Without knowing what the Christmas Tree looks
     * like it is tricky to know when to stop.
     *
     * After seeing it, and seeing there is a border maybe I could look for robots in a line?
     *
     * Seems a quirky one today...
     *
     * @param input Robot locations and velocities
     * @return The second a Christmas tree appears
     */
    public long solvePartTwo(List<String> input) {
        List<Pair<Point>> robots = new ArrayList<>();

        for(String line : input) {
            String[] part = line.split("[=, ]");
            robots.add(new Pair<Point>(
                    new Point(Integer.parseInt(part[1]), Integer.parseInt(part[2])),
                    new Point(Integer.parseInt(part[4]), Integer.parseInt(part[5]))
            ));
        }

        int WIDTH = 11;
        WIDTH = 101;
        int HEIGHT = 7;
        HEIGHT = 103;

        for(int i = 0; i < 10000; i++) {
            for(Pair<Point> robot : robots) {
                robot.a.x += robot.b.x;
                robot.a.y += robot.b.y;

                //Make sure it's in our area properly
                while(robot.a.x > WIDTH-1) {
                    robot.a.x -= WIDTH;
                }
                while(robot.a.x < 0) {
                    robot.a.x += WIDTH;
                }
                while(robot.a.y > HEIGHT-1) {
                    robot.a.y -= HEIGHT;
                }
                while(robot.a.y < 0) {
                    robot.a.y += HEIGHT;
                }
            }

            Set<Point> robotsInSet = new HashSet<>();
            boolean foundOverlap = false;

            for(Pair<Point> robot : robots) {
                if(robotsInSet.contains(robot.a)) {
                    foundOverlap = true;
                }
                robotsInSet.add(robot.a);
            }

            if(!foundOverlap) {
                return i+1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 14);

        Day14 d = new Day14();
        long partOne = d.solvePartOne(input);
        System.out.println("The safety factor is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The seconds at which the Christmas Tree appears is " + partTwo);
    }
}


