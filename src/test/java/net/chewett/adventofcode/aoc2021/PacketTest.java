package net.chewett.adventofcode.aoc2021;

import net.chewett.adventofcode.aoc2015.ByteHexConversion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PacketTest {

    /**
     * Test to make sure parsing a simple literal packet works as expected
     */
    @Test
    public void testSimpleLiteralPacket() {
        Packet p = new Packet("01010000011");
        Assertions.assertEquals(2, p.getVersion());
        Assertions.assertEquals(4, p.getType());
        Assertions.assertEquals(3, p.getLiteralValOfPacket());
        Assertions.assertEquals("", p.getRestOfString());
        Assertions.assertEquals(2, p.getSumOfAllVersions());
    }


    /**
     * Parse a simple literal and handle lots of extraneous data
     */
    @Test
    public void testLotsOfDataLeftInLiteralPacket() {
        Packet p = new Packet("01010000011000000000");
        Assertions.assertEquals(2, p.getVersion());
        Assertions.assertEquals(4, p.getType());
        Assertions.assertEquals(3, p.getLiteralValOfPacket());
        Assertions.assertEquals("000000000", p.getRestOfString());
        Assertions.assertEquals(2, p.getSumOfAllVersions());
    }

    /**
     * Tests a larger literal packet
     */
    @Test
    public void testLargerLiteralPacket() {
        Packet p = new Packet("0101001000101111");
        Assertions.assertEquals(2, p.getVersion());
        Assertions.assertEquals(4, p.getType());
        Assertions.assertEquals(31, p.getLiteralValOfPacket());
        Assertions.assertEquals("", p.getRestOfString());
        Assertions.assertEquals(2, p.getSumOfAllVersions());
    }

    /**
     * Test OP six with two children
     */
    @Test
    public void testOpSixLengthId0TwoChildren() {
        Packet p = new Packet("00111000000000000110111101000101001010010001001000000000");
        Assertions.assertEquals(1, p.getVersion());
        Assertions.assertEquals(6, p.getType());
        //TODO: Add some more checks such as making sure the children are correct
        Assertions.assertEquals("0000000", p.getRestOfString());
        Assertions.assertEquals(9, p.getSumOfAllVersions());
    }

    /**
     * Test OP three with three children
     */
    @Test
    public void testOpThreeLengthId1ThreeChildren() {
        Packet p = new Packet("11101110000000001101010000001100100000100011000001100000");
        Assertions.assertEquals(7, p.getVersion());
        Assertions.assertEquals(3, p.getType());
        //TODO: Add some more checks such as making sure the children are correct
        Assertions.assertEquals("00000", p.getRestOfString());
        Assertions.assertEquals(14, p.getSumOfAllVersions());
    }

    /**
     * Test the first example and verify the sum
     */
    @Test
    public void testExampleOne() {
        Packet p = new Packet(ByteHexConversion.convertHexStringToBinaryString("8A004A801A8002F478"));
        Assertions.assertEquals(16, p.getSumOfAllVersions());
    }

    /**
     * Test the second example and verify the sum
     */
    @Test
    public void testExampleTwo() {
        Packet p = new Packet(ByteHexConversion.convertHexStringToBinaryString("620080001611562C8802118E34"));
        Assertions.assertEquals(12, p.getSumOfAllVersions());
    }

    /**
     * Test the third example and verify the sum
     */
    @Test
    public void testExampleThree() {
        Packet p = new Packet(ByteHexConversion.convertHexStringToBinaryString("C0015000016115A2E0802F182340"));
        Assertions.assertEquals(23, p.getSumOfAllVersions());
    }

    /**
     * Test the fourth example and verify the sum
     */
    @Test
    public void testExampleFour() {
        Packet p = new Packet(ByteHexConversion.convertHexStringToBinaryString("A0016C880162017C3686B18A3D4780"));
        Assertions.assertEquals(31, p.getSumOfAllVersions());
    }

}
