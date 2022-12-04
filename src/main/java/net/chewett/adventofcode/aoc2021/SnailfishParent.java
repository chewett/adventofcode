package net.chewett.adventofcode.aoc2021;

/**
 * The Snailfish Parent class is used as a "base case" to encapsulate all of the Snailfish numbers
 * and handle reducing and exploding.
 *
 * This is comproised of a single Snailfish number and the Rightmost value in the tree
 */
public class SnailfishParent {

    private SnailfishNumber root;
    private SnailfishVal rightmostVal;

    public SnailfishParent(String str) {
        //Create the Snailfish Number tree (which will possibly contain many snailfish numbers)
        this.root = new SnailfishNumber(str);

        //Traverse the list to link up the parts
        this.rightmostVal = this.root.traverseListFindingLeftValues(null);
    }

    /**
     * Gets the root Snailfish number held by this parent
     * @return Root snailfish number
     */
    public SnailfishNumber getRoot() {
        return root;
    }

    /**
     * Gets the rightmost value in the Snailfish number "tree"
     * @return Rightmost Snailfish value in the Snailfish Number
     */
    public SnailfishVal getRightmostVal() {
        return rightmostVal;
    }

    /**
     * Snailfish Numbers need reducing, I have decided to make this a conscious effort to more easily test this
     * Although you could probably just do it automatically when the number is created.
     * It iteratively tries to explode it, and then keeps exploding and checking for splits until it has finished+-
     */
    public void reduce() {
        boolean foundSomethingToDo = true;
        while(foundSomethingToDo) {
            boolean exploded = this.root.checkForExplosions();
            if(!exploded) {
                foundSomethingToDo = this.root.checkForSplits();
            }
        }
    }
}
