package net.chewett.adventofcode.aoc2019.intcode.instructions;

import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode.aoc2019.intcode.ParameterMode;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.MoveRelativeBaseAddress;

public class AdjustRelativeBaseInstruction extends OneParameterInstruction {

    @Override
    public long getIntcodeInstructionNumber() {
        return 9;
    }

    @Override
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return new MoveRelativeBaseAddress((int)this.getValueOfOperand(icc, memory, currentAddress + 1, this.operandMode), currentAddress + this.getIntsConsumed());
    }

    @Override
    public String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return "AdjustRelativeBaseInstruction("+memory.getIntAtAddress(currentAddress)+"addressToOutput=" + ParameterMode.getModename(this.operandMode) +":"+this.getValueOfOperand(icc, memory, currentAddress + 1, this.operandMode)+")";
    }

}
