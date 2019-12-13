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

public class Day13PartTwo {

    public static void printScreen(Map<Point, Long> screen) {
        int maxX = 0;
        int minX = 9999;
        int maxY = 0;
        int minY = 9999;
        System.out.println("");
        System.out.println("SCREEN");

        for(Map.Entry<Point, Long> e : screen.entrySet()) {
            maxX = Math.max(maxX, e.getKey().x);
            minX = Math.min(minX, e.getKey().x);
            maxY = Math.max(maxY, e.getKey().y);
            minY = Math.min(minY, e.getKey().y);
        }

        for(int y = minY; y <= maxY; y++) {
            String rowStr = "";
            for (int x = minX; x <= maxX; x++) {
                Point curPoint = new Point(x, y);
                if(screen.containsKey(curPoint)) {
                    rowStr += screen.get(curPoint);
                }else{
                    rowStr += " ";
                }
            }
            System.out.println(rowStr);
        }

        System.out.println("");
        System.out.println("");
    }

    public static Point getBallPosition(Map<Point, Long> screen) {
        for(Map.Entry<Point, Long> e : screen.entrySet()) {
            if(e.getValue() == 4) {
                return e.getKey();
            }
        }
        return new Point(0,0);
    }

    public static Point getPaddlePos(Map<Point, Long> screen) {
        for(Map.Entry<Point, Long> e : screen.entrySet()) {
            if(e.getValue() == 3) {
                return e.getKey();
            }
        }
        return new Point(0,0);
    }

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
            //Put in unlimited quarters
            ic.setIntToAddress(0, 2);
            icc.initIntcode(ic);
            icc.runIntcode();

            while(!icc.isComputationEntirelyFinished()) {
                while(icc.hasOutputToRead()) {
                    Point curPoint = new Point((int)icc.getOutput(), (int)icc.getOutput());
                    if(curPoint.x == -1 && curPoint.y == 0) {
                        System.out.println("CURRENT SCORE: " + icc.getOutput());
                    }else{
                        screenData.put(curPoint, icc.getOutput());
                    }
                }

                Day13PartTwo.printScreen(screenData);

                if(icc.isComputationAwaitingInput()) {
                    Point paddlePos = Day13PartTwo.getPaddlePos(screenData);
                    Point ballPos = Day13PartTwo.getBallPosition(screenData);
                    if(paddlePos.x > ballPos.x) {
                        icc.addToInput(-1);
                    }else if(paddlePos.x < ballPos.x) {
                        icc.addToInput(1);
                    }else{
                        icc.addToInput(0);
                    }
                }
                icc.runIntcode();
            }

            while(icc.hasOutputToRead()) {
                Point curPoint = new Point((int)icc.getOutput(), (int)icc.getOutput());
                if(curPoint.x == -1 && curPoint.y == 0) {
                    System.out.println("CURRENT SCORE: " + icc.getOutput());
                }else{
                    screenData.put(curPoint, icc.getOutput());
                }
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
