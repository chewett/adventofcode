package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.Directions;
import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.datastructures.Point3D;
import net.chewett.adventofcode.helpers.FormatConversion;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 22: Monkey Map ---
 * The monkeys take you on a surprisingly easy trail through the jungle. They're even going in roughly the right
 * direction according to your handheld device's Grove Positioning System.
 *
 * As you walk, the monkeys explain that the grove is protected by a force field. To pass through the force field, you
 * have to enter a password; doing so involves tracing a specific path on a strangely-shaped board.
 *
 * At least, you're pretty sure that's what you have to do; the elephants aren't exactly fluent in monkey.
 *
 * The monkeys give you notes that they took when they last saw the password entered (your puzzle input).
 *
 * For example:
 *
 *         ...#
 *         .#..
 *         #...
 *         ....
 * ...#.......#
 * ........#...
 * ..#....#....
 * ..........#.
 *         ...#....
 *         .....#..
 *         .#......
 *         ......#.
 *
 * 10R5L5R10L4R5L5
 * The first half of the monkeys' notes is a map of the board. It is comprised of a set of open tiles (on which you can
 * move, drawn .) and solid walls (tiles which you cannot enter, drawn #).
 *
 * The second half is a description of the path you must follow. It consists of alternating numbers and letters:
 *
 * A number indicates the number of tiles to move in the direction you are facing. If you run into a wall, you stop
 * moving forward and continue with the next instruction.
 * A letter indicates whether to turn 90 degrees clockwise (R) or counterclockwise (L). Turning happens in-place; it
 * does not change your current tile.
 * So, a path like 10R5 means "go forward 10 tiles, then turn clockwise 90 degrees, then go forward 5 tiles".
 *
 * You begin the path in the leftmost open tile of the top row of tiles. Initially, you are facing to the right (from
 * the perspective of how the map is drawn).
 *
 * If a movement instruction would take you off of the map, you wrap around to the other side of the board. In other
 * words, if your next tile is off of the board, you should instead look in the direction opposite of your current
 * facing as far as you can until you find the opposite edge of the board, then reappear there.
 *
 * For example, if you are at A and facing to the right, the tile in front of you is marked B; if you are at C and
 * facing down, the tile in front of you is marked D:
 *
 *         ...#
 *         .#..
 *         #...
 *         ....
 * ...#.D.....#
 * ........#...
 * B.#....#...A
 * .....C....#.
 *         ...#....
 *         .....#..
 *         .#......
 *         ......#.
 * It is possible for the next tile (after wrapping around) to be a wall; this still counts as there being a wall in
 * front of you, and so movement stops before you actually wrap to the other side of the board.
 *
 * By drawing the last facing you had with an arrow on each tile you visit, the full path taken by the above example
 * looks like this:
 *
 *         >>v#
 *         .#v.
 *         #.v.
 *         ..v.
 * ...#...v..v#
 * >>>v...>#.>>
 * ..#v...#....
 * ...>>>>v..#.
 *         ...#....
 *         .....#..
 *         .#......
 *         ......#.
 * To finish providing the password to this strange input device, you need to determine numbers for your final row, column, and facing as your final position appears from the perspective of the original map. Rows start from 1 at the top and count downward; columns start from 1 at the left and count rightward. (In the above example, row 1, column 1 refers to the empty space with no tile on it in the top-left corner.) Facing is 0 for right (>), 1 for down (v), 2 for left (<), and 3 for up (^). The final password is the sum of 1000 times the row, 4 times the column, and the facing.
 *
 * In the above example, the final row is 6, the final column is 8, and the final facing is 0. So, the final password
 * is 1000 * 6 + 4 * 8 + 0: 6032.
 *
 * Follow the path given in the monkeys' notes. What is the final password?
 *
 * --- Part Two ---
 * As you reach the force field, you think you hear some Elves in the distance. Perhaps they've already arrived?
 *
 * You approach the strange input device, but it isn't quite what the monkeys drew in their notes. Instead, you are met
 * with a large cube; each of its six faces is a square of 50x50 tiles.
 *
 * To be fair, the monkeys' map does have six 50x50 regions on it. If you were to carefully fold the map, you should
 * be able to shape it into a cube!
 *
 * In the example above, the six (smaller, 4x4) faces of the cube are:
 *
 *         1111
 *         1111
 *         1111
 *         1111
 * 222233334444
 * 222233334444
 * 222233334444
 * 222233334444
 *         55556666
 *         55556666
 *         55556666
 *         55556666
 * You still start in the same position and with the same facing as before, but the wrapping rules are different. Now,
 * if you would walk off the board, you instead proceed around the cube. From the perspective of the map, this can look
 * a little strange. In the above example, if you are at A and move to the right, you would arrive at B facing down; if
 * you are at C and move down, you would arrive at D facing up:
 *
 *         ...#
 *         .#..
 *         #...
 *         ....
 * ...#.......#
 * ........#..A
 * ..#....#....
 * .D........#.
 *         ...#..B.
 *         .....#..
 *         .#......
 *         ..C...#.
 * Walls still block your path, even if they are on a different face of the cube. If you are at E facing up, your
 * movement is blocked by the wall marked by the arrow:
 *
 *         ...#
 *         .#..
 *      -->#...
 *         ....
 * ...#..E....#
 * ........#...
 * ..#....#....
 * ..........#.
 *         ...#....
 *         .....#..
 *         .#......
 *         ......#.
 * Using the same method of drawing the last facing you had with an arrow on each tile you visit, the full path taken
 * by the above example now looks like this:
 *
 *         >>v#
 *         .#v.
 *         #.v.
 *         ..v.
 * ...#..^...v#
 * .>>>>>^.#.>>
 * .^#....#....
 * .^........#.
 *         ...#..v.
 *         .....#v.
 *         .#v<<<<.
 *         ..v...#.
 * The final password is still calculated from your final position and facing from the perspective of the map. In this
 * example, the final row is 5, the final column is 7, and the final facing is 3, so the final password is
 * 1000 * 5 + 4 * 7 + 3 = 5031.
 *
 * Fold the map into a cube, then follow the path given in the monkeys' notes. What is the final password?
 */
public class Day22 {

    /**
     * Given the current position this will determine the new position for part two (if it needs changing)
     * @param newX New X position
     * @param newY New Y position
     * @return A 3D point object representing the new X, Y, and direction (as the Z value in the point)
     */
    public Point3D determineNewPositionForPartTwo(int newX, int newY) {
        int newDirection;

        //50 is the width, 0-49 50-99, 100-149
        if(newX == 49 && (newY >= 0 && newY <= 49)) {
            newY = 149 - newY;
            newX = 0;
            newDirection = Directions.EAST;

        }else if(newX == -1 && (newY >= 100 && newY <= 149)) {
            newY = 149 - newY;
            newX = 50;
            newDirection = Directions.EAST;

        }else if(newY == 99 && (newX >= 0 && newX <= 49)) {
            newY = newX + 50;
            newX = 50;
            newDirection = Directions.EAST;
        }else if(newX == 100 && (newY >= 50 && newY <= 99)) {

            newX = newY + 50;
            newY = 49;
            newDirection = Directions.NORTH;

        }else if(newY == 50 && (newX >= 100 && newX <= 149)) {

            newY = newX - 50;
            newX = 99;
            newDirection = Directions.WEST;

        }else if(newY == -1 && (newX >= 100 && newX <= 149)) {

            newX = newX - 100;
            newY = 199;
            newDirection = Directions.NORTH;

        }else if(newX == -1 && (newY >= 150 && newY <= 199)) {
            newX = newY -100;
            newY = 0;
            newDirection = Directions.SOUTH;

        }else if(newY == -1 && (newX >= 50 && newX <= 99)) {
            newY = newX + 100;
            newX = 0;
            newDirection = Directions.EAST;

        }else if(newX == 100 && (newY >= 100 && newY <= 149)) {

            newY = 149 - newY;
            newX = 149;
            newDirection = Directions.WEST;

        }else if(newX == 150 && (newY >= 0 && newY <= 49)) {

            newY = 100 + (49 - newY);
            newX = 99;
            newDirection = Directions.WEST;

        }else if(newY == 150 && (newX >= 50 && newX <= 99)) {
            newY = newX + 100;
            newX = 49;
            newDirection = Directions.WEST;

        }else if(newX == 49 && (newY >= 50 && newY <= 99)) {

            newX = newY - 50;
            newY = 100;
            newDirection = Directions.SOUTH;

        }else if(newX == 50 && (newY >= 150 && newY <= 199)) {

            newX = newY - 100;
            newY = 149;
            newDirection = Directions.NORTH;

        }else if(newY == 200 && (newX >= 0 && newX <= 49)) {
            newX = newX + 100;
            newY = 0;
            newDirection = Directions.SOUTH;

        }else {
            throw new RuntimeException("Unknown coordinates");

        }

        return new Point3D(newX, newY, newDirection);
    }

    private Point getMovementVector(int movementId) {
        int moveY = 0;
        int moveX = 0;
        if(movementId == Directions.EAST) {
            moveX = 1;
        }else if(movementId == Directions.SOUTH) {
            moveY = 1;
        }else if(movementId == Directions.WEST) {
            moveX = -1;
        }else if(movementId == Directions.NORTH) {
            moveY = -1;
        }

        return new Point(moveX, moveY);
    }

    /**
     * Move the point on the grid for part two
     * @param mapGrid Map grid to move on
     * @param position Current position value (This is modified to keep things a little simpler)
     * @param direction Direction of movement
     * @param moveVal Movement value to move
     * @return Returns the new direction
     */
    private int moveOnGridPartTwo(Discrete2DPositionGrid<Character> mapGrid, Point position, int direction, String moveVal) {
        int moveDistance = Integer.parseInt(moveVal);

        //Move one by one, each time performing the full movement checks
        for(int moveIndex = 0; moveIndex < moveDistance; moveIndex++) {
            Point movementVector = this.getMovementVector(direction);
            int newX = position.x + movementVector.x;
            int newY = position.y + movementVector.y;
            int newDirection = direction;

            char nextMovementLocation = mapGrid.getValueAtPosition(newX, newY);
            //If its a space then we need to work out where the next "real" space is located
            if(nextMovementLocation == ' ') {

                //Here we work out the next space and direction it would be moving in
                Point3D newPositionAndDirection = this.determineNewPositionForPartTwo(newX, newY);
                newX = newPositionAndDirection.getX();
                newY = newPositionAndDirection.getY();
                newDirection = newPositionAndDirection.getZ();

                //work out the next locations character (either a rock or a space)
                nextMovementLocation = mapGrid.getValueAtPosition(newX, newY);
            }

            //If its a dot (space) then we can move and continue the loop
            if(nextMovementLocation == '.') {
                //We can move!
                position.x = newX;
                position.y = newY;
                direction = newDirection;
            }else{

                //If the next location is a rock, we don't move
                return direction;
            }
        }

        return direction;
    }


    /**
     * Move the point on the grid for part one
     * @param mapGrid Map grid to move on
     * @param position Current position value (This is modified to keep things a little simpler)
     * @param direction Direction of movement
     * @param moveVal Movement value to move
     * @return Returns the final position
     */
    private Point moveOnGrid(Discrete2DPositionGrid<Character> mapGrid, Point position, int direction, String moveVal) {
        int moveDistance = Integer.parseInt(moveVal);
        Point movementVector = this.getMovementVector(direction);

        for(int moveIndex = 0; moveIndex < moveDistance; moveIndex++) {
            int newX = position.x + movementVector.x;
            int newY = position.y + movementVector.y;

            char nextMovementLocation = mapGrid.getValueAtPosition(newX, newY);
            //If its a space then we need to work out where the next "real" space is located
            if(nextMovementLocation == ' ') {
                if(direction == Directions.EAST) {
                    for(int x = mapGrid.getMinX(); x <= mapGrid.getMaxX(); x++) {
                        char charToCheck = mapGrid.getValueAtPosition(x, newY);
                        if(charToCheck != ' ') {
                            nextMovementLocation = charToCheck;
                            newX = x;
                            break;
                        }
                    }

                }else if(direction == Directions.SOUTH) {
                    for(int y = mapGrid.getMinY(); y <= mapGrid.getMaxY(); y++) {
                        char charToCheck = mapGrid.getValueAtPosition(newX, y);
                        if(charToCheck != ' ') {
                            nextMovementLocation = charToCheck;
                            newY = y;
                            break;
                        }
                    }

                }else if(direction == Directions.WEST) {
                    for(int x = mapGrid.getMaxX(); x >= mapGrid.getMinX(); x--) {
                        char charToCheck = mapGrid.getValueAtPosition(x, newY);
                        if(charToCheck != ' ') {
                            nextMovementLocation = charToCheck;
                            newX = x;
                            break;
                        }
                    }
                }else if(direction == Directions.NORTH) {
                    for(int y = mapGrid.getMaxY(); y >= mapGrid.getMinY(); y--) {
                        char charToCheck = mapGrid.getValueAtPosition(newX, y);
                        if(charToCheck != ' ') {
                            nextMovementLocation = charToCheck;
                            newY = y;
                            break;
                        }
                    }
                }
            }

            if(nextMovementLocation == '.') {
                //We can move!
                position.x = newX;
                position.y = newY;
            }else{
                //Got to stop moving, we hit a wall
                return position;
            }
        }

        return position;
    }

    /**
     * Work out the final password needed to reach the grove
     * @param input The grove board and directions
     * @return The grove coordinate and direction as an integer
     */
    public long solvePartOne(List<String> input) {
        List<String> map = new ArrayList<>();
        String moves = input.get(input.size() - 1);

        for(int rowId = 0; rowId < input.size() - 1; rowId++) {
            map.add(input.get(rowId));
        }

        Discrete2DPositionGrid<Character> mapGrid =
            FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
                FormatConversion.convertStringArrayToCharListList(map)
            );

        //Find the starting point
        Point position = new Point(0, 0);
        for(int x = mapGrid.getMinX(); x <= mapGrid.getMaxX(); x++) {
            char charToCheck = mapGrid.getValueAtPosition(x, mapGrid.getMinY());
            if(charToCheck == '.') {
                position = new Point(x, mapGrid.getMinY());
                break;
            }
        }

        int direction = Directions.EAST;

        String currentMove = "";
        for(int inputIndex = 0; inputIndex < moves.length(); inputIndex++) {
            char thisChar = moves.charAt(inputIndex);
            if(thisChar == 'R' || thisChar == 'L') {
                position = this.moveOnGrid(mapGrid, position, direction, currentMove);

                if(thisChar == 'R') {
                    direction++;
                    if(direction > 4) {
                        direction -= 4;
                    }
                }else if(thisChar == 'L') {
                    direction--;
                    if(direction < 1) {
                        direction += 4;
                    }
                }
                currentMove = "";
            }else{
                currentMove += ""+thisChar;
            }
        }

        position = this.moveOnGrid(mapGrid, position, direction, currentMove);

        int directionVal;
        if(direction == Directions.EAST) {
            directionVal = 0;
        }else if(direction == Directions.SOUTH) {
            directionVal = 1;
        }else if(direction == Directions.WEST) {
            directionVal = 2;
        }else if(direction == Directions.NORTH) {
            directionVal = 3;
        }else{
            throw new RuntimeException("Invalid direction found");
        }


        //Zero index so add one to position of X and Y
        return (1000 * (position.y + 1)) + (4 * (position.x + 1)) + directionVal;
    }

    /**
     * Work out the final password needed to reach the grove with the right answer
     * @param input The grove board and directions
     * @return The grove coordinate and direction as an integer
     */
    public long solvePartTwo(List<String> input) {
        List<String> map = new ArrayList<>();
        String moves = input.get(input.size() - 1);

        for(int rowId = 0; rowId < input.size() - 1; rowId++) {
            map.add(input.get(rowId));
        }

        Discrete2DPositionGrid<Character> mapGrid =
            FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(
                FormatConversion.convertStringArrayToCharListList(map)
            );

        Point position = new Point(0, 0);
        for(int x = mapGrid.getMinX(); x <= mapGrid.getMaxX(); x++) {
            char charToCheck = mapGrid.getValueAtPosition(x, mapGrid.getMinY());
            if(charToCheck == '.') {
                position = new Point(x, mapGrid.getMinY());
                break;
            }
        }

        int direction = Directions.EAST;

        String currentMove = "";
        for(int inputIndex = 0; inputIndex < moves.length(); inputIndex++) {
            char thisChar = moves.charAt(inputIndex);
            if(thisChar == 'R' || thisChar == 'L') {
                direction = this.moveOnGridPartTwo(mapGrid, position, direction, currentMove);

                if(thisChar == 'R') {
                    direction++;
                    if(direction > 4) {
                        direction -= 4;
                    }
                }else if(thisChar == 'L') {
                    direction--;
                    if(direction < 1) {
                        direction += 4;
                    }
                }
                currentMove = "";
            }else{
                currentMove += ""+thisChar;
            }
        }

        direction = this.moveOnGridPartTwo(mapGrid, position, direction, currentMove);

        int directionVal;
        if(direction == Directions.EAST) {
            directionVal = 0;
        }else if(direction == Directions.SOUTH) {
            directionVal = 1;
        }else if(direction == Directions.WEST) {
            directionVal = 2;
        }else if(direction == Directions.NORTH) {
            directionVal = 3;
        }else{
            throw new RuntimeException("Invalid direction found");
        }

        //Zero index so add one to position of X and Y
        return (1000 * (position.y + 1)) + (4 * (position.x + 1)) + directionVal;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 22);

        Day22 d = new Day22();
        long partOne = d.solvePartOne(input);
        System.out.println("The final password using the first method is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The final password using the correct method is "+ partTwo);

    }


}
