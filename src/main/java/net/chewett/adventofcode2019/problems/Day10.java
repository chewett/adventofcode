package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.Asteroid;

import java.util.*;

/**
 * Taken from: https://adventofcode.com/2019/day/10
 *
 * --- Day 10: Monitoring Station ---
 * You fly into the asteroid belt and reach the Ceres monitoring station. The Elves here have an emergency: they're having trouble tracking all of the asteroids and can't be sure they're safe.
 *
 * The Elves would like to build a new monitoring station in a nearby area of space; they hand you a map of all of the asteroids in that region (your puzzle input).
 *
 * The map indicates whether each position is empty (.) or contains an asteroid (#). The asteroids are much smaller than they appear on the map, and every asteroid is exactly in the center of its marked position. The asteroids can be described with X,Y coordinates where X is the distance from the left edge and Y is the distance from the top edge (so the top-left corner is 0,0 and the position immediately to its right is 1,0).
 *
 * Your job is to figure out which asteroid would be the best place to build a new monitoring station. A monitoring station can detect any asteroid to which it has direct line of sight - that is, there cannot be another asteroid exactly between them. This line of sight can be at any angle, not just lines aligned to the grid or diagonally. The best location is the asteroid that can detect the largest number of other asteroids.
 *
 * For example, consider the following map:
 *
 * .#..#
 * .....
 * #####
 * ....#
 * ...##
 * The best location for a new monitoring station on this map is the highlighted asteroid at 3,4 because it can detect 8 asteroids, more than any other location. (The only asteroid it cannot detect is the one at 1,0; its view of this asteroid is blocked by the asteroid at 2,2.) All other asteroids are worse locations; they can detect 7 or fewer other asteroids. Here is the number of other asteroids a monitoring station on each asteroid could detect:
 *
 * .7..7
 * .....
 * 67775
 * ....7
 * ...87
 * Here is an asteroid (#) and some examples of the ways its line of sight might be blocked. If there were another asteroid at the location of a capital letter, the locations marked with the corresponding lowercase letter would be blocked and could not be detected:
 *
 * #.........
 * ...A......
 * ...B..a...
 * .EDCG....a
 * ..F.c.b...
 * .....c....
 * ..efd.c.gb
 * .......c..
 * ....f...c.
 * ...e..d..c
 * Here are some larger examples:
 *
 * Best is 5,8 with 33 other asteroids detected:
 *
 * ......#.#.
 * #..#.#....
 * ..#######.
 * .#.#.###..
 * .#..#.....
 * ..#....#.#
 * #..#....#.
 * .##.#..###
 * ##...#..#.
 * .#....####
 * Best is 1,2 with 35 other asteroids detected:
 *
 * #.#...#.#.
 * .###....#.
 * .#....#...
 * ##.#.#.#.#
 * ....#.#.#.
 * .##..###.#
 * ..#...##..
 * ..##....##
 * ......#...
 * .####.###.
 * Best is 6,3 with 41 other asteroids detected:
 *
 * .#..#..###
 * ####.###.#
 * ....###.#.
 * ..###.##.#
 * ##.##.#.#.
 * ....###..#
 * ..#.#..#.#
 * #..#.#.###
 * .##...##.#
 * .....#.#..
 * Best is 11,13 with 210 other asteroids detected:
 *
 * .#..##.###...#######
 * ##.############..##.
 * .#.######.########.#
 * .###.#######.####.#.
 * #####.##.#.##.###.##
 * ..#####..#.#########
 * ####################
 * #.####....###.#.#.##
 * ##.#################
 * #####.##.###..####..
 * ..######..##.#######
 * ####.##.####...##..#
 * .#####..#.######.###
 * ##...#.##########...
 * #.##########.#######
 * .####.#.###.###.#.##
 * ....##.##.###..#####
 * .#.#.###########.###
 * #.#.#.#####.####.###
 * ###.##.####.##.#..##
 * Find the best location for a new monitoring station. How many other asteroids can be detected from that location?
 *
 * --- Part Two ---
 * Once you give them the coordinates, the Elves quickly deploy an Instant Monitoring Station to the location and discover the worst: there are simply too many asteroids.
 *
 * The only solution is complete vaporization by giant laser.
 *
 * Fortunately, in addition to an asteroid scanner, the new monitoring station also comes equipped with a giant rotating laser perfect for vaporizing asteroids. The laser starts by pointing up and always rotates clockwise, vaporizing any asteroid it hits.
 *
 * If multiple asteroids are exactly in line with the station, the laser only has enough power to vaporize one of them before continuing its rotation. In other words, the same asteroids that can be detected can be vaporized, but if vaporizing one asteroid makes another one detectable, the newly-detected asteroid won't be vaporized until the laser has returned to the same position by rotating a full 360 degrees.
 *
 * For example, consider the following map, where the asteroid with the new monitoring station (and laser) is marked X:
 *
 * .#....#####...#..
 * ##...##.#####..##
 * ##...#...#.#####.
 * ..#.....X...###..
 * ..#.#.....#....##
 * The first nine asteroids to get vaporized, in order, would be:
 *
 * .#....###24...#..
 * ##...##.13#67..9#
 * ##...#...5.8####.
 * ..#.....X...###..
 * ..#.#.....#....##
 * Note that some asteroids (the ones behind the asteroids marked 1, 5, and 7) won't have a chance to be vaporized until the next full rotation. The laser continues rotating; the next nine to be vaporized are:
 *
 * .#....###.....#..
 * ##...##...#.....#
 * ##...#......1234.
 * ..#.....X...5##..
 * ..#.9.....8....76
 * The next nine to be vaporized are then:
 *
 * .8....###.....#..
 * 56...9#...#.....#
 * 34...7...........
 * ..2.....X....##..
 * ..1..............
 * Finally, the laser completes its first full rotation (1 through 3), a second rotation (4 through 8), and vaporizes the last asteroid (9) partway through its third rotation:
 *
 * ......234.....6..
 * ......1...5.....7
 * .................
 * ........X....89..
 * .................
 * In the large example above (the one with the best monitoring station location at 11,13):
 *
 * The 1st asteroid to be vaporized is at 11,12.
 * The 2nd asteroid to be vaporized is at 12,1.
 * The 3rd asteroid to be vaporized is at 12,2.
 * The 10th asteroid to be vaporized is at 12,8.
 * The 20th asteroid to be vaporized is at 16,0.
 * The 50th asteroid to be vaporized is at 16,9.
 * The 100th asteroid to be vaporized is at 10,16.
 * The 199th asteroid to be vaporized is at 9,6.
 * The 200th asteroid to be vaporized is at 8,2.
 * The 201st asteroid to be vaporized is at 10,9.
 * The 299th and final asteroid to be vaporized is at 11,1.
 * The Elves are placing bets on which will be the 200th asteroid to be vaporized. Win the bet by determining which asteroid that will be; what do you get if you multiply its X coordinate by 100 and then add its Y coordinate? (For example, 8,2 becomes 802.)
 *
 */
public class Day10 {

    public static void main(String[] args) {
        String[] asteroidsOne = new String[] {
                ".#..#",
                ".....",
                "#####",
                "....#",
                "...##"
        };

        asteroidsOne = new String[] {
            "......#.#.",
            "#..#.#....",
            "..#######.",
            ".#.#.###..",
            ".#..#.....",
            "..#....#.#",
            "#..#....#.",
            ".##.#..###",
            "##...#..#.",
            ".#....####"
        };
        asteroidsOne = new String[] {
            "#.#...#.#.",
            ".###....#.",
            ".#....#...",
            "##.#.#.#.#",
            "....#.#.#.",
            ".##..###.#",
            "..#...##..",
            "..##....##",
            "......#...",
            ".####.###.",
        };
        asteroidsOne = new String[] {
            ".#..#..###",
            "####.###.#",
            "....###.#.",
            "..###.##.#",
            "##.##.#.#.",
            "....###..#",
            "..#.#..#.#",
            "#..#.#.###",
            ".##...##.#",
            ".....#.#..",
        };
        asteroidsOne = new String[] {
            ".#..##.###...#######",
            "##.############..##.",
            ".#.######.########.#",
            ".###.#######.####.#.",
            "#####.##.#.##.###.##",
            "..#####..#.#########",
            "####################",
            "#.####....###.#.#.##",
            "##.#################",
            "#####.##.###..####..",
            "..######..##.#######",
            "####.##.####...##..#",
            ".#####..#.######.###",
            "##...#.##########...",
            "#.##########.#######",
            ".####.#.###.###.#.##",
            "....##.##.###..#####",
            ".#.#.###########.###",
            "#.#.#.#####.####.###",
            "###.##.####.##.#..##",
        };

        //This is the actual problem.
        asteroidsOne = new String[] {
            "##.##..#.####...#.#.####",
            "##.###..##.#######..##..",
            "..######.###.#.##.######",
            ".#######.####.##.#.###.#",
            "..#...##.#.....#####..##",
            "#..###.#...#..###.#..#..",
            "###..#.##.####.#..##..##",
            ".##.##....###.#..#....#.",
            "########..#####..#######",
            "##..#..##.#..##.#.#.#..#",
            "##.#.##.######.#####....",
            "###.##...#.##...#.######",
            "###...##.####..##..#####",
            "##.#...#.#.....######.##",
            ".#...####..####.##...##.",
            "#.#########..###..#.####",
            "#.##..###.#.######.#####",
            "##..##.##...####.#...##.",
            "###...###.##.####.#.##..",
            "####.#.....###..#.####.#",
            "##.####..##.#.##..##.#.#",
            "#####..#...####..##..#.#",
            ".##.##.##...###.##...###",
            "..###.########.#.###..#."
        };
        List<Asteroid> field = Asteroid.parseFromField(asteroidsOne);

        int maxAsteroidsFound = 0;
        Asteroid bestOneForStation = field.get(0);
        //Loop over every asteroid
        for(Asteroid a :field) {
            //I like this way of doing it. An asteroid is only blocking another one if the angle of each asteroid
            //is identical. Therefore if we store all unique angles of every asteroid, we can find out how many unique
            //different asteroids it can see. Therefore use a hash set of and just store all the angles!
            Set<Double> seenAsteroids = new HashSet<>();
            for(Asteroid a2: field) {
                if(a.equals(a2)) { //Ignore itself when looping over the data.
                    continue;
                }
                //Calculcate the angle and store it (a set wont store the duplicates so dont bother checking)
                seenAsteroids.add(a.getAngleToAsteroid(a2));
            }
            //If we have found more this time, store the new max, and the best one for a station (to be used later!)
            if(maxAsteroidsFound < seenAsteroids.size()) {
                maxAsteroidsFound = seenAsteroids.size();
                bestOneForStation = a;
            }
        }

        //Answer for part 1.
        System.out.println("The best station can see " + maxAsteroidsFound + " asteroids directly");

        //Store a queue of asteroid in a key which is the angle to the asteroid.
        //This lets me know every asteroid that is on each angle. The queue will be used later!
        Map<Double, Queue<Asteroid>> angleMapping = new HashMap<>();
        for(Asteroid a : field) {
            if(bestOneForStation.equals(a)) { //Again ignore the best station for this.
                continue;
            }

            //Work out the angle, store it in the queue (creating it if we need to) and also store the distance to the observation
            //station in the asteroid. We will use this in a minute.
            double angle = bestOneForStation.getAngleToAsteroid(a);
            if(!angleMapping.containsKey(angle)) {
                angleMapping.put(angle, new LinkedList<>());
            }
            double distanceTo = bestOneForStation.getDistanceTo(a);
            a.storeDistanceToStation(distanceTo);
            angleMapping.get(angle).add(a);

        }

        //Get every angle out of the mapping and convert that to an array list. Once converted sort it in an ascending order.
        Set<Double> allAngles = angleMapping.keySet();
        List<Double> allAnglesSort = new ArrayList<>(allAngles);
        Collections.sort(allAnglesSort);

        //Loop over every angle, taking out the queue, converting it an an array list, sorting it, then putting the result back in as a queue.
        //This leaves the map with queues which are ordered in ascending distance to the base station. This is important for the next step.
        for(double d : allAnglesSort) {
            Queue<Asteroid> asteroidsToOrder = angleMapping.get(d);
            List<Asteroid> asteroidsToOrderList = new ArrayList<>(asteroidsToOrder);
            Collections.sort(asteroidsToOrderList);
            angleMapping.put(d, new LinkedList<>(asteroidsToOrderList));
        }

        //Right, this is the clever bit:
        //Loop over every angle (using the sorted list of angles from 0 -> 359.99 if there is one).
        //For each queue, if there is an element in the queue pop it off and increment the astroid number.
        //If the asteroid number is 200, print out the number and finish.
        //If it isnt 200, continue popping the next double. Keep doing this until the 200th asteroid is found.
        //This works because the queues are sorted in distance order which means the closer "first ones it can see" are popped off first.
        //Since the asteroid laser destroys from the 0 angle to 359.99 angle this looping over the angles sorted emulates this behaviour.
        int asteroidNo = 0;
        while(asteroidNo < 200) {
            for(double d : allAnglesSort) {
                if(angleMapping.get(d).size() > 0) {
                    asteroidNo++;
                    Asteroid a = angleMapping.get(d).remove();
                    if(asteroidNo == 200) {
                        System.out.println(a.getAsteroidNumber());

                        return;
                    }
                }
            }
        }
    }

}
