package net.chewett.adventofcode2019.intcode;

public class IntcodeComputerMemory {

    private int[] memory;

    public void loadIntcode(Intcode intcode) {
        this.memory = intcode.readIntoMemory();
    }

    public int getIntAtAddress(int address) {
        return this.memory[address];
    }

    public void storeIntAtAddress(int address, int value) {
        this.memory[address] = value;
    }

    public void printMemory() {
        String mem = "";
        int memLoc = 0;
        for(int i : memory) {
            mem += memLoc + ":" + i + ",";
            memLoc++;
        }

        System.out.println(mem);
    }

}
