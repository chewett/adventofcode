package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 21: Keypad Conundrum ---
 * As you teleport onto Santa's Reindeer-class starship, The Historians begin to panic: someone from their search
 * party is missing. A quick life-form scan by the ship's computer reveals that when the missing Historian
 * teleported, he arrived in another part of the ship.
 *
 * The door to that area is locked, but the computer can't open it; it can only be opened by physically typing the
 * door codes (your puzzle input) on the numeric keypad on the door.
 *
 * The numeric keypad has four rows of buttons: 789, 456, 123, and finally an empty gap followed by 0A. Visually,
 * they are arranged like this:
 *
 * +---+---+---+
 * | 7 | 8 | 9 |
 * +---+---+---+
 * | 4 | 5 | 6 |
 * +---+---+---+
 * | 1 | 2 | 3 |
 * +---+---+---+
 *     | 0 | A |
 *     +---+---+
 * Unfortunately, the area outside the door is currently depressurized and nobody can go near the door. A robot needs
 * to be sent instead.
 *
 * The robot has no problem navigating the ship and finding the numeric keypad, but it's not designed for button
 * pushing: it can't be told to push a specific button directly. Instead, it has a robotic arm that can be controlled
 * remotely via a directional keypad.
 *
 * The directional keypad has two rows of buttons: a gap / ^ (up) / A (activate) on the first row
 * and < (left) / v (down) / > (right) on the second row. Visually, they are arranged like this:
 *
 *     +---+---+
 *     | ^ | A |
 * +---+---+---+
 * | < | v | > |
 * +---+---+---+
 * When the robot arrives at the numeric keypad, its robotic arm is pointed at the A button in the bottom right
 * corner. After that, this directional keypad remote control must be used to maneuver the robotic arm:
 * the up / down / left / right buttons cause it to move its arm one button in that direction, and the A button causes
 * the robot to briefly move forward, pressing the button being aimed at by the robotic arm.
 *
 * For example, to make the robot type 029A on the numeric keypad, one sequence of inputs on the directional keypad
 * you could use is:
 *
 * < to move the arm from A (its initial position) to 0.
 * A to push the 0 button.
 * ^A to move the arm to the 2 button and push it.
 * >^^A to move the arm to the 9 button and push it.
 * vvvA to move the arm to the A button and push it.
 * In total, there are three shortest possible sequences of button presses on this directional keypad that would
 * cause the robot to type 029A: <A^A>^^AvvvA, <A^A^>^AvvvA, and <A^A^^>AvvvA.
 *
 * Unfortunately, the area containing this directional keypad remote control is currently experiencing high levels
 * of radiation and nobody can go near it. A robot needs to be sent instead.
 *
 * When the robot arrives at the directional keypad, its robot arm is pointed at the A button in the upper right
 * corner. After that, a second, different directional keypad remote control is used to control this robot (in the
 * same way as the first robot, except that this one is typing on a directional keypad instead of a numeric keypad).
 *
 * There are multiple shortest possible sequences of directional keypad button presses that would cause this robot
 * to tell the first robot to type 029A on the door. One such sequence is v<<A>>^A<A>AvA<^AA>A<vAAA>^A.
 *
 * Unfortunately, the area containing this second directional keypad remote control is currently -40 degrees! Another
 * robot will need to be sent to type on that directional keypad, too.
 *
 * There are many shortest possible sequences of directional keypad button presses that would cause this robot to
 * tell the second robot to tell the first robot to eventually type 029A on the door. One such sequence is
 * <vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A.
 *
 * Unfortunately, the area containing this third directional keypad remote control is currently full of Historians,
 * so no robots can find a clear path there. Instead, you will have to type this sequence yourself.
 *
 * Were you to choose this sequence of button presses, here are all of the buttons that would be pressed on your
 * directional keypad, the two robots' directional keypads, and the numeric keypad:
 *
 * <vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A
 * v<<A>>^A<A>AvA<^AA>A<vAAA>^A
 * <A^A>^^AvvvA
 * 029A
 * In summary, there are the following keypads:
 *
 * One directional keypad that you are using.
 * Two directional keypads that robots are using.
 * One numeric keypad (on a door) that a robot is using.
 * It is important to remember that these robots are not designed for button pushing. In particular, if a robot arm
 * is ever aimed at a gap where no button is present on the keypad, even for an instant, the robot will panic
 * unrecoverably. So, don't do that. All robots will initially aim at the keypad's A key, wherever it is.
 *
 * To unlock the door, five codes will need to be typed on its numeric keypad. For example:
 *
 * 029A
 * 980A
 * 179A
 * 456A
 * 379A
 * For each of these, here is a shortest sequence of button presses you could type to cause the desired code to be
 * typed on the numeric keypad:
 *
 * 029A: <vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A
 * 980A: <v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A
 * 179A: <v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A
 * 456A: <v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A
 * 379A: <v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A
 * The Historians are getting nervous; the ship computer doesn't remember whether the missing Historian is trapped
 * in the area containing a giant electromagnet or molten lava. You'll need to make sure that for each of the five
 * codes, you find the shortest sequence of button presses necessary.
 *
 * The complexity of a single code (like 029A) is equal to the result of multiplying these two values:
 *
 * The length of the shortest sequence of button presses you need to type on your directional keypad in order to cause
 * the code to be typed on the numeric keypad; for 029A, this would be 68.
 * The numeric part of the code (ignoring leading zeroes); for 029A, this would be 29.
 * In the above example, complexity of the five codes can be found by calculating
 * 68 * 29, 60 * 980, 68 * 179, 64 * 456, and 64 * 379. Adding these together produces 126384.
 *
 * Find the fewest number of button presses you'll need to perform in order to cause the robot in front of the door to
 * type each code. What is the sum of the complexities of the five codes on your list?
 *
 * --- Part Two ---
 * Just as the missing Historian is released, The Historians realize that a second member of their search party has
 * also been missing this entire time!
 *
 * A quick life-form scan reveals the Historian is also trapped in a locked area of the ship. Due to a variety of
 * hazards, robots are once again dispatched, forming another chain of remote control keypads managing
 * robotic-arm-wielding robots.
 *
 * This time, many more robots are involved. In summary, there are the following keypads:
 *
 * One directional keypad that you are using.
 * 25 directional keypads that robots are using.
 * One numeric keypad (on a door) that a robot is using.
 * The keypads form a chain, just like before: your directional keypad controls a robot which is typing on a
 * directional keypad which controls a robot which is typing on a directional keypad... and so on, ending with
 * the robot which is typing on the numeric keypad.
 *
 * The door codes are the same this time around; only the number of robots and directional keypads has changed.
 *
 * Find the fewest number of button presses you'll need to perform in order to cause the robot in front of the
 * door to type each code. What is the sum of the complexities of the five codes on your list?
 */
public class Day21 {


    //Stores the positions of the buttons on the numpad and position grid's
    private Discrete2DPositionGrid<Character> numpadGrid = new Discrete2DPositionGrid<>(' ');
    private Discrete2DPositionGrid<Character> positionGrid = new Discrete2DPositionGrid<>(' ');


    public Day21() {
        //Init the grids to a nice set of values
        this.numpadGrid.setValueAtPosition(1, 0, '0');
        this.numpadGrid.setValueAtPosition(2, 0, 'A');

        this.numpadGrid.setValueAtPosition(0, 1, '1');
        this.numpadGrid.setValueAtPosition(1, 1, '2');
        this.numpadGrid.setValueAtPosition(2, 1, '3');

        this.numpadGrid.setValueAtPosition(0, 2, '4');
        this.numpadGrid.setValueAtPosition(1, 2, '5');
        this.numpadGrid.setValueAtPosition(2, 2, '6');

        this.numpadGrid.setValueAtPosition(0, 3, '7');
        this.numpadGrid.setValueAtPosition(1, 3, '8');
        this.numpadGrid.setValueAtPosition(2, 3, '9');

        this.positionGrid.setValueAtPosition(0, 0, '<');
        this.positionGrid.setValueAtPosition(1, 0, 'v');
        this.positionGrid.setValueAtPosition(2, 0, '>');

        this.positionGrid.setValueAtPosition(1, 1, '^');
        this.positionGrid.setValueAtPosition(2, 1, 'A');
    }


    Map<Character, Map<Character, List<String>>> positionpadSequences = new HashMap<>();

    /**
     * Memoized helper function to find the set of paths from each position to each other position on the number page
     * //TODO: This is nigh identical to the grid one, refactor this so it's the same function essentially
     *
     * @param start Starting character
     * @param end Ending character
     * @return List of possible shortest ways to go from the start to the end
     */
    public List<String> findShortestPositionPadSequence(char start, char end) {
        if(positionpadSequences.containsKey(start) && positionpadSequences.get(start).containsKey(end)) {
            return positionpadSequences.get(start).get(end);
        }

        List<String> valToMemoize = new ArrayList<>();

        //Base case
        if(start == end) {
            valToMemoize.add("A");

        }else if(start == ' ') {
            //Do nothing... There are no ways off this square so don't return anything

        }else{
            //We can only move in the shortest way either by doing horizontally or vertically towards the objective
            //So we just add either A <> or v^ each time depending on the direction to it
            //Sometimes we only go one direction because we are already in line with the goal
            Point startPoint = this.positionGrid.getPositionOfValueAssumingOnlyOne(start);
            Point endPoint = this.positionGrid.getPositionOfValueAssumingOnlyOne(end);

            if(startPoint.x < endPoint.x) {
                Point newPos = new Point(startPoint.x+1, startPoint.y);
                char newChar = positionGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestPositionPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('>' + s);
                }
            }else if(startPoint.x > endPoint.x) {
                Point newPos = new Point(startPoint.x-1, startPoint.y);
                char newChar = positionGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestPositionPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('<' + s);
                }
            }

            if(startPoint.y < endPoint.y) {
                Point newPos = new Point(startPoint.x, startPoint.y+1);
                char newChar = positionGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestPositionPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('^' + s);
                }
            }else if(startPoint.y > endPoint.y) {
                Point newPos = new Point(startPoint.x, startPoint.y-1);
                char newChar = positionGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestPositionPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('v' + s);
                }
            }
        }

        if(!numpadSequences.containsKey(start)) {
            numpadSequences.put(start, new HashMap<>());
        }

        numpadSequences.get(start).put(end, valToMemoize);

        return valToMemoize;
    }



    Map<Character, Map<Character, List<String>>> numpadSequences = new HashMap<>();

    /**
     * Memoized helper function to find the set of paths from each position to each other position on the number page
     * //TODO: This is nigh identical to the number pad one, refactor this so it's the same function essentially
     *
     * @param start Starting character
     * @param end Ending character
     * @return List of possible shortest ways to go from the start to the end
     */
    public List<String> findShortestKeyPadSequence(char start, char end) {
        if(numpadSequences.containsKey(start) && numpadSequences.get(start).containsKey(end)) {
            return numpadSequences.get(start).get(end);
        }

        List<String> valToMemoize = new ArrayList<>();

        //Base case
        if(start == end) {
            valToMemoize.add("A");

        }else if(start == ' ') {
            //Do nothing... There are no ways off this square so don't return anything

        }else{
            //We can only move in the shortest way either by doing horizontally or vertically towards the objective
            //So we just add either A <> or v^ each time depending on the direction to it
            //Sometimes we only go one direction because we are already in line with the goal
            Point startPoint = this.numpadGrid.getPositionOfValueAssumingOnlyOne(start);
            Point endPoint = this.numpadGrid.getPositionOfValueAssumingOnlyOne(end);

            if(startPoint.x < endPoint.x) {
                Point newPos = new Point(startPoint.x+1, startPoint.y);
                char newChar = numpadGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestKeyPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('>' + s);
                }
            }else if(startPoint.x > endPoint.x) {
                Point newPos = new Point(startPoint.x-1, startPoint.y);
                char newChar = numpadGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestKeyPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('<' + s);
                }
            }

            if(startPoint.y < endPoint.y) {
                Point newPos = new Point(startPoint.x, startPoint.y+1);
                char newChar = numpadGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestKeyPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('^' + s);
                }
            }else if(startPoint.y > endPoint.y) {
                Point newPos = new Point(startPoint.x, startPoint.y-1);
                char newChar = numpadGrid.getValueAtPosition(newPos);
                List<String> allStringsToAppend = this.findShortestKeyPadSequence(newChar, end);
                for(String s : allStringsToAppend) {
                    valToMemoize.add('v' + s);
                }
            }
        }


        if(!numpadSequences.containsKey(start)) {
            numpadSequences.put(start, new HashMap<>());
        }

        numpadSequences.get(start).put(end, valToMemoize);

        return valToMemoize;
    }

    public Map<String, Map<Integer, Long>> memoizedRecurseSingleString = new HashMap<>();

    /**
     * This is the memoized function which makes this code actually possible. We track the inputs and store it so
     * that we can return it later because this is called A LOT
     *
     * @param step String representing the buttons we need to press
     * @param keypads The number of keypads we need to simulate, the base case is where this is 1
     * @return The minimum number of steps we need for the given input
     */
    public long recurseSingleString(String step, int keypads) {
        if(memoizedRecurseSingleString.containsKey(step) && memoizedRecurseSingleString.get(step).containsKey(keypads)) {
            return memoizedRecurseSingleString.get(step).get(keypads);
        }

        long thisCombinedLength = 0;
        char startCharForInnerLoop = 'A';
        for(char innerChar : step.toCharArray()) {
            List<String> allInnerMoves = this.findShortestPositionPadSequence(startCharForInnerLoop, innerChar);
            if(keypads == 1) {
                //If we have no more inputs, then this is the "final" length and base case
                //This function always returns a set of inputs the same length, so we can just pick the lenght of the first
                thisCombinedLength += allInnerMoves.get(0).length();
            }else{
                //Recursive step
                thisCombinedLength += this.newRecursiveStep(allInnerMoves, keypads-1);
            }
            startCharForInnerLoop = innerChar;
        }

        if(!memoizedRecurseSingleString.containsKey(step)) {
            memoizedRecurseSingleString.put(step, new HashMap<>());
        }
        memoizedRecurseSingleString.get(step).put(keypads, thisCombinedLength);

        return thisCombinedLength;
    }

    /**
     * Recursive step to loop over each of the strings and find the smallest number of ways to process them
     * @param allMyMoves The list of inputs to try
     * @param keypads The number of iterative keypads we need to process
     * @return The minimum length of inputs for this problem
     */
    public long newRecursiveStep(List<String> allMyMoves, int keypads) {
        long smallestInner = 0;
        for(String s : allMyMoves) {
            long thisCombinedLength = this.recurseSingleString(s, keypads);

            if(smallestInner == 0 || smallestInner > thisCombinedLength) {
                smallestInner = thisCombinedLength;
            }
        }

        return smallestInner;
    }

    /**
     * Given a single string this will work out the minimum length series of inputs
     * @param string String to run the algorithm on
     * @param keypads The number of number keypads in the chain
     * @return Minimum length of inputs
     */
    public long runAlgorithmOnSingleString(String string, int keypads) {
        long bestStringLength = 0;
        char curChar = 'A';
        //Loop over moving from character to character each time
        for(char c : string.toCharArray()) {
            List<String> bottomLayerOptions = this.findShortestKeyPadSequence(curChar, c);
            bestStringLength += this.newRecursiveStep(bottomLayerOptions, keypads-1);
            curChar = c;
        }

        return bestStringLength;
    }

    /**
     * Works out how to control all three robots to eventually press all the right buttons and free the historian
     * @param input Buttons we need to press
     * @return The sum of the complexities
     */
    public long solvePartOne(List<String> input) {
        long total = 0;
        for(String line: input) {
            long ans = this.runAlgorithmOnSingleString(line, 3);
            long multFactor = Long.parseLong(line.substring(0, line.length()-1));
            total += ans * multFactor;
        }

        return total;

    }

    /**
     * Works out how to control all the many many robots to eventually press all the right buttons and free the historian
     * @param input Buttons we need to press
     * @return The sum of the complexities
     */
    public long solvePartTwo(List<String> input) {
        long total = 0;
        for(String line: input) {
            long ans = this.runAlgorithmOnSingleString(line, 26);
            long multFactor = Long.parseLong(line.substring(0, line.length()-1));
            total += ans * multFactor;
        }

        return total;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 21);

        Day21 d = new Day21();
        long partOne = d.solvePartOne(input);
        System.out.println("The sum of the complexities is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The new sum of the complexities is " + partTwo);
    }
}


