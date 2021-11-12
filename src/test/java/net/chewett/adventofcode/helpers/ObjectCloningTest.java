package net.chewett.adventofcode.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ObjectCloningTest {

    @Test
    public void testCreateNewClonedList() {
        List<List<Character>> cll = new ArrayList<>();
        List<Character> listOne = new ArrayList<>();
        listOne.add('z');
        listOne.add('y');
        listOne.add('x');
        cll.add(listOne);

        List<List<Character>> newList = ObjectCloning.createNewClonedList(cll);
        newList.get(0).add(0, '1');
        newList.get(0).add(0, '2');
        newList.get(0).add(0, '3');

        Assertions.assertEquals('z', (char)cll.get(0).get(0));
        Assertions.assertEquals('y', (char)cll.get(0).get(1));
        Assertions.assertEquals('x', (char)cll.get(0).get(2));
    }

}
