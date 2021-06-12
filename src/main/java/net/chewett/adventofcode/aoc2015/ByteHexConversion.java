package net.chewett.adventofcode.aoc2015;

public class ByteHexConversion {

    /**
     * Takes an array of bytes and returns a string of hex representing them
     * @param bytes Array of bytes to convert to hex
     * @return String representing the hex form of the given bytes
     */
    public static String convertBytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            //Bitwise & to "force" the number into a range that the toHexString likes
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if(hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
