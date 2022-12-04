package net.chewett.adventofcode.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnailfishNumberTest {

    /**
     * Tests to make sure a simple snailfish number returns the expected data
     */
    @Test
    public void testSimpleNumberTwoInts() {
        SnailfishNumber sn = new SnailfishNumber("[1,2]");
        Assertions.assertEquals(1,sn.getLeftNumber().getVal());
        Assertions.assertEquals(2,sn.getRightNumber().getVal());
        Assertions.assertNull(sn.getLeftSnail());
        Assertions.assertNull(sn.getRightSnail());
        Assertions.assertEquals(0,sn.getLevel());
    }

    /**
     * Tests to make sure nesting works as I would expect
     */
    @Test
    public void testSimpleNesting() {
        SnailfishNumber sn = new SnailfishNumber("[[1,2],3]");
        Assertions.assertEquals(0,sn.getLevel());
        Assertions.assertNull(sn.getLeftNumber());
        Assertions.assertEquals(3,sn.getRightNumber().getVal());
        SnailfishNumber left = sn.getLeftSnail();
        Assertions.assertNull(sn.getRightSnail());
        Assertions.assertNotNull(left);

        Assertions.assertEquals(1,left.getLevel());
        Assertions.assertEquals(1,left.getLeftNumber().getVal());
        Assertions.assertEquals(2,left.getRightNumber().getVal());
    }

    /**
     * Tests to make sure that a simple addition works and creates the right data
     */
    @Test
    public void testSimpleAddition() {
        SnailfishNumber sn1 = new SnailfishNumber("[1,2]");
        SnailfishNumber sn2 = new SnailfishNumber("[[3,4],5]");

        Assertions.assertEquals("[[1,2],[[3,4],5]]",sn1.add(sn2).toString());
    }

    /**
     * Tests the magnitude works in a simple context
     */
    @Test
    public void testMagnitude1() {
        SnailfishNumber n = new SnailfishNumber("[[1,2],[[3,4],5]]");
        Assertions.assertEquals(143, n.getMagnitude());
    }

    /**
     * Tests the magnitude works in a more complex context
     */
    @Test
    public void testMagnitude2() {
        SnailfishNumber n = new SnailfishNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]");
        Assertions.assertEquals(1384, n.getMagnitude());
    }

    /**
     * Tests the magnitude works in a more complex context
     */
    @Test
    public void testMagnitude3() {
        SnailfishNumber n = new SnailfishNumber("[[[[1,1],[2,2]],[3,3]],[4,4]]");
        Assertions.assertEquals(445, n.getMagnitude());
    }


    /**
     * Tests the magnitude works in a more complex context
     */
    @Test
    public void testMagnitude4() {
        SnailfishNumber n = new SnailfishNumber("[[[[3,0],[5,3]],[4,4]],[5,5]]");
        Assertions.assertEquals(791, n.getMagnitude());
    }

    /**
     * Tests the magnitude works in a more complex context
     */
    @Test
    public void testMagnitude5() {
        SnailfishNumber n = new SnailfishNumber("[[[[5,0],[7,4]],[5,5]],[6,6]]");
        Assertions.assertEquals(1137, n.getMagnitude());
    }

    /**
     * Tests the magnitude works in a more complex context
     */
    @Test
    public void testMagnitude6() {
        SnailfishNumber n = new SnailfishNumber("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]");
        Assertions.assertEquals(3488, n.getMagnitude());
    }


}
