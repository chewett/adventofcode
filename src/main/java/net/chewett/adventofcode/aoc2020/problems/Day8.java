package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 {

    /**
     * Run the instructions until we get to a point where the program tries to run the same instruction a second time
     * @param instructions Set of instructions to run
     * @return The accumulator value at the point that a second instructions
     */
    public int solvePartOne(List<String> instructions) {
        int acc = 0;
        int curInstruction = 0;
        //Keep track of the instruction numbers we have already seen
        Set<Integer> instructionsRan = new HashSet<>();
        //Only continue running while we are running the instruction
        while(!instructionsRan.contains(curInstruction)) {
            String[] insParts = instructions.get(curInstruction).split(" ");
            if(insParts[0].equals("nop")) {
                //Do nothing
            }else if(insParts[0].equals("acc")) {
                acc += Integer.parseInt(insParts[1].replace("+", ""));
            } else if (insParts[0].equals("jmp")) {
                curInstruction += Integer.parseInt(insParts[1].replace("+", "")) - 1;
            }

            instructionsRan.add(curInstruction);
            curInstruction++;
        }


        return acc;
    }

    /**
     * Run the program modifying a single instruction at a time, until we find one that ends
     * @param instructions List of "base" instructions that has a single incorrect instruction
     * @return Returns the result of the accumulator when the program is corrected, returns -1 if there was an issue
     */
    public int solvePartTwo(List<String> instructions) {
        for(int i = 0; i < instructions.size(); i++) {
            //If the instruction is acc, we don't change anything so it wont "improve" the program so skip to the next one
            if(instructions.get(i).equals("acc")) {
                continue;
            }

            int acc = 0;
            int curInstruction = 0;
            Set<Integer> instructionsRan = new HashSet<>();
            while(!instructionsRan.contains(curInstruction) && curInstruction != instructions.size()) {
                instructionsRan.add(curInstruction);
                String[] insParts = instructions.get(curInstruction).split(" ");
                String instructionToRun = insParts[0];

                if(curInstruction == i) {
                    if(instructionToRun.equals("nop")) {
                        instructionToRun = "jmp";
                    }else if(instructionToRun.equals("jmp")) {
                        instructionToRun = "nop";
                    }
                }

                if(instructionToRun.equals("nop")) {
                    //Do nothing
                }else if(instructionToRun.equals("acc")) {
                    acc += Integer.parseInt(insParts[1].replace("+", ""));
                } else if (instructionToRun.equals("jmp")) {
                    curInstruction += Integer.parseInt(insParts[1].replace("+", "")) - 1;
                }

                curInstruction++;
            }

            //If the instruction to run is "off the end" of the instructions, then it finished properly!
            if(curInstruction == instructions.size()) {
                return acc;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Day8 d = new Day8();
        List<String> instructions = ProblemLoader.loadProblemIntoStringArray(2020, 8);

        int accValueForPartOne = d.solvePartOne(instructions);
        System.out.println("Accumulator value when looping instruction found: " + accValueForPartOne);

        int accValueForPartTwo = d.solvePartTwo(instructions);
        System.out.println("Accumulator value after fixing jump found: " + accValueForPartTwo);

    }

}
