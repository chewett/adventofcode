package net.chewett.adventofcode.aoc2019.intcode;

import net.chewett.adventofcode.aoc2019.intcode.exceptions.UnsupportedIntcodeInstruction;
import net.chewett.adventofcode.aoc2019.intcode.instructionreturns.IntcodeInstructionReturn;
import net.chewett.adventofcode.aoc2019.intcode.instructions.*;

import java.util.*;

public class IntcodeComputer {

    private Map<Long, IntcodeInstruction> instructionSet = new HashMap<>();
    private IntcodeComputerMemory memory = new IntcodeComputerMemory();
    private int currentAddress = 0;
    private int relativeBaseAddress = 0;
    private boolean computationEntirelyFinished = false;
    private boolean computationAwaitingInput = false;
    private Queue<Long> output = new LinkedList<>();
    private Queue<Long> input = new LinkedList<>();

    /**
     * Helper function to get a fully configured Intcode computer
     * @return Fully configured Intcode computer
     */
    public static IntcodeComputer getFullyConfiguredComputer() {
        List<IntcodeInstruction> instructions = new ArrayList<>();
        instructions.add(new FinishInstruction());
        instructions.add(new AddInstruction());
        instructions.add(new MultiplyInstruction());
        instructions.add(new InputSaveInstruction());
        instructions.add(new WriteOutputInstruction());
        instructions.add(new JumpIfTrueInstruction());
        instructions.add(new JumpIfFalseInstruction());
        instructions.add(new LessThanInstruction());
        instructions.add(new EqualsInstruction());
        instructions.add(new AdjustRelativeBaseInstruction());

        return new IntcodeComputer(instructions);
    }

    public IntcodeComputer(List<IntcodeInstruction> instructions) {
        for(IntcodeInstruction i : instructions) {
            if(this.instructionSet.containsKey(i.getIntcodeInstructionNumber())) {
                throw new RuntimeException("Two instructions supplied mapping to the same net.chewett.adventofcode.aoc2019.intcode.Intcode Instruction");
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
        this.relativeBaseAddress = 0;
        this.computationEntirelyFinished = false;
        this.computationAwaitingInput = false;
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
            //System.out.println("CurAddress:" + this.currentAddress + " RelAddress:" + this.relativeBaseAddress + " "  + i.getInstructionDetails(this, this.currentAddress, this.memory));
            IntcodeInstructionReturn iir = i.performInstructionOnMemory(this, this.currentAddress, this.memory);
            if(iir.isFinished()) {
                this.computationEntirelyFinished = true;
            }
            if(iir.isAwaitingInput()) {
                this.computationAwaitingInput = true;
            }
            if(iir.needsToMoveToNewAddress()) {
                this.currentAddress = iir.getNewAddressToMoveTo();
            }
            if(iir.needsToMoveRelativeBaseAddress()) {
                //System.out.println("MOVING ADDRESS BY: " + iir.getValueToMoveByRelativeAddress());
                this.relativeBaseAddress += iir.getValueToMoveByRelativeAddress();
            }
        }
    }

    private IntcodeInstruction fetchInstructionAtCurrentAddress() {
        long instructionModeValue = this.memory.getIntAtAddress(this.currentAddress);
        int modeSettings = (int)Math.floor(instructionModeValue / 100.0);
        long intcodeInstructionId = instructionModeValue % 100;

        if(!this.instructionSet.containsKey(intcodeInstructionId)) {
            throw new UnsupportedIntcodeInstruction(intcodeInstructionId);
        }

        IntcodeInstruction i = this.instructionSet.get(intcodeInstructionId);
        i.configureMode(modeSettings);

        return i;
    }

    public void addOutputString(long i) {
        this.output.add(i);
    }

    public long getOutput() {
        return this.output.remove();
    }

    public boolean hasOutputToRead() {
        return this.output.size() > 0;
    }

    public void addToInput(long input) {
        this.input.add(input);
    }

    public void addAllToInput(String input) {
        for(char c : input.toCharArray()) {
            this.addToInput((int)c);
        }
    }

    public boolean hasInputToRead() {
        return this.input.size() > 0;
    }

    public long getInput() {
        return this.input.remove();
    }

    public long readMemoryAddress(int address) {
        return this.memory.getIntAtAddress(address);
    }

    public long getResultOfComputation() {
        return this.memory.getIntAtAddress(0);
    }

    public boolean isComputationEntirelyFinished() {
        return computationEntirelyFinished;
    }

    public int getRelativeBaseAddress() {
        return this.relativeBaseAddress;
    }

    public boolean isComputationAwaitingInput() {
        return computationAwaitingInput;
    }
}
