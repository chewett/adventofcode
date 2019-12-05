package net.chewett.adventofcode2019.intcode.instructions;

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
    public boolean performInstructionOnMemory(int currentAddress, IntcodeComputerMemory memory) {
        //FIXME: This just writes out the results, change this to have some way of outputting stuff.

        if(this.operandMode == 0) {
            System.out.println(memory.getIntAtAddress(memory.getIntAtAddress(currentAddress + 1)));
        }else{
            System.out.println(memory.getIntAtAddress(currentAddress + 1));
        }


        return false;
    }

}
