package net.chewett.adventofcode2019.intcode;

import net.chewett.adventofcode2019.intcode.exceptions.UnsupportedIntcodeInstruction;
import net.chewett.adventofcode2019.intcode.instructions.IntcodeInstruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntcodeComputer {

    private Map<Integer, IntcodeInstruction> instructionSet = new HashMap<>();
    private IntcodeComputerMemory memory = new IntcodeComputerMemory();
    private int currentAddress = 0;

    public IntcodeComputer(List<IntcodeInstruction> instructions) {
        for(IntcodeInstruction i : instructions) {
            if(this.instructionSet.containsKey(i.getIntcodeInstructionNumber())) {
                throw new RuntimeException("Two instructions supplied mapping to the same net.chewett.adventofcode2019.intcode.Intcode Instruction");
            }

            this.instructionSet.put(i.getIntcodeInstructionNumber(), i);
        }
    }

    private void initMemory(Intcode intcode) {
        this.memory.loadIntcode(intcode);
    }

    public int runIntcode(Intcode intcode) {
        //Init the memory and set the current address to 0
        this.initMemory(intcode);
        this.currentAddress = 0;

        boolean finishedIntcode = false;
        while(!finishedIntcode) {
            IntcodeInstruction i = this.fetchInstructionAtCurrentAddress();
            finishedIntcode = i.performInstructionOnMemory(this.currentAddress, this.memory);
            this.currentAddress += i.getIntsConsumed();
        }

        //Final result is at address 0
        return this.memory.getIntAtAddress(0);
    }

    private IntcodeInstruction fetchInstructionAtCurrentAddress() {
        int intcodeInstructionId = this.memory.getIntAtAddress(this.currentAddress);

        if(!this.instructionSet.containsKey(intcodeInstructionId)) {
            throw new UnsupportedIntcodeInstruction();
        }

        return this.instructionSet.get(intcodeInstructionId);
    }

}
