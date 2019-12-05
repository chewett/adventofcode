package net.chewett.adventofcode2019.passwords;

import org.junit.Assert;
import org.junit.Test;

public class PasswordFinderTest {

    @Test
    public void testValidNumber() {
        Assert.assertTrue(PasswordFinder.isValidPassword(111111));
    }

    @Test
    public void testDecreasingNumber() {
        Assert.assertFalse(PasswordFinder.isValidPassword(111110));
    }

    @Test
    public void testDecreasingNumberTwo() {
        Assert.assertFalse(PasswordFinder.isValidPassword(223450));
    }

    @Test
    public void testNoDouble() {
        Assert.assertFalse(PasswordFinder.isValidPassword(123789));
    }

    @Test
    public void testMoreComplexValidNumber() {
        Assert.assertTrue(PasswordFinder.isValidPasswordMoreComplex(112233));
    }

    @Test
    public void testMoreTooManyDuplicates() {
        Assert.assertFalse(PasswordFinder.isValidPasswordMoreComplex(123444));
    }

    @Test
    public void testMoreComplexValidNumberLotsDuplicated() {
        Assert.assertTrue(PasswordFinder.isValidPasswordMoreComplex(111122));
    }

}
