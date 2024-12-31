package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.List;

/**
 * Awesome problem taken from: https://adventofcode.com/2019/day/11
 * Go have a try yourself!
 * ----------------------------------------------------------
 *
 */
public class Day11 {

    public void solve() {
        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_11_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            //Day eleven input is a single line, so just load that
            String hullPaintingIntcode = br.readLine();

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
            Intcode ic = new Intcode(hullPaintingIntcode);
            icc.initIntcode(ic);
            icc.runIntcode();

            int x = 0;
            int y = 0;
            boolean firstPaint = true;
            Set<Point> paintedPoints = new HashSet<>(); //Panels start black
            Map<Point, String> colour = new HashMap<>();
            int direction = 0;
            while(icc.isComputationAwaitingInput()) {
                Point currentPoint = new Point(x, y);
                if(firstPaint) { // Only relevant for part 2
                    icc.addToInput(1);
                    firstPaint = false;
                }else if (!colour.containsKey(currentPoint) || colour.get(currentPoint).equals("black")) {
                    icc.addToInput(0);
                } else {
                    icc.addToInput(1);
                }
                icc.runIntcode();
                String colourToPaint = (icc.getOutput() == 0 ? "black" : "white");
                paintedPoints.add(currentPoint);
                colour.put(currentPoint, colourToPaint);
                direction += (icc.getOutput() == 0 ? -90 : 90);
                direction += 360;
                direction = direction % 360;

                switch (direction) {
                    case 0:
                        y -= 1;
                        break;
                    case 90:
                        x += 1;
                        break;
                    case 180:
                        y += 1;
                        break;
                    case 270:
                        x -= 1;
                        break;
                }
            }

            System.out.println(paintedPoints.size());

            // Only relevant for part 2
            int minX = 9999;
            int maxX = -9999;
            int minY = 9999;
            int maxY = -9999;
            for(Point p : colour.keySet()) {
                minX = Math.min(minX, p.x);
                maxX = Math.max(maxX, p.x);
                minY = Math.min(minY, p.y);
                maxY = Math.max(maxY, p.y);
            }

            for(y = minY; y <= maxY; y++) {
                String rowLine = "";
                for(x = minX; x <= maxX; x++) {
                    String colourOfPanel = "black";
                    Point curPoint = new Point(x, y);
                    if(colour.containsKey(curPoint)) {
                        colourOfPanel = colour.get(curPoint);
                    }

                    rowLine += (colourOfPanel.equals("white") ? "#" : "_");
                }
                System.out.println(rowLine);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Day11 d = new Day11();
        d.solve();
    }

}
