package net.chewett.adventofcode2019.intcode.instructionreturns;

public class MoveCurrentAddressPointerInstructionReturn extends IntcodeInstructionReturn {

    public MoveCurrentAddressPointerInstructionReturn(int newAddress) {
        this.newAddress = newAddress;
        this.needsToMoveToAddress = true;
    }

}
