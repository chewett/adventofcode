package net.chewett.adventofcode.aoc2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher {

    /**
     * Given a string this will return the MD5 hash in hex
     * @param str String to hash
     * @return Hash of the given string in hex
     */
    public static String hash(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return ByteHexConversion.convertBytesToHex(digest);
        }catch(NoSuchAlgorithmException e) {
            //Do nothing, we know this algorithm exists
            throw new RuntimeException("This should never occur, MD5 algorithm does not exist");
        }

    }

}
