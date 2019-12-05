package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

abstract public class IntcodeInstruction {

    public abstract int getIntsConsumed();

    public abstract int getIntcodeInstructionNumber();

    public abstract boolean performInstructionOnMemory(int currentAddress, IntcodeComputerMemory memory);

    public abstract  void configureMode(int modeSetting);


}
