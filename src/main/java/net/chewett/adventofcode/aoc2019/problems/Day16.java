package net.chewett.adventofcode.aoc2019.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 16: Flawed Frequency Transmission ---
 * You're 3/4ths of the way through the gas giants. Not only do roundtrip signals to Earth take five hours, but the
 * signal quality is quite bad as well. You can clean up the signal with the Flawed Frequency Transmission algorithm,
 * or FFT.
 *
 * As input, FFT takes a list of numbers. In the signal you received (your puzzle input), each number is a single
 * digit: data like 15243 represents the sequence 1, 5, 2, 4, 3.
 *
 * FFT operates in repeated phases. In each phase, a new list is constructed with the same length as the input list.
 * This new list is also used as the input for the next phase.
 *
 * Each element in the new list is built by multiplying every value in the input list by a value in a repeating
 * pattern and then adding up the results. So, if the input list were 9, 8, 7, 6, 5 and the pattern for a given element
 * were 1, 2, 3, the result would be 9*1 + 8*2 + 7*3 + 6*1 + 5*2 (with each input element on the left and each value in
 * the repeating pattern on the right of each multiplication). Then, only the ones digit is kept: 38 becomes
 * 8, -17 becomes 7, and so on.
 *
 * While each element in the output array uses all of the same input array elements, the actual repeating pattern to
 * use depends on which output element is being calculated. The base pattern is 0, 1, 0, -1. Then, repeat each value in
 * the pattern a number of times equal to the position in the output list being considered. Repeat once for the first
 * element, twice for the second element, three times for the third element, and so on. So, if the third element of the
 * output list is being calculated, repeating the values would produce: 0, 0, 0, 1, 1, 1, 0, 0, 0, -1, -1, -1.
 *
 * When applying the pattern, skip the very first value exactly once. (In other words, offset the whole pattern left by
 * one.) So, for the second element of the output list, the actual pattern used would be:
 * 0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1, ....
 *
 * After using this process to calculate each element of the output list, the phase is complete, and the output list of
 * this phase is used as the new input list for the next phase, if any.
 *
 * Given the input signal 12345678, below are four phases of FFT. Within each phase, each output digit is calculated
 * on a single line with the result at the far right; each multiplication operation shows the input digit on the left
 * and the pattern value on the right:
 *
 * Input signal: 12345678
 *
 * 1*1  + 2*0  + 3*-1 + 4*0  + 5*1  + 6*0  + 7*-1 + 8*0  = 4
 * 1*0  + 2*1  + 3*1  + 4*0  + 5*0  + 6*-1 + 7*-1 + 8*0  = 8
 * 1*0  + 2*0  + 3*1  + 4*1  + 5*1  + 6*0  + 7*0  + 8*0  = 2
 * 1*0  + 2*0  + 3*0  + 4*1  + 5*1  + 6*1  + 7*1  + 8*0  = 2
 * 1*0  + 2*0  + 3*0  + 4*0  + 5*1  + 6*1  + 7*1  + 8*1  = 6
 * 1*0  + 2*0  + 3*0  + 4*0  + 5*0  + 6*1  + 7*1  + 8*1  = 1
 * 1*0  + 2*0  + 3*0  + 4*0  + 5*0  + 6*0  + 7*1  + 8*1  = 5
 * 1*0  + 2*0  + 3*0  + 4*0  + 5*0  + 6*0  + 7*0  + 8*1  = 8
 *
 * After 1 phase: 48226158
 *
 * 4*1  + 8*0  + 2*-1 + 2*0  + 6*1  + 1*0  + 5*-1 + 8*0  = 3
 * 4*0  + 8*1  + 2*1  + 2*0  + 6*0  + 1*-1 + 5*-1 + 8*0  = 4
 * 4*0  + 8*0  + 2*1  + 2*1  + 6*1  + 1*0  + 5*0  + 8*0  = 0
 * 4*0  + 8*0  + 2*0  + 2*1  + 6*1  + 1*1  + 5*1  + 8*0  = 4
 * 4*0  + 8*0  + 2*0  + 2*0  + 6*1  + 1*1  + 5*1  + 8*1  = 0
 * 4*0  + 8*0  + 2*0  + 2*0  + 6*0  + 1*1  + 5*1  + 8*1  = 4
 * 4*0  + 8*0  + 2*0  + 2*0  + 6*0  + 1*0  + 5*1  + 8*1  = 3
 * 4*0  + 8*0  + 2*0  + 2*0  + 6*0  + 1*0  + 5*0  + 8*1  = 8
 *
 * After 2 phases: 34040438
 *
 * 3*1  + 4*0  + 0*-1 + 4*0  + 0*1  + 4*0  + 3*-1 + 8*0  = 0
 * 3*0  + 4*1  + 0*1  + 4*0  + 0*0  + 4*-1 + 3*-1 + 8*0  = 3
 * 3*0  + 4*0  + 0*1  + 4*1  + 0*1  + 4*0  + 3*0  + 8*0  = 4
 * 3*0  + 4*0  + 0*0  + 4*1  + 0*1  + 4*1  + 3*1  + 8*0  = 1
 * 3*0  + 4*0  + 0*0  + 4*0  + 0*1  + 4*1  + 3*1  + 8*1  = 5
 * 3*0  + 4*0  + 0*0  + 4*0  + 0*0  + 4*1  + 3*1  + 8*1  = 5
 * 3*0  + 4*0  + 0*0  + 4*0  + 0*0  + 4*0  + 3*1  + 8*1  = 1
 * 3*0  + 4*0  + 0*0  + 4*0  + 0*0  + 4*0  + 3*0  + 8*1  = 8
 *
 * After 3 phases: 03415518
 *
 * 0*1  + 3*0  + 4*-1 + 1*0  + 5*1  + 5*0  + 1*-1 + 8*0  = 0
 * 0*0  + 3*1  + 4*1  + 1*0  + 5*0  + 5*-1 + 1*-1 + 8*0  = 1
 * 0*0  + 3*0  + 4*1  + 1*1  + 5*1  + 5*0  + 1*0  + 8*0  = 0
 * 0*0  + 3*0  + 4*0  + 1*1  + 5*1  + 5*1  + 1*1  + 8*0  = 2
 * 0*0  + 3*0  + 4*0  + 1*0  + 5*1  + 5*1  + 1*1  + 8*1  = 9
 * 0*0  + 3*0  + 4*0  + 1*0  + 5*0  + 5*1  + 1*1  + 8*1  = 4
 * 0*0  + 3*0  + 4*0  + 1*0  + 5*0  + 5*0  + 1*1  + 8*1  = 9
 * 0*0  + 3*0  + 4*0  + 1*0  + 5*0  + 5*0  + 1*0  + 8*1  = 8
 *
 * After 4 phases: 01029498
 * Here are the first eight digits of the final output list after 100 phases for some larger inputs:
 *
 * 80871224585914546619083218645595 becomes 24176176.
 * 19617804207202209144916044189917 becomes 73745418.
 * 69317163492948606335995924319873 becomes 52432133.
 * After 100 phases of FFT, what are the first eight digits in the final output list?
 *
 * --- Part Two ---
 * Now that your FFT is working, you can decode the real signal.
 *
 * The real signal is your puzzle input repeated 10000 times. Treat this new signal as a single input list. Patterns
 * are still calculated as before, and 100 phases of FFT are still applied.
 *
 * The first seven digits of your initial input signal also represent the message offset. The message offset is the
 * location of the eight-digit message in the final output list. Specifically, the message offset indicates the number
 * of digits to skip before reading the eight-digit message. For example, if the first seven digits of your initial
 * input signal were 1234567, the eight-digit message would be the eight digits after skipping 1,234,567 digits of the
 * final output list. Or, if the message offset were 7 and your final output list were 98765432109876543210, the
 * eight-digit message would be 21098765. (Of course, your real message offset will be a seven-digit number, not a
 * one-digit number like 7.)
 *
 * Here is the eight-digit message in the final output list after 100 phases. The message offset given in each input
 * has been highlighted. (Note that the inputs given below are repeated 10000 times to find the actual starting input
 * lists.)
 *
 * 03036732577212944063491565474664 becomes 84462026.
 * 02935109699940807407585447034323 becomes 78725270.
 * 03081770884921959731165446850517 becomes 53553731.
 * After repeating your input signal 10000 times and running 100 phases of FFT, what is the eight-digit message
 * embedded in the final output list?
 */
public class Day16 {

    /**
     * Take the string and convert it to an int array for faster processing
     * @param input Input to convert to int array
     * @return Int array converted from the string
     */
    public int[] convertToIntArray(String input) {
        //Convert to a set of int's to make it quicker to process
        int[] inputWeCareAboutAsInts = new int[input.length()];
        int curIndex = 0;
        for(char inputWeCareAboutChar : input.toCharArray()) {
            inputWeCareAboutAsInts[curIndex] = Integer.parseInt(String.valueOf(inputWeCareAboutChar));
            curIndex++;
        }
        return inputWeCareAboutAsInts;
    }

    /**
     * Given an amount of repeats this generates the repeating pattern needed for the wonky FFT
     * @param amountOfRepeats Number of times to repeat the FFT pattern
     * @return The final pattern
     */
    public int[] getRepeatingPattern(int amountOfRepeats) {
        int[] basePattern = new int[] {0, 1, 0, -1};
        List<Integer> lists = new ArrayList<>();
        for(int i: basePattern) {
            for(int num = 0; num < amountOfRepeats; num++) {
                lists.add(i);
            }
        }

        int[] actualPattern = new int[lists.size()];
        for(int indexInts = 0; indexInts < lists.size(); indexInts++) {
            actualPattern[indexInts] = lists.get(indexInts);
        }

        return actualPattern;
    }

    /**
     * Runs the FFT correction and returns the first eight characters of the final FFT processed string
     * @param input Initial input
     * @return First eight characters of the FFT processed string
     */
    public long solvePartOne(String input) {
        int[] intArray = this.convertToIntArray(input);

        //Run the process 100 times
        for(int numberOfProcesses = 0; numberOfProcesses < 100; numberOfProcesses++) {
            int[] newInput = new int[intArray.length];
            for(int indexInput = 0; indexInput < intArray.length; indexInput++) {
                int[] repeatingPattern = this.getRepeatingPattern(indexInput + 1);
                long newValue = 0;
                int patternIndex = 1;
                //Run the multiplication against the repeating pattern and sum the value
                for(int insideInputIndex = 0; insideInputIndex < intArray.length; insideInputIndex++) {
                    long newCalculatedValue = intArray[insideInputIndex] * repeatingPattern[patternIndex];
                    newValue += newCalculatedValue;
                    patternIndex++;
                    if(patternIndex >= repeatingPattern.length) {
                        patternIndex = 0;
                    }
                }

                newInput[indexInput] = (int)Math.abs(newValue % 10);
            }

            intArray = newInput;
        }

        //Then work out the final string from the int's
        StringBuilder finalInt = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            finalInt.append(intArray[i]);
        }

        return Long.parseLong(finalInt.toString());
    }

    /**
     * This runs the FFT processing on the input after expanding it massively.
     *
     * Note: Since the offset value is always greater than half of the length, it means that from the repeating pattern
     * [0, 1, 0, -1] we only actually handle [0, 1]. Since we only care about the offset string we can ignore all
     * the previous digits since they call into the 0, and then the rest are just 1. Which means we can just sum them up.
     *
     * Even better, if we do that in reverse order, we can slowly accumulate the number backwards working out the digit
     * one by one. This makes it actually feasible to calculate
     *
     * @param input Initial input
     * @return First eight characters of the FFT processed string
     */
    public long solvePartTwo(String input) {
        int repeatVal = 10000;
        //First work out the actual string (by just repeating it)
        String actualInput = input.repeat(repeatVal);
        //Then work out the offset from the initial input
        int offset = Integer.parseInt(actualInput.substring(0, 7));

        //The "hack" only works if the offset is halfway or more into the string, so crash out if this isn't true
        //It will be true for all advent of code strings
        if(offset < (repeatVal / 2)) {
            throw new RuntimeException("This 'trick' only works when the offset is greater than half of the length of the string");
        }

        //We can then just cut off all the string we don't care about by removing everything before the offset
        String inputWeCareAbout = actualInput.substring(offset);
        //Convert to a set of int's to make it quicker to process
        int[] inputWeCareAboutAsInts = this.convertToIntArray(inputWeCareAbout);

        //Run the process 100 times
        for(int numberOfProcesses = 0; numberOfProcesses < 100; numberOfProcesses++) {
            int finalNum = 0;
            int[] newInput = new int[inputWeCareAboutAsInts.length];
            for(int indexInput = inputWeCareAboutAsInts.length-1; indexInput >=0; indexInput--) {
                finalNum += inputWeCareAboutAsInts[indexInput];
                finalNum = finalNum % 10;
                newInput[indexInput] = finalNum;
            }
            inputWeCareAboutAsInts = newInput;
        }

        //Then work out the final string from the int's
        StringBuilder finalInt = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            finalInt.append(inputWeCareAboutAsInts[i]);
        }

        return Long.parseLong(finalInt.toString());
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2019, 16);

        Day16 d = new Day16();
        long partOne = d.solvePartOne(input);
        System.out.println("The first eight digits of the input after 100 FFT transformations is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("After tweaking the input the first eight digits of the input is " + partTwo);
    }
}


