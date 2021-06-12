package net.chewett.adventofcode.aoc2015;

import org.junit.Assert;
import org.junit.Test;


public class MD5HasherTest {

    @Test
    public void testHashWithHelloWorld() {
        Assert.assertEquals("ed076287532e86365e841e92bfc50d8c", MD5Hasher.hash("Hello World!"));
    }


}
