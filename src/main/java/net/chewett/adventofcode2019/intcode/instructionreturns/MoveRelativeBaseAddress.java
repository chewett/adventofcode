package net.chewett.adventofcode2019.intcode.instructionreturns;

public class MoveRelativeBaseAddress extends IntcodeInstructionReturn {

    public MoveRelativeBaseAddress(int movementValue, int nextAddressToMoveTo) {
        this.needsToMoveRelativeBaseAddress = true;
        this.valueToMoveByRelativeAddress = movementValue;
        this.needsToMoveToAddress = true;
        this.newAddress = nextAddressToMoveTo;
    }

}
