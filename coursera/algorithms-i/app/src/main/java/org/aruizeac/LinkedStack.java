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

    public T peek() {
        if (headNode == null) return null;
        return headNode.getVal();
    }

    public int count() {
        return this.count;
    }

    public boolean hasNext() {
        return count > 0;
    }

    public T next() {
        return pop();
    }
}