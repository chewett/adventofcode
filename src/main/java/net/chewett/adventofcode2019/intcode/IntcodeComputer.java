package net.chewett.adventofcode2019.intcode;

import net.chewett.adventofcode2019.intcode.exceptions.UnsupportedIntcodeInstruction;
import net.chewett.adventofcode2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode2019.intcode.instructions.IntcodeInstruction;

import java.util.*;

public class IntcodeComputer {

    private Map<Integer, IntcodeInstruction> instructionSet = new HashMap<>();
    private IntcodeComputerMemory memory = new IntcodeComputerMemory();
    private int currentAddress = 0;
    private boolean computationEntirelyFinished = false;
    private boolean computationAwaitingInput = false;
    private Queue<Integer> output = new LinkedList<>();
    private Queue<Integer> input = new LinkedList<>();

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

    public void initIntcode(Intcode intcode) {
        //Init the memory and set the current address to 0
        this.initMemory(intcode);
        this.currentAddress = 0;
    }

    public void runIntcode() {
        if(this.computationEntirelyFinished) {
            return;
        }
        if(this.computationAwaitingInput && this.hasInputToRead()) {
            this.computationAwaitingInput = false;
        }

        //this.memory.printMemory();
        while(!this.computationAwaitingInput && !this.computationEntirelyFinished) {
            IntcodeInstruction i = this.fetchInstructionAtCurrentAddress();

            //DEBUG
            //System.out.println("Running address:" + this.currentAddress + " " + i.getInstructionDetails(this, this.currentAddress, this.memory));
            IntcodeInstructionReturn iir = i.performInstructionOnMemory(this, this.currentAddress, this.memory);
            if(iir.isFinished()) {
                this.computationEntirelyFinished = true;
            }else if(iir.isAwaitingInput()) {
                this.computationAwaitingInput = true;
            }else if(iir.needsToMoveToNewAddress()) {
                this.currentAddress = iir.getNewAddressToMoveTo();
            }
        }
    }

    private IntcodeInstruction fetchInstructionAtCurrentAddress() {
        int instructionModeValue = this.memory.getIntAtAddress(this.currentAddress);
        int modeSettings = (int)Math.floor(instructionModeValue / 100.0);
        int intcodeInstructionId = instructionModeValue % 100;

        if(!this.instructionSet.containsKey(intcodeInstructionId)) {
            throw new UnsupportedIntcodeInstruction(intcodeInstructionId);
        }

        IntcodeInstruction i = this.instructionSet.get(intcodeInstructionId);
        i.configureMode(modeSettings);

        return i;
    }

    public void addOutputString(int i) {
        this.output.add(i);
    }

    public int getOutput() {
        return this.output.remove();
    }

    public boolean hasOutputToRead() {
        return this.output.size() > 0;
    }

    public void addToInput(int input) {
        this.input.add(input);
    }

    public boolean hasInputToRead() {
        return this.input.size() > 0;
    }

    public int getInput() {
        return this.input.remove();
    }

    public int getResultOfComputation() {
        return this.memory.getIntAtAddress(0);
    }

    public boolean isComputationEntirelyFinished() {
        return computationEntirelyFinished;
    }

    public boolean isComputationAwaitingInput() {
        return computationAwaitingInput;
    }
}
