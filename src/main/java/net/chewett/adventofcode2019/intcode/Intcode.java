package net.chewett.adventofcode2019.intcode;

public class Intcode {

    private int[] intsFromIntcode;

    public Intcode(String intcode) {
        String[] intcodeParts = intcode.split(",");
        this.intsFromIntcode = new int[intcodeParts.length];

        for(int i = 0; i < intcodeParts.length; i++) {
            this.intsFromIntcode[i] = Integer.parseInt(intcodeParts[i]);
        }
    }

    public Intcode(int[] intcode) {
        this.intsFromIntcode = intcode;
    }

    public int[] readIntoMemory() {
        return this.intsFromIntcode.clone();
    }

    public void setIntToAddress(int address, int value) {
        this.intsFromIntcode[address] = value;
    }

}
