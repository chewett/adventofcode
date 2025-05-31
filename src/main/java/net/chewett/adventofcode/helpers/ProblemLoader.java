package net.chewett.adventofcode.helpers;

import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The problem loader is going to be a general static class which is going to let you load the problems into various
 * different formats depending on their format.
 */
public class ProblemLoader {

    /**
     * Given a year and a day this will load the problem input, read it line by line, convert each line to a long
     * and then return that as a list.
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return List of longs that are inside the given file
     */
    public static List<Long> loadProblemIntoLongList(int year, int day) {
        List<String> stringData = ProblemLoader.loadProblemIntoStringArray(year, day);
        List<Long> longData = new ArrayList<>();
        for(String str : stringData) {
            longData.add(Long.parseLong(str));
        }

        return longData;
    }


    /**
     * Given a year and a day this will load the problem input, read it line by line, convert each line to an integer
     * and then return that as a list.
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return List of integers that are inside the given file
     */
    public static List<Integer> loadProblemIntoIntegerList(int year, int day) {
        List<String> stringData = ProblemLoader.loadProblemIntoStringArray(year, day);
        List<Integer> ints = new ArrayList<>();
        for(String str : stringData) {
            ints.add(Integer.parseInt(str));
        }

        return ints;
    }

    /**
     * Given a year and a day this will load the problem input, split it on commas, and return an integer list
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return List of integers that are inside the given file
     */
    public static List<Integer> loadProblemFromCommaSeperatedStringIntoIntegerList(int year, int day) {
        return FormatConversion.convertCommaSeperatedStringToIntList(
            ProblemLoader.loadProblemIntoString(year, day)
        );
    }

    /**
     * Given a year and a day this will laod the problem input, read it line by line, and convert a two dimensional
     * list of characters
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return List of List of characters that are inside the file
     */
    public static List<List<Character>> loadProblemIntoXYCharList(int year, int day) {
        List<String> strings = ProblemLoader.loadProblemIntoStringArray(year, day);
        return FormatConversion.convertStringArrayToCharListList(strings);
    }

    /**
     * Given a year and a day this will load the problem input and return a string of the first line.
     * If there is an issue with the format (should only ever be a programming bug) a Runtime exception is thrown
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return A string representing the first line of the file
     */
    public static String loadProblemIntoString(int year, int day) {
        List<String> st = ProblemLoader.loadProblemIntoStringArray(year, day);
        if(st.size() > 1) {
            throw new RuntimeException("The problem for year " + year + " day " + day + " is longer than a single line");
        }
        return st.get(0);
    }

    /**
     * Given a year and a day this will load the problem input and return a list of strings representing the file
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return A list of strings representing the file
     */
    public static List<String> loadProblemIntoStringArray(int year, int day) {
        String filePath = "/aoc" + year + "/" + year + "_day_" + day + "_input.txt";

        List<String> lines = new ArrayList<>();
        try {
            //Use the URL decoder because the resource file path could give a %20 if there are spaces and this breaks everything
            File file = new File(URLDecoder.decode(ProblemLoader.class.getResource(filePath).getFile(), StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                lines.add(st);
            }
        }catch (IOException e) {
            //This shouldn't really happen so lets just catch it all and throw a runtime exception so its clear what the issue is.
            e.printStackTrace();
            throw new RuntimeException("Failed to load problem", e);
        }

        return lines;
    }

    /**
     * Given a year and a day this will load the problem input and return a Discrete2D position grid representing the
     * problem input
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return Discrete2DPositionGrid representing the problem input
     */
    public static Discrete2DPositionGrid<Integer> loadProblemIntoDiscrete2DPositionIntegerGrid(int year, int day) {
        List<List<Character>> gridData = ProblemLoader.loadProblemIntoXYCharList(year, day);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(gridData);
    }

    /**
     * Given a eyar and a day this will load the problem input and return a Discrete2D position grid representing the
     * problem input storing characters in the grid
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return Discrete2DPositionGrid representing the problem input
     */
    public static Discrete2DPositionGrid<Character> loadProblemIntoDiscrete2DPositionCharacterGrid(int year, int day) {
        List<List<Character>> gridData = ProblemLoader.loadProblemIntoXYCharList(year, day);
        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(gridData);
    }

    /**
     * Given a list of strings this will parse it into a series of character grids
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return List of Discrete2DPositionGrid's representing the problem input
     */
    public static List<Discrete2DPositionGrid<Character>> loadProblemIntoListOfDiscrete2DPositionCharacterGrids(int year, int day) {
        List<String> strings = ProblemLoader.loadProblemIntoStringArray(year, day);
        return FormatConversion.convertListOfStringsToListOfDiscrete2DPositionCharacterGrids(strings);
    }

}
