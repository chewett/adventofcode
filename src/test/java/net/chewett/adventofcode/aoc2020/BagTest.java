package net.chewett.adventofcode.aoc2020;

import org.junit.Assert;
import org.junit.Test;

public class BagTest {

    @Test
    public void testNoContainingBags() {
        String testString = "dark violet bags contain no other bags.";
        Bag b = new Bag(testString);

        Assert.assertEquals("dark violet", b.getBagName());
        Assert.assertEquals(0, b.getContainingBags().size());
    }

    @Test
    public void testContainingOneBag() {
        String testString = "dark blue bags contain 2 dark violet bags.";
        Bag b = new Bag(testString);

        Assert.assertEquals("dark blue", b.getBagName());
        Assert.assertEquals(1, b.getContainingBags().size());
        Assert.assertNotNull(b.getContainingBags().get("dark violet"));
        Assert.assertEquals(2, (int)b.getContainingBags().get("dark violet"));
    }

    @Test
    public void testContainingTwoBags() {
        String testString = "dark orange bags contain 3 bright white bags, 4 muted yellow bags.";
        Bag b = new Bag(testString);

        Assert.assertEquals("dark orange", b.getBagName());
        Assert.assertEquals(2, b.getContainingBags().size());
        Assert.assertNotNull(b.getContainingBags().get("bright white"));
        Assert.assertEquals(3, (int)b.getContainingBags().get("bright white"));
        Assert.assertNotNull(b.getContainingBags().get("muted yellow"));
        Assert.assertEquals(4, (int)b.getContainingBags().get("muted yellow"));
    }

    @Test
    public void testContainingThreeBags() {
        String testString = "clear magenta bags contain 2 drab indigo bags, 4 pale crimson bags, 5 light turquoise bags.";
        Bag b = new Bag(testString);

        Assert.assertEquals("clear magenta", b.getBagName());
        Assert.assertEquals(3, b.getContainingBags().size());
        Assert.assertNotNull(b.getContainingBags().get("drab indigo"));
        Assert.assertEquals(2, (int)b.getContainingBags().get("drab indigo"));
        Assert.assertNotNull(b.getContainingBags().get("pale crimson"));
        Assert.assertEquals(4, (int)b.getContainingBags().get("pale crimson"));
        Assert.assertNotNull(b.getContainingBags().get("light turquoise"));
        Assert.assertEquals(5, (int)b.getContainingBags().get("light turquoise"));
    }

}
