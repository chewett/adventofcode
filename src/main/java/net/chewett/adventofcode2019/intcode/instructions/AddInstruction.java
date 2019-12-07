package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode2019.intcode.ParameterMode;
import net.chewett.adventofcode2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode2019.intcode.instructionreturns.MoveCurrentAddressPointerInstructionReturn;

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
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        int operandAValue = this.getValueOfOperand(memory, currentAddress + 1, this.operandAMode);
        int operandBValue = this.getValueOfOperand(memory, currentAddress + 2, this.operandBMode);

        int locToStoreResult = memory.getIntAtAddress(currentAddress + 3);
        memory.storeIntAtAddress(locToStoreResult, operandAValue + operandBValue);

        return new MoveCurrentAddressPointerInstructionReturn(currentAddress + this.getIntsConsumed());
    }

    @Override
    public String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return "AddInstruction(" +
                "OpA=" + ParameterMode.getModename(this.operandAMode) + ":" + memory.getIntAtAddress(currentAddress + 1) +
                ",OpB=" + ParameterMode.getModename(this.operandBMode) + ":" + memory.getIntAtAddress(currentAddress + 2) +
                ",SaveTo="+memory.getIntAtAddress(currentAddress + 3)+")";
    }
}
