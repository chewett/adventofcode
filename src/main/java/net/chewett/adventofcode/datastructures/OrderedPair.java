package net.chewett.adventofcode.datastructures;

import java.util.Objects;

/**
 * Simple class to hold two strings in a specific order (to ensure that we can just throw two strings into the pair
 * and not worry about the ordering of them)
 */
public class OrderedPair {

    private String pairOne;
    private String pairTwo;

    public OrderedPair(String pairOne, String pairTwo) {
        if(pairOne.compareTo(pairTwo) > 0) {
            this.pairOne = pairOne;
            this.pairTwo = pairTwo;
        }else{
            this.pairOne = pairTwo;
            this.pairTwo = pairOne;
        }
    }

    public String getPairOne() {
        return pairOne;
    }

    public String getPairTwo() {
        return pairTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedPair that = (OrderedPair) o;
        return Objects.equals(pairOne, that.pairOne) && Objects.equals(pairTwo, that.pairTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pairOne, pairTwo);
    }
}
