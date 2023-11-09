package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.aoc2021.Day23State;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 23: Amphipod ---
 * A group of amphipods notice your fancy submarine and flag you down. "With such an impressive shell," one amphipod
 * says, "surely you can help us with a question that has stumped our best scientists."
 *
 * They go on to explain that a group of timid, stubborn amphipods live in a nearby burrow. Four types of amphipods
 * live there: Amber (A), Bronze (B), Copper (C), and Desert (D). They live in a burrow that consists of a hallway and
 * four side rooms. The side rooms are initially full of amphipods, and the hallway is initially empty.
 *
 * They give you a diagram of the situation (your puzzle input), including locations of each amphipod (A, B, C, or D,
 * each of which is occupying an otherwise open space), walls (#), and open space (.).
 *
 * For example:
 *
 * #############
 * #...........#
 * ###B#C#B#D###
 *   #A#D#C#A#
 *   #########
 * The amphipods would like a method to organize every amphipod into side rooms so that each side room contains one
 * type of amphipod and the types are sorted A-D going left to right, like this:
 *
 * #############
 * #...........#
 * ###A#B#C#D###
 *   #A#B#C#D#
 *   #########
 * Amphipods can move up, down, left, or right so long as they are moving into an unoccupied open space. Each type of
 * amphipod requires a different amount of energy to move one step: Amber amphipods require 1 energy per step, Bronze
 * amphipods require 10 energy, Copper amphipods require 100, and Desert ones require 1000. The amphipods would like
 * you to find a way to organize the amphipods that requires the least total energy.
 *
 * However, because they are timid and stubborn, the amphipods have some extra rules:
 *
 * Amphipods will never stop on the space immediately outside any room. They can move into that space so long as they
 * immediately continue moving. (Specifically, this refers to the four open spaces in the hallway that are directly
 * above an amphipod starting position.)
 * Amphipods will never move from the hallway into a room unless that room is their destination room and that room
 * contains no amphipods which do not also have that room as their own destination. If an amphipod's starting room is
 * not its destination room, it can stay in that room until it leaves the room. (For example, an Amber amphipod will
 * not move from the hallway into the right three rooms, and will only move into the leftmost room if that room is
 * empty or if it only contains other Amber amphipods.)
 * Once an amphipod stops moving in the hallway, it will stay in that spot until it can move into a room. (That is,
 * once any amphipod starts moving, any other amphipods currently in the hallway are locked in place and will not move
 * again until they can move fully into a room.)
 * In the above example, the amphipods can be organized using a minimum of 12521 energy. One way to do this is shown
 * below.
 *
 * Starting configuration:
 *
 * #############
 * #...........#
 * ###B#C#B#D###
 *   #A#D#C#A#
 *   #########
 * One Bronze amphipod moves into the hallway, taking 4 steps and using 40 energy:
 *
 * #############
 * #...B.......#
 * ###B#C#.#D###
 *   #A#D#C#A#
 *   #########
 * The only Copper amphipod not in its side room moves there, taking 4 steps and using 400 energy:
 *
 * #############
 * #...B.......#
 * ###B#.#C#D###
 *   #A#D#C#A#
 *   #########
 * A Desert amphipod moves out of the way, taking 3 steps and using 3000 energy, and then the Bronze amphipod takes its
 * place, taking 3 steps and using 30 energy:
 *
 * #############
 * #.....D.....#
 * ###B#.#C#D###
 *   #A#B#C#A#
 *   #########
 * The leftmost Bronze amphipod moves to its room using 40 energy:
 *
 * #############
 * #.....D.....#
 * ###.#B#C#D###
 *   #A#B#C#A#
 *   #########
 * Both amphipods in the rightmost room move into the hallway, using 2003 energy in total:
 *
 * #############
 * #.....D.D.A.#
 * ###.#B#C#.###
 *   #A#B#C#.#
 *   #########
 * Both Desert amphipods move into the rightmost room using 7000 energy:
 *
 * #############
 * #.........A.#
 * ###.#B#C#D###
 *   #A#B#C#D#
 *   #########
 * Finally, the last Amber amphipod moves into its room, using 8 energy:
 *
 * #############
 * #...........#
 * ###A#B#C#D###
 *   #A#B#C#D#
 *   #########
 * What is the least energy required to organize the amphipods?
 *
 * --- Part Two ---
 * As you prepare to give the amphipods your solution, you notice that the diagram they handed you was actually
 * folded up. As you unfold it, you discover an extra part of the diagram.
 *
 * Between the first and second lines of text that contain amphipod starting positions, insert the following lines:
 *
 *   #D#C#B#A#
 *   #D#B#A#C#
 * So, the above example now becomes:
 *
 * #############
 * #...........#
 * ###B#C#B#D###
 *   #D#C#B#A#
 *   #D#B#A#C#
 *   #A#D#C#A#
 *   #########
 * The amphipods still want to be organized into rooms similar to before:
 *
 * #############
 * #...........#
 * ###A#B#C#D###
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 * In this updated example, the least energy required to organize these amphipods is 44169:
 *
 * #############
 * #...........#
 * ###B#C#B#D###
 *   #D#C#B#A#
 *   #D#B#A#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #..........D#
 * ###B#C#B#.###
 *   #D#C#B#A#
 *   #D#B#A#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #A.........D#
 * ###B#C#B#.###
 *   #D#C#B#.#
 *   #D#B#A#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #A........BD#
 * ###B#C#.#.###
 *   #D#C#B#.#
 *   #D#B#A#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #A......B.BD#
 * ###B#C#.#.###
 *   #D#C#.#.#
 *   #D#B#A#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #AA.....B.BD#
 * ###B#C#.#.###
 *   #D#C#.#.#
 *   #D#B#.#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #AA.....B.BD#
 * ###B#.#.#.###
 *   #D#C#.#.#
 *   #D#B#C#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #AA.....B.BD#
 * ###B#.#.#.###
 *   #D#.#C#.#
 *   #D#B#C#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #AA...B.B.BD#
 * ###B#.#.#.###
 *   #D#.#C#.#
 *   #D#.#C#C#
 *   #A#D#C#A#
 *   #########
 *
 * #############
 * #AA.D.B.B.BD#
 * ###B#.#.#.###
 *   #D#.#C#.#
 *   #D#.#C#C#
 *   #A#.#C#A#
 *   #########
 *
 * #############
 * #AA.D...B.BD#
 * ###B#.#.#.###
 *   #D#.#C#.#
 *   #D#.#C#C#
 *   #A#B#C#A#
 *   #########
 *
 * #############
 * #AA.D.....BD#
 * ###B#.#.#.###
 *   #D#.#C#.#
 *   #D#B#C#C#
 *   #A#B#C#A#
 *   #########
 *
 * #############
 * #AA.D......D#
 * ###B#.#.#.###
 *   #D#B#C#.#
 *   #D#B#C#C#
 *   #A#B#C#A#
 *   #########
 *
 * #############
 * #AA.D......D#
 * ###B#.#C#.###
 *   #D#B#C#.#
 *   #D#B#C#.#
 *   #A#B#C#A#
 *   #########
 *
 * #############
 * #AA.D.....AD#
 * ###B#.#C#.###
 *   #D#B#C#.#
 *   #D#B#C#.#
 *   #A#B#C#.#
 *   #########
 *
 * #############
 * #AA.......AD#
 * ###B#.#C#.###
 *   #D#B#C#.#
 *   #D#B#C#.#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #AA.......AD#
 * ###.#B#C#.###
 *   #D#B#C#.#
 *   #D#B#C#.#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #AA.......AD#
 * ###.#B#C#.###
 *   #.#B#C#.#
 *   #D#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #AA.D.....AD#
 * ###.#B#C#.###
 *   #.#B#C#.#
 *   #.#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #A..D.....AD#
 * ###.#B#C#.###
 *   #.#B#C#.#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #...D.....AD#
 * ###.#B#C#.###
 *   #A#B#C#.#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #.........AD#
 * ###.#B#C#.###
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #..........D#
 * ###A#B#C#.###
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 *
 * #############
 * #...........#
 * ###A#B#C#D###
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #A#B#C#D#
 *   #########
 * Using the initial configuration from the full diagram, what is the least energy required to organize the amphipods?
 */
public class Day23 {

    /**
     * Function to call and set up the solver to solve it
     * @param exampleInput Input for the problem
     * @param extended Whether to solve in extended mode or not (part 1 or part 2)
     * @return Energy required to run the function
     */
    private int solveNormally(String exampleInput, boolean extended) {
        Day23State state = new Day23State(exampleInput, 0, extended);

        Set<String> foundStates = new HashSet<>();
        Map<String, Integer> stateEnergyMin = new HashMap<>();

        //Store a queue of states that we need to explode and keep going...
        PriorityQueue<Day23State> queue = new PriorityQueue<>();
        queue.add(state);

        int bestState = Integer.MAX_VALUE;
        Day23State foundEnd = null;
        while(!queue.isEmpty()) {
            Day23State checkState = queue.poll();
            String thisStateAsString = checkState.getState();

            //If we have found this before then check to see if we have visited this at a lower energy level
            if(foundStates.contains(thisStateAsString)) {
                if(checkState.getEnergy() >= stateEnergyMin.get(thisStateAsString)) {
                    continue;
                }
                stateEnergyMin.put(thisStateAsString, checkState.getEnergy());
            }

            foundStates.add(thisStateAsString);
            stateEnergyMin.put(thisStateAsString, checkState.getEnergy());
            if(checkState.isFinalState()) {
                if(checkState.getEnergy() < bestState) {
                    foundEnd = checkState;
                    bestState = checkState.getEnergy();
                }

            }else{
                List<Day23State> newStates = checkState.getNewStates();
                queue.addAll(newStates);
            }
        }

        return foundEnd.getEnergy();
    }


    public long solvePartOne(String exampleInput) {
        return this.solveNormally(exampleInput, false);
    }

    public long solvePartTwo(String exampleInput) {
        return this.solveNormally(exampleInput, true);
    }

    public static void main(String[] args) {
        String exampleInput = ProblemLoader.loadProblemIntoString(2021, 23);

        Day23 d = new Day23();
        System.out.println("Minimum energy required is " + d.solvePartOne(exampleInput));
        System.out.println("Once unfolded the true minimum energy is " + d.solvePartTwo(exampleInput));

    }

}
