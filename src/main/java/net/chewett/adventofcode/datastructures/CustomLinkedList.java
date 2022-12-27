package net.chewett.adventofcode.datastructures;

/**
 * Simple custom Linked List node implementation used for various simple Linked List stuff
 * @param <T> Any kind of data param to store
 */
public class CustomLinkedList<T> {

    private T val;

    private CustomLinkedList<T> prev;
    private CustomLinkedList<T> next;

    public CustomLinkedList(T valToSave) {
        this.val = valToSave;
    }

    public T getVal() {
        return val;
    }

    public CustomLinkedList<T> getPrev() {
        return prev;
    }

    public CustomLinkedList<T> getNext() {
        return next;
    }

    public void setPrev(CustomLinkedList<T> prev, boolean adjustNext) {
        this.prev = prev;
        if(adjustNext) {
            prev.setNext(this, false);
        }
    }

    public void setNext(CustomLinkedList<T> next, boolean adjustPrev) {
        this.next = next;
        if(adjustPrev) {
            next.setPrev(this, false);
        }
    }
}
