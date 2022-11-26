package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 13: Transparent Origami ---
 * You reach another volcanically active part of the cave. It would be nice if you could do some kind of thermal imaging
 * so you could tell ahead of time which caves are too hot to safely enter.
 *
 * Fortunately, the submarine seems to be equipped with a thermal camera! When you activate it, you are greeted with:
 *
 * Congratulations on your purchase! To activate this infrared thermal imaging
 * camera system, please enter the code found on page 1 of the manual.
 * Apparently, the Elves have never used this feature. To your surprise, you manage to find the manual; as you go to
 * open it, page 1 falls out. It's a large sheet of transparent paper! The transparent paper is marked with random dots
 * and includes instructions on how to fold it up (your puzzle input). For example:
 *
 * 6,10
 * 0,14
 * 9,10
 * 0,3
 * 10,4
 * 4,11
 * 6,0
 * 6,12
 * 4,1
 * 0,13
 * 10,12
 * 3,4
 * 3,0
 * 8,4
 * 1,10
 * 2,14
 * 8,10
 * 9,0
 *
 * fold along y=7
 * fold along x=5
 * The first section is a list of dots on the transparent paper. 0,0 represents the top-left coordinate. The first
 * value, x, increases to the right. The second value, y, increases downward. So, the coordinate 3,0 is to the right
 * of 0,0, and the coordinate 0,7 is below 0,0. The coordinates in this example form the following pattern, where # is
 * a dot on the paper and . is an empty, unmarked position:
 *
 * ...#..#..#.
 * ....#......
 * ...........
 * #..........
 * ...#....#.#
 * ...........
 * ...........
 * ...........
 * ...........
 * ...........
 * .#....#.##.
 * ....#......
 * ......#...#
 * #..........
 * #.#........
 * Then, there is a list of fold instructions. Each instruction indicates a line on the transparent paper and wants you
 * to fold the paper up (for horizontal y=... lines) or left (for vertical x=... lines). In this example, the first
 * fold instruction is fold along y=7, which designates the line formed by all of the positions where y is 7 (marked
 * here with -):
 *
 * ...#..#..#.
 * ....#......
 * ...........
 * #..........
 * ...#....#.#
 * ...........
 * ...........
 * -----------
 * ...........
 * ...........
 * .#....#.##.
 * ....#......
 * ......#...#
 * #..........
 * #.#........
 * Because this is a horizontal line, fold the bottom half up. Some of the dots might end up overlapping after the fold
 * is complete, but dots will never appear exactly on a fold line. The result of doing this fold looks like this:
 *
 * #.##..#..#.
 * #...#......
 * ......#...#
 * #...#......
 * .#.#..#.###
 * ...........
 * ...........
 * Now, only 17 dots are visible.
 *
 * Notice, for example, the two dots in the bottom left corner before the transparent paper is folded; after the fold is
 * complete, those dots appear in the top left corner (at 0,0 and 0,1). Because the paper is transparent, the dot just
 * below them in the result (at 0,3) remains visible, as it can be seen through the transparent paper.
 *
 * Also notice that some dots can end up overlapping; in this case, the dots merge together and become a single dot.
 *
 * The second fold instruction is fold along x=5, which indicates this line:
 *
 * #.##.|#..#.
 * #...#|.....
 * .....|#...#
 * #...#|.....
 * .#.#.|#.###
 * .....|.....
 * .....|.....
 * Because this is a vertical line, fold left:
 *
 * #####
 * #...#
 * #...#
 * #...#
 * #####
 * .....
 * .....
 * The instructions made a square!
 *
 * The transparent paper is pretty big, so for now, focus on just completing the first fold. After the first fold in the
 * example above, 17 dots are visible - dots that end up overlapping after the fold is completed count as a single dot.
 *
 * How many dots are visible after completing just the first fold instruction on your transparent paper?
 *
 * --- Part Two ---
 * Finish folding the transparent paper according to the instructions. The manual says the code is always eight capital letters.
 *
 * What code do you use to activate the infrared thermal imaging camera system?
 *
 */
public class Day13 {

    /**
     * Given a fold instruction and a grid, this will return the new grid after folding
     * @param grid Grid to perform an instruction on
     * @param foldInstruction Fold instruction to perform
     * @return New grid after folding
     */
    private Discrete2DPositionGrid<Character> foldGrid(Discrete2DPositionGrid<Character> grid, String foldInstruction) {
        Discrete2DPositionGrid<Character> newGrid = new Discrete2DPositionGrid<>('.');

        int maxX = grid.getMaxX();
        int maxY = grid.getMaxY();

        //parse the fold
        String[] foldAlongTmp = foldInstruction.split(" ");
        foldAlongTmp = foldAlongTmp[2].split("=");
        String direction = foldAlongTmp[0];
        int value = Integer.parseInt(foldAlongTmp[1]);

        //Loop over the old array
        for(int x = 0; x <= maxX; x++) {
            for(int y = 0; y <= maxY; y++) {
                char charAtPosition = grid.getValueAtPosition(x, y);
                //and for every dot (#) we work out where it should be after folding
                if(charAtPosition == '#') {
                    int xPosToSave = x;
                    int yPosToSave = y;
                    //Handle X and Y direcitons
                    if (direction.equals("y")) {
                        if (yPosToSave > value) {
                            //Handle the fact that the location will be past the fold, so we need to reverse it
                            yPosToSave = value - (yPosToSave - value);
                        }
                    }else if (direction.equals("x")) {
                        if (xPosToSave > value) {
                            //Handle the fact that the location will be past the fold, so we need to reverse it
                            xPosToSave = value - (xPosToSave - value);
                        }
                    }else{
                        throw new RuntimeException("Unspecified fold type");
                    }
                    //Save the new dot position
                    newGrid.setValueAtPosition(xPosToSave, yPosToSave, '#');
                }

            }
        }

        return newGrid;
    }

    /**
     * Takes in a grid and a set of dots and instructions and writes the dots to the grid and returns the list of instructions
     * @param grid Grid to write the dots to
     * @param dotsAndInstructions Dots and instructions to parse
     * @return List of instructions to run (and modifies the grid passed in)
     */
    private List<String> readDotsAndInstructions(Discrete2DPositionGrid<Character> grid, List<String> dotsAndInstructions) {
        List<String> instructions = new ArrayList<>();

        new ArrayList<>();
        boolean readingDots = true;
        for(String str : dotsAndInstructions) {
            if(str.equals("")) {
                readingDots = false;
            }else if(readingDots) {
                String[] parts = str.split(",");
                grid.setValueAtPosition(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), '#');
            }else{
                instructions.add(str);
            }
        }

        return instructions;
    }

    /**
     * Read in the dots and instructions and then return the number of dots (#'s) after a single fold
     * @param dotsAndInstructions Dots and instructions to perform
     * @return Number of dots (#'s) in the folded paper
     */
    public long solvePartOne(List<String> dotsAndInstructions) {
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');
        List<String> instructions = readDotsAndInstructions(grid, dotsAndInstructions);

        //Just fold once for now
        grid = this.foldGrid(grid, instructions.get(0));

        return grid.countInstancesOfValue('#');
    }

    /**
     * Read in the dots and instructions and then perform all the instructions and return the finished paper (grid)
     * @param dotsAndInstructions Dots and instructions to perform
     * @return The grid after folding all the instructions
     */
    public Discrete2DPositionGrid<Character> solvePartTwo(List<String> dotsAndInstructions) {
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');
        List<String> instructions = readDotsAndInstructions(grid, dotsAndInstructions);

        //Fold all the things!
        for(String str : instructions) {
            grid = this.foldGrid(grid, str);
        }

        return grid;
    }

    public static void main(String[] args) {
        List<String> dots = ProblemLoader.loadProblemIntoStringArray(2021, 13);

        Day13 d = new Day13();
        long partOne = d.solvePartOne(dots);
        System.out.println("The number of dots visible after the first fold are " + partOne);

        Discrete2DPositionGrid<Character> grid = d.solvePartTwo(dots);

        System.out.println();
        System.out.println("The paper after folding all the parts is");

        System.out.println();
        grid.print('.');
    }

}
