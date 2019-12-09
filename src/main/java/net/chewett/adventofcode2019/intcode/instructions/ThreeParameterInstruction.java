package net.chewett.adventofcode2019.intcode.instructions;

abstract public class ThreeParameterInstruction extends IntcodeInstruction {

    protected int operandAMode = 0;
    protected int operandBMode = 0;
    protected int operandCMode = 0;

    @Override
    public void configureMode(int modeSetting) {
        this.operandAMode = modeSetting % 10;
        this.operandBMode = (modeSetting / 10) % 10;
        this.operandCMode = (modeSetting / 100);
    }

    @Override
    public String toString() {
        return super.toString() + " OpA:" + operandAMode + " OpB:" + operandBMode;
    }

    @Override
    public int getIntsConsumed() {
        return 4;
    }
}
