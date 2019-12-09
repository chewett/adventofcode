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

public class Day9 {


    public static void main(String[] args) {

        try {
            File file = new File("day_9_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            //Day seven input is a single line, so just load that
            String boostProgram = br.readLine();

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
            //This outputs itself
            String intcodeString = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";

            Intcode ic = new Intcode(boostProgram);
            icc.initIntcode(ic);
            //icc.addToInput(1);
            icc.addToInput(2);
            icc.runIntcode();

            System.out.println("Printing out all output");
            while(icc.hasOutputToRead()) {
                System.out.println(icc.getOutput());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
