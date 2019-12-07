package net.chewett.adventofcode2019.intcode.instructions;

import net.chewett.adventofcode2019.intcode.IntcodeComputer;
import net.chewett.adventofcode2019.intcode.IntcodeComputerMemory;
import net.chewett.adventofcode2019.intcode.ParameterMode;
import net.chewett.adventofcode2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode2019.intcode.instructionreturns.MoveCurrentAddressPointerInstructionReturn;

public class JumpIfFalseInstruction extends TwoParameterInstruction {

    @Override
    public int getIntsConsumed() {
        return 3;
    }

    @Override
    public int getIntcodeInstructionNumber() {
        return 6;
    }

    @Override
    public IntcodeInstructionReturn performInstructionOnMemory(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        int valueToCheck;
        if(this.operandAMode == 0) {
            valueToCheck = memory.getIntAtAddress(memory.getIntAtAddress(currentAddress + 1));
        }else{
            valueToCheck = memory.getIntAtAddress(currentAddress + 1);
        }

        if(valueToCheck == 0) {
            int newPointerAddress;
            if(this.operandBMode == 0) {
                newPointerAddress = memory.getIntAtAddress(memory.getIntAtAddress(currentAddress + 2));
            }else{
                newPointerAddress = memory.getIntAtAddress(currentAddress + 2);
            }

            return new MoveCurrentAddressPointerInstructionReturn(newPointerAddress);
        }

        return new MoveCurrentAddressPointerInstructionReturn(currentAddress + this.getIntsConsumed());
    }

    @Override
    public String getInstructionDetails(IntcodeComputer icc, int currentAddress, IntcodeComputerMemory memory) {
        return "JumpIfFalseInstruction(" +
                "ToCheck=" + ParameterMode.getModename(this.operandAMode) + ":" + memory.getIntAtAddress(currentAddress + 1) +
                "ToJumpTo=" + ParameterMode.getModename(this.operandBMode) + ":" + memory.getIntAtAddress(currentAddress + 2) +
                ")";
    }

}
