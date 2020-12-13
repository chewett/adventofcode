package net.chewett.adventofcode.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple conversion helpers
 */
public class FormatConversion {

    /**
     * Simple function to convert a string list into a List List Character object
     * @param strList String list to convert
     * @return List of Lists of Characters that can be X/Y indexed
     */
    public static List<List<Character>> convertStringArrayToCharListList(List<String> strList) {
        return FormatConversion.convertStringArrayToCharListList((String[]) strList.toArray());
    }

    /**
     * Simple function to convert a string array into a List List Character object
     * @param strArr String array to convert
     * @return List of Lists of Characters that can be X/Y indexed
     */
    public static List<List<Character>> convertStringArrayToCharListList(String[] strArr) {
        List<List<Character>> newCharList = new ArrayList<>();

        for(String str : strArr) {
            List<Character> arrToAddTo = new ArrayList<>();
            for(int i = 0; i < str.length(); i++) {
                arrToAddTo.add(str.charAt(i));
            }
            newCharList.add(arrToAddTo);
        }

        return newCharList;
    }

}
