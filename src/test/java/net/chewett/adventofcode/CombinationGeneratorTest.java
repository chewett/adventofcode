package net.chewett.adventofcode;

import net.chewett.adventofcode.helpers.CombinationGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CombinationGeneratorTest {

    private void confirmExpectedItemsAreInReturnedValue(List<List<Integer>> returnedItems, String expectedValues) {
        String[] expectedValuePieces = expectedValues.split(";");
        Set<String> expectedValuesSet = new HashSet<>(Arrays.asList(expectedValuePieces));

        Set<String> realValuesSet = new HashSet<>();
        for(List<Integer> returnedIntLists : returnedItems) {
            Collections.sort(returnedIntLists);
            List<String> returnedStringLists = new ArrayList<>();
            for(int i : returnedIntLists) {
                returnedStringLists.add(""+ i);
            }

            String tmpString = String.join(",", returnedStringLists);
            realValuesSet.add(tmpString);
        }

        Assertions.assertEquals(expectedValuesSet, realValuesSet);
    }

    @Test
    public void testNoElements() {
        List<Integer> noElementList = new ArrayList<>();

        List<List<Integer>> generatedList = CombinationGenerator.createCombinationsWhereEachElementCanOrCannotExist(noElementList);
        Assertions.assertEquals(1, generatedList.size());
        this.confirmExpectedItemsAreInReturnedValue(generatedList, "");
    }

    @Test
    public void testOneElement() {
        List<Integer> oneElementList = new ArrayList<>();
        oneElementList.add(12);

        List<List<Integer>> generatedList = CombinationGenerator.createCombinationsWhereEachElementCanOrCannotExist(oneElementList);
        Assertions.assertEquals(2, generatedList.size());
        this.confirmExpectedItemsAreInReturnedValue(generatedList, ";12");
    }

    @Test
    public void testTwoElements() {
        List<Integer> twoElementList = new ArrayList<>();
        twoElementList.add(13); twoElementList.add(15);

        List<List<Integer>> generatedList = CombinationGenerator.createCombinationsWhereEachElementCanOrCannotExist(twoElementList);
        Assertions.assertEquals(4, generatedList.size());
        this.confirmExpectedItemsAreInReturnedValue(generatedList, ";13;15;13,15");
    }

    @Test
    public void testThreeElements() {
        List<Integer> threeElementsList = new ArrayList<>();
        threeElementsList.add(99); threeElementsList.add(95); threeElementsList.add(90);

        List<List<Integer>> generatedList = CombinationGenerator.createCombinationsWhereEachElementCanOrCannotExist(threeElementsList);
        Assertions.assertEquals(8, generatedList.size());
        this.confirmExpectedItemsAreInReturnedValue(generatedList, ";99;95;90;95,99;90,95;90,99;90,95,99");
    }

}
