package org.aruizeac;

import java.util.Iterator;

public class ArrayStack<T> implements Stack<T>, Iterator<T> {
    private T[] buf;
    private int pivot;

    public ArrayStack() {
        buf = (T[])new Object[1];
    }

    public ArrayStack(int capacity) {
        buf = (T[])new Object[capacity];
    }

    private void resizeBuffer(int capacity) {
        // repeated doubling - Golang-like slice implementation
        T[] tmpBuf = (T[])new Object[capacity];
        System.arraycopy(buf, 0, tmpBuf, 0, buf.length);
        buf = tmpBuf;
    }

    public void push(T val) {
        if (pivot >= buf.length) resizeBuffer(buf.length * 2);
        buf[pivot] = val;
        pivot++;
    }

    public T pop() {
        if (pivot == 0) return null;
        pivot--;
        T res = buf[pivot];
        buf[pivot] = null;
//        if (pivot > 0 && pivot == buf.length/4) resizeBuffer(buf.length/2);
        return res;
    }

    public T peek() {
        if (pivot == 0) return null;
        return buf[pivot-1];
    }

    public int count() {
        return pivot;
    }

    public boolean hasNext() {
        return count() > 0;
    }

    public T next() {
        return pop();
    }
}