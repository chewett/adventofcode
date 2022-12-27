package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * --- Day 23: Unstable Diffusion ---
 * You enter a large crater of gray dirt where the grove is supposed to be. All around you, plants you imagine were
 * expected to be full of fruit are instead withered and broken. A large group of Elves has formed in the middle of
 * the grove.
 *
 * "...but this volcano has been dormant for months. Without ash, the fruit can't grow!"
 *
 * You look up to see a massive, snow-capped mountain towering above you.
 *
 * "It's not like there are other active volcanoes here; we've looked everywhere."
 *
 * "But our scanners show active magma flows; clearly it's going somewhere."
 *
 * They finally notice you at the edge of the grove, your pack almost overflowing from the random star fruit you've
 * been collecting. Behind you, elephants and monkeys explore the grove, looking concerned. Then, the Elves recognize
 * the ash cloud slowly spreading above your recent detour.
 *
 * "Why do you--" "How is--" "Did you just--"
 *
 * Before any of them can form a complete question, another Elf speaks up: "Okay, new plan. We have almost enough fruit
 * already, and ash from the plume should spread here eventually. If we quickly plant new seedlings now, we can still
 * make it to the extraction point. Spread out!"
 *
 * The Elves each reach into their pack and pull out a tiny plant. The plants rely on important nutrients from the ash,
 * so they can't be planted too close together.
 *
 * There isn't enough time to let the Elves figure out where to plant the seedlings themselves; you quickly scan the
 * grove (your puzzle input) and note their positions.
 *
 * For example:
 *
 * ....#..
 * ..###.#
 * #...#.#
 * .#...##
 * #.###..
 * ##.#.##
 * .#..#..
 * The scan shows Elves # and empty ground .; outside your scan, more empty ground extends a long way in every
 * direction. The scan is oriented so that north is up; orthogonal directions are written N (north), S (south),
 * W (west), and E (east), while diagonal directions are written NE, NW, SE, SW.
 *
 * The Elves follow a time-consuming process to figure out where they should each go; you can speed up this process
 * considerably. The process consists of some number of rounds during which Elves alternate between considering where
 * to move and actually moving.
 *
 * During the first half of each round, each Elf considers the eight positions adjacent to themself. If no other Elves
 * are in one of those eight positions, the Elf does not do anything during this round. Otherwise, the Elf looks in
 * each of four directions in the following order and proposes moving one step in the first valid direction:
 *
 * If there is no Elf in the N, NE, or NW adjacent positions, the Elf proposes moving north one step.
 * If there is no Elf in the S, SE, or SW adjacent positions, the Elf proposes moving south one step.
 * If there is no Elf in the W, NW, or SW adjacent positions, the Elf proposes moving west one step.
 * If there is no Elf in the E, NE, or SE adjacent positions, the Elf proposes moving east one step.
 * After each Elf has had a chance to propose a move, the second half of the round can begin. Simultaneously, each Elf
 * moves to their proposed destination tile if they were the only Elf to propose moving to that position. If two or
 * more Elves propose moving to the same position, none of those Elves move.
 *
 * Finally, at the end of the round, the first direction the Elves considered is moved to the end of the list of
 * directions. For example, during the second round, the Elves would try proposing a move to the south first, then
 * west, then east, then north. On the third round, the Elves would first consider west, then east, then north, then
 * south.
 *
 * As a smaller example, consider just these five Elves:
 *
 * .....
 * ..##.
 * ..#..
 * .....
 * ..##.
 * .....
 * The northernmost two Elves and southernmost two Elves all propose moving north, while the middle Elf cannot move
 * north and proposes moving south. The middle Elf proposes the same destination as the southwest Elf, so neither of
 * them move, but the other three do:
 *
 * ..##.
 * .....
 * ..#..
 * ...#.
 * ..#..
 * .....
 * Next, the northernmost two Elves and the southernmost Elf all propose moving south. Of the remaining middle two
 * Elves, the west one cannot move south and proposes moving west, while the east one cannot move south or west and
 * proposes moving east. All five Elves succeed in moving to their proposed positions:
 *
 * .....
 * ..##.
 * .#...
 * ....#
 * .....
 * ..#..
 * Finally, the southernmost two Elves choose not to move at all. Of the remaining three Elves, the west one proposes
 * moving west, the east one proposes moving east, and the middle one proposes moving north; all three succeed in
 * moving:
 *
 * ..#..
 * ....#
 * #....
 * ....#
 * .....
 * ..#..
 * At this point, no Elves need to move, and so the process ends.
 *
 * The larger example above proceeds as follows:
 *
 * == Initial State ==
 * ..............
 * ..............
 * .......#......
 * .....###.#....
 * ...#...#.#....
 * ....#...##....
 * ...#.###......
 * ...##.#.##....
 * ....#..#......
 * ..............
 * ..............
 * ..............
 *
 * == End of Round 1 ==
 * ..............
 * .......#......
 * .....#...#....
 * ...#..#.#.....
 * .......#..#...
 * ....#.#.##....
 * ..#..#.#......
 * ..#.#.#.##....
 * ..............
 * ....#..#......
 * ..............
 * ..............
 *
 * == End of Round 2 ==
 * ..............
 * .......#......
 * ....#.....#...
 * ...#..#.#.....
 * .......#...#..
 * ...#..#.#.....
 * .#...#.#.#....
 * ..............
 * ..#.#.#.##....
 * ....#..#......
 * ..............
 * ..............
 *
 * == End of Round 3 ==
 * ..............
 * .......#......
 * .....#....#...
 * ..#..#...#....
 * .......#...#..
 * ...#..#.#.....
 * .#..#.....#...
 * .......##.....
 * ..##.#....#...
 * ...#..........
 * .......#......
 * ..............
 *
 * == End of Round 4 ==
 * ..............
 * .......#......
 * ......#....#..
 * ..#...##......
 * ...#.....#.#..
 * .........#....
 * .#...###..#...
 * ..#......#....
 * ....##....#...
 * ....#.........
 * .......#......
 * ..............
 *
 * == End of Round 5 ==
 * .......#......
 * ..............
 * ..#..#.....#..
 * .........#....
 * ......##...#..
 * .#.#.####.....
 * ...........#..
 * ....##..#.....
 * ..#...........
 * ..........#...
 * ....#..#......
 * ..............
 * After a few more rounds...
 *
 * == End of Round 10 ==
 * .......#......
 * ...........#..
 * ..#.#..#......
 * ......#.......
 * ...#.....#..#.
 * .#......##....
 * .....##.......
 * ..#........#..
 * ....#.#..#....
 * ..............
 * ....#..#..#...
 * ..............
 * To make sure they're on the right track, the Elves like to check after round 10 that they're making good progress
 * toward covering enough ground. To do this, count the number of empty ground tiles contained by the smallest
 * rectangle that contains every Elf. (The edges of the rectangle should be aligned to the N/S/E/W directions; the
 * Elves do not have the patience to calculate arbitrary rectangles.) In the above example, that rectangle is:
 *
 * ......#.....
 * ..........#.
 * .#.#..#.....
 * .....#......
 * ..#.....#..#
 * #......##...
 * ....##......
 * .#........#.
 * ...#.#..#...
 * ............
 * ...#..#..#..
 * In this region, the number of empty ground tiles is 110.
 *
 * Simulate the Elves' process and find the smallest rectangle that contains the Elves after 10 rounds. How many empty
 * ground tiles does that rectangle contain?
 *
 * --- Part Two ---
 * It seems you're on the right track. Finish simulating the process and figure out where the Elves need to go. How
 * many rounds did you save them?
 *
 * In the example above, the first round where no Elf moved was round 20:
 *
 * .......#......
 * ....#......#..
 * ..#.....#.....
 * ......#.......
 * ...#....#.#..#
 * #.............
 * ....#.....#...
 * ..#.....#.....
 * ....#.#....#..
 * .........#....
 * ....#......#..
 * .......#......
 * Figure out where the Elves need to go. What is the number of the first round where no Elf moves?
 */
public class Day23 {

    /**
     * Work out if the elf can move north
     * @param elf Elf position
     * @param elfPositions Grid of elf positions
     * @return True if the Elf can move north
     */
    private boolean canMoveNorth(Point elf, Discrete2DPositionGrid<Character> elfPositions) {
        return
            elfPositions.getValueAtPosition( elf.x - 1, elf.y - 1) != '#'
            && elfPositions.getValueAtPosition( elf.x, elf.y - 1) != '#'
            && elfPositions.getValueAtPosition( elf.x + 1, elf.y - 1) != '#';
    }

    /**
     * Work out if the elf can move south
     * @param elf Elf position
     * @param elfPositions Grid of elf positions
     * @return True if the Elf can move south
     */
    private boolean canMoveSouth(Point elf, Discrete2DPositionGrid<Character> elfPositions) {
        return
            elfPositions.getValueAtPosition( elf.x - 1, elf.y + 1) != '#'
            && elfPositions.getValueAtPosition( elf.x, elf.y + 1) != '#'
            && elfPositions.getValueAtPosition( elf.x + 1, elf.y + 1) != '#';
    }

    /**
     * Work out if the elf can move west
     * @param elf Elf position
     * @param elfPositions Grid of elf positions
     * @return True if the Elf can move west
     */
    private boolean canMoveWest(Point elf, Discrete2DPositionGrid<Character> elfPositions) {
        return
            elfPositions.getValueAtPosition( elf.x - 1, elf.y + 1) != '#'
            && elfPositions.getValueAtPosition( elf.x - 1, elf.y) != '#'
            && elfPositions.getValueAtPosition( elf.x - 1, elf.y - 1) != '#';
    }

    /**
     * Work out if the elf can move east
     * @param elf Elf position
     * @param elfPositions Grid of elf positions
     * @return True if the Elf can move east
     */
    private boolean canMoveEast(Point elf, Discrete2DPositionGrid<Character> elfPositions) {
        return
            elfPositions.getValueAtPosition( elf.x + 1, elf.y + 1) != '#'
            && elfPositions.getValueAtPosition( elf.x + 1, elf.y) != '#'
            && elfPositions.getValueAtPosition( elf.x + 1, elf.y - 1) != '#';
    }

    /**
     * Work out which location the elf should move into
     * @param elf Elf position
     * @param elfPositions Grid of elf positions
     * @param orderOfSearch The order to search the positions in
     * @return The point they should end up in
     */
    private Point findLocationToMoveInto(Point elf, Discrete2DPositionGrid<Character> elfPositions, Queue<Character> orderOfSearch) {

        boolean canMoveNorth = this.canMoveNorth(elf, elfPositions);
        boolean canMoveSouth = this.canMoveSouth(elf, elfPositions);
        boolean canMoveWest = this.canMoveWest(elf, elfPositions);
        boolean canMoveEast = this.canMoveEast(elf, elfPositions);

        //If its empty in all directions, do nothing
        if(canMoveNorth && canMoveSouth && canMoveWest && canMoveEast) {
            return new Point(elf);
        }

        //Otherwise work out which position it should move into
        for(Character c : orderOfSearch) {
            if(c == 'N') {
                if(canMoveNorth) {
                    return new Point(elf.x, elf.y - 1);
                }
            }else if(c == 'S') {
                if(canMoveSouth) {
                    return new Point(elf.x, elf.y + 1);
                }
            }else if(c == 'W') {
                if(canMoveWest) {
                    return new Point(elf.x - 1, elf.y);
                }
            }else if(c == 'E') {
                if(canMoveEast) {
                    return new Point(elf.x + 1, elf.y);
                }
            }else{
                throw new RuntimeException("Unknown character");
            }
        }

        //If we can't move into any position, don't move
        return new Point(elf.x, elf.y);
    }

    /**
     * Get the initial ordering of the searching
     * @return Returns a Queue with the order of the searching
     */
    private Queue<Character> getInitialOrdering() {
        Queue<Character> orderOfSearch = new LinkedList<>();
        orderOfSearch.add('N');
        orderOfSearch.add('S');
        orderOfSearch.add('W');
        orderOfSearch.add('E');
        return orderOfSearch;
    }

    /**
     * Iterate moving the elves one turn and return whether any elves moved or not
     * @param currentElfPositions The current elf positions in a list
     * @param orderOfSearch Order of the search to run
     * @return Boolean representing whether the elves moved or not
     */
    private boolean iterateElvesMoving(List<Point> currentElfPositions, Queue<Character> orderOfSearch) {
        Discrete2DPositionGrid<Character> elfPositions = this.createElfPositionMap(currentElfPositions);
        Discrete2DPositionGrid<Integer> proposedPositions = new Discrete2DPositionGrid<>(0);

        //Work out where each elf proposes to move
        List<Point> proposedPositionList = new ArrayList<>();
        for(Point elf : currentElfPositions) {
            Point proposedElfPosition = this.findLocationToMoveInto(elf, elfPositions, orderOfSearch);
            proposedPositionList.add(proposedElfPosition);
            proposedPositions.setValueAtPosition(proposedElfPosition, proposedPositions.getValueAtPosition(proposedElfPosition) + 1);
        }

        //Then work out if no other elves proposed moving to the same location
        boolean anyElvesMoved = false;
        List<Point> newPositions = new ArrayList<>();
        for(int elfIndex = 0; elfIndex < currentElfPositions.size(); elfIndex++) {
            Point proposedElfPosition = proposedPositionList.get(elfIndex);
            Point currentElfPosition = currentElfPositions.get(elfIndex);

            //An elf moved only if no one else suggested moving to that location and its not the current location
            if(proposedPositions.getValueAtPosition(proposedElfPosition) == 1 && !proposedElfPosition.equals(currentElfPosition)) {
                newPositions.add(proposedElfPosition);
                anyElvesMoved = true;

            }else{ // Too many elves going here, so lets just stay where we are!
                newPositions.add(currentElfPositions.get(elfIndex));
            }
        }

        //Update the array (this is a bit slow since we remove all and then add all but this keeps the reference
        //the same so we can just use the same reference)
        currentElfPositions.removeAll(currentElfPositions);
        currentElfPositions.addAll(newPositions);

        //And then move the next search direction
        char charToMove = orderOfSearch.poll();
        orderOfSearch.add(charToMove);

        return anyElvesMoved;
    }

    /**
     * Create a simple map with the elf positions
     * @param currentElfPositions The current elf positions
     * @return Return a grid representing all the elf positions
     */
    private Discrete2DPositionGrid<Character> createElfPositionMap(List<Point> currentElfPositions) {
        Discrete2DPositionGrid<Character> elfPositions = new Discrete2DPositionGrid<>('.');
        for(Point elf : currentElfPositions) {
            elfPositions.setValueAtPosition(elf, '#');
        }
        return elfPositions;
    }

    /**
     * Solve part one by iterating the elf position moving code ten times and then return the number of ground blocks
     * @param grid Initial elf position grid
     * @return The number of empty ground tiles
     */
    public long solvePartOne(Discrete2DPositionGrid<Character> grid) {
        List<Point> currentElfPositions = grid.getPositionsOfValue('#');
        Queue<Character> orderOfSearch = this.getInitialOrdering();

        //Run the algorithm for 10 rounds
        for(int roundId = 0; roundId < 10; roundId++) {
            this.iterateElvesMoving(currentElfPositions, orderOfSearch);
        }

        //Then work out how many empty tiles there are
        Discrete2DPositionGrid<Character> elfPositions = this.createElfPositionMap(currentElfPositions);
        List<Point> allPositions = elfPositions.getPositionsOfValue('#');
        int maxX = -99999;
        int minX = 99999;
        int maxY = -99999;
        int minY = 99999;
        for(Point elf : allPositions) {
            maxX = Math.max(maxX, elf.x);
            minX = Math.min(minX, elf.x);
            maxY = Math.max(maxY, elf.y);
            minY = Math.min(minY, elf.y);
        }

        //Just find the width/length and then minus the elf count
        long totalFound = 0;
        for(int x = minX; x <= maxX; x++) {
            for(int y = minY; y <= maxY; y++) {
                if(elfPositions.getValueAtPosition(x, y) == '.') {
                    totalFound++;
                }
            }
        }

        return totalFound;
    }

    /**
     * Keep iterating the elves movement until they stop moving
     * @param grid Initial elf position grid
     * @return The number of rounds for all the elves to stop moving
     */
    public long solvePartTwo(Discrete2DPositionGrid<Character> grid) {
        List<Point> currentElfPositions = grid.getPositionsOfValue('#');
        Queue<Character> orderOfSearch = this.getInitialOrdering();

        //Just keep moving until they stop!
        int roundId = 0;
        boolean elvesMoved = true;
        while(elvesMoved) {
            elvesMoved = this.iterateElvesMoving(currentElfPositions, orderOfSearch);
            roundId++;
        }

        return roundId;
    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Character> grid = ProblemLoader.loadProblemIntoDiscrete2DPositionGridCharacter(2022, 23);

        Day23 d = new Day23();
        long partOne = d.solvePartOne(grid);
        System.out.println("The number of empty ground tiles around the elves after 10 rounds " + partOne);

        long partTwo = d.solvePartTwo(grid);
        System.out.println("The number of rounds that you need to iterate until all the elves stop moving is " + partTwo);

    }


}
