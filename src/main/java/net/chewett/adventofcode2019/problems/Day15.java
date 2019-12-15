package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.intcode.Intcode;
import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.instructions.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

    public static void main(String[] args) {
        try {
            File file = new File("day_15_input.txt");
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

            Map<Point, Integer> map = new HashMap<>();
            map.put(new Point(0, 0), 1);
            int x = 0;
            int y = 0;

            boolean notFound = true;
            while(notFound) {
                icc.addToInput(1);
                icc.runIntcode();
                long moveResult = icc.getOutput();
                if(moveResult == 0) {
                    //TODO: handle the fact that this cant move in this position.
                }

                System.out.println(moveResult);

                if(moveResult == 0)
                    notFound = false;
            }





        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
