package net.chewett.adventofcode.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The problem loader is going to be a general static class which is going to let you load the problems into various
 * different formats depending on their format.
 */
public class ProblemLoader {

    /**
     * Given a year and a day this will load the problem input, read it line by line, convert each line to an integer
     * and then return that as a list.
     * @param year Year to load the problem for
     * @param day Day to load the problem for
     * @return List of integers that are inside the given file
     */
    public static List<Integer> loadProblemIntoIntegerList(int year, int day) {
        String filePath = "/aoc" + year + "/" + year + "_day_" + day + "_input.txt";

        List<Integer> ints = new ArrayList<>();
        try {
            File file = new File(ProblemLoader.class.getResource(filePath).getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                int val = Integer.parseInt(st);
                ints.add(val);
            }
        }catch (IOException e) {
            //This shouldnt really happen so lets just catch it all and throw a runtime exception so its clear what the issue is.
            e.printStackTrace();
            throw new RuntimeException("Failed to load problem", e);
        }

        return ints;
    }

    public static List<List<Character>> loadProblemIntoXYCharList(int year, int day) {
        String filePath = "/aoc" + year + "/" + year + "_day_" + day + "_input.txt";

        List<List<Character>> chars = new ArrayList<>();
        try {
            File file = new File(ProblemLoader.class.getResource(filePath).getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                List<Character> rowList = new ArrayList<>();
                for(int i = 0; i < st.length(); i++) {
                    rowList.add(st.charAt(i));
                }
                chars.add(rowList);
            }
        }catch (IOException e) {
            //This shouldnt really happen so lets just catch it all and throw a runtime exception so its clear what the issue is.
            e.printStackTrace();
            throw new RuntimeException("Failed to load problem", e);
        }

        return chars;
    }

    public static List<String> loadProblemIntoStringArray(int year, int day) {
        String filePath = "/aoc" + year + "/" + year + "_day_" + day + "_input.txt";

        List<String> lines = new ArrayList<>();
        try {
            File file = new File(ProblemLoader.class.getResource(filePath).getFile());
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                lines.add(st);
            }
        }catch (IOException e) {
            //This shouldnt really happen so lets just catch it all and throw a runtime exception so its clear what the issue is.
            e.printStackTrace();
            throw new RuntimeException("Failed to load problem", e);
        }

        return lines;
    }



}
