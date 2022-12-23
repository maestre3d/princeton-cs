package org.aruizeac;

class LinkedNode<T> {
    private final T val;
    protected LinkedNode<T> next;

    protected LinkedNode(T v) {
        this.val = v;
    }

    protected T getVal() {
        return val;
    }

    protected void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    protected LinkedNode<T> getNext() {
        return next;
    }
}