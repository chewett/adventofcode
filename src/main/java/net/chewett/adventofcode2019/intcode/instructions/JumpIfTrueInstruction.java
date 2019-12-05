package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;

public class JumpIfTrueInstruction extends TwoParameterInstruction {

    private int tmpOverrideIntsConsumed = 0;

    @Override
    public int getIntsConsumed() {
        int overrideValue = this.tmpOverrideIntsConsumed;
        this.tmpOverrideIntsConsumed = 0;

        return 3 + overrideValue;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 5;
    }

    @Override
    public boolean performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        //FIXME: This just writes out the results, change this to have some way of outputting stuff.

        int valueToCheck;
        if(this.operandAMode == 0) {
            valueToCheck = memory.getIntAtAddress(memory.getIntAtAddress(currentAddress + 1));
        }else{
            valueToCheck = memory.getIntAtAddress(currentAddress + 1);
        }

        if(valueToCheck != 0) {
            int newPointerAddress;
            if(this.operandBMode == 0) {
                newPointerAddress = memory.getIntAtAddress(memory.getIntAtAddress(currentAddress + 2));
            }else{
                newPointerAddress = memory.getIntAtAddress(currentAddress + 2);
            }

            this.tmpOverrideIntsConsumed = newPointerAddress - 3 - currentAddress;
        }

        return false;
    }

}
