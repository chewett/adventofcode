package net.chewett.adventofcode.helpers;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;

import java.util.*;
import java.util.stream.Collectors;

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
        return FormatConversion.convertStringArrayToCharListList(strList.toArray(new String[0]));
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

    /**
     * Given a List of List Character this converts it to a List String
     * @param charListList Character List List to convert over
     * @return List of Strings representing the character List List
     */
    public static List<String> convertCharListListToStringList(List<List<Character>> charListList) {
        List<String> stringList = new ArrayList<>();
        for(List<Character> chars : charListList) {
            StringBuilder sb = new StringBuilder();

            for(char c : chars) {
                sb.append(c);
            }
            stringList.add(sb.toString());
        }
        return stringList;
    }

    /**
     * Given a List of List of Characters this will rotate the list 90 degrees on his axis anticlockwise and return a
     * new list. This assumes that the x,y lists form a rectangular array
     * @param charListList List that you wish to rotate
     * @return New list rotated by 90 degrees anticlockwise
     */
    public static List<List<Character>> rotateCharListListAnticlockwise(List<List<Character>> charListList) {
        char[][] tmpCharArray = new char[charListList.size()][charListList.get(0).size()];

        for(int y = 0; y < charListList.size(); y++) {
            for(int x = 0; x < charListList.get(y).size(); x++) {
                int newX = y;
                int newY = charListList.get(y).size() - 1 - x;

                tmpCharArray[newY][newX] = charListList.get(y).get(x);
            }
        }

        List<List<Character>> newReturnListList = new ArrayList<>();
        for(int y = 0; y < tmpCharArray.length; y++) {
            List<Character> row = new ArrayList<>();
            for (int x = 0; x < tmpCharArray[y].length; x++) {
                row.add(tmpCharArray[y][x]);
            }
            newReturnListList.add(row);
        }

        return newReturnListList;
    }

    /**
     * Given a List of List of Characters this will flip all the elements. So each Sub-list will have their order
     * reversed.
     * @param charListList List you wish to flip
     * @return New List List of characters
     */
    public static List<List<Character>> flipCharListList(List<List<Character>> charListList) {
        List<List<Character>> flippedArray = new ArrayList<>();
        for(List<Character> charList : charListList) {
            List<Character> newCharList = new ArrayList<>();
            for(int i = charList.size() - 1; i >= 0; i--) {
                newCharList.add(charList.get(i));
            }
            flippedArray.add(newCharList);
        }
        return flippedArray;
    }

    public static List<Integer> convertCommaSeperatedStringToIntList(String str) {
        return Arrays.stream(str.split(",")).map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * Given a string this will return a Set with the characters of the string as the values inside the set
     * @param str String to convert into a Set
     * @return Set with all the characters of the string as members
     */
    public static Set<Character> convertStringToSet(String str) {
        Set<Character> charSet = new HashSet<>();
        for(int i = 0; i < str.length(); i++) {
            charSet.add(str.charAt(i));
        }
        return charSet;
    }


    /**
     * Converts a List of character lists into a Discrete2DPositionGrid<Integer> with a default value of -1
     * @param gridData The input grid data in List List Character format
     * @return A Discrete2DPositionGrid
     */
    public static Discrete2DPositionGrid<Integer> convertCharArrayIntoDiscrete2DPositionGrid(List<List<Character>> gridData) {
        Discrete2DPositionGrid<Integer> grid = new Discrete2DPositionGrid<>(-1);
        int y = 0;
        int x = 0;
        for (List<Character> listChar : gridData) {
            for (char c : listChar) {
                grid.setValueAtPosition(x, y, Integer.parseInt("" + c));
                x++;
            }
            y++;
            x = 0;
        }

        return grid;
    }

    /**
     * Converts a List of character lists into a Discrete2DPositionGrid<Character> with a default value of ' '
     * @param gridData The input grid data in List List Character format
     * @return A Discrete2DPositionGrid
     */
    public static Discrete2DPositionGrid<Character> convertCharArrayIntoDiscrete2DPositionGridCharacter(List<List<Character>> gridData) {
        Discrete2DPositionGrid<Character> grid = new Discrete2DPositionGrid<>(' ');
        int y = 0;
        int x = 0;
        for (List<Character> listChar : gridData) {
            for (char c : listChar) {
                grid.setValueAtPosition(x, y, c);
                x++;
            }
            y++;
            x = 0;
        }

        return grid;
    }

    /**
     * Given a list of strings this will parse it into a series of character grids
     * @param input List of strings representing a set of character grids
     * @return List of character grids parsed from the input
     */
    public static List<Discrete2DPositionGrid<Character>> convertListOfStringsToListOfDiscrete2DPositionCharacterGrids(List<String> input) {
        List<String> curCharGrid = new ArrayList<>();
        List<Discrete2DPositionGrid<Character>> grids = new ArrayList<>();

        for(String str : input) {
            if(str.isEmpty()) {
                List<List<Character>> newCharArr = FormatConversion.convertStringArrayToCharListList(curCharGrid);
                grids.add(FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(newCharArr));
                curCharGrid = new ArrayList<>();
            }else{
                curCharGrid.add(str);
            }
        }

        //Handle the final graph
        List<List<Character>> newCharArr = FormatConversion.convertStringArrayToCharListList(curCharGrid);
        grids.add(FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(newCharArr));

        return grids;
    }

}
