package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.List;


/**
 * --- Day 12: Rain Risk ---
 * Your ferry made decent progress toward the island, but the storm came in faster than anyone expected. The ferry needs
 * to take evasive actions!
 *
 * Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a route directly to
 * safety, it produced extremely circuitous instructions. When the captain uses the PA system to ask if anyone can help,
 * you quickly volunteer.
 *
 * The navigation instructions (your puzzle input) consists of a sequence of single-character actions paired with
 * integer input values. After staring at them for a few minutes, you work out what they probably mean:
 *
 * Action N means to move north by the given value.
 * Action S means to move south by the given value.
 * Action E means to move east by the given value.
 * Action W means to move west by the given value.
 * Action L means to turn left the given number of degrees.
 * Action R means to turn right the given number of degrees.
 * Action F means to move forward by the given value in the direction the ship is currently facing.
 * The ship starts by facing east. Only the L and R actions change the direction the ship is facing. (That is, if the
 * ship is facing east and the next instruction is N10, the ship would move north 10 units, but would still move east
 * if the following action were F.)
 *
 * For example:
 *
 * F10
 * N3
 * F7
 * R90
 * F11
 * These instructions would be handled as follows:
 *
 * F10 would move the ship 10 units east (because the ship starts by facing east) to east 10, north 0.
 * N3 would move the ship 3 units north to east 10, north 3.
 * F7 would move the ship another 7 units east (because the ship is still facing east) to east 17, north 3.
 * R90 would cause the ship to turn right by 90 degrees and face south; it remains at east 17, north 3.
 * F11 would move the ship 11 units south to east 17, south 8.
 * At the end of these instructions, the ship's Manhattan distance (sum of the absolute values of its east/west position
 * and its north/south position) from its starting position is 17 + 8 = 25.
 *
 * Figure out where the navigation instructions lead. What is the Manhattan distance between that location and the
 * ship's starting position?
 *
 * --- Part Two ---
 * Before you can give the destination to the captain, you realize that the actual action meanings were printed on the
 * back of the instructions the whole time.
 *
 * Almost all of the actions indicate how to move a waypoint which is relative to the ship's position:
 *
 * Action N means to move the waypoint north by the given value.
 * Action S means to move the waypoint south by the given value.
 * Action E means to move the waypoint east by the given value.
 * Action W means to move the waypoint west by the given value.
 * Action L means to rotate the waypoint around the ship left (counter-clockwise) the given number of degrees.
 * Action R means to rotate the waypoint around the ship right (clockwise) the given number of degrees.
 * Action F means to move forward to the waypoint a number of times equal to the given value.
 * The waypoint starts 10 units east and 1 unit north relative to the ship. The waypoint is relative to the ship; that
 * is, if the ship moves, the waypoint moves with it.
 *
 * For example, using the same instructions as above:
 *
 * F10 moves the ship to the waypoint 10 times (a total of 100 units east and 10 units north), leaving the ship at east
 * 100, north 10. The waypoint stays 10 units east and 1 unit north of the ship.
 * N3 moves the waypoint 3 units north to 10 units east and 4 units north of the ship. The ship remains at east 100,
 * north 10.
 * F7 moves the ship to the waypoint 7 times (a total of 70 units east and 28 units north), leaving the ship at east
 * 170, north 38. The waypoint stays 10 units east and 4 units north of the ship.
 * R90 rotates the waypoint around the ship clockwise 90 degrees, moving it to 4 units east and 10 units south of the
 * ship. The ship remains at east 170, north 38.
 * F11 moves the ship to the waypoint 11 times (a total of 44 units east and 110 units south), leaving the ship at east
 * 214, south 72. The waypoint stays 4 units east and 10 units south of the ship.
 * After these operations, the ship's Manhattan distance from its starting position is 214 + 72 = 286.
 *
 * Figure out where the navigation instructions actually lead. What is the Manhattan distance between that location and
 * the ship's starting position?
 */
public class Day12 {

    /**
     * Determines the Manhattan distance from the ships original starting location by following a set of instructions
     * @param instructions The set of instructions to follow
     * @return The manhattan distance from the original starting position
     */
    public int solvePartOne(List<String> instructions) {
        int curAngle = 90;
        int currentXPos = 0;
        int currentYPos = 0;

        for(String instruction : instructions) {
            char instructionType = instruction.charAt(0);
            int instructionMovement = Integer.parseInt(instruction.substring(1));

            //If the instruction is forward, parse it into a NESW instruction direction
            if(instructionType == 'F') {
                if(curAngle == 0) { // North
                    instructionType = 'N';
                }else if(curAngle == 90) { // East
                    instructionType = 'E';
                }else if(curAngle == 180) { // South
                    instructionType = 'S';
                }else if(curAngle == 270) { // West
                    instructionType = 'W';
                }else{
                    throw new RuntimeException("Unknown angle " + curAngle);
                }
            }

            //Handle NSEW instructions to move the ship in that direction by the given value
            if(instructionType == 'N') {
                currentYPos -= instructionMovement;
            }else if(instructionType == 'S') {
                currentYPos += instructionMovement;
            }else if(instructionType == 'E') {
                currentXPos += instructionMovement;
            }else if(instructionType == 'W') {
                currentXPos -= instructionMovement;

            //If the instruction is left/right then rotate the angle and ensure it always ends within a 0 - 359 heading
            }else if(instructionType == 'L') {
                curAngle -= instructionMovement;
                while(curAngle < 0) {
                    curAngle += 360;
                }
            }else if(instructionType == 'R') {
                curAngle += instructionMovement;
                while(curAngle >= 360) {
                    curAngle -= 360;
                }
            }else {
                throw new RuntimeException("Unknown command " + instruction);
            }

        }

        return Math.abs(currentXPos) + Math.abs(currentYPos);
    }

    /**
     * Determines the Manhattan distance from the ships original starting location by following a set of instructions
     * and using the net method to interpret the directions
     * @param instructions The set of instructions to follow
     * @return The manhattan distance from the original starting position
     */
    public long solvePartTwo(List<String> instructions) {
        int currentXPos = 0;
        int currentYPos = 0;
        //Here we are also storing the waypoint as this is required for the new instruction handling
        int waypointXPos = 10;
        int waypointYPos = -1;

        for(String instruction : instructions) {
            char instructionType = instruction.charAt(0);
            int instructionMovement = Integer.parseInt(instruction.substring(1));

            //Here instead of modifying the ships actual position we tweak the waypoint position
            if(instructionType == 'N') {
                waypointYPos -= instructionMovement;
            }else if(instructionType == 'S') {
                waypointYPos += instructionMovement;
            }else if(instructionType == 'E') {
                waypointXPos += instructionMovement;
            }else if(instructionType == 'W') {
                waypointXPos -= instructionMovement;

            //left/right also now modifies the waypoint position
            }else if(instructionType == 'L') {
                if(instructionMovement == 180) {
                    waypointXPos = -waypointXPos;
                    waypointYPos = -waypointYPos;
                }else if(instructionMovement == 90) {
                    int tmpY = waypointYPos;
                    waypointYPos = -waypointXPos;
                    waypointXPos = tmpY;
                }else if(instructionMovement == 270) {
                    int tmpY = waypointYPos;
                    waypointYPos = waypointXPos;
                    waypointXPos = -tmpY;
                }
            }else if(instructionType == 'R') {
                if(instructionMovement == 180) {
                    waypointXPos = -waypointXPos;
                    waypointYPos = -waypointYPos;
                }else if(instructionMovement == 270) {
                    int tmpY = waypointYPos;
                    waypointYPos = -waypointXPos;
                    waypointXPos = tmpY;
                }else if(instructionMovement == 90) {
                    int tmpY = waypointYPos;
                    waypointYPos = waypointXPos;
                    waypointXPos = -tmpY;
                }

            //Forward is somewhat easier as its a multiplier of the waypoint position so its a quick set of maths
            }else if(instructionType == 'F') {
                currentXPos += (instructionMovement * waypointXPos);
                currentYPos += (instructionMovement * waypointYPos);
            }else {
                throw new RuntimeException("Unknown command " + instruction);
            }

        }

        return Math.abs(currentXPos) + Math.abs(currentYPos);
    }

    public static void main(String[] args) {
        Day12 d = new Day12();
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 12);
        int p1 = d.solvePartOne(instructions);
        System.out.println("The waypoint after the instructions with the first way of interpreting it is " + p1);
        long p2 = d.solvePartTwo(instructions);
        System.out.println("The waypoint after the instructions with the second way of interpreting it is " + p2);

    }
}
