package net.chewett.adventofcode.aoc2019.intcode.instructionreturns;

public class IntcodeInstructionReturn {

    protected boolean awaitingInput = false;
    protected boolean finished = false;
    protected boolean needsToMoveToAddress = false;
    protected int newAddress = 0;
    protected boolean needsToMoveRelativeBaseAddress = false;
    protected int valueToMoveByRelativeAddress = 0;


    public boolean isAwaitingInput() {
        return this.awaitingInput;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean needsToMoveToNewAddress() {
        return this.needsToMoveToAddress;
    }

    public int getNewAddressToMoveTo() {
        return this.newAddress;
    }

    public boolean needsToMoveRelativeBaseAddress() {
        return this.needsToMoveRelativeBaseAddress;
    }

    public int getValueToMoveByRelativeAddress() {
        return this.valueToMoveByRelativeAddress;
    }
}
