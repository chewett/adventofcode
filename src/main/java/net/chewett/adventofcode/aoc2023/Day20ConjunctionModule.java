package net.chewett.adventofcode.aoc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The conjunction module is the most complex combining various inputs into an output
 * After creating we have to loop over the set of connectors to tell it how many inputs it has and what their labels are
 */
public class Day20ConjunctionModule extends Day20Connector {

    private Map<String, Boolean> inputMapping = new HashMap<>();

    public Day20ConjunctionModule(String name, String[] outputs) {
        super(name, outputs);
    }

    /**
     * This will be called once we have all the connectors nicely as objects and then we set up all its inputs
     * @param input String representing the input to this connector
     */
    public void setAsInput(String input) {
        this.inputMapping.put(input, false);
    }

    /**
     * Simple getter to get the inputs to this
     * @return List of inputs tot his connector
     */
    public List<String> getInputs() {
        List<String> inputs = new ArrayList<>();
        for(Map.Entry<String, Boolean> b : this.inputMapping.entrySet()) {
            inputs.add(b.getKey());
        }
        return inputs;
    }

    public Map<String, Boolean> getInputMapping() {
        return this.inputMapping;
    }

    public List<String[]> pulseHigh(Map<String, Day20Connector> connectors, String from) {
        this.inputMapping.put(from, true);
        return this.sendPulse();
    }

    public List<String[]> pulseLow(Map<String, Day20Connector> connectors, String from) {
        this.inputMapping.put(from, false);
        return this.sendPulse();
    }

    /**
     * This abstracts the send high/low pulse functions as both run this logic once we have set high/low for the
     * specific input
     * @return List of new generated pulses
     */
    public List<String[]> sendPulse() {
        boolean allHigh = true;
        for(Map.Entry<String, Boolean> b : this.inputMapping.entrySet()) {
            allHigh = allHigh && b.getValue();
        }

        List<String[]> newPulses = new ArrayList<>();
        for(String output : this.outputs) {
            //If they are all high we send a low, otherwise a high
            if(allHigh) {
                newPulses.add(new String[] {this.name, output, "low"});
            }else{
                newPulses.add(new String[] {this.name, output, "high"});
            }
        }

        return newPulses;
    }

    public String toString() {
        String onStateString = "";
        for(Map.Entry<String, Boolean> b : this.inputMapping.entrySet()) {
            onStateString += b.getKey() + "=";
            if(b.getValue()) {
                onStateString += "ON";
            }else{
                onStateString += "OFF";
            }
            onStateString += ", ";
        }

        return this.name + " Conj(" + onStateString +" )";
    }

}
