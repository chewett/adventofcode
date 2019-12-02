package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.intcode.Intcode;
import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.instructions.AddInstruction;
import net.chewett.adventofcode2019.intcode.instructions.FinishInstruction;
import net.chewett.adventofcode2019.intcode.instructions.IntcodeInstruction;
import net.chewett.adventofcode2019.intcode.instructions.MultiplyInstruction;
import net.chewett.adventofcode2019.spaceship.Spaceship;
import net.chewett.adventofcode2019.spaceship.SpaceshipModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static void main(String[] argv) {
        try {
            File file = new File("day_2_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            //Day two input is a single line, so just load that
            String st = br.readLine();

            //Set up my Instruction set
            List<IntcodeInstruction> instructions = new ArrayList<>();
            instructions.add(new FinishInstruction());
            instructions.add(new AddInstruction());
            instructions.add(new MultiplyInstruction());

            //Init the computer so its ready
            IntcodeComputer icc = new IntcodeComputer(instructions);

            //Load the intcode and then modify it for the starting problem
            Intcode ic = new Intcode(st);
            ic.setIntToAddress(1, 12);
            ic.setIntToAddress(2, 2);
            int finalResult = icc.runIntcode(ic);

            System.out.println("Finished processing the input, the result is: " + finalResult);

        }catch(IOException e) {
            e.printStackTrace();
        }







    }
}
