package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 21: Step Counter ---
 * You manage to catch the airship right as it's dropping someone else off on their all-expenses-paid trip to Desert
 * Island! It even helpfully drops you off near the gardener and his massive farm.
 *
 * "You got the sand flowing again! Great work! Now we just need to wait until we have enough sand to filter the water
 * for Snow Island and we'll have snow again in no time."
 *
 * While you wait, one of the Elves that works with the gardener heard how good you are at solving problems and would
 * like your help. He needs to get his steps in for the day, and so he'd like to know which garden plots he can reach
 * with exactly his remaining 64 steps.
 *
 * He gives you an up-to-date map (your puzzle input) of his starting position (S), garden plots (.), and rocks (#).
 * For example:
 *
 * ...........
 * .....###.#.
 * .###.##..#.
 * ..#.#...#..
 * ....#.#....
 * .##..S####.
 * .##..#...#.
 * .......##..
 * .##.#.####.
 * .##..##.##.
 * ...........
 * The Elf starts at the starting position (S) which also counts as a garden plot. Then, he can take one step north,
 * south, east, or west, but only onto tiles that are garden plots. This would allow him to reach any of the tiles
 * marked O:
 *
 * ...........
 * .....###.#.
 * .###.##..#.
 * ..#.#...#..
 * ....#O#....
 * .##.OS####.
 * .##..#...#.
 * .......##..
 * .##.#.####.
 * .##..##.##.
 * ...........
 * Then, he takes a second step. Since at this point he could be at either tile marked O, his second step would allow
 * him to reach any garden plot that is one step north, south, east, or west of any tile that he could have reached
 * after the first step:
 *
 * ...........
 * .....###.#.
 * .###.##..#.
 * ..#.#O..#..
 * ....#.#....
 * .##O.O####.
 * .##.O#...#.
 * .......##..
 * .##.#.####.
 * .##..##.##.
 * ...........
 * After two steps, he could be at any of the tiles marked O above, including the starting position (either by going
 * north-then-south or by going west-then-east).
 *
 * A single third step leads to even more possibilities:
 *
 * ...........
 * .....###.#.
 * .###.##..#.
 * ..#.#.O.#..
 * ...O#O#....
 * .##.OS####.
 * .##O.#...#.
 * ....O..##..
 * .##.#.####.
 * .##..##.##.
 * ...........
 * He will continue like this until his steps for the day have been exhausted. After a total of 6 steps, he could
 * reach any of the garden plots marked O:
 *
 * ...........
 * .....###.#.
 * .###.##.O#.
 * .O#O#O.O#..
 * O.O.#.#.O..
 * .##O.O####.
 * .##.O#O..#.
 * .O.O.O.##..
 * .##.#.####.
 * .##O.##.##.
 * ...........
 * In this example, if the Elf's goal was to get exactly 6 more steps today, he could use them to reach any of 16
 * garden plots.
 *
 * However, the Elf actually needs to get 64 steps today, and the map he's handed you is much larger than the example
 * map.
 *
 * Starting from the garden plot marked S on your map, how many garden plots could the Elf reach in exactly 64 steps?
 *
 * --- Part Two ---
 * The Elf seems confused by your answer until he realizes his mistake: he was reading from a list of his favorite
 * numbers that are both perfect squares and perfect cubes, not his step counter.
 *
 * The actual number of steps he needs to get today is exactly 26501365.
 *
 * He also points out that the garden plots and rocks are set up so that the map repeats infinitely in every direction.
 *
 * So, if you were to look one additional map-width or map-height out from the edge of the example map above, you
 * would find that it keeps repeating:
 *
 * .................................
 * .....###.#......###.#......###.#.
 * .###.##..#..###.##..#..###.##..#.
 * ..#.#...#....#.#...#....#.#...#..
 * ....#.#........#.#........#.#....
 * .##...####..##...####..##...####.
 * .##..#...#..##..#...#..##..#...#.
 * .......##.........##.........##..
 * .##.#.####..##.#.####..##.#.####.
 * .##..##.##..##..##.##..##..##.##.
 * .................................
 * .................................
 * .....###.#......###.#......###.#.
 * .###.##..#..###.##..#..###.##..#.
 * ..#.#...#....#.#...#....#.#...#..
 * ....#.#........#.#........#.#....
 * .##...####..##..S####..##...####.
 * .##..#...#..##..#...#..##..#...#.
 * .......##.........##.........##..
 * .##.#.####..##.#.####..##.#.####.
 * .##..##.##..##..##.##..##..##.##.
 * .................................
 * .................................
 * .....###.#......###.#......###.#.
 * .###.##..#..###.##..#..###.##..#.
 * ..#.#...#....#.#...#....#.#...#..
 * ....#.#........#.#........#.#....
 * .##...####..##...####..##...####.
 * .##..#...#..##..#...#..##..#...#.
 * .......##.........##.........##..
 * .##.#.####..##.#.####..##.#.####.
 * .##..##.##..##..##.##..##..##.##.
 * .................................
 * This is just a tiny three-map-by-three-map slice of the inexplicably-infinite farm layout; garden plots and rocks
 * repeat as far as you can see. The Elf still starts on the one middle tile marked S, though - every other repeated
 * S is replaced with a normal garden plot (.).
 *
 * Here are the number of reachable garden plots in this new infinite version of the example map for different numbers
 * of steps:
 *
 * In exactly 6 steps, he can still reach 16 garden plots.
 * In exactly 10 steps, he can reach any of 50 garden plots.
 * In exactly 50 steps, he can reach 1594 garden plots.
 * In exactly 100 steps, he can reach 6536 garden plots.
 * In exactly 500 steps, he can reach 167004 garden plots.
 * In exactly 1000 steps, he can reach 668697 garden plots.
 * In exactly 5000 steps, he can reach 16733044 garden plots.
 * However, the step count the Elf needs is much larger! Starting from the garden plot marked S on your infinite map,
 * how many garden plots could the Elf reach in exactly 26501365 steps?
 */
public class Day21 {


    /**
     * Given a number of steps to simulate and the input this simulates moving the given number of steps
     * and returns the number of locations you can reach
     * @param input Input to move across
     * @param steps Number of steps to move
     * @return The number of locations you can reach
     */
    public int findFinalCountOfStatesForStep(Discrete2DPositionGrid<Character> input, int steps) {
        Discrete2DPositionGrid<Character> newGrid = input.clone();
        Discrete2DPositionGrid<Character> cleanGrid = newGrid.clone();
        List<Point> sPoses = newGrid.getPositionsOfValue('S');
        newGrid.setValueAtPosition(sPoses.get(0), 'O');
        cleanGrid.setValueAtPosition(sPoses.get(0), '.');

        for(int day = 0; day < steps; day++) {
            Discrete2DPositionGrid<Character> todaysGrid = cleanGrid.clone();
            List<Point> oldPoints = newGrid.getPositionsOfValue('O');
            for(Point old : oldPoints) {
                List<Point> possibleMoves = newGrid.getDirectlyAdjacentRegardlessOfSize(old);
                for(Point possibleMove : possibleMoves) {
                    //Use floorMod to always get the positive modulo which is always inside our "first" grid
                    int realX = Math.floorMod(possibleMove.x, input.getWidth());
                    int realY = Math.floorMod(possibleMove.y, input.getHeight());

                    //So we can check the mapped position on the "original" grid without having to replicate it infinitely
                    if(newGrid.getValueAtPosition(realX, realY) != '#') {
                        //But then when we save our O we still use the real coordinates, so the map keeps getting bigger
                        todaysGrid.setValueAtPosition(possibleMove, 'O');
                    }
                }
            }
            newGrid = todaysGrid;
        }

        return newGrid.countInstancesOfValue('O');
    }

    /**
     * Work out how many different locatiosn we can get to after 64 steps
     * This is a basic brute force
     * @param input Map of the garden
     * @return Number of locations we can visit after 64 steps
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        return this.findFinalCountOfStatesForStep(input, 64);

    }

    /**
     * There are a few tricks that mean we can work this out without brute forcing it...
     *
     * First, that the visit region is always a nice diamond at best. What this means is you can easily calculate a
     * "region" where it will visit at most.
     * Second, the pattern nicely repeats with a border around everything.
     * Third, the row and column that you start on is always empty and this means you can move into any other
     * grid just by following this line.
     * Fourth, the step number is a bit of a weird value isn't it? Suggestive of an equation...
     * Fifth, my there are a lot of suggestions in the input, maybe hinting of an algorithm?
     *
     * This means actually the number of locations you can visit end up being a quadratic equation!
     * (which does make sense as there are two dimensions to move in)
     *
     * But how do we find that? Well luckily we can use
     * <a href="https://en.wikipedia.org/wiki/Newton_polynomial">Newton Polynomial</a>
     *
     * @param input Map of the garden
     * @return Number of locations we can visit after 64 steps
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        int stepNo = 26501365;

        int repeatingLength = input.getHeight();

        //Work out the "extra" height we need to go
        int remainderOfSizeOfArea = stepNo % repeatingLength;

        //These are step one, two, three of the quadratic formula to work out the final steps
        int stepOne = remainderOfSizeOfArea; // step 1
        int stepTwo = remainderOfSizeOfArea + repeatingLength; // Step 1 + n
        int stepThree = remainderOfSizeOfArea + (repeatingLength * 2); // Step 1 + 2n

        //Work out the three "steps" required for the Newton Polynomial
        List<Integer> steps = new ArrayList<>();
        steps.add(this.findFinalCountOfStatesForStep(input, stepOne));
        steps.add(this.findFinalCountOfStatesForStep(input, stepTwo));
        steps.add(this.findFinalCountOfStatesForStep(input, stepThree));

        long differenceBetweenFirstSteps = steps.get(1) - steps.get(0);
        long differenceBetweenSecondSteps = steps.get(2) - steps.get(1);
        long avalue = Math.floorDiv (differenceBetweenSecondSteps - differenceBetweenFirstSteps, 2);
        long bValue = differenceBetweenFirstSteps - (3*avalue);
        long cValue = steps.get(0) - bValue - avalue;
        long xValue = (long) Math.ceil(1.0*stepNo / repeatingLength); //1.0 needed here to force float division

        //And now we have our ax^2 + bx + c and we can work out the maths!
        return (avalue * (long)Math.pow(xValue, 2)) + (bValue * xValue) + cValue;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 21);

        Day21 d = new Day21();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of locations you can visit with 64 steps is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The number of locations you can visit with many steps is " + partTwo);
    }
}


