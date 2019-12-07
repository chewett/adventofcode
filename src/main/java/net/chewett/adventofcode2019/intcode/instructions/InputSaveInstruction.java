package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode2019.intcode.instructionreturns.MoveCurrentAddressPointerInstructionReturn;
import net.chewett.adventofcode2019.intcode.instructionreturns.NeedsInputInstructionReturn;

public class InputSaveInstruction extends IntcodeInstruction {

    @Override
    public int getIntsConsumed() {
        return 2;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 3;
    }

    @Override
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        if(!icc.hasInputToRead()) {
            return new NeedsInputInstructionReturn();
        }

        memory.storeIntAtAddress(memory.getIntAtAddress(currentAddress + 1), icc.getInput());

        return new MoveCurrentAddressPointerInstructionReturn(currentAddress + this.getIntsConsumed());
    }

    @Override
    public void configureMode(int modeSetting) {
        //Do nothing
    }

    @Override
    public String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return "InputSaveInstruction(saveToAddress="+memory.getIntAtAddress(currentAddress + 1)+")";
    }

}
