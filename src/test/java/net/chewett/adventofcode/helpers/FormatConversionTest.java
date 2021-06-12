package net.chewett.adventofcode.helpers;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FormatConversionTest {

    @Test
    public void testRotateCharListListAnticlockwise() {
        List<List<Character>> testList = new ArrayList<>();
        List<Character> topRow = new ArrayList<>();
        topRow.add('1');topRow.add('2');topRow.add('3');
        testList.add(topRow);

        List<Character> middleRow = new ArrayList<>();
        middleRow.add('4');middleRow.add('5');middleRow.add('6');
        testList.add(middleRow);

        List<Character> bottomRow = new ArrayList<>();
        bottomRow.add('7');bottomRow.add('8');bottomRow.add('9');
        testList.add(bottomRow);

        List<List<Character>> rotated = FormatConversion.rotateCharListListAnticlockwise(testList);

        Assert.assertEquals('1', (char)rotated.get(2).get(0));
        Assert.assertEquals('2', (char)rotated.get(1).get(0));
        Assert.assertEquals('3', (char)rotated.get(0).get(0));

        Assert.assertEquals('4', (char)rotated.get(2).get(1));
        Assert.assertEquals('5', (char)rotated.get(1).get(1));
        Assert.assertEquals('6', (char)rotated.get(0).get(1));

        Assert.assertEquals('7', (char)rotated.get(2).get(2));
        Assert.assertEquals('8', (char)rotated.get(1).get(2));
        Assert.assertEquals('9', (char)rotated.get(0).get(2));

    }

    /**
     * Simple test to make sure that the converting from a List of strings to a List of List of chars works as expected
     */
    @Test
    public void testConvertStringListToCharListList() {
        List<String> strings = new ArrayList<>();
        strings.add("abcdef");
        strings.add("ghijklm");
        strings.add("1234");
        strings.add("5678910");

        List<List<Character>> chars = FormatConversion.convertStringArrayToCharListList(strings);
        Assert.assertEquals('a', (char)chars.get(0).get(0));
        Assert.assertEquals('b', (char)chars.get(0).get(1));
        Assert.assertEquals('c', (char)chars.get(0).get(2));
        Assert.assertEquals('d', (char)chars.get(0).get(3));
        Assert.assertEquals('e', (char)chars.get(0).get(4));
        Assert.assertEquals('f', (char)chars.get(0).get(5));

        Assert.assertEquals('g', (char)chars.get(1).get(0));
        Assert.assertEquals('h', (char)chars.get(1).get(1));
        Assert.assertEquals('i', (char)chars.get(1).get(2));
        Assert.assertEquals('j', (char)chars.get(1).get(3));
        Assert.assertEquals('k', (char)chars.get(1).get(4));
        Assert.assertEquals('l', (char)chars.get(1).get(5));
        Assert.assertEquals('m', (char)chars.get(1).get(6));

        Assert.assertEquals('1', (char)chars.get(2).get(0));
        Assert.assertEquals('2', (char)chars.get(2).get(1));
        Assert.assertEquals('3', (char)chars.get(2).get(2));
        Assert.assertEquals('4', (char)chars.get(2).get(3));

        Assert.assertEquals('5', (char)chars.get(3).get(0));
        Assert.assertEquals('6', (char)chars.get(3).get(1));
        Assert.assertEquals('7', (char)chars.get(3).get(2));
        Assert.assertEquals('8', (char)chars.get(3).get(3));
        Assert.assertEquals('9', (char)chars.get(3).get(4));
        Assert.assertEquals('1', (char)chars.get(3).get(5));
        Assert.assertEquals('0', (char)chars.get(3).get(6));
    }

    /**
     * Simple test to make sure that the converting from a List of strings to a List of List of chars works as expected
     */
    @Test
    public void testConvertStringArrayToCharListList() {
        String[] strings = new String[]{"abcdef", "ghijklm", "1234", "5678910"};

        List<List<Character>> chars = FormatConversion.convertStringArrayToCharListList(strings);
        Assert.assertEquals('a', (char)chars.get(0).get(0));
        Assert.assertEquals('b', (char)chars.get(0).get(1));
        Assert.assertEquals('c', (char)chars.get(0).get(2));
        Assert.assertEquals('d', (char)chars.get(0).get(3));
        Assert.assertEquals('e', (char)chars.get(0).get(4));
        Assert.assertEquals('f', (char)chars.get(0).get(5));

        Assert.assertEquals('g', (char)chars.get(1).get(0));
        Assert.assertEquals('h', (char)chars.get(1).get(1));
        Assert.assertEquals('i', (char)chars.get(1).get(2));
        Assert.assertEquals('j', (char)chars.get(1).get(3));
        Assert.assertEquals('k', (char)chars.get(1).get(4));
        Assert.assertEquals('l', (char)chars.get(1).get(5));
        Assert.assertEquals('m', (char)chars.get(1).get(6));

        Assert.assertEquals('1', (char)chars.get(2).get(0));
        Assert.assertEquals('2', (char)chars.get(2).get(1));
        Assert.assertEquals('3', (char)chars.get(2).get(2));
        Assert.assertEquals('4', (char)chars.get(2).get(3));

        Assert.assertEquals('5', (char)chars.get(3).get(0));
        Assert.assertEquals('6', (char)chars.get(3).get(1));
        Assert.assertEquals('7', (char)chars.get(3).get(2));
        Assert.assertEquals('8', (char)chars.get(3).get(3));
        Assert.assertEquals('9', (char)chars.get(3).get(4));
        Assert.assertEquals('1', (char)chars.get(3).get(5));
        Assert.assertEquals('0', (char)chars.get(3).get(6));
    }

    /**
     * Tests to make sure that I can convert between a Char list list to a String List
     */
    @Test
    public void testConvertCharListListToStringList() {
        List<List<Character>> cll = new ArrayList<>();
        List<Character> listOne = new ArrayList<>();
        listOne.add('z');
        listOne.add('y');
        listOne.add('x');
        List<Character> listTwo = new ArrayList<>();
        listTwo.add('o');
        listTwo.add('n');
        listTwo.add('e');
        listTwo.add('t');
        listTwo.add('w');
        listTwo.add('o');
        List<Character> listThree = new ArrayList<>();
        listThree.add('1');
        listThree.add('2');
        listThree.add('3');
        listThree.add('4');
        listThree.add('5');
        listThree.add('6');
        cll.add(listOne);
        cll.add(listTwo);
        cll.add(listThree);

        List<String> newList = FormatConversion.convertCharListListToStringList(cll);

        Assert.assertEquals("zyx", newList.get(0));
        Assert.assertEquals("onetwo", newList.get(1));
        Assert.assertEquals("123456", newList.get(2));
    }

    @Test
    public void testFlipCharListList() {
        List<List<Character>> cll = new ArrayList<>();
        List<Character> listOne = new ArrayList<>();
        listOne.add('z');
        listOne.add('y');
        listOne.add('x');
        List<Character> listTwo = new ArrayList<>();
        listTwo.add('o');
        listTwo.add('n');
        listTwo.add('e');
        listTwo.add('t');
        listTwo.add('w');
        listTwo.add('o');
        List<Character> listThree = new ArrayList<>();
        listThree.add('1');
        listThree.add('2');
        listThree.add('3');
        listThree.add('4');
        listThree.add('5');
        listThree.add('6');
        cll.add(listOne);
        cll.add(listTwo);
        cll.add(listThree);

        List<List<Character>> flipCharListList = FormatConversion.flipCharListList(cll);

        Assert.assertEquals('x', (char)flipCharListList.get(0).get(0));
        Assert.assertEquals('y', (char)flipCharListList.get(0).get(1));
        Assert.assertEquals('z', (char)flipCharListList.get(0).get(2));

        Assert.assertEquals('o', (char)flipCharListList.get(1).get(0));
        Assert.assertEquals('w', (char)flipCharListList.get(1).get(1));
        Assert.assertEquals('t', (char)flipCharListList.get(1).get(2));
        Assert.assertEquals('e', (char)flipCharListList.get(1).get(3));
        Assert.assertEquals('n', (char)flipCharListList.get(1).get(4));
        Assert.assertEquals('o', (char)flipCharListList.get(1).get(5));

        Assert.assertEquals('6', (char)flipCharListList.get(2).get(0));
        Assert.assertEquals('5', (char)flipCharListList.get(2).get(1));
        Assert.assertEquals('4', (char)flipCharListList.get(2).get(2));
        Assert.assertEquals('3', (char)flipCharListList.get(2).get(3));
        Assert.assertEquals('2', (char)flipCharListList.get(2).get(4));
        Assert.assertEquals('1', (char)flipCharListList.get(2).get(5));
    }


}
