package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

public class AddInstruction extends TwoParameterInstruction {

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
        int operandAValue;
        int operandBValue;

        if(this.operandAMode == 0) {
            int locOfOperandA = memory.getIntAtAddress(currentAddress + 1);
            operandAValue = memory.getIntAtAddress(locOfOperandA);
        }else if (this.operandAMode == 1) {
            operandAValue = memory.getIntAtAddress(currentAddress + 1);
        }else{
            throw new RuntimeException("Unsupported parameter mode");
    }

        if(this.operandBMode == 0) {
            int locOfOperandB = memory.getIntAtAddress(currentAddress + 2);
            operandBValue = memory.getIntAtAddress(locOfOperandB);
        }else if (this.operandBMode == 1) {
            operandBValue = memory.getIntAtAddress(currentAddress + 2);
        }else{
            throw new RuntimeException("Unsupported parameter mode");
        }

        int locToStoreResult = memory.getIntAtAddress(currentAddress + 3);
        memory.storeIntAtAddress(locToStoreResult, operandAValue + operandBValue);

        return false;
    }

}
