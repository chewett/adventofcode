package net.chewett.adventofcode.aoc2019.intcode;

public class IntcodeComputerMemory {

    private long[] memory;

    public void loadIntcode(Intcode intcode) {
         this.memory = new long[100000];
         for(int i = 0; i < memory.length; i++) {
             this.memory[i] = 0;
         }

        long[] baseMemory = intcode.readIntoMemory();
        for(int i = 0; i < baseMemory.length; i++) {
            this.memory[i] = baseMemory[i];
        }
    }

    public long getIntAtAddress(int address) {
        return this.memory[address];
    }

    public void storeIntAtAddress(int address, long value) {
        this.memory[address] = value;
    }

    public void printMemory() {
        String mem = "";
        int memLoc = 0;
        for(long i : memory) {
            mem += memLoc + ":" + i + ",";
            memLoc++;
        }

        System.out.println(mem);
    }

}
