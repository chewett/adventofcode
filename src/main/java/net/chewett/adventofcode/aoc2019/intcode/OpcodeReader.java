package net.chewett.adventofcode.aoc2019.intcode;

import java.util.List;

public class OpcodeReader {

    private int currentPos = 0;
    private List<Integer> opcodes;

    public OpcodeReader(List<Integer> opcodes) {
        this.opcodes = opcodes;
    }

    public int readValueAtPos(int pos) {
        return this.opcodes.get(pos);
    }

    public int process() {
        boolean currentlyRunning = true;
        while(currentlyRunning) {
            currentlyRunning = this.handleCurrentPos();
        }

        return this.opcodes.get(0);
    }

    private boolean handleCurrentPos() {
        int curInstruction = this.opcodes.get(this.currentPos);


        if(curInstruction == 1) {
            int operandA = this.opcodes.get(this.opcodes.get(this.currentPos + 1));
            int operandB = this.opcodes.get(this.opcodes.get(this.currentPos + 2));
            int outputLoc = this.opcodes.get(this.currentPos + 3);
            this.opcodes.set(outputLoc, operandA + operandB);
        }else if(curInstruction == 2) {
            int operandA = this.opcodes.get(this.opcodes.get(this.currentPos + 1));
            int operandB = this.opcodes.get(this.opcodes.get(this.currentPos + 2));
            int outputLoc = this.opcodes.get(this.currentPos + 3);
            this.opcodes.set(outputLoc, operandA * operandB);

        }else if(curInstruction == 99) {
            return false;
        }else{
            throw new RuntimeException("Invalid opcode");
        }
        this.currentPos += 4;
        return true;
    }

}
