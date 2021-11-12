package net.chewett.adventofcode.aoc2019.passwords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordFinderTest {

    @Test
    public void testValidNumber() {
        Assertions.assertTrue(PasswordFinder.isValidPassword(111111));
    }

    @Test
    public void testDecreasingNumber() {
        Assertions.assertFalse(PasswordFinder.isValidPassword(111110));
    }

    @Test
    public void testDecreasingNumberTwo() {
        Assertions.assertFalse(PasswordFinder.isValidPassword(223450));
    }

    @Test
    public void testNoDouble() {
        Assertions.assertFalse(PasswordFinder.isValidPassword(123789));
    }

    @Test
    public void testMoreComplexValidNumber() {
        Assertions.assertTrue(PasswordFinder.isValidPasswordMoreComplex(112233));
    }

    @Test
    public void testMoreTooManyDuplicates() {
        Assertions.assertFalse(PasswordFinder.isValidPasswordMoreComplex(123444));
    }

    @Test
    public void testMoreComplexValidNumberLotsDuplicated() {
        Assertions.assertTrue(PasswordFinder.isValidPasswordMoreComplex(111122));
    }

}
