package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;
import net.chewett.adventofcode.helpers.ProblemLoader;

/**
 * --- Day 8: Treetop Tree House ---
 * The expedition comes across a peculiar patch of tall trees all planted carefully in a grid. The Elves explain that
 * a previous expedition planted these trees as a reforestation effort. Now, they're curious if this would be a good
 * location for a tree house.
 *
 * First, determine whether there is enough tree cover here to keep a tree house hidden. To do this, you need to count
 * the number of trees that are visible from outside the grid when looking directly along a row or column.
 *
 * The Elves have already launched a quadcopter to generate a map with the height of each tree (your puzzle input). For
 * example:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 * Each tree is represented as a single digit whose value is its height, where 0 is the shortest and 9 is the tallest.
 *
 * A tree is visible if all of the other trees between it and an edge of the grid are shorter than it. Only consider
 * trees in the same row or column; that is, only look up, down, left, or right from any given tree.
 *
 * All of the trees around the edge of the grid are visible - since they are already on the edge, there are no trees to
 * block the view. In this example, that only leaves the interior nine trees to consider:
 *
 * The top-left 5 is visible from the left and top. (It isn't visible from the right or bottom since other trees of
 * height 5 are in the way.)
 * The top-middle 5 is visible from the top and right.
 * The top-right 1 is not visible from any direction; for it to be visible, there would need to only be trees of height
 * 0 between it and an edge.
 * The left-middle 5 is visible, but only from the right.
 * The center 3 is not visible from any direction; for it to be visible, there would need to be only trees of at most
 * height 2 between it and an edge.
 * The right-middle 3 is visible from the right.
 * In the bottom row, the middle 5 is visible, but the 3 and 4 are not.
 * With 16 trees visible on the edge and another 5 visible in the interior, a total of 21 trees are visible in this
 * arrangement.
 *
 * Consider your map; how many trees are visible from outside the grid?
 *
 * --- Part Two ---
 * Content with the amount of tree cover available, the Elves just need to know the best spot to build their tree
 * house: they would like to be able to see a lot of trees.
 *
 * To measure the viewing distance from a given tree, look up, down, left, and right from that tree; stop if you reach
 * an edge or at the first tree that is the same height or taller than the tree under consideration. (If a tree is
 * right on the edge, at least one of its viewing distances will be zero.)
 *
 * The Elves don't care about distant trees taller than those found by the rules above; the proposed tree house has
 * large eaves to keep it dry, so they wouldn't be able to see higher than the tree house anyway.
 *
 * In the example above, consider the middle 5 in the second row:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 *
 * Looking up, its view is not blocked; it can see 1 tree (of height 3).
 * Looking left, its view is blocked immediately; it can see only 1 tree (of height 5, right next to it).
 * Looking right, its view is not blocked; it can see 2 trees.
 * Looking down, its view is blocked eventually; it can see 2 trees (one of height 3, then the tree of height 5 that
 * blocks its view).
 * A tree's scenic score is found by multiplying together its viewing distance in each of the four directions. For this
 * tree, this is 4 (found by multiplying 1 * 1 * 2 * 2).
 *
 * However, you can do even better: consider the tree of height 5 in the middle of the fourth row:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 *
 * Looking up, its view is blocked at 2 trees (by another tree with a height of 5).
 * Looking left, its view is not blocked; it can see 2 trees.
 * Looking down, its view is also not blocked; it can see 1 tree.
 * Looking right, its view is blocked at 2 trees (by a massive tree of height 9).
 * This tree's scenic score is 8 (2 * 2 * 1 * 2); this is the ideal spot for the tree house.
 *
 * Consider each tree on your map. What is the highest scenic score possible for any tree?
 */
public class Day8 {

    /**
     * Process the data and work out how many individual trees are visible from outside the grid
     * @param trees Grid of tree heights
     * @return Number of trees visible from outside the grid
     */
    public long solvePartOne(Discrete2DPositionGrid<Integer> trees) {
        int totalFound = 0;

        //Loop over every tree
        for(int y = trees.getMinY(); y <= trees.getMaxY(); y++) {
            for (int x = trees.getMinX(); x <= trees.getMaxX(); x++) {
                int myHeight = trees.getValueAtPosition(x, y);
                boolean treeCanBeSeenFromOutside = false;
                boolean foundHigher = false;
                //Check to see if trees left of this are shorter
                for(int checkX = x - 1; checkX >= 0; checkX--) {
                    if(trees.getValueAtPosition(checkX, y) >= myHeight) {
                        foundHigher = true;
                    }
                }
                if(!foundHigher) {
                    treeCanBeSeenFromOutside = true;
                }

                foundHigher = false;
                //Check to see if the trees right of this are shorter
                for(int checkX = x +1; checkX <= trees.getMaxX(); checkX++) {
                    if(trees.getValueAtPosition(checkX, y) >= myHeight) {
                        foundHigher = true;
                    }
                }
                if(!foundHigher) {
                    treeCanBeSeenFromOutside = true;
                }


                foundHigher = false;
                //Check to see if trees above this (closer to Y=0) are shorter
                for(int checkY = y - 1; checkY >= 0; checkY--) {
                    if(trees.getValueAtPosition(x, checkY) >= myHeight) {
                        foundHigher = true;
                    }
                }
                if(!foundHigher) {
                    treeCanBeSeenFromOutside = true;
                }

                foundHigher = false;
                //Check to see if trees below this (closer to Y=Infinity) are shorter
                for(int checkY = y +1; checkY <= trees.getMaxY(); checkY++) {
                    if(trees.getValueAtPosition(x, checkY) >= myHeight) {
                        foundHigher = true;
                    }
                }
                if(!foundHigher) {
                    treeCanBeSeenFromOutside = true;
                }

                if(treeCanBeSeenFromOutside) {
                    totalFound++;
                }

            }
        }

        return totalFound;
    }

    /**
     * Process the data and work out the highest scenic score in the forest
     * @param trees Grid of tree heights
     * @return Highest scenic score in the forest
     */
    public long solvePartTwo(Discrete2DPositionGrid<Integer> trees) {
        int bestScore = 0;

        //Loop over every tree position
        for(int y = trees.getMinY(); y <= trees.getMaxY(); y++) {
            for (int x = trees.getMinX(); x <= trees.getMaxX(); x++) {
                int myHeight = trees.getValueAtPosition(x, y);

                int thisScenicValue = 1;
                int foundTrees = 0;
                //Count the scenic value for all the trees on the left
                for(int checkX = x - 1; checkX >= 0; checkX--) {
                    if(trees.getValueAtPosition(checkX, y) >= myHeight) {
                        foundTrees++;
                        break;
                    }
                    foundTrees++;
                }
                thisScenicValue *= foundTrees;

                foundTrees = 0;
                //Count the scenic value for all the trees on the right
                for(int checkX = x +1; checkX <= trees.getMaxX(); checkX++) {
                    if(trees.getValueAtPosition(checkX, y) >= myHeight) {
                        foundTrees++;
                        break;
                    }
                    foundTrees++;
                }
                thisScenicValue *= foundTrees;

                foundTrees = 0;
                //Count the scenic value for all the trees above this (closer to Y=0)
                for(int checkY = y - 1; checkY >= 0; checkY--) {
                    if(trees.getValueAtPosition(x, checkY) >= myHeight) {
                        foundTrees++;
                        break;
                    }
                    foundTrees++;
                }
                thisScenicValue *= foundTrees;

                foundTrees = 0;
                //Count the scenic value for all the trees below this (closer to Y=Infinity)
                for(int checkY = y +1; checkY <= trees.getMaxY(); checkY++) {
                    if(trees.getValueAtPosition(x, checkY) >= myHeight) {
                        foundTrees++;
                        break;
                    }
                    foundTrees++;
                }
                thisScenicValue *= foundTrees;

                //If this new scenic value is better then we store it
                if(thisScenicValue > bestScore) {
                    bestScore = thisScenicValue;
                }

            }
        }

        return bestScore;

    }

    public static void main(String[] args) {
        Discrete2DPositionGrid<Integer> trees = ProblemLoader.loadProblemIntoDiscrete2DPositionIntegerGrid(2022, 8);

        Day8 d = new Day8();
        long partOne = d.solvePartOne(trees);
        System.out.println("The number of trees visible from outside is " + partOne);

        long partTwo = d.solvePartTwo(trees);
        System.out.println("The tree with the highest scenic value is " + partTwo);
    }

}
