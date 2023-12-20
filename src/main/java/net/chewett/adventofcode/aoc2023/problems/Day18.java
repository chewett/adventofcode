package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.List;

/**
 * --- Day 18: Lavaduct Lagoon ---
 * Thanks to your efforts, the machine parts factory is one of the first factories up and running since the lavafall
 * came back. However, to catch up with the large backlog of parts requests, the factory will also need a large supply
 * of lava for a while; the Elves have already started creating a large lagoon nearby for this purpose.
 *
 * However, they aren't sure the lagoon will be big enough; they've asked you to take a look at the dig plan (your
 * puzzle input). For example:
 *
 * R 6 (#70c710)
 * D 5 (#0dc571)
 * L 2 (#5713f0)
 * D 2 (#d2c081)
 * R 2 (#59c680)
 * D 2 (#411b91)
 * L 5 (#8ceee2)
 * U 2 (#caa173)
 * L 1 (#1b58a2)
 * U 2 (#caa171)
 * R 2 (#7807d2)
 * U 3 (#a77fa3)
 * L 2 (#015232)
 * U 2 (#7a21e3)
 * The digger starts in a 1 meter cube hole in the ground. They then dig the specified number of meters up (U),
 * down (D), left (L), or right (R), clearing full 1 meter cubes as they go. The directions are given as seen from
 * above, so if "up" were north, then "right" would be east, and so on. Each trench is also listed with the color
 * that the edge of the trench should be painted as an RGB hexadecimal color code.
 *
 * When viewed from above, the above example dig plan would result in the following loop of trench (#) having been
 * dug out from otherwise ground-level terrain (.):
 *
 * #######
 * #.....#
 * ###...#
 * ..#...#
 * ..#...#
 * ###.###
 * #...#..
 * ##..###
 * .#....#
 * .######
 * At this point, the trench could contain 38 cubic meters of lava. However, this is just the edge of the lagoon;
 * the next step is to dig out the interior so that it is one meter deep as well:
 *
 * #######
 * #######
 * #######
 * ..#####
 * ..#####
 * #######
 * #####..
 * #######
 * .######
 * .######
 * Now, the lagoon can contain a much more respectable 62 cubic meters of lava. While the interior is dug out, the
 * edges are also painted according to the color codes in the dig plan.
 *
 * The Elves are concerned the lagoon won't be large enough; if they follow their dig plan, how many cubic meters of
 * lava could it hold?
 *
 * Your puzzle answer was 62573.
 *
 * --- Part Two ---
 * The Elves were right to be concerned; the planned lagoon would be much too small.
 *
 * After a few minutes, someone realizes what happened; someone swapped the color and instruction parameters when
 * producing the dig plan. They don't have time to fix the bug; one of them asks if you can extract the correct
 * instructions from the hexadecimal codes.
 *
 * Each hexadecimal code is six hexadecimal digits long. The first five hexadecimal digits encode the distance in
 * meters as a five-digit hexadecimal number. The last hexadecimal digit encodes the direction to dig: 0 means R,
 * 1 means D, 2 means L, and 3 means U.
 *
 * So, in the above example, the hexadecimal codes can be converted into the true instructions:
 *
 * #70c710 = R 461937
 * #0dc571 = D 56407
 * #5713f0 = R 356671
 * #d2c081 = D 863240
 * #59c680 = R 367720
 * #411b91 = D 266681
 * #8ceee2 = L 577262
 * #caa173 = U 829975
 * #1b58a2 = L 112010
 * #caa171 = D 829975
 * #7807d2 = L 491645
 * #a77fa3 = U 686074
 * #015232 = L 5411
 * #7a21e3 = U 500254
 * Digging out this loop and its interior produces a lagoon that can hold an impressive 952408144115 cubic meters of
 * lava.
 *
 * Convert the hexadecimal color codes into the correct instructions; if the Elves follow this new dig plan, how many
 * cubic meters of lava could the lagoon hold?
 */
public class Day18 {

    /**
     * Works out the number of cubic meters that the lagoon could hold after digging it out
     *
     * This is the "brute force" method of solving it
     *
     * @param input Instructions to dig out the lagoon
     * @return Number of cubic meters that the lagoon could hold
     */
    public long solvePartOne(List<String> input) {
        //Define a grid to hold the lagoon data
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');
        //Start in the hole
        grid.setValueAtPosition(0, 0, '#');

        int x = 0;
        int y = 0;
        for(String line : input) {
            //parse the input and slowly dig the channel
            String[] parts = line.split(" ");
            String dir = parts[0];
            int numMovement = Integer.parseInt(parts[1]);
            Point offset = null;
            if(dir.equals("U")) {
                offset = Directions.getDirectionModifier(Directions.UP);
            }else if(dir.equals("D")) {
                offset = Directions.getDirectionModifier(Directions.DOWN);
            }else if(dir.equals("L")) {
                offset = Directions.getDirectionModifier(Directions.LEFT);
            }else if(dir.equals("R")) {
                offset = Directions.getDirectionModifier(Directions.RIGHT);
            }
            //Diggy Diggy Hole
            for(int i = 0; i < numMovement; i++) {
                x += offset.x;
                y += offset.y;
                grid.setValueAtPosition(x, y, '#');
            }
        }

        //Once we have dug the outline of the lagoon we need to dig out the center.
        //We can actually just work out what "isn't" the center and then use that with maths!

        //First flood the grid with F's on the outside, this leaves a square of F's surrounding the lagoon
        grid.floodFillTopLeft('F', '.');
        //And then the total size of the lagoon is the total size of the grid minus the flood values
        return grid.getTotalPositions() - grid.countInstancesOfValue('F');
    }

    /**
     * Finds out the size of the lagoon after correcting the confusing instructions
     *
     * Since the numbers become "very large" it's not possible to use the first method. We can actually use something
     * called the <a href="https://en.wikipedia.org/wiki/Shoelace_formula">Shoelace Formula </a>
     *
     * @param input Instructions to dig out the lagoon
     * @return Number of cubic meters that the lagoon could hold
     */
    public long solvePartTwo(List<String> input) {

        //Keep track of the permimeter and the area
        long perimeter = 0;
        long area = 0;

        long x = 0;

        //Go over every line
        for(String line : input) {
            String[] parts = line.split("\\(#");
            int distance = Integer.parseInt(parts[1].substring(0, 5), 16);
            int directionInt = Integer.parseInt(""+parts[1].charAt(5));

            Point offset = null;
            if(directionInt == 0) {
                offset = Directions.getDirectionModifier(Directions.RIGHT);
            }else if(directionInt == 1) {
                offset = Directions.getDirectionModifier(Directions.DOWN);
            }else if(directionInt == 2) {
                offset = Directions.getDirectionModifier(Directions.LEFT);
            }else if(directionInt == 3) {
                offset = Directions.getDirectionModifier(Directions.UP);
            }

            //Work out the distance in the X and the Y
            long distanceX = offset.x * distance;
            long distanceY = offset.y * distance;

            //Shoelace (increment X, increment distance, add new area)
            x += distanceX;
            perimeter += distance;
            area += x * distanceY;
        }

        //The final area is the area + (half the perimeter) + 1
        return area + (long)Math.floor(perimeter /2) + 1;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 18);

        Day18 d = new Day18();
        long partOne = d.solvePartOne(input);
        System.out.println("The area in the first lagoon is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The area in the second larger lagoon is " + partTwo);
    }
}


