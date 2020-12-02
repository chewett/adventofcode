package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day19 {

    public void solve() {
        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_19_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String tractorBeamRobot = br.readLine();

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
            Intcode ic = new Intcode(tractorBeamRobot);
            int tractorBeamPointPulling = 0;


            int across_distance = 50;
            for(int y = 0; y < across_distance; y++) {
                for(int x = 0; x < across_distance; x++) {

                    icc.initIntcode(ic);
                    icc.addToInput(x);
                    icc.addToInput(y);
                    icc.runIntcode();

                    if(!icc.hasOutputToRead()) {
                        System.out.println("Awaiting input? " + icc.isComputationAwaitingInput());
                        System.out.println("Finished? " + icc.isComputationEntirelyFinished());
                    }

                    int output = (int)icc.getOutput();
                    System.out.println(x +", " + y + " - " + output);

                    if(output == 1L) {
                        tractorBeamPointPulling++;
                    }
                }
            }

            System.out.println("Number of points the tractor beam is finding " + tractorBeamPointPulling);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       Day19 d = new Day19();
       d.solve();
    }
}
