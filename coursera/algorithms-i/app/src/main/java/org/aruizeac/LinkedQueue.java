package org.aruizeac;

public class LinkedQueue<T> implements Queue<T> {
    private int count;
    private LinkedNode<T> headNode, tailNode;

    public LinkedQueue() {
    }

    public void enqueue(T val) {
        count++;
        LinkedNode<T> node = new LinkedNode<>(val);
        if (tailNode == null) headNode = tailNode = node;
        else tailNode.next = tailNode = node;
    }

    public T dequeue() {
        if (headNode == null) return null;
        if (count > 0) count--;
        LinkedNode<T> node = headNode;
        headNode = headNode.next;
        if (headNode == null) tailNode = null;
        return node.getVal();
    }

    public T peek() {
        if (headNode == null) return null;
        return headNode.getVal();
    }

    public int count() {
        return this.count;
    }
}