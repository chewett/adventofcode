package net.chewett.adventofcode;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProblemCreator {

    public static List<String> loadTestTemplateFile() {
        return ProblemCreator.loadFileHelper("/UnitTestTemplate.txt");
    }

    public static List<String> loadClassTemplateFile() {
        return ProblemCreator.loadFileHelper("/ProblemClassTemplate.txt");
    }

    public static List<String> loadFileHelper(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(ProblemLoader.class.getResource(filePath).getFile());
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

    public static List<String> runReplacement(List<String> curFile, String template, String replacement) {
        List<String> newLines = new ArrayList<>();

        for(String str : curFile) {
            newLines.add(str.replaceAll(template.replace("{", "\\{"), replacement));
        }

        return newLines;
    }


    public static void main(String[] args) throws IOException {

        int year = 2023;
        int day = 4;
        String type = "StringList";

        String curDir = System.getProperty("user.dir");

        String classPath = curDir + "\\src\\main\\java\\net\\chewett\\adventofcode\\aoc" + year + "\\problems\\Day" + day + ".java";
        String testPath = curDir + "\\src\\test\\java\\net\\chewett\\adventofcode\\aoc" + year + "\\problems\\Day" + day + "Test.java";
        String inputPath = curDir + "\\src\\main\\resources\\aoc" + year + "\\" + year + "_day_" + day + "_input.txt";


        //Write the class file
        System.out.println("Writing: " + classPath);
        List<String> classFile = ProblemCreator.loadClassTemplateFile();
        classFile = ProblemCreator.runReplacement(classFile, "{{year}}", ""+year);
        classFile = ProblemCreator.runReplacement(classFile, "{{day}}", ""+day);
        Files.write(Paths.get(classPath), classFile);

        //Write the test file
        System.out.println("Writing: " + testPath);
        List<String> testFile = ProblemCreator.loadTestTemplateFile();
        testFile = ProblemCreator.runReplacement(testFile, "{{year}}", ""+year);
        testFile = ProblemCreator.runReplacement(testFile, "{{day}}", ""+day);
        Files.write(Paths.get(testPath), testFile);

        //Write the input file
        System.out.println("Writing: " + inputPath);
        Files.write(Paths.get(inputPath), new ArrayList<>());









    }

}
