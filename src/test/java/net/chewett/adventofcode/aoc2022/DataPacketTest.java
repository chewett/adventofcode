package net.chewett.adventofcode.aoc2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataPacketTest {

    @Test
    public void testTwoIntsComparisonPasses() {
        DataPacket left = new DataPacket("5");
        DataPacket right = new DataPacket("6");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }

    @Test
    public void testTwoIntsComparisonFails() {
        DataPacket left = new DataPacket("6");
        DataPacket right = new DataPacket("1");

        Assertions.assertEquals(-1, left.isOrderedVsRight(right));
    }

    @Test
    public void testSimpleListComparisonPasses() {
        DataPacket left = new DataPacket("[1,2,3]");
        DataPacket right = new DataPacket("[1,2,5]");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }

    @Test
    public void testSimpleListComparisonFails() {
        DataPacket left = new DataPacket("[1,2,3]");
        DataPacket right = new DataPacket("[1,2,2]");

        Assertions.assertEquals(-1, left.isOrderedVsRight(right));
    }

    @Test
    public void testDifferentListSizesPasses() {
        DataPacket left = new DataPacket("[1,2]");
        DataPacket right = new DataPacket("[1,2,5]");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }

    @Test
    public void testDifferentListSizesFails() {
        DataPacket left = new DataPacket("[1,2,5]");
        DataPacket right = new DataPacket("[1,2]");

        Assertions.assertEquals(-1, left.isOrderedVsRight(right));
    }

    @Test
    public void testOneSideNotAList() {
        DataPacket left = new DataPacket("[1]");
        DataPacket right = new DataPacket("5");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }

    @Test
    public void testOtherSideNotAList() {
        DataPacket left = new DataPacket("1");
        DataPacket right = new DataPacket("[5]");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }

    @Test
    public void testExampleIndiceTwo() {
        DataPacket left = new DataPacket("[[1],[2,3,4]]");
        DataPacket right = new DataPacket("[[1],4]");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }

    @Test
    public void testSecondPartOfIndiceTwo() {
        DataPacket left = new DataPacket("[2,3,4]");
        DataPacket right = new DataPacket("4");

        Assertions.assertEquals(1, left.isOrderedVsRight(right));
    }



}
