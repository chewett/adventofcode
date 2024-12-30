package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.List;

/**
 * --- Day 17: Set and Forget ---
 * An early warning system detects an incoming solar flare and automatically activates the ship's electromagnetic shield. Unfortunately, this has cut off the Wi-Fi for many small robots that, unaware of the impending danger, are now trapped on exterior scaffolding on the unsafe side of the shield. To rescue them, you'll have to act quickly!
 *
 * The only tools at your disposal are some wired cameras and a small vacuum robot currently asleep at its charging
 * station. The video quality is poor, but the vacuum robot has a needlessly bright LED that makes it easy to spot no
 * matter where it is.
 *
 * An Intcode program, the Aft Scaffolding Control and Information Interface (ASCII, your puzzle input), provides
 * access to the cameras and the vacuum robot. Currently, because the vacuum robot is asleep, you can only access the
 * cameras.
 *
 * Running the ASCII program on your Intcode computer will provide the current view of the scaffolds. This is output,
 * purely coincidentally, as ASCII code: 35 means #, 46 means ., 10 starts a new line of output below the current one,
 * and so on. (Within a line, characters are drawn left-to-right.)
 *
 * In the camera output, # represents a scaffold and . represents open space. The vacuum robot is visible as
 * ^, v, <, or > depending on whether it is facing up, down, left, or right respectively. When drawn like this, the
 * vacuum robot is always on a scaffold; if the vacuum robot ever walks off of a scaffold and begins tumbling through
 * space uncontrollably, it will instead be visible as X.
 *
 * In general, the scaffold forms a path, but it sometimes loops back onto itself. For example, suppose you can see
 * the following view from the cameras:
 *
 * ..#..........
 * ..#..........
 * #######...###
 * #.#...#...#.#
 * #############
 * ..#...#...#..
 * ..#####...^..
 * Here, the vacuum robot, ^ is facing up and sitting at one end of the scaffold near the bottom-right of the image.
 * The scaffold continues up, loops across itself several times, and ends at the top-left of the image.
 *
 * The first step is to calibrate the cameras by getting the alignment parameters of some well-defined points. Locate
 * all scaffold intersections; for each, its alignment parameter is the distance between its left edge and the left
 * edge of the view multiplied by the distance between its top edge and the top edge of the view. Here, the
 * intersections from the above image are marked O:
 *
 * ..#..........
 * ..#..........
 * ##O####...###
 * #.#...#...#.#
 * ##O###O###O##
 * ..#...#...#..
 * ..#####...^..
 * For these intersections:
 *
 * The top-left intersection is 2 units from the left of the image and 2 units from the top of the image, so its
 * alignment parameter is 2 * 2 = 4.
 * The bottom-left intersection is 2 units from the left and 4 units from the top, so its alignment parameter
 * is 2 * 4 = 8.
 * The bottom-middle intersection is 6 from the left and 4 from the top, so its alignment parameter is 24.
 * The bottom-right intersection's alignment parameter is 40.
 * To calibrate the cameras, you need the sum of the alignment parameters. In the above example, this is 76.
 *
 * Run your ASCII program. What is the sum of the alignment parameters for the scaffold intersections?
 *
 * --- Part Two ---
 * Now for the tricky part: notifying all the other robots about the solar flare. The vacuum robot can do this
 * automatically if it gets into range of a robot. However, you can't see the other robots on the camera, so you need
 * to be thorough instead: you need to make the vacuum robot visit every part of the scaffold at least once.
 *
 * The vacuum robot normally wanders randomly, but there isn't time for that today. Instead, you can override its
 * movement logic with new rules.
 *
 * Force the vacuum robot to wake up by changing the value in your ASCII program at address 0 from 1 to 2. When you do
 * this, you will be automatically prompted for the new movement rules that the vacuum robot should use. The ASCII
 * program will use input instructions to receive them, but they need to be provided as ASCII code; end each line of
 * logic with a single newline, ASCII code 10.
 *
 * First, you will be prompted for the main movement routine. The main routine may only call the movement functions:
 * A, B, or C. Supply the movement functions to use as ASCII text, separating them with commas (,, ASCII code 44),
 * and ending the list with a newline (ASCII code 10). For example, to call A twice, then alternate between B and C
 * three times, provide the string A,A,B,C,B,C,B,C and then a newline.
 *
 * Then, you will be prompted for each movement function. Movement functions may use L to turn left, R to turn right,
 * or a number to move forward that many units. Movement functions may not call other movement functions. Again,
 * separate the actions with commas and end the list with a newline. For example, to move forward 10 units, turn left,
 * move forward 8 units, turn right, and finally move forward 6 units, provide the string 10,L,8,R,6 and then a newline.
 *
 * Finally, you will be asked whether you want to see a continuous video feed; provide either y or n and a newline.
 * Enabling the continuous video feed can help you see what's going on, but it also requires a significant amount of
 * processing power, and may even cause your Intcode computer to overheat.
 *
 * Due to the limited amount of memory in the vacuum robot, the ASCII definitions of the main routine and the movement
 * functions may each contain at most 20 characters, not counting the newline.
 *
 * For example, consider the following camera feed:
 *
 * #######...#####
 * #.....#...#...#
 * #.....#...#...#
 * ......#...#...#
 * ......#...###.#
 * ......#.....#.#
 * ^########...#.#
 * ......#.#...#.#
 * ......#########
 * ........#...#..
 * ....#########..
 * ....#...#......
 * ....#...#......
 * ....#...#......
 * ....#####......
 * In order for the vacuum robot to visit every part of the scaffold at least once, one path it could take is:
 *
 * R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2
 * Without the memory limit, you could just supply this whole string to function A and have the main routine call A
 * once. However, you'll need to split it into smaller parts.
 *
 * One approach is:
 *
 * Main routine: A,B,C,B,A,C
 * (ASCII input: 65, 44, 66, 44, 67, 44, 66, 44, 65, 44, 67, 10)
 * Function A:   R,8,R,8
 * (ASCII input: 82, 44, 56, 44, 82, 44, 56, 10)
 * Function B:   R,4,R,4,R,8
 * (ASCII input: 82, 44, 52, 44, 82, 44, 52, 44, 82, 44, 56, 10)
 * Function C:   L,6,L,2
 * (ASCII input: 76, 44, 54, 44, 76, 44, 50, 10)
 * Visually, this would break the desired path into the following parts:
 *
 * A,        B,            C,        B,            A,        C
 * R,8,R,8,  R,4,R,4,R,8,  L,6,L,2,  R,4,R,4,R,8,  R,8,R,8,  L,6,L,2
 *
 * CCCCCCA...BBBBB
 * C.....A...B...B
 * C.....A...B...B
 * ......A...B...B
 * ......A...CCC.B
 * ......A.....C.B
 * ^AAAAAAAA...C.B
 * ......A.A...C.B
 * ......AAAAAA#AB
 * ........A...C..
 * ....BBBB#BBBB..
 * ....B...A......
 * ....B...A......
 * ....B...A......
 * ....BBBBA......
 * Of course, the scaffolding outside your ship is much more complex.
 *
 * As the vacuum robot finds other robots and notifies them of the impending solar flare, it also can't help but leave
 * them squeaky clean, collecting any space dust it finds. Once it finishes the programmed set of movements, assuming
 * it hasn't drifted off into space, the cleaning robot will return to its docking station and report the amount of
 * space dust it collected as a large, non-ASCII value in a single output instruction.
 *
 * After visiting every part of the scaffold at least once, how much dust does the vacuum robot report it has
 * collected?
 */
public class Day17 {

    /**
     * Run the input getting the scaffolding path and work out the alignment parameters
     * @param input Intcode computation
     * @return Sum of alignment parameters
     */
    public long solvePartOne(String input) {

        try {
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

            List<String> rows = this.runAndCollectOutput(icc);

            //Work out x points on the graph and then work out it's value
            int totalCombinations = 0;
            //Start from x=1 y=1 since to have a square you need to not be on an edge
            for(int y = 1; y < rows.size() - 2; y++) {
                for(int x = 1; x < rows.get(y).length() - 2; x++) {
                    if(rows.get(y).substring(x, x+1).equals("#")) {
                        if(
                                rows.get(y).substring(x-1, x).equals("#") &&
                                        rows.get(y).substring(x+1, x+2).equals("#") &&
                                        rows.get(y-1).substring(x, x+1).equals("#") &&
                                        rows.get(y+1).substring(x, x+1).equals("#")
                        ){
                            totalCombinations += x * y;
                        }
                    }
                }
            }

            return totalCombinations;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something broke with the intcode computer");
        }
    }

    /**
     * Gets the intcode computer, collects the output, and returns it
     * @param icc Intcode computer
     * @return Returns the output as a list of strings
     */
    private List<String> runAndCollectOutput(IntcodeComputer icc) {
        List<String> rows = new ArrayList<>();

        String curRow = "";
        while(icc.hasOutputToRead()) {
            long output = icc.getOutput();

            if(output == 10) {
                rows.add(curRow);
                curRow = "";
            }else{
                //Handle non ASCII outputs (aka the right output rather than a character)
                if(output > 255) {
                    curRow += ""+ output;
                }else{
                    curRow += ((char) output);
                }
            }
        }
        rows.add(curRow);

        return rows;
    }

    /**
     * When we have found the list of instructions we need to compress it into macros A, B, C
     * This does that, returning a list of valid triples for A/B/C
     *
     * This is a recursive solution to try all the variosu combinations and then return only the valid ones
     *
     * @param parts Parts of the string which still need compression
     * @param a Value already set for A or null if not set
     * @param b Value already set for B or null if not set
     * @param c Value already set for C or null if not set
     * @return List of triples which are valid macro values for A, B, and C
     */
    private List<List<String>> compress(List<String> parts, String a, String b, String c) {
        List<List<String>> abcParts = new ArrayList<>();

        //Base case where A, B, and C have all been set to a non-null value
        if(a != null && b != null && c != null) {
            //If we have finished emptying the part list, then we can just add these sets and remove it
            if(parts.size() == 0) {
                List<String> newParts = new ArrayList<>();
                newParts.add(a);
                newParts.add(b);
                newParts.add(c);
                abcParts.add(newParts);
            }

            //If the part list wasn't empty then this set of A, B, and C are not valid and therefore we just return
            // An empty list here
            return abcParts;
        }

        //Now the recursive step... Grab the first item in the list and split it by commas
        String[] firstPartList = parts.get(0).split(",");

        //Then we work out all the ways we can split it and define it as a new macro
        for(int i = 1; i <= firstPartList.length; i++) {
            //First we just take the first elemenet, then the first two, etc, until we try the full length of it
            String newReplacement = String.join(",", Arrays.stream(firstPartList).toList().subList(0, i));
            //The macro cannot be longer than 20 so we have to enforce this here and give up if that's the case
            if(newReplacement.length() > 20) {
                //We break here since any further checks will always return a longer macro which won't be valid
                break;
            }

            //Now we have to create a new set of lists after removing the macro from it
            List<String> newListsToProcess = new ArrayList<>();
            for(String part : parts) {
                //If this string contains the macro we need to split it
                if(part.contains(newReplacement)) {
                    String[] newSplitPart = part.split(newReplacement);
                    for(String str : newSplitPart) {
                        //For each split we have to remove leading and trailing comma's
                        // TODO: Is there not a better way to do this in Java?
                        if(str.length() > 0) {
                            if(str.charAt(0) == ',') {
                                str = str.substring(1);
                            }
                            if(str.length() > 0) {
                                if (str.charAt(str.length() - 1) == ',') {
                                    str = str.substring(0, str.length() - 1);
                                }
                            }
                            //Assuming we don't end up with a zero length string we can add it
                            if(str.length() > 0) {
                                newListsToProcess.add(str);
                            }
                        }
                    }

                }else{
                    //If it didn't contain the macro then we just add it again
                    newListsToProcess.add(part);
                }
            }

            //Then we recursively call this after setting the macro to A, B, or C depending on what has been set before
            if(a == null) {
                abcParts.addAll(this.compress(newListsToProcess, newReplacement, null, null));
            }else if(b == null) {
                abcParts.addAll(this.compress(newListsToProcess, a, newReplacement, null));
            }else{
                abcParts.addAll(this.compress(newListsToProcess, a, b, newReplacement));
            }
        }

        return abcParts;
    }

    /**
     * Run the input and then sends the directions of the robot, which will then return the amount of space dust it cleaned
     * @param input Intcode computation
     * @return Sum of all the space dust it cleaned
     */
    public long solvePartTwo(String input) {
        try {
            input = "2" + input.substring(1);

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

            //First run it and then work out the grid
            icc.runIntcode();
            List<String> output = this.runAndCollectOutput(icc);

            //Parse out the grid and convert it to my helper grid
            List<String> gridAsString = output.subList(0, output.size()-2);
            Discrete2DPositionGrid<Character> grid = FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
                    FormatConversion.convertStringArrayToCharListList(gridAsString)
            );

            //Now we need to work out how to move "along" the grid
            Point curLocation = grid.getPositionOfValueAssumingOnlyOne('^');
            //Track the "last" point so we don't end up going back on ourselves
            Point lastPoint = grid.getPositionOfValueAssumingOnlyOne('^');
            int curDirection = Directions.NORTH;

            //Track all the directions
            List<String> directions = new ArrayList<>();
            while(true) {
                //Find all the possible directions we could move
                List<Point> possibleMoves = grid.getDirectlyAdjacentPoints(curLocation);
                Point nextPoint = null;
                for(Point p : possibleMoves) {
                    //If we find a # that is not our "last" point then it's a new direction to go in
                    if(grid.getValueAtPosition(p) == '#' && !p.equals(lastPoint)) {
                        nextPoint = p;
                        break;
                    }
                }

                //If we didn't find a new point we can stop
                if(nextPoint == null) {
                    break;
                }

                //Work out the new direction we need to move in
                int newDirection = Directions.getDirectionFromPointToPoint(curLocation, nextPoint);
                //And this will then work out how we need to rotate and add that to the list of directions
                directions.addAll(Directions.getDirectionsFromPointToPoint(curDirection, newDirection));

                //Now we work out how "far" we need to move along the list
                int numToMove = 0;
                boolean foundStop = false;
                while(!foundStop) {
                    Point nextPointAlongLine = Directions.getPointInDirection(curLocation, newDirection);
                    if(grid.getValueAtPosition(nextPointAlongLine) == '#') {
                        numToMove++;
                        //Ensure we track the last point before we move
                        lastPoint = curLocation;
                        curLocation = nextPointAlongLine;
                    }else{
                        foundStop = true;
                    }
                }
                curDirection = newDirection;

                directions.add(String.valueOf(numToMove));
            }

            List<String> fullPart = new ArrayList<>();
            fullPart.add(String.join(",", directions));

            List<List<String>> compressed = this.compress(fullPart, null, null, null);
            List<String> allMacroCalls = new ArrayList<>();
            for(List<String> compressedParts : compressed) {
                String a = compressedParts.get(0);
                String b = compressedParts.get(1);
                String c = compressedParts.get(2);

                String tmp = String.join(",", directions);
                List<String> macroCalls = new ArrayList<>();
                while(tmp.length() > 0) {
                    if(tmp.startsWith(a)) {
                        macroCalls.add("A");
                        tmp = tmp.substring(a.length());
                    }else if(tmp.startsWith(b)) {
                        macroCalls.add("B");
                        tmp = tmp.substring(b.length());
                    }else if(tmp.startsWith(c)) {
                        macroCalls.add("C");
                        tmp = tmp.substring(c.length());
                    }else{
                        throw new RuntimeException("Something went wrong with the solver if we hit here");
                    }
                    //It should always start with , until we get to the end
                    if(tmp.startsWith(",")) {
                        tmp = tmp.substring(1);
                    }

                }
                allMacroCalls.add(String.join(",", macroCalls));
            }

            //Now we have a list of directions to move in
            // The full path is:
            // L,12,L,10,R,8,L,12,R,8,R,10,R,12,L,12,L,10,R,8,L,12,R,8,R,10,R,12,L,10,R,12,R,8,L,10,R,12,R,8,R,8,R,10,R,12,L,12,L,10,R,8,L,12,R,8,R,10,R,12,L,10,R,12,R,8
            //Now I manually split it into parts:

            // A: L,12,L,10,R,8,L,12
            // B: R,8,R,10,R,12
            // A: L,12,L,10,R,8,L,12
            // B: R,8,R,10,R,12
            // C: L,10,R,12,R,8
            // C: L,10,R,12,R,8
            // B: R,8,R,10,R,12,
            // A: L,12,L,10,R,8,L,12
            // B: R,8,R,10,R,12
            // C: L,10,R,12,R,8

            //Which then has directions of:  A,B,A,B,C,C,B,A,B,C
            //Originally I then just put this in and got it working but then I worked out how to generate that automatically

            //Assumes there is only one solution (there is)
            List<String> com1 = compressed.get(0);
            String macroCall = allMacroCalls.get(0);

            // A B and C series
            icc.addAllToInput(macroCall + "\n");

            // A
            icc.addAllToInput(com1.get(0) + "\n");

            // B
            icc.addAllToInput(com1.get(1) + "\n");

            // C
            icc.addAllToInput(com1.get(2) + "\n");

            // Continuous feed?
            icc.addToInput((int)'n');
            icc.addToInput((int)'\n');

            icc.runIntcode();
            List<String> finalOut = this.runAndCollectOutput(icc);
            return Long.parseLong(finalOut.get(finalOut.size()-1));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something broke with the intcode computer");
        }
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2019, 17);

        Day17 d = new Day17();
        long partOne = d.solvePartOne(input);
        System.out.println("The total combinations is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The amount of space dust collected is " + partTwo);
    }
}


