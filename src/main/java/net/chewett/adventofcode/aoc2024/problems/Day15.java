package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * --- Day 15: Warehouse Woes ---
 * You appear back inside your own mini submarine! Each Historian drives their mini submarine in a different direction;
 * maybe the Chief has his own submarine down here somewhere as well?
 *
 * You look up to see a vast school of lanternfish swimming past you. On closer inspection, they seem quite anxious,
 * so you drive your mini submarine over to see if you can help.
 *
 * Because lanternfish populations grow rapidly, they need a lot of food, and that food needs to be stored somewhere.
 * That's why these lanternfish have built elaborate warehouse complexes operated by robots!
 *
 * These lanternfish seem so anxious because they have lost control of the robot that operates one of their most
 * important warehouses! It is currently running amok, pushing around boxes in the warehouse with no regard for
 * lanternfish logistics or lanternfish inventory management strategies.
 *
 * Right now, none of the lanternfish are brave enough to swim up to an unpredictable robot so they could shut it
 * off. However, if you could anticipate the robot's movements, maybe they could find a safe option.
 *
 * The lanternfish already have a map of the warehouse and a list of movements the robot will attempt to make (your
 * puzzle input). The problem is that the movements will sometimes fail as boxes are shifted around, making the actual
 * movements of the robot difficult to predict.
 *
 * For example:
 *
 * ##########
 * #..O..O.O#
 * #......O.#
 * #.OO..O.O#
 * #..O@..O.#
 * #O#..O...#
 * #O..O..O.#
 * #.OO.O.OO#
 * #....O...#
 * ##########
 *
 * <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
 * vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
 * ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
 * <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
 * ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
 * ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
 * >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
 * <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
 * ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
 * v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
 * As the robot (@) attempts to move, if there are any boxes (O) in the way, the robot will also attempt to push those
 * boxes. However, if this action would cause the robot or a box to move into a wall (#), nothing moves instead,
 * including the robot. The initial positions of these are shown on the map at the top of the document the lanternfish
 * gave you.
 *
 * The rest of the document describes the moves (^ for up, v for down, < for left, > for right) that the robot will
 * attempt to make, in order. (The moves form a single giant sequence; they are broken into multiple lines just to
 * make copy-pasting easier. Newlines within the move sequence should be ignored.)
 *
 * Here is a smaller example to get started:
 *
 * ########
 * #..O.O.#
 * ##@.O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * <^^>>>vv<v>>v<<
 * Were the robot to attempt the given sequence of moves, it would push around the boxes as follows:
 *
 * Initial state:
 * ########
 * #..O.O.#
 * ##@.O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move <:
 * ########
 * #..O.O.#
 * ##@.O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move ^:
 * ########
 * #.@O.O.#
 * ##..O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move ^:
 * ########
 * #.@O.O.#
 * ##..O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move >:
 * ########
 * #..@OO.#
 * ##..O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move >:
 * ########
 * #...@OO#
 * ##..O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move >:
 * ########
 * #...@OO#
 * ##..O..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #......#
 * ########
 *
 * Move v:
 * ########
 * #....OO#
 * ##..@..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move v:
 * ########
 * #....OO#
 * ##..@..#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move <:
 * ########
 * #....OO#
 * ##.@...#
 * #...O..#
 * #.#.O..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move v:
 * ########
 * #....OO#
 * ##.....#
 * #..@O..#
 * #.#.O..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move >:
 * ########
 * #....OO#
 * ##.....#
 * #...@O.#
 * #.#.O..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move >:
 * ########
 * #....OO#
 * ##.....#
 * #....@O#
 * #.#.O..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move v:
 * ########
 * #....OO#
 * ##.....#
 * #.....O#
 * #.#.O@.#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move <:
 * ########
 * #....OO#
 * ##.....#
 * #.....O#
 * #.#O@..#
 * #...O..#
 * #...O..#
 * ########
 *
 * Move <:
 * ########
 * #....OO#
 * ##.....#
 * #.....O#
 * #.#O@..#
 * #...O..#
 * #...O..#
 * ########
 * The larger example has many more moves; after the robot has finished those moves, the warehouse would look like this:
 *
 * ##########
 * #.O.O.OOO#
 * #........#
 * #OO......#
 * #OO@.....#
 * #O#.....O#
 * #O.....OO#
 * #O.....OO#
 * #OO....OO#
 * ##########
 * The lanternfish use their own custom Goods Positioning System (GPS for short) to track the locations of the boxes.
 * The GPS coordinate of a box is equal to 100 times its distance from the top edge of the map plus its distance
 * from the left edge of the map. (This process does not stop at wall tiles; measure all the way to the edges of
 * the map.)
 *
 * So, the box shown below has a distance of 1 from the top edge of the map and 4 from the left edge of the map,
 * resulting in a GPS coordinate of 100 * 1 + 4 = 104.
 *
 * #######
 * #...O..
 * #......
 * The lanternfish would like to know the sum of all boxes' GPS coordinates after the robot finishes moving. In the
 * larger example, the sum of all boxes' GPS coordinates is 10092. In the smaller example, the sum is 2028.
 *
 * Predict the motion of the robot and boxes in the warehouse. After the robot is finished moving, what is the sum
 * of all boxes' GPS coordinates?
 *
 * --- Part Two ---
 * The lanternfish use your information to find a safe moment to swim in and turn off the malfunctioning robot! Just
 * as they start preparing a festival in your honor, reports start coming in that a second warehouse's robot is also
 * malfunctioning.
 *
 * This warehouse's layout is surprisingly similar to the one you just helped. There is one key difference: everything
 * except the robot is twice as wide! The robot's list of movements doesn't change.
 *
 * To get the wider warehouse's map, start with your original map and, for each tile, make the following changes:
 *
 * If the tile is #, the new map contains ## instead.
 * If the tile is O, the new map contains [] instead.
 * If the tile is ., the new map contains .. instead.
 * If the tile is @, the new map contains @. instead.
 * This will produce a new warehouse map which is twice as wide and with wide boxes that are represented by [].
 * (The robot does not change size.)
 *
 * The larger example from before would now look like this:
 *
 * ####################
 * ##....[]....[]..[]##
 * ##............[]..##
 * ##..[][]....[]..[]##
 * ##....[]@.....[]..##
 * ##[]##....[]......##
 * ##[]....[]....[]..##
 * ##..[][]..[]..[][]##
 * ##........[]......##
 * ####################
 * Because boxes are now twice as wide but the robot is still the same size and speed, boxes can be aligned such that
 * they directly push two other boxes at once. For example, consider this situation:
 *
 * #######
 * #...#.#
 * #.....#
 * #..OO@#
 * #..O..#
 * #.....#
 * #######
 *
 * <vv<<^^<<^^
 * After appropriately resizing this map, the robot would push around these boxes as follows:
 *
 * Initial state:
 * ##############
 * ##......##..##
 * ##..........##
 * ##....[][]@.##
 * ##....[]....##
 * ##..........##
 * ##############
 *
 * Move <:
 * ##############
 * ##......##..##
 * ##..........##
 * ##...[][]@..##
 * ##....[]....##
 * ##..........##
 * ##############
 *
 * Move v:
 * ##############
 * ##......##..##
 * ##..........##
 * ##...[][]...##
 * ##....[].@..##
 * ##..........##
 * ##############
 *
 * Move v:
 * ##############
 * ##......##..##
 * ##..........##
 * ##...[][]...##
 * ##....[]....##
 * ##.......@..##
 * ##############
 *
 * Move <:
 * ##############
 * ##......##..##
 * ##..........##
 * ##...[][]...##
 * ##....[]....##
 * ##......@...##
 * ##############
 *
 * Move <:
 * ##############
 * ##......##..##
 * ##..........##
 * ##...[][]...##
 * ##....[]....##
 * ##.....@....##
 * ##############
 *
 * Move ^:
 * ##############
 * ##......##..##
 * ##...[][]...##
 * ##....[]....##
 * ##.....@....##
 * ##..........##
 * ##############
 *
 * Move ^:
 * ##############
 * ##......##..##
 * ##...[][]...##
 * ##....[]....##
 * ##.....@....##
 * ##..........##
 * ##############
 *
 * Move <:
 * ##############
 * ##......##..##
 * ##...[][]...##
 * ##....[]....##
 * ##....@.....##
 * ##..........##
 * ##############
 *
 * Move <:
 * ##############
 * ##......##..##
 * ##...[][]...##
 * ##....[]....##
 * ##...@......##
 * ##..........##
 * ##############
 *
 * Move ^:
 * ##############
 * ##......##..##
 * ##...[][]...##
 * ##...@[]....##
 * ##..........##
 * ##..........##
 * ##############
 *
 * Move ^:
 * ##############
 * ##...[].##..##
 * ##...@.[]...##
 * ##....[]....##
 * ##..........##
 * ##..........##
 * ##############
 * This warehouse also uses GPS to locate the boxes. For these larger boxes, distances are measured from the edge of
 * the map to the closest edge of the box in question. So, the box shown below has a distance of 1 from the top edge
 * of the map and 5 from the left edge of the map, resulting in a GPS coordinate of 100 * 1 + 5 = 105.
 *
 * ##########
 * ##...[]...
 * ##........
 * In the scaled-up version of the larger example from above, after the robot has finished all of its moves, the
 * warehouse would look like this:
 *
 * ####################
 * ##[].......[].[][]##
 * ##[]...........[].##
 * ##[]........[][][]##
 * ##[]......[]....[]##
 * ##..##......[]....##
 * ##..[]............##
 * ##..@......[].[][]##
 * ##......[][]..[]..##
 * ####################
 * The sum of these boxes' GPS coordinates is 9021.
 *
 * Predict the motion of the robot and boxes in this new, scaled-up warehouse. What is the sum of all boxes' final
 * GPS coordinates?
 */
public class Day15 {

    /**
     * Simulate the movement of the boxes and work out the sum of all the GPS locations
     * @param input Warehouse and movements
     * @return Sum of all GPS locations
     */
    public long solvePartOne(List<String> input) {
        Discrete2DPositionGrid<Character> grid = null;
        StringBuilder moves = new StringBuilder();

        List<String> gridStrings = new ArrayList<>();
        for (String line : input) {
            if(line.equals("")) {
                List<List<Character>> gridData = FormatConversion.convertStringArrayToCharListList(gridStrings);
                grid = FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(gridData);
            }

            if(grid == null) {
                gridStrings.add(line);
            }else{
                moves.append(line);
            }
        }

        for(int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            Point modifier = Directions.getDirectionModifierFromChar(move);
            Point curPos = grid.getPositionOfValueAssumingOnlyOne('@');

            char curVal = '@';
            Point newPos = new Point(curPos);
            while(curVal != '.') {
                newPos.x += modifier.x;
                newPos.y += modifier.y;
                curVal = grid.getValueAtPosition(newPos);

                //Hit something... do nothing
                if(curVal == '#') {
                    break;
                }
            }

            //This means it worked and we can move everything up by one
            if(curVal == '.') {
                grid.setValueAtPosition(curPos, '.');
                curPos.x += modifier.x;
                curPos.y += modifier.y;

                boolean movingBoxes = false;
                if(grid.getValueAtPosition(curPos) == 'O') {
                    movingBoxes = true;
                }

                grid.setValueAtPosition(curPos, '@');

                if(movingBoxes) {
                    curPos.x += modifier.x;
                    curPos.y += modifier.y;

                    char newChar = 'O';
                    while (newChar != '.') {
                        newChar = grid.getValueAtPosition(curPos);
                        grid.setValueAtPosition(curPos, 'O');

                        curPos.x += modifier.x;
                        curPos.y += modifier.y;
                    }
                }
            }
        }


        List<Point> allBoxes = grid.getPositionsOfValue('O');
        long totalGpsValues = 0;
        for(Point box : allBoxes) {
            totalGpsValues += box.x + box.y * 100;
        }

        return totalGpsValues;
    }

    /**
     * Simple function to contain the recursive step to see if a thing can move in the given direction
     * @param grid Grid that it moves on
     * @param direction Direction it moves
     * @param thing Location of the point that is moving
     * @return Boolean representing whether it can move or not
     */
    public boolean canMove(Discrete2DPositionGrid<Character> grid, Point direction, Point thing) {
        Point nextLoc = new Point(thing.x + direction.x, thing.y + direction.y);
        char nextChar = grid.getValueAtPosition(nextLoc);

        //Hitting # or . is simple, it either can or can't move
        if(nextChar == '#') {
            return false;
        }else if(nextChar == '.') {
            return true;
        }

        //Something is in the way, Let's see if that can move forward
        boolean canThisElementMove = this.canMove(grid, direction, nextLoc);
        if(!canThisElementMove) {
            return false;
        }

        //If we are moving up or down and reached here, we hit a box which is two wide, painful step!
        if(Objects.equals(direction, Directions.getDirectionModifier(Directions.UP)) ||
                Objects.equals(direction, Directions.getDirectionModifier(Directions.DOWN))) {

            //Handle checking if the "other" side of the box can move (also recursive)
            if (nextChar == ']') {
                return this.canMove(grid, direction, new Point(nextLoc.x - 1, nextLoc.y));
            } else if (nextChar == '[') {
                return this.canMove(grid, direction, new Point(nextLoc.x + 1, nextLoc.y));
            }else{
                throw new RuntimeException("Unknown element on the board");
            }
        }

        return true;
    }

    /**
     * This contains the recursive moving algorithm. To not get in a pickle it's important to move other pieces
     * first so we don't accidentally override something that needs moving.
     * @param grid Grid that it moves on
     * @param direction Direction it moves
     * @param thing Location of the point that is moving
     */
    public void moveForward(Discrete2DPositionGrid<Character> grid, Point direction, Point thing) {
        Point nextLoc = new Point(thing.x + direction.x, thing.y + direction.y);
        char nextChar = grid.getValueAtPosition(nextLoc);

        if(nextChar != '.') {
            // Move forward the thing in front of it
            this.moveForward(grid, direction, nextLoc);

            //If we are going up/down we also need to move the "other" part of the box
            if(Objects.equals(direction, Directions.getDirectionModifier(Directions.UP)) ||
                    Objects.equals(direction, Directions.getDirectionModifier(Directions.DOWN))) {

                //Handle moving the other part
                if (nextChar == ']') {
                    this.moveForward(grid, direction, new Point(nextLoc.x - 1, nextLoc.y));
                } else if (nextChar == '[') {
                    this.moveForward(grid, direction, new Point(nextLoc.x + 1, nextLoc.y));
                }else {
                    throw new RuntimeException("Unknown element on the board");
                }
            }
        }

        //Now we can move this piece and reset where it stood
        grid.setValueAtPosition(nextLoc, grid.getValueAtPosition(thing));
        grid.setValueAtPosition(thing, '.');

    }

    /**
     * Simulate the movement of the boxes and work out the sum of all the GPS locations
     * @param input Warehouse and movements
     * @return Sum of all GPS locations
     */
    public long solvePartTwo(List<String> input) {
        Discrete2DPositionGrid<Character> grid = null;
        StringBuilder moves = new StringBuilder();
        List<String> gridStrings = new ArrayList<>();

        for (String line : input) {
            if(line.equals("")) {

                //reformat the data into the new form:
                List<String> newGridStrings = new ArrayList<>();
                for(String gridLine : gridStrings) {
                    StringBuilder newString = new StringBuilder();
                    for(int i = 0; i < gridLine.length(); i++) {
                        char currentProcessingChar = gridLine.charAt(i);
                        if(currentProcessingChar == '#') {
                            newString.append("##");
                        }else if(currentProcessingChar == 'O') {
                            newString.append("[]");
                        }else if(currentProcessingChar == '.') {
                            newString.append("..");
                        }else if(currentProcessingChar == '@') {
                            newString.append("@.");
                        }else{
                            throw new RuntimeException("Input is not in the right format");
                        }
                    }
                    newGridStrings.add(newString.toString());
                }

                List<List<Character>> gridData = FormatConversion.convertStringArrayToCharListList(newGridStrings);
                grid = FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(gridData);
            }

            if(grid == null) {
                gridStrings.add(line);
            }else{
                moves.append(line);
            }
        }

        //Just do each move checking if it can move, and moving if it can
        for(int i = 0; i < moves.length(); i++) {
            char move = moves.charAt(i);
            Point modifier = Directions.getDirectionModifierFromChar(move);
            Point curPos = grid.getPositionOfValueAssumingOnlyOne('@');

            if(this.canMove(grid, modifier, curPos)) {
                this.moveForward(grid, modifier, curPos);
            }
        }

        //TODO: abstract out the code checking to make this slightly less copy+paste
        List<Point> allBoxes = grid.getPositionsOfValue('[');
        long totalGpsValues = 0;
        for(Point box : allBoxes) {
            totalGpsValues += box.x + box.y * 100;
        }

        return totalGpsValues;

    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 15);

        Day15 d = new Day15();
        long partOne = d.solvePartOne(input);
        System.out.println("The sum of all GPS locations is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The new sum of all GPS locations is " + partTwo);
    }
}


