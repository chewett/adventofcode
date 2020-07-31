package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.MapArea;
import net.chewett.adventofcode2019.OxygenRefillingModel;
import net.chewett.adventofcode2019.intcode.Intcode;
import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.instructions.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.List;

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

            MapArea ma = new MapArea();
            ma.setMapData(0, 0, '.');

            Queue<Point> pointsToVisitNext = new LinkedList<>(ma.findAdjacentUnexploredPoints(0, 0));
            int x = 0;
            int y = 0;

            Point pointToVisit = pointsToVisitNext.remove();
            Point oxygenPoint = new Point(1,1);
            while(pointToVisit != null) {
                while(pointToVisit.x == x && pointToVisit.y == y) {
                    pointToVisit = pointsToVisitNext.remove();
                }

                int direction = ma.getDirectionToMoveToPoint(x, y, (int)pointToVisit.getX(), (int)pointToVisit.getY());
                Point newPointImAimingFor = ma.getPointInDirection(new Point(x, y), direction);
                icc.addToInput(direction);
                icc.runIntcode();
                long moveResult = icc.getOutput();
                if(moveResult == 0) {
                    ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '#');
                    if(newPointImAimingFor.x == pointToVisit.x && newPointImAimingFor.y == pointToVisit.y) {
                        if(pointsToVisitNext.size() > 0) {
                            pointToVisit = pointsToVisitNext.remove();
                        }else{
                            pointToVisit = null;
                        }
                    }
                }else if(moveResult == 1) {
                    x = newPointImAimingFor.x;
                    y = newPointImAimingFor.y;

                    ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '.');
                    if(newPointImAimingFor.equals(pointToVisit)) {
                        pointsToVisitNext.addAll(ma.findAdjacentUnexploredPoints(x, y));
                        pointToVisit = pointsToVisitNext.remove();
                        while(pointToVisit.x == x && pointToVisit.y == y) {
                            if(pointsToVisitNext.size() > 0) {
                                pointToVisit = pointsToVisitNext.remove();
                            }else{
                                pointToVisit = null;
                            }
                        }
                    }
                }else if(moveResult == 2) {
                    x = newPointImAimingFor.x;
                    y = newPointImAimingFor.y;
                    ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, 'O');
                    oxygenPoint = newPointImAimingFor;
                }
            }

            System.out.println("Found: " + ma.calculateCostBetweenPoints(0, 0, oxygenPoint.x, oxygenPoint.y));

            OxygenRefillingModel orm = new OxygenRefillingModel(ma);

            int minutesToFill = orm.getMinutesToFillAreaWithOxygen();
            System.out.println("Minutes to fill the area with oxygen: " + minutesToFill);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
