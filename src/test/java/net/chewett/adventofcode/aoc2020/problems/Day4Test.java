package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Day4Test {

    public List<String> getExampleInput() {
        List<String> example = new ArrayList<>();

        example.add("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd");
        example.add("byr:1937 iyr:2017 cid:147 hgt:183cm");
        example.add("");
        example.add("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884");
        example.add("hcl:#cfa07d byr:1929");
        example.add("");
        example.add("hcl:#ae17e1 iyr:2013");
        example.add("eyr:2024");
        example.add("ecl:brn pid:760753108 byr:1931");
        example.add("hgt:179cm");
        example.add("");
        example.add("hcl:#cfa07d eyr:2025 pid:166559648");
        example.add("iyr:2011 ecl:brn hgt:59in");

        return example;
    }

    public List<String> getInvalidPassports() {
        List<String> example = new ArrayList<>();

        example.add("eyr:1972 cid:100");
        example.add("hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926");
        example.add("");
        example.add("iyr:2019");
        example.add("hcl:#602927 eyr:1967 hgt:170cm");
        example.add("ecl:grn pid:012533040 byr:1946");
        example.add("");
        example.add("hcl:dab227 iyr:2012");
        example.add("ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277");
        example.add("");
        example.add("hgt:59cm ecl:zzz");
        example.add("eyr:2038 hcl:74454a iyr:2023");
        example.add("pid:3556412378 byr:2007");

        return example;
    }

    public List<String> getValidPassports() {
        List<String> example = new ArrayList<>();

        example.add("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980");
        example.add("hcl:#623a2f");
        example.add("");
        example.add("eyr:2029 ecl:blu cid:129 byr:1989");
        example.add("iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm");
        example.add("");
        example.add("hcl:#888785");
        example.add("hgt:164cm byr:2001 iyr:2015 cid:88");
        example.add("pid:545766238 ecl:hzl");
        example.add("eyr:2022");
        example.add("");
        example.add("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719");

        return example;

    }

    /**
     * Test Day 4 part one with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartOne() {
        List<String> lines = this.getExampleInput();

        Day4 d = new Day4();
        int partOneAnswer = d.solvePartOne(lines);
        Assertions.assertEquals(2, partOneAnswer);
    }

    /**
     * Test Day 4 part one with the invalid input (with the known result)
     * It has valid fields but invalid data, so it will pass all three passports
     */
    @Test
    public void testInvalidInputPartOne() {
        List<String> lines = this.getInvalidPassports();

        Day4 d = new Day4();
        int partOneAnswer = d.solvePartOne(lines);
        Assertions.assertEquals(4, partOneAnswer);
    }

    /**
     * Test Day 4 part one with the valid input (with the known result)
     */
    @Test
    public void testValidInputPartOne() {
        List<String> lines = this.getValidPassports();

        Day4 d = new Day4();
        int partOneAnswer = d.solvePartOne(lines);
        Assertions.assertEquals(4, partOneAnswer);
    }

    /**
     * Test Day 4 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 4);
        Day4 d = new Day4();
        int partOneAnswer = d.solvePartOne(lines);

        Assertions.assertEquals(256, partOneAnswer);
    }

    /**
     * Test Day 4 part two with the example input (with the known result)
     */
    @Test
    public void testExampleInputPartTwo() {
        List<String> lines = this.getExampleInput();

        Day4 d = new Day4();
        long partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(2, partTwoAnswer);
    }


    /**
     * Test Day 4 part two with the invalid input (with the known result)
     */
    @Test
    public void testInvalidInputPartTwo() {
        List<String> lines = this.getInvalidPassports();

        Day4 d = new Day4();
        long partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(0, partTwoAnswer);
    }

    /**
     * Test Day 4 part two with the invalid input (with the known result)
     */
    @Test
    public void testValidInputPartTwo() {
        List<String> lines = this.getValidPassports();

        Day4 d = new Day4();
        long partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(4, partTwoAnswer);
    }

    /**
     * Test Day 4 part two with the real input (with the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 4);
        Day4 d = new Day4();
        long partTwoAnswer = d.solvePartTwo(lines);

        Assertions.assertEquals(198, partTwoAnswer);
    }


}
