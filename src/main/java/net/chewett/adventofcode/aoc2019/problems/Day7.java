package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.helpers.PermutationGenerator;
import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/7
 * Go have a try yourself!
 *
 * --- Day 7: Amplification Circuit ---
 * Based on the navigational maps, you're going to need to send more power to your ship's thrusters to reach Santa in
 * time. To do this, you'll need to configure a series of amplifiers already installed on the ship.
 *
 * There are five amplifiers connected in series; each one receives an input signal and produces an output signal.
 * They are connected such that the first amplifier's output leads to the second amplifier's input, the second
 * amplifier's output leads to the third amplifier's input, and so on. The first amplifier's input value is 0, and the
 * last amplifier's output leads to your ship's thrusters.
 *
 *     O-------O  O-------O  O-------O  O-------O  O-------O
 * 0 ->| Amp A |->| Amp B |->| Amp C |->| Amp D |->| Amp E |-> (to thrusters)
 *     O-------O  O-------O  O-------O  O-------O  O-------O
 * The Elves have sent you some Amplifier Controller Software (your puzzle input), a program that should run on your
 * existing Intcode computer. Each amplifier will need to run a copy of the program.
 *
 * When a copy of the program starts running on an amplifier, it will first use an input instruction to ask the
 * amplifier for its current phase setting (an integer from 0 to 4). Each phase setting is used exactly once, but the
 * Elves can't remember which amplifier needs which phase setting.
 *
 * The program will then call another input instruction to get the amplifier's input signal, compute the correct output
 * signal, and supply it back to the amplifier with an output instruction. (If the amplifier has not yet received an
 * input signal, it waits until one arrives.)
 *
 * Your job is to find the largest output signal that can be sent to the thrusters by trying every possible combination
 * of phase settings on the amplifiers. Make sure that memory is not shared or reused between copies of the program.
 *
 * For example, suppose you want to try the phase setting sequence 3,1,2,4,0, which would mean setting amplifier A to
 * phase setting 3, amplifier B to setting 1, C to 2, D to 4, and E to 0. Then, you could determine the output signal
 * that gets sent from amplifier E to the thrusters with the following steps:
 *
 * Start the copy of the amplifier controller software that will run on amplifier A. At its first input instruction,
 * provide it the amplifier's phase setting, 3. At its second input instruction, provide it the input signal, 0. After
 * some calculations, it will use an output instruction to indicate the amplifier's output signal.
 * Start the software for amplifier B. Provide it the phase setting (1) and then whatever output signal was produced
 * from amplifier A. It will then produce a new output signal destined for amplifier C.
 * Start the software for amplifier C, provide the phase setting (2) and the value from amplifier B, then collect its
 * output signal.
 * Run amplifier D's software, provide the phase setting (4) and input value, and collect its output signal.
 * Run amplifier E's software, provide the phase setting (0) and input value, and collect its output signal.
 * The final output signal from amplifier E would be sent to the thrusters. However, this phase setting sequence may
 * not have been the best one; another sequence might have sent a higher signal to the thrusters.
 *
 * Here are some example programs:
 *
 * Max thruster signal 43210 (from phase setting sequence 4,3,2,1,0):
 *
 * 3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0
 * Max thruster signal 54321 (from phase setting sequence 0,1,2,3,4):
 *
 * 3,23,3,24,1002,24,10,24,1002,23,-1,23,
 * 101,5,23,23,1,24,23,23,4,23,99,0,0
 * Max thruster signal 65210 (from phase setting sequence 1,0,4,3,2):
 *
 * 3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,
 * 1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0
 * Try every combination of phase settings on the amplifiers. What is the highest signal that can be sent to the
 * thrusters?
 *
 */
public class Day7 {

    public long solvePartOne(String input) {
        //Day seven input is a single line, so just load that
        List<Integer> thrusterValues = new ArrayList<>();
        thrusterValues.add(0);
        thrusterValues.add(1);
        thrusterValues.add(2);
        thrusterValues.add(3);
        thrusterValues.add(4);

        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> thrusterPhaseCombinations = pg.generatePermutations(thrusterValues);

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

        int maxThrustSignal = 0;
        for (List<Integer> thrusterPhase : thrusterPhaseCombinations) {

            //Init the computer so its ready
            IntcodeComputer[] amplifiers = new IntcodeComputer[]{
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
            };

            Intcode ic = new Intcode(input);
            long currentInput = 0;
            for (int i = 0; i < amplifiers.length; i++) {
                amplifiers[i].addToInput(thrusterPhase.get(i));
                amplifiers[i].addToInput(currentInput);
                amplifiers[i].initIntcode(ic);
                amplifiers[i].runIntcode();
                currentInput = amplifiers[i].getOutput();
            }

            maxThrustSignal = Math.max((int)maxThrustSignal, (int)currentInput);
        }

        return maxThrustSignal;
    }


    public long solvePartTwo(String input) {
        List<Integer> thrusterValues = new ArrayList<>();

        thrusterValues.add(5);
        thrusterValues.add(6);
        thrusterValues.add(7);
        thrusterValues.add(8);
        thrusterValues.add(9);

        PermutationGenerator<Integer> pg = new PermutationGenerator<>();
        List<List<Integer>> thrusterPhaseCombinations = pg.generatePermutations(thrusterValues);

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

        int maxThrustSignal = 0;
        for (List<Integer> thrusterPhase : thrusterPhaseCombinations) {
            //Init the computer so its ready
            IntcodeComputer[] amplifiers = new IntcodeComputer[]{
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
                    new IntcodeComputer(instructions),
            };

            Intcode ic = new Intcode(input);
            //Set up all the amplifiers
            for (int i = 0; i < amplifiers.length; i++) {
                amplifiers[i].addToInput(thrusterPhase.get(i));
                amplifiers[i].initIntcode(ic);
            }

            boolean finalAmpIsFinished = false;
            long currentInput = 0;
            while (!finalAmpIsFinished) {
                for (IntcodeComputer amplifier : amplifiers) {
                    amplifier.addToInput(currentInput);
                    amplifier.runIntcode();
                    currentInput = amplifier.getOutput();
                }

                if (amplifiers[4].isComputationEntirelyFinished()) {
                    finalAmpIsFinished = true;
                }
            }
            maxThrustSignal = Math.max((int)maxThrustSignal, (int)currentInput);
        }

        return maxThrustSignal;
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2019, 7);

        Day7 d = new Day7();
        long partOne = d.solvePartOne(input);
        System.out.println("Found max thrust: " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("Found max thrust: " + partTwo);
    }
}


