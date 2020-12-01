package net.chewett.adventofcode.aoc2019.intcode;

public class Intcode {

    private long[] intsFromIntcode;

    public Intcode(String intcode) {
        String[] intcodeParts = intcode.split(",");
        this.intsFromIntcode = new long[intcodeParts.length];

        for(int i = 0; i < intcodeParts.length; i++) {
            this.intsFromIntcode[i] = Long.parseLong(intcodeParts[i]);
        }
    }

    public Intcode(long[] intcode) {
        this.intsFromIntcode = intcode;
    }

    public long[] readIntoMemory() {
        return this.intsFromIntcode.clone();
    }

    public void setIntToAddress(int address, long value) {
        this.intsFromIntcode[address] = value;
    }

}
