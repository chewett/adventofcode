package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.List;


/**
 * --- Day 3: Toboggan Trajectory ---
 * With the toboggan login problems resolved, you set off toward the airport. While travel by toboggan might be easy,
 * it's certainly not safe: there's very minimal steering and the area is covered in trees. You'll need to see which
 * angles will take you near the fewest trees.
 *
 * Due to the local geology, trees in this area only grow on exact integer coordinates in a grid. You make a map (your
 * puzzle input) of the open squares (.) and trees (#) you can see. For example:
 *
 * ..##.......
 * #...#...#..
 * .#....#..#.
 * ..#.#...#.#
 * .#...##..#.
 * ..#.##.....
 * .#.#.#....#
 * .#........#
 * #.##...#...
 * #...##....#
 * .#..#...#.#
 * These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome
 * stability, the same pattern repeats to the right many times:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........#.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...##....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 * You start on the open square (.) in the top-left corner and need to reach the bottom (below the bottom-most row on
 * your map).
 *
 * The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers rational numbers);
 * start by counting all the trees you would encounter for the slope right 3, down 1:
 *
 * From your starting position at the top-left, check the position that is right 3 and down 1. Then, check the position
 * that is right 3 and down 1 from there, and so on until you go past the bottom of the map.
 *
 * The locations you'd check in the above example are marked here with O where there was an open square and X where
 * there was a tree:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........X.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...#X....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 * In this example, traversing the map using this slope would cause you to encounter 7 trees.
 *
 * Starting at the top-left corner of your map and following a slope of right 3 and down 1, how many trees would you
 * encounter?
 *
 * Your puzzle answer was 178.
 *
 * --- Part Two ---
 * Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.
 *
 * Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left
 * corner and traverse the map all the way to the bottom:
 *
 * Right 1, down 1.
 * Right 3, down 1. (This is the slope you already checked.)
 * Right 5, down 1.
 * Right 7, down 1.
 * Right 1, down 2.
 * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these
 * produce the answer 336.
 *
 * What do you get if you multiply together the number of trees encountered on each of the listed slopes?
 */
public class Day3 {

    private int getHits(List<List<Character>> slope, int xPosMove, int yPosMove) {
        int x = 0; int y = 0;

        int hits = 0;
        while(y < (slope.size()-1)) {
            //Move based on the X/Y pos movement each time, keep going until we hit the end
            x += xPosMove;
            y += yPosMove;

            //Ensure we loop around every time we go off the end
            if(x > (slope.get(y).size()-1)) {
                x -= slope.get(y).size();
            }

            //Ouch, we hit a tree!
            if(slope.get(y).get(x) == '#') {
                hits++;
            }
        }
        return hits;
    }

    public int solvePartOne(List<List<Character>> slope) {
        return this.getHits(slope, 3, 1);
    }

   public long solvePartTwo(List<List<Character>> slope) {
        int[][] slopesToCheck = new int[][]{ {1,1}, {3,1}, {5,1}, {7,1}, {1, 2} };

        long hitsMultiplied = 1L;
        for(int[] movement : slopesToCheck) {
            int hits = this.getHits(slope, movement[0], movement[1]);
            hitsMultiplied *= hits;
        }

        return hitsMultiplied;
    }

    public static void main(String[] args) {
        Day3 d = new Day3();
        List<List<Character>> slope = ProblemLoader.loadProblemIntoXYCharList(2020, 3);
        int partOneHits = d.solvePartOne(slope);
        System.out.println("Part one hits: " + partOneHits);

        long partTwoHits = d.solvePartTwo(slope);
        System.out.println("Part two hits multiplied together: " + partTwoHits);
    }

}
