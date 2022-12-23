package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * --- Day 17: Pyroclastic Flow ---
 * Your handheld device has located an alternative exit from the cave for you and the elephants. The ground is rumbling almost continuously now, but the strange valves bought you some time. It's definitely getting warmer in here, though.
 *
 * The tunnels eventually open into a very tall, narrow chamber. Large, oddly-shaped rocks are falling into the chamber from above, presumably due to all the rumbling. If you can't work out where the rocks will fall next, you might be crushed!
 *
 * The five types of rocks have the following peculiar shapes, where # is rock and . is empty space:
 *
 * ####
 *
 * .#.
 * ###
 * .#.
 *
 * ..#
 * ..#
 * ###
 *
 * #
 * #
 * #
 * #
 *
 * ##
 * ##
 * The rocks fall in the order shown above: first the - shape, then the + shape, and so on. Once the end of the list is reached, the same order repeats: the - shape falls first, sixth, 11th, 16th, etc.
 *
 * The rocks don't spin, but they do get pushed around by jets of hot gas coming out of the walls themselves. A quick scan reveals the effect the jets of hot gas will have on the rocks as they fall (your puzzle input).
 *
 * For example, suppose this was the jet pattern in your cave:
 *
 * >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
 * In jet patterns, < means a push to the left, while > means a push to the right. The pattern above means that the jets will push a falling rock right, then right, then right, then left, then left, then right, and so on. If the end of the list is reached, it repeats.
 *
 * The tall, vertical chamber is exactly seven units wide. Each rock appears so that its left edge is two units away from the left wall and its bottom edge is three units above the highest rock in the room (or the floor, if there isn't one).
 *
 * After a rock appears, it alternates between being pushed by a jet of hot gas one unit (in the direction indicated by the next symbol in the jet pattern) and then falling one unit down. If any movement would cause any part of the rock to move into the walls, floor, or a stopped rock, the movement instead does not occur. If a downward movement would have caused a falling rock to move into the floor or an already-fallen rock, the falling rock stops where it is (having landed on something) and a new rock immediately begins falling.
 *
 * Drawing falling rocks with @ and stopped rocks with #, the jet pattern in the example above manifests as follows:
 *
 * The first rock begins falling:
 * |..@@@@.|
 * |.......|
 * |.......|
 * |.......|
 * +-------+
 *
 * Jet of gas pushes rock right:
 * |...@@@@|
 * |.......|
 * |.......|
 * |.......|
 * +-------+
 *
 * Rock falls 1 unit:
 * |...@@@@|
 * |.......|
 * |.......|
 * +-------+
 *
 * Jet of gas pushes rock right, but nothing happens:
 * |...@@@@|
 * |.......|
 * |.......|
 * +-------+
 *
 * Rock falls 1 unit:
 * |...@@@@|
 * |.......|
 * +-------+
 *
 * Jet of gas pushes rock right, but nothing happens:
 * |...@@@@|
 * |.......|
 * +-------+
 *
 * Rock falls 1 unit:
 * |...@@@@|
 * +-------+
 *
 * Jet of gas pushes rock left:
 * |..@@@@.|
 * +-------+
 *
 * Rock falls 1 unit, causing it to come to rest:
 * |..####.|
 * +-------+
 *
 * A new rock begins falling:
 * |...@...|
 * |..@@@..|
 * |...@...|
 * |.......|
 * |.......|
 * |.......|
 * |..####.|
 * +-------+
 *
 * Jet of gas pushes rock left:
 * |..@....|
 * |.@@@...|
 * |..@....|
 * |.......|
 * |.......|
 * |.......|
 * |..####.|
 * +-------+
 *
 * Rock falls 1 unit:
 * |..@....|
 * |.@@@...|
 * |..@....|
 * |.......|
 * |.......|
 * |..####.|
 * +-------+
 *
 * Jet of gas pushes rock right:
 * |...@...|
 * |..@@@..|
 * |...@...|
 * |.......|
 * |.......|
 * |..####.|
 * +-------+
 *
 * Rock falls 1 unit:
 * |...@...|
 * |..@@@..|
 * |...@...|
 * |.......|
 * |..####.|
 * +-------+
 *
 * Jet of gas pushes rock left:
 * |..@....|
 * |.@@@...|
 * |..@....|
 * |.......|
 * |..####.|
 * +-------+
 *
 * Rock falls 1 unit:
 * |..@....|
 * |.@@@...|
 * |..@....|
 * |..####.|
 * +-------+
 *
 * Jet of gas pushes rock right:
 * |...@...|
 * |..@@@..|
 * |...@...|
 * |..####.|
 * +-------+
 *
 * Rock falls 1 unit, causing it to come to rest:
 * |...#...|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * A new rock begins falling:
 * |....@..|
 * |....@..|
 * |..@@@..|
 * |.......|
 * |.......|
 * |.......|
 * |...#...|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 * The moment each of the next few rocks begins falling, you would see this:
 *
 * |..@....|
 * |..@....|
 * |..@....|
 * |..@....|
 * |.......|
 * |.......|
 * |.......|
 * |..#....|
 * |..#....|
 * |####...|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |..@@...|
 * |..@@...|
 * |.......|
 * |.......|
 * |.......|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |..@@@@.|
 * |.......|
 * |.......|
 * |.......|
 * |....##.|
 * |....##.|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |...@...|
 * |..@@@..|
 * |...@...|
 * |.......|
 * |.......|
 * |.......|
 * |.####..|
 * |....##.|
 * |....##.|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |....@..|
 * |....@..|
 * |..@@@..|
 * |.......|
 * |.......|
 * |.......|
 * |..#....|
 * |.###...|
 * |..#....|
 * |.####..|
 * |....##.|
 * |....##.|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |..@....|
 * |..@....|
 * |..@....|
 * |..@....|
 * |.......|
 * |.......|
 * |.......|
 * |.....#.|
 * |.....#.|
 * |..####.|
 * |.###...|
 * |..#....|
 * |.####..|
 * |....##.|
 * |....##.|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |..@@...|
 * |..@@...|
 * |.......|
 * |.......|
 * |.......|
 * |....#..|
 * |....#..|
 * |....##.|
 * |....##.|
 * |..####.|
 * |.###...|
 * |..#....|
 * |.####..|
 * |....##.|
 * |....##.|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 *
 * |..@@@@.|
 * |.......|
 * |.......|
 * |.......|
 * |....#..|
 * |....#..|
 * |....##.|
 * |##..##.|
 * |######.|
 * |.###...|
 * |..#....|
 * |.####..|
 * |....##.|
 * |....##.|
 * |....#..|
 * |..#.#..|
 * |..#.#..|
 * |#####..|
 * |..###..|
 * |...#...|
 * |..####.|
 * +-------+
 * To prove to the elephants your simulation is accurate, they want to know how tall the tower will get after 2022 rocks have stopped (but before the 2023rd rock begins falling). In this example, the tower of rocks will be 3068 units tall.
 *
 * How many units tall will the tower of rocks be after 2022 rocks have stopped falling?
 *
 * --- Part Two ---
 * The elephants are not impressed by your simulation. They demand to know how tall the tower will be after 1000000000000 rocks have stopped! Only then will they feel confident enough to proceed through the cave.
 *
 * In the example above, the tower would be 1514285714288 units tall!
 *
 * How tall will the tower be after 1000000000000 rocks have stopped?
 */
public class Day17 {


    /**
     * Simple hardcoding of the shape ID to a length
     * @param shapeId Shape ID to get the length for
     * @return Length of the shape
     */
    public int getLengthOfShape(int shapeId) {
        if(shapeId == 0) {
            return 4;
        }else if(shapeId == 1 || shapeId == 2) {
            return 3;
        }else if(shapeId == 3) {
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * Given a grid, shape ID and Position, this determines if you can move left or not
     * @param grid Grid to check positions for
     * @param shapeId Shape ID
     * @param position Current position of shape
     * @return True if this shape can move left otherwise false
     */
    public boolean canMoveLeft(Discrete2DPositionGrid<Character> grid, int shapeId, Point position) {
        //X = 1 is the leftmost position
        if(position.x == 1) {
            return false;
        }

        //Long 4 block
        if(shapeId == 0) {
            return grid.getValueAtPosition(position.x-1, position.y) == '.';

            //cross
        }else if(shapeId == 1) {

            return grid.getValueAtPosition(position.x -1, position.y+1) == '.' &&
                    grid.getValueAtPosition(position.x, position.y+2) == '.' &&
                    grid.getValueAtPosition(position.x, position.y) == '.';

            //Square L
        }else if(shapeId == 2) {
            return grid.getValueAtPosition(position.x-1, position.y) == '.' &&
                    grid.getValueAtPosition(position.x+1, position.y+1) == '.' &&
                    grid.getValueAtPosition(position.x+1, position.y+2) == '.';

            // tall 4 block
        }else if(shapeId == 3) {
            return grid.getValueAtPosition(position.x-1, position.y) == '.' &&
                    grid.getValueAtPosition(position.x-1, position.y+1) == '.' &&
                    grid.getValueAtPosition(position.x-1, position.y+2) == '.' &&
                    grid.getValueAtPosition(position.x-1, position.y+3) == '.';

            //Square
        }else if(shapeId == 4) {
            return grid.getValueAtPosition(position.x-1, position.y) == '.' &&
                    grid.getValueAtPosition(position.x-1, position.y+1) == '.';
        }else{
            throw new RuntimeException("Unknown shape ID " + shapeId);
        }
    }

    /**
     * Given a grid, shape ID and Position, this determines if you can move right or not
     * @param grid Grid to check positions for
     * @param shapeId Shape ID
     * @param position Current position of shape
     * @return True if this shape can move right otherwise false
     */
    public boolean canMoveRight(Discrete2DPositionGrid<Character> grid, int shapeId, Point position) {
        int length = this.getLengthOfShape(shapeId);
        //Find the right-most point and check it's not 7
        if(position.x - 1 + length == 7) {
            return false;
        }

        //Long 4 block
        if(shapeId == 0) {
            return grid.getValueAtPosition(position.x+length, position.y) == '.';

            //cross
        }else if(shapeId == 1) {

            return grid.getValueAtPosition(position.x +length, position.y+1) == '.' &&
                    grid.getValueAtPosition(position.x+2, position.y+2) == '.' &&
                    grid.getValueAtPosition(position.x+2, position.y) == '.';

            //Square L
        }else if(shapeId == 2) {
            return grid.getValueAtPosition(position.x+length, position.y) == '.' &&
                    grid.getValueAtPosition(position.x+length, position.y+1) == '.' &&
                    grid.getValueAtPosition(position.x+length, position.y+2) == '.';

            // tall 4 block
        }else if(shapeId == 3) {
            return grid.getValueAtPosition(position.x+length, position.y) == '.' &&
                    grid.getValueAtPosition(position.x+length, position.y+1) == '.' &&
                    grid.getValueAtPosition(position.x+length, position.y+2) == '.' &&
                    grid.getValueAtPosition(position.x+length, position.y+3) == '.';

            //Square
        }else if(shapeId == 4) {
            return grid.getValueAtPosition(position.x+length, position.y) == '.' &&
                    grid.getValueAtPosition(position.x+length, position.y+1) == '.';

        }else{
            throw new RuntimeException("Unknown shape ID " + shapeId);
        }
    }

    /**
     * Checks to see if this shape can move down one by checking if there is space for all the blocks to move into safely
     * @param grid Grid to check
     * @param shapeId Shape ID
     * @param position Current position
     * @return True if this can move down one
     */
    public boolean isThereSpaceToMoveDownInto(Discrete2DPositionGrid<Character> grid, int shapeId, Point position) {
        int newY = position.y - 1;

        //Can't go to y=0
        if(newY == 0) {
            return false;
        }

        //Long 4 block
        if(shapeId == 0) {
            return grid.getValueAtPosition(position.x, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 1, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 2, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 3, newY) == '.';

        //cross
        }else if(shapeId == 1) {

            return grid.getValueAtPosition(position.x, newY+1) == '.' &&
                    grid.getValueAtPosition(position.x + 1, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 2, newY+1) == '.';

        //Square L
        }else if(shapeId == 2) {
            return grid.getValueAtPosition(position.x, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 1, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 2, newY) == '.';

        // tall 4 block
        }else if(shapeId == 3) {
            return grid.getValueAtPosition(position.x, newY) == '.';

        //Square
        }else if(shapeId == 4) {
            return grid.getValueAtPosition(position.x, newY) == '.' &&
                    grid.getValueAtPosition(position.x + 1, newY) == '.';

        }else{
            throw new RuntimeException("Unknown shape ID " + shapeId);
        }
    }

    /**
     * Given the grid, the shape ID, and the position, write the shape into the grid with a '#' character
     * @param grid Grid to write the shape into
     * @param shapeId Shape ID
     * @param position Position to write the shape into
     */
    public void paintShape(Discrete2DPositionGrid<Character> grid, int shapeId, Point position) {
        if(shapeId == 0) {
            grid.setValueAtPosition(position.x, position.y, '#');
            grid.setValueAtPosition(position.x+1, position.y, '#');
            grid.setValueAtPosition(position.x+2, position.y, '#');
            grid.setValueAtPosition(position.x+3, position.y, '#');

            //cross
        }else if(shapeId == 1) {
            grid.setValueAtPosition(position.x, position.y+1, '#');
            grid.setValueAtPosition(position.x+1, position.y, '#');
            grid.setValueAtPosition(position.x+1, position.y+1, '#');
            grid.setValueAtPosition(position.x+1, position.y+2, '#');
            grid.setValueAtPosition(position.x+2, position.y+1, '#');


            //Square L
        }else if(shapeId == 2) {
            grid.setValueAtPosition(position.x, position.y, 'L');
            grid.setValueAtPosition(position.x+1, position.y, 'L');
            grid.setValueAtPosition(position.x+2, position.y, 'L');
            grid.setValueAtPosition(position.x+2, position.y+1, 'L');
            grid.setValueAtPosition(position.x+2, position.y+2, 'L');

            // tall 4 block
        }else if(shapeId == 3) {
            grid.setValueAtPosition(position.x, position.y, '#');
            grid.setValueAtPosition(position.x, position.y+1, '#');
            grid.setValueAtPosition(position.x, position.y+2, '#');
            grid.setValueAtPosition(position.x, position.y+3, '#');

            //Square
        }else if(shapeId == 4) {
            grid.setValueAtPosition(position.x, position.y, '#');
            grid.setValueAtPosition(position.x, position.y+1, '#');
            grid.setValueAtPosition(position.x+1, position.y, '#');
            grid.setValueAtPosition(position.x+1, position.y+1, '#');

        }else{
            throw new RuntimeException("Unknown shape ID " + shapeId);
        }
    }

    /**
     * Simulate the rocky shapes falling with the given movement pattern and the number of rocks to simulate
     * @param movements The movement pattern
     * @param rockNumberToSimulateTo The number of rocks to simulate falling
     * @return The height of the tower
     */
    private long simulateToRockX(String movements, int rockNumberToSimulateTo) {
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');

        int rockNumber = 1;
        int shapeId = 0;
        int rockMovementId = 0;
        while(rockNumber <= rockNumberToSimulateTo) {
            //Point is always bottom left most section of the shape and 4 higher than the highest point
            Point rockPos = new Point(3, grid.getMaxY()+4);

            boolean keepMoving = true;
            while(keepMoving) {
                //Move the shape left or right
                char rockMovement = movements.charAt(rockMovementId);

                //Only move if we can actually move left/right
                if(rockMovement == '<') {
                    if(this.canMoveLeft(grid, shapeId, rockPos)) {
                        rockPos.x--;
                    }
                }else if(rockMovement == '>'){
                    if(this.canMoveRight(grid, shapeId, rockPos)) {
                        rockPos.x++;
                    }
                }else{
                    throw new RuntimeException("Bad movement " + rockMovement);
                }

                if(this.isThereSpaceToMoveDownInto(grid, shapeId, rockPos)) {
                    rockPos.y--;
                }else{
                    this.paintShape(grid, shapeId, rockPos);
                    keepMoving = false;
                }

                rockMovementId++;
                if(rockMovementId >= movements.length()) {
                    rockMovementId = 0;
                }
            }

            rockNumber++;
            shapeId++;
            if(shapeId > 4) {
                shapeId = 0;
            }
        }


        return grid.getMaxY();
    }

    /**
     * Simulate 2022 rocks and then return the height of the stack
     * @param movements Jet movement string
     * @return Height of the stacks
     */
    public long solvePartOne(String movements) {
        return this.simulateToRockX(movements, 2022);
    }

    /**
     * Simulates 1000000000000 rocks and then returns the hieght of the stack.
     * This uses a cheeky amount of MATHS to avoid simulating everything ;)
     * @param movements Jet movement string
     * @return Height of the stacks
     */
    public long solvePartTwo(String movements) {
        Map<String, Integer> seenStates = new HashMap<>();

        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>('.');

        int rockNumber = 1;
        int shapeId = 0;
        int rockMovementId = 0;
        boolean cycleFound = false;
        int cycleStart = -1;
        int cycleEnd = -1;

        while(!cycleFound) {
            //Point is always bottom left most section of the shape and 4 higher than the highest point
            Point rockPos = new Point(3, grid.getMaxY()+4);

            boolean keepMoving = true;
            while(keepMoving) {
                //Move the shape left or right
                char rockMovement = movements.charAt(rockMovementId);

                if(rockMovement == '<') {
                    if(this.canMoveLeft(grid, shapeId, rockPos)) {
                        rockPos.x--;
                    }
                }else if(rockMovement == '>'){
                    if(this.canMoveRight(grid, shapeId, rockPos)) {
                        rockPos.x++;
                    }
                }else{
                    throw new RuntimeException("Bad movement");
                }

                if(this.isThereSpaceToMoveDownInto(grid, shapeId, rockPos)) {
                    rockPos.y--;
                }else{
                    this.paintShape(grid, shapeId, rockPos);
                    keepMoving = false;
                }

                rockMovementId++;
                if(rockMovementId >= movements.length()) {
                    rockMovementId = 0;
                }
            }


            //This is my cycle detection indexing! A tricky one
            int curMaxY = grid.getMaxY();
            StringBuilder cycleHashSb = new StringBuilder(rockMovementId + "_" + shapeId);
            for(int roofVal = 1; roofVal <= 7; roofVal++) {
                int columnKey = grid.getMaxYOfColumn(roofVal) - curMaxY;
                cycleHashSb.append("_").append(columnKey);
            }

            String cycleHash = cycleHashSb.toString();
            if(seenStates.containsKey(cycleHash)) {
                cycleFound = true;
                cycleStart = seenStates.get(cycleHash);
                cycleEnd = rockNumber;
            }else {
                seenStates.put(cycleHash, rockNumber);

                rockNumber++;
                shapeId++;
                if (shapeId > 4) {
                    shapeId = 0;
                }
            }
        }

        //Work out the starting and ending heights of the cycle
        long heightAtStartOfCycle = this.simulateToRockX(movements, cycleStart);
        long heightAtEndOfCycle = this.simulateToRockX(movements, cycleEnd);
        long cycleHeightDiff = heightAtEndOfCycle - heightAtStartOfCycle;
        long cycleDiff = cycleEnd - cycleStart;

        //So we need to simulate: Cycles at start + (looped cycles * number of times we run the loop) + remaining cycles

        //Work out how many cycles we need to simulate in the loop
        long valuesLeftToSimulate = 1000000000000L - cycleStart;
        long multFactor = Math.floorDiv(valuesLeftToSimulate, cycleDiff);
        long finalRocks = valuesLeftToSimulate % cycleDiff;

        //So now we know the "remaining" cycles to simulate.
        int remainingCyclesToSimulate = (int) (cycleStart+finalRocks);
        //If we simulate to remainingCyclesToSimulate then we include the start too, so the final sum is that
        //plus the number of times we loop
        return (cycleHeightDiff * multFactor) + this.simulateToRockX(movements, remainingCyclesToSimulate);
    }

    public static void main(String[] args) {
        String movements = ProblemLoader.loadProblemIntoString(2022, 17);

        Day17 d = new Day17();
        long partOne = d.solvePartOne(movements);
        System.out.println("The tower will be " + partOne + " units tall after 2022 rocks");

        long partTwo = d.solvePartTwo(movements);
        System.out.println("The tower will be "+ partTwo + " units tall after 1000000000000 rocks");
    }


}
