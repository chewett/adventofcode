package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day17 {

    public void solve() {

        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_17_input.txt").getFile());
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

            int totalCombinations = 0;
            for(int y = 1; y < rows.size() - 1; y++) {
                for(int x = 1; x < rows.get(y).length() - 1; x++) {
                    if(rows.get(y).substring(x, x+1).equals("#")) {
                        if(
                                rows.get(y).substring(x-1, x).equals("#") &&
                                        rows.get(y).substring(x+1, x+2).equals("#") &&
                                        rows.get(y-1).substring(x, x+1).equals("#") &&
                                        rows.get(y+1).substring(x, x+1).equals("#")
                        ){
                            totalCombinations += x * y;
                        }
                    }
                }
            }

            System.out.println("Total combinations: " + totalCombinations);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Day17 d = new Day17();
        d.solve();
    }

}
