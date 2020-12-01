package net.chewett.adventofcode.aoc2019.intcode.instructions;

import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.MoveCurrentAddressPointerInstructionReturn;

public class WriteOutputInstruction extends OneParameterInstruction {

    @Override
    public long getIntcodeInstructionNumber() {
        return 4;
    }

    @Override
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        long newOutput = this.getValueOfOperand(icc, memory, currentAddress + 1, this.operandMode);
        icc.addOutputString(newOutput);
        return new MoveCurrentAddressPointerInstructionReturn(currentAddress + this.getIntsConsumed());
    }

    @Override
    public String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return "WriteOutputInstruction(addressToOutput="+memory.getIntAtAddress(currentAddress + 1)+")";
    }

}
