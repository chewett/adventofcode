package net.chewett.adventofcode.aoc2019.intcode.instructionreturns;

public class FinishedOperationInstructionReturn extends IntcodeInstructionReturn {

    public FinishedOperationInstructionReturn() {
        this.finished = true;
    }

}
