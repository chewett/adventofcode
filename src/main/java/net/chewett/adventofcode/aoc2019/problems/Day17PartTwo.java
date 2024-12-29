package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.aoc2019.intcode.Intcode;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Day17PartTwo {

    private List<String> runAndPrint(IntcodeComputer icc) {
        List<String> rows = new ArrayList<>();

        String curRow = "";
        while(icc.hasOutputToRead()) {
            long output = icc.getOutput();

            if(output == 10) {
                rows.add(curRow);
                curRow = "";
            }else{
                if(output > 255) {
                    curRow += ""+ output;
                }else{
                    curRow += ((char) output);
                }
            }
        }
        rows.add(curRow);

        return rows;
    }

    public long solve() {
        try {
            File file = new File(getClass().getResource("/aoc2019/2019_day_17_input.txt").getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String oxygenRobot = br.readLine();
            oxygenRobot = "2" + oxygenRobot.substring(1);


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
            List<String> output = this.runAndPrint(icc);

            List<String> gridAsString = output.subList(0, output.size()-2);

            Discrete2DPositionGrid<Character> grid = FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
                    FormatConversion.convertStringArrayToCharListList(gridAsString)
            );

            Point curLocation = grid.getPositionOfValueAssumingOnlyOne('^');
            //Track the "last" point so we don't end up going back on ourselves
            Point lastPoint = grid.getPositionOfValueAssumingOnlyOne('^');
            int curDirection = Directions.NORTH;

            List<String> directions = new ArrayList<>();
            while(true) {
                List<Point> possibleMoves = grid.getDirectlyAdjacentPoints(curLocation);
                Point nextPoint = null;
                for(Point p : possibleMoves) {
                    if(grid.getValueAtPosition(p) == '#' && !p.equals(lastPoint)) {
                        nextPoint = p;
                        break;
                    }
                }

                if(nextPoint == null) {
                    break;
                }

                int newDirection = Directions.getDirectionFromPointToPoint(curLocation, nextPoint);
                directions.addAll(Directions.getDirectionsFromPointToPoint(curDirection, newDirection));

                int numToMove = 0;
                boolean foundStop = false;
                while(!foundStop) {
                    Point nextPointAlongLine = Directions.getPointInDirection(curLocation, newDirection);
                    if(grid.getValueAtPosition(nextPointAlongLine) == '#') {
                        numToMove++;
                        //Ensure we track the last point before we move
                        lastPoint = curLocation;
                        curLocation = nextPointAlongLine;
                    }else{
                        foundStop = true;
                    }
                }
                curDirection = newDirection;

                directions.add(String.valueOf(numToMove));
            }

            // A B and C series
            icc.addAllToInput("A,B,A,B,C,C,B,A,B,C\n");

            // A
            icc.addAllToInput("L,12,L,10,R,8,L,12\n");

            // B
            icc.addAllToInput("R,8,R,10,R,12\n");

            // C
            icc.addAllToInput("L,10,R,12,R,8\n");

            // Continuous feed?
            icc.addToInput((int)'n');
            icc.addToInput((int)'\n');
            icc.addToInput((int)'\n');
            icc.addToInput((int)'\n');


            icc.runIntcode();
            List<String> finalOut = this.runAndPrint(icc);
            return Long.parseLong(finalOut.get(finalOut.size()-1));



        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void main(String[] args) {
        Day17PartTwo d = new Day17PartTwo();
        System.out.println(d.solve());
    }

}
