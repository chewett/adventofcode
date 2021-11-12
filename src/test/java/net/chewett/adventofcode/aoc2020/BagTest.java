package net.chewett.adventofcode.aoc2020;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagTest {

    @Test
    public void testNoContainingBags() {
        String testString = "dark violet bags contain no other bags.";
        Bag b = new Bag(testString);

        Assertions.assertEquals("dark violet", b.getBagName());
        Assertions.assertEquals(0, b.getContainingBags().size());
    }

    @Test
    public void testContainingOneBag() {
        String testString = "dark blue bags contain 2 dark violet bags.";
        Bag b = new Bag(testString);

        Assertions.assertEquals("dark blue", b.getBagName());
        Assertions.assertEquals(1, b.getContainingBags().size());
        Assertions.assertNotNull(b.getContainingBags().get("dark violet"));
        Assertions.assertEquals(2, (int)b.getContainingBags().get("dark violet"));
    }

    @Test
    public void testContainingTwoBags() {
        String testString = "dark orange bags contain 3 bright white bags, 4 muted yellow bags.";
        Bag b = new Bag(testString);

        Assertions.assertEquals("dark orange", b.getBagName());
        Assertions.assertEquals(2, b.getContainingBags().size());
        Assertions.assertNotNull(b.getContainingBags().get("bright white"));
        Assertions.assertEquals(3, (int)b.getContainingBags().get("bright white"));
        Assertions.assertNotNull(b.getContainingBags().get("muted yellow"));
        Assertions.assertEquals(4, (int)b.getContainingBags().get("muted yellow"));
    }

    @Test
    public void testContainingThreeBags() {
        String testString = "clear magenta bags contain 2 drab indigo bags, 4 pale crimson bags, 5 light turquoise bags.";
        Bag b = new Bag(testString);

        Assertions.assertEquals("clear magenta", b.getBagName());
        Assertions.assertEquals(3, b.getContainingBags().size());
        Assertions.assertNotNull(b.getContainingBags().get("drab indigo"));
        Assertions.assertEquals(2, (int)b.getContainingBags().get("drab indigo"));
        Assertions.assertNotNull(b.getContainingBags().get("pale crimson"));
        Assertions.assertEquals(4, (int)b.getContainingBags().get("pale crimson"));
        Assertions.assertNotNull(b.getContainingBags().get("light turquoise"));
        Assertions.assertEquals(5, (int)b.getContainingBags().get("light turquoise"));
    }

}
