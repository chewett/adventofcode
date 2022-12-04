package net.chewett.adventofcode.datastructures;

/**
 * Add in a very simple Pair function which accepts two objects of type T called a and b
 * These are both public so no getters/setters are used, just so hold two similar values together
 *
 * This is slightly easier than an array of length two, or a list of a similar length
 * @param <T>
 */
public class Pair<T> {

    public T a;
    public T b;

    public Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

}
