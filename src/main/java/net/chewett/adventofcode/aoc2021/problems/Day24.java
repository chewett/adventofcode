package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.List;

/**
 * --- Day 24: Arithmetic Logic Unit ---
 * Magic smoke starts leaking from the submarine's arithmetic logic unit (ALU). Without the ability to perform basic
 * arithmetic and logic functions, the submarine can't produce cool patterns with its Christmas lights!
 *
 * It also can't navigate. Or run the oxygen system.
 *
 * Don't worry, though - you probably have enough oxygen left to give you enough time to build a new ALU.
 *
 * The ALU is a four-dimensional processing unit: it has integer variables w, x, y, and z. These variables all start
 * with the value 0. The ALU also supports six instructions:
 *
 * inp a - Read an input value and write it to variable a.
 * add a b - Add the value of a to the value of b, then store the result in variable a.
 * mul a b - Multiply the value of a by the value of b, then store the result in variable a.
 * div a b - Divide the value of a by the value of b, truncate the result to an integer, then store the result in
 * variable a. (Here, "truncate" means to round the value toward zero.)
 * mod a b - Divide the value of a by the value of b, then store the remainder in variable a. (This is also called
 * the modulo operation.)
 * eql a b - If the value of a and b are equal, then store the value 1 in variable a. Otherwise, store the value 0 in
 * variable a.
 * In all of these instructions, a and b are placeholders; a will always be the variable where the result of the
 * operation is stored (one of w, x, y, or z), while b can be either a variable or a number. Numbers can be positive
 * or negative, but will always be integers.
 *
 * The ALU has no jump instructions; in an ALU program, every instruction is run exactly once in order from top to
 * bottom. The program halts after the last instruction has finished executing.
 *
 * (Program authors should be especially cautious; attempting to execute div with b=0 or attempting to execute mod
 * with a<0 or b<=0 will cause the program to crash and might even damage the ALU. These operations are never
 * intended in any serious ALU program.)
 *
 * For example, here is an ALU program which takes an input number, negates it, and stores it in x:
 *
 * inp x
 * mul x -1
 * Here is an ALU program which takes two input numbers, then sets z to 1 if the second input number is three times
 * larger than the first input number, or sets z to 0 otherwise:
 *
 * inp z
 * inp x
 * mul z 3
 * eql z x
 * Here is an ALU program which takes a non-negative integer as input, converts it into binary, and stores the lowest
 * (1's) bit in z, the second-lowest (2's) bit in y, the third-lowest (4's) bit in x, and the fourth-lowest (8's) bit
 * in w:
 *
 * inp w
 * add z w
 * mod z 2
 * div w 2
 * add y w
 * mod y 2
 * div w 2
 * add x w
 * mod x 2
 * div w 2
 * mod w 2
 * Once you have built a replacement ALU, you can install it in the submarine, which will immediately resume what it
 * was doing when the ALU failed: validating the submarine's model number. To do this, the ALU will run the MOdel
 * Number Automatic Detector program (MONAD, your puzzle input).
 *
 * Submarine model numbers are always fourteen-digit numbers consisting only of digits 1 through 9. The digit 0 cannot
 * appear in a model number.
 *
 * When MONAD checks a hypothetical fourteen-digit model number, it uses fourteen separate inp instructions, each
 * expecting a single digit of the model number in order of most to least significant. (So, to check the model number
 * 13579246899999, you would give 1 to the first inp instruction, 3 to the second inp instruction, 5 to the third inp
 * instruction, and so on.) This means that when operating MONAD, each input instruction should only ever be given an
 * integer value of at least 1 and at most 9.
 *
 * Then, after MONAD has finished running all of its instructions, it will indicate that the model number was valid by
 * leaving a 0 in variable z. However, if the model number was invalid, it will leave some other non-zero value in z.
 *
 * MONAD imposes additional, mysterious restrictions on model numbers, and legend says the last copy of the MONAD
 * documentation was eaten by a tanuki. You'll need to figure out what MONAD does some other way.
 *
 * To enable as many submarine features as possible, find the largest valid fourteen-digit model number that contains
 * no 0 digits. What is the largest model number accepted by MONAD?
 *
 * --- Part Two ---
 * As the submarine starts booting up things like the Retro Encabulator, you realize that maybe you don't need all
 * these submarine features after all.
 *
 * What is the smallest model number accepted by MONAD?
 */
public class Day24 {

    /**
     * I see that the "trick" to this is understanding that you start at z = 0 and want to end at z = 0
     * Each time you run the algorithm on a new input you either multiply the number by 26 or divide it by 26
     *
     * To get back to 0 you need to divide the same number of times as you multiply
     *
     * Since only 7 sets divide by 26 (the others divide by 1) then you need all of these divider blocks to divide
     *
     * So what we do is bruteforce the solutions while maintaining the above axiom (equal divider + multiplier blocks)
     *
     * For a block that needs to divide, you can work out the digit needed to be passed in mathmatically
     * Even better, sometimes this digit is outside our range (1-9) so we just can ignore the set of data
     *
     */


    //This is a hardcoded set of values taken from the solution. These are the only values that actually matter
    public int[] divideParts = new int[] {1, 1, 1, 26, 1, 1, 1, 26, 1, 26, 26, 26, 26, 26};
    public int[] addXParts = new int[] {10, 12, 15, -9, 15, 10, 14, -5, 14, -7, -12, -10, -1, -11};
    public int[] addYParts = new int[] {15, 8, 2, 6, 13, 4, 1, 9, 5, 13, 9, 6 ,2 ,2};

    //This allows me to abstract the code a little better and fix the order of checking values for part 1 and part 2
    private int[] largestNumbersFirst = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private int[] smallestNumbersFirst = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9};


    /**
     * This is all the instructions do with the parameters being the differences converted to a few lines of formulae
     * @param z0 This is the starting value of Z (the only parameter that carries over)
     * @param w This is the possible "input digit" which is always placed into the w block
     * @param divide This is your "divider" value, it is always either 26 or 1
     * @param addX This is your value you add to X
     * @param addY This is the value you add to Y
     * @return This returns the "ending value" of Z which as mentioned is the only thing which carries over
     */
    public long runCodeSegment(long z0, int w, int divide, int addX, int addY) {
        // This comes from either: `div z 1` or `div z 26`
        long z1 = z0 / divide;

        /**
         * This comes from:
         * add x z
         * mod x 26
         * add x 10
         * eql x w
         * eql x 0
         *
         * If x is 0 after this, then you just return z1
         * This is because `y` goes through a few permutations and then is multiplied by `x` and then added to `z`
         * Therefore if `x` is zero, then `y` becomes zero, and nothing is added to `z`
         */
        if (w == ((z0 % 26) + addX)) {
            return z1;
        }else{
            /**
             * If x is not set to 0 then add y to z, and y is created thusly:
             *
             * mul y 0
             * add y 25
             * mul y x
             * add y 1
             * mul z y # y here is either 1 or 26, as mentioned above if its 1 (because x is 0) then nothing happens
             *
             * mul y 0
             * add y w
             * add y 15
             * mul y x # again if x is 0, then y is 0
             * add z y # and then this does nothing (aka the top part of this if runs instead of this)
             */

            return (z1 * 26) + w + addY;
        }
    }

    /**
     * This works out the value needed for the input to cause the division code to run
     * @param z0 Takes the current Z value
     * @param addX And your "add X" constant
     * @return Returns the value needed for w to enter this section of the code
     */
    public int workOutDivValue(long z0, int addX) {
        return (int)(z0 % 26) + addX;
    }

    /**
     * Recursive step to solve problem. This has the current Z value (only thing persisted), the index telling it
     * which segment we are on, and whether we are starting at the largest or smallest values
     * @param z0 Current Z value
     * @param index Index of the segments to proceed from
     * @param findLargest Whether we want to find the largest or smallest value
     * @return Returns a string if we have found a valid model number, otherwise it returns null indicating it should continue
     */
    public String solvePart(long z0, int index, boolean findLargest) {
        //If we have reached index 14 we have finished so we check to see what the z value is
        if(index == 14) {
            if(z0 == 0) {
                //Returning an empty string says we have found a z=0 value, and should calculate the model number
                return "";
            }else{
                return null;
            }
        }

        //Pull out the various data for our current index
        int divideVal = this.divideParts[index];
        int addXVal = this.addXParts[index];
        int addYVal = this.addYParts[index];

        //We need to do 7 *26 and 7 /26 so whenever the divide value is 26 we MUST do a divide.
        //Since doing the divide is relatively simple maths, we can just work out whether this is valid or not
        if(divideVal == 26) {
            //Work out the correct input to run a divide, and then check if that's valid
            int thisPosValue = this.workOutDivValue(z0, addXVal);
            if(thisPosValue > 9 || thisPosValue < 1) {
                //If it's not valid then we can immediately drop this chain of checking saving lots of time
                return null;
            }

            long zVal = this.runCodeSegment(z0, thisPosValue, divideVal, addXVal, addYVal);
            String solvedString = this.solvePart(zVal, index+1, findLargest);
            if(solvedString != null) {
                //Recursive "solution" step where we add our value to the current string and step backwards
                solvedString = thisPosValue + solvedString;
                return solvedString;
            }else{
                return null;
            }

        }else{
            //Simple way of switching which ordering we want for part 1 and part 2
            int[] orderingToUse;
            if(findLargest) {
                orderingToUse = this.largestNumbersFirst;
            }else{
                orderingToUse = this.smallestNumbersFirst;
            }

            //This isn't a divide step so we just go through all the options
            for(int i : orderingToUse) {
                //Work out the current zval for this index
                long zVal = this.runCodeSegment(z0, i, divideVal, addXVal, addYVal);
                //And then recurse to see if we can find a possible solution
                String solvedString = this.solvePart(zVal, index+1, findLargest);
                if(solvedString != null) {
                    solvedString = i + solvedString;
                    return solvedString;
                }
            }
        }

        return null;
    }

    /**
     * This just solves it looking for the largest number
     * @return Largest valid model number
     */
    public long solvePartOne() {
        String res = this.solvePart(0L, 0, true);
        if(res != null) {
            return Long.parseLong(res);
        }

        return -1;
    }

    /**
     * Near identically to part 1 this solves for the smallest number
     * @return Smallest valid model number
     */
    public long solvePartTwo() {
        String res = this.solvePart(0L, 0, false);
        if(res != null) {
            return Long.parseLong(res);
        }

        return -1;
    }

    public static void main(String[] args) {
        //TODO: in the future parse the magic numbers out of this.
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2021, 24);

        Day24 d = new Day24();
        System.out.println("The highest valid model number is " + d.solvePartOne());
        System.out.println("The lowest valid model number is " + d.solvePartTwo());

    }

}
