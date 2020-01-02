package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.MapArea;
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

    public static int calculateDirectionToPoint(Point startingPoint, Point endingPoint) {




        return 1;
    }


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

            boolean notFound = true;
            Point pointToVisit = pointsToVisitNext.remove();
            Point oxygenPoint = new Point(1,1);
            while(notFound) {
                System.out.println("Current position " + x + "," + y + " Objective: " + pointToVisit.x + "," + pointToVisit.y);
                int direction = ma.getDirectionToMoveToPoint(x, y, (int)pointToVisit.getX(), (int)pointToVisit.getY());
                Point newPointImAimingFor = ma.getPointInDirection(new Point(x, y), direction);
                System.out.println("Moving in direction " + direction);
                icc.addToInput(direction);
                icc.runIntcode();
                long moveResult = icc.getOutput();
                System.out.println("Result of move: " + moveResult);
                if(moveResult == 0) {
                    ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '#');
                    if(newPointImAimingFor.x == pointToVisit.x && newPointImAimingFor.y == pointToVisit.y) {
                        pointToVisit = pointsToVisitNext.remove();
                    }
                }else if(moveResult == 1) {
                    x = newPointImAimingFor.x;
                    y = newPointImAimingFor.y;

                    ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, '.');
                    if(newPointImAimingFor.equals(pointToVisit)) {
                        System.out.println("Found position, getting new one");
                        pointsToVisitNext.addAll(ma.findAdjacentUnexploredPoints(x, y));
                        pointToVisit = pointsToVisitNext.remove();
                        while(pointToVisit.x == x && pointToVisit.y == y) {
                            System.out.println("Throwing away new position");
                            pointToVisit = pointsToVisitNext.remove();
                        }
                    }
                }else if(moveResult == 2) {
                    ma.setMapData(newPointImAimingFor.x, newPointImAimingFor.y, 'O');
                    System.out.println("Found Oxygen point " + newPointImAimingFor);
                    oxygenPoint = newPointImAimingFor;
                    notFound = false;
                }
                ma.draw(x, y);
            }


            System.out.println("Found: " + ma.calculateCostBetweenPoints(0, 0, oxygenPoint.x, oxygenPoint.y));





        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
