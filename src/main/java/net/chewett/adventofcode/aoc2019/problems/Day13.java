package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/13
 * Go have a try yourself!
 *
 * --- Day 13: Care Package ---
 * As you ponder the solitude of space and the ever-increasing three-hour roundtrip for messages between you and Earth,
 * you notice that the Space Mail Indicator Light is blinking. To help keep you sane, the Elves have sent you a care
 * package.
 *
 * It's a new game for the ship's arcade cabinet! Unfortunately, the arcade is all the way on the other end of the ship.
 * Surely, it won't be hard to build your own - the care package even comes with schematics.
 *
 * The arcade cabinet runs Intcode software like the game the Elves sent (your puzzle input). It has a primitive screen
 * capable of drawing square tiles on a grid. The software draws tiles to the screen with output instructions:
 * every three output instructions specify the x position (distance from the left), y position (distance from the top),
 * and tile id. The tile id is interpreted as follows:
 *
 * 0 is an empty tile. No game object appears in this tile.
 * 1 is a wall tile. Walls are indestructible barriers.
 * 2 is a block tile. Blocks can be broken by the ball.
 * 3 is a horizontal paddle tile. The paddle is indestructible.
 * 4 is a ball tile. The ball moves diagonally and bounces off objects.
 * For example, a sequence of output values like 1,2,3,6,5,4 would draw a horizontal paddle tile (1 tile from the left
 * and 2 tiles from the top) and a ball tile (6 tiles from the left and 5 tiles from the top).
 *
 * Start the game. How many block tiles are on the screen when the game exits?
 *
 * --- Part Two ---
 * The game didn't run because you didn't put in any quarters. Unfortunately, you did not bring any quarters. Memory
 * address 0 represents the number of quarters that have been inserted; set it to 2 to play for free.
 *
 * The arcade cabinet has a joystick that can move left and right. The software reads the position of the joystick
 * with input instructions:
 *
 * If the joystick is in the neutral position, provide 0.
 * If the joystick is tilted to the left, provide -1.
 * If the joystick is tilted to the right, provide 1.
 * The arcade cabinet also has a segment display capable of showing a single number that represents the player's
 * current score. When three output instructions specify X=-1, Y=0, the third output instruction is not a tile;
 * the value instead specifies the new score to show in the segment display. For example, a sequence of output values
 * like -1,0,12345 would show 12345 as the player's current score.
 *
 * Beat the game by breaking all the blocks. What is your score after the last block is broken?
 */
public class Day13 {

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

        Map<Point, Long> screenData = new HashMap<>();

        IntcodeComputer icc = new IntcodeComputer(instructions);
        Intcode ic = new Intcode(input);
        icc.initIntcode(ic);
        icc.runIntcode();

        //Just keep reading the input and store it on a map of point to screen data
        while(icc.hasOutputToRead()) {
            Point curPoint = new Point((int)icc.getOutput(), (int)icc.getOutput());
            screenData.put(curPoint, icc.getOutput());
        }

        //Now just count how many block tiles there are
        int c = 0;
        for(Map.Entry<Point, Long> e : screenData.entrySet()) {
            if(e.getValue() == 2) {
                c++;
            }
        }

        return c;
    }

    public Point getBallPosition(Map<Point, Long> screen) {
        for(Map.Entry<Point, Long> e : screen.entrySet()) {
            if(e.getValue() == 4) {
                return e.getKey();
            }
        }
        return new Point(0,0);
    }

    public Point getPaddlePos(Map<Point, Long> screen) {
        for(Map.Entry<Point, Long> e : screen.entrySet()) {
            if(e.getValue() == 3) {
                return e.getKey();
            }
        }
        return new Point(0,0);
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

        Map<Point, Long> screenData = new HashMap<>();

        IntcodeComputer icc = new IntcodeComputer(instructions);
        Intcode ic = new Intcode(input);
        //Put in unlimited quarters
        ic.setIntToAddress(0, 2);
        icc.initIntcode(ic);
        icc.runIntcode();

        while(!icc.isComputationEntirelyFinished()) {
            while(icc.hasOutputToRead()) {
                Point curPoint = new Point((int)icc.getOutput(), (int)icc.getOutput());
                if(curPoint.x == -1 && curPoint.y == 0) {
                    //Read the output but do nothing
                    icc.getOutput();
                }else{
                    screenData.put(curPoint, icc.getOutput());
                }
            }

            //Move the paddle!
            if(icc.isComputationAwaitingInput()) {
                Point paddlePos = this.getPaddlePos(screenData);
                Point ballPos = this.getBallPosition(screenData);
                if(paddlePos.x > ballPos.x) {
                    icc.addToInput(-1);
                }else if(paddlePos.x < ballPos.x) {
                    icc.addToInput(1);
                }else{
                    icc.addToInput(0);
                }
            }
            icc.runIntcode();
        }

        while(icc.hasOutputToRead()) {
            Point curPoint = new Point((int)icc.getOutput(), (int)icc.getOutput());
            if(curPoint.x == -1 && curPoint.y == 0) {
                //Game has finished and now we have the score written out, save it!
                return icc.getOutput();
            }else{
                screenData.put(curPoint, icc.getOutput());
            }
        }

        //Something went wrong
        return -1;
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2019, 13);

        Day13 d = new Day13();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of blocks are " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The final score is " + partTwo);
    }
}


