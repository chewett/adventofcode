package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/11
 * Go have a try yourself!
 *
 * --- Day 11: Space Police ---
 * On the way to Jupiter, you're pulled over by the Space Police.
 *
 * "Attention, unmarked spacecraft! You are in violation of Space Law! All spacecraft must have a clearly visible
 * registration identifier! You have 24 hours to comply or be sent to Space Jail!"
 *
 * Not wanting to be sent to Space Jail, you radio back to the Elves on Earth for help. Although it takes almost three
 * hours for their reply signal to reach you, they send instructions for how to power up the emergency hull painting
 * robot and even provide a small Intcode program (your puzzle input) that will cause it to paint your ship
 * appropriately.
 *
 * There's just one problem: you don't have an emergency hull painting robot.
 *
 * You'll need to build a new emergency hull painting robot. The robot needs to be able to move around on the grid of
 * square panels on the side of your ship, detect the color of its current panel, and paint its current panel black or
 * white. (All of the panels are currently black.)
 *
 * The Intcode program will serve as the brain of the robot. The program uses input instructions to access the robot's
 * camera: provide 0 if the robot is over a black panel or 1 if the robot is over a white panel. Then, the program
 * will output two values:
 *
 * First, it will output a value indicating the color to paint the panel the robot is over: 0 means to paint the panel
 * black, and 1 means to paint the panel white.
 * Second, it will output a value indicating the direction the robot should turn: 0 means it should turn left
 * 90 degrees, and 1 means it should turn right 90 degrees.
 * After the robot turns, it should always move forward exactly one panel. The robot starts facing up.
 *
 * The robot will continue running for a while like this and halt when it is finished drawing. Do not restart the
 * Intcode computer inside the robot during this process.
 *
 * For example, suppose the robot is about to start running. Drawing black panels as ., white panels as #, and the
 * robot pointing the direction it is facing (< ^ > v), the initial state and region near the robot looks like this:
 *
 * .....
 * .....
 * ..^..
 * .....
 * .....
 * The panel under the robot (not visible here because a ^ is shown instead) is also black, and so any input
 * instructions at this point should be provided 0. Suppose the robot eventually outputs 1 (paint white) and then
 * 0 (turn left). After taking these actions and moving forward one panel, the region now looks like this:
 *
 * .....
 * .....
 * .<#..
 * .....
 * .....
 * Input instructions should still be provided 0. Next, the robot might output 0 (paint black) and then 0 (turn left):
 *
 * .....
 * .....
 * ..#..
 * .v...
 * .....
 * After more outputs (1,0, 1,0):
 *
 * .....
 * .....
 * ..^..
 * .##..
 * .....
 * The robot is now back where it started, but because it is now on a white panel, input instructions should be
 * provided 1. After several more outputs (0,1, 1,0, 1,0), the area looks like this:
 *
 * .....
 * ..<#.
 * ...#.
 * .##..
 * .....
 * Before you deploy the robot, you should probably have an estimate of the area it will cover: specifically, you
 * need to know the number of panels it paints at least once, regardless of color. In the example above, the robot
 * painted 6 panels at least once. (It painted its starting panel twice, but that panel is still only counted once;
 * it also never painted the panel it ended on.)
 *
 * Build a new emergency hull painting robot and run the Intcode program on it. How many panels does it paint at
 * least once?
 *
 * --- Part Two ---
 * You're not sure what it's trying to paint, but it's definitely not a registration identifier. The Space Police are
 * getting impatient.
 *
 * Checking your external ship cameras again, you notice a white panel marked "emergency hull painting robot starting
 * panel". The rest of the panels are still black, but it looks like the robot was expecting to start on a white panel,
 * not a black one.
 *
 * Based on the Space Law Space Brochure that the Space Police attached to one of your windows, a valid registration
 * identifier is always eight capital letters. After starting the robot on a single white panel instead, what
 * registration identifier does it paint on your hull?
 *
 */
public class Day11 {

    public long solvePartOne(String input) {
        //Set up my Instruction set
        List<IntcodeInstruction> instructions = new ArrayList<>();
        instructions.add(new FinishInstruction());
        instructions.add(new AddInstruction());
        instructions.add(new MultiplyInstruction());
        instructions.add(new InputSaveInstruction());
        instructions.add(new WriteOutputInstruction());
        instructions.add(new JumpIfTrueInstruction());
        instructions.add(new JumpIfFalseInstruction());
        instructions.add(new LessThanInstruction());
        instructions.add(new EqualsInstruction());
        instructions.add(new AdjustRelativeBaseInstruction());

        IntcodeComputer icc = new IntcodeComputer(instructions);
        Intcode ic = new Intcode(input);
        icc.initIntcode(ic);
        icc.runIntcode();

        int x = 0;
        int y = 0;
        Set<Point> paintedPoints = new HashSet<>(); //Panels start black
        Map<Point, String> colour = new HashMap<>();
        int direction = 0;
        while(icc.isComputationAwaitingInput()) {
            Point currentPoint = new Point(x, y);
            if (!colour.containsKey(currentPoint) || colour.get(currentPoint).equals("black")) {
                icc.addToInput(0);
            } else {
                icc.addToInput(1);
            }
            icc.runIntcode();
            String colourToPaint = (icc.getOutput() == 0 ? "black" : "white");
            paintedPoints.add(currentPoint);
            colour.put(currentPoint, colourToPaint);
            direction += (icc.getOutput() == 0 ? -90 : 90);
            direction += 360;
            direction = direction % 360;

            //TODO: Directions class can help here
            switch (direction) {
                case 0:
                    y -= 1;
                    break;
                case 90:
                    x += 1;
                    break;
                case 180:
                    y += 1;
                    break;
                case 270:
                    x -= 1;
                    break;
            }
        }

        return paintedPoints.size();
    }

    public long solvePartTwo(String input) {
        //Set up my Instruction set
        List<IntcodeInstruction> instructions = new ArrayList<>();
        instructions.add(new FinishInstruction());
        instructions.add(new AddInstruction());
        instructions.add(new MultiplyInstruction());
        instructions.add(new InputSaveInstruction());
        instructions.add(new WriteOutputInstruction());
        instructions.add(new JumpIfTrueInstruction());
        instructions.add(new JumpIfFalseInstruction());
        instructions.add(new LessThanInstruction());
        instructions.add(new EqualsInstruction());
        instructions.add(new AdjustRelativeBaseInstruction());

        IntcodeComputer icc = new IntcodeComputer(instructions);
        Intcode ic = new Intcode(input);
        icc.initIntcode(ic);
        icc.runIntcode();

        int x = 0;
        int y = 0;
        boolean firstPaint = true;
        Set<Point> paintedPoints = new HashSet<>(); //Panels start black
        Map<Point, String> colour = new HashMap<>();
        int direction = 0;
        while(icc.isComputationAwaitingInput()) {
            Point currentPoint = new Point(x, y);
            if(firstPaint) { // Only relevant for part 2
                icc.addToInput(1);
                firstPaint = false;
            }else if (!colour.containsKey(currentPoint) || colour.get(currentPoint).equals("black")) {
                icc.addToInput(0);
            } else {
                icc.addToInput(1);
            }
            icc.runIntcode();
            String colourToPaint = (icc.getOutput() == 0 ? "black" : "white");
            paintedPoints.add(currentPoint);
            colour.put(currentPoint, colourToPaint);
            direction += (icc.getOutput() == 0 ? -90 : 90);
            direction += 360;
            direction = direction % 360;

            switch (direction) {
                case 0:
                    y -= 1;
                    break;
                case 90:
                    x += 1;
                    break;
                case 180:
                    y += 1;
                    break;
                case 270:
                    x -= 1;
                    break;
            }
        }

        // Only relevant for part 2
        int minX = 9999;
        int maxX = -9999;
        int minY = 9999;
        int maxY = -9999;
        for(Point p : colour.keySet()) {
            minX = Math.min(minX, p.x);
            maxX = Math.max(maxX, p.x);
            minY = Math.min(minY, p.y);
            maxY = Math.max(maxY, p.y);
        }

        for(y = minY; y <= maxY; y++) {
            String rowLine = "";
            for(x = minX; x <= maxX; x++) {
                String colourOfPanel = "black";
                Point curPoint = new Point(x, y);
                if(colour.containsKey(curPoint)) {
                    colourOfPanel = colour.get(curPoint);
                }

                rowLine += (colourOfPanel.equals("white") ? "#" : " ");
            }
            System.out.println(rowLine);
        }



        return -1;
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2019, 11);

        Day11 d = new Day11();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of panels painted is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("" + partTwo);
    }
}


