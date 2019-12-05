package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

public class WriteOutputInstruction extends IntcodeInstruction {

    @Override
    public int getIntsConsumed() {
        return 2;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 4;
    }

    @Override
    public boolean performInstructionOnMemory(int currentAddress, IntcodeComputerMemory memory) {
        //TODO: Do something

        return false;
    }

}
