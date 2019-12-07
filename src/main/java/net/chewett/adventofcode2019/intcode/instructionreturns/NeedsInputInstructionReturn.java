package net.chewett.adventofcode2019.intcode.instructionreturns;

public class NeedsInputInstructionReturn extends IntcodeInstructionReturn {

    public NeedsInputInstructionReturn() {
        this.awaitingInput = true;
    }

}
