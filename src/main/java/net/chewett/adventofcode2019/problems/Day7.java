package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.PermutationGenerator;
import net.chewett.adventofcode2019.intcode.Intcode;
import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.instructions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Taken from: https://adventofcode.com/2019/day/7
 *
 *
 */
public class Day7 {

    public static void main(String[] args) {

        try {
            File file = new File("day_7_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            //Day seven input is a single line, so just load that
            String thrusterProgramExample = br.readLine();
            List<Integer> thrusterValues = new ArrayList<>();
            thrusterValues.add(0);
            thrusterValues.add(1);
            thrusterValues.add(2);
            thrusterValues.add(3);
            thrusterValues.add(4);

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
                int currentInput = 0;
                for (int i = 0; i < amplifiers.length; i++) {
                    amplifiers[i].addToInput(thrusterPhase.get(i));
                    amplifiers[i].addToInput(currentInput);
                    amplifiers[i].initIntcode(ic);
                    amplifiers[i].runIntcode();
                    currentInput = amplifiers[i].getOutput();
                }

                maxThrustSignal = Math.max(maxThrustSignal, currentInput);
            }

            System.out.println("Found max thrust: " + maxThrustSignal);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

}
