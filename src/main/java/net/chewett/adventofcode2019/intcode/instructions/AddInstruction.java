package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode2019.intcode.ParameterMode;
import net.chewett.adventofcode2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode2019.intcode.instructionreturns.MoveCurrentAddressPointerInstructionReturn;

public class AddInstruction extends ThreeParameterInstruction {

    @Override
    public long getIntcodeInstructionNumber() {
        return 1;
    }

    @Override
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        long operandAValue = this.getValueOfOperand(icc, memory, currentAddress + 1, this.operandAMode);
        long operandBValue = this.getValueOfOperand(icc, memory, currentAddress + 2, this.operandBMode);

        int locToStoreResult = this.getMemoryAddressToOperateOn (icc, memory, currentAddress + 3, this.operandCMode);
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
