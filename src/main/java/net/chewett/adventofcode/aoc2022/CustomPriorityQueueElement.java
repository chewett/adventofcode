package net.chewett.adventofcode.aoc2022;

/**
 * Custom priority queue element that holds two values and a comparison value which is used for comparing two elements
 * @param <T> First type of the value
 * @param <E> Second type of the value
 */
public class CustomPriorityQueueElement<T, E> implements Comparable<CustomPriorityQueueElement<T, E>> {

    private int comparisonVal;
    private T valueOne;

    private E valueTwo;


    public CustomPriorityQueueElement(int comparisonVal, T valueOne, E valueTwo) {
        this.comparisonVal = comparisonVal;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public int getComparisonVal() {
        return comparisonVal;
    }

    public T getValueOne() {
        return valueOne;
    }

    public E getValueTwo() {
        return valueTwo;
    }

    @Override
    public int compareTo(CustomPriorityQueueElement<T, E> o) {
        //Only use the single comparison value to compare between the two elements
        return Integer.compare(this.comparisonVal, o.getComparisonVal());
    }
}
