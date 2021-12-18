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

    /**
     * Really simple function to convert a string of hex into a binary string
     * @param hexString A string representing a hex number
     * @return A string of binary
     */
    public static String convertHexStringToBinaryString(String hexString) {
        String newHexString = hexString;
        newHexString = newHexString.replaceAll("0", "0000");
        newHexString = newHexString.replaceAll("1", "0001");
        newHexString = newHexString.replaceAll("2", "0010");
        newHexString = newHexString.replaceAll("3", "0011");
        newHexString = newHexString.replaceAll("4", "0100");
        newHexString = newHexString.replaceAll("5", "0101");
        newHexString = newHexString.replaceAll("6", "0110");
        newHexString = newHexString.replaceAll("7", "0111");
        newHexString = newHexString.replaceAll("8", "1000");
        newHexString = newHexString.replaceAll("9", "1001");
        newHexString = newHexString.replaceAll("A", "1010");
        newHexString = newHexString.replaceAll("B", "1011");
        newHexString = newHexString.replaceAll("C", "1100");
        newHexString = newHexString.replaceAll("D", "1101");
        newHexString = newHexString.replaceAll("E", "1110");
        newHexString = newHexString.replaceAll("F", "1111");
        return newHexString;
    }

}
