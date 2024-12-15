package net.chewett.adventofcode.aoc2024.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 8: Resonant Collinearity ---
 * You find yourselves on the roof of a top-secret Easter Bunny installation.
 *
 * While The Historians do their thing, you take a look at the familiar huge antenna. Much to your surprise, it seems
 * to have been reconfigured to emit a signal that makes people 0.1% more likely to buy Easter Bunny brand Imitation
 * Mediocre Chocolate as a Christmas gift! Unthinkable!
 *
 * Scanning across the city, you find that there are actually many such antennas. Each antenna is tuned to a specific
 * frequency indicated by a single lowercase letter, uppercase letter, or digit. You create a map (your puzzle input)
 * of these antennas. For example:
 *
 * ............
 * ........0...
 * .....0......
 * .......0....
 * ....0.......
 * ......A.....
 * ............
 * ............
 * ........A...
 * .........A..
 * ............
 * ............
 * The signal only applies its nefarious effect at specific antinodes based on the resonant frequencies of the
 * antennas. In particular, an antinode occurs at any point that is perfectly in line with two antennas of the same
 * frequency - but only when one of the antennas is twice as far away as the other. This means that for any pair of
 * antennas with the same frequency, there are two antinodes, one on either side of them.
 *
 * So, for these two antennas with frequency a, they create the two antinodes marked with #:
 *
 * ..........
 * ...#......
 * ..........
 * ....a.....
 * ..........
 * .....a....
 * ..........
 * ......#...
 * ..........
 * ..........
 * Adding a third antenna with the same frequency creates several more antinodes. It would ideally add four antinodes,
 * but two are off the right side of the map, so instead it adds only two:
 *
 * ..........
 * ...#......
 * #.........
 * ....a.....
 * ........a.
 * .....a....
 * ..#.......
 * ......#...
 * ..........
 * ..........
 * Antennas with different frequencies don't create antinodes; A and a count as different frequencies. However,
 * antinodes can occur at locations that contain antennas. In this diagram, the lone antenna with frequency capital A
 * creates no antinodes but has a lowercase-a-frequency antinode at its location:
 *
 * ..........
 * ...#......
 * #.........
 * ....a.....
 * ........a.
 * .....a....
 * ..#.......
 * ......A...
 * ..........
 * ..........
 * The first example has antennas with two different frequencies, so the antinodes they create look like this, plus an
 * antinode overlapping the topmost A-frequency antenna:
 *
 * ......#....#
 * ...#....0...
 * ....#0....#.
 * ..#....0....
 * ....0....#..
 * .#....A.....
 * ...#........
 * #......#....
 * ........A...
 * .........A..
 * ..........#.
 * ..........#.
 * Because the topmost A-frequency antenna overlaps with a 0-frequency antinode, there are 14 total unique locations
 * that contain an antinode within the bounds of the map.
 *
 * Calculate the impact of the signal. How many unique locations within the bounds of the map contain an antinode?
 *
 * --- Part Two ---
 * Watching over your shoulder as you work, one of The Historians asks if you took the effects of resonant harmonics
 * into your calculations.
 *
 * Whoops!
 *
 * After updating your model, it turns out that an antinode occurs at any grid position exactly in line with at least
 * two antennas of the same frequency, regardless of distance. This means that some of the new antinodes will occur at
 * the position of each antenna (unless that antenna is the only one of its frequency).
 *
 * So, these three T-frequency antennas now create many antinodes:
 *
 * T....#....
 * ...T......
 * .T....#...
 * .........#
 * ..#.......
 * ..........
 * ...#......
 * ..........
 * ....#.....
 * ..........
 * In fact, the three T-frequency antennas are all exactly in line with two antennas, so they are all also antinodes!
 * This brings the total number of antinodes in the above example to 9.
 *
 * The original example now has 34 antinodes, including the antinodes that appear on every antenna:
 *
 * ##....#....#
 * .#.#....0...
 * ..#.#0....#.
 * ..##...0....
 * ....0....#..
 * .#...#A....#
 * ...#..#.....
 * #....#.#....
 * ..#.....A...
 * ....#....A..
 * .#........#.
 * ...#......##
 * Calculate the impact of the signal using this updated model. How many unique locations within the bounds of the map
 * contain an antinode?
 *
 */
public class Day8 {

    /**
     * Work out the number of antinodes on my map
     * @param input Map of antennas
     * @return Number of antinodes
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Character> antinodes = input.clone();

        Set<Character> distinctAntennas = new HashSet<>(input.getAllValuesStored());
        distinctAntennas.remove('.');

        for(char antennaType : distinctAntennas) {
            List<Point> antennas = input.getPositionsOfValue(antennaType);

            for(int i = 0; i < antennas.size(); i++) {
                //Starting at i+1 means we never overlap the same thing twice
                for(int j = i+1; j < antennas.size(); j++) {
                    Point antennaA = antennas.get(i);
                    Point antennaB = antennas.get(j);

                    int xDiff = antennaA.x - antennaB.x;
                    int yDiff = antennaA.y - antennaB.y;

                    //Work out the antinodes and set them if they are inside the map
                    Point aAntinode = new Point(antennaA.x + xDiff, antennaA.y + yDiff);
                    Point bAntinode = new Point(antennaB.x - xDiff, antennaB.y - yDiff);

                    if(antinodes.getValueAtPosition(aAntinode) != ' ') {
                        antinodes.setValueAtPosition(aAntinode, '#');
                    }
                    if(antinodes.getValueAtPosition(bAntinode) != ' ') {
                        antinodes.setValueAtPosition(bAntinode, '#');
                    }
                }
            }
        }

        return antinodes.countInstancesOfValue('#');
    }

    /**
     * Work out the number of antinodes on my map with the new knowledge about antinodes
     * @param input Map of antennas
     * @return Number of antinodes
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {
        Discrete2DPositionGrid<Character> antinodes = input.clone();

        Set<Character> distinctAntennas = new HashSet<>(input.getAllValuesStored());
        distinctAntennas.remove('.');

        for(char antennaType : distinctAntennas) {
            List<Point> antennas = input.getPositionsOfValue(antennaType);
            for(int i = 0; i < antennas.size(); i++) {
                //Starting at i+1 means we never overlap the same thing twice
                for(int j = i+1; j < antennas.size(); j++) {
                    Point antennaA = antennas.get(i);
                    Point antennaB = antennas.get(j);

                    int xDiff = antennaA.x - antennaB.x;
                    int yDiff = antennaA.y - antennaB.y;

                    //If they are non-zero they could be a multiple of each other so use greatest common divisor
                    if(xDiff != 0 && yDiff != 0) {
                        int gcd = ArithmeticUtils.gcd(xDiff, yDiff);
                        xDiff /= gcd;
                        yDiff /= gcd;
                    }

                    //Then just mark all the antinodes on the line
                    Point antinode = new Point(antennaA.x, antennaA.y);
                    while(antinodes.getValueAtPosition(antinode) != ' ') {
                        antinodes.setValueAtPosition(antinode, '#');
                        antinode.x += xDiff;
                        antinode.y += yDiff;
                    }
                    antinode = new Point(antennaA.x - xDiff, antennaA.y - yDiff);
                    while(antinodes.getValueAtPosition(antinode) != ' ') {
                        antinodes.setValueAtPosition(antinode, '#');
                        antinode.x -= xDiff;
                        antinode.y -= yDiff;
                    }
                }
            }
        }

        return antinodes.countInstancesOfValue('#');
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2024, 8);

        Day8 d = new Day8();
        long partOne = d.solvePartOne(input);
        System.out.println("There are " + partOne + " antinodes");

        long partTwo = d.solvePartTwo(input);
        System.out.println("There are " + partTwo + " antinodes after adjusting the calulations");
    }
}


