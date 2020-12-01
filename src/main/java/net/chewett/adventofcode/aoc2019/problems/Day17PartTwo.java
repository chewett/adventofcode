package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day17PartTwo {

    public static void main(String[] args) {

        try {
            File file = new File("day_17_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String oxygenRobot = br.readLine();

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
            Intcode ic = new Intcode(oxygenRobot);
            icc.initIntcode(ic);
            icc.runIntcode();

            List<String> rows = new ArrayList<>();

            String curRow = "";
            while(icc.hasOutputToRead()) {
                long output = icc.getOutput();

                if(output == 10) {
                    rows.add(curRow);
                    curRow = "";
                }else{
                    curRow += ((char)output);
                }
            }

           for(String s : rows) {
               System.out.println(s);
           }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
