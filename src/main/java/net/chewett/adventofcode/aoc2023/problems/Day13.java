package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 13: Point of Incidence ---
 * With your help, the hot springs team locates an appropriate spring which launches you neatly and precisely up to
 * the edge of Lava Island.
 *
 * There's just one problem: you don't see any lava.
 *
 * You do see a lot of ash and igneous rock; there are even what look like gray mountains scattered around. After a
 * while, you make your way to a nearby cluster of mountains only to discover that the valley between them is
 * completely full of large mirrors. Most of the mirrors seem to be aligned in a consistent way; perhaps you should
 * head in that direction?
 *
 * As you move through the valley of mirrors, you find that several of them have fallen from the large metal frames
 * keeping them in place. The mirrors are extremely flat and shiny, and many of the fallen mirrors have lodged into
 * the ash at strange angles. Because the terrain is all one color, it's hard to tell where it's safe to walk or where
 * you're about to run into a mirror.
 *
 * You note down the patterns of ash (.) and rocks (#) that you see as you walk (your puzzle input); perhaps by
 * carefully analyzing these patterns, you can figure out where the mirrors are!
 *
 * For example:
 *
 * #.##..##.
 * ..#.##.#.
 * ##......#
 * ##......#
 * ..#.##.#.
 * ..##..##.
 * #.#.##.#.
 *
 * #...##..#
 * #....#..#
 * ..##..###
 * #####.##.
 * #####.##.
 * ..##..###
 * #....#..#
 * To find the reflection in each pattern, you need to find a perfect reflection across either a horizontal line
 * between two rows or across a vertical line between two columns.
 *
 * In the first pattern, the reflection is across a vertical line between two columns; arrows on each of the two
 * columns point at the line between the columns:
 *
 * 123456789
 *     ><
 * #.##..##.
 * ..#.##.#.
 * ##......#
 * ##......#
 * ..#.##.#.
 * ..##..##.
 * #.#.##.#.
 *     ><
 * 123456789
 * In this pattern, the line of reflection is the vertical line between columns 5 and 6. Because the vertical line
 * is not perfectly in the middle of the pattern, part of the pattern (column 1) has nowhere to reflect onto and can
 * be ignored; every other column has a reflected column within the pattern and must match exactly: column 2 matches
 * column 9, column 3 matches 8, 4 matches 7, and 5 matches 6.
 *
 * The second pattern reflects across a horizontal line instead:
 *
 * 1 #...##..# 1
 * 2 #....#..# 2
 * 3 ..##..### 3
 * 4v#####.##.v4
 * 5^#####.##.^5
 * 6 ..##..### 6
 * 7 #....#..# 7
 * This pattern reflects across the horizontal line between rows 4 and 5. Row 1 would reflect with a hypothetical row 8,
 * but since that's not in the pattern, row 1 doesn't need to match anything. The remaining rows match: row 2 matches
 * row 7, row 3 matches row 6, and row 4 matches row 5.
 *
 * To summarize your pattern notes, add up the number of columns to the left of each vertical line of reflection; to
 * that, also add 100 multiplied by the number of rows above each horizontal line of reflection. In the above example,
 * the first pattern's vertical line has 5 columns to its left and the second pattern's horizontal line has 4 rows
 * above it, a total of 405.
 *
 * Find the line of reflection in each of the patterns in your notes. What number do you get after summarizing all
 * of your notes?
 *
 * --- Part Two ---
 * You resume walking through the valley of mirrors and - SMACK! - run directly into one. Hopefully nobody was
 * watching, because that must have been pretty embarrassing.
 *
 * Upon closer inspection, you discover that every mirror has exactly one smudge: exactly one . or # should be the
 * opposite type.
 *
 * In each pattern, you'll need to locate and fix the smudge that causes a different reflection line to be valid.
 * (The old reflection line won't necessarily continue being valid after the smudge is fixed.)
 *
 * Here's the above example again:
 *
 * #.##..##.
 * ..#.##.#.
 * ##......#
 * ##......#
 * ..#.##.#.
 * ..##..##.
 * #.#.##.#.
 *
 * #...##..#
 * #....#..#
 * ..##..###
 * #####.##.
 * #####.##.
 * ..##..###
 * #....#..#
 * The first pattern's smudge is in the top-left corner. If the top-left # were instead ., it would have a different,
 * horizontal line of reflection:
 *
 * 1 ..##..##. 1
 * 2 ..#.##.#. 2
 * 3v##......#v3
 * 4^##......#^4
 * 5 ..#.##.#. 5
 * 6 ..##..##. 6
 * 7 #.#.##.#. 7
 * With the smudge in the top-left corner repaired, a new horizontal line of reflection between rows 3 and 4 now
 * exists. Row 7 has no corresponding reflected row and can be ignored, but every other row matches exactly: row 1
 * matches row 6, row 2 matches row 5, and row 3 matches row 4.
 *
 * In the second pattern, the smudge can be fixed by changing the fifth symbol on row 2 from . to #:
 *
 * 1v#...##..#v1
 * 2^#...##..#^2
 * 3 ..##..### 3
 * 4 #####.##. 4
 * 5 #####.##. 5
 * 6 ..##..### 6
 * 7 #....#..# 7
 * Now, the pattern has a different horizontal line of reflection between rows 1 and 2.
 *
 * Summarize your notes as before, but instead use the new different reflection lines. In this example, the first
 * pattern's new horizontal line has 3 rows above it and the second pattern's new horizontal line has 1 row above
 * it, summarizing to the value 400.
 *
 * In each pattern, fix the smudge and find the different line of reflection. What number do you get after
 * summarizing the new reflection line in each pattern in your notes?
 */
public class Day13 {

    /**
     * Given a grid and a value to ignore this attempts to find a horizontal reflection
     * @param grid Grid to check
     * @param ignoreVal Horizontal reflection to ignore
     * @return Horizontal reflection value or 0 if there are none found
     */
    private int findHorizontalReflection(Discrete2DPositionGrid<Character> grid, int ignoreVal) {
        int horizontal = 0;

        //Go over each y value
        for(int y = 0; y < grid.getMaxY(); y++) {
            //And attempt to see if we can find a reflection
            boolean reflectionPointFound = true;
            for (int x = 0; x <= grid.getMaxX(); x++) {
                //loop over the values going from the reflection point backwards comparing each one
                for (int yToCheck = y, otherY = y + 1; yToCheck >= 0 && otherY <= grid.getMaxY(); yToCheck--, otherY++) {
                    if (grid.getValueAtPosition(x, yToCheck) != grid.getValueAtPosition(x, otherY)) {
                        reflectionPointFound = false;
                    }
                }
            }

            //Only track the new reflection point if it's not the same as the ignore value
            if (reflectionPointFound) {
                int newRef = y+1;
                if(newRef != ignoreVal) {
                    horizontal = newRef;
                }
            }
        }

        return horizontal;
    }

    /**
     * Given a grid and a value to ignore this attempts to find a vertical reflection
     * @param grid Grid to check
     * @param ignoreVal Vertical reflection to ignore
     * @return Vertical reflection value or 0 if there are none found
     */
    private int findVerticalReflection(Discrete2DPositionGrid<Character> grid, int ignoreVal) {
        int vertical = 0;

        //Go over each x value
        for(int x = 0; x < grid.getMaxX(); x++) {
            //And attempt to see if we can find a reflection
            boolean reflectionPointFound = true;
            for (int y = 0; y <= grid.getMaxY(); y++) {
                //loop over the values going from the reflection point backwards comparing each one
                for (int xToCheck = x, otherX = x + 1; xToCheck >= 0 && otherX <= grid.getMaxX(); xToCheck--, otherX++) {
                    if (grid.getValueAtPosition(xToCheck, y) != grid.getValueAtPosition(otherX, y)) {
                        reflectionPointFound = false;
                    }
                }
            }

            //Only track the new reflection point if its not the same as the ignore value
            if (reflectionPointFound) {
                int newRef = x+1;
                if(newRef != ignoreVal) {
                    vertical = x + 1;
                }
            }
        }
        return vertical;
    }

    /**
     * Looks to find the reflection point of every grid
     * @param grids Grids to find the reflection points of
     * @return Sum of the reflection point values
     */
    public long solvePartOne(List<Discrete2DPositionGrid<Character>> grids) {
        //Loop over every graph and find the horizontal or vertical reflection
        long total = 0;
        for(Discrete2DPositionGrid<Character> grid : grids) {
            int vert = this.findVerticalReflection(grid, -1);
            int horizontal = this.findHorizontalReflection(grid, -1);

            //If we failed to find either of them crash out (helpful for debugging)
            if(vert == 0 && horizontal == 0) {
                throw new RuntimeException("Failed to find :(");
            }

            //Add the vertical and horizontal (one will be 0 so we can just add it easily enough)
            total += vert;
            total += (100*horizontal);
        }

        return total;
    }

    /**
     * Attempts to find the "smudge value" and then the new reflection point
     * @param grids Grids to find the new reflection points of
     * @return Sum of the new reflection point values
     */
    public long solvePartTwo(List<Discrete2DPositionGrid<Character>> grids) {
        long total = 0;
        for(Discrete2DPositionGrid<Character> grid : grids) {
            //Find the "original" reflection values
            int orignalVert = this.findVerticalReflection(grid, -1);
            int originalHorizontal = this.findHorizontalReflection(grid, -1);

            Discrete2DPositionGrid<Character> copiedGrid = grid.clone();
            boolean foundValue = false;
            //And then loop over every character, flipping it, and then seeing if we find a new reflection
            for(int y = 0; y <= grid.getMaxY() && !foundValue; y++){
                for (int x = 0; x <= grid.getMaxX() && !foundValue; x++) {
                    char curVal = grid.getValueAtPosition(x, y);
                    char setValue = '.';
                    if(curVal == '.') {
                        setValue = '#';
                    }
                    copiedGrid.setValueAtPosition(x, y, setValue);
                    int newVert = this.findVerticalReflection(copiedGrid, orignalVert);
                    int newHorizon = this.findHorizontalReflection(copiedGrid, originalHorizontal);

                    //If we found a new vertical or horizontal save it!
                    if(newVert != 0 && newVert != orignalVert) {
                        total += newVert;
                        foundValue = true;
                    }
                    if(newHorizon != 0 && newHorizon != originalHorizontal) {
                        total += 100*newHorizon;
                        foundValue = true;
                    }

                    //Reset the grid and try again!
                    copiedGrid.setValueAtPosition(x, y, curVal);
                }
            }

            //Debug helper in the event we failed to find the new value
            if(!foundValue) {
                throw new RuntimeException("can't find :(");
            }
        }

        return total;
    }

    public static void main(String[] args) {
        List<Discrete2DPositionGrid<Character>> input = ProblemLoader.loadProblemIntoListOfDiscrete2DPositionCharacterGrids(2023, 13);

        Day13 d = new Day13();
        long partOne = d.solvePartOne(input);
        System.out.println("The sum of all the lines of reflection are " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("After fixing the smudges all of the lines of reflection are " + partTwo);
    }
}


