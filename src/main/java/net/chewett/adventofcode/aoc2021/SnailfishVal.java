package net.chewett.adventofcode.aoc2021;

/**
 * Simple holding function for Snailfish values
 *
 * This is mostly a datastructure without any complexity
 */
public class SnailfishVal {

    /**
     * Holds the value of this snailfish
     */
    private int val;
    /** Holds the left value if set */
    private SnailfishVal leftVal = null;
    /** Holds the right value if set */
    private SnailfishVal rightVal = null;

    public SnailfishVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public SnailfishVal getLeftVal() {
        return leftVal;
    }

    public void setLeftVal(SnailfishVal leftVal) {
        this.leftVal = leftVal;
    }

    public SnailfishVal getRightVal() {
        return rightVal;
    }

    public void setRightVal(SnailfishVal rightVal) {
        this.rightVal = rightVal;
    }
}
