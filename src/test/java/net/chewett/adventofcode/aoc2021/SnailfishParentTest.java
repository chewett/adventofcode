package net.chewett.adventofcode.aoc2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnailfishParentTest {

    /**
     * TEsts to make sure that the parent data works as expected
     */
    @Test
    public void testSnailfishParentTrail() {
        SnailfishParent p = new SnailfishParent("[1,2]");
        SnailfishVal v = p.getRightmostVal();
        Assertions.assertNotNull(v);
        Assertions.assertEquals(2,v.getVal());
    }

    /**
     * Makes sure that the parent works with a really long number
     */
    @Test
    public void testSnailfishParentLongTrail() {
        SnailfishParent p = new SnailfishParent("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]");
        SnailfishVal v = p.getRightmostVal();
        Assertions.assertNotNull(v);
        Assertions.assertEquals(5,v.getVal());
        int[] expectedVals = new int[] {4,1,2,8,2,8,6,5,0,6,7,4,2};
        for(int expectedVal : expectedVals) {
            v = v.getLeftVal();
            Assertions.assertNotNull(v);
            Assertions.assertEquals(expectedVal,v.getVal());
        }
        v = v.getLeftVal();
        Assertions.assertNull(v);
    }

    /**
     * Tests to make sure that the explosion works as expected
     */
    @Test
    public void testSingleExplosion() {
        SnailfishParent p = new SnailfishParent("[[[[[9,8],1],2],3],4]");
        boolean found = p.getRoot().checkForExplosions();
        Assertions.assertTrue(found);
        Assertions.assertEquals("[[[[0,9],2],3],4]",p.getRoot().toString());
    }

    /**
     * Tests a further single explosions
     */
    @Test
    public void testSingleExplosion2() {
        SnailfishParent p = new SnailfishParent("[7,[6,[5,[4,[3,2]]]]]");
        boolean found = p.getRoot().checkForExplosions();
        Assertions.assertTrue(found);
        Assertions.assertEquals("[7,[6,[5,[7,0]]]]",p.getRoot().toString());
    }

    /**
     * Tests a further single explosions
     */
    @Test
    public void testSingleExplosion3() {
        SnailfishParent p = new SnailfishParent("[[6,[5,[4,[3,2]]]],1]");
        boolean found = p.getRoot().checkForExplosions();
        Assertions.assertTrue(found);
        Assertions.assertEquals("[[6,[5,[7,0]]],3]",p.getRoot().toString());
    }

    /**
     * Tests a further single explosions
     */
    @Test
    public void testSingleExplosion4() {
        SnailfishParent p = new SnailfishParent("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
        boolean found = p.getRoot().checkForExplosions();
        Assertions.assertTrue(found);
        Assertions.assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]",p.getRoot().toString());
    }

    /**
     * Tests to make sure that the explosion works twice to produce the right output
     */
    @Test
    public void testDoubleExplosionTwoTurns() {
        SnailfishParent p = new SnailfishParent("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
        boolean found = p.getRoot().checkForExplosions();
        Assertions.assertTrue(found);
        Assertions.assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",p.getRoot().toString());
        found = p.getRoot().checkForExplosions();
        Assertions.assertTrue(found);
        Assertions.assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]",p.getRoot().toString());
    }

    /**
     * Tests to make sure that the reduce works as expected
     */
    @Test
    public void testReduce() {
        SnailfishNumber n1 = new SnailfishNumber("[[[[4,3],4],4],[7,[[8,4],9]]]");
        SnailfishNumber n2 = new SnailfishNumber("[1,1]");
        SnailfishNumber sum = n1.add(n2);

        Assertions.assertEquals("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]", sum.toString());

        SnailfishParent p = new SnailfishParent(sum.toString());
        p.reduce();

        Assertions.assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", p.getRoot().toString());
    }



}
