package net.chewett.adventofcode.aoc2015.problems;

import net.chewett.adventofcode.aoc2015.CircuitTable;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 7: Some Assembly Required ---
 * This year, Santa brought little Bobby Tables a set of wires and bitwise logic gates! Unfortunately, little Bobby is
 * a little under the recommended age range, and he needs help assembling the circuit.
 *
 * Each wire has an identifier (some lowercase letters) and can carry a 16-bit signal (a number from 0 to 65535). A
 * signal is provided to each wire by a gate, another wire, or some specific value. Each wire can only get a signal from
 * one source, but can provide its signal to multiple destinations. A gate provides no signal until all of its inputs
 * have a signal.
 *
 * The included instructions booklet describes how to connect the parts together: x AND y -> z means to connect wires x
 * and y to an AND gate, and then connect its output to wire z.
 *
 * For example:
 *
 * 123 -> x means that the signal 123 is provided to wire x.
 * x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
 * p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
 * NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
 * Other possible gates include OR (bitwise OR) and RSHIFT (right-shift). If, for some reason, you'd like to emulate the
 * circuit instead, almost all programming languages (for example, C, JavaScript, or Python) provide operators for these
 * gates.
 *
 * For example, here is a simple circuit:
 *
 * 123 -> x
 * 456 -> y
 * x AND y -> d
 * x OR y -> e
 * x LSHIFT 2 -> f
 * y RSHIFT 2 -> g
 * NOT x -> h
 * NOT y -> i
 * After it is run, these are the signals on the wires:
 *
 * d: 72
 * e: 507
 * f: 492
 * g: 114
 * h: 65412
 * i: 65079
 * x: 123
 * y: 456
 * In little Bobby's kit's instructions booklet (provided as your puzzle input), what signal is ultimately provided to
 * wire a?
 *
 * --- Part Two ---
 * Now, take the signal you got on wire a, override wire b to that signal, and reset the other wires (including wire a).
 * What new signal is ultimately provided to wire a?
 */
public class Day7 {

    /**
     * When given a set of instructions of how to connect the circuits it will return the value of a once connected
     * @param circuitLines List of instructions how to connect the circuits
     * @return The value of wire a once it has been connected together
     */
    public long solvePartOne(List<String> circuitLines) {
        CircuitTable ct = new CircuitTable(circuitLines);
        ct.solve();
        return ct.getValueOfWire("a");
    }

    /**
     * When given a set of instructions of how to connect the circuits, it will do it twice, feeding the value for a
     * back into the circuit and return the final value of a
     * @param circuitLines List of instructions how to connect the circuits
     * @return The value of wire a once it has been connected together
     */
    public long solvePartTwo(List<String> circuitLines) {
        CircuitTable ct = new CircuitTable(circuitLines);
        ct.solve();
        long valueA = ct.getValueOfWire("a");

        //Now we have to change the value of B to be the old value of A
        for(int i = 0; i < circuitLines.size(); i++) {
            String str = circuitLines.get(i);
            if(str.endsWith("-> b")) {
                circuitLines.set(i, valueA + " -> b");
            }
        }

        CircuitTable ct2 = new CircuitTable(circuitLines);
        ct.solve();
        return ct2.getValueOfWire("a");
    }

    public static void main(String[] args) {
        List<String> circuitLines = ProblemLoader.loadProblemIntoStringArray(2015, 7);
        Day7 d = new Day7();
        long valueOfA = d.solvePartOne(circuitLines);
        System.out.println("The value of wire A once we have run the program " + valueOfA);
        long valueOfASecondTime = d.solvePartTwo(circuitLines);
        System.out.println("The value of wire A once we have run it twice, substituting the value of B for the result of A in the second instance" + valueOfASecondTime);
    }

}
