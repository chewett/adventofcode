package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

public class AddInstruction extends IntcodeInstruction {

    @Override
    public int getIntsConsumed() {
        return 4;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 1;
    }

    @Override
    public boolean performInstructionOnMemory(int currentAddress, IntcodeComputerMemory memory) {
        int locOfOperandA = memory.getIntAtAddress(currentAddress + 1);
        int locOfOperandB = memory.getIntAtAddress(currentAddress + 2);
        int locToStoreResult = memory.getIntAtAddress(currentAddress + 3);

        int operandAValue = memory.getIntAtAddress(locOfOperandA);
        int operandBValue = memory.getIntAtAddress(locOfOperandB);

        memory.storeIntAtAddress(locToStoreResult, operandAValue + operandBValue);

        return false;
    }

}
