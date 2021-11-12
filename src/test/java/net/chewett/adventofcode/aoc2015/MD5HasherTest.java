package net.chewett.adventofcode.aoc2015;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MD5HasherTest {

    @Test
    public void testHashWithHelloWorld() {
        Assertions.assertEquals("ed076287532e86365e841e92bfc50d8c", MD5Hasher.hash("Hello World!"));
    }


}
