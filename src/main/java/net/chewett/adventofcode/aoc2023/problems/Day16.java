package net.chewett.adventofcode.aoc2023.problems;

import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.datastructures.Point3D;
import net.chewett.adventofcode.helpers.ListHelpers;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 16: The Floor Will Be Lava ---
 * With the beam of light completely focused somewhere, the reindeer leads you deeper still into the Lava Production
 * Facility. At some point, you realize that the steel facility walls have been replaced with cave, and the doorways
 * are just cave, and the floor is cave, and you're pretty sure this is actually just a giant cave.
 *
 * Finally, as you approach what must be the heart of the mountain, you see a bright light in a cavern up ahead.
 * There, you discover that the beam of light you so carefully focused is emerging from the cavern wall closest to
 * the facility and pouring all of its energy into a contraption on the opposite side.
 *
 * Upon closer inspection, the contraption appears to be a flat, two-dimensional square grid containing empty space (.),
 * mirrors (/ and \), and splitters (| and -).
 *
 * The contraption is aligned so that most of the beam bounces around the grid, but each tile on the grid converts some
 * of the beam's light into heat to melt the rock in the cavern.
 *
 * You note the layout of the contraption (your puzzle input). For example:
 *
 * .|...\....
 * |.-.\.....
 * .....|-...
 * ........|.
 * ..........
 * .........\
 * ..../.\\..
 * .-.-/..|..
 * .|....-|.\
 * ..//.|....
 * The beam enters in the top-left corner from the left and heading to the right. Then, its behavior depends on what
 * it encounters as it moves:
 *
 * If the beam encounters empty space (.), it continues in the same direction.
 * If the beam encounters a mirror (/ or \), the beam is reflected 90 degrees depending on the angle of the mirror.
 * For instance, a rightward-moving beam that encounters a / mirror would continue upward in the mirror's column, while
 * a rightward-moving beam that encounters a \ mirror would continue downward from the mirror's column.
 * If the beam encounters the pointy end of a splitter (| or -), the beam passes through the splitter as if the splitter
 * were empty space. For instance, a rightward-moving beam that encounters a - splitter would continue in the same
 * direction.
 * If the beam encounters the flat side of a splitter (| or -), the beam is split into two beams going in each of the
 * two directions the splitter's pointy ends are pointing. For instance, a rightward-moving beam that encounters a |
 * splitter would split into two beams: one that continues upward from the splitter's column and one that continues
 * downward from the splitter's column.
 * Beams do not interact with other beams; a tile can have many beams passing through it at the same time. A tile is
 * energized if that tile has at least one beam pass through it, reflect in it, or split in it.
 *
 * In the above example, here is how the beam of light bounces around the contraption:
 *
 * >|<<<\....
 * |v-.\^....
 * .v...|->>>
 * .v...v^.|.
 * .v...v^...
 * .v...v^..\
 * .v../2\\..
 * <->-/vv|..
 * .|<<<2-|.\
 * .v//.|.v..
 * Beams are only shown on empty tiles; arrows indicate the direction of the beams. If a tile contains beams moving in
 * multiple directions, the number of distinct directions is shown instead. Here is the same diagram but instead only
 * showing whether a tile is energized (#) or not (.):
 *
 * ######....
 * .#...#....
 * .#...#####
 * .#...##...
 * .#...##...
 * .#...##...
 * .#..####..
 * ########..
 * .#######..
 * .#...#.#..
 * Ultimately, in this example, 46 tiles become energized.
 *
 * The light isn't energizing enough tiles to produce lava; to debug the contraption, you need to start by analyzing
 * the current situation. With the beam starting in the top-left heading right, how many tiles end up being energized?
 *
 * --- Part Two ---
 * As you try to work out what might be wrong, the reindeer tugs on your shirt and leads you to a nearby control panel.
 * There, a collection of buttons lets you align the contraption so that the beam enters from any edge tile and heading
 * away from that edge. (You can choose either of two directions for the beam if it starts on a corner; for instance,
 * if the beam starts in the bottom-right corner, it can start heading either left or upward.)
 *
 * So, the beam could start on any tile in the top row (heading downward), any tile in the bottom row (heading upward),
 * any tile in the leftmost column (heading right), or any tile in the rightmost column (heading left). To produce
 * lava, you need to find the configuration that energizes as many tiles as possible.
 *
 * In the above example, this can be achieved by starting the beam in the fourth tile from the left in the top row:
 *
 * .|<2<\....
 * |v-v\^....
 * .v.v.|->>>
 * .v.v.v^.|.
 * .v.v.v^...
 * .v.v.v^..\
 * .v.v/2\\..
 * <-2-/vv|..
 * .|<<<2-|.\
 * .v//.|.v..
 * Using this configuration, 51 tiles are energized:
 *
 * .#####....
 * .#.#.#....
 * .#.#.#####
 * .#.#.##...
 * .#.#.##...
 * .#.#.##...
 * .#.#####..
 * ########..
 * .#######..
 * .#...#.#..
 * Find the initial beam configuration that energizes the largest number of tiles; how many tiles are energized in that configuration?
 */
public class Day16 {

    /**
     * Given a map and a starting point (and direction) trace the light sources to see what locations get energized
     * @param input Grid of mirrors
     * @param startingLoc Starting position and direction (the Z value of the Point3D)
     * @return Number of locations that are energized
     */
    public int energize(Discrete2DPositionGrid<Character> input, Point3D startingLoc) {
        Discrete2DPositionGrid<Character> energised = new Discrete2DPositionGrid<>('.');
        //Keep track of the places we have visited (alongside the direction when we visited it)
        //We need to keep track of direction plus position because each will produce a different value
        Set<Point3D> directionsVisited = new HashSet<>();
        Stack<Point3D> beamLocationsToExpand = new Stack<>();

        Point startLoc = new Point(startingLoc.getX(), startingLoc.getY());
        int startingDir = startingLoc.getZ();
        int otherStartingDir = -1;

        //The starting position might be a mirror so we need to change the direction in the event it is
        //If it's a splitter we might then have two directions to start from!
        char startingVal = input.getValueAtPosition(startLoc);
        if(startingDir == Directions.EAST) {
            if(startingVal == '|') {
                startingDir = Directions.SOUTH;
                otherStartingDir = Directions.NORTH;
            }else if(startingVal == '\\') {
                startingDir = Directions.SOUTH;
            }else if(startingVal == '/') {
                startingDir = Directions.NORTH;
            }

        }else if(startingDir == Directions.SOUTH) {
            if(startingVal == '-') {
                startingDir = Directions.WEST;
                otherStartingDir = Directions.EAST;
            }else if(startingVal == '\\') {
                startingDir = Directions.EAST;
            }else if(startingVal == '/') {
                startingDir = Directions.WEST;
            }

        }else if(startingDir == Directions.NORTH) {
            if(startingVal == '-') {
                startingDir = Directions.WEST;
                otherStartingDir = Directions.EAST;
            }else if(startingVal == '\\') {
                startingDir = Directions.WEST;
            }else if(startingVal == '/') {
                startingDir = Directions.EAST;
            }

        }else if(startingDir == Directions.WEST) {
            if(startingVal == '|') {
                startingDir = Directions.SOUTH;
                otherStartingDir = Directions.NORTH;
            }else if(startingVal == '\\') {
                startingDir = Directions.NORTH;
            }else if(startingVal == '/') {
                startingDir = Directions.SOUTH;
            }
        }

        //Starting position and direction
        beamLocationsToExpand.add(new Point3D(startLoc.x, startLoc.y, startingDir));
        //Handle if we split immediately and add a second starting point
        if(otherStartingDir != -1) {
            beamLocationsToExpand.add(new Point3D(startLoc.x, startLoc.y, otherStartingDir));
        }

        while(!beamLocationsToExpand.isEmpty()) {
            Point3D curLoc = beamLocationsToExpand.pop();
            //Track current location + direction as to whether we have visited it or not to stop loops
            if(directionsVisited.contains(curLoc)) {
                continue;
            }
            directionsVisited.add(curLoc);

            int direction = curLoc.getZ();
            Point curLocPoint = new Point(curLoc.getX(), curLoc.getY());
            energised.setValueAtPosition(curLoc.getX(), curLoc.getY(), '#');

            //Handle each direction and work out where we move to
            if(direction == Directions.SOUTH) {
                Point nextPoint = input.getSouth(curLocPoint);
                if(nextPoint != null) {
                    char nextPointChar = input.getValueAtPosition(nextPoint);
                    if(nextPointChar == '.') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '|') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '-') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.WEST));
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.EAST));
                    }else if(nextPointChar == '/') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.WEST));
                    }else if(nextPointChar == '\\') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.EAST));
                    }
                }
            }else if(direction == Directions.NORTH) {
                Point nextPoint = input.getNorth(curLocPoint);
                if(nextPoint != null) {
                    char nextPointChar = input.getValueAtPosition(nextPoint);
                    if(nextPointChar == '.') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '|') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '-') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.WEST));
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.EAST));
                    }else if(nextPointChar == '/') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.EAST));
                    }else if(nextPointChar == '\\') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.WEST));
                    }
                }
            }else if(direction == Directions.WEST) {
                Point nextPoint = input.getWest(curLocPoint);
                if(nextPoint != null) {
                    char nextPointChar = input.getValueAtPosition(nextPoint);
                    if(nextPointChar == '.') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '|') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.NORTH));
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.SOUTH));
                    }else if(nextPointChar == '-') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '/') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.SOUTH));
                    }else if(nextPointChar == '\\') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.NORTH));
                    }
                }
            }else if(direction == Directions.EAST) {
                Point nextPoint = input.getEast(curLocPoint);
                if(nextPoint != null) {
                    char nextPointChar = input.getValueAtPosition(nextPoint);
                    if(nextPointChar == '.') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '|') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.NORTH));
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.SOUTH));
                    }else if(nextPointChar == '-') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, direction));
                    }else if(nextPointChar == '/') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.NORTH));
                    }else if(nextPointChar == '\\') {
                        beamLocationsToExpand.add(new Point3D(nextPoint.x, nextPoint.y, Directions.SOUTH));
                    }
                }
            }else{
                //Debug statement just in the event something goes wrong
                throw new RuntimeException("wrong direction");
            }
        }

        //Count the number of energised locations
        return energised.countInstancesOfValue('#');
    }

    /**
     * Part one just starts top left and moves right and works out the energised value after the state
     * @param input Grid of mirrors
     * @return Number of energised locations
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> input) {
        return this.energize(input, new Point3D(0, 0, Directions.EAST));
    }

    /**
     * Work out the best way to cause the most number of states to be energised
     * @param input Grid of mirrors
     * @return The highest number of energised locations
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> input) {

        //Go over each possible location finding the energised value
        List<Integer> energisedVals = new ArrayList<>();
        for(int x = 0; x <= input.getMaxX(); x++) {
            energisedVals.add(this.energize(input, new Point3D(x, 0, Directions.SOUTH)));
            energisedVals.add(this.energize(input, new Point3D(x, input.getMaxY(), Directions.NORTH)));
        }
        for(int y = 0; y <= input.getMaxY(); y++) {
            energisedVals.add(this.energize(input, new Point3D(0, y, Directions.EAST)));
            energisedVals.add(this.energize(input, new Point3D(input.getMaxX(), y, Directions.WEST)));
        }

        //And then return the highest number
        return ListHelpers.findMax(energisedVals);
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> input = ProblemLoader.loadProblemIntoDiscrete2DPositionCharacterGrid(2023, 16);

        Day16 d = new Day16();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of energised states is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The highest number of energised states possible is " + partTwo);
    }
}


