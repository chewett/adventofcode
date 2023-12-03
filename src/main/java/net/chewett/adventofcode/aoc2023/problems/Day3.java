package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 3: Gear Ratios ---
 * You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water
 * source, but this is as far as he can bring you. You go inside.
 *
 * It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.
 *
 * "Aaah!"
 *
 * You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting
 * anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
 *
 * The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
 * If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
 *
 * The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers
 * and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part
 * number" and should be included in your sum. (Periods (.) do not count as a symbol.)
 *
 * Here is an example engine schematic:
 *
 * 467..114..
 * ...*......
 * ..35..633.
 * ......#...
 * 617*......
 * .....+.58.
 * ..592.....
 * ......755.
 * ...$.*....
 * .664.598..
 * In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and
 * 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.
 *
 * Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine
 * schematic?
 *
 * --- Part Two ---
 * The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the
 * closest gondola, finally ready to ascend to the water source.
 *
 * You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone
 * labeled "help", so you pick it up and the engineer answers.
 *
 * Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding
 * a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station. You
 * exit the gondola.
 *
 * The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is
 * adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.
 *
 * This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out
 * which gear needs to be replaced.
 *
 * Consider the same engine schematic again:
 *
 * 467..114..
 * ...*......
 * ..35..633.
 * ......#...
 * 617*......
 * .....+.58.
 * ..592.....
 * ......755.
 * ...$.*....
 * .664.598..
 * In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear
 * ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a
 * gear because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.
 *
 * What is the sum of all of the gear ratios in your engine schematic?
 */
public class Day3 {

    /**
     * Attempts to find all the part numbers in the engine schematic
     * @param engineSchematic Schematic for the engine
     * @return Total of the part numbers
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> engineSchematic) {
        long total = 0;
        StringBuilder continuedNumber = new StringBuilder();
        boolean partNo = false;
        //Loop over every row
        for(int y = 0; y <= engineSchematic.getMaxY(); y++) {
            for(int x = 0; x <= engineSchematic.getMaxX(); x++) {
                char curChar = engineSchematic.getValueAtPosition(x, y);

                //If we have found a digit, append it to the "continued number" string
                if(Character.isDigit(curChar)) {
                    continuedNumber.append(curChar);

                    //Loop over each adjacent value
                    List<Point> adjacementValue = engineSchematic.getAdjacentPoints(x, y);
                    for(Point p : adjacementValue) {
                        //If any of the adjacent values are a digit or not . then it's a valid part number
                        if(!Character.isDigit(engineSchematic.getValueAtPosition(p)) && engineSchematic.getValueAtPosition(p) != '.') {
                            partNo = true;
                        }
                    }
                }else{
                    //If we found these adjacent to a symbol then this is a part number, add it up
                    if(partNo) {
                        total += Long.parseLong(continuedNumber.toString());
                    }
                    partNo = false;
                    continuedNumber = new StringBuilder();
                }
            }
        }

        return total;
    }


    /**
     * Find the total of all the gear ratios in all hears
     * @param engineSchematic Schematic for the engine
     * @return Returns the sum of the gear ratios
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> engineSchematic) {
        long total = 0;
        String continuedNumber = "";
        boolean partNo = false;
        Set<Point> adjacentGears = new HashSet<>();

        Map<Point, List<Long>> gearsToPartNumbers = new HashMap<>();
        //Loop over every row
        for(int y = 0; y <= engineSchematic.getMaxY(); y++) {
            for(int x = 0; x <= engineSchematic.getMaxX(); x++) {
                char curChar = engineSchematic.getValueAtPosition(x, y);

                //If we have found a digit, append it to the "continued number" string
                if(Character.isDigit(curChar)) {
                    continuedNumber += curChar;

                    //Loop over each adjacent value
                    List<Point> adjacementValue = engineSchematic.getAdjacentPoints(x, y);
                    for(Point p : adjacementValue) {
                        //If any of the adjacent values are a digit or not . then it's a valid part number
                        if(!Character.isDigit(engineSchematic.getValueAtPosition(p)) && engineSchematic.getValueAtPosition(p) != '.') {
                            partNo = true;
                        }
                        //If this is adjacent to a gear, add this to the list
                        if(engineSchematic.getValueAtPosition(p) == '*') {
                            adjacentGears.add(p);
                        }
                    }
                }else{
                    if(partNo) {
                        //If this was part number, add this list to the mapping
                        for(Point gear : adjacentGears) {
                            if(!gearsToPartNumbers.containsKey(gear)) {
                                gearsToPartNumbers.put(gear, new ArrayList<>());
                            }
                            gearsToPartNumbers.get(gear).add(Long.parseLong(continuedNumber));
                        }
                    }
                    partNo = false;
                    continuedNumber = "";
                    adjacentGears = new HashSet<>();
                }
            }
        }

        //Loop over every gear and if they have two part numbers multiple and total them
        for(Map.Entry<Point, List<Long>> e : gearsToPartNumbers.entrySet()) {
            if(e.getValue().size() == 2) {
                total += (e.getValue().get(0) * e.getValue().get(1));
            }
        }

        return total;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> grid = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 3);

        Day3 d = new Day3();
        long partOne = d.solvePartOne(grid);
        System.out.println("The total of all part numbers is " + partOne);

        long partTwo = d.solvePartTwo(grid);
        System.out.println("The total of the product of all gears is " + partTwo);
    }
}


