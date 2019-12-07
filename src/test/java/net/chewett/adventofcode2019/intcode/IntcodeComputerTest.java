package net.chewett.adventofcode2019.intcode;

import net.chewett.adventofcode2019.intcode.instructions.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IntcodeComputerTest {

    private IntcodeComputer getBasicAddComputer() {
        List<IntcodeInstruction> instructions = new ArrayList<>();
        instructions.add(new FinishInstruction());
        instructions.add(new AddInstruction());

        return new IntcodeComputer(instructions);
    }

    private IntcodeComputer getBasicMultiplyComputer() {
        List<IntcodeInstruction> instructions = new ArrayList<>();
        instructions.add(new FinishInstruction());
        instructions.add(new MultiplyInstruction());

        return new IntcodeComputer(instructions);
    }

    public IntcodeComputer getFullyFeaturedComputer() {
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

        return new IntcodeComputer(instructions);
    }

    @Test
    public void basicIntcodeAddTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        icc.initIntcode(new Intcode("1,0,0,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        int finalResult = icc.getResultOfComputation();

        Assert.assertEquals(2, finalResult);
    }

    @Test
    public void basicIntcodeMultiplyTest() {
        IntcodeComputer icc = this.getBasicMultiplyComputer();

        icc.initIntcode(new Intcode("2,2,2,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        int finalResult = icc.getResultOfComputation();

        Assert.assertEquals(4, finalResult);
    }

    @Test
    public void basicIntcodeAddParameterImmediateModeTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        icc.initIntcode(new Intcode("1101,5,10,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        int finalResult = icc.getResultOfComputation();

        Assert.assertEquals(15, finalResult);
    }

    @Test
    public void basicIntcodeMultiplyParameterImmediateModeTest() {
        IntcodeComputer icc = this.getBasicMultiplyComputer();

        icc.initIntcode(new Intcode("1102,3,9,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        int finalResult = icc.getResultOfComputation();

        Assert.assertEquals(27, finalResult);
    }

    @Test
    public void basicInstructionJumpingTest() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();
        Intcode ic = new Intcode("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99");
        icc.addToInput(1);
        icc.initIntcode(ic);
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());

        int output = icc.getOutput();
        Assert.assertEquals(999, output);
    }

    @Test
    public void basicInstructionJumpingTestTwo() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();
        Intcode ic = new Intcode("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99");
        icc.addToInput(8);
        icc.initIntcode(ic);
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());

        int output = icc.getOutput();
        Assert.assertEquals(1000, output);
    }

    @Test
    public void basicInstructionJumpingTestThree() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();
        Intcode ic = new Intcode("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99");
        icc.addToInput(10);
        icc.initIntcode(ic);
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());

        int output = icc.getOutput();
        Assert.assertEquals(1001, output);
    }



}
