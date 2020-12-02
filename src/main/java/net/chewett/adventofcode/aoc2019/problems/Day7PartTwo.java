package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.PermutationGenerator;
import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Taken from: https://adventofcode.com/2019/day/7
 *
 * --- Part Two ---
 * It's no good - in this configuration, the amplifiers can't generate a large enough output signal to produce the thrust you'll need. The Elves quickly talk you through rewiring the amplifiers into a feedback loop:
 *
 *       O-------O  O-------O  O-------O  O-------O  O-------O
 * 0 -+->| Amp A |->| Amp B |->| Amp C |->| Amp D |->| Amp E |-.
 *    |  O-------O  O-------O  O-------O  O-------O  O-------O |
 *    |                                                        |
 *    '--------------------------------------------------------+
 *                                                             |
 *                                                             v
 *                                                      (to thrusters)
 * Most of the amplifiers are connected as they were before; amplifier A's output is connected to amplifier B's input, and so on. However, the output from amplifier E is now connected into amplifier A's input. This creates the feedback loop: the signal will be sent through the amplifiers many times.
 *
 * In feedback loop mode, the amplifiers need totally different phase settings: integers from 5 to 9, again each used exactly once. These settings will cause the Amplifier Controller Software to repeatedly take input and produce output many times before halting. Provide each amplifier its phase setting at its first input instruction; all further input/output instructions are for signals.
 *
 * Don't restart the Amplifier Controller Software on any amplifier during this process. Each one should continue receiving and sending signals until it halts.
 *
 * All signals sent or received in this process will be between pairs of amplifiers except the very first signal and the very last signal. To start the process, a 0 signal is sent to amplifier A's input exactly once.
 *
 * Eventually, the software on the amplifiers will halt after they have processed the final loop. When this happens, the last output signal from amplifier E is sent to the thrusters. Your job is to find the largest output signal that can be sent to the thrusters using the new phase settings and feedback loop arrangement.
 *
 * Here are some example programs:
 *
 * Max thruster signal 139629729 (from phase setting sequence 9,8,7,6,5):
 *
 * 3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
 * 27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5
 * Max thruster signal 18216 (from phase setting sequence 9,7,8,5,6):
 *
 * 3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,
 * -5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,
 * 53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10
 * Try every combination of the new phase settings on the amplifier feedback loop. What is the highest signal that can be sent to the thrusters?
 */
public class Day7PartTwo {

    public void solve() {
        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_7_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            //Day seven input is a single line, so just load that
            String thrusterProgramExample = br.readLine();
            List<Integer> thrusterValues = new ArrayList<>();

            thrusterValues.add(5);
            thrusterValues.add(6);
            thrusterValues.add(7);
            thrusterValues.add(8);
            thrusterValues.add(9);

            List<List<Integer>> thrusterPhaseCombinations = PermutationGenerator.generatePermutations(thrusterValues);

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

                Intcode ic = new Intcode(thrusterProgramExample);
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

            System.out.println("Found max thrust: " + maxThrustSignal);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Day7PartTwo d = new Day7PartTwo();
    }

}
