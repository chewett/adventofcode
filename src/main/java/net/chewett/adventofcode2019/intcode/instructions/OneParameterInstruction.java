package net.chewett.adventofcode2019.intcode.instructions;

abstract public class OneParameterInstruction extends IntcodeInstruction {

    protected int operandMode = 0;

    @Override
    public void configureMode(int modeSetting) {
        this.operandMode = modeSetting;
    }

    @Override
    public int getIntsConsumed() {
        return 2;
    }

}
