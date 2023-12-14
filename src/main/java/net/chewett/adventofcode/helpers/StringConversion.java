package net.chewett.adventofcode.helpers;

public class StringConversion {

    /**
     * Given a string and a split regex this will convert the list to a list of ints
     * @param str String to split
     * @param splitRegex Split regex
     * @return Int array that was represented by the string
     */
    public static int[] convertStringToIntArray(String str, String splitRegex) {
        String[] stringParts = str.split(splitRegex);
        int[] arr = new int[stringParts.length];
        for(int i = 0; i < stringParts.length; i++) {
            arr[i] = Integer.parseInt(stringParts[i]);
        }
        return arr;
    }

}
