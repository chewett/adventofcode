package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

public class FinishInstruction extends IntcodeInstruction {


    @Override
    public int getIntsConsumed() {
        return 1;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 99;
    }

    @Override
    public boolean performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return true;
    }

    @Override
    public void configureMode(int modeSetting) {
        //Do nothing
    }
}
