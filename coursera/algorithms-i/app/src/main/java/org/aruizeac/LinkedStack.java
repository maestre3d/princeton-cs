package org.aruizeac;

public class LinkedStack<T> implements Stack<T> {
    private LinkedNode<T> headNode;
    private int count;

    public LinkedStack() {
    }

    public void push(T val) {
        count++;
        LinkedNode<T> tmp = headNode;
        headNode = new LinkedNode<>(val);
        headNode.setNext(tmp);
    }

    public T pop() {
        if (headNode == null) return null;
        if (count > 0) count--;
        T res = headNode.getVal();
        headNode = headNode.getNext();
        return res;
    }

    public int count() {
        return this.count;
    }
}