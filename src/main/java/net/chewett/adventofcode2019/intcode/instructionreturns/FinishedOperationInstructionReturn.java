package net.chewett.adventofcode2019.intcode.instructionreturns;

import net.chewett.adventofcode2019.intcode.instructions.IntcodeInstruction;

public class FinishedOperationInstructionReturn extends IntcodeInstructionReturn {

    public FinishedOperationInstructionReturn() {
        this.finished = true;
    }

}
