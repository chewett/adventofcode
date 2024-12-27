package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 13: Claw Contraption ---
 * Next up: the lobby of a resort on a tropical island. The Historians take a moment to admire the hexagonal floor
 * tiles before spreading out.
 *
 * Fortunately, it looks like the resort has a new arcade! Maybe you can win some prizes from the claw machines?
 *
 * The claw machines here are a little unusual. Instead of a joystick or directional buttons to control the claw, these
 * machines have two buttons labeled A and B. Worse, you can't just put in a token and play; it costs 3 tokens to push
 * the A button and 1 token to push the B button.
 *
 * With a little experimentation, you figure out that each machine's buttons are configured to move the claw a specific
 * amount to the right (along the X axis) and a specific amount forward (along the Y axis) each time that button is
 * pressed.
 *
 * Each machine contains one prize; to win the prize, the claw must be positioned exactly above the prize on both the X
 * and Y axes.
 *
 * You wonder: what is the smallest number of tokens you would have to spend to win as many prizes as possible? You
 * assemble a list of every machine's button behavior and prize location (your puzzle input). For example:
 *
 * Button A: X+94, Y+34
 * Button B: X+22, Y+67
 * Prize: X=8400, Y=5400
 *
 * Button A: X+26, Y+66
 * Button B: X+67, Y+21
 * Prize: X=12748, Y=12176
 *
 * Button A: X+17, Y+86
 * Button B: X+84, Y+37
 * Prize: X=7870, Y=6450
 *
 * Button A: X+69, Y+23
 * Button B: X+27, Y+71
 * Prize: X=18641, Y=10279
 * This list describes the button configuration and prize location of four different claw machines.
 *
 * For now, consider just the first claw machine in the list:
 *
 * Pushing the machine's A button would move the claw 94 units along the X axis and 34 units along the Y axis.
 * Pushing the B button would move the claw 22 units along the X axis and 67 units along the Y axis.
 * The prize is located at X=8400, Y=5400; this means that from the claw's initial position, it would need to move
 * exactly 8400 units along the X axis and exactly 5400 units along the Y axis to be perfectly aligned with the prize
 * in this machine.
 * The cheapest way to win the prize is by pushing the A button 80 times and the B button 40 times. This would line up
 * the claw along the X axis (because 80*94 + 40*22 = 8400) and along the Y axis (because 80*34 + 40*67 = 5400). Doing
 * this would cost 80*3 tokens for the A presses and 40*1 for the B presses, a total of 280 tokens.
 *
 * For the second and fourth claw machines, there is no combination of A and B presses that will ever win a prize.
 *
 * For the third claw machine, the cheapest way to win the prize is by pushing the A button 38 times and the B button
 * 86 times. Doing this would cost a total of 200 tokens.
 *
 * So, the most prizes you could possibly win is two; the minimum tokens you would have to spend to win all (two)
 * prizes is 480.
 *
 * You estimate that each button would need to be pressed no more than 100 times to win a prize. How else would someone
 * be expected to play?
 *
 * Figure out how to win as many prizes as possible. What is the fewest tokens you would have to spend to win all
 * possible prizes?
 */
public class Day13 {

    /**
     * This solves the machines by using a simple brute force method
     * @param input Machines and details of those machines
     * @return Minimum number of tokens to win all winnable options
     */
    public long solvePartOne(List<String> input) {
        int totalPresses = 0;

        for(int lineNo = 0; lineNo < input.size(); lineNo += 4) {
            String[] btnA = input.get(lineNo).split("[\\+,]");
            int aX = Integer.parseInt(btnA[1]);
            int aY = Integer.parseInt(btnA[3]);

            String[] btnB = input.get(lineNo+1).split("[\\+,]");
            int bX = Integer.parseInt(btnB[1]);
            int bY = Integer.parseInt(btnB[3]);

            String[] prize = input.get(lineNo+2).split("[=,]");
            int prizeX = Integer.parseInt(prize[1]);
            int prizeY = Integer.parseInt(prize[3]);

            //Just brute force them since they said it needs a max of 100 presses for each button
            int minVal = Integer.MAX_VALUE;
            for(int aPress = 0; aPress <= 100; aPress++) {
                for(int bPress = 0; bPress <= 100; bPress++) {
                    int x = aX * aPress + bX * bPress;
                    int y = aY * aPress + bY * bPress;

                    if(x == prizeX && y == prizeY) {
                        //A press costs 3, b press costs 1
                        minVal = Math.min(minVal, aPress*3 + bPress);
                    }
                }
            }

            if(minVal != Integer.MAX_VALUE) {
                totalPresses += minVal;
            }
        }

        return totalPresses;
    }

    /**
     * This solves the machines by using MATHS! (details below)
     * @param input Machines and details of those machines
     * @return Minimum number of tokens to win all winnable options
     */
    public long solvePartTwo(List<String> input) {

        long totalPresses = 0;
        for(int lineNo = 0; lineNo < input.size(); lineNo += 4) {
            String[] btnA = input.get(lineNo).split("[\\+,]");
            int aX = Integer.parseInt(btnA[1]);
            int aY = Integer.parseInt(btnA[3]);

            String[] btnB = input.get(lineNo+1).split("[\\+,]");
            int bX = Integer.parseInt(btnB[1]);
            int bY = Integer.parseInt(btnB[3]);

            String[] prize = input.get(lineNo+2).split("[=,]");
            long prizeX = Integer.parseInt(prize[1]) + 10000000000000L;
            long prizeY = Integer.parseInt(prize[3]) + 10000000000000L;

            /*
             * TODO: write this into LaTeX in the repo? Sounds exciting!
             *
             * Here you have to use MATHS! you have a set of simultaneous equations
             * Time to crack out a paper and pen (and then I painfully transposed it here...)
             *
             *  1: aX * aPress + bX * bPress = prizeX
             *  2: aY * aPress + bY * bPress = prizeY
             *
             *  First get bPress:
             *  rearrange 1: bX * bPress = prizeX - aX * aPress
             *  rearrange:  bPress = (prizeX - aX * aPress) / bX
             *
             *  Put rearranged into 2: prizeY = aY * aPress + bY * ((prizeX - aX * aPress) / bX)
             *  rearrange: prizeY = aY * aPress + bY * ((prizeX/ bX) - (aX * aPress)/ bX)
             *  rearrange: prizeY = aY * aPress + (bY * prizeX)/ bX - (bY * aX * aPress)/ bX
             *  mult bX: prizeY * bX = bX * aY * aPress + bY * prizeX - bY * aX * aPress
             *  rearrange: (prizeY * bX) - (bY * prizeX) = bX * aY * aPress  - bY * aX * aPress
             *  rearrange: (prizeY * bX) - (bY * prizeX) = aPress * (bX * aY  - bY * aX)
             *  rearrange: (prizeY * bX - bY * prizeX) / (bX * aY  - bY * aX) = aPress
             *
             * Final: aPress = (prizeY * bX - bY * prizeX) / (bX * aY  - bY * aX)
             *
             */

            long aPress = (prizeY * bX - bY * prizeX) / (bX * aY  - bY * aX);
            long bPress = (prizeX - aX * aPress) / bX;

            //Verify we get the right value, if not then it didn't work and we can't solve it
            if ((aX * aPress + bX * bPress == prizeX) && (aY * aPress + bY * bPress == prizeY)) {
                totalPresses += 3*aPress + bPress;
            }
        }

        return totalPresses;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 13);

        Day13 d = new Day13();
        long partOne = d.solvePartOne(input);
        System.out.println("The min number of tokens to win all winnable machines are " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The min number of tokens for the new positions are " + partTwo);
    }
}


