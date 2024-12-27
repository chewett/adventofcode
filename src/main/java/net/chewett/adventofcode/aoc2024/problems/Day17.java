package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;
import java.util.List;

/**
 * --- Day 17: Chronospatial Computer ---
 * The Historians push the button on their strange device, but this time, you all just feel like you're falling.
 *
 * "Situation critical", the device announces in a familiar voice. "Bootstrapping process failed. Initializing
 * debugger...."
 *
 * The small handheld device suddenly unfolds into an entire computer! The Historians look around nervously before one
 * of them tosses it to you.
 *
 * This seems to be a 3-bit computer: its program is a list of 3-bit numbers (0 through 7), like 0,1,2,3. The computer
 * also has three registers named A, B, and C, but these registers aren't limited to 3 bits and can instead hold any
 * integer.
 *
 * The computer knows eight instructions, each identified by a 3-bit number (called the instruction's opcode). Each
 * instruction also reads the 3-bit number after it as an input; this is called its operand.
 *
 * A number called the instruction pointer identifies the position in the program from which the next opcode will
 * be read; it starts at 0, pointing at the first 3-bit number in the program. Except for jump instructions, the
 * instruction pointer increases by 2 after each instruction is processed (to move past the instruction's opcode and
 * its operand). If the computer tries to read an opcode past the end of the program, it instead halts.
 *
 * So, the program 0,1,2,3 would run the instruction whose opcode is 0 and pass it the operand 1, then run the
 * instruction having opcode 2 and pass it the operand 3, then halt.
 *
 * There are two types of operands; each instruction specifies the type of its operand. The value of a literal
 * operand is the operand itself. For example, the value of the literal operand 7 is the number 7. The value of a
 * combo operand can be found as follows:
 *
 * Combo operands 0 through 3 represent literal values 0 through 3.
 * Combo operand 4 represents the value of register A.
 * Combo operand 5 represents the value of register B.
 * Combo operand 6 represents the value of register C.
 * Combo operand 7 is reserved and will not appear in valid programs.
 * The eight instructions are as follows:
 *
 * The adv instruction (opcode 0) performs division. The numerator is the value in the A register. The denominator is
 * found by raising 2 to the power of the instruction's combo operand. (So, an operand of 2 would divide A by 4 (2^2);
 * an operand of 5 would divide A by 2^B.) The result of the division operation is truncated to an integer and then
 * written to the A register.
 *
 * The bxl instruction (opcode 1) calculates the bitwise XOR of register B and the instruction's literal operand,
 * then stores the result in register B.
 *
 * The bst instruction (opcode 2) calculates the value of its combo operand modulo 8 (thereby keeping only its lowest
 * 3 bits), then writes that value to the B register.
 *
 * The jnz instruction (opcode 3) does nothing if the A register is 0. However, if the A register is not zero, it jumps
 * by setting the instruction pointer to the value of its literal operand; if this instruction jumps, the instruction
 * pointer is not increased by 2 after this instruction.
 *
 * The bxc instruction (opcode 4) calculates the bitwise XOR of register B and register C, then stores the result in
 * register B. (For legacy reasons, this instruction reads an operand but ignores it.)
 *
 * The out instruction (opcode 5) calculates the value of its combo operand modulo 8, then outputs that value. (If a
 * program outputs multiple values, they are separated by commas.)
 *
 * The bdv instruction (opcode 6) works exactly like the adv instruction except that the result is stored in the B
 * register. (The numerator is still read from the A register.)
 *
 * The cdv instruction (opcode 7) works exactly like the adv instruction except that the result is stored in the C
 * register. (The numerator is still read from the A register.)
 *
 * Here are some examples of instruction operation:
 *
 * If register C contains 9, the program 2,6 would set register B to 1.
 * If register A contains 10, the program 5,0,5,1,5,4 would output 0,1,2.
 * If register A contains 2024, the program 0,1,5,4,3,0 would output 4,2,5,6,7,7,7,7,3,1,0 and leave 0 in register A.
 * If register B contains 29, the program 1,7 would set register B to 26.
 * If register B contains 2024 and register C contains 43690, the program 4,0 would set register B to 44354.
 * The Historians' strange device has finished initializing its debugger and is displaying some information about the
 * program it is trying to run (your puzzle input). For example:
 *
 * Register A: 729
 * Register B: 0
 * Register C: 0
 *
 * Program: 0,1,5,4,3,0
 * Your first task is to determine what the program is trying to output. To do this, initialize the registers to the
 * given values, then run the given program, collecting any output produced by out instructions. (Always join the
 * values produced by out instructions with commas.) After the above program halts, its final output will
 * be 4,6,3,5,6,3,5,2,1,0.
 *
 * Using the information provided by the debugger, initialize the registers to the given values, then run the program.
 * Once it halts, what do you get if you use commas to join the values it output into a single string?
 *
 * --- Part Two ---
 * Digging deeper in the device's manual, you discover the problem: this program is supposed to output another copy of
 * the program! Unfortunately, the value in register A seems to have been corrupted. You'll need to find a new value
 * to which you can initialize register A so that the program's output instructions produce an exact copy of the
 * program itself.
 *
 * For example:
 *
 * Register A: 2024
 * Register B: 0
 * Register C: 0
 *
 * Program: 0,3,5,4,3,0
 * This program outputs a copy of itself if register A is instead initialized to 117440. (The original initial value
 * of register A, 2024, is ignored.)
 *
 * What is the lowest positive initial value for register A that causes the program to output a copy of itself?
 */
public class Day17 {

    /**
     * Simple function to run the program
     * @param regA Initial value for register A
     * @param regB Initial value for register B
     * @param regC Initial value for register C
     * @param program The program to run
     * @return The list of output integers
     */
    public List<Integer> runProgram(long regA, long regB, long regC, List<Pair<Integer>> program) {
        List<Integer> output = new ArrayList<>();

        int instructionNo = 0;
        while(instructionNo < program.size()) {
            Pair<Integer> instruction = program.get(instructionNo);

            //Nasty stuff...
            long comboVal = Long.MIN_VALUE;
            if(instruction.b == 0) {
                comboVal = 0;
            }else if(instruction.b == 1) {
                comboVal = 1;
            }else if(instruction.b == 2) {
                comboVal = 2;
            }else if(instruction.b == 3) {
                comboVal = 3;
            }else if(instruction.b == 4) {
                comboVal = regA;
            }else if(instruction.b == 5) {
                comboVal = regB;
            }else if(instruction.b == 6) {
                comboVal = regC;
            }else if(instruction.b == 7) {
                if(instruction.a == 0 || instruction.a == 2 || instruction.a == 5 || instruction.a == 6 || instruction.a == 7) {
                    throw new RuntimeException("Invalid instruction: " + instruction.a + " " + instruction.b);
                }
            }

            int literalVal = instruction.b;

            boolean jump = false;

            //0 = adv = division of regA and save to regA
            if(instruction.a == 0) {
                regA = regA >> comboVal;

                //1 = bxl = bitwise xor
            }else if(instruction.a == 1) {
                regB = regB ^ literalVal;

                //2 = bst = combo mod 8
            }else if(instruction.a == 2) {
                regB = comboVal % 8;

                //3 = jnz = jump to literal value if reg A not zero
            }else if(instruction.a == 3) {
                if(regA != 0) {
                    instructionNo = literalVal;
                    jump = true;
                }

                //4 = bxc = bitwise xor B + C
            }else if(instruction.a == 4) {
                regB = regB ^ regC;

                //5 = out = combo mod
            }else if(instruction.a == 5) {
                int outputData = (int)(comboVal % 8);
                output.add(outputData);

                //6 = bdv = division of regA and save to regB
            }else if(instruction.a == 6) {
                regB = regA >> comboVal;

                //7 = cdv = division of regA and save to regC
            }else if(instruction.a == 7) {
                regC = regA >> comboVal;
            }

            if(!jump) {
                instructionNo++;
            }
        }

        return output;
    }


    /**
     * Solves part one just by running the program
     * @param input Input to parse
     * @return String representing the output
     */
    public String solvePartOne(List<String> input) {
        long regA = Long.parseLong(input.get(0).split(": ")[1]);
        long regB = Long.parseLong(input.get(1).split(": ")[1]);
        long regC = Long.parseLong(input.get(2).split(": ")[1]);

        String[] programStr = input.get(4).split(": ")[1].split(",");

        List<Pair<Integer>> program = new ArrayList<>();
        for(int i = 0; i < programStr.length; i+=2){
            program.add(new Pair<>(Integer.parseInt(programStr[i]), Integer.parseInt(programStr[i+1])));
        }

        List<Integer> output = this.runProgram(regA, regB, regC, program);

        String finalOut = "";
        for (Integer i : output) {
            finalOut += i + ",";
        }
        finalOut = finalOut.substring(0, finalOut.length()-1);

        return finalOut;
    }

    //Part two uses these cached values to reduce the amount of data we pass around
    //This reduces the weight of the recursive calls somewhat
    //TODO: abstract these variables out somewhere nicer possibly (like it's own class)
    public List<Pair<Integer>> program;
    public List<Integer> wanted;
    public long bRegister;
    public long cRegister;

    /**
     * Given a thing we want to check and the expected length, we verify if this is the end values of the wanted list
     * @param thingToCheck Thing to check
     * @param expectedLength Length we expect this to be
     * @return True if this matches the end, otherwise false
     */
    public boolean matchEnd(List<Integer> thingToCheck, int expectedLength) {
        //Handle the fact we should have generated more digits, but we didn't
        if(thingToCheck.size() != expectedLength) {
            return false;
        }

        //Halting condition for generating numbers too big
        if(thingToCheck.size() > wanted.size()) {
            return false;
        }

        //Work out the region to start from in the wanted string
        int startX = wanted.size() - thingToCheck.size();
        //Then just compare val by val
        for(int i = 0; i < thingToCheck.size(); i++) {
            if(thingToCheck.get(i) != this.wanted.get(startX+i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Here is the lovely recursive function to incrementally run the program and see if we generated an output character
     * @param carryValue Value to carry over each time accumulating ints
     * @param numberOfChars The number of characters we are expecting to generate
     * @return The "carry number" for the number of characters we have found or -1 if we didn't find anything
     */
    public long findTheEndOfTheProgramRecursively(long carryValue, int numberOfChars) {
        //Run the program and check to see if we have found the solution
        List<Integer> output = this.runProgram(carryValue, this.bRegister, this.cRegister, this.program);
        if (output.equals(this.wanted)) {
            //Yay finally!
            return carryValue;
        }

        //Otherwise try and generate more data
        // We can recurse lower if:
        //  * The number of chars is zero we are just starting, so always try 0 -> 7
        //  * We matched the end of the wanted string (and therefore are getting closer to the right string)
        if(numberOfChars == 0 || this.matchEnd(output, numberOfChars)) {
            for(int n = 0; n < 8; n++) {
                //Each time multiply the carry by 8 to shift it right (could bitshift here)
                long out = this.findTheEndOfTheProgramRecursively(8*carryValue + n, numberOfChars+1);
                //The recursive function returns the value when we found it, otherwise returns -1.
                if(out != -1) {
                    return out;
                }
            }
        }

        return -1;
    }

    /**
     * Attempts to "reverse engineer" the input needed for the program.
     *
     * This works by the fact that each of the digits are generated by 3 bits of data which is then shifted way (divided)
     * and therefore each 3 bits represents a number.
     * @param input Program and the register details
     * @return The input for register A that is needed to generate the program as an output
     */
    public long solvePartTwo(List<String> input) {
        this.bRegister = Long.parseLong(input.get(1).split(": ")[1]);
        this.cRegister = Long.parseLong(input.get(2).split(": ")[1]);

        String[] programStr = input.get(4).split(": ")[1].split(",");
        List<Integer> intendedOutput = new ArrayList<>();

        List<Pair<Integer>> program = new ArrayList<>();
        for(int i = 0; i < programStr.length; i+=2){
            intendedOutput.add(Integer.parseInt(programStr[i]));
            intendedOutput.add(Integer.parseInt(programStr[i+1]));
            program.add(new Pair<>(Integer.parseInt(programStr[i]), Integer.parseInt(programStr[i+1])));
        }
        this.program = program;
        this.wanted = intendedOutput;

        return this.findTheEndOfTheProgramRecursively(0, 0);
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 17);

        Day17 d = new Day17();
        String partOne = d.solvePartOne(input);
        System.out.println("The output after running is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The value for register A that generates the program is " + partTwo);
    }
}


