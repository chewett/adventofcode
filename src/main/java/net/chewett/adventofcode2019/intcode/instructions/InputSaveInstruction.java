package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

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
    public boolean performInstructionOnMemory(int currentAddress, IntcodeComputerMemory memory) {
        //FIXME: This hardcodes it to save 1 for now as the "air conditioner". Change this later.
        memory.storeIntAtAddress(memory.getIntAtAddress(currentAddress + 1), 1);

        return false;
    }

    @Override
    public void configureMode(int modeSetting) {
        //Do nothing
    }

}
