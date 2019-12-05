package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

public class WriteOutputInstruction extends OneParameterInstruction {

    @Override
    public int getIntsConsumed() {
        return 2;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 4;
    }

    @Override
    public boolean performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        //FIXME: This just writes out the results, change this to have some way of outputting stuff.

        int newOutput;
        if(this.operandMode == 0) {
            newOutput = memory.getIntAtAddress(memory.getIntAtAddress(currentAddress + 1));
        }else{
            newOutput = memory.getIntAtAddress(currentAddress + 1);
        }

        icc.addOutputString(newOutput);
        return false;
    }

}
