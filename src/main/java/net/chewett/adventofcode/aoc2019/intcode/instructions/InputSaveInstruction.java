package net.chewett.adventofcode.aoc2019.intcode.instructions;

import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputer;
import net.chewett.adventofcode.aoc2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.MoveCurrentAddressPointerInstructionReturn;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.NeedsInputInstructionReturn;

public class InputSaveInstruction extends OneParameterInstruction {

    @Override
    public long getIntcodeInstructionNumber() {
        return 3;
    }

    @Override
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        if(!icc.hasInputToRead()) {
            return new NeedsInputInstructionReturn();
        }

        memory.storeIntAtAddress(this.getMemoryAddressToOperateOn(icc, memory, currentAddress + 1, this.operandMode), icc.getInput());

        return new MoveCurrentAddressPointerInstructionReturn(currentAddress + this.getIntsConsumed());
    }

    @Override
    public String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return "InputSaveInstruction(saveToAddress="+memory.getIntAtAddress(currentAddress + 1)+")";
    }

}
