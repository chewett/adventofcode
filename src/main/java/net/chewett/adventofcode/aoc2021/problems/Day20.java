package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.aoc2015.ByteHexConversion;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 20: Trench Map ---
 * With the scanners fully deployed, you turn their attention to mapping the floor of the ocean trench.
 *
 * When you get back the image from the scanners, it seems to just be random noise. Perhaps you can combine an image
 * enhancement algorithm and the input image (your puzzle input) to clean it up a little.
 *
 * For example:
 *
 * ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##
 * #..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###
 * .######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.
 * .#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....
 * .#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..
 * ...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....
 * ..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#
 *
 * #..#.
 * #....
 * ##..#
 * ..#..
 * ..###
 * The first section is the image enhancement algorithm. It is normally given on a single line, but it has been wrapped
 * to multiple lines in this example for legibility. The second section is the input image, a two-dimensional grid of
 * light pixels (#) and dark pixels (.).
 *
 * The image enhancement algorithm describes how to enhance an image by simultaneously converting all pixels in the
 * input image into an output image. Each pixel of the output image is determined by looking at a 3x3 square of pixels
 * centered on the corresponding input image pixel. So, to determine the value of the pixel at (5,10) in the output
 * image, nine pixels from the input image need to be considered:
 * (4,9), (4,10), (4,11), (5,9), (5,10), (5,11), (6,9), (6,10), and (6,11). These nine input pixels are combined into a
 * single binary number that is used as an index in the image enhancement algorithm string.
 *
 * For example, to determine the output pixel that corresponds to the very middle pixel of the input image, the nine
 * pixels marked by [...] would need to be considered:
 *
 * # . . # .
 * #[. . .].
 * #[# . .]#
 * .[. # .].
 * . . # # #
 * Starting from the top-left and reading across each row, these pixels are ..., then #.., then .#.; combining these
 * forms ...#...#.. By turning dark pixels (.) into 0 and light pixels (#) into 1, the binary number 000100010 can be
 * formed, which is 34 in decimal.
 *
 * The image enhancement algorithm string is exactly 512 characters long, enough to match every possible 9-bit binary
 * number. The first few characters of the string (numbered starting from zero) are as follows:
 *
 * 0         10        20        30  34    40        50        60        70
 * |         |         |         |   |     |         |         |         |
 * ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##
 * In the middle of this first group of characters, the character at index 34 can be found: #. So, the output pixel in
 * the center of the output image should be #, a light pixel.
 *
 * This process can then be repeated to calculate every pixel of the output image.
 *
 * Through advances in imaging technology, the images being operated on here are infinite in size. Every pixel of the
 * infinite output image needs to be calculated exactly based on the relevant pixels of the input image. The small
 * input image you have is only a small region of the actual infinite input image; the rest of the input image consists
 * of dark pixels (.). For the purposes of the example, to save on space, only a portion of the infinite-sized input
 * and output images will be shown.
 *
 * The starting input image, therefore, looks something like this, with more dark pixels (.) extending forever in every
 * direction not shown here:
 *
 * ...............
 * ...............
 * ...............
 * ...............
 * ...............
 * .....#..#......
 * .....#.........
 * .....##..#.....
 * .......#.......
 * .......###.....
 * ...............
 * ...............
 * ...............
 * ...............
 * ...............
 * By applying the image enhancement algorithm to every pixel simultaneously, the following output image can be obtained:
 *
 * ...............
 * ...............
 * ...............
 * ...............
 * .....##.##.....
 * ....#..#.#.....
 * ....##.#..#....
 * ....####..#....
 * .....#..##.....
 * ......##..#....
 * .......#.#.....
 * ...............
 * ...............
 * ...............
 * ...............
 * Through further advances in imaging technology, the above output image can also be used as an input image! This
 * allows it to be enhanced a second time:
 *
 * ...............
 * ...............
 * ...............
 * ..........#....
 * ....#..#.#.....
 * ...#.#...###...
 * ...#...##.#....
 * ...#.....#.#...
 * ....#.#####....
 * .....#.#####...
 * ......##.##....
 * .......###.....
 * ...............
 * ...............
 * ...............
 * Truly incredible - now the small details are really starting to come through. After enhancing the original input
 * image twice, 35 pixels are lit.
 *
 * Start with the original input image and apply the image enhancement algorithm twice, being careful to account for
 * the infinite size of the images. How many pixels are lit in the resulting image?
 *
 * --- Part Two ---
 * You still can't quite make out the details in the image. Maybe you just didn't enhance it enough.
 *
 * If you enhance the starting input image in the above example a total of 50 times, 3351 pixels are lit in the final
 * output image.
 *
 * Start again with the original input image and apply the image enhancement algorithm 50 times. How many pixels are
 * lit in the resulting image?
 */
public class Day20 {


    /**
     * Gets all the surrounding points of this point, alongside itself
     * @param p Point to look around of to find all the points surrounding it
     * @return All points surrounding this one (including this one)
     */
    private List<Point> getSurroundingPointsInclusiveOfPoint(Point p) {
          List<Point> allPoints = new ArrayList<>();
          //For these purposes we want X-2 to X+2 and the same for Y
          for(int x = p.x-2; x <= p.x+2; x++) {
              for(int y = p.y-2; y <= p.y+2; y++) {
                  allPoints.add(new Point(x, y));
              }
          }

          return allPoints;
    };

    /**
     * Perform a single iteration of the algorithm to magnify it
     * @param oldGrid The current state of the 2D grid
     * @param conversionVal The magnification string that defines how we should convert it
     * @param defaultVal The default "background value", this will depend on your magnification function and current iteration
     * @return New grid after the magnification process
     */
    private Discrete2DPositionGrid<Character> performIteration(Discrete2DPositionGrid<Character> oldGrid, String conversionVal, char defaultVal) {
        Discrete2DPositionGrid<Character> newGrid = new Discrete2DPositionGrid<>(defaultVal);

        //Find all the positions to check
        Set<Point> allPositionsToCheck = new HashSet<>();
        List<Point> positionsOfHash = oldGrid.getPositionsOfValue('#');
        for(Point p : positionsOfHash) {
            allPositionsToCheck.addAll(this.getSurroundingPointsInclusiveOfPoint(p));
        }

        //loop over them and then work out the new value
        for(Point p : allPositionsToCheck) {
            //Build the string
            StringBuilder sb = new StringBuilder();
            for(int y = p.y-1; y <= p.y+1; y++) {
                for(int x = p.x-1; x <= p.x+1; x++) {
                    sb.append(oldGrid.getValueAtPosition(x, y));
                }
            }
            //Create the index from binary
            int index = Integer.parseInt(sb.toString().replace(".", "0").replace("#", "1"), 2);
            //Set the new value
            newGrid.setValueAtPosition(p, conversionVal.charAt(index));
        }

        return newGrid;
    };

    /**
     * Runs the image magnifications algorithm on the input (both in the same List<String> for a specific number of iterations
     * @param input Magnification algorithm and the original image
     * @param iterations Number of iterations to run it for
     * @return The number of lit pixels after running the magnification
     */
    public long countLitPixelsAfterIterations(List<String> input, int iterations) {
        String conversionVal = input.get(0);

        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');
        for (int i = 2; i < input.size(); i++) {
            int y = i -2;
            for(int x = 0; x < input.get(i).length(); x++) {
                grid.setValueAtPosition(x, y, input.get(i).charAt(x));
            }
        }

        for(int i = 0; i < iterations; i++) {
            //Now this part is really really nasty! In the example input your "0" value is . which means it doesn't change
            //however the real input has a # which means every iteration everything lights up, then darkens again as pairs.
            //So to handle both the example input and the real data you need to switch between the first char and zero
            grid = this.performIteration(grid, conversionVal, (i % 2 == 0) ? conversionVal.charAt(0) : '.');
        }

        return grid.countInstancesOfValue('#');
    }

    /**
     * Solve part one by performing 2 iterations
     * @param input Magnification algorithm and the original image
     * @return Number of lit pixels
     */
    public long solvePartOne(List<String> input) {
        return this.countLitPixelsAfterIterations(input, 2);
    }

    /**
     * Solve part one by performing 50 iterations
     * @param input Magnification algorithm and the original image
     * @return Number of lit pixels
     */
    public long solvePartTwo(List<String> input) {
        return this.countLitPixelsAfterIterations(input, 50);
    }

    public static void main(String[]  args) {
        List<String> str = ProblemLoader.loadProblemIntoStringArray(2021, 20);

        Day20 d = new Day20();
        System.out.println("The lit pixels after 2 iterations are " + d.solvePartOne(str));
        System.out.println("The lit pixels after 50 iterations are " + d.solvePartTwo(str));
    }

}
