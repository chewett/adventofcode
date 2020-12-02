package net.chewett.adventofcode.aoc2019.intcode;

import net.chewett.adventofcode.aoc2019.intcode.instructions.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        instructions.add(new AdjustRelativeBaseInstruction());

        return new IntcodeComputer(instructions);
    }

    @Test
    public void basicIntcodeAddTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        icc.initIntcode(new Intcode("1,0,0,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getResultOfComputation();

        Assert.assertEquals(2, finalResult);
    }

    @Test
    public void basicIntcodeMultiplyTest() {
        IntcodeComputer icc = this.getBasicMultiplyComputer();

        icc.initIntcode(new Intcode("2,2,2,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getResultOfComputation();

        Assert.assertEquals(4, finalResult);
    }

    @Test
    public void basicIntcodeAddParameterImmediateModeTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        icc.initIntcode(new Intcode("1101,5,10,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getResultOfComputation();

        Assert.assertEquals(15, finalResult);
    }

    @Test
    public void basicIntcodeMultiplyParameterImmediateModeTest() {
        IntcodeComputer icc = this.getBasicMultiplyComputer();

        icc.initIntcode(new Intcode("1102,3,9,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getResultOfComputation();

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

        long output = icc.getOutput();
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

        long output = icc.getOutput();
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

        long output = icc.getOutput();
        Assert.assertEquals(1001, output);
    }

    @Test
    public void testLargeMemorySpace() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();
        Intcode ic = new Intcode("99");
        icc.initIntcode(ic);
        icc.runIntcode();

        long expectedZero = icc.readMemoryAddress(10000);
        Assert.assertEquals(0, expectedZero);
    }

    @Test
    public void basicIntcodeAddParameterRelativeBaseModeTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        icc.initIntcode(new Intcode("2201,1,2,0,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getResultOfComputation();

        Assert.assertEquals(3, finalResult);
    }

    @Test
    public void testLargeNumberSupport() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();

        icc.initIntcode(new Intcode("1102,34915192,34915192,7,4,7,99,0"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getOutput();

        Assert.assertEquals(1219070632396864L, finalResult);
    }

    @Test
    public void testOutputLargeNumber() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();

        icc.initIntcode(new Intcode("104,1125899906842624,99"));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getOutput();

        Assert.assertEquals(1125899906842624L, finalResult);
    }

    @Test
    public void testRelativeOutputInstruction() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();

        icc.initIntcode(new Intcode("109,5,203,-5,99"));
        icc.addToInput(12);
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());
        long finalResult = icc.getResultOfComputation();

        Assert.assertEquals(12, (int)finalResult);
    }

    @Test
    public void testRelativeOutputInstructionTwo() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();

        icc.initIntcode(new Intcode("109,7,203,-2,99,0"));
        icc.addToInput(12);
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());

        Assert.assertEquals(12, icc.readMemoryAddress(5));
    }

    @Test
    public void testOutputProgram() {
        IntcodeComputer icc = this.getFullyFeaturedComputer();
        String program = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";

        icc.initIntcode(new Intcode(program));
        icc.runIntcode();
        Assert.assertTrue(icc.isComputationEntirelyFinished());

        List<String> programOutput = new ArrayList<>();
        while(icc.hasOutputToRead()) {
            programOutput.add(String.valueOf(icc.getOutput()));
        }

        Assert.assertEquals(program, String.join(",", programOutput));

    }


    @Test
    public void testDay9BoostProgramPartOne() throws Exception {
        File file = new File("2019_day_9_input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        //Day seven input is a single line, so just load that
        String boostProgram = br.readLine();
        IntcodeComputer icc = this.getFullyFeaturedComputer();

        Intcode ic = new Intcode(boostProgram);
        icc.initIntcode(ic);
        icc.addToInput(1);
        icc.runIntcode();

        Assert.assertEquals(4006117640L, icc.getOutput());
    }

    @Test
    public void testDay9BoostProgramPartTwo() throws Exception {
        File file = new File("2019_day_9_input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        //Day seven input is a single line, so just load that
        String boostProgram = br.readLine();
        IntcodeComputer icc = this.getFullyFeaturedComputer();

        Intcode ic = new Intcode(boostProgram);
        icc.initIntcode(ic);
        icc.addToInput(2);
        icc.runIntcode();

        Assert.assertEquals(88231, icc.getOutput());
    }


}
