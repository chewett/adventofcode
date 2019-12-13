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

public class Day13 {

    public static void main(String[] args) {

        try {
            File file = new File("day_13_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            //Day eleven input is a single line, so just load that
            String arcadeGameIntcode = br.readLine();

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

            Map<Point, Long> screenData = new HashMap<>();

            IntcodeComputer icc = new IntcodeComputer(instructions);
            Intcode ic = new Intcode(arcadeGameIntcode);
            icc.initIntcode(ic);
            icc.runIntcode();

            while(icc.hasOutputToRead()) {
                Point curPoint = new Point((int)icc.getOutput(), (int)icc.getOutput());
                screenData.put(curPoint, icc.getOutput());
            }

            int c = 0;
            for(Map.Entry<Point, Long> e : screenData.entrySet()) {
                if(e.getValue() == 2) {
                    c++;
                }
            }

            System.out.println(c);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
