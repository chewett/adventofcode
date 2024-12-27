package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 *
 */
public class Day24 {

    /**
     * This just runs all the connections iteratively until all are done
     * @param input List of connections
     * @return The final number as a long
     */
    public long solvePartOne(List<String> input) {
        Map<String, Boolean> vals = new HashMap<>();
        LinkedList<List<String>> connections = new LinkedList<>();

        boolean firstSection = true;
        for(String s : input) {
            if(s.equals("")) {
                firstSection = false;
            }else if(firstSection) {
                String[] parts = s.split(": ");
                boolean boolVal = parts[1].equals("1");
                vals.put(parts[0], boolVal);
            }else{
                List<String> newWire = new ArrayList<>();
                String[] parts = s.split(" ");
                newWire.add(parts[0]);
                newWire.add(parts[1]);
                newWire.add(parts[2]);
                newWire.add(parts[4]);
                connections.add(newWire);
            }
        }

        //Keep looping until we have converted all the connections into a distinct value. No loops makes this nice
        while(!connections.isEmpty()) {
            for(int i = 0; i < connections.size(); i++) {
                List<String> connectionToTry = connections.get(i);
                String operator = connectionToTry.get(1);
                if(vals.containsKey(connectionToTry.get(0)) && vals.containsKey(connectionToTry.get(2))) {

                    boolean newVal;
                    if(operator.equals("AND")) {
                        newVal = vals.get(connectionToTry.get(0)) && vals.get(connectionToTry.get(2));
                    }else if(operator.equals("OR")) {
                        newVal = vals.get(connectionToTry.get(0)) || vals.get(connectionToTry.get(2));
                    }else if(operator.equals("XOR")) {
                        newVal = vals.get(connectionToTry.get(0)) ^ vals.get(connectionToTry.get(2));
                    }else{
                        throw new RuntimeException("Unknown operator " + operator);
                    }

                    vals.put(connectionToTry.get(3), newVal);
                    connections.remove(i);
                    break;
                }
            }
        }

        //Then work out all the outputs in a nicer form
        List<String> outputs = new ArrayList<>();
        for(Map.Entry<String, Boolean> entry : vals.entrySet()) {
            outputs.add(entry.getKey());
        }

        //Sort them and then get the parts and return it as a Long
        StringBuilder outputBits = new StringBuilder();
        outputs.sort(Comparator.reverseOrder());
        for(String output : outputs) {
            if(output.startsWith("z")) {
                outputBits.append(vals.get(output) ? "1" : "0");
            }
        }

        return Long.parseLong(outputBits.toString(), 2);
    }

    /**
     * This is a fun one but initially I didn't know how to code it.
     *
     * So they tell you this is a binary adder, and then that makes perfect sense with the instructions. Essentially
     * A binary adder is:
     *
     * X XOR Y = A
     * X AND Y = B
     * Carry XOR A = Output bit
     * Carry AND A = D
     * D AND B = Carry out
     *
     * See: https://en.wikipedia.org/wiki/Adder_(electronics)#/media/File:Full-adder_logic_diagram.svg
     *
     * Since I knew this had a "common pattern" I did the maths adding up the two binary numbers to compare the bad bits,
     * then drew it all in dot.
     *
     * By slowly modifying the output and flipping wires I ended up with the solution... But how to code that?
     *
     * I could have actually just done that in code, work out the least significant bit that was wrong, kept flipping it
     * until it fixed itself, and then iteratively did that. But I had a look at what reddit did and I stumbled on:
     * https://www.reddit.com/r/adventofcode/comments/1hla5ql/comment/m3kws15/
     *
     * This offered four "rules" to detect the wrong parts which makes sense based on the adder:
     *   - If output is Z then it must be an XOR (does not count for final z45 output)
     *   - If output is not z AND inputs are not X and Y then it must be an AND or OR gate
     *   - If the XOR has inputs X and Y, then it's output must be to an XOR (does not apply to x00 and y00 inputs)
     *   - If there is an AND then it's output must go to an OR (does not apply to x00 and y00 inputs)
     *
     *  Then you just scan the connections for these invalid rules, and print them out.
     *
     *  Very cool
     *
     * @param input List of connections
     * @return A list of the invalid parts
     */
    public String solvePartTwo(List<String> input) {

        //Get the connections
        List<List<String>> allConn = new ArrayList<>();
        boolean firstSection = true;
        for(String s : input) {
            if(s.equals("")) {
                firstSection = false;
            }else if(firstSection) {
                //Do nothing
            }else{
                List<String> newWire = new ArrayList<>();
                String[] parts = s.split(" ");
                newWire.add(parts[0]);
                newWire.add(parts[1]);
                newWire.add(parts[2]);
                newWire.add(parts[4]);
                allConn.add(newWire);
            }
        }

        //Loop over the items and work out which ones are bad
        Set<String> badOutputs = new HashSet<>();
        for(List<String> connectionToTry : allConn) {

            //If the output is to Z, then the gate must be XOR because otherwise it is wrong
            if (connectionToTry.get(3).startsWith("z") && !connectionToTry.get(1).equals("XOR")
                && !connectionToTry.get(3).equals("z45")
            ) {
                badOutputs.add(connectionToTry.get(3));
            }

            //The only two XOR gates point to Z, OR have X+Y as inputs. So if something is an XOR
            //And doesn't fit this it's wrong
            if (
                !connectionToTry.get(3).startsWith("z") &&
                    !(
                        (connectionToTry.get(0).startsWith("x") && connectionToTry.get(2).startsWith("y")) ||
                        (connectionToTry.get(2).startsWith("x") && connectionToTry.get(0).startsWith("y"))
                    )
                && connectionToTry.get(1).equals("XOR")
            ) {
                badOutputs.add(connectionToTry.get(3));
            }

            //These next two rules DO NOT apply to things with input X00 and y00
            if (!(
                    (connectionToTry.get(0).equals("x00") && connectionToTry.get(2).equals("y00")) ||
                            (connectionToTry.get(2).equals("x00") && connectionToTry.get(0).equals("y00"))
            )) {

                //If you have an XOR with X and Y as the input, the output must be an input to an XOR
                if (
                        ((connectionToTry.get(0).startsWith("x") && connectionToTry.get(2).startsWith("y")) ||
                                (connectionToTry.get(2).startsWith("x") && connectionToTry.get(0).startsWith("y")))
                                && connectionToTry.get(1).equals("XOR")
                ) {
                    //Check to see if the output is an input to an XOR

                    String gateToSearchFor = connectionToTry.get(3);

                    boolean foundMatch = false;
                    for (List<String> seeIfWeCanFindAMatch : allConn) {
                        //Look for an XOR with this as an input
                        if (seeIfWeCanFindAMatch.get(1).equals("XOR") &&
                                (seeIfWeCanFindAMatch.get(0).equals(gateToSearchFor) ||
                                        seeIfWeCanFindAMatch.get(2).equals(gateToSearchFor))
                        ) {
                            foundMatch = true;
                        }
                    }

                    if (!foundMatch) {
                        badOutputs.add(connectionToTry.get(3));
                    }
                }

                // If I have an AND gate there must be an OR gate with this gate as an input
                if (connectionToTry.get(1).equals("AND")) {
                    String gateToSearchFor = connectionToTry.get(3);

                    boolean foundMatch = false;
                    for (List<String> seeIfWeCanFindAMatch : allConn) {
                        //Look for an OR with this as an input
                        if (seeIfWeCanFindAMatch.get(1).equals("OR") &&
                                (seeIfWeCanFindAMatch.get(0).equals(gateToSearchFor) ||
                                        seeIfWeCanFindAMatch.get(2).equals(gateToSearchFor))
                        ) {
                            foundMatch = true;
                        }
                    }

                    if (!foundMatch) {
                        badOutputs.add(connectionToTry.get(3));
                    }

                }
            }
        }

        List<String> badOutputsSorted= new ArrayList<>(badOutputs);
        Collections.sort(badOutputsSorted);

        return String.join(",", badOutputsSorted);
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 24);

        Day24 d = new Day24();
        long partOne = d.solvePartOne(input);
        System.out.println("The outputs are " + partOne);

        String partTwo = d.solvePartTwo(input);
        System.out.println("The ordered wrong outputs are " + partTwo);
    }
}


