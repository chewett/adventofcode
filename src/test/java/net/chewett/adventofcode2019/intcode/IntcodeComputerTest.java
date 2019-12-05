package net.chewett.adventofcode2019.intcode;

import net.chewett.adventofcode2019.intcode.instructions.AddInstruction;
import net.chewett.adventofcode2019.intcode.instructions.FinishInstruction;
import net.chewett.adventofcode2019.intcode.instructions.IntcodeInstruction;
import net.chewett.adventofcode2019.intcode.instructions.MultiplyInstruction;
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

    @Test
    public void basicIntcodeAddTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        int finalResult = icc.runIntcode(new Intcode("1,0,0,0,99"));

        Assert.assertEquals(2, finalResult);
    }

    @Test
    public void basicIntcodeMultiplyTest() {
        IntcodeComputer icc = this.getBasicMultiplyComputer();

        int finalResult = icc.runIntcode(new Intcode("2,2,2,0,99"));

        Assert.assertEquals(4, finalResult);
    }

    @Test
    public void basicIntcodeAddParameterImmediateModeTest() {
        IntcodeComputer icc = this.getBasicAddComputer();

        int finalResult = icc.runIntcode(new Intcode("1101,5,10,0,99"));

        Assert.assertEquals(15, finalResult);
    }

    @Test
    public void basicIntcodeMultiplyParameterImmediateModeTest() {
        IntcodeComputer icc = this.getBasicMultiplyComputer();

        int finalResult = icc.runIntcode(new Intcode("1102,3,9,0,99"));

        Assert.assertEquals(27, finalResult);
    }



}
