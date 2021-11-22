package net.chewett.adventofcode.aoc2015;

import net.chewett.adventofcode.aoc2015.problems.Day7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CircuitTableTest {

    /**
     * A simple test with the example input and the outputs on the page
     */
    @Test
    public void testExampleInput() {
        List<String> circuitLines = new ArrayList<>();
        circuitLines.add("123 -> x");
        circuitLines.add("456 -> y");
        circuitLines.add("x AND y -> d");
        circuitLines.add("x OR y -> e");
        circuitLines.add("x LSHIFT 2 -> f");
        circuitLines.add("y RSHIFT 2 -> g");
        circuitLines.add("NOT x -> h");
        circuitLines.add("NOT y -> i");

        CircuitTable ct = new CircuitTable(circuitLines);
        ct.solve();
        Assertions.assertEquals(72, ct.getValueOfWire("d"));
        Assertions.assertEquals(507, ct.getValueOfWire("e"));
        Assertions.assertEquals(492, ct.getValueOfWire("f"));
        Assertions.assertEquals(114, ct.getValueOfWire("g"));
        Assertions.assertEquals(65412, ct.getValueOfWire("h"));
        Assertions.assertEquals(65079, ct.getValueOfWire("i"));
        Assertions.assertEquals(123, ct.getValueOfWire("x"));
        Assertions.assertEquals(456, ct.getValueOfWire("y"));
    }


    /**
     * Tests to make sure that setting a single value to a wire works as expected
     */
    @Test
    public void testSettingOne() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("123 -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(123, simpleTestAnswer);
    }

    /**
     * Tests making sure that a chained set works when it's in a different order
     */
    @Test
    public void testSettingChainedNotOrdered() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("b -> a");
        exampleInput.add("123 -> b");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(123, simpleTestAnswer);
    }

    /**
     * Test a really simple bitwise AND
     */
    @Test
    public void testAndSimple() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("2 -> c");
        exampleInput.add("3 -> b");
        exampleInput.add("b AND c -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(2, simpleTestAnswer);
    }

    /**
     * Tests a bitwise AND using a number too
     */
    @Test
    public void testAndSimpleWithNumbers() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("9 -> c");
        exampleInput.add("c AND 3 -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(1, simpleTestAnswer);
    }

    /**
     * Tests a simple bitwise or
     */
    @Test
    public void testOrSimple() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("8 -> c");
        exampleInput.add("1 -> b");
        exampleInput.add("b OR c -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(9, simpleTestAnswer);
    }

    /**
     * Tests a bitwise OR using a constant instead of a wire
     */
    @Test
    public void testORSimpleWithNumbers() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("8 -> c");
        exampleInput.add("c OR 1 -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(9, simpleTestAnswer);
    }

    /**
     * Tests to see if a left shift 1 works as expected
     */
    @Test
    public void testLshiftSimple() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("8 -> c");
        exampleInput.add("c LSHIFT 1 -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(16, simpleTestAnswer);
    }

    /**
     * Tests to see if a right shift 1 works as expected
     */
    @Test
    public void testRshiftSimple() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("8 -> c");
        exampleInput.add("c RSHIFT 1 -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(4, simpleTestAnswer);
    }

    /**
     * Tests to see if simple NOT works as expected
     */
    @Test
    public void testNotSimple() {
        List<String> exampleInput = new ArrayList<>();
        exampleInput.add("32768 -> c");
        exampleInput.add("NOT c -> a");

        CircuitTable ct = new CircuitTable(exampleInput);
        long simpleTestAnswer = ct.getValueOfWire("a");
        Assertions.assertEquals(32767, simpleTestAnswer);
    }

}
