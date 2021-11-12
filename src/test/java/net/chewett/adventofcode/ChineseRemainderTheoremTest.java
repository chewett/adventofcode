package net.chewett.adventofcode;

import net.chewett.adventofcode.helpers.ChineseRemainderTheorem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChineseRemainderTheoremTest {

    @Test
    public void solveSimpleTest() {
        List<Integer> values = new ArrayList<>();
        values.add(17); values.add(13); values.add(19);
        List<Integer> remainders = new ArrayList<>();
        remainders.add(0); remainders.add(11); remainders.add(16);

        long solution = ChineseRemainderTheorem.solve(values, remainders);
        Assertions.assertEquals(3417, solution);
    }

    @Test
    public void solveSecondTest() {
        List<Integer> values = new ArrayList<>();
        values.add(3); values.add(5); values.add(7);
        List<Integer> remainders = new ArrayList<>();
        remainders.add(2); remainders.add(3); remainders.add(2);

        long solution = ChineseRemainderTheorem.solve(values, remainders);
        Assertions.assertEquals(23, solution);
    }



}
