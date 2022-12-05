package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 5: Supply Stacks ---
 * The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored in
 * stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need to be
 * rearranged.
 *
 * The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get crushed
 * or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After the crates are
 * rearranged, the desired crates will be at the top of each stack.
 *
 * The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask her
 * which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.
 *
 * They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure (your puzzle
 * input). For example:
 *
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 *
 * move 1 from 2 to 1
 * move 3 from 1 to 3
 * move 2 from 2 to 1
 * move 1 from 1 to 2
 * In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and crate
 * N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally, stack 3
 * contains a single crate, P.
 *
 * Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one
 * stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from stack 2
 * to stack 1, resulting in this configuration:
 *
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first
 * crate to be moved (D) ends up below the second and third crates:
 *
 *         [Z]
 *         [N]
 *     [C] [D]
 *     [M] [P]
 *  1   2   3
 * Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up
 * below crate M:
 *
 *         [Z]
 *         [N]
 * [M]     [D]
 * [C]     [P]
 *  1   2   3
 * Finally, one crate is moved from stack 1 to stack 2:
 *
 *         [Z]
 *         [N]
 *         [D]
 * [C] [M] [P]
 *  1   2   3
 * The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C in
 * stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.
 *
 * After the rearrangement procedure completes, what crate ends up on top of each stack?
 *
 * --- Part Two ---
 * As you watch the crane operator expertly rearrange the crates, you notice the process isn't following your prediction.
 *
 * Some mud was covering the writing on the side of the crane, and you quickly wipe it away. The crane isn't a
 * CrateMover 9000 - it's a CrateMover 9001.
 *
 * The CrateMover 9001 is notable for many new and exciting features: air conditioning, leather seats, an extra cup
 * holder, and the ability to pick up and move multiple crates at once.
 *
 * Again considering the example above, the crates begin in the same configuration:
 *
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * Moving a single crate from stack 2 to stack 1 behaves the same as before:
 *
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * However, the action of moving three crates from stack 1 to stack 3 means that those three moved crates stay in the
 * same order, resulting in this new configuration:
 *
 *         [D]
 *         [N]
 *     [C] [Z]
 *     [M] [P]
 *  1   2   3
 * Next, as both crates are moved from stack 2 to stack 1, they retain their order as well:
 *
 *         [D]
 *         [N]
 * [C]     [Z]
 * [M]     [P]
 *  1   2   3
 * Finally, a single crate is still moved from stack 1 to stack 2, but now it's crate C that gets moved:
 *
 *         [D]
 *         [N]
 *         [Z]
 * [M] [C] [P]
 *  1   2   3
 * In this example, the CrateMover 9001 has put the crates in a totally different order: MCD.
 *
 * Before the rearrangement process finishes, update your simulation so that the Elves know where they should stand to
 * be ready to unload the final supplies. After the rearrangement procedure completes, what crate ends up on top of
 * each stack?
 */
public class Day5 {

    /**
     * Work out how the crates will be moved once the rearrangement procedure is performed
     * depending on the CrateMover version (either 9000, part 1, or 9001, part two).
     * @param crateMoverVersion Version of the CrateMover being used
     * @param billOfWork The current crate stack and the procedure to move them
     * @return A string representing the concatenated top of each stack
     */
    public String solveWithCrateMover(int crateMoverVersion, List<String> billOfWork) {
        if(crateMoverVersion != 9000 && crateMoverVersion != 9001) {
            throw new RuntimeException("We only support CrateMover 9000 and CrateMover 9001");
        }

        List<Stack<Character>> stacks = new ArrayList<>();
        int numberOfStacksFound = 0;

        //Find the number of stacks by finding the stack row and counting the highest one
        int stringListIndex = 0;
        for(; stringListIndex < billOfWork.size(); stringListIndex++) {
            String listOfCrates = billOfWork.get(stringListIndex);
            if(listOfCrates.charAt(1) == '1') {
                for(int charIndex = 0; charIndex < listOfCrates.length(); charIndex++) {
                    char characterFound = listOfCrates.charAt(charIndex);
                    if(characterFound != ' ') {
                        numberOfStacksFound = Math.max(numberOfStacksFound, Integer.parseInt(""+characterFound));
                    }
                }
                break;
            }
        }

        //Init the temporary data we need to store
        List<List<Character>> tmpCrates = new ArrayList<>();
        for(int stacksToInit = 0; stacksToInit < numberOfStacksFound; stacksToInit++) {
            tmpCrates.add(new ArrayList<>());
        }

        stringListIndex = 0;
        for(; stringListIndex < billOfWork.size(); stringListIndex++) {
            String listOfCrates = billOfWork.get(stringListIndex);

            //This is the list of locations so we can stop reading when we reach this
            if(listOfCrates.charAt(1) == '1') {
                break;
            }

            //Store the current stacks as a list so we can add it into a stack in the right order later
            int stackIdToPop = 0;
            for(int characterPosition = 1; characterPosition < listOfCrates.length(); characterPosition += 4) {
                char toReviewChar = listOfCrates.charAt(characterPosition);
                if(toReviewChar != ' ') {
                    tmpCrates.get(stackIdToPop).add(toReviewChar);
                }

                stackIdToPop += 1;
            }
        }

        //Now we have all the data reverse the lists and then add it into the stack objects
        for(int stacksToInit = 0; stacksToInit < numberOfStacksFound; stacksToInit++) {
            Collections.reverse(tmpCrates.get(stacksToInit));
            Stack<Character> newStack = new Stack<>();
            for(Character c : tmpCrates.get(stacksToInit)) {
                newStack.push(c);
            }

            stacks.add(newStack);
        }

        //Now parse the movement instructions
        stringListIndex += 2;
        List<int[]> instructions = new ArrayList<>();
        for(; stringListIndex < billOfWork.size(); stringListIndex++) {
            String[] parts = billOfWork.get(stringListIndex).split("(move | from | to )");
            instructions.add(new int[] { Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]) });
        }

        //Once we have the moevement instructions we can perform the movements
        for(int[] instruction : instructions) {
            int numberToMove = instruction[0];
            int indexToMove = instruction[1] - 1;
            int indexToMoveTo = instruction[2] - 1;

            //9000 - Move the crates one by one
            if(crateMoverVersion == 9000) {
                while (numberToMove > 0) {
                    stacks.get(indexToMoveTo).push(stacks.get(indexToMove).pop());

                    numberToMove--;
                }
            //9001 - Move the crates all at once
            }else if(crateMoverVersion == 9001) {
                //To move themn all at once and preserve the order, we use another stack to pop everything on
                Stack<Character> poppedThings = new Stack<>();
                while(numberToMove > 0) {
                    poppedThings.push(stacks.get(indexToMove).pop());
                    numberToMove--;
                }

                //And then pop everything back which will ensure the order is preserved
                while(!poppedThings.empty()) {
                    stacks.get(indexToMoveTo).push(poppedThings.pop());
                }
            }

        }

        //And then read the stop of each stack
        StringBuilder response = new StringBuilder();
        for(Stack<Character> st : stacks) {
            response.append(st.peek());
        }

        return response.toString();
    }

    /**
     * Works out the top crates once we have performed the bill of work provided.
     * This assumes that the crane is a CrateMover 9000
     * @param billOfWork Bill of work and initial state of the crates
     * @return All the crates at the top of the stack as a string
     */
    public String solvePartOne(List<String> billOfWork) {
       return this.solveWithCrateMover(9000, billOfWork);
    }

    /**
     * Works out the top crates once we have performed the bill of work provided.
     * This assumes that the crane is a CrateMover 9001
     * @param billOfWork Bill of work and initial state of the crates
     * @return All the crates at the top of the stack as a string
     */
    public String solvePartTwo(List<String> billOfWork) {
        return this.solveWithCrateMover(9001, billOfWork);
    }

    public static void main(String[] args) {
        List<String> assignments = ProblemLoader.loadProblemIntoStringArray(2022, 5);

        Day5 d = new Day5();
        String partOne = d.solvePartOne(assignments);
        System.out.println("The top crates are " + partOne);

        String partTwo = d.solvePartTwo(assignments);
        System.out.println("The top crates now I know that it is the CrateMover 9001 are " + partTwo);
    }


}
