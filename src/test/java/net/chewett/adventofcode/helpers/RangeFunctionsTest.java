package net.chewett.adventofcode.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeFunctionsTest {

    /**
     * Tests to make sure that the two ranges that are next to each other but not overlapping are marked as non-overlapping
     */
    @Test
    public void testNonOverlapping() {
        Assertions.assertFalse(RangeFunctions.isRangeOverlapping(10, 14, 15, 20));
    }

    /**
     * Tests to make sure that the two ranges that overlap on the left are reported as overlapping
     */
    @Test
    public void testLeftOverlap() {
        Assertions.assertTrue(RangeFunctions.isRangeOverlapping(10, 20, 15, 20));
    }

    /**
     * Tests to make sure a slightly different left overlap is reported as overlapping
     */
    @Test
    public void testLeftOverlap2() {
        Assertions.assertTrue(RangeFunctions.isRangeOverlapping(10, 16, 15, 20));
    }

    /**
     * Tests to make sure that one range containing another properly reports the ranges as overlapping
     */
    @Test
    public void testLeftContains() {
        Assertions.assertTrue(RangeFunctions.isRangeOverlapping(16, 16, 15, 20));
    }
}
