package net.chewett.adventofcode.aoc2015;

import java.util.*;

/**
 * This class is a small wrapper to hold the details of the circuit, run it, and then save the values of all the wires
 * in the end
 */
public class CircuitTable {

    private List<String> circuitLines;
    private boolean solved = false;
    private Map<String, Long> wireValues = new HashMap<>();

    public CircuitTable(List<String> circuitLines) {
        this.circuitLines = circuitLines;
    }

    /**
     * Helper to attempt to parse the string value and determine if it's a wire reference or if it's just a long
     * @param dataToParse String representing a value or wire reference to assign
     * @return Null if the wire reference isn't yet known or the value of the long or wire reference
     */
    private Long parseConstantOrWireNumber(String dataToParse) {
        long leftSide;
        try {
            leftSide = Long.parseLong(dataToParse);
        }catch(NumberFormatException e) {

            //We don't have this value, time to put it onto the queue again!
            if(!this.wireValues.containsKey(dataToParse)) {
                return null;
            }

            leftSide = this.wireValues.get(dataToParse);
        }
        return leftSide;
    }

    /**
     * When run this will determine what each wire has as a value
     */
    public void solve() {
        if(!this.solved) {
            //We are going to keep track of what lines we still need to process as we may not be able to process them
            //all initially so we might have to revisit them
            Queue<String> linesToProcess = new LinkedList<>(circuitLines);

            int ranEnoughTime = 0;

            //Keep running until there are no lines to process
            while(linesToProcess.peek() != null) {
                //This is mainly for debug, if you have something configured run it will get stuck in a loop of processing
                ranEnoughTime++;
                if(ranEnoughTime > 25000) {
                    throw new RuntimeException("Ending as this has run too long and hit a loop most likely");
                }

                String str = linesToProcess.poll();

                //Split it into the two parts
                String[] operationParts = str.split(" -> ");
                String outputWire = operationParts[1];
                String operation = operationParts[0];

                long outValue = 0;
                //Handle these values differently as they have two parts
                if(operation.contains(" AND ") || operation.contains(" OR ") || operation.contains(" LSHIFT ") || operation.contains(" RSHIFT ")) {
                    String[] leftRightParts = operation.split(" (AND|OR|LSHIFT|RSHIFT) ");

                    Long leftSide = this.parseConstantOrWireNumber(leftRightParts[0]);
                    //If we return null, then we can't resolve this yet.
                    if(leftSide == null) {
                        linesToProcess.add(str);
                        continue;
                    }

                    Long rightSide = this.parseConstantOrWireNumber(leftRightParts[1]);
                    //If we return null, then we can't resolve this yet.
                    if(rightSide == null) {
                        linesToProcess.add(str);
                        continue;
                    }

                    //Actually perform the operations now
                    if(operation.contains(" AND ")) {
                        outValue = leftSide & rightSide;
                    }else if(operation.contains(" OR ")) {
                        outValue = leftSide | rightSide;
                    }else if(operation.contains(" LSHIFT ")) {
                        outValue = leftSide << rightSide;
                    }else if(operation.contains(" RSHIFT ")) {
                        outValue = leftSide >> rightSide;
                    }

                    //NOT is handled differently as it's a simple value
                }else if(operation.contains("NOT ")) {
                    String thisWire = operation.replace("NOT ", "");

                    //If we don't have this yet, put it onto the back of the queue and go to the next part
                    if(!this.wireValues.containsKey(thisWire)) {
                        linesToProcess.add(str);
                        continue;
                    }

                    long rightPart = this.wireValues.get(thisWire);
                    outValue = ~rightPart;
                    if(outValue < 0) {
                        //Values are from 0 -> 65535 so if it becomes negative we force it into the range
                        outValue += 65536;
                    }

                }else{
                    //If it isn't an operation it's a simple set
                    try {
                        outValue = Long.parseLong(operation);
                    }catch(NumberFormatException e) {

                        //We don't have this value, time to put it onto the queue again!
                        if(!this.wireValues.containsKey(operation)) {
                            linesToProcess.add(str);
                            continue;
                        }

                        outValue = this.wireValues.get(operation);
                    }
                }

                this.wireValues.put(outputWire, outValue);
                ranEnoughTime = 0;
            }

            this.solved = true;
        }
    }

    /**
     * Simple method to get the value of a specific wire
     * If this hasn't been solved it will be solved first.
     * @param wire String representing the wire name
     * @return The value of the wire
     */
    public long getValueOfWire(String wire) {
        if(!this.solved) {
            this.solve();
        }

        return this.wireValues.get(wire);
    }

}
