package net.chewett.adventofcode.aoc2020;

/**
 * Holder class to store the details of each Crab cup
 */
public class CrabCup {

    private int value;
    private CrabCup next;

    public CrabCup(int val) {
        this.value = val;
    }

    /**
     * Setter to allow storing the next crab cup
     * @param c The next crab cup
     */
    public void setNext(CrabCup c) {
        this.next = c;
    }

    /**
     * Returns the next crab cup
     * @return The next crab cup
     */
    public CrabCup getNext() {
        return this.next;
    }

    /**
     * Gets the value of this crab cup
     * @return The value of this crab cup
     */
    public int getValue() {
        return value;
    }
}
