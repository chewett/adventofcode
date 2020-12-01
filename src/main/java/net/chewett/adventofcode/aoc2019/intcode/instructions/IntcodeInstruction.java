package net.chewett.adventofcode.aoc2019.intcode.instructions;

import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.IntcodeInstructionReturn;

abstract public class IntcodeInstruction {

    public abstract int getIntsConsumed();

    public abstract long getIntcodeInstructionNumber();

    public abstract IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory);

    public abstract  void configureMode(int modeSetting);

    public abstract String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory);

    public int getMemoryAddressToOperateOn(IntcodeComputer icc, IntcodeComputerMemory memory, int address, int operandMode) {
        int memoryAddress;
        if(operandMode == 0) {
            memoryAddress = (int)memory.getIntAtAddress(address);
        }else if (operandMode == 1) {
            throw new RuntimeException("This is not valid");
        }else if(operandMode == 2) {
            memoryAddress = (int)(icc.getRelativeBaseAddress() + memory.getIntAtAddress(address));
        }else{
            throw new RuntimeException("Unsupported parameter mode");
        }
        return memoryAddress;
    }


    public long getValueOfOperand(IntcodeComputer icc, IntcodeComputerMemory memory, int address, int operandMode) {
        long operandValue;
        if(operandMode == 0) {
            int locOfOperandB = (int)memory.getIntAtAddress(address);
            operandValue = memory.getIntAtAddress(locOfOperandB);
        }else if (operandMode == 1) {
            operandValue = memory.getIntAtAddress(address);
        }else if(operandMode == 2) {
            operandValue = memory.getIntAtAddress((int)(icc.getRelativeBaseAddress() + memory.getIntAtAddress(address)));
        }else{
            throw new RuntimeException("Unsupported parameter mode");
        }
        return operandValue;
    }

}
