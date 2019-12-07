package net.chewett.adventofcode2019.intcode.instructionreturns;

public class IntcodeInstructionReturn {

    protected boolean awaitingInput = false;
    protected boolean finished = false;
    protected boolean needsToMoveToAddress = false;
    protected int newAddress = 0;


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


}
