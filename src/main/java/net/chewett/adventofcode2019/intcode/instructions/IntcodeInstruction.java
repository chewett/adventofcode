package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode2019.intcode.instructionreturns.IntcodeInstructionReturn;

abstract public class IntcodeInstruction {

    public abstract int getIntsConsumed();

    public abstract int getIntcodeInstructionNumber();

    public abstract IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory);

    public abstract  void configureMode(int modeSetting);

    public abstract String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory);

    public int getValueOfOperand(IntcodeComputerMemory memory, int address, int operandMode) {
        int operandValue;
        if(operandMode == 0) {
            int locOfOperandB = memory.getIntAtAddress(address);
            operandValue = memory.getIntAtAddress(locOfOperandB);
        }else if (operandMode == 1) {
            operandValue = memory.getIntAtAddress(address);
        }else{
            throw new RuntimeException("Unsupported parameter mode");
        }
        return operandValue;
    }

}
