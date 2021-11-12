package net.chewett.adventofcode.aoc2015;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByteHexConversionTest {

    @Test
    public void testConvertBytesToHexWithNoInput() {
        byte[] b = new byte[0];
        String str = ByteHexConversion.convertBytesToHex(b);
        Assertions.assertEquals(0, str.length());
    }

    @Test
    public void testConvertBytesToHexSimpleInput() {
        byte[] b = new byte[1];
        b[0] = (byte) 0xa;

        String str = ByteHexConversion.convertBytesToHex(b);
        Assertions.assertEquals(2, str.length());
        Assertions.assertEquals("0a", str);
    }

    @Test
    public void testConvertBytesToHexTwoByteInput() {
        byte[] b = new byte[2];
        b[0] = (byte) 0xCA;
        b[1] = (byte) 0xFE;

        String str = ByteHexConversion.convertBytesToHex(b);
        Assertions.assertEquals(4, str.length());
        Assertions.assertEquals("cafe", str);
    }

}
