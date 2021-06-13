package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ObjectCloning;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.List;


/**
 * --- Day 11: Seating System ---
 * Your plane lands with plenty of time to spare. The final leg of your journey is a ferry that goes directly to the
 * tropical island where you can finally start your vacation. As you reach the waiting area to board the ferry, you
 * realize you're so early, nobody else has even arrived yet!
 *
 * By modeling the process people use to choose (or abandon) their seat in the waiting area, you're pretty sure you can
 * predict the best place to sit. You make a quick map of the seat layout (your puzzle input).
 *
 * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L), or an occupied seat (#).
 * For example, the initial seat layout might look like this:
 *
 * L.LL.LL.LL
 * LLLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLLL
 * L.LLLLLL.L
 * L.LLLLL.LL
 *
 * Now, you just need to model the people who will be arriving shortly. Fortunately, people are entirely predictable and
 * always follow a simple set of rules. All decisions are based on the number of occupied seats adjacent to a given seat
 * (one of the eight positions immediately up, down, left, right, or diagonal from the seat). The following rules are
 * applied to every seat simultaneously:
 *
 * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
 * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
 * Otherwise, the seat's state does not change.
 * Floor (.) never changes; seats don't move, and nobody sits on the floor.
 *
 * After one round of these rules, every seat in the example layout becomes occupied:
 *
 * #.##.##.##
 * #######.##
 * #.#.#..#..
 * ####.##.##
 * #.##.##.##
 * #.#####.##
 * ..#.#.....
 * ##########
 * #.######.#
 * #.#####.##
 *
 * After a second round, the seats with four or more occupied adjacent seats become empty again:
 *
 * #.LL.L#.##
 * #LLLLLL.L#
 * L.L.L..L..
 * #LLL.LL.L#
 * #.LL.LL.LL
 * #.LLLL#.##
 * ..L.L.....
 * #LLLLLLLL#
 * #.LLLLLL.L
 * #.#LLLL.##
 *
 * This process continues for three more rounds:
 *
 * #.##.L#.##
 * #L###LL.L#
 * L.#.#..#..
 * #L##.##.L#
 * #.##.LL.LL
 * #.###L#.##
 * ..#.#.....
 * #L######L#
 * #.LL###L.L
 * #.#L###.##
 *
 * #.#L.L#.##
 * #LLL#LL.L#
 * L.L.L..#..
 * #LLL.##.L#
 * #.LL.LL.LL
 * #.LL#L#.##
 * ..L.L.....
 * #L#LLLL#L#
 * #.LLLLLL.L
 * #.#L#L#.##
 *
 * #.#L.L#.##
 * #LLL#LL.L#
 * L.#.L..#..
 * #L##.##.L#
 * #.#L.LL.LL
 * #.#L#L#.##
 * ..L.L.....
 * #L#L##L#L#
 * #.LLLLLL.L
 * #.#L#L#.##
 *
 * At this point, something interesting happens: the chaos stabilizes and further applications of these rules cause no
 * seats to change state! Once people stop moving around, you count 37 occupied seats.
 *
 * Simulate your seating area by applying the seating rules repeatedly until no seats change state. How many seats end
 * up occupied?
 *
 * --- Part Two ---
 * As soon as people start to arrive, you realize your mistake. People don't just care about adjacent seats - they care
 * about the first seat they can see in each of those eight directions!
 *
 * Now, instead of considering just the eight immediately adjacent seats, consider the first seat in each of those eight
 * directions. For example, the empty seat below would see eight occupied seats:
 *
 * .......#.
 * ...#.....
 * .#.......
 * .........
 * ..#L....#
 * ....#....
 * .........
 * #........
 * ...#.....
 *
 * The leftmost empty seat below would only see one empty seat, but cannot see any of the occupied ones:
 *
 * .............
 * .L.L.#.#.#.#.
 * .............
 *
 * The empty seat below would see no occupied seats:
 *
 * .##.##.
 * #.#.#.#
 * ##...##
 * ...L...
 * ##...##
 * #.#.#.#
 * .##.##.
 *
 * Also, people seem to be more tolerant than you expected: it now takes five or more visible occupied seats for an
 * occupied seat to become empty (rather than four or more from the previous rules). The other rules still apply: empty
 * seats that see no occupied seats become occupied, seats matching no rule don't change, and floor never changes.
 *
 * Given the same starting layout as above, these new rules cause the seating area to shift around as follows:
 *
 * L.LL.LL.LL
 * LLLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLLL
 * L.LLLLLL.L
 * L.LLLLL.LL
 *
 * #.##.##.##
 * #######.##
 * #.#.#..#..
 * ####.##.##
 * #.##.##.##
 * #.#####.##
 * ..#.#.....
 * ##########
 * #.######.#
 * #.#####.##
 *
 * #.LL.LL.L#
 * #LLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLL#
 * #.LLLLLL.L
 * #.LLLLL.L#
 *
 * #.L#.##.L#
 * #L#####.LL
 * L.#.#..#..
 * ##L#.##.##
 * #.##.#L.##
 * #.#####.#L
 * ..#.#.....
 * LLL####LL#
 * #.L#####.L
 * #.L####.L#
 *
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##LL.LL.L#
 * L.LL.LL.L#
 * #.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 *
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##L#.#L.L#
 * L.L#.#L.L#
 * #.L####.LL
 * ..#.#.....
 * LLL###LLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 *
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##L#.#L.L#
 * L.L#.LL.L#
 * #.LLLL#.LL
 * ..#.L.....
 * LLL###LLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 *
 * Again, at this point, people stop shifting around and the seating area reaches equilibrium. Once this occurs, you
 * count 26 occupied seats.
 *
 * Given the new visibility method and the rule change for occupied seats becoming empty, once equilibrium is reached,
 * how many seats end up occupied?
 */
public class Day11 {

    /**
     * Iterate over the seating positions until we have found a stable pattern that doesn't change time to time
     * @param seats The initial state of the seats as a list of list of chars
     * @return The number of occupied seats in the final position
     */
    public int solvePartOne(List<List<Character>> seats) {

        //Keep track of every time the seating changes and just keep looping until that is no longer true
        boolean seatingChanges = true;
        List<List<Character>> curSeats = ObjectCloning.createNewClonedList(seats);
        List<List<Character>> previousSeats = null;
        while(seatingChanges) {
            //Right, now store the fact that nothing has changed yet, this is our "end condition"
            seatingChanges = false;
            previousSeats = curSeats;
            //Ensure we are operating on a new list and not modifying the previous one
            curSeats = ObjectCloning.createNewClonedList(previousSeats);

            //Loop over every seat and then check the surrounding seats.
            int maxY = previousSeats.size()-1;
            for(int y = 0; y < previousSeats.size(); y++) {
                int maxX = previousSeats.get(y).size()-1;
                for(int x = 0; x < previousSeats.get(y).size(); x++) {
                    char curSeat = previousSeats.get(y).get(x);
                    //Do nothing if there is no seat here, just go to the next part of the loop
                    if(curSeat == '.') {
                        continue;
                    }

                    //Perform the dance to count how many people are around the seat
                    int peopleAround = 0;
                    if(y != 0 && x != 0 && previousSeats.get(y-1).get(x-1) == '#') { peopleAround++; }
                    if(x != 0 && previousSeats.get(y).get(x-1) == '#') { peopleAround++; }
                    if(y != maxY && x != 0 && previousSeats.get(y+1).get(x-1) == '#') { peopleAround++; }

                    if(y != 0  && x != maxX && previousSeats.get(y-1).get(x+1) == '#') { peopleAround++; }
                    if(x != maxX && previousSeats.get(y).get(x+1) == '#') { peopleAround++; }
                    if(y != maxY && x != maxX && previousSeats.get(y+1).get(x+1) == '#') { peopleAround++; }

                    if(y != 0 && previousSeats.get(y-1).get(x) == '#') { peopleAround++; }
                    if(y != maxY && previousSeats.get(y+1).get(x) == '#') { peopleAround++; }

                    //Now we know how many people are around, we can determine whether this changes or not
                    char newSeat = curSeat;
                    if(curSeat == 'L' && peopleAround == 0) {
                        newSeat = '#';
                    }else if(curSeat == '#' && peopleAround >= 4) {
                        newSeat = 'L';
                    }
                    curSeats.get(y).set(x, newSeat);
                    //Since the new and old states are different, store that the seating has changed
                    if(newSeat != curSeat) {
                        seatingChanges = true;
                    }
                }
            }
        }

        //Now everything has stablised lets count the number of seats occupied
        int occupiedSeats = 0;
        for(List<Character> row : curSeats) {
            for(char c : row) {
                if(c == '#') {
                    occupiedSeats++;
                }
            }
        }

        return occupiedSeats;
    }

    /**
     * Iterate over the seating algorithm slowly to determine the final seating position when the seats stablise
     * @param seats The initial pattern of seats
     * @return The number of people seated once the algorithm has reached stability
     */
    public int solvePartTwo(List<List<Character>> seats) {

        boolean seatingChanges = true;
        List<List<Character>> curSeats = ObjectCloning.createNewClonedList(seats);
        List<List<Character>> previousSeats = null;
        while(seatingChanges) {
            seatingChanges = false;
            previousSeats = curSeats;
            curSeats = ObjectCloning.createNewClonedList(previousSeats);

            int sizeY = previousSeats.size();
            for(int y = 0; y < previousSeats.size(); y++) {
                int sizeX = previousSeats.get(y).size();
                for(int x = 0; x < previousSeats.get(y).size(); x++) {
                    char curSeat = previousSeats.get(y).get(x);
                    //If this isn't a seat, move onto the next one
                    if(curSeat == '.') {
                        continue;
                    }

                    //Right, now we need to slowly check every person around us iteratively, this will take a while!

                    int peopleAround = 0;
                    //Seats right from this seat
                    for(int curX = x+1; curX < sizeX; curX++) {
                        if(previousSeats.get(y).get(curX) != '.') {
                            if(previousSeats.get(y).get(curX) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                    }

                    //Seats left from this seat
                    for(int curX = x-1; curX >= 0; curX--) {
                        if(previousSeats.get(y).get(curX) != '.') {
                            if(previousSeats.get(y).get(curX) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                    }

                    //Seats up from this seat
                    for(int curY = y-1; curY >= 0; curY--) {
                        if(previousSeats.get(curY).get(x) != '.') {
                            if(previousSeats.get(curY).get(x) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                    }

                    //Seats down from this seat
                    for(int curY = y+1; curY < sizeY; curY++) {
                        if(previousSeats.get(curY).get(x) != '.') {
                            if(previousSeats.get(curY).get(x) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                    }

                    //Seats right and up from this seat
                    int curX = x+1; int curY = y-1;
                    while(curX < sizeX && curY >= 0) {
                        if(previousSeats.get(curY).get(curX) != '.') {
                            if(previousSeats.get(curY).get(curX) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                        curX++; curY--;
                    }

                    //Seats right and down from this seat
                    curX = x+1; curY = y+1;
                    while(curX < sizeX && curY < sizeY) {
                        if(previousSeats.get(curY).get(curX) != '.') {
                            if(previousSeats.get(curY).get(curX) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                        curX++; curY++;
                    }

                    //Seats left and down from this seat
                    curX = x-1; curY = y+1;
                    while(curX >= 0 && curY < sizeY) {
                        if(previousSeats.get(curY).get(curX) != '.') {
                            if(previousSeats.get(curY).get(curX) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                        curX--; curY++;
                    }

                    //Seats left and down from this seat
                    curX = x-1; curY = y-1;
                    while(curX >= 0 && curY >= 0) {
                        if(previousSeats.get(curY).get(curX) != '.') {
                            if(previousSeats.get(curY).get(curX) == '#') {
                                peopleAround++;
                            }
                            break;
                        }
                        curX--; curY--;
                    }

                    //Determine if this seat needs to change or not
                    char newSeat = curSeat;
                    if(curSeat == 'L' && peopleAround == 0) {
                        newSeat = '#';
                    }else if(curSeat == '#' && peopleAround >= 5) {
                        newSeat = 'L';
                    }
                    //If it does change, store that it has changed so we go around the loop again
                    curSeats.get(y).set(x, newSeat);
                    if(newSeat != curSeat) {
                        seatingChanges = true;
                    }
                }
            }
        }

        //Now we have finished, total up the number of seated people
        int occupiedSeats = 0;
        for(List<Character> row : curSeats) {
            for(char c : row) {
                if(c == '#') {
                    occupiedSeats++;
                }
            }
        }

        return occupiedSeats;
    }

    public static void main(String[] args) {
        Day11 d = new Day11();
        List<List<Character>> seats = ProblemLoader.loadProblemIntoXYCharList(2020, 11);
        int p1 = d.solvePartOne(seats);
        System.out.println("Final number of occupied seats: " + p1);
        long p2 = d.solvePartTwo(seats);
        System.out.println("Final number of occupied seats after second set of modelling: " + p2);
    }
}
