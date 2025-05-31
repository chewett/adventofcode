package net.chewett.adventofcode;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<String> runReplacements(List<String> curFile, Map<String, String> replacements) {
        List<String> newLines = new ArrayList<>();

        for(String str : curFile) {
            String stringToReplace = str;

            for(Map.Entry<String, String> e : replacements.entrySet()) {
                stringToReplace = stringToReplace.replaceAll("\\{\\{" + e.getKey() + "\\}\\}", e.getValue());
            }
            newLines.add(stringToReplace);
        }

        return newLines;
    }


    public static void main(String[] args) throws IOException {

        int year = 2019;
        String day = "15";
        String type = "StringList";
        //type = "IntList";
        type = "String";
        //type = "LongList";
        //type = "CharacterGrid";
        //type = "IntGrid";

        String curDir = System.getProperty("user.dir");

        String classPath = curDir + "\\src\\main\\java\\net\\chewett\\adventofcode\\aoc" + year + "\\problems\\Day" + day + ".java";
        String testPath = curDir + "\\src\\test\\java\\net\\chewett\\adventofcode\\aoc" + year + "\\problems\\Day" + day + "Test.java";
        String inputPath = curDir + "\\src\\main\\resources\\aoc" + year + "\\" + year + "_day_" + day + "_input.txt";

        Map<String, String> replacements = new HashMap<>();
        replacements.put("year", ""+year);
        replacements.put("day", ""+day);

        if(type.equals("StringList")) {
            replacements.put("type", "List<String>");
            replacements.put("problemLoaderFunc", "loadProblemIntoStringArray");
            replacements.put("imports", "");
            replacements.put("exampleInputStart", "List<String> input = new ArrayList<>();");
            replacements.put("exampleInputEnd", "return input;");

        }else if(type.equals("IntList")) {
            replacements.put("type", "List<Integer>");
            replacements.put("problemLoaderFunc", "loadProblemIntoIntegerList");
            replacements.put("imports", "");
            replacements.put("exampleInputStart", "List<Integer> input = new ArrayList<>();");
            replacements.put("exampleInputEnd", "return input;");

        }else if(type.equals("LongList")) {
            replacements.put("type", "List<Long>");
            replacements.put("problemLoaderFunc", "loadProblemIntoLongList");
            replacements.put("imports", "");
            replacements.put("exampleInputStart", "List<Long> input = new ArrayList<>();");
            replacements.put("exampleInputEnd", "return input;");

        }else if(type.equals("String")) {
            replacements.put("type", "String");
            replacements.put("problemLoaderFunc", "loadProblemIntoString");
            replacements.put("imports", "");
            replacements.put("exampleInputStart", "String input = \"\";");
            replacements.put("exampleInputEnd", "return input;");

        }else if(type.equals("CharacterGrid")) {
            replacements.put("type", "Discrete2DPositionGrid<Character>");
            replacements.put("problemLoaderFunc", "loadProblemIntoDiscrete2DPositionCharacterGrid");
            replacements.put("imports", "import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;\n" +
                    "import net.chewett.adventofcode.helpers.FormatConversion;");
            replacements.put("exampleInputStart", "List<String> input = new ArrayList<>();");
            replacements.put("exampleInputEnd", "List<List<Character>> engineSchematicArray = FormatConversion.convertStringArrayToCharListList(input);\n" +
                    "        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGridCharacter(engineSchematicArray);");

        }else if(type.equals("IntGrid")) {
            replacements.put("type", "Discrete2DPositionGrid<Integer>");
            replacements.put("problemLoaderFunc", "loadProblemIntoDiscrete2DPositionIntegerGrid");
            replacements.put("imports", "import net.chewett.adventofcode.datastructures.Discrete2DPositionGrid;\n" +
                    "import net.chewett.adventofcode.helpers.FormatConversion;");
            replacements.put("exampleInputStart", "List<String> input = new ArrayList<>();");
            replacements.put("exampleInputEnd", "List<List<Character>> inputArr = FormatConversion.convertStringArrayToCharListList(input);\n" +
                    "        return FormatConversion.convertCharArrayIntoDiscrete2DPositionGrid(inputArr);");
        }


        //Write the class file
        System.out.println("Writing: " + classPath);
        List<String> classFile = ProblemCreator.loadClassTemplateFile();
        classFile = ProblemCreator.runReplacements(classFile, replacements);
        Files.write(Paths.get(classPath), classFile);

        //Write the test file
        System.out.println("Writing: " + testPath);
        List<String> testFile = ProblemCreator.loadTestTemplateFile();
        testFile = ProblemCreator.runReplacements(testFile, replacements);
        Files.write(Paths.get(testPath), testFile);

        //Write the input file
        if(Paths.get(inputPath).toFile().exists()) {
            System.out.println("File already exists, NOT writing: " + inputPath);
        }else{
            System.out.println("Writing: " + inputPath);
            Files.write(Paths.get(inputPath), new ArrayList<>());
        }












    }

}
