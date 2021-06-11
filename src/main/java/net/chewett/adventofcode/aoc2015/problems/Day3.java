package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 *
 * He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him
 * via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>),
 * or west (<). After each move, he delivers another present to the house at his new location.
 *
 * However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off, and
 * Santa ends up visiting some houses more than once. How many houses receive at least one present?
 *
 * For example:
 *
 * > delivers presents to 2 houses: one at the starting location, and one to the east.
 * ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 * ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 *
 * --- Part Two ---
 * The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents
 * with him.
 *
 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns
 * moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.
 *
 * This year, how many houses receive at least one present?
 *
 * For example:
 *
 * ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
 * ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
 * ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
 */
public class Day3 {

    /**
     * Given a path representing how Santa moves it will keep track of which houses
     * he has visited and return the number of houses visited once or more
     * @param path Path that Santa should visit
     * @return Number of houses Santa visited once or more
     */
    public long solvePartOne(String path) {
        int x = 0;
        int y = 0;
        Map<Integer, Map<Integer, Boolean>> visitedHouses = new HashMap<>();
        visitedHouses.put(0, new HashMap<>());
        visitedHouses.get(0).put(0, true);
        for(int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if(c == '<') {
                x--;
            }else if(c == '>') {
                x++;
            }else if(c == 'v') {
                y--;
            }else{
                y++;
            }

            if(!visitedHouses.containsKey(x)) {
                visitedHouses.put(x, new HashMap<>());
            }

            visitedHouses.get(x).put(y, true);
        }

        int totalVisited = 0;
        for(Map.Entry<Integer, Map<Integer, Boolean>> x1 : visitedHouses.entrySet()) {
            for(Map.Entry<Integer, Boolean> y1 :x1.getValue().entrySet()) {
                if(y1.getValue()) {
                    totalVisited++;
                }
            }
        }

        return totalVisited;
    }


    /**
     * Given a path Santa and Robo Santa need to visit this will return the number
     * of houses visited once or more
     * @param path Path for Santa and Robo Santa to take
     * @return The number of houses visited once or more
     */
    public long solvePartTwo(String path) {
        Map<Integer, Map<Integer, Boolean>> visitedHouses = new HashMap<>();
        visitedHouses.put(0, new HashMap<>());
        visitedHouses.get(0).put(0, true);

        //Actually we can just keep the same solution and run it twice starting at a different number
        for(int startingNumber = 0; startingNumber < 2; startingNumber++) {
            int x = 0;
            int y = 0;

            //Here the only change is that we increment by two each time and start on the given number
            for (int i = startingNumber; i < path.length(); i += 2) {
                char c = path.charAt(i);
                if (c == '<') {
                    x--;
                } else if (c == '>') {
                    x++;
                } else if (c == 'v') {
                    y--;
                } else {
                    y++;
                }

                if (!visitedHouses.containsKey(x)) {
                    visitedHouses.put(x, new HashMap<>());
                }

                visitedHouses.get(x).put(y, true);
            }
        }

        int totalVisited = 0;
        for(Map.Entry<Integer, Map<Integer, Boolean>> x1 : visitedHouses.entrySet()) {
            for(Map.Entry<Integer, Boolean> y1 :x1.getValue().entrySet()) {
                if(y1.getValue()) {
                    totalVisited++;
                }
            }
        }

        return totalVisited;
    }

    public static void main(String[] args) {
        String input = ProblemLoader.loadProblemIntoString(2015, 3);
        Day3 d = new Day3();
        long numberOfHousesWithAPresentOrMore = d.solvePartOne(input);
        System.out.println("Santa has visited " + numberOfHousesWithAPresentOrMore + " houses once or more");
        long numberOfHousesWithAPresentOrMorePartTwo = d.solvePartTwo(input);
        System.out.println("Santa and robo santa have visited " + numberOfHousesWithAPresentOrMorePartTwo + " houses once or more");
    }

}
